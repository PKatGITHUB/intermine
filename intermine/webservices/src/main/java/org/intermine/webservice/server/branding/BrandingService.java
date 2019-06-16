package org.intermine.webservice.server.branding;

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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.intermine.api.InterMineAPI;
import org.intermine.util.PropertiesUtil;
import org.intermine.webservice.JSONServiceSpring;
import org.intermine.webservice.model.Branding;

import static org.apache.commons.lang.StringEscapeUtils.escapeJava;

/**
 * Serve branding information so that a client can provide a branded
 * visual experience.
 * @author Alex Kalderimis
 *
 */
public class BrandingService extends JSONServiceSpring
{

    private static final String PROPERTIES_NEED_2_SECTIONS
        = "Branding properties should contain at least two sections. Skipping ";
    private static final String PREFIX = "branding.";
    private static final Logger LOG = Logger.getLogger(BrandingService.class);

    public Branding getBranding() {
        return branding;
    }

    private Branding branding;

    /** @param im The InterMine state object. **/
    public BrandingService(InterMineAPI im) {
        super(im);
        branding = new Branding();
    }

    @Override
    protected void execute() throws Exception {
        Properties props = PropertiesUtil.getPropertiesStartingWith(PREFIX, webProperties);
        Map<String, Object> brandingMap = new HashMap<String, Object>();
        for (Object key: props.keySet()) {
            String keyString = String.valueOf(key);
            String[] keyParts = keyString.split("\\.");
            if (keyParts.length < 2) {
                LOG.warn(PROPERTIES_NEED_2_SECTIONS + key);
                continue;
            }
            Queue<String> path = new LinkedList<String>();
            for (int i = 1; i < keyParts.length; i++) {
                path.add(keyParts[i]);
            }
            setProperty(brandingMap, path, props.getProperty(keyString));
        }
        branding.setProperties(brandingMap);
    }

    @Override
    public String getResultsKey() {
        return "properties";
    }

    @SuppressWarnings("unchecked")
    private void setProperty(
            final Map<String, Object> branding,
            final Queue<String> path,
            final String value) {
        String key = path.remove();
        if (path.isEmpty()) {
            branding.put(key, value);
        } else {
            Map<String, Object> thisLevel;
            if (!branding.containsKey(key)) {
                thisLevel = new HashMap<String, Object>();
                branding.put(key, thisLevel);
            } else {
                thisLevel = (Map<String, Object>) branding.get(key);
            }
            setProperty(thisLevel, path, value);
        }
    }

    @Override
    public void setFooter(){
        Date now = Calendar.getInstance().getTime();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd HH:mm::ss");
        String executionTime = dateFormatter.format(now);
        branding.setExecutionTime(executionTime);


        if (status >= 400) {
            branding.setWasSuccessful(false);
            branding.setError(escapeJava(errorMessage));
        } else {
            branding.setWasSuccessful(true);
        }
        branding.setStatusCode(status);
    }

}
