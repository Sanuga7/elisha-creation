/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.elisha.panel;

import com.elisha.database.Database;
import com.elisha.dialog.UpdateProductDialog;
import com.elisha.dialog.ProductDiaog;
import com.elisha.loggers.Loggers;
import com.elisha.userDAO.User;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import com.elisha.gui.Home;


/**
 *
 * @author Sanuga
 */
public class ManageProducts extends javax.swing.JPanel {
    
        
    private final Home homeScreen;
    private static final String cls = ManageProducts.class.getName();
    /**
     * Creates new form ManageProducts
     */
    public ManageProducts(Home parent) {
        initComponents();
        this.homeScreen = parent;
        init();
    }
    
    private void init(){
    
        jButton1.setIcon(new FlatSVGIcon("com/elisha/icon/new.svg", 20, 20));
        loadProductTable();
    
    }
    
    private void loadProductTable() {
        
            Vector<Vector<Object>> dataVector = new Vector<>();
            Vector<String> columnNames = new Vector<>(Arrays.asList(
                "Product ID", "Title", "Brand", "Category", "Available Colors","Changes"
            ));

            String query = "SELECT \n" +
                            "    p.id AS product_id,\n" +
                            "    p.title,\n" +
                            "    b.brand,\n" +
                            "    p.product_sku AS sku,\n" +
                            "    c.name AS category,\n" +
                            "    GROUP_CONCAT(DISTINCT col.name SEPARATOR ', ') AS colors\n" +
                            "FROM product p\n" +
                            "JOIN brand b ON p.brand_id = b.id\n" +
                            "JOIN category c ON p.category_id = c.id\n" +
                            "LEFT JOIN stock_has_product shp ON p.id = shp.product_id\n" +
                            "LEFT JOIN stock_has_variation shv ON shp.stock_id = shv.stock_id\n" +
                            "LEFT JOIN variation v ON shv.variation_id = v.id\n" +
                            "LEFT JOIN color col ON v.color_id = col.id\n" +
                            "GROUP BY p.id, p.title, b.brand, c.name;";

            try (Connection connection = Database.createConnection()) {
                
                try(Statement stmt = connection.createStatement()){
                
                        ResultSet rs = stmt.executeQuery(query);

                        while (rs.next()) {
                            Vector<Object> row = new Vector<>();
                            row.add(rs.getString("sku"));
                            row.add(rs.getString("title"));
                            row.add(rs.getString("brand"));
                            row.add(rs.getString("category"));
                            String colors = rs.getString("colors");
                            if (colors == null) {
                                colors = "No colors available";
                            }
                            row.add(colors);
                            row.add("Edit");
                            dataVector.add(row);
                        }

                        DefaultTableModel model = new DefaultTableModel(dataVector, columnNames) {
                            @Override
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };

                        jTable1.setModel(model);
                        
                        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                            @Override
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                int row = jTable1.rowAtPoint(evt.getPoint());
                                int col = jTable1.columnAtPoint(evt.getPoint());

                                if (col == jTable1.getColumnCount() - 1) {
                                    String sku = jTable1.getValueAt(row, 0).toString();

                                    UpdateProductDialog dialog = new UpdateProductDialog(homeScreen, true);
                                    dialog.setLocationRelativeTo(null);
                                    dialog.setVisible(true);

                                    loadProductTable();
                                }
                            }
                        });
                        }

            } catch (SQLException e) {
                Loggers.logInfo("Failed to load product data: " + e.getMessage(), cls);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null", "Edit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1071, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ProductDiaog productDialog =  new ProductDiaog(homeScreen, true);
        productDialog.setLocationRelativeTo(null);
        productDialog.setVisible(true);
        
        loadProductTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
