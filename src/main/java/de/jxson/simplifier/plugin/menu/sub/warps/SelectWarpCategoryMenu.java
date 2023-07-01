package de.jxson.simplifier.plugin.menu.sub.warps;

import de.jxson.simplifier.Globals;
import de.jxson.simplifier.api.warp.WarpCategory;
import de.jxson.simplifier.plugin.SimplifierPlugin;
import de.jxson.simplifier.plugin.menu.Menu;
import de.jxson.simplifier.plugin.menu.MenuHandler;
import de.jxson.simplifier.plugin.menu.MenuUtility;
import de.jxson.simplifier.plugin.menu.sub.SimplifierMenu;
import de.jxson.simplifier.plugin.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class SelectWarpCategoryMenu extends Menu {

    private static final ItemCreator EXIT_ITEM = new ItemCreator(Material.BARRIER, 1)
            .setName("§8➥ §c§lGo back")
            .setLore("", "§aLeave §7this menu", "", "§7[Click] §eGo back to the previous menu", "");
    private static final ItemCreator FARMING_WARPS = new ItemCreator(Material.GRASS_BLOCK, 1).setName("§e§lFarmen");
    private static final ItemCreator CITIES_WARPS = new ItemCreator(Material.STONE_BRICK_WALL, 1).setName("§e§lCities");
    private static final ItemCreator BIOMES_WARPS = new ItemCreator(Material.JUNGLE_WOOD, 1).setName("§e§lBiomes");
    private static final ItemCreator OTHER_WARPS = new ItemCreator(Material.CAKE, 1).setName("§e§lOthers");
    private static final ItemCreator PERSONAL_WARPS = new ItemCreator(Material.BOOK, 1).setName("§e§lPersonal Warps");

    public SelectWarpCategoryMenu(MenuUtility menuUtility) {
        super(menuUtility);
    }

    @Override
    public String getMenuName() {
        return "§e§lWarp Categories";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        if(event.getCurrentItem().isSimilar(EXIT_ITEM.getItemStack()))
            new SimplifierMenu(MenuHandler.getMenuUtility(player)).open();
        if(event.getCurrentItem().isSimilar(CITIES_WARPS.getItemStack()))
            new WarpsPerCategoryMenu(
                    MenuHandler.getMenuUtility(player),
                    WarpCategory.CITY,
                    SimplifierPlugin.getPluginInstance().getIntegration().getWarpHandler(),
                    0).open();
        if(event.getCurrentItem().isSimilar(FARMING_WARPS.getItemStack()))
            new WarpsPerCategoryMenu(
                    MenuHandler.getMenuUtility(player),
                    WarpCategory.FARM,
                    SimplifierPlugin.getPluginInstance().getIntegration().getWarpHandler(),
                    0).open();
        if(event.getCurrentItem().isSimilar(BIOMES_WARPS.getItemStack()))
            new WarpsPerCategoryMenu(
                    MenuHandler.getMenuUtility(player),
                    WarpCategory.BIOMES,
                    SimplifierPlugin.getPluginInstance().getIntegration().getWarpHandler(),
                    0).open();
        if(event.getCurrentItem().isSimilar(OTHER_WARPS.getItemStack()))
            new WarpsPerCategoryMenu(
                    MenuHandler.getMenuUtility(player),
                    WarpCategory.OTHER,
                    SimplifierPlugin.getPluginInstance().getIntegration().getWarpHandler(),
                    0).open();
        if(event.getCurrentItem().isSimilar(PERSONAL_WARPS.getItemStack()))
        {
            player.sendMessage(Globals.PLUGIN_PREFIX + "§cNot implemented yet.");
        }
    }

    @Override
    public void setMenuItems() {
        FARMING_WARPS.setSlot(getInventory(), 10);
        CITIES_WARPS.setSlot(getInventory(), 12);
        BIOMES_WARPS.setSlot(getInventory(), 14);
        OTHER_WARPS.setSlot(getInventory(), 16);
        EXIT_ITEM.setSlot(getInventory(), 18);
        PERSONAL_WARPS.setSlot(getInventory(), 22);
        fill();
    }
}
