package de.jxson.simplifier.plugin.menu.sub.warps;

import de.jxson.simplifier.Globals;
import de.jxson.simplifier.api.warp.Warp;
import de.jxson.simplifier.plugin.menu.Menu;
import de.jxson.simplifier.plugin.menu.MenuHandler;
import de.jxson.simplifier.plugin.menu.MenuUtility;
import de.jxson.simplifier.plugin.menu.sub.SimplifierMenu;
import de.jxson.simplifier.plugin.utils.ItemCreator;
import de.jxson.simplifier.plugin.warp.WarpHandler;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EditWarpMenu extends Menu {

    private final WarpHandler warpHandler;
    private final Warp warp;

    private static final ItemCreator EXIT_ITEM = new ItemCreator(Material.BARRIER, 1)
            .setName("§8➥ §c§lGo back")
            .setLore("", "§aLeave §7this menu", "", "§7[Click] §eGo back to the previous menu", "");
    private static final ItemCreator DELETE_ITEM = new ItemCreator(Material.SKELETON_SKULL, 1)
            .setName("§8➥ §a§lDelete")
            .setLore("", "§aDelete §7this warp", "", "§7[Click] §eDelete this warp location", ""); //No confirmation here yet. (might be a setting?)
    private static final ItemCreator INFORMATION_ITEM = new ItemCreator(Material.BOOK, 1);
    private static final ItemCreator CHANGE_ITEM = new ItemCreator(Material.GRASS_BLOCK, 1);

    public EditWarpMenu(MenuUtility menuUtility, Warp warp, WarpHandler warpHandler) {
        super(menuUtility);
        this.warpHandler = warpHandler;
        this.warp = warp;
    }

    @Override
    public String getMenuName() {
        return "§8➥ " + warp.getName();
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        if(event.getCurrentItem().isSimilar(EXIT_ITEM.getItemStack()))
            new WarpsPerCategoryMenu(MenuHandler.getMenuUtility(player), this.warp.getCategory(), this.warpHandler, WarpsPerCategoryMenu.PLAYER_PAGE_OFFSET.get(player)).open();
        if(event.getCurrentItem().isSimilar(DELETE_ITEM.getItemStack()))
        {
            warpHandler.deleteWarp(warp);
            new WarpsPerCategoryMenu(MenuHandler.getMenuUtility(player), WarpsPerCategoryMenu.LAST_INVENTORY_CATEGORY.get(player), this.warpHandler, WarpsPerCategoryMenu.PLAYER_PAGE_OFFSET.get(player)).open();
            player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            player.sendMessage(Globals.PLUGIN_PREFIX + "§7The warp "+warp.getName()+" has been successfully deleted.");
        }
        if(event.getCurrentItem().isSimilar(CHANGE_ITEM.getItemStack()))
            new ChangeWarpItemMenu(MenuHandler.getMenuUtility(player), this.warp, this.warpHandler).open();
    }

    @Override
    public void setMenuItems() {
        EXIT_ITEM.setSlot(getInventory(), 0);
        DELETE_ITEM.setSlot(getInventory(), 3);
        INFORMATION_ITEM
                .setName("§8➥ §a§l"+warp.getName())
                .setLore("",
                        "§7Location:",
                        "  §7X: §e" + warp.getLocation().getX(),
                        "  §7Y: §e" + warp.getLocation().getY(),
                        "  §7Z: §e" + warp.getLocation().getZ(),
                        "  §7World: §e" + warp.getLocation().getWorld().getName(),
                        "  §7Yaw: §e" + warp.getLocation().getYaw(),
                        "  §7Pitch: §e" + warp.getLocation().getPitch(),
                        "",
                        "§7Category:",
                        "  §e" + warp.getCategory().name(),
                        "")
                .setSlot(getInventory(), 4);
        CHANGE_ITEM
                .setItem(this.warp.getItem())
                .setName("§8➥ §a§lChange Item")
                .setLore("", "§7Change the item of this warp", "", "§7[Click] §eOpen \"change item\" menu")
                .setSlot(getInventory(), 5);
        fill();
    }
}
