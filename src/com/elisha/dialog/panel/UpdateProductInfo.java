/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.elisha.dialog.panel;

import com.elisha.database.Database;
import com.elisha.loggers.Loggers;
import com.elisha.session.UserSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Sanuga
 */
public class UpdateProductInfo extends javax.swing.JPanel {

    /**
     * Creates new form UpdateProductInfo
     */
    
    private static String cls = UpdateProductInfo.class.getName();
    private HashMap<String, Integer> statusMap;
    
    public UpdateProductInfo() {
        initComponents();
        loadAddedby();
        loadBrand();
        this.statusMap = new HashMap<>();
        loadCategory();
        loadStatus();
    }
    
    private ResultSet searchData(String query){
      ResultSet rs = null;
        try{
        
            try{
              Class.forName("com.mysql.cj.jdbc.Driver");
               Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elisha_db", "root", "2006@Sanuga");
               Statement smt = c.createStatement();
               rs = smt.executeQuery(query);
            }catch(ClassNotFoundException e){
               e.printStackTrace();
            }
        }catch(SQLException e){
          e.printStackTrace();
        }
        return rs;
    }

    private void loadCategory(){
        try{
            ResultSet rs = searchData("SELECT * FROM `category`");
            Vector<String> categories = new Vector();
            categories.add("Select Category");
            while(rs.next()){
              String categoriesName = rs.getString("name");
              categories.add(categoriesName);
            }
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(categories);
            prCatCombo.setModel(dcm);
        }catch(SQLException e){
          e.printStackTrace();
        }
    }
    
    private void loadAddedby(){

        Vector<String> added = new Vector();
        added.add(UserSession.name);
        
        DefaultComboBoxModel dcm = new DefaultComboBoxModel<>(added);
        prGenderCombo.setModel(dcm);
    
    }
    
    private void loadBrand(){
        try{
            ResultSet rs = searchData("SELECT * FROM `brand`");
            Vector<String> brands = new Vector();
            brands.add("Select Brands");
            while(rs.next()){
              String brandName = rs.getString("brand");
              brands.add(brandName);
            }
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(brands);
            prBrandCombo.setModel(dcm);
        }catch(SQLException e){
          e.printStackTrace();
        }
    }
    
    private void loadStatus(){
        try{
            ResultSet rs = searchData("SELECT * FROM `status`");
            Vector<String> status = new Vector();
            while(rs.next()){
              String statusName = rs.getString("type");
              int statusId = rs.getInt("id");
              status.add(statusName);
              statusMap.put(statusName, statusId);
            }
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(status);
            prStatusCombo.setModel(dcm);
        }catch(SQLException e){
          e.printStackTrace();
        }
    }
    
    public void setProductBySKU(String sku) {

            try (Connection connection = Database.createConnection()) {
                if (connection != null) {
                    String query = "SELECT * FROM `product` WHERE `product_sku` = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, sku);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            prSKUInput.setText(sku);
                            prNameInput.setText(rs.getString("title"));
                            prDescriptionInput.setText(rs.getString("description"));

                            Vector<String> added = new Vector<>();
                            added.add(rs.getString("added_by"));
                            DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<>(added);
                            prGenderCombo.setModel(dcm);

                            prBrandCombo.setSelectedIndex(rs.getInt("brand_id"));
                            prCatCombo.setSelectedIndex(rs.getInt("category_id"));

                            prNameInput.revalidate();
                            prNameInput.repaint();
                            prDescriptionInput.revalidate();
                            prDescriptionInput.repaint();
                            this.revalidate();
                            this.repaint();
                        } else {
                            System.out.println("No product found for SKU: " + sku);
                        }
                    }
                }
            } catch (SQLException e) {
                Loggers.logInfo("Failed to load product data: " + e.getMessage(), cls);
            }
    }

    public JTextField getPrSKUInput() {
        return prSKUInput;
    }

    public HashMap<String, Integer> getStatusMap() {
        return statusMap;
    }
    
    public JComboBox<String> getPrStatusCombo() {
        return prStatusCombo;
    }

    public JTextArea getPrDescriptionInput() {
        return prDescriptionInput;
    }

    public JTextField getPrNameInput() {
        return prNameInput;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        prNameInput = new javax.swing.JTextField();
        prSKUInput = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        prDescriptionInput = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        prBrandCombo = new javax.swing.JComboBox<>();
        prCatCombo = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        prGenderCombo = new javax.swing.JComboBox<>();
        prStatusCombo = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(238, 65));
        jPanel1.setLayout(new java.awt.GridLayout(2, 2, 5, 5));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel2.setText("Product Name");
        jPanel1.add(jLabel2);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setText("Product SKU Name");
        jPanel1.add(jLabel3);

        prNameInput.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        prNameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prNameInputActionPerformed(evt);
            }
        });
        jPanel1.add(prNameInput);

        prSKUInput.setEditable(false);
        prSKUInput.setBackground(new java.awt.Color(255, 255, 255));
        prSKUInput.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        prSKUInput.setEnabled(false);
        jPanel1.add(prSKUInput);

        prDescriptionInput.setColumns(20);
        prDescriptionInput.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        prDescriptionInput.setRows(5);
        jScrollPane2.setViewportView(prDescriptionInput);

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel4.setText("Product Description");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(2, 2, 5, 5));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel1.setText("Product Category");
        jPanel2.add(jLabel1);

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel5.setText("Product Brand");
        jPanel2.add(jLabel5);

        prBrandCombo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        prBrandCombo.setEnabled(false);
        jPanel2.add(prBrandCombo);

        prCatCombo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        prCatCombo.setEnabled(false);
        prCatCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prCatComboActionPerformed(evt);
            }
        });
        jPanel2.add(prCatCombo);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridLayout(2, 2, 5, 5));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel6.setText("Added By");
        jPanel3.add(jLabel6);

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel7.setText("Product Status");
        jPanel3.add(jLabel7);

        prGenderCombo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        prGenderCombo.setEnabled(false);
        jPanel3.add(prGenderCombo);

        prStatusCombo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jPanel3.add(prStatusCombo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void prNameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prNameInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prNameInputActionPerformed

    private void prCatComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prCatComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prCatComboActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> prBrandCombo;
    private javax.swing.JComboBox<String> prCatCombo;
    private javax.swing.JTextArea prDescriptionInput;
    private javax.swing.JComboBox<String> prGenderCombo;
    private javax.swing.JTextField prNameInput;
    private javax.swing.JTextField prSKUInput;
    private javax.swing.JComboBox<String> prStatusCombo;
    // End of variables declaration//GEN-END:variables
}
