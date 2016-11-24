package org.intermine.webservice.server.query.result;

/*
 * Copyright (C) 2002-2016 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.intermine.webservice.server.exceptions.ServiceException;
import org.xml.sax.SAXParseException;

/**
 * XMLValidator is class that validates xml string according an XML Schema at specified url.
 * @author Radek Štěpán after Jakub Kulaviak
 **/
public class XMLValidator
{
    private XMLValidatorErrorHandler errorHandler = null;
    private static final Logger LOG = Logger.getLogger(XMLValidator.class);

    /**
     * Validates an XML string according to an XML Schema at a given URL.
     *
     * @param xmlToValidate an XML string.
     * @param xmlSchemaUrl the URL of an XML Schema.
     */
    public void validate(String xmlToValidate, String xmlSchemaUrl) {
        String xml = xmlToValidate;
        errorHandler = new XMLValidatorErrorHandler();

        try {
            // changed to use baseURL instead of current URL because tomcat uses a different
            // URL when in docker. There is no way this could not work.
            LOG.info("Using the xmlSchemaUrl " + xmlSchemaUrl);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            URL schemaLocation = new URL(xmlSchemaUrl);
            Reader schemaReader = new InputStreamReader(schemaLocation.openStream());
            Schema schema = factory.newSchema(new StreamSource(schemaReader));

            Validator validator = schema.newValidator();
            validator.setErrorHandler(errorHandler);
            validator.validate(new StreamSource(new StringReader(xml)));

        } catch (SAXParseException e) {
            LOG.debug(e);
        } catch (Exception e) {
            throw new ServiceException("XML validation failed. " + xmlSchemaUrl, e);
        }
    }

    /**
     * Returns errors occurred during parsing xml.
     * @return errors
     */
    public List<String> getErrors() {
        return errorHandler.getErrors();
    }

    /**
     * Returns warnings occurred during parsing xml.
     * @return warning
     */
    public List<String> getWarnings() {
        return errorHandler.getWarnings();
    }

    /**
     * Returns errors and warning occurred during parsing xml.
     * @return errors and warning
     */
    public List<String> getErrorsAndWarnings() {
        List<String> ret = new ArrayList<String>();
        for (String s : getErrors()) {
            ret.add(s);
        }
        for (String s : getWarnings()) {
            ret.add(s);
        }
        return ret;
    }
}
