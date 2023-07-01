package de.jxson.simplifier.plugin;

import de.jxson.simplifier.plugin.commands.SimplifierCommand;
import de.jxson.simplifier.plugin.commands.WarpCommand;
import de.jxson.simplifier.plugin.menu.MenuClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimplifierPlugin extends JavaPlugin {

    private static SimplifierPlugin pluginInstance;
    private SimplyAPIIntegration integration;

    @Override
    public void onEnable() {
        SimplifierPlugin.pluginInstance = this;

        this.integration = new SimplyAPIIntegration();

        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("sfy").setExecutor(new SimplifierCommand());

        Bukkit.getPluginManager().registerEvents(new MenuClickEvent(), this);

    }

    @Override
    public void onDisable() {
        this.integration.onDisable();
    }

    public static SimplifierPlugin getPluginInstance() {
        return pluginInstance;
    }

    public SimplyAPIIntegration getIntegration() {
        return integration;
    }
}
