package org.intermine.pathquery;

/*
 * Copyright (C) 2002-2016 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.intermine.metadata.ConstraintOp;
import org.intermine.metadata.Model;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Extension of DefaultHandler to handle parsing PathQuery objects
 *
 * @author Mark Woodbridge
 * @author Kim Rutherford
 * @author Thomas Riley
 * @author Matthew Wakeling
 */
public class PathQueryHandler extends DefaultHandler
{
    protected final Map<String, PathQuery> queries;
    //protected String queryName;
    protected Stack<String> queryNameStack = new Stack<String>();
    //protected PathQuery query;
    protected Stack<PathQuery> queryStack = new Stack<PathQuery>();
    //protected String constraintLogic = null;
    protected Stack<String> constraintLogicStack = new Stack<String>();
    protected String currentNodePath = null;
    private Model model = null;
    protected int version;
    private List<PathConstraintSubclass> questionableSubclasses;
    /** This is a list of String type descriptions that are attribute types */
    public static final Set<String> ATTRIBUTE_TYPES = new HashSet<String>(
            Arrays.asList("boolean", "float", "double", "short", "int", "long",
                    "Boolean", "Float", "Double", "Short", "Integer", "Long",
                    "BigDecimal", "Date", "String"));
    private StringBuilder valueBuffer = null;
    //protected String constraintPath = null;
    protected Stack<String> constraintPathStack = new Stack<String>();
    //protected Map<String, String> constraintAttributes = null;
    protected Stack<Map<String, String>> constraintAttributesStack
        = new Stack<Map<String, String>>();
    protected Collection<String> constraintValues = null;
    protected String constraintCode = null;
    protected boolean isSubclassConstraint = false;
    protected boolean isConstraintAttribute = false;
    private static final Logger LOG = Logger.getLogger(PathQueryHandler.class);

    /**
     * Collection of models used for lookup before resorting to
     * Model.getInstanceByName(). This enables multiple models to be used with
     * the same name.
     */
    private final Map<String, Model> models = new HashMap<String, Model>();

    /**
     * Constructor
     *
     * @param queries
     *            Map from query name to PathQuery
     * @param version
     *            the version of the xml, an attribute on the profile manager
     */
    public PathQueryHandler(Map<String, PathQuery> queries, int version) {
        this.queries = queries;
        this.version = version;
    }

    /**
     * Constructor.
     * @param queries The accumulator.
     * @param version The path-query format version.
     * @param model A default model to use, in preference to the model retrieved
     *              by <code>Model.getInstanceByName()</code>.
     */
    public PathQueryHandler(Map<String, PathQuery> queries, int version, Model model) {
        this(queries, version);
        if (model != null) {
            addModel(model);
        }
    }

