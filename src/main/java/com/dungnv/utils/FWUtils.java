/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.utils;

import com.vaadin.ui.Window;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ODIN NGUYEN
 */
public class FWUtils {
    public static void reloadWindow(Window subWindow) {

        subWindow.addWindowModeChangeListener((Window.WindowModeChangeEvent event) -> {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException ex) {
                Logger.getLogger(FWUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public static boolean urlExists(String URLName){
    try {
      HttpURLConnection.setFollowRedirects(false);
      // note : you may also need
      //        HttpURLConnection.setInstanceFollowRedirects(false)
      HttpURLConnection con =
         (HttpURLConnection) new URL(URLName).openConnection();
      con.setRequestMethod("HEAD");
//      return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
      return true;
    }
    catch (Exception e) {
       e.printStackTrace();
       return false;
    }
  }
}
