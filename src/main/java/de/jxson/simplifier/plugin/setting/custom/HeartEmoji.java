package de.jxson.simplifier.plugin.setting.custom;

import de.jxson.simplifier.plugin.SimplifierPlugin;
import de.jxson.simplifier.plugin.setting.SettingImpl;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class HeartEmoji extends SettingImpl {

    public HeartEmoji() {
        super("HEART_EMOJI", "Toggle if the chat should replace (<3)");
    }

    @Override
    public void onRegister() {
        Bukkit.getPluginManager().registerEvents(new HeartEmojiChatEvent(), SimplifierPlugin.getPluginInstance());
    }

    @Override
    public void onUnregister() {

    }

    class HeartEmojiChatEvent implements Listener {
        @EventHandler
        public void onSendChat(AsyncPlayerChatEvent event) {
            if(!event.getMessage().contains("<3")) return;
            if(isEnabled()) event.setMessage(event.getMessage().replace("<3", "❤"));
        }
    }

}
