/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.elisha.loggers;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Sanuga
 */
public class Loggers {
    
    public static Logger logger = Logger.getLogger("Elisha");
    
    static {
        
        try {
            if (logger.getHandlers().length == 0) {
                FileHandler handler = new FileHandler("elisha.log", true);
                handler.setFormatter(new SimpleFormatter());
                logger.addHandler(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void logInfo(String msg, String loc){ 
        logger.log(Level.INFO, loc+"-"+msg);
    }
    
    public static void logWarning(String msg, String loc){ 
        logger.log(Level.WARNING, loc+"-"+msg);
    }
    
    public static void logServe(String msg, String loc){ 
        logger.log(Level.SEVERE, loc+"-"+msg);
    }
    
    public static void logALL(String msg, String loc){ 
        logger.log(Level.ALL, loc+"-"+msg);
    }
    
}
