package org.intermine.webservice.server.jbrowse;

/*
 * Copyright (C) 2002-2019 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.intermine.api.InterMineAPI;
import org.intermine.webservice.JSONServiceSpring;
import org.intermine.webservice.model.JBrowseFeatureDensityStats;
import org.intermine.webservice.model.JBrowseFeatureStats;
import org.intermine.webservice.model.JBrowseFeatures;
import org.intermine.webservice.server.Format;
import org.intermine.webservice.server.core.JSONService;
import org.intermine.webservice.server.exceptions.ResourceNotFoundException;
import org.intermine.webservice.server.exceptions.ServiceException;
import org.intermine.webservice.server.output.JSONFormatter;

/**
 *
 * @author Alex
 *
 */
public class Endpoint extends JSONServiceSpring
{

    private static final String CMD_RUNNER = "webservice.jbrowse.commandrunner.";


    private String domain;
    private String actionName;
    private String section;
    private String realSection;



    protected JBrowseFeatures jBrowseFeatures;

    protected JBrowseFeatureStats jBrowseFeatureStats;

    public JBrowseFeatures getjBrowseFeatures() {
        return jBrowseFeatures;
    }

    public JBrowseFeatureStats getjBrowseFeatureStats() {
        return jBrowseFeatureStats;
    }

    public JBrowseFeatureDensityStats getjBrowseFeatureDensityStats() {
        return jBrowseFeatureDensityStats;
    }

    protected JBrowseFeatureDensityStats jBrowseFeatureDensityStats;

    /**
     *
     * @param im InterMine API
     */
    public Endpoint(InterMineAPI im, Format format, String domain, String actionName, String section, String realSection) {
        super(im, format);
        this.domain = domain;
        this.actionName = actionName;
        this.section = section;
        this.realSection = realSection;
    }

    @Override
    protected void execute() throws ServiceException {
        Command cmd = getCommand();

        CommandRunner runner = CommandRunner.getRunner(getProperty(CMD_RUNNER
                + im.getModel().getName()), im);

        runner.run(cmd);

        jBrowseFeatures = runner.getjBrowseFeatures();
        jBrowseFeatureStats = runner.getjBrowseFeatureStats();
        jBrowseFeatureDensityStats = runner.getjBrowseFeatureDensityStats();
    }

    // Never null
    private Command getCommand() throws ServiceException {
        @SuppressWarnings("unchecked")
        Map<String, String[]> params = request.getParameterMap();
        Command cmd = Commands.getCommand(domain, actionName, section, realSection, singlefyMap(params));
        if (cmd == null) {
            throw new ResourceNotFoundException(request.getPathInfo());
        }
        return cmd;
    }

    private static <K, V> Map<K, V> singlefyMap(Map<K, V[]> input) {
        Map<K, V> output = new HashMap<K, V>();
        for (Entry<K, V[]> entry: input.entrySet()) {
            V[] values = entry.getValue();
            if (values.length == 1) {
                output.put(entry.getKey(), values[0]);
            }
        }
        return output;
    }

}
