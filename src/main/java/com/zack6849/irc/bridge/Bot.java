/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zack6849.irc.bridge;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;

/**
 *
 * @author Zack
 */
public class Bot {
      public static PircBotX bot;
      public Bot(String server, int port, String nickname, String ident, String password, boolean debug, boolean identify, String[] chans){
          try {
              bot = new PircBotX();
              bot.setName(nickname);
              bot.setLogin(ident);
              bot.setVerbose(debug);
              bot.getListenerManager().addListener(new IRCListener());
              bot.connect(server, port);
              if(identify){
                  bot.identify(password);
              }
              for(String s : chans){
                  bot.joinChannel(s);
              }
          } catch (IOException ex) {
              Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
          } catch (IrcException ex) {
              Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      public static void disconenct(){
          bot.shutdown(true);
      }
}
