package de.jxson.simplifier.api;

import de.jxson.simplifier.api.warp.Warp;
import de.jxson.simplifier.api.warp.WarpCategory;
import org.bukkit.Location;

public class SimplyAPI {

    private static SimplyAPILocal local;

    public SimplyAPI(SimplyAPILocal local)
    {
        SimplyAPI.local = local;
    }

    public static Warp getWarp(String name, WarpCategory category)
    {
        return local.getWarp(name, category);
    }

    public static void createWarp(String name, WarpCategory category, Location location)
    {
        local.createWarp(name, category, location);
    }

}
