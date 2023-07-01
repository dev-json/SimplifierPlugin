package de.jxson.simplifier.plugin.menu;

import org.bukkit.entity.Player;

public class MenuUtility {

    private Player owner;

    public MenuUtility(Player player)
    {
        this.owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
