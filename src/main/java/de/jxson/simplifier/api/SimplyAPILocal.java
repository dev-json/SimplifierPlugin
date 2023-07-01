package de.jxson.simplifier.api;

import de.jxson.simplifier.api.warp.Warp;
import de.jxson.simplifier.api.warp.WarpCategory;
import org.bukkit.Location;

public interface SimplyAPILocal {

    Warp getWarp(String name, WarpCategory category);
    void createWarp(String name, WarpCategory category, Location location);

}
