package de.jxson.simplifier.plugin.menu.sub.teleportation;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import de.jxson.simplifier.plugin.menu.Menu;
import de.jxson.simplifier.plugin.menu.MenuHandler;
import de.jxson.simplifier.plugin.menu.MenuUtility;
import de.jxson.simplifier.plugin.menu.sub.SimplifierMenu;
import de.jxson.simplifier.plugin.utils.ItemCreator;
import it.unimi.dsi.fastutil.Hash;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeleportationMenu extends Menu {

    private static final ItemCreator EXIT_ITEM = new ItemCreator(Material.BARRIER, 1)
            .setName("§8➥ §c§lGo back")
            .setLore("", "§aLeave §7this menu", "", "§7[Click] §eGo back to the previous menu", "");

    private static final ItemCreator PLAYER_ITEM = new ItemCreator(Material.PLAYER_HEAD, 1);


    private final HashMap<String, String> SPECIAL_PLAYERS = new HashMap<>();

    public TeleportationMenu(MenuUtility menuUtility) {
        super(menuUtility);
        SPECIAL_PLAYERS.put("SunsetGFX", "§7§l[§l"+IridiumColorAPI.process("<GRADIENT:c4a1a>Sonnenuntergang</GRADIENT:f7b733>")+"§7§l]");
        SPECIAL_PLAYERS.put("_Jxson", "§7§l[§l"+IridiumColorAPI.process("<GRADIENT:333333>Spinning Wizard</GRADIENT:dd1818>")+"§7§l]");
        SPECIAL_PLAYERS.put("dev_json", "§7§l[§l"+IridiumColorAPI.process("<GRADIENT:36D1DC>Husband Material</GRADIENT:5B86E5>")+"§7§l]");
    }

    @Override
    public String getMenuName() {
        return "§8➥ §e§lTeleportation";
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
        if(event.getCurrentItem().isSimilar(PLAYER_ITEM.getItemStack()))
        {
            SkullMeta skullOwner = (SkullMeta) PLAYER_ITEM.getItemStack().getItemMeta();
            Player target = Bukkit.getPlayer(skullOwner.getOwner());
            if(target != null)
                player.teleport(target);
        }
    }

    @Override
    public void setMenuItems() {
        EXIT_ITEM.setSlot(getInventory(), 45);

        for(Player player : Bukkit.getOnlinePlayers())
        {
            PLAYER_ITEM.setName("§7§l" + player.getName());
            if(SPECIAL_PLAYERS.containsKey(player.getName())) {
                PLAYER_ITEM.setName(SPECIAL_PLAYERS.get(player.getName()) + " §7§l" + player.getName());
            }
            PLAYER_ITEM.setSkullOwner(player.getName()).setSlot(getInventory(), getInventory().firstEmpty());
        }

        for(int i = 0;i < 9; i++) {
            if(inventory.getItem(i+45) == null)
                PLACEHOLDER_ITEM.setSlot(inventory, i+45);
        }
    }
}
