package de.jxson.simplifier.plugin.setting.custom;

import de.jxson.simplifier.plugin.SimplifierPlugin;
import de.jxson.simplifier.plugin.setting.SettingImpl;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ColoredChat extends SettingImpl implements Listener {

    public ColoredChat() {
        super("COLOR_CHAT", "Makes the char more fancier (Supporting color-codes with \"&\")");
    }

    @Override
    public void onRegister() {
        Bukkit.getPluginManager().registerEvents(this, SimplifierPlugin.getPluginInstance());
    }

    @Override
    public void onUnregister()
    {

    }

    @EventHandler
    public void handleChatEvent(AsyncPlayerChatEvent event)
    {
        if(isEnabled())
            event.setMessage(event.getMessage().replace("&", "§"));
    }

}
