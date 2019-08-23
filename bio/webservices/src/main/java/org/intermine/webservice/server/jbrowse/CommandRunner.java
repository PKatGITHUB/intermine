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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.intermine.api.InterMineAPI;
import org.intermine.webservice.model.JBrowseFeatureDensityStats;
import org.intermine.webservice.model.JBrowseFeatureStats;
import org.intermine.webservice.model.JBrowseFeatures;

/**
 *
 * @author Alex
 *
 */
public abstract class CommandRunner
{

    private InterMineAPI api;

    /**
     * @param api InterMine API
     */
    public CommandRunner(InterMineAPI api) {
        this.api = api;
        this.jBrowseFeatures = new JBrowseFeatures();
        this.jBrowseFeatureStats = new JBrowseFeatureStats();
        this.jBrowseFeatureDensityStats = new JBrowseFeatureDensityStats();
    }

    /**
     * @return api InterMine API
     */
    protected InterMineAPI getAPI() {
        return api;
    }

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
     * @param className class name
     * @param im InterMine API
     * @return command runner
     */
    public static CommandRunner getRunner(String className, InterMineAPI im) {
        CommandRunner runner;
        try {
            @SuppressWarnings("unchecked")
            Class<CommandRunner> runnerCls = (Class<CommandRunner>) Class.forName(className);
            Constructor<CommandRunner> ctr = runnerCls.getConstructor(InterMineAPI.class);
            runner = ctr.newInstance(im);
        } catch (ClassCastException e) {
            throw new RuntimeException("Configuration is incorrect.", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find " + className);
        } catch (SecurityException e) {
            throw new RuntimeException("Not allowed to access " + className);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Cannot access constructor for " + className);
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot instantiate runner", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot access runner", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Cannot invoke constructor of " + className, e);
        }
        return runner;
    }


    /**
     * @param command command
     */
    public void run(Command command) {
        switch (command.getAction()) {
            case STATS:
                stats(command);
                break;
            case REFERENCE:
                reference(command);
                break;
            case FEATURES:
                features(command);
                break;
            case DENSITIES:
                densities(command);
                break;
            default:
                throw new IllegalArgumentException("Unknown action: " + command.getAction());
        }
    }

    /**
     * @param command command
     */
    public abstract void stats(Command command);

    /**
     * @param command command
     */
    public abstract void reference(Command command);

    /**
     * @param command command
     */
    public abstract void features(Command command);

    /**
     * @param command command
     */
    public abstract void densities(Command command);

}
