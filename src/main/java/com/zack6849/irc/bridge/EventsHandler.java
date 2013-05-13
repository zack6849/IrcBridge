/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zack6849.irc.bridge;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.pircbotx.Channel;

/**
 *
 * @author Zack
 */
public class EventsHandler implements Listener{
    public static Plugin pl;
    public EventsHandler(Plugin pl){
        EventsHandler.pl = pl;
    }
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        String format = String.format("<%s> %s", event.getPlayer().getName(), event.getMessage());
        sendMessage(format);
    }
    @EventHandler
    public void onKick(PlayerKickEvent event){
        String format = String.format("%s was kicked for %s", event.getPlayer().getName(), event.getReason());
        sendMessage(format);
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        String format = String.format("%s left the game", event.getPlayer().getName());
        sendMessage(format);
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        String format = String.format("%s joined the game" , event.getPlayer().getName());
        sendMessage(format);
    }
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event){
       String command = event.getMessage().replaceFirst("/", "");
       String[] args = command.split(" ");
       if(command.startsWith("say")){
           if(event.getPlayer().hasPermission("bukkit.command.say")){
               StringBuilder sb = new StringBuilder();
               for(int i = 1; i < args.length; i++){
                   sb.append(args[i]).append(" ");
               }
               String message = sb.toString().trim();
               sendMessage("[Server] " + message);
           }
       }
       /*if(command.startsWith("ban")){
           
       }*/
    }
    public static void sendMessage(final String s){
        Bukkit.getScheduler().runTaskAsynchronously(App.plugin, new Runnable(){
            public void run(){
                for(Channel c : Bot.bot.getChannels()){
                    Bot.bot.sendMessage(c, s);
                }
            }
        });
    }
}
