package de.jxson.simplifier.plugin.commands;

import de.jxson.simplifier.Globals;
import de.jxson.simplifier.plugin.menu.MenuHandler;
import de.jxson.simplifier.plugin.menu.MenuUtility;
import de.jxson.simplifier.plugin.menu.sub.SimplifierMenu;
import de.jxson.simplifier.plugin.menu.sub.extensions.TimeMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SimplifierCommand implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player))
        {
            commandSender.sendMessage(Globals.PLUGIN_PREFIX + "§cYou need to be a player!");
            return false;
        }

        Player player = (Player) commandSender;

        if(strings.length < 1)
            new SimplifierMenu(MenuHandler.getMenuUtility(player)).open();

        if(strings.length > 0) {
            switch (strings[0])
            {
                case "fly" ->
                {
                    if (!player.getAllowFlight()) {
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        player.sendMessage(Globals.PLUGIN_PREFIX + "§aYou can fly now.");
                    } else {
                        player.setAllowFlight(false);
                        player.setFlying(false);
                        player.sendMessage(Globals.PLUGIN_PREFIX + "§aYou can no longer fly.");
                    }
                }
                case "time" -> new TimeMenu(MenuHandler.getMenuUtility(player)).open();
                default -> player.sendMessage(Globals.PLUGIN_PREFIX + "§c/sfy [<subcommands>]");
            }
            return false;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            ArrayList<String> list = new ArrayList<>();
            list.add("fly");
            list.add("time");
            return list;
        }
        return null;
    }

}