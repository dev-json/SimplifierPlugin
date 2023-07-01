package de.jxson.simplifier.plugin.menu.sub.extensions;

import de.jxson.simplifier.Globals;
import de.jxson.simplifier.plugin.menu.Menu;
import de.jxson.simplifier.plugin.menu.MenuHandler;
import de.jxson.simplifier.plugin.menu.MenuUtility;
import de.jxson.simplifier.plugin.menu.sub.SimplifierMenu;
import de.jxson.simplifier.plugin.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.sql.Time;

public class ExtensionsMenu extends Menu
{
    private static final ItemCreator EXIT_ITEM = new ItemCreator(Material.BARRIER, 1)
            .setName("§8➥ §c§lClose")
            .setLore("", "§aClosing §7this menu", "", "§7[Click] §eClose the menu", "");
    private static final ItemCreator FLY_ITEM = new ItemCreator(Material.FEATHER, 1)
            .setName("§8➥ §a§lFly")
            .setLore("", "§aEnable or disable flying", "", "§7[Click] §eToggle fly", "");
    private static final ItemCreator TIME_ITEM = new ItemCreator(Material.CLOCK, 1)
            .setName("§8➥ §a§lTime")
            .setLore("", "§aBe the master of the time", "", "§7[Click] §eOpen menu", "");

    public ExtensionsMenu(MenuUtility menuUtility) {
        super(menuUtility);
    }

    @Override
    public String getMenuName() {
        return "§8➥ §e§lExtensions";
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
        {
            new SimplifierMenu(MenuHandler.getMenuUtility(player)).open();
            return;
        }
        if(event.getCurrentItem().isSimilar(FLY_ITEM.getItemStack()))
        {
            if(!player.getAllowFlight())
            {
                player.setFlying(true);
                player.setAllowFlight(true);
                player.sendMessage(Globals.PLUGIN_PREFIX + "§aYou can fly now.");
            }
            else
            {
                player.setFlying(false);
                player.setAllowFlight(false);
                player.sendMessage(Globals.PLUGIN_PREFIX + "§aYou can no longer fly.");
            }
            return;
        }
        if(event.getCurrentItem().isSimilar(TIME_ITEM.getItemStack()))
        {
            new TimeMenu(MenuHandler.getMenuUtility(player)).open();
        }
    }

    @Override
    public void setMenuItems() {
        EXIT_ITEM.setSlot(getInventory(), 18);
        FLY_ITEM.setSlot(getInventory(), 12);
        TIME_ITEM.setSlot(getInventory(), 14);
        fill();
    }
}
