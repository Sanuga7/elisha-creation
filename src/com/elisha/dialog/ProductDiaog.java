/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elisha.dialog;

import java.awt.CardLayout;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import com.elisha.dialog.panel.BasicProductInfoPanel;
import com.elisha.dialog.panel.ProductImagePanel;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Sanuga
 */
public class ProductDiaog extends javax.swing.JDialog {

    private BasicProductInfoPanel productInfoPanel;
    private ProductImagePanel productImgPanel;
    private CardLayout contentPanelLayout;
    
    public ProductDiaog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadPanels();
        init();
    }
    
    private void init(){
      backBtn.setVisible(false);
      jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
    }

    private void loadPanels(){
      if(contentPanelLayout == null && productContentPanel.getLayout() instanceof CardLayout){
         contentPanelLayout = (CardLayout) productContentPanel.getLayout();
      }
      
      productInfoPanel = new BasicProductInfoPanel();
      productImgPanel = new ProductImagePanel();
      
      productContentPanel.add(productInfoPanel, "product_info");
      productContentPanel.add(productImgPanel, "product_img");
      SwingUtilities.updateComponentTreeUI(productContentPanel);
      
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        controlBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        productContentPanel = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Product Information");
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(600, 580));
        setResizable(false);
        setSize(new java.awt.Dimension(576, 479));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(60, 63, 65));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel1.setText("Product Information");

        controlBtn.setBackground(new java.awt.Color(0, 0, 255));
        controlBtn.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        controlBtn.setForeground(new java.awt.Color(255, 255, 255));
        controlBtn.setText("Next");
        controlBtn.setFocusPainted(false);
        controlBtn.setPreferredSize(new java.awt.Dimension(76, 33));
        controlBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controlBtnActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        productContentPanel.setBackground(new java.awt.Color(255, 255, 255));
        productContentPanel.setLayout(new java.awt.CardLayout());
        jScrollPane2.setViewportView(productContentPanel);

        backBtn.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        backBtn.setText("Back");
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(controlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(controlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void controlBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_controlBtnActionPerformed
        if(controlBtn.getText().equalsIgnoreCase("next")){
          contentPanelLayout.next(productContentPanel);
          backBtn.setVisible(true);
          controlBtn.setText("Submit");
        }else{
          insertProductData();
        }
    }//GEN-LAST:event_controlBtnActionPerformed

    private synchronized void insertProductData(){
    
        String productName = productInfoPanel.getPrNameInput().getText();
        String prSKU = productInfoPanel.getPrSKUInput().getText();
        String description  = productInfoPanel.getPrDescriptionInput().getText();
        int categorySelectIndex = productInfoPanel.getPrCatCombo().getSelectedIndex();
        int brandSelectIndex = productInfoPanel.getPrBrandCombo().getSelectedIndex();
        String gender = String.valueOf(productInfoPanel.getPrGenderCombo().getSelectedItem());
        String status = String.valueOf(productInfoPanel.getPrStatusCombo().getSelectedItem());
        
        String imagePath = productImgPanel.getPrImagePathInput().getText();
        
        HashMap<String, Integer> categoriesMap = productInfoPanel.getCategoryMap();
        HashMap<String, Integer> brandsMap = productInfoPanel.getBrandMap();
        HashMap<String, Integer> genderMap = productInfoPanel.getGenderMap();
        HashMap<String, Integer> statusMap = productInfoPanel.getStatusMap();
        
        String category = productInfoPanel.getPrCatCombo().getItemAt(categorySelectIndex);
        String brand = productInfoPanel.getPrBrandCombo().getItemAt(brandSelectIndex);
        
        int categoryId = categoriesMap.get(category);
        int brandId = brandsMap.get(brand);
        int genderId = genderMap.get(gender);
        int statusId = statusMap.get(status);
        
        try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection c  = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smarttrade", "root", "2006@Sanuga");
          Statement smt = c.createStatement();
          ResultSet rs  = smt.executeQuery("SELECT `product_id` FROM `product` WHERE `product`.`name` = '"+productName+"' "
                  + "AND `product`.`category_id` = '"+categoryId+"' "
                          + "AND `product`.`brand_id` = '"+brandId+"' "
                                  + "AND `product`.`gender_id` = '"+genderId+"'");
          if(rs.next()){
              JOptionPane.showMessageDialog(null, 
                      "This product Already exists", "Info", JOptionPane.ERROR_MESSAGE);
          }else {
              smt.executeUpdate("INSERT INTO `product` (`name`,`description`,`product_sku`,`category_id`,`brand_id`,`gender_id`,`status_id`)"
                      + "VALUES ('"+productName+"', '"+description+"', '"+prSKU+"', '"+categoryId+"', '"+brandId+"', '"+genderId+"', '"+statusId+"')");
              
              ResultSet rs1 = smt.executeQuery("SELECT LAST_INSERT_ID()");
              
              if(rs1.next()){
                int lastInserID = rs1.getInt(1);
                smt.executeUpdate("INSERT INTO `product_img` (`path`,`product_id`) VALUES('"+imagePath+"','"+lastInserID+"')");
              }
              
              JOptionPane.showMessageDialog(null, 
                      "New product Added Successfully", "Done", JOptionPane.INFORMATION_MESSAGE);
              
              this.dispose();
          }
        }catch(SQLException | ClassNotFoundException e){
          e.printStackTrace();
        }
        
    }
    
    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        controlBtn.setText("Next");
        contentPanelLayout.previous(productContentPanel);
        backBtn.setVisible(false);
    }//GEN-LAST:event_backBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductDiaog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductDiaog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductDiaog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductDiaog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProductDiaog dialog = new ProductDiaog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton controlBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel productContentPanel;
    // End of variables declaration//GEN-END:variables
}
