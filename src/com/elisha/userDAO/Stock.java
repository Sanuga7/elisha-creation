/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.elisha.userDAO;

import com.elisha.database.Database;
import com.elisha.loggers.Loggers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Sanuga
 */
public class Stock {
    
    private static final String cls = Stock.class.getName();
    
    public static boolean UpdateStock(int stockId, int variationId, int sizeId, String Rprice, String Sprice, int colorId, int status, String qty){
    
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        String date = dtf.format(time);
        
        String query = "UPDATE `stock` SET `received_price` = ?, `selling_price` = ?, `updated_time` = ?, `status_id` = ? WHERE `id` = ?";
        String query1 = "UPDATE `variation` SET `color_id` = ?, `size_id` = ?, `quantity` = ? WHERE `id` = ?";
        
        try(Connection conn = Database.createConnection()){
           if(conn != null){
              try(PreparedStatement stmt = conn.prepareStatement(query);
                  PreparedStatement stmt1 = conn.prepareStatement(query1)){
                 
                  
                  
              }
           }
        }catch(SQLException e){
            Loggers.logServe(e.getMessage(), cls);
        }
       return false;
    }
    
}
