package de.jxson.simplifier.plugin.menu.sub.warps;

import de.jxson.simplifier.Globals;
import de.jxson.simplifier.api.warp.Warp;
import de.jxson.simplifier.plugin.menu.Menu;
import de.jxson.simplifier.plugin.menu.MenuHandler;
import de.jxson.simplifier.plugin.menu.MenuUtility;
import de.jxson.simplifier.plugin.utils.ItemCreator;
import de.jxson.simplifier.plugin.warp.WarpHandler;
import de.jxson.simplifier.plugin.warp.WarpImpl;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ChangeWarpItemMenu extends Menu {

    private Warp warp;
    private WarpHandler warpHandler;

    private static final ItemCreator EXIT_ITEM = new ItemCreator(Material.BARRIER, 1)
            .setName("§8➥ §c§lGo back")
            .setLore("", "§aLeave §7this menu", "", "§7[Click] §eGo back to the previous menu", "");

    private static final ItemCreator SAVE_ITEM = new ItemCreator(Material.LIME_STAINED_GLASS_PANE, 1)
            .setName("§8➥ §a§lSave Item")
            .setLore("", "§aSave §7the item if its not empty", "", "§7[Click] §eSave item for the warp", "");

    private static final ItemCreator INSERT_RIGHT_ITEM = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE, 1)
            .setName("§a§lInsert Item §8»");
    private static final ItemCreator INSERT_LEFT_ITEM = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE, 1)
            .setName("§8« §a§lInsert Item");
    public ChangeWarpItemMenu(MenuUtility menuUtility, Warp warp, WarpHandler warpHandler) {
        super(menuUtility);
        this.warp = warp;
        this.warpHandler = warpHandler;
    }

    @Override
    public String getMenuName() {
        return "§8➥ Change Item §e" + warp.getName();
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(event.getSlot() != 4 && event.getClickedInventory() != player.getInventory());
        if(event.getCurrentItem().isSimilar(EXIT_ITEM.getItemStack()))
        {
            //fix for loosing items, finally fixed... since 1.14.4 :D
            if(event.getView().getItem(4) != null)
                player.getInventory().addItem(event.getView().getItem(4));
            new EditWarpMenu(MenuHandler.getMenuUtility(player), this.warp, this.warpHandler).open();
        }
        if(event.getCurrentItem().isSimilar(SAVE_ITEM.getItemStack()))
        {
            if(event.getView().getItem(4) == null)
            {
                player.sendMessage(Globals.PLUGIN_PREFIX + "§cAIR cannot be saved!");
                return;
            }
            ItemStack newItem = event.getView().getItem(4);
            ((WarpImpl) warp).setItemStack(newItem);
            this.warpHandler.setItem(newItem, warp);
            player.sendMessage(Globals.PLUGIN_PREFIX + "§7Warp-item was successfully updated!");
            player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            new EditWarpMenu(MenuHandler.getMenuUtility(player), this.warp, this.warpHandler).open();
        }
    }

    @Override
    public void setMenuItems() {
        EXIT_ITEM.setSlot(getInventory(), 0);
        INSERT_RIGHT_ITEM.setSlot(getInventory(), 3);
        INSERT_LEFT_ITEM.setSlot(getInventory(), 5);
        SAVE_ITEM.setSlot(getInventory(), 8);
        fill();
        getInventory().setItem(4, null);
    }
}
