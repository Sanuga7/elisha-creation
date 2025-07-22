/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.elisha.panel;

import com.elisha.database.Database;
import com.elisha.loggers.Loggers;
import com.elisha.optionpane.Message;
import com.elisha.userDAO.User;
import com.elisha.validator.UserValidate;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sanuga
 */
public class ManageManagers extends javax.swing.JPanel {

    /**
     * Creates new form ManageManagers
     */
    private static final String cls = ManageManagers.class.getName();
    public ManageManagers() {
        initComponents();
        loadTable();
        loadStatus();
        init();
    }
    
    private void init(){
    
        jButton1.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        jButton2.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        
    }
    
    private void loadTable(){
        
        Vector<Vector> data = new Vector<>();
        Vector<String> Name = new Vector<>();
        Name.add("First Name");
        Name.add("Last Name");
        Name.add("Email");
        Name.add("Mobile Number");
        Name.add("Edit");
        try(Connection connection = Database.createConnection()){
        
            if(connection != null){
             
                String query = "SELECT * FROM `users` WHERE `user_type_id` = 2 ";
                
                try(Statement stmt = connection.createStatement()){
                
                    ResultSet rs = stmt.executeQuery(query);
                    
                    while(rs.next()){
                    
                        Vector<String> row = new Vector();
                        row.add(rs.getString("fname"));
                        row.add(rs.getString("lname"));
                        row.add(rs.getString("email"));
                        row.add(rs.getString("mobile"));
                        row.add("Edit");
                    
                        data.add(row);
                    }   
                    
                    DefaultTableModel dtm = new DefaultTableModel(data, Name){
                      @Override
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                    };
                    jTable1.setModel(dtm);
                    
                    jTable1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int row = jTable1.rowAtPoint(e.getPoint());
                            int column = jTable1.columnAtPoint(e.getPoint());

                            if (column == 4 && row >= 0) {
                                
                                String email = jTable1.getValueAt(row, 2).toString();
                                setData(email);
                                
                            }
                        }
                    });
                
                }
                
            }
        
        }catch(SQLException e){
            Loggers.logServe(e.getMessage(), cls);
        }
    
    }

    private void setData(String email){
    
        try(Connection conn = Database.createConnection()){
        
            if(conn != null){
            
                String query1 = "SELECT * FROM `users` WHERE `email` = ? ";
                
                try(PreparedStatement stmt1 = conn.prepareStatement(query1)){
                
                    stmt1.setString(1, email);
                    ResultSet rs = stmt1.executeQuery();
                    
                    if(rs.next()){
                    
                        fnameInput.setText(rs.getString("fname"));
                        lnameInput.setText(rs.getString("lname"));
                        emailInput.setText(rs.getString("email"));
                        emailInput.setEditable(false);
                        pwd.setText("Can Not Change");
                        pwd.setEditable(false);
                        mobile.setText(rs.getString("mobile"));
                        
                        int status = rs.getInt("status_id");
                        if(status == 1){
                          status = 0;
                        }else{
                          status = 1;
                        }
                        StatusCombo.setSelectedIndex(status);
                    }
                
                }
            
            }
        
        }catch(SQLException e){
          Loggers.logServe(e.getMessage(), cls);
        }
    
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
    
    private void loadStatus(){
        try{
            ResultSet rs = searchData("SELECT * FROM `status`");
            Vector<String> status = new Vector();
            while(rs.next()){
              String statusName = rs.getString("type");
              int statusId = rs.getInt("id");
              status.add(statusName);
            }
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(status);
            StatusCombo.setModel(dcm);
        }catch(SQLException e){
          e.printStackTrace();
        }
    }

    private synchronized void addManager() {
        
        String email = emailInput.getText().trim();
        String fname = fnameInput.getText().trim();
        String lname = lnameInput.getText().trim();
        String pass = pwd.getText().trim();
        String number = mobile.getText().trim();
        int statusIndex = StatusCombo.getSelectedIndex();
        int type = 2;

        if (email.isEmpty() || fname.isEmpty() || lname.isEmpty() || pass.isEmpty() || number.isEmpty()) {
            Message.showError(this, "All fields are required!");
            return;
        }
        
        if(number.length() > 10){
           Message.showError(this, "Number Too Long: "+number);
           return;           
        }
        
        if(!UserValidate.isEmailValid(email)){
           Message.showError(this, "Invalid Email Address");
           return; 
        }
        
        if(!UserValidate.isMobileValid(number)){
           Message.showError(this, "Invalid Mobile Number");
           return; 
        }

        int status = (statusIndex == 0) ? 1 : 2;

        UserValidate valid = new UserValidate();

        if (valid.isEmailExist(email)) {
            // Update existing user
            User user = new User();
            if (user.userUpdate(fname, lname, number, status, email)) {
                Message.showSucess(this, "Manager successfully updated");
            } else {
                Message.showError(this, "Something went wrong");
            }
        } else {
            // Add new user
            if(!UserValidate.isPWDValid(pass)){
                Message.showError(this, "Password must be at least 8 characters long "
                        + "and include uppercase, lowercase,"
                        + " number, and "
                        + "special character (@#$%^&+=).");
                return; 
            }
                    
            if (User.userAdd(fname, lname, number, status, email, pass, type)) {
                Message.showSucess(this, "Manager successfully added");
            } else {
                Message.showError(this, "Something went wrong");
            }
        }

        loadTable();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fnameInput = new javax.swing.JTextField();
        lnameInput = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        emailInput = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pwd = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        mobile = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        StatusCombo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel1.setText("Manage Managers");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(2, 2, 5, 5));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel2.setText("First Name");
        jPanel2.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setText("Last Name");
        jPanel2.add(jLabel3);

        fnameInput.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jPanel2.add(fnameInput);

        lnameInput.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jPanel2.add(lnameInput);

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel4.setText("Email Address");

        emailInput.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel5.setText("Password");

        pwd.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel6.setText("Mobile Number");

        mobile.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel7.setText("Status");

        StatusCombo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        StatusCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setBackground(new java.awt.Color(255, 112, 92));
        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add Manager");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(emailInput)
            .addComponent(mobile)
            .addComponent(pwd)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(StatusCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StatusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(0, 24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        fnameInput.setText("");
        lnameInput.setText("");
        emailInput.setText("");
        emailInput.setEditable(true);
        pwd.setText("");
        pwd.setEditable(true);
        mobile.setText("");
        StatusCombo.setSelectedIndex(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        addManager();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> StatusCombo;
    private javax.swing.JTextField emailInput;
    private javax.swing.JTextField fnameInput;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField lnameInput;
    private javax.swing.JTextField mobile;
    private javax.swing.JTextField pwd;
    // End of variables declaration//GEN-END:variables
}
