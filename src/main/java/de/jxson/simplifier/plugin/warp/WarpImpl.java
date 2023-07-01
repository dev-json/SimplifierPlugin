package de.jxson.simplifier.plugin.warp;

import de.jxson.simplifier.api.warp.Warp;
import de.jxson.simplifier.api.warp.WarpCategory;
import de.jxson.simplifier.plugin.utils.ItemCreator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WarpImpl implements Warp {

    private String warpName;
    private Location warpLocation;
    private WarpCategory warpCategory;

    private ItemStack itemStack;

    public static final ItemStack DEFAULT_ITEM = new ItemCreator(Material.GRASS_BLOCK, 1).getItemStack();

    public WarpImpl(String name)
    {
        this.warpName = name;
        this.warpLocation = null;
        this.warpCategory = WarpCategory.OTHER;
        this.itemStack = WarpImpl.DEFAULT_ITEM;
    }

    @Override
    public String getName() {
        return warpName;
    }

    public void setWarpName(String warpName) {
        this.warpName = warpName;
    }

    @Override
    public WarpCategory getCategory() {
        return warpCategory;
    }

    public void setWarpCategory(WarpCategory warpCategory) {
        this.warpCategory = warpCategory;
    }

    @Override
    public Location getLocation() {
        return warpLocation;
    }

    @Override
    public ItemStack getItem() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void setWarpLocation(Location warpLocation) {
        this.warpLocation = warpLocation;
    }
}
