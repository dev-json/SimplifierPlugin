package de.jxson.simplifier.plugin.commands;

import de.jxson.simplifier.Globals;
import de.jxson.simplifier.api.SimplyAPI;
import de.jxson.simplifier.api.warp.Warp;
import de.jxson.simplifier.api.warp.WarpCategory;
import de.jxson.simplifier.plugin.menu.MenuHandler;
import de.jxson.simplifier.plugin.menu.sub.warps.SelectWarpCategoryMenu;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarpCommand implements CommandExecutor, TabCompleter {

    /* /warp add <name> <category> */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Globals.PLUGIN_PREFIX + "§cYou need to be a player!");
            return false;
        }

        if(!player.hasPermission("simplifier.command.warp.use"))
        {
            player.sendMessage(Globals.PLUGIN_PREFIX + "§cYou are lacking the following permissions §8simplifier.command.warp.use");
            player.playSound(player, Sound.BLOCK_CHEST_LOCKED, 1, 1);
            return false;
        }

        if(args.length == 1)
        {
            new SelectWarpCategoryMenu(MenuHandler.getMenuUtility(player)).open();
            return true;
        }

        if (args.length != 3 || !args[0].equalsIgnoreCase("add")) {
            player.sendMessage(Globals.PLUGIN_PREFIX + "§c/warp add <name> <category>");
            return false;
        }

        WarpCategory selectedCategory = WarpCategory.OTHER;
        for (WarpCategory category : WarpCategory.values()) {
            if (category.name().equalsIgnoreCase(args[2])) {
                selectedCategory = category;
            }
        }

        Warp warp = SimplyAPI.getWarp(args[1], selectedCategory);

        if (warp != null) {
            player.sendMessage(Globals.PLUGIN_PREFIX + String.format("§cPlease choose another warp name! This warp already exists in the §9%s §ccategory!", selectedCategory));
            return false;
        }

        SimplyAPI.createWarp(args[1], selectedCategory, player.getLocation());
        player.sendMessage(Globals.PLUGIN_PREFIX + "§aWarp was successfully created!");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 1) {
            ArrayList<String> list = new ArrayList<>();
            list.add("add");
            return list;
        } else if (args.length == 3) {
            return Arrays.stream(WarpCategory.values()).map(c -> c.name().toLowerCase()).toList();
        }

        return null;
    }
}