    /** @param m The model to add. **/
    public void addModel(Model m) {
        String name = m.getName();
        models.put(name, m);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attrs) throws SAXException {
        if (valueBuffer != null) {
            throw new SAXException("Cannot have any tags inside a value tag");
        }
        if (isConstraintAttribute
                && !("value".equals(qName) || "nullValue".equals(qName) || "query".equals(qName))) {
            throw new SAXException(
                    "Cannot have anything other than value tag or query (for subquery) "
                    + "inside a constraint");
        }
        if ("query-list".equals(qName)) {
            // Do nothing
        } else if ("query".equals(qName)) {
            isConstraintAttribute = false;
            queryNameStack.push(validateName(attrs.getValue("name")));
            String modelName = attrs.getValue("model");
            if (models.containsKey(modelName)) {
                model = models.get(modelName);
            } else {
                try {
                    model = Model.getInstanceByName(attrs.getValue("model"));
                } catch (Exception e) {
                    throw new SAXException(e);
                }
            }
            PathQuery query = new PathQuery(model);
            queryStack.push(query);

            if (attrs.getValue("title") != null
                    && !attrs.getValue("title").isEmpty()) {
                query.setTitle(attrs.getValue("title"));
            }

            final String longDescription = attrs.getValue("longDescription");
            if (StringUtils.isNotBlank(longDescription)) {
                query.setDescription(longDescription);
            }
            String view = attrs.getValue("view");
            if (view != null) {
                if (view.contains(":")) {
                    // This is an old style query, and we need to convert the
                    // colons into outer
                    // joins
                    String[] viewPathArray = PathQuery.SPACE_SPLITTER
                            .split(view.trim());
                    for (String viewPath : viewPathArray) {
                        setOuterJoins(query, viewPath);
                    }
                    view = view.replace(':', '.');
                }
                query.addViewSpaceSeparated(view);
            }
            String so = attrs.getValue("sortOrder");
            if (!StringUtils.isBlank(so)) {
                if (so.indexOf(' ') < 0) {
                    // be accomodating of input as possible - assume asc.
                    so += " asc";
                }
                query.addOrderBySpaceSeparated(so);
            }
            String constraintLogic = (attrs.getValue("constraintLogic") != null )
                    ? attrs.getValue("constraintLogic") : StringUtils.EMPTY;
            constraintLogicStack.push(constraintLogic);
            questionableSubclasses = new ArrayList<PathConstraintSubclass>();
        } else if ("node".equals(qName)) {
            // There's a node tag, so all constraints inside must inherit this
            // path. Set it in a
            // variable, and reset the variable to null when we see the end tag.
            PathQuery query = queryStack.peek();
            currentNodePath = attrs.getValue("path");
            if (currentNodePath.contains(":")) {
                setOuterJoins(query, currentNodePath);
                currentNodePath = currentNodePath.replace(':', '.');
            }
            String type = attrs.getValue("type");
            if ((type != null)
                    && (!ATTRIBUTE_TYPES.contains(type))
                    && (currentNodePath.contains(".") || currentNodePath
                            .contains(":"))) {
                PathConstraintSubclass subclass = new PathConstraintSubclass(
                        currentNodePath, type);
                query.addConstraint(subclass);
                questionableSubclasses.add(subclass);
            }
        } else if ("constraint".equals(qName)) {
            isConstraintAttribute = true;
            String path = attrs.getValue("path");
            if (currentNodePath != null) {
                if (path != null) {
                    throw new SAXException(
                            "Cannot set path in a constraint inside a node");
                }
                path = currentNodePath;
            }
            String code = attrs.getValue("code");
            String type = attrs.getValue("type");
            if (type != null) {
                PathQuery query = queryStack.peek();
                query.addConstraint(new PathConstraintSubclass(path, type));
                isSubclassConstraint = true;
            } else {
                path = path.replace(':', '.');
                String constraintPath = path;
                constraintPathStack.push(constraintPath);
                HashMap<String, String> constraintAttributes = new HashMap<String, String>();
                for (int i = 0; i < attrs.getLength(); i++) {
                    constraintAttributes.put(attrs.getQName(i),
                            attrs.getValue(i));
                }
                constraintAttributesStack.push(constraintAttributes);
                constraintValues = new LinkedHashSet<String>();
                constraintCode = code;
            }
        } else if ("pathDescription".equals(qName)) {
            String pathString = attrs.getValue("pathString");
            String description = attrs.getValue("description");

            // Descriptions should only refer to classes that appear in the
            // view, which we have
            // already read. This check makes sure the description is for a
            // class that has
            // attributes on the view list before adding it. We ignore invalid
            // descriptions to
            // cope with legacy bad validation of query XML.
            if (pathString.endsWith(".")) {
                throw new SAXException("Invalid path '" + pathString
                        + "' for description: " + description);
            }
            String pathToCheck = pathString + ".";
            PathQuery query = queryStack.peek();
            for (String viewString : query.getView()) {
                if (viewString.startsWith(pathToCheck)) {
                    query.setDescription(pathString, description);
                    break;
                }
            }
        } else if ("join".equals(qName)) {
            String pathString = attrs.getValue("path");
            String type = attrs.getValue("style");
            PathQuery query = queryStack.peek();
            if ("INNER".equals(type.toUpperCase())) {
                query.setOuterJoinStatus(pathString, OuterJoinStatus.INNER);
            } else if ("OUTER".equals(type.toUpperCase())) {
                query.setOuterJoinStatus(pathString, OuterJoinStatus.OUTER);
            } else {
                throw new SAXException("Unknown join style " + type
                        + " for path " + pathString);
            }
        } else if ("value".equals(qName)) {
            valueBuffer = new StringBuilder();
        }
    }

