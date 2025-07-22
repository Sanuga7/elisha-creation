/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.elisha.validator;

import com.elisha.database.Database;
import com.elisha.loggers.Loggers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sanuga
 */
public class UserValidate {
    
    public static boolean isEmailValid(String email){

           if(email.matches("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
             return true;
           }else{
             return false;
           }
    }  
    
    public static boolean isPwdValid(String pwd){
    
        if(pwd == null){
          return false;
        }
        
        if(pwd.length() <= 20){
            return true;
        }
    
        return false;
    }
    
    public static boolean isEmpty(String text){
    
        if(text.isEmpty()){
          return false;
        }else {
          return true;
        }
    
    }
    
    public static boolean isMobileValid(String mobile){
    
        if(mobile.matches("^(0{1})(7{1})([0|1|2|4|5|6|7|8]{1})([0-9]{7})")){
           return true;
        }
     return false;
    }
    
    public static boolean isPWDValid(String pwd){
    
        if(pwd.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")){
           return true;
        }
     return false;
    }
    
    public static boolean isEmailExist(String email){
    
        try(Connection conn = Database.createConnection()){
        
            if(conn != null){
            
                String query = "SELECT * FROM `users` WHERE `email` = ?";
                try(PreparedStatement stmt = conn.prepareStatement(query)){
                
                    stmt.setString(1, email);
                    
                    ResultSet rs = stmt.executeQuery();
                    
                    if(rs.next()){
                    
                        return true;
                        
                    }else{
                       return false;
                    }
                    
                }
                
            }
            
        }catch(SQLException e){
            Loggers.logServe(e.getMessage(), "UserValidate.java-->isEmailExist");
        }
       return false; 
    }

}
