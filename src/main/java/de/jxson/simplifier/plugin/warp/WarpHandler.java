package de.jxson.simplifier.plugin.warp;

import de.jxson.simplifier.api.warp.Warp;
import de.jxson.simplifier.api.warp.WarpCategory;
import de.jxson.simplifier.plugin.config.Config;
import de.jxson.simplifier.plugin.menu.sub.warps.WarpsPerCategoryMenu;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WarpHandler
{

    private HashMap<WarpCategory, List<Warp>> foundWarps;
    private Config config;

    public WarpHandler()
    {
        this.config = new Config("warps.yml");
        this.foundWarps = new HashMap<>();
        loadWarps();
    }

    private void loadWarps()
    {
        this.foundWarps = new HashMap<>();
        YamlConfiguration configuration = this.config.getConfiguration();
        ConfigurationSection categorySection = configuration.getConfigurationSection("warps.");

        if(categorySection == null) return;

        for(String category : categorySection.getKeys(false))
        {
            ConfigurationSection warpSections =  configuration.getConfigurationSection("warps." + category + ".instance.");
            List<Warp> warpList = new ArrayList<>();

            if(warpSections == null) continue;

            for(String warpName : warpSections.getKeys(false))
            {
                WarpImpl warp = new WarpImpl(warpName);
                warp.setWarpCategory(WarpCategory.valueOf(category));
                warp.setWarpLocation(configuration.getLocation("warps."+category+".instance."+warpName+".location"));
                warp.setItemStack(configuration.getItemStack("warps."+category+".instance."+warpName+".item"));
                warpList.add(warp);
            }
            foundWarps.put(WarpCategory.valueOf(category), warpList);
        }
    }

    public void addWarp(String name, Location location, WarpCategory category)
    {
        this.config.set("warps."+category+".instance."+name+".location", location);
        this.config.set("warps."+category+".instance."+name+".item", WarpImpl.DEFAULT_ITEM);
        loadWarps();
    }

    public void deleteWarp(Warp warp)
    {
        this.config.set("warps."+warp.getCategory()+".instance."+warp.getName(), null);
        loadWarps();
    }

    public void setItem(ItemStack itemStack, Warp warp)
    {
        this.config.set("warps."+warp.getCategory()+".instance."+warp.getName()+".item", itemStack);
        loadWarps();
    }

    public Warp getWarp(String name, WarpCategory category)
    {
        //If no warp in this category exists
        if(foundWarps.get(category) == null)
            return null;

        for(Warp warps : foundWarps.get(category))
            if(warps.getName().equals(name))
                return warps;

        return null;
    }

    public boolean warpExists(String name, WarpCategory category)
    {
        return config.getConfiguration().getConfigurationSection("warp."+category+".instance."+name) != null;
    }

    public HashMap<WarpCategory, List<Warp>> getFoundWarps() {
        return foundWarps;
    }
}
