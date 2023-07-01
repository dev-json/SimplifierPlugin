package de.jxson.simplifier.plugin.setting.custom;

import de.jxson.simplifier.plugin.SimplifierPlugin;
import de.jxson.simplifier.plugin.setting.SettingImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Bat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class BatSpawning extends SettingImpl {

    public BatSpawning() {
        super("BAT_SPAWNING", "Toggle if bats can spawn or not");
    }

    @Override
    public void onRegister() {
        Bukkit.getPluginManager().registerEvents(new BatSpawnEvent(), SimplifierPlugin.getPluginInstance());
    }

    @Override
    public void onUnregister() {

    }
    class BatSpawnEvent implements Listener {

        @EventHandler
        public void onBadSpawn(CreatureSpawnEvent event) {
            if(event.getEntity() instanceof Bat)
                event.setCancelled(!isEnabled());
        }
    }

}
