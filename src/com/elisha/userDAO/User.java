/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.elisha.userDAO;
import com.elisha.database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.elisha.loggers.Loggers;
import com.elisha.session.UserSession;

/**
 *
 * @author Sanuga
 */
public class User {
    
    private static final String cls = User.class.getName();
    
    public static boolean userLogin(String email, String pwd){
    
        try(Connection connection = Database.createConnection()){
           if(connection == null){
              Loggers.logInfo("Something Went Wrong Failed to createConnnection", cls);
              return false;
           }else {
               
               String query = "SELECT * FROM `users` WHERE `email` = ? AND `password` = ?";
               try(PreparedStatement stmt = connection.prepareStatement(query)){
                   stmt.setString(1, email);
                   stmt.setString(2, pwd);
                   
                   try(ResultSet rs = stmt.executeQuery()){
                      if(rs.next()){
                        String type = rs.getString("user_type_id");
                        String fname = rs.getString("fname");
                        String lname = rs.getString("lname");
                          UserSession.createSession(email, type, fname, lname);
                        return true;
                      }else {
                        return false;
                      }
                   }
               } catch (Exception e) {
                   Loggers.logInfo("Something Went Wrong Failed to createSatement"+ e.getMessage(), cls);
                   return false;
               }
            
           }
        
        }catch(Exception e){
            Loggers.logServe(String.valueOf(e), cls);
            return false;
        }
        
    
    }
    
}
