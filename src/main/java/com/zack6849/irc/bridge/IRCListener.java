/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zack6849.irc.bridge;

import org.bukkit.Bukkit;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.KickEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.ModeEvent;
import org.pircbotx.hooks.events.PartEvent;
import org.pircbotx.hooks.events.QuitEvent;

/**
 *
 * @author Zack
 */
public class IRCListener extends ListenerAdapter {

    @Override
    public void onMessage(MessageEvent event) {
        String format = String.format("[%s] %s: %s", event.getChannel().getName(), event.getUser().getNick(), event.getMessage());
        Bukkit.broadcastMessage(format);
    }

    @Override
    public void onAction(ActionEvent event) {
        String format = String.format("[%s] * %s %s", event.getChannel().getName(), event.getUser().getNick(), event.getMessage());
        Bukkit.broadcastMessage(format);
    }

    @Override
    public void onPart(PartEvent event) {
        if (!event.getReason().isEmpty()) {
            String format = String.format("[%s] ~ %s has left the channel (%s)", event.getChannel().getName(), event.getUser().getNick(), event.getReason());
            Bukkit.broadcastMessage(format);
        } else {
            String format = String.format("[%s] ~ %s has left the channel", event.getChannel().getName(), event.getUser().getNick());
            Bukkit.broadcastMessage(format);
        }
    }

    @Override
    public void onJoin(JoinEvent event) {
        String format = String.format("[%s] ~ %s joins channel %s", event.getChannel().getName(), event.getUser().getNick(), event.getChannel().getName());
        Bukkit.broadcastMessage(format);
    }

    @Override
    public void onKick(KickEvent event) {
        if (!event.getReason().isEmpty()) {
            String format = String.format("[%s] ~ %s has kicked %s (%s)", event.getChannel().getName(), event.getSource().getNick(), event.getRecipient().getNick(), event.getReason());
            Bukkit.broadcastMessage(format);
        } else {
            String format = String.format("[%s] ~ %s has kicked %s from the channel", event.getChannel().getName(), event.getSource().getNick(), event.getRecipient().getNick());
            Bukkit.broadcastMessage(format);
        }
    }
    @Override
    public void onQuit(QuitEvent event) {
        String format = String.format("~ %s has quit %s", event.getUser().getNick(), event.getReason());
        Bukkit.broadcastMessage(format);
    }

    @Override
    public void onMode(ModeEvent event) {
        String format = String.format("[%s] ~ %s sets mode %s", event.getChannel().getName(), event.getUser().getNick(), event.getMode());
        Bukkit.broadcastMessage(format);
    }
}
