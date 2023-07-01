package de.jxson.simplifier.plugin.setting;

import de.jxson.simplifier.Globals;
import de.jxson.simplifier.api.setting.Setting;
import de.jxson.simplifier.plugin.config.Config;
import de.jxson.simplifier.plugin.config.KeyValue;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class SettingHandler {

    private final List<Setting> registeredSettings;
    private final Config config;

    public SettingHandler()
    {
        registeredSettings = new ArrayList<>();
        this.config = new Config("settings.yml");
    }

    public void registerSetting(Setting setting)
    {
        if(isSettingRegistered(setting)) return;
        setting.onRegister();
        Bukkit.getConsoleSender().sendMessage(Globals.PLUGIN_PREFIX +
                String.format("§7Register setting §9%s", setting.getName()));

        config.addDefault(
                new KeyValue<>("setting."+setting.getName()+".name", setting.getName()),
                new KeyValue<>("setting."+setting.getName()+".description", setting.getDescription()),
                new KeyValue<>("setting."+setting.getName()+".enabled", setting.isEnabled())

        );
        ((SettingImpl) setting).setEnabled(this.config.getConfiguration().getBoolean("setting."+setting.getName()+".enabled"));

        registeredSettings.add(setting);
    }

    public void toggleSetting(Setting setting, boolean newState)
    {
        if(!getRegisteredSettings().contains(setting)) return;
        ((SettingImpl) setting).setEnabled(newState);
        this.config.set("setting."+setting.getName()+".enabled", newState);
    }

    public void unregisterSetting(Setting setting)
    {
        if(!registeredSettings.contains(setting)) return;
        setting.onUnregister();
        Bukkit.getConsoleSender().sendMessage(Globals.PLUGIN_PREFIX +
                String.format("§7Unregister setting §9%s", setting.getName()));
        registeredSettings.remove(setting);
    }

    public boolean isSettingRegistered(Setting setting)
    {
        return registeredSettings.contains(setting);
    }

    public List<Setting> getRegisteredSettings()
    {
        return registeredSettings;
    }
}
