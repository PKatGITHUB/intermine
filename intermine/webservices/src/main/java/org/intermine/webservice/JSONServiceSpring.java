package org.intermine.webservice;

import org.intermine.api.InterMineAPI;
import org.intermine.api.bag.BagManager;
import org.intermine.metadata.Model;
import org.intermine.webservice.server.output.JSONFormatter;

import java.util.HashMap;
import java.util.Map;

public class JSONServiceSpring extends WebServiceSpring {

    protected final BagManager bagManager;
    protected final Model model;

    private final Map<String, String> kvPairs = new HashMap<String, String>();

    /**
     * Constructor
     * @param im The InterMine configuration object.
     */
    public JSONServiceSpring(InterMineAPI im) {
        super(im);
        bagManager = im.getBagManager();
        model = im.getObjectStore().getModel();
    }

    /**
     * @return The key for the results property.
     */
    protected String getResultsKey() {
        return null;
    }

    /**
     * @return Whether to treat this as a lazy list.
     */
    protected boolean lazyList() {
        return false;
    }

    /**
     * Get the header attributes to apply to the formatter.
     * @return A map from string to object.
     */
    protected void setHeadersPostInit() {
        String resultsKey = getResultsKey();
        if (resultsKey != null) {
            String intro = "\"" + resultsKey + "\":";
            if (lazyList()) {
                intro += "[";
                responseHeaders.add(JSONFormatter.KEY_OUTRO, "]");
            }
            responseHeaders.add(JSONFormatter.KEY_INTRO, intro);
        }
        if (formatIsJSONP()) {
            responseHeaders.add(JSONFormatter.KEY_CALLBACK, getCallback());
        }
        responseHeaders.add(JSONFormatter.KEY_KV_PAIRS, kvPairs.toString());
    }

}
