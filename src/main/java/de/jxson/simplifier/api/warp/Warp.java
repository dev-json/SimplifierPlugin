package de.jxson.simplifier.api.warp;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public interface Warp {

    String getName();

    WarpCategory getCategory();

    Location getLocation();

    ItemStack getItem();



}
