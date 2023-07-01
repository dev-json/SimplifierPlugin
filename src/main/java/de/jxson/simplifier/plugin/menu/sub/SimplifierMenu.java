package de.jxson.simplifier.plugin.menu.sub;

import de.jxson.simplifier.Globals;
import de.jxson.simplifier.plugin.SimplifierPlugin;
import de.jxson.simplifier.plugin.menu.Menu;
import de.jxson.simplifier.plugin.menu.MenuHandler;
import de.jxson.simplifier.plugin.menu.MenuUtility;
import de.jxson.simplifier.plugin.menu.sub.extensions.ExtensionsMenu;
import de.jxson.simplifier.plugin.menu.sub.settings.SettingsMenu;
import de.jxson.simplifier.plugin.menu.sub.teleportation.TeleportationMenu;
import de.jxson.simplifier.plugin.menu.sub.warps.SelectWarpCategoryMenu;
import de.jxson.simplifier.plugin.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class SimplifierMenu extends Menu {

    private static final ItemCreator WARP_ITEM = new ItemCreator(Material.COMPASS, 1)
            .setName("§8➥ §a§lWarps")
            .setLore("", "§7Warp category selection", "", "§7[Click] §eOpen menu", "")
            .addEnchantment(Enchantment.ARROW_DAMAGE, 1)
            .hideFlags();
    private static final ItemCreator TELEPORTATION_ITEM = new ItemCreator(Material.ENDER_PEARL, 1)
            .setName("§8➥ §a§lTeleportation")
            .setLore("", "§7Teleport to a §eplayer", "", "§7[Click] §eOpen menu", "")
            .addEnchantment(Enchantment.ARROW_DAMAGE, 1)
            .hideFlags();

    private static final ItemCreator EXTENSIONS_ITEM = new ItemCreator(Material.BOOK, 1)
            .setName("§8➥ §a§lExtensions")
            .setLore("", "§7Some §elittle helper §7tools", "", "§7[Click] §eOpen menu", "")
            .addEnchantment(Enchantment.ARROW_DAMAGE, 1)
            .hideFlags();
    private static final ItemCreator EXIT_ITEM = new ItemCreator(Material.BARRIER, 1)
            .setName("§8➥ §c§lClose")
            .setLore("", "§aClosing §7this menu", "", "§7[Click] §eClose the menu", "");
    private static final ItemCreator ADMIN_SETTINGS_ITEM = new ItemCreator(Material.COMMAND_BLOCK, 1)
            .setName("§8➥ §a§lSettings")
            .setLore("", "§aAdmin only §7settings", "", "§7[Click] §eOpen menu", "")
            .addEnchantment(Enchantment.ARROW_DAMAGE, 1)
            .hideFlags();

    public SimplifierMenu(MenuUtility menuUtility) {
        super(menuUtility);
    }
    @Override
    public String getMenuName() {
        return "§8➥ §c§lSimplifier Menu";
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
            player.closeInventory(InventoryCloseEvent.Reason.PLUGIN);

        if(event.getCurrentItem().isSimilar(WARP_ITEM.getItemStack()))
            new SelectWarpCategoryMenu(MenuHandler.getMenuUtility(player)).open();

        if(event.getCurrentItem().isSimilar(ADMIN_SETTINGS_ITEM.getItemStack()))
        {
            if (!player.hasPermission("simplifier.open.admin.settings")) {
                player.playSound(player, Sound.BLOCK_CHEST_LOCKED, 1, 1);
                player.sendMessage(Globals.PLUGIN_PREFIX + "§cYou are lacking the following permissions §8simplifier.open.admin.settings");
            }
            else
            {
                new SettingsMenu(MenuHandler.getMenuUtility(player), SimplifierPlugin.getPluginInstance().getIntegration().getSettingHandler()).open();
            }
        }

        if(event.getCurrentItem().isSimilar(TELEPORTATION_ITEM.getItemStack()))
            new TeleportationMenu(MenuHandler.getMenuUtility(player)).open();
        if(event.getCurrentItem().isSimilar(EXTENSIONS_ITEM.getItemStack()))
            new ExtensionsMenu(MenuHandler.getMenuUtility(player)).open();
    }

    @Override
    public void setMenuItems() {
        WARP_ITEM.setSlot(inventory, 12);
        TELEPORTATION_ITEM.setSlot(inventory, 13);
        EXTENSIONS_ITEM.setSlot(inventory, 14);
        EXIT_ITEM.setSlot(inventory, 18);

        ADMIN_SETTINGS_ITEM.setSlot(inventory, 26);

        fill();
    }
}
