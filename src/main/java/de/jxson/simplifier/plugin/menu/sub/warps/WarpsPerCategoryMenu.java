package de.jxson.simplifier.plugin.menu.sub.warps;

import de.jxson.simplifier.Globals;
import de.jxson.simplifier.api.warp.Warp;
import de.jxson.simplifier.api.warp.WarpCategory;
import de.jxson.simplifier.plugin.menu.Menu;
import de.jxson.simplifier.plugin.menu.MenuHandler;
import de.jxson.simplifier.plugin.menu.MenuUtility;
import de.jxson.simplifier.plugin.menu.sub.SimplifierMenu;
import de.jxson.simplifier.plugin.utils.ItemCreator;
import de.jxson.simplifier.plugin.warp.WarpHandler;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WarpsPerCategoryMenu extends Menu {

    private WarpHandler warpHandler;
    private WarpCategory warpCategory;

    private int offsets;
    public static final HashMap<Player, Integer> PLAYER_PAGE_OFFSET = new HashMap<>();

    public static final HashMap<Player, WarpCategory> LAST_INVENTORY_CATEGORY = new HashMap<>();
    public static final HashMap<Player, Warp> LAST_USED_WARP = new HashMap<>();

    private static final ItemCreator EXIT_ITEM = new ItemCreator(Material.BARRIER, 1)
            .setName("§8➥ §c§lGo back")
            .setLore("", "§aLeave §7this menu", "", "§7[Click] §eGo back to the previous menu", "");
    private static final ItemCreator PREVIOUS_PAGE_ITEM = new ItemCreator(Material.PAPER, 1)
            .setName("§8➥ §a§lPrevious Page")
            .setLore("", "§7Go back one page", "", "§7[Click] §eOpen previous page", "");
    private static final ItemCreator NEXT_PAGE_ITEM = new ItemCreator(Material.PAPER, 1)
            .setName("§8➥ §a§lNext Page")
            .setLore("", "§7Go forwards one page", "", "§7[Click] §eOpen next page", "");
    private static final ItemCreator BOOK_PAGE_ITEM = new ItemCreator(Material.BOOK, 1)
            .setName("§8➥ §a§lPage ?/?");

    public WarpsPerCategoryMenu(MenuUtility menuUtility, WarpCategory category, WarpHandler warpHandler, int offset) {
        super(menuUtility);
        this.warpCategory = category;
        this.warpHandler = warpHandler;
        WarpsPerCategoryMenu.PLAYER_PAGE_OFFSET.put(menuUtility.getOwner(), offset);
        this.offsets = offset;
    }


    @Override
    public String getMenuName() {
        return "§8➥ §9"+warpCategory;
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
        {
            new SelectWarpCategoryMenu(MenuHandler.getMenuUtility(player)).open();
            return;
        }
        List<Warp> warpList = this.warpHandler.getFoundWarps().get(warpCategory);
        if(this.warpHandler.getFoundWarps().get(warpCategory) != null && !warpList.isEmpty())
            for(Warp warp : this.warpHandler.getFoundWarps().get(warpCategory))
            {
                if(event.getCurrentItem().getItemMeta().getDisplayName().contains(warp.getName()))
                {
                    if(event.getClick() == ClickType.RIGHT)
                    {
                        if (!player.hasPermission("simplifier.edit.warp")) {
                            player.playSound(player, Sound.BLOCK_CHEST_LOCKED, 1, 1);
                            player.sendMessage(Globals.PLUGIN_PREFIX + "§cYou are lacking the following permissions §8simplifier.edit.warp");
                            return;
                        }
                        new EditWarpMenu(MenuHandler.getMenuUtility(player), warp, this.warpHandler).open();
                        LAST_USED_WARP.put(player, warp);
                        LAST_INVENTORY_CATEGORY.put(player, warpCategory);
                    }
                    else
                    {
                        player.closeInventory(InventoryCloseEvent.Reason.TELEPORT);
                        player.teleport(warp.getLocation());
                        player.sendMessage(Globals.PLUGIN_PREFIX+"§7Teleported.");
                    }
                }
            }
        if(event.getCurrentItem().isSimilar(NEXT_PAGE_ITEM.getItemStack()))
        {
            new WarpsPerCategoryMenu(MenuHandler.getMenuUtility(player), this.warpCategory, this.warpHandler, WarpsPerCategoryMenu.PLAYER_PAGE_OFFSET.get(player)+45).open();
            return;
        }
        if(event.getCurrentItem().isSimilar(PREVIOUS_PAGE_ITEM.getItemStack()))
        {
            if(offsets > 0)
                new WarpsPerCategoryMenu(MenuHandler.getMenuUtility(player), this.warpCategory, this.warpHandler, WarpsPerCategoryMenu.PLAYER_PAGE_OFFSET.get(player)-45).open();
        }

    }

    @Override
    public void setMenuItems() {
        int a = 0, slot = 0, warps = 0, totalWarps = 0, page = 1;
        if(offsets > 0) {
            page = (offsets + 45) / 45;
        }
        List<Warp> warpList = this.warpHandler.getFoundWarps().get(warpCategory);
        if(this.warpHandler.getFoundWarps().get(warpCategory) != null && !warpList.isEmpty())
            for(Warp warp : warpList)
            {
                totalWarps++;
                if(a < offsets)
                    a++;
                else if(warps < 45)
                {
                    ArrayList<String> itemLore = new ArrayList<>();
                    itemLore.add("");
                    itemLore.add("§7[Left-Click] §eTeleport");
                    itemLore.add("§7[Right-Click] §eEdit warp ");
                    itemLore.add("");
                    new ItemCreator(warp.getItem().getType(), 1).setName("§8➥ §a§l" + warp.getName()).setLore(itemLore).setSlot(getInventory(), slot);
                    slot++;
                    warps++;
                }
            }

        EXIT_ITEM.setSlot(getInventory(), 45);
        if(offsets > 0)
            PREVIOUS_PAGE_ITEM.setSlot(getInventory(), 48);
        BOOK_PAGE_ITEM.setName("§8➥ §a§lPage " + page + "/" + ((totalWarps / 45) <= 0 ? 1 : (totalWarps / 45))).setSlot(getInventory(), 49);
        NEXT_PAGE_ITEM.setSlot(getInventory(), 50);

        for(int i = 0;i < 9; i++) {
            if(inventory.getItem(i+45) == null)
                PLACEHOLDER_ITEM.setSlot(inventory, i+45);
        }

    }

    @Override
    public void open() {
        super.open();
        LAST_INVENTORY_CATEGORY.remove(menuUtility.getOwner());
        LAST_USED_WARP.remove(menuUtility.getOwner());
    }
}
