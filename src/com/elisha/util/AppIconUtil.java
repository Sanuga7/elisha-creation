/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elisha.util;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Sanuga
 */
public class AppIconUtil {
    
    private static Image appIcon;
    
    static {
         
         try{
            URL iconPath = AppIconUtil.class.getResource("/com/elisha/img/logo.png");
              ImageIcon icon = new ImageIcon(iconPath);
              AppIconUtil.appIcon = icon.getImage();
         }catch (NullPointerException e){
             JOptionPane.showMessageDialog(null, "Invalid Icon Path...");
         }
        
    }
    
    public static void applyIcon(JFrame frame){
    
        if(frame != null){
          frame.setIconImage(appIcon);
        }
        
    }
    
}
