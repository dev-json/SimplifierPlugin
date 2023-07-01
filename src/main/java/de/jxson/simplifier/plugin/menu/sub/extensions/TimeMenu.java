package de.jxson.simplifier.plugin.menu.sub.extensions;

import de.jxson.simplifier.plugin.menu.Menu;
import de.jxson.simplifier.plugin.menu.MenuHandler;
import de.jxson.simplifier.plugin.menu.MenuUtility;
import de.jxson.simplifier.plugin.menu.sub.SimplifierMenu;
import de.jxson.simplifier.plugin.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TimeMenu extends Menu {

    private static final ItemCreator EXIT_ITEM = new ItemCreator(Material.BARRIER, 1)
            .setName("§8➥ §c§lClose")
            .setLore("", "§aClosing §7this menu", "", "§7[Click] §eClose the menu", "");
    private static final ItemCreator SUNRISE_ITEM = new ItemCreator(Material.CLOCK, 1)
            .setName("§8➥ §e§lSunrise")
            .setLore("", "§7Set the time to sunrise", "", "§7[Click] §eSet time", "");
    private static final ItemCreator DAY_ITEM = new ItemCreator(Material.CLOCK, 1)
            .setName("§8➥ §e§lSunrise")
            .setLore("", "§7Set the time to day", "", "§7[Click] §eSet time", "");
    private static final ItemCreator SUNSET_ITEM = new ItemCreator(Material.CLOCK, 1)
            .setName("§8➥ §e§lSunrise")
            .setLore("", "§7Set the time to sunset", "", "§7[Click] §eSet time", "");
    private static final ItemCreator MIDNIGHT_ITEM = new ItemCreator(Material.CLOCK, 1)
            .setName("§8➥ §e§lSunrise")
            .setLore("", "§7Set the time to midnight", "", "§7[Click] §eSet time", "");

    public TimeMenu(MenuUtility menuUtility) {
        super(menuUtility);
    }

    @Override
    public String getMenuName() {
        return "§8➥ §e§lTime";
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
            new ExtensionsMenu(MenuHandler.getMenuUtility(player)).open();
        if(event.getCurrentItem().isSimilar(SUNRISE_ITEM.getItemStack()))
            player.getWorld().setTime(0);
        if(event.getCurrentItem().isSimilar(DAY_ITEM.getItemStack()))
            player.getWorld().setTime(6000);
        if(event.getCurrentItem().isSimilar(SUNSET_ITEM.getItemStack()))
            player.getWorld().setTime(12000);
        if(event.getCurrentItem().isSimilar(MIDNIGHT_ITEM.getItemStack()))
            player.getWorld().setTime(18000);
    }

    @Override
    public void setMenuItems() {
        EXIT_ITEM.setSlot(getInventory(), 18);
        SUNRISE_ITEM.setSlot(getInventory(), 10);
        DAY_ITEM.setSlot(getInventory(), 12);
        SUNSET_ITEM.setSlot(getInventory(), 14);
        MIDNIGHT_ITEM.setSlot(getInventory(), 16);
        fill();
    }
}