    /**
     * Process a constraint from the xml attributes.
     *
     * @param q
     *            the PathQuery, to enable creating Path objects
     * @param path
     *            the path of the constraint to create
     * @param attrs
     *            the XML attributes
     * @param values
     *            the enclosed values
     * @return a PathConstraint object
     * @throws SAXException
     *             if something is wrong
     */
    public PathConstraint processConstraint(PathQuery q, String path,
            Map<String, String> attrs, Collection<String> values)
        throws SAXException {

        if (path == null) {
            throw new SAXException("Bad constraint: Path is null. "
                    + q.toString());
        }

        ConstraintOp constraintOp = ConstraintOp.getConstraintOp(attrs
                .get("op"));
        if (constraintOp == null) {
            // Handle any allowed synonyms.
            String origOp = attrs.get("op");
            if ("IS EMPTY".equals(origOp)) { // Synonym for IS NULL
                constraintOp = ConstraintOp.IS_NULL;
            } else if ("IS NOT EMPTY".equals(origOp)) { // Synonym for IS NOT
                                                        // NULL
                constraintOp = ConstraintOp.IS_NOT_NULL;
            }
        }
        // TODO: work out if this is pointless busy-work.
        if (ConstraintOp.CONTAINS.equals(constraintOp)) {
            constraintOp = ConstraintOp.CONTAINS;
        }
        if (ConstraintOp.DOES_NOT_CONTAIN.equals(constraintOp)) {
            constraintOp = ConstraintOp.DOES_NOT_CONTAIN;
        }

        if (PathConstraintRange.VALID_OPS.contains(constraintOp)
                && !values.isEmpty()) {
            Collection<String> valuesCollection = new LinkedHashSet<String>();
            for (String value : values) {
                valuesCollection.add(value.trim());
            }
            return new PathConstraintRange(path, constraintOp, valuesCollection);
        } else if (PathConstraintMultitype.VALID_OPS.contains(constraintOp)
                && !values.isEmpty()) {
            Collection<String> typesCollection = new LinkedHashSet<String>();
            for (String value : values) {
                typesCollection.add(value.trim());
            }
            return new PathConstraintMultitype(path, constraintOp,
                    typesCollection);
        } else if (PathConstraintAttribute.VALID_OPS.contains(constraintOp)) {
            boolean isLoop = (attrs.get("loopPath") != null);
            if (PathConstraintLoop.VALID_OPS.contains(constraintOp)) {
                try {
                    Path constraintPath2 = q.makePath(path);
                    if (!constraintPath2.endIsAttribute()) {
                        isLoop = true;
                    }
                } catch (PathException e) {
                    if (isLoop) {
                        // A genuine error - rethrow
                        throw new SAXException(
                                "Illegal loop constraint definition", e);
                    } else {
                        // Not actually a loop constraint. Ignore.
                        LOG.error("Cannot recognise path in constraint: "
                                + path, e);
                    }
                }
            }
            if (isLoop) {
                String loopPath = attrs.get("loopPath");
                if (loopPath == null) {
                    loopPath = attrs.get("value");
                }
                loopPath = loopPath.replace(':', '.');
                return new PathConstraintLoop(path, constraintOp, loopPath);
            } else {
                String constraintValue = attrs.get("value");
                return new PathConstraintAttribute(path, constraintOp,
                        constraintValue);
            }
        } else if (PathConstraintNull.VALID_OPS.contains(constraintOp)) {
            return new PathConstraintNull(path, constraintOp);
        } else if (PathConstraintBag.VALID_OPS.contains(constraintOp)) {
            String bag = attrs.get("value");
            String ids = attrs.get("ids");
            if (bag != null) {
                return new PathConstraintBag(path, constraintOp, bag);
            } else if (ids != null) {
                String[] idArray = ids.split(",");
                Collection<Integer> idsCollection = new LinkedHashSet<Integer>();
                for (String id : idArray) {
                    try {
                        idsCollection.add(Integer.valueOf(id.trim()));
                    } catch (NumberFormatException e) {
                        throw new SAXException(
                                "List of IDs contains invalid integer: " + id,
                                e);
                    }
                }
                return new PathConstraintIds(path, constraintOp, idsCollection);
            } else {
                /*throw new SAXException("Invalid query: operation was: "
                        + constraintOp
                        + " but no bag or ids were provided (from text \""
                        + attrs.get("op") + "\", attributes: " + attrs + ")");*/
                return null;
            }

        } else if (PathConstraintMultiValue.VALID_OPS.contains(constraintOp)) {
            Collection<String> valuesCollection = new LinkedHashSet<String>();
            for (String value : values) {
                valuesCollection.add(value == null ? value : value.trim());
            }
            return new PathConstraintMultiValue(path, constraintOp,
                    valuesCollection);
        } else if (ConstraintOp.LOOKUP.equals(constraintOp)) {
            String lookup = attrs.get("value");
            String extraValue = attrs.get("extraValue");
            if (StringUtils.isBlank(extraValue)) {
                extraValue = null;
            }
            return new PathConstraintLookup(path, lookup, extraValue);
        } else {
            throw new SAXException("Invalid operation type: " + constraintOp
                    + " (from text \"" + attrs.get("op") + "\", attributes: "
                    + attrs + ")");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endElement(String uri, String localName, String qName)
        throws SAXException {
        if ("query".equals(qName)) {
            PathQuery query = queryStack.peek();
            String constraintLogic = constraintLogicStack.pop();
            if (constraintLogic != null && !constraintLogic.isEmpty()) {
                query.setConstraintLogic(constraintLogic);
            }

            if (query.isValid()) {
                try {
                    Map<String, String> subClasses = query.getSubclasses();
                    for (PathConstraintSubclass subclass : questionableSubclasses) {
                        Map<String, String> trimmedSubclasses = new HashMap<String, String>(
                                subClasses);
                        trimmedSubclasses.remove(subclass.getPath());
                        Path path = new Path(model, subclass.getPath(),
                                trimmedSubclasses);
                        if (path.getEndClassDescriptor().getUnqualifiedName()
                                .equals(subclass.getType())) {
                            query.removeConstraint(subclass);
                        }
                    }
                } catch (PathException e) {
                    // Shouldn't ever happen, as the query is valid
                    throw new Error("Error", e);
                }
            }
            String queryName = queryNameStack.pop();
            if (queryStack.size() == 1) {
                queries.put(queryName, query);
                queryStack.pop();
            }
        } else if ("node".equals(qName)) {
            currentNodePath = null;
        } else if ("constraint".equals(qName)) {
            isConstraintAttribute = false;
            if (isSubclassConstraint) {
                isSubclassConstraint = false;
                return;
            }
            PathQuery query = queryStack.peek();
            String constraintPath = constraintPathStack.pop();
            Map<String, String> constraintAttributes = constraintAttributesStack.pop();
            PathConstraint constraint = processConstraint(query,
                    constraintPath, constraintAttributes, constraintValues);
            if (constraint == null) {
                constraint = new PathConstraintSubquery(constraintPath,
                        ConstraintOp.getConstraintOp(constraintAttributes.get("op")), query);
                queryStack.pop();
                query = queryStack.peek();
            }
            if (constraintCode == null) {
                query.addConstraint(constraint);
            } else {
                query.addConstraint(constraint, constraintCode);
            }
            constraintCode = null;
            constraintPath = null;
        } else if ("value".equals(qName)) {
            if (valueBuffer == null || valueBuffer.length() < 1) {
                throw new NullPointerException(
                        "No value provided in value tag."
                                + " Failed for template query: " + queryNameStack.peek()
                                + " on constraint: " + constraintPathStack.peek());
            }
            constraintValues.add(valueBuffer.toString());
            valueBuffer = null;
        } else if ("nullValue".equals(qName)) {
            constraintValues.add(null);
            valueBuffer = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        if (valueBuffer != null) {
            valueBuffer.append(ch, start, length);
        }
    }

    /**
     * Convert a List of Objects to a List of Strings using toString
     *
     * @param list
     *            the Object List
     * @return the String list
     */
    protected List<String> toStrings(List<Object> list) {
        List<String> strings = new ArrayList<String>();
        for (Object o : list) {
            strings.add(o.toString());
        }
        return strings;
    }

    /**
     * Checks that the query has a name and that there's no name duplicates and
     * appends a number to the name if there is.
     *
     * @param name
     *            the query name
     * @return the validated query name
     */
    protected String validateName(String name) {
        String validatedName = name;
        if (name == null || name.length() == 0) {
            validatedName = "unnamed_query";
        }
        if (queries.containsKey(validatedName)) {
            int i = 1;
            while (true) {
                String testName = validatedName + "_" + i;
                if (!queries.containsKey((testName))) {
                    return testName;
                }
                i++;
            }
        }
        return validatedName;
    }

    /**
     * Given a path that may contain ':' characters to represent outer joins,
     * find each : separated segment and set the status for that join to OUTER.
     * NOTE this method will change the query parameter handed to it.
     *
     * @param query
     *            the query to set join styles
     * @param path
     *            a path that may contain outer joins represented by ':'
     */
    protected void setOuterJoins(PathQuery query, String path) {
        int from = 0;
        while (path.indexOf(':', from) != -1) {
            int colonPos = path.indexOf(':', from);
            int nextDot = path.replace(':', '.').indexOf('.', colonPos + 1);
            String outerJoin = nextDot == -1 ? path : path
                    .substring(0, nextDot);
            query.setOuterJoinStatus(outerJoin.replace(':', '.'),
                    OuterJoinStatus.OUTER);
            from = colonPos + 1;
        }
    }
}
