package de.jxson.simplifier.plugin.config;

import de.jxson.simplifier.plugin.SimplifierPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Config {

    private File file;
    private YamlConfiguration yamlConfiguration;

    private static final String PLUGIN_DIR = SimplifierPlugin.getPluginInstance().getDataFolder().getPath();

    public Config(String filename, String filePath)
    {
        this.file = new File(filePath, filename);
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(getConfigFile());
    }
    public Config(String filename) {
        this.file = new File(PLUGIN_DIR, filename);
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(getConfigFile());
    }

    public File getConfigFile() {
        return file;
    }

    public YamlConfiguration getConfiguration() {
        return yamlConfiguration;
    }

    public void set(String s, Object o) {
        this.yamlConfiguration.set(s, o);
        save();
    }

    public <T> T get(String s) {
        return (T) yamlConfiguration.get(s);
    }

    public Config getConfig() {
        return this;
    }

    /* Custom Logic - above is base from epochcore */

    public void addDefault(KeyValue<?>... values)
    {
        Arrays.stream(values).forEach(keyValue ->
        {
            if(this.yamlConfiguration.get(keyValue.path()) == null)
            {
                set(keyValue.path(), keyValue.object());
            }
        });
        save();
    }

    private void save()
    {
        try
        {
            this.yamlConfiguration.save(this.file);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
