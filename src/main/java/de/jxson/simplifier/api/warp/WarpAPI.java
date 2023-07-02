package de.jxson.simplifier.api.warp;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public interface WarpAPI {

    Warp getWarp(String name, WarpCategory category);

    void reloadWarps();
    void createWarp(String name, WarpCategory category, Location location);
    void deleteWarp(Warp warp);
    void setWarpItem(ItemStack itemStack, Warp warp);

    HashMap<WarpCategory, List<Warp>> getWarps();

    boolean isWarpExists(String name, WarpCategory category);


}
