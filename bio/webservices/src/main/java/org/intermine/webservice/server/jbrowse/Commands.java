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

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Alex
 *
 */
public final class Commands
{
    /**
     * @author Alex
     */
    public enum Action {
        /**
         * stats
         */
        STATS,
        /**
         * reference
         */
        REFERENCE,
        /**
         * features
         */
        FEATURES,
        /**
         * densities
         */
        DENSITIES
    }

    private Commands() {
        // don't
    }

    /**
     *  return null if not a suitable command.
     *
     * Interprets commands such as: /7227/features/X?start=100&end=200&type=Gene
     * as /(domain)/(action)/(section)?start=(start)&end=(end)&type=(featureType)
     * See: http://gmod.org/wiki/JBrowse_Configuration_Guide
     * #Writing_JBrowse-compatible_Web_Services
     *
     * @param domain domain
     * @param actionName action name
     * @param section section
     * @param realSection real section
     * @param parameters params
     * @return command
     */
    public static Command getCommand(
            String domain,
            String actionName,
            String section,
            String realSection,
            Map<String, String> parameters) {
        if (domain == null) {
            return null;
        }

        Integer start = getIntegerParam(parameters, "start");
        Integer end = getIntegerParam(parameters, "end");

        String tempSection = section;

        String featureType = parameters.get("type");
        if ("stats".equals(actionName) && !"global".equals(section)) {
            section = realSection;
        }

        Segment segment = Segment.makeSegment(section, start, end);
        Action action = null;

        if ("stats".equals(actionName)) {
            action = ("regionFeatureDensities".equals(tempSection)) ? Action.DENSITIES : Action.STATS;
        } else if ("features".equals(actionName)) {
            if ("true".equals(parameters.get("reference"))) {
                action = Action.REFERENCE;
            } else {
                action = Action.FEATURES;
            }
        }
        if (action == null) {
            return null;
        } else {
            return new Command(action, domain, featureType, segment, parameters);
        }
    }

    private static Integer getIntegerParam(Map<String, String> params, String key) {
        String numStr = params.get(key);
        if (numStr == null || "null".equalsIgnoreCase(numStr)) {
            return null;
        }
        return Integer.valueOf(numStr);
    }

}
