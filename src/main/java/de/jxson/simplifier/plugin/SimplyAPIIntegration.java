package de.jxson.simplifier.plugin;

import de.jxson.simplifier.api.SimplyAPI;
import de.jxson.simplifier.api.SimplyAPILocal;
import de.jxson.simplifier.api.setting.Setting;
import de.jxson.simplifier.api.warp.Warp;
import de.jxson.simplifier.api.warp.WarpCategory;
import de.jxson.simplifier.plugin.setting.SettingHandler;
import de.jxson.simplifier.plugin.setting.custom.*;
import de.jxson.simplifier.plugin.warp.WarpHandler;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class SimplyAPIIntegration implements SimplyAPILocal {
    private SettingHandler settingHandler;
    private WarpHandler warpHandler;

    public SimplyAPIIntegration()
    {
        new SimplyAPI(this);

        this.settingHandler = new SettingHandler();
        SimplyAPI.registerSetting(new ColoredChat());
        SimplyAPI.registerSetting(new HeartEmoji());
        SimplyAPI.registerSetting(new BeehiveChecker());
        SimplyAPI.registerSetting(new BatSpawning());
        SimplyAPI.registerSetting(new PhantomSpawning());

        this.warpHandler = new WarpHandler();
    }

    public void onDisable()
    {

    }

    @Override
    public Warp getWarp(String name, WarpCategory category) {
        return warpHandler.getWarp(name, category);
    }

    @Override
    public void reloadWarps() {
        this.warpHandler.reload();
    }

    @Override
    public void createWarp(String name, WarpCategory category, Location location) {
        warpHandler.addWarp(name, location, category);
    }

    @Override
    public void deleteWarp(Warp warp) {
        warpHandler.deleteWarp(warp);
    }

    @Override
    public void setWarpItem(ItemStack itemStack, Warp warp) {
        warpHandler.setItem(itemStack, warp);
    }

    @Override
    public HashMap<WarpCategory, List<Warp>> getWarps() {
        return warpHandler.getFoundWarps();
    }

    @Override
    public boolean isWarpExists(String name, WarpCategory category) {
        return warpHandler.warpExists(name, category);
    }

    @Override
    public List<Setting> getSettings() {
        return null;
    }

    @Override
    public void registerSetting(Setting setting) {
        settingHandler.registerSetting(setting);
    }

    @Override
    public void unregisterSetting(Setting setting) {
        settingHandler.unregisterSetting(setting);
    }

    public SettingHandler getSettingHandler() {
        return settingHandler;
    }

    public WarpHandler getWarpHandler() {
        return warpHandler;
    }
}
