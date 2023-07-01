package de.jxson.simplifier.plugin.setting.custom;

import de.jxson.simplifier.Globals;
import de.jxson.simplifier.plugin.SimplifierPlugin;
import de.jxson.simplifier.plugin.setting.SettingImpl;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Beehive;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BeehiveChecker extends SettingImpl implements Listener {

    public BeehiveChecker() {
        super("BEEHIVE_CHECKER", "Right-click a beehive to check the amount of bees in there");
    }

    @Override
    public void onRegister() {
        Bukkit.getPluginManager().registerEvents(new BeehiveCheckEvent(), SimplifierPlugin.getPluginInstance());
    }

    @Override
    public void onUnregister() {

    }

    class BeehiveCheckEvent implements Listener {
        @EventHandler
        public void onBeehiveCheck(PlayerInteractEvent event) {
            if(event.getClickedBlock() == null) return;
            if(event.getClickedBlock().getType() == Material.BEE_NEST || event.getClickedBlock().getType() == Material.BEEHIVE) {
                Beehive hive = (Beehive) event.getClickedBlock().getState();
                event.getPlayer().sendMessage(Globals.PLUGIN_PREFIX + "§7This §ebeehive §7contains §b" + hive.getEntityCount() + " §7bees");
            }
        }
    }

}

