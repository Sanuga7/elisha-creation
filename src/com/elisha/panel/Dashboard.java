/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.elisha.panel;

import com.elisha.database.Database;
import com.elisha.loggers.Loggers;
import com.elisha.userDAO.User;
import com.formdev.flatlaf.FlatClientProperties;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sanuga
 */
public class Dashboard extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    
    private static final String cls = User.class.getName();
    
    public Dashboard() {
        initComponents();
        init();
        loadTabel();
    }
    
    private void init(){
    
        jPanel4.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel3.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jPanel1.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        
        charteCreate();
        dailyEarn();
        totalEarn();
    
    }
    
    private void dailyEarn(){
    
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        
        String query = "SELECT * FROM `invoice` WHERE `time` LIKE ?";
        
        try(Connection connection = Database.createConnection()){
        
            if(connection != null){
            
                try(PreparedStatement stmt = connection.prepareStatement(query)){
                
                    stmt.setString(1, time+"%");
                    
                    ResultSet rs = stmt.executeQuery();
                    
                    int price = 0;
                    
                    while(rs.next()){
                    
                        price += Integer.valueOf(rs.getString("price"));
                        
                    }
                    
                    jLabel2.setText("Rs."+price+".00");
                
                }
            
            }
        
        }catch(SQLException e){
           Loggers.logInfo("Something Went Wrong Failed to createSatement"+ e.getMessage(), cls);
        }
    
    }
    
    private void totalEarn(){
        
        String query = "SELECT * FROM `invoice`";
        
        try(Connection connection = Database.createConnection()){
        
            if(connection != null){
            
                try(PreparedStatement stmt = connection.prepareStatement(query)){
                    
                    ResultSet rs = stmt.executeQuery();
                    
                    int price = 0;
                    int x = 0;
                    while(rs.next()){
                    
                       price += Integer.valueOf(rs.getString("price"));
                       x++;
                    }
                    
                    jLabel4.setText("Rs."+price+".00");
                    jLabel6.setText(x+" Sales");
                
                }
            
            }
        
        }catch(SQLException e){
           Loggers.logInfo("Something Went Wrong Failed to createSatement"+ e.getMessage(), cls);
        }
    
    }
    
    private void charteCreate(){
        
        YearMonth currentMonth = YearMonth.now();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (int i = 0; i < 5; i++) {
            
            String query = "SELECT * FROM `invoice` WHERE `time` LIKE ? ";
            
            try(Connection connection = Database.createConnection()){
            
                if(connection != null){

                   try(PreparedStatement stmt = connection.prepareStatement(query)){
                       
                       stmt.setString(1, currentMonth.toString() + "-%");
                       
                       ResultSet rs = stmt.executeQuery();
                       
                       int price = 0;
                     
                       while (rs.next()) {                           
                           price += Integer.valueOf(rs.getString("price"));
                       }
                       
                       YearMonth targetMonth = currentMonth;
                       int month = targetMonth.getMonthValue();
                       String monthName = "";
                       
                       switch(month){
                       
                           case 1:
                              monthName = "january";
                              break;
                           
                           case 2:
                              monthName = "February";
                              break;
                              
                           case 3:
                              monthName = "March";
                              break;
                              
                           case 4:
                              monthName = "April";
                              break;
                              
                           case 5:
                              monthName = "May";
                              break;
                              
                           case 6:
                              monthName = "June";
                              break;
                              
                           case 7:
                              monthName = "July";
                              break;
                              
                           case 8:
                              monthName = "August";
                              break;
                              
                           case 9:
                              monthName = "September";
                              break;
                              
                           case 10:
                              monthName = "October";
                              break;   
                              
                           case 11:
                              monthName = "November";
                              break;
                              
                           case 12:
                              monthName = "December";
                              break; 
                              
                           default:
                               monthName = "Unkown";
                               break;
                       
                       }
                       
                       dataset.addValue(price, "Earnings", monthName);
                   
                   }

                }
            
            }catch(SQLException e){
               Loggers.logInfo("Something Went Wrong Failed to createSatement"+ e.getMessage(), cls);
            }
            
            currentMonth = currentMonth.minusMonths(1);
            
        }
        // --- Create the chart ---
        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Earnings",     
                "Month",                 
                "Earnings (Rs)",     
                dataset                
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(348, 352));  

        jPanel5.removeAll(); 
        jPanel5.add(chartPanel, java.awt.BorderLayout.CENTER);
        jPanel5.revalidate();
        jPanel5.repaint();

    }
    
    private void loadTabel(){
    
        String query =  "SELECT * \n" +
                        "FROM `product`\n" +
                        "INNER JOIN `brand` ON product.brand_id = brand.id\n" +
                        "INNER JOIN `category` ON product.category_id = category.id";
        
        try(Connection connection = Database.createConnection()){
           if(connection == null){
              Loggers.logInfo("Something Went Wrong Failed to createConnnection", cls);
           }else{
           
               try(PreparedStatement stmt = connection.prepareStatement(query)){
                   ResultSet rs = stmt.executeQuery();
                       
                       Vector<Vector> vd = new Vector<>();
                       int x = 1;
                                                  
                       while(rs.next()){
                      
                           Vector<String> row = new Vector<>();
                           row.add(String.valueOf(x));
                           row.add(rs.getString("title"));
                           row.add(rs.getString("brand"));
                           row.add(rs.getString("name"));
                           
                           vd.add(row);
                           x++;
                       }
                       
                       Vector<String> vcn = new Vector<>();
                       vcn.add("Product_id");
                       vcn.add("Product_Title");
                       vcn.add("Product_Brand");
                       vcn.add("Product_Category");
                       
                       DefaultTableModel dtm = new DefaultTableModel(vd, vcn);
                       
                       jTable1.setModel(dtm);
                   }
               }
        }catch(SQLException e){
           Loggers.logInfo("Something Went Wrong Failed to createSatement"+ e.getMessage(), cls);
        }

    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(246, 55, 207));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total Daily Earnings");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Rs.10000.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(23, 23, 23))
        );

        jPanel3.setBackground(new java.awt.Color(102, 255, 204));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total Earnings");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Rs.2000.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 255, 153));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total Daily Sales");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("40 Sales");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel5.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Title", "Brand", "Category"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(166, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
