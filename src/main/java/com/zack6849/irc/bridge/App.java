package com.zack6849.irc.bridge;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Hello world!
 *
 */
public class App extends JavaPlugin {
    public static Plugin plugin;
    Logger log;
    @Override
    public void onEnable(){
        this.log = getLogger();
        App.plugin = this;
        Bukkit.getPluginManager().registerEvents(new EventsHandler(plugin), plugin);
        Bot b = new Bot("availo.esper.net", 6667, "zack6849-bridgebot", "bot", "none", true, false, new String[] {"#zack6849"});
    }
    @Override
    public void onDisable(){
        Bot.disconenct();
    }
}
