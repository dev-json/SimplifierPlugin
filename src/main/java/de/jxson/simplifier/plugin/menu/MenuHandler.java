package de.jxson.simplifier.plugin.menu;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class MenuHandler {

    private static final HashMap<Player, MenuUtility> PLAYER_MENU_UTILITY_HASH_MAP = new HashMap<>();

    public static MenuUtility getMenuUtility(Player player) {
        MenuUtility utility;

        if(!PLAYER_MENU_UTILITY_HASH_MAP.containsKey(player))
        {
            utility = new MenuUtility(player);
            PLAYER_MENU_UTILITY_HASH_MAP.put(player, utility);
            return utility;
        }
        else
        {
            return PLAYER_MENU_UTILITY_HASH_MAP.get(player);
        }
    }

}
