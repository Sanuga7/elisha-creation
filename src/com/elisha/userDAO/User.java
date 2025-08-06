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
import java.sql.SQLException;

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
               
               String query = "SELECT * FROM `users` WHERE `email` = ? AND `password` = ? AND `status_id` = ?";
               try(PreparedStatement stmt = connection.prepareStatement(query)){
                   stmt.setString(1, email);
                   stmt.setString(2, pwd);
                   stmt.setInt(3, 1);
                   
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
    
    public static boolean userUpdate(String fname, String lname, String mobile, int status, String email){
    
        System.out.println(fname+ " "+ lname+ " "+ mobile+" "+email);
        try(Connection conn = Database.createConnection()){
        
            if(conn != null){
            
                String query = "UPDATE `users` SET `fname` = ? , `lname` = ?, `mobile` = ?, `status_id` = ? WHERE `email` = ?";
                try(PreparedStatement stmt = conn.prepareStatement(query)){
                
                    stmt.setString(1, fname);
                    stmt.setString(2, lname);
                    stmt.setString(3, mobile);
                    stmt.setInt(4, status);
                    stmt.setString(5, email);
                    
                    int row = stmt.executeUpdate();
                    
                    if(row == 1){
                    
                        return true;
                        
                    }else{
                       return false;
                    }
                    
                }
                
            }
            
        }catch(SQLException e){
            Loggers.logServe(e.getMessage(), cls);
        }
        
    return false;
    }
    
    public static boolean userAdd(String fname, String lname, String mobile, int status, String email, String pwd, int type){
    
        try(Connection conn = Database.createConnection()){
        
            if(conn != null){
            
                String query = "INSERT INTO `users` (`email`,`fname`,`lname`,`password`,`mobile`,`user_type_id`,`status_id`) VALUES (?,?,?,?,?,?,?)";
                try(PreparedStatement stmt = conn.prepareStatement(query)){
                
                    stmt.setString(1, email);
                    stmt.setString(2, fname);
                    stmt.setString(3, lname);
                    stmt.setString(4, pwd);
                    stmt.setString(5, mobile);
                    stmt.setInt(6, type);
                    stmt.setInt(7, status);
                    
                    int row = stmt.executeUpdate();
                    
                    if(row == 1){
                    
                        return true;
                        
                    }else{
                       return false;
                    }
                    
                }
                
            }
            
        }catch(SQLException e){
            Loggers.logServe(e.getMessage(), cls);
        }
        
    return false;
    }
    
    public static boolean supplierUpdate(String fname,  String mobile, int status, String email){
    
        try(Connection conn = Database.createConnection()){
        
            if(conn != null){
            
                String query = "UPDATE `supplier` SET `name` = ? , `mobile_number` = ?, `status_id` = ? WHERE `email` = ?";
                try(PreparedStatement stmt = conn.prepareStatement(query)){
                
                    stmt.setString(1, fname);
                    stmt.setString(2, mobile);
                    stmt.setInt(3, status);
                    stmt.setString(4, email);
                    
                    int row = stmt.executeUpdate();
                    
                    if(row == 1){
                    
                        return true;
                        
                    }else{
                       return false;
                    }
                    
                }
                
            }
            
        }catch(SQLException e){
            Loggers.logServe(e.getMessage(), cls);
        }
        
    return false;
    }
    
    public static boolean supplierAdd(String name, String mobile, int status, String email){
    
        try(Connection conn = Database.createConnection()){
        
            if(conn != null){
            
                String query = "INSERT INTO `supplier` (`name`,`mobile_number`,`email`,`status_id`) VALUES (?,?,?,?)";
                try(PreparedStatement stmt = conn.prepareStatement(query)){
                
                    stmt.setString(1, name);
                    stmt.setString(2, mobile);
                    stmt.setString(3, email);
                    stmt.setInt(4, status);
                    
                    int row = stmt.executeUpdate();
                    
                    if(row == 1){
                    
                        return true;
                        
                    }else{
                       return false;
                    }
                    
                }
                
            }
            
        }catch(SQLException e){
            Loggers.logServe(e.getMessage(), cls);
        }
        
    return false;
    }
}
