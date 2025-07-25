/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.elisha.gui;

import com.elisha.loggers.Loggers;
import com.elisha.panel.Dashboard;
import com.elisha.panel.ManageCashier;
import com.elisha.panel.ManageManagers;
import com.elisha.panel.ManageProducts;
import com.elisha.panel.ManageStocks;
import com.elisha.session.UserSession;
import com.elisha.util.AppIconUtil;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.CardLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Sanuga
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public static String email = null;
    public static String type = null;
    public static String role = null;
    public static String name = null;
    private CardLayout contentPanelLayout;
    private Dashboard dashboardPanel;
    private ManageProducts productPanel;
    private ManageStocks customerPanel;
    private ManageCashier cashierPanel;
    private ManageManagers managerPanel;
    
    public Home() {
        initComponents();
        init();
        menuAccess();
        loadPanel();
    }
    
    private void init(){
        
        AppIconUtil.applyIcon(this);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(40);
        
        email = UserSession.email;
        type = UserSession.type;
        role = UserSession.role;
        name = UserSession.name;
        
        jLabel4.setIcon(new FlatSVGIcon("com/elisha/icon/menu.svg", 20, 20));
        DashboardBtn.setIcon(new FlatSVGIcon("com/elisha/icon/dashboard.svg", 20, 20));
        ManageProductsBtn.setIcon(new FlatSVGIcon("com/elisha/icon/product.svg", 20, 20));
        ManageCashierBtn.setIcon(new FlatSVGIcon("com/elisha/icon/user.svg", 20, 20));
        ManageCustomerBtn.setIcon(new FlatSVGIcon("com/elisha/icon/cashier.svg", 20, 20));
        ManageManagersBtn.setIcon(new FlatSVGIcon("com/elisha/icon/manager.svg", 20, 20));
        jButton1.setIcon(new FlatSVGIcon("com/elisha/icon/Logout.svg", 20, 20));
        
        jPanel4.putClientProperty(FlatClientProperties.STYLE, "arc:25");
        jPanel2.putClientProperty(FlatClientProperties.STYLE, "arc:25");
        jPanel3.putClientProperty(FlatClientProperties.STYLE, "arc:25");
        jLabel10.putClientProperty(FlatClientProperties.STYLE, "arc:25");
        jScrollPane2.putClientProperty(FlatClientProperties.STYLE, "arc:25");
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        jLabel10.setText("Today, "+time);
        
        jLabel1.setText("Welcome Back! "+name);
        jLabel11.setIcon(new FlatSVGIcon("com/elisha/icon/profile.svg", 20, 20));

    }
    
    private void menuAccess(){
    
        if(role.equalsIgnoreCase("Cashier")){        
            ManageManagersBtn.setVisible(false);
            ManageCashierBtn.setVisible(false);
        }else if(role.equalsIgnoreCase("Manager")){
            ManageManagersBtn.setVisible(false);
        }else {
            this.dispose();
        }
    
    
    }
    
    private void loadPanel(){
    
        if(contentPanelLayout ==  null && jPanel4.getLayout() instanceof CardLayout){
           this.contentPanelLayout = (CardLayout)jPanel4.getLayout();
        }
        
        this.dashboardPanel = new Dashboard();
        this.dashboardPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.jPanel4.add(dashboardPanel,"dashboard_panel");
        this.productPanel = new ManageProducts(this);
        this.productPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.jPanel4.add(productPanel,"product_panel");
        this.cashierPanel = new ManageCashier();
        this.cashierPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.jPanel4.add(cashierPanel,"cashier_panel");
        this.managerPanel = new ManageManagers();
        this.managerPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.jPanel4.add(managerPanel,"manager_panel");
        this.customerPanel = new ManageStocks();
        this.customerPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        this.jPanel4.add(customerPanel,"customer_panel");
        
        SwingUtilities.updateComponentTreeUI(jPanel4);
        jPanel4.revalidate();
        jPanel4.repaint();
    
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
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        DashboardBtn = new javax.swing.JButton();
        ManageProductsBtn = new javax.swing.JButton();
        ManageCustomerBtn = new javax.swing.JButton();
        ManageCashierBtn = new javax.swing.JButton();
        ManageManagersBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        jLabel4.setText("Elisha");

        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 102, 102));
        jButton1.setText("Logout");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);

        jLabel10.setBackground(new java.awt.Color(204, 204, 204));
        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Today,");
        jLabel10.setOpaque(true);

        DashboardBtn.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        DashboardBtn.setText("Dashboard");
        DashboardBtn.setBorderPainted(false);
        DashboardBtn.setFocusPainted(false);
        DashboardBtn.setFocusable(false);
        DashboardBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        DashboardBtn.setRequestFocusEnabled(false);
        DashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DashboardBtnActionPerformed(evt);
            }
        });

        ManageProductsBtn.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        ManageProductsBtn.setText("Manage Products");
        ManageProductsBtn.setBorderPainted(false);
        ManageProductsBtn.setFocusPainted(false);
        ManageProductsBtn.setFocusable(false);
        ManageProductsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        ManageProductsBtn.setRequestFocusEnabled(false);
        ManageProductsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManageProductsBtnActionPerformed(evt);
            }
        });

        ManageCustomerBtn.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        ManageCustomerBtn.setText("Manage Stocks");
        ManageCustomerBtn.setBorderPainted(false);
        ManageCustomerBtn.setFocusPainted(false);
        ManageCustomerBtn.setFocusable(false);
        ManageCustomerBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        ManageCustomerBtn.setRequestFocusEnabled(false);
        ManageCustomerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManageCustomerBtnActionPerformed(evt);
            }
        });

        ManageCashierBtn.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        ManageCashierBtn.setText("Manage Cashier");
        ManageCashierBtn.setBorderPainted(false);
        ManageCashierBtn.setFocusPainted(false);
        ManageCashierBtn.setFocusable(false);
        ManageCashierBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        ManageCashierBtn.setRequestFocusEnabled(false);
        ManageCashierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManageCashierBtnActionPerformed(evt);
            }
        });

        ManageManagersBtn.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        ManageManagersBtn.setText("Manage Manager");
        ManageManagersBtn.setBorderPainted(false);
        ManageManagersBtn.setFocusPainted(false);
        ManageManagersBtn.setFocusable(false);
        ManageManagersBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        ManageManagersBtn.setRequestFocusEnabled(false);
        ManageManagersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManageManagersBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DashboardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addComponent(ManageProductsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ManageCustomerBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ManageCashierBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ManageManagersBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(DashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ManageProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ManageCustomerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ManageCashierBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ManageManagersBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerifyInputWhenFocusTarget(false);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.CardLayout());
        jScrollPane2.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ManageCustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageCustomerBtnActionPerformed
        // TODO add your handling code here:
        this.contentPanelLayout.show(jPanel4, "customer_panel");
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }//GEN-LAST:event_ManageCustomerBtnActionPerformed

    private void ManageCashierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageCashierBtnActionPerformed
        // TODO add your handling code here:
        this.contentPanelLayout.show(jPanel4, "cashier_panel");
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }//GEN-LAST:event_ManageCashierBtnActionPerformed

    private void ManageManagersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageManagersBtnActionPerformed
        // TODO add your handling code here:
        this.contentPanelLayout.show(jPanel4, "manager_panel");
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    }//GEN-LAST:event_ManageManagersBtnActionPerformed

    private void DashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DashboardBtnActionPerformed
        // TODO add your handling code here:
        this.contentPanelLayout.show(jPanel4, "dashboard_panel");
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        
    }//GEN-LAST:event_DashboardBtnActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_formWindowOpened

    private void ManageProductsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageProductsBtnActionPerformed
        // TODO add your handling code here:
        this.contentPanelLayout.show(jPanel4, "product_panel");
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    }//GEN-LAST:event_ManageProductsBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try{
           FlatIntelliJLaf.setup();
        }catch(Exception e){
            Loggers.logInfo("Failed to Load Theme: "+e.getMessage() ,  String.valueOf(Home.class.getClass()));
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DashboardBtn;
    private javax.swing.JButton ManageCashierBtn;
    private javax.swing.JButton ManageCustomerBtn;
    private javax.swing.JButton ManageManagersBtn;
    private javax.swing.JButton ManageProductsBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
