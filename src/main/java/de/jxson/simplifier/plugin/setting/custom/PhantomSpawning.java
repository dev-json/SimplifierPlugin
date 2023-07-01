package de.jxson.simplifier.plugin.setting.custom;

import de.jxson.simplifier.plugin.SimplifierPlugin;
import de.jxson.simplifier.plugin.setting.SettingImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class PhantomSpawning extends SettingImpl {

    public PhantomSpawning() {
        super("PHANTOM_SPAWNING", "Toggle if phantoms can spawn or not");
    }

    @Override
    public void onRegister() {
        Bukkit.getPluginManager().registerEvents(new PhantomSpawningEvent(), SimplifierPlugin.getPluginInstance());
    }

    @Override
    public void onUnregister() {

    }

    class PhantomSpawningEvent implements Listener {
        @EventHandler
        public void onPhantomSpawn(CreatureSpawnEvent event) {
            if(event.getEntity() instanceof Phantom)
                event.setCancelled(!isEnabled());
        }
    }

}
