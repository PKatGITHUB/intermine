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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.io.StringReader;

import org.intermine.metadata.ConstraintOp;

/**
 * Representation of a constraint to a subquery in the PathQuery
 *
 * @author Daniela Butano
 */
public class PathConstraintSubquery extends PathConstraint
{
    /** List of valid ops for this type of constraint */
    public static final Set<ConstraintOp> VALID_OPS = new HashSet<ConstraintOp>(Arrays.asList(
                ConstraintOp.IN, ConstraintOp.NOT_IN));
    private PathQuery subquery;

    /**
     * Constructs a new PathConstraintSubquery. The path should be a normal path expression
     * with dots separating the parts. Do not use colons to represent outer joins, and do not
     * use square brackets to represent subclass constraints. The path will be checked for
     * format, but can only be verified once inside a PathQuery object by the
     * PathQuery.verifyQuery() method. This object is used to form a constraint to a subquery.
     *
     * @param path the path that the constraint is attached to
     * @param op the type of operation
     * @param subquery the subquery to constrain to
     * @throws NullPointerException if path, op, or subquery are null
     * @throws IllegalArgumentException if the path contains colons or square brackets, or is
     * otherwise in a bad format, or if the op is invalid for this constraint type
     */
    public PathConstraintSubquery(String path, ConstraintOp op, String subquery) {
        super(path, op);
        if (op == null) {
            throw new NullPointerException("Cannot construct a PathConstraintSubquery"
                    + " with a null op");
        }
        if (!VALID_OPS.contains(op)) {
            throw new IllegalArgumentException("PathConstraints to subqueries must use an op in"
                    + " the list \"" + VALID_OPS + "\"");
        }
        if (subquery == null) {
            throw new NullPointerException("Cannot create a constraint to a null subquery");
        }
        this.subquery = PathQueryBinding.unmarshalPathQuery(new StringReader(subquery),
                PathQuery.USERPROFILE_VERSION);
    }

    /**
     * Returns the subquery that the constraint is constraining to.
     *
     * @return a PathQuery
     */
    public PathQuery getSubquery() {
        return subquery;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return path + " " + op + " " + subquery.toString();
    }
}
