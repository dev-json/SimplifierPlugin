package de.jxson.simplifier.plugin;

import de.jxson.simplifier.api.SimplyAPI;
import de.jxson.simplifier.api.SimplyAPILocal;
import de.jxson.simplifier.api.warp.Warp;
import de.jxson.simplifier.api.warp.WarpCategory;
import de.jxson.simplifier.plugin.setting.SettingHandler;
import de.jxson.simplifier.plugin.setting.custom.*;
import de.jxson.simplifier.plugin.warp.WarpHandler;
import org.bukkit.Location;

public class SimplyAPIIntegration implements SimplyAPILocal {
    private SettingHandler settingHandler;
    private WarpHandler warpHandler;

    public SimplyAPIIntegration()
    {
        new SimplyAPI(this);

        this.settingHandler = new SettingHandler();
        this.settingHandler.registerSetting(new ColoredChat());
        this.settingHandler.registerSetting(new HeartEmoji());
        this.settingHandler.registerSetting(new BeehiveChecker());
        this.settingHandler.registerSetting(new BatSpawning());
        this.settingHandler.registerSetting(new PhantomSpawning());

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
    public void createWarp(String name, WarpCategory category, Location location) {
        warpHandler.addWarp(name, location, category);
    }

    public SettingHandler getSettingHandler() {
        return settingHandler;
    }

    public WarpHandler getWarpHandler() {
        return warpHandler;
    }
}
