package org.intermine.webservice.server.widget;

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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.intermine.api.InterMineAPI;
import org.intermine.api.profile.InterMineBag;
import org.intermine.api.profile.Profile;
import org.intermine.web.logic.widget.Widget;
import org.intermine.web.logic.widget.config.WidgetConfig;
import org.intermine.webservice.JSONServiceSpring;
import org.intermine.webservice.server.Format;
import org.intermine.webservice.server.core.JSONService;
import org.intermine.webservice.server.exceptions.BadRequestException;
import org.intermine.webservice.server.output.JSONFormatter;

import static org.apache.commons.lang.StringEscapeUtils.escapeJava;

/**
 * @author dbutano
 */
public abstract class WidgetService extends JSONServiceSpring
{

    public String getOutputString() {
        return outputString;
    }

    protected String outputString;

    /**
     * Construct the webservice controller.
     * @param im The API object.
     */
    public WidgetService(InterMineAPI im, Format format) {
        super(im, format);
        this.outputString = "{\n";
    }

    /**
     * Get the bag in question.
     * @param bagName The name of the bag.
     * @return The list.
     */
    protected InterMineBag retrieveBag(String bagName) {
        if (bagName == null || "".equals(bagName)) {
            return null;
        }
        Profile profile = getPermission().getProfile();
        InterMineBag imBag = im.getBagManager().getBag(profile, bagName);
        if (imBag == null) {
            throw new BadRequestException("You do not have access to a bag named " + bagName);
        }
        return imBag;
    }

    /**
     * Wrapper around addOutputInfo that makes sure we don't add empty values.
     * @param label The attribute name.
     * @param value The value of the attribute.
     */
    protected void addOutputAttribute(String label, String value) {
        if (StringUtils.isNotBlank(value)) {
            outputString = outputString.concat("\""+label+"\" : \""+value+"\"").concat("\n");
        }
    }

    /**
     * Add metadata about the list we are processing.
     * @param imBag The list object.
     */
    @SuppressWarnings("deprecation")
    protected void addOutputListInfo(InterMineBag imBag) {
        if (imBag != null) {
            outputString = outputString.concat("\"type\" : \""+imBag.getType()+"\"").concat("\n");
            outputString = outputString.concat("\"list\" : \""+imBag.getName()+"\"").concat("\n");
        }

        // TODO: remove requestedAt - we already output this info.
        outputString = outputString.concat("\"requestedAt\" : \""+(new Date().toGMTString())+"\"").concat("\n");
    }

    /**
     * Add metadata about the IDs we are processing. Use these IDs instead of bag or
     * populationBag.
     * @param ids intermine object ids to be analysed
     * @param populationIds intermine object ids to use as background population
     */
    protected void addOutputIdsInfo(String ids, String populationIds) {
        if (ids != null) {
            outputString = outputString.concat("\"ids\" : \""+populationIds+"\"").concat("\n");
        }
        if (populationIds != null) {
            outputString = outputString.concat("\"populationIds\" : \""+populationIds+"\"").concat("\n");
        }
    }

    /**
     * Add metadata about the widget we are using.
     * @param config The description of the widget.
     */
    protected void addOutputConfig(WidgetConfig config) {
        addOutputAttribute("title", config.getTitle());
        addOutputAttribute("description", config.getDescription());
    }

    /**
     * Add information about the filters as attributes to the result.
     * @param widgetConfig The description of the widgets.
     * @param filterSelectedValue The currently selected value.
     * @param imBag The list we are processing.
     * @param ids The ids we are processing, use instead of imBag
     */
    protected void addOutputFilter(
            WidgetConfig widgetConfig,
            String filterSelectedValue,
            InterMineBag imBag,
            String ids) {
        addOutputAttribute("filterLabel", widgetConfig.getFilterLabel());
        List<String> filters = getFilters(widgetConfig, imBag, ids);
        if (filters != null && !filters.isEmpty()) {
            addOutputAttribute("filters", StringUtils.join(filters, ","));
            addOutputAttribute("filterSelectedValue", filterSelectedValue);
        }
    }

    /**
     * Send results to the outside world.
     * @param widget The widget we are processing.
     * @throws Exception If we can't get results.
     */
    protected void addOutputResult(Widget widget) throws Exception {
        WidgetResultProcessor processor = getProcessor();
        Iterator<List<Object>> it = widget.getResults().iterator();
        outputString = outputString.concat("\"results\" : [").concat("\n");
        while (it.hasNext()) {
            List<Object> row = it.next();
            List<String> processed = processor.formatRow(row);
            if (formatIsJSON() && it.hasNext()) {
                processed.add(""); // TODO: this idiom is dumb. Expunge.
            }
            outputString = outputString.concat(processed.toString() + ",").concat("\n");
        }
        outputString = outputString.concat("]");
    }

    /**
     * @return A widget result processor for outputting results.
     */
    protected abstract WidgetResultProcessor getProcessor();

    private List<String> cachedFilters = null;

    private List<String> getFilters(WidgetConfig widgetConfig, InterMineBag imBag, String ids) {
        if (cachedFilters == null) {
            cachedFilters = widgetConfig.getFiltersValues(im.getObjectStore(), imBag, ids);
        }
        return cachedFilters;
    }

    /**
     * Get the default filter value for a widget.
     * @param widgetConfig The widget description.
     * @param imBag The bag we are thinking of running this widget on.
     * @param ids The ids we are thinking of running this widget on, use instead of imBag
     * @return A string (possibly null) which contains the default filter value.
     */
    protected String getDefaultFilterValue(WidgetConfig widgetConfig, InterMineBag imBag,
        String ids) {
        List<String> filters = getFilters(widgetConfig, imBag, ids);
        String defaultValue = null;
        if (filters != null && !filters.isEmpty()) {
            defaultValue = filters.get(0);
        }
        return defaultValue;
    }


}
