/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.elisha.session;

/**
 *
 * @author Sanuga
 */
public class UserSession {
    
    public static String email = null;
    public static String type = null;
    public static String role = null;
    public static String name = null;
    
    public static void createSession(String user, String utype, String fname, String lname){
      
        if(user != null){
            email = user;
            type = utype;
            name = fname+" "+lname;
        }else{
            email = "Guest";
            type = "Guest";
            name = "Guest";       
        }
        
        if(type.equals("1")){
           role = "ADMIN";
        }else if(type.equals("2")){
           role = "MANAGER";
        }else if(type.equals("3")){
           role = "CASHIER";
        }else {
           role = null;
        }
        
    }
    
}
