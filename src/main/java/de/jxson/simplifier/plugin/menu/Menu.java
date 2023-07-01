package de.jxson.simplifier.plugin.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import de.jxson.simplifier.plugin.utils.ItemCreator;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;
    protected MenuUtility menuUtility;
    public final ItemCreator PLACEHOLDER_ITEM = new ItemCreator(Material.BLACK_STAINED_GLASS_PANE, 1).setName(" ");

    public Menu(MenuUtility menuUtility)
    {
        this.menuUtility = menuUtility;
    }

    public abstract String getMenuName();
    public abstract int getSlots();
    public abstract void handleMenu(InventoryClickEvent event);
    public abstract void setMenuItems();

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());
        this.setMenuItems();
        menuUtility.getOwner().openInventory(getInventory());
    }
    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void fill()
    {
        for(int i = 0;i < getSlots(); i++) {
            if(getInventory().getItem(i) == null)
                PLACEHOLDER_ITEM.setSlot(getInventory(), i);
        }
    }

}
