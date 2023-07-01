package de.jxson.simplifier.plugin.menu.sub.settings;

import de.jxson.simplifier.api.setting.Setting;
import de.jxson.simplifier.plugin.menu.Menu;
import de.jxson.simplifier.plugin.menu.MenuHandler;
import de.jxson.simplifier.plugin.menu.MenuUtility;
import de.jxson.simplifier.plugin.menu.sub.SimplifierMenu;
import de.jxson.simplifier.plugin.setting.SettingHandler;
import de.jxson.simplifier.plugin.setting.SettingImpl;
import de.jxson.simplifier.plugin.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class SettingsMenu extends Menu {

    private SettingHandler settingHandler;

    private static final ItemCreator EXIT_ITEM = new ItemCreator(Material.BARRIER, 1)
            .setName("§8➥ §c§lClose")
            .setLore("", "§aClosing §7this menu", "", "§7[Click] §eClose the menu", "");

    public SettingsMenu(MenuUtility menuUtility, SettingHandler settingHandler) {
        super(menuUtility);
        this.settingHandler = settingHandler;
    }

    @Override
    public String getMenuName() {
        return "§8➥ §e§lSettings";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);
        if(event.getCurrentItem().isSimilar(EXIT_ITEM.getItemStack()))
            new SimplifierMenu(MenuHandler.getMenuUtility(player)).open();
        if(event.getCurrentItem().getType() == Material.RED_STAINED_GLASS || event.getCurrentItem().getType() == Material.GREEN_STAINED_GLASS)
        {
            for(Setting setting : this.settingHandler.getRegisteredSettings())
            {
                if(event.getCurrentItem().getItemMeta().getDisplayName().contains(setting.getName()))
                {
                    if(setting.isEnabled())
                    {
                        this.settingHandler.toggleSetting(setting, false);
                        player.playSound(player, Sound.BLOCK_IRON_DOOR_CLOSE, 1, 1);
                    }
                    else
                    {
                        this.settingHandler.toggleSetting(setting, true);
                        player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                    }
                    new SettingsMenu(MenuHandler.getMenuUtility(player), settingHandler).open();
                }
            }
        }
    }

    @Override
    public void setMenuItems() {

        for(Setting setting : settingHandler.getRegisteredSettings())
        {
            ItemCreator settingItemCreator = new ItemCreator(Material.RED_STAINED_GLASS, 1);
            if(setting.isEnabled())
            {
                settingItemCreator.setItem(Material.GREEN_STAINED_GLASS);
                settingItemCreator.setName("§e"+setting.getName() + " §8[§2Enabled§8]");
                settingItemCreator.setLore("", "§7"+setting.getDescription(), "", "§7[Click] §eDeactivate setting", "");
            }
            else
            {
                settingItemCreator.setItem(Material.RED_STAINED_GLASS);
                settingItemCreator.setName("§e"+setting.getName() + " §8[§4Disabled§8]");
                settingItemCreator.setLore("", "§7"+setting.getDescription(), "", "§7[Click] §eActivate setting", "");
            }
            getInventory().addItem(settingItemCreator.getItemStack());
        }

        EXIT_ITEM.setSlot(getInventory(), 45);
        fill();
    }
}
