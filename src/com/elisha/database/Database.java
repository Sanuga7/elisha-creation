/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.elisha.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.elisha.loggers.Loggers;
/**
 *
 * @author Sanuga
 */
public class Database {
    
    private static final String DATABASE = "elisha_db";
    private static final String USER = "root";
    private static final String PASSWORD = "2006@Sanuga";
    
    public static Connection createConnection(){

            try{
          
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+DATABASE, USER,PASSWORD);
            
            }catch(ClassNotFoundException | SQLException e){
                String err = e.toString();
                String name = String.valueOf(Database.class.getClass());
                Loggers.logServe(err, name);
                return null;
            }
        
    }
    
}
