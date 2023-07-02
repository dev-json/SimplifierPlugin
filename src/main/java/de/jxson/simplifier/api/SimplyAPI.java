package de.jxson.simplifier.api;

import de.jxson.simplifier.api.setting.Setting;
import de.jxson.simplifier.api.warp.Warp;
import de.jxson.simplifier.api.warp.WarpCategory;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class SimplyAPI {

    private static SimplyAPILocal local;

    public SimplyAPI(SimplyAPILocal local)
    {
        SimplyAPI.local = local;
    }

    /* Warps section here */

    public static Warp getWarp(String name, WarpCategory category)
    {
        return local.getWarp(name, category);
    }

    public static void createWarp(String name, WarpCategory category, Location location)
    {
        local.createWarp(name, category, location);
    }

    public static void deleteWarp(Warp warp) {
        local.deleteWarp(warp);
    }

    public static void setWarpItem(ItemStack itemStack, Warp warp) {
        local.setWarpItem(itemStack, warp);
    }

    public HashMap<WarpCategory, List<Warp>> getWarps() {
        return local.getWarps();
    }

    public boolean isWarpExists(String name, WarpCategory category) {
        return local.isWarpExists(name, category);
    }

    public static void reloadWarps() {
        local.reloadWarps();
    }

    /* Settings section here */

    public static void registerSetting(Setting setting) {
        local.registerSetting(setting);
    }

    public static void unregisterSetting(Setting setting) {
        local.unregisterSetting(setting);
    }

}
