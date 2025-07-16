/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.elisha.userDAO;

import com.elisha.database.Database;
import com.elisha.loggers.Loggers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Sanuga
 */
public class Product {
 
    private static final String cls = Product.class.getName();
    
    public static boolean InsertProduct(HashMap<String, String> pr_Data){
        
        String title = pr_Data.get("title");
        String description = pr_Data.get("description");
        String brandId = pr_Data.get("brand");
        String categoryId = pr_Data.get("category");
        String sku = pr_Data.get("sku");
        String addedBy = pr_Data.get("added");
        String imagePath = pr_Data.get("path");
    
        try(Connection connection = Database.createConnection()){
            
            if(connection != null){
                
                String query = "SELECT * FROM `product` WHERE `title` = ?";
            
                try(PreparedStatement stmt = connection.prepareStatement(query)){
                
                    stmt.setString(1, pr_Data.get("title"));
                
                    ResultSet rs = stmt.executeQuery();
                    
                    if(rs.next()){
                       JOptionPane.showMessageDialog(null, "Product Already Exist", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{

                        String query1 = "INSERT INTO `product_img` (`path`) VALUES (?)";
                        
                        try(PreparedStatement stmt1 = connection.prepareStatement(query1)){
                             stmt1.setString(1, pr_Data.get("path"));
                             
                             int num = stmt1.executeUpdate();
                             if(num > 0){
                             
                                 String query2 = "SELECT `id` FROM `product_img` WHERE `path` = ?";
                                 
                                 try(PreparedStatement stmt2 = connection.prepareStatement(query2)){
                                      stmt2.setString(1, pr_Data.get("path"));
                                      
                                      ResultSet rs1 = stmt2.executeQuery();
                                      
                                      if(rs1.next()){
                                         String img_id = rs1.getString("id");
                                         
                                         String query3 = "INSERT INTO `product`(`title`,`description`,`brand_id`,`category_id`,`product_sku`,`product_img_id`,"
                                                 + "`added_by`) VALUES (?, ?, ?, ?, ?, ?, ?)";
                                         
                                         try(PreparedStatement stmt3 = connection.prepareStatement(query3)){
                                            stmt3.setString(1, title);
                                            stmt3.setString(2, description);
                                            stmt3.setString(3, brandId);
                                            stmt3.setString(4, categoryId);
                                            stmt3.setString(5, sku);
                                            stmt3.setString(6, img_id);
                                            stmt3.setString(7, addedBy);
                                            
                                            return stmt3.executeUpdate() > 0;
                                         }catch(Exception e){
                                            Loggers.logInfo("Something Went Wrong Failed to createSatement"+ e.getMessage(), cls); 
                                         }
                                      }
                                 }catch(Exception e){
                                    Loggers.logInfo("Something Went Wrong Failed to createSatement"+ e.getMessage(), cls);
                                 }
                             
                             }
                        }catch(Exception e){
                           Loggers.logInfo("Something Went Wrong Failed to createSatement"+ e.getMessage(), cls);
                        }
                        
                    }
                }
                
            }
            
        } catch (SQLException e) {
            Loggers.logServe(String.valueOf(e), cls);
            return false;
        }
        return false;

    }
    
}
