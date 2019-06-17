package org.intermine.webservice.server;

/*
 * Copyright (C) 2002-2019 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.intermine.api.InterMineAPI;
import org.intermine.webservice.JSONServiceSpring;
import org.intermine.webservice.model.Schema;
import org.intermine.webservice.server.core.JSONService;

import static org.apache.commons.lang.StringEscapeUtils.escapeJava;

/**
 * Serve up the list of schemata that we have.
 * @author Alexis Kalderimis
 *
 */
public class SchemaListService extends JSONServiceSpring
{

    public Schema getSchema() {
        return schema;
    }

    private Schema schema;

    /**
     * Constructor
     * @param im InterMine settings
     */
    public SchemaListService(InterMineAPI im) {
        super(im);
        schema = new Schema();
    }

    /*
     * @see org.intermine.webservice.server.WebService#execute(
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void execute() throws Exception {
        List<String> schemata =
            Arrays.asList(webProperties.getProperty("schema.filenames", "").split(","));
        schema.setSchemata(schemata);
    }

    @Override
    protected String getResultsKey() {
        return "schemata";
    }

    @Override
    protected String getDefaultFileName() {
        return "schemata.json";
    }

    @Override
    public void setFooter(){
        Date now = Calendar.getInstance().getTime();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm::ss");
        String executionTime = dateFormatter.format(now);
        schema.setExecutionTime(executionTime);


        if (status >= 400) {
            schema.setWasSuccessful(false);
            schema.setError(escapeJava(errorMessage));
        } else {
            schema.setWasSuccessful(true);
        }
        schema.setStatusCode(status);
    }
}
