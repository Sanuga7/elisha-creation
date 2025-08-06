/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.elisha.panel;

import com.elisha.database.Database;
import com.elisha.loggers.Loggers;
import com.elisha.optionpane.Message;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sanuga
 */
public class ManageStocks extends javax.swing.JPanel {

    /**
     * Creates new form ManagerCustomer
     */
    private static final String cls = ManageStocks.class.getName();
    private static int colorId;
    private static int sizeId;
    private static int qtyId;
    private static int stockId;
    private static int variationId;
    private static JTextField id;
    private static JTextField name;

    public ManageStocks() {
        initComponents();
        loadTable();
        loadStatus();
        loadProduct();
        loadSupplier();
        loadColor();
        loadSize();
        init();
    }
    
    private void init(){
       id = new JTextField();
       id.setText("");
       id.setVisible(false);
       name = new JTextField();
       name.setText("");
       name.setVisible(false);
    }

    private ResultSet searchData(String query) {
        ResultSet rs = null;
        try {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elisha_db", "root", "2006@Sanuga");
                Statement smt = c.createStatement();
                rs = smt.executeQuery(query);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private void loadStatus() {
        try {
            ResultSet rs = searchData("SELECT * FROM `status`");
            Vector<String> status = new Vector();
            while (rs.next()) {
                String statusName = rs.getString("type");
                int statusId = rs.getInt("id");
                status.add(statusName);
            }
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(status);
            StatusCombo.setModel(dcm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadProduct() {
        try {
            ResultSet rs = searchData("SELECT * FROM `product`");
            Vector<String> product = new Vector();
            while (rs.next()) {
                String productTitle = rs.getString("title");
                int productId = rs.getInt("id");
                product.add(productTitle);
            }
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(product);
            ProductCombo.setModel(dcm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSupplier() {
        try {
            ResultSet rs = searchData("SELECT * FROM `supplier`");
            Vector<String> supplier = new Vector();
            while (rs.next()) {
                String supplierName = rs.getString("name");
                int supplierId = rs.getInt("id");
                supplier.add(supplierName);
            }
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(supplier);
            SupplierCombo.setModel(dcm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadColor() {
        try {
            ResultSet rs = searchData("SELECT * FROM `color`");
            Vector<String> color = new Vector();
            while (rs.next()) {
                String colorName = rs.getString("name");
                int colorID = rs.getInt("id");
                color.add(colorName);
            }
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(color);
            ColorCombo.setModel(dcm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSize() {
        try {
            ResultSet rs = searchData("SELECT * FROM `size`");
            Vector<String> size = new Vector();
            while (rs.next()) {
                String sizeName = rs.getString("size");
                int sizeID = rs.getInt("id");
                size.add(sizeName);
            }
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(size);
            SizeCombo.setModel(dcm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadTable() {
        try (Connection conn = Database.createConnection()) {
            if (conn != null) {
                String query
                        = "SELECT "
                        + "    p.title, "
                        + "    st.received_price, "
                        + "    st.selling_price, "
                        + "    st.added_time, "
                        + "    st.updated_time, "
                        + "    c.name AS color, "
                        + "    s.size AS size, "
                        + "    v.quantity, "
                        + "    st.id "
                        + "FROM stock st "
                        + "INNER JOIN stock_has_product shp ON st.id = shp.stock_id "
                        + "INNER JOIN product p ON shp.product_id = p.id "
                        + "INNER JOIN stock_has_variation shv ON st.id = shv.stock_id "
                        + "INNER JOIN variation v ON shv.variation_id = v.id "
                        + "INNER JOIN color c ON v.color_id = c.id "
                        + "INNER JOIN size s ON v.size_id = s.id;";

                try (Statement stmt = conn.createStatement()) {
                    ResultSet rs = stmt.executeQuery(query);

                    Vector<String> headers = new Vector<>();
                    headers.add("id");
                    headers.add("title");
                    headers.add("received_price");
                    headers.add("selling_price");
                    headers.add("time");
                    headers.add("color");
                    headers.add("size");
                    headers.add("Quantity");
                    headers.add("Edit");

                    Vector<Vector<String>> data = new Vector<>();

                    while (rs.next()) {
                        Vector<String> row = new Vector<>();
                        row.add(rs.getString("id"));
                        row.add(rs.getString("title"));
                        row.add(rs.getString("received_price"));
                        row.add(rs.getString("selling_price"));
                        if(rs.getString("updated_time") != null){
                           row.add(rs.getString("updated_time"));
                        }else{
                           row.add(rs.getString("added_time"));
                        }
                        row.add(rs.getString("color"));
                        row.add(rs.getString("size"));
                        row.add(rs.getString("quantity"));
                        row.add("edit");
                        data.add(row);
                    }

                    DefaultTableModel model = new DefaultTableModel(data, headers) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };

                    jTable1.setModel(model);
                    jTable1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int row = jTable1.rowAtPoint(e.getPoint());
                            int column = jTable1.columnAtPoint(e.getPoint());

                            if (column == 8 && row >= 0) {
                                String idVal = jTable1.getValueAt(row, 0).toString();
                                String titleVal = jTable1.getValueAt(row, 1).toString();
                                String color = jTable1.getValueAt(row, 5).toString();
                                String size = jTable1.getValueAt(row, 6).toString();
                                String qty = jTable1.getValueAt(row, 7).toString();
                                setData(color, size, qty);
                                jButton1.setText("Update Stock");
                                id.setText(idVal);
                                name.setText(titleVal);
                            }
                        }
                    });
                }
            }
        } catch (SQLException e) {
            Loggers.logServe(e.getMessage(), cls);
        }
    }

    private void setData(String color, String size, String qty) {
        try (Connection conn = Database.createConnection()) {
            if (conn != null) {
                String query1 = "SELECT id FROM color WHERE name = ?";
                String query2 = "SELECT id FROM size WHERE size = ?";

                try (PreparedStatement stmt = conn.prepareStatement(query1); PreparedStatement stmt1 = conn.prepareStatement(query2)) {

                    stmt.setString(1, color);
                    stmt1.setString(1, size);

                    ResultSet rs = stmt.executeQuery();
                    ResultSet rs1 = stmt1.executeQuery();

                    if (rs.next()) {
                        colorId = rs.getInt("id");
                    }
                    if (rs1.next()) {
                        sizeId = rs1.getInt("id");
                    }
                    qtyId = Integer.parseInt(qty);

                    String query3
                            = "SELECT v.*, shv.*, st.*, p.title, s.name AS supplier_name "
                            + "FROM variation v "
                            + "INNER JOIN stock_has_variation shv ON v.id = shv.variation_id "
                            + "INNER JOIN supplier s ON shv.supplier_id = s.id "
                            + "INNER JOIN stock st ON shv.stock_id = st.id "
                            + "INNER JOIN stock_has_product shp ON st.id = shp.stock_id "
                            + "INNER JOIN product p ON shp.product_id = p.id "
                            + "WHERE v.color_id = ? AND v.size_id = ? AND v.quantity = ?";

                    try (PreparedStatement stmt2 = conn.prepareStatement(query3)) {
                        stmt2.setInt(1, colorId);
                        stmt2.setInt(2, sizeId);
                        stmt2.setInt(3, qtyId);

                        ResultSet rs3 = stmt2.executeQuery();

                        if (rs3.next()) {
                            stockId = rs3.getInt("stock_id");

                            // Set form fields
                            int status = rs3.getInt("status_id");
                            StatusCombo.setSelectedIndex(status - 1); // Assuming Combo is 0-based

                            qtyInput.setText(rs3.getString("quantity"));
                            Atime.setText(rs3.getString("added_time"));
                            Rprice.setText(rs3.getString("received_price"));
                            Sprice.setText(rs3.getString("selling_price"));
                            ColorCombo.setSelectedItem(color);
                            SizeCombo.setSelectedItem(size);

                            ProductCombo.setSelectedItem(rs3.getString("title"));
                            SupplierCombo.setSelectedItem(rs3.getString("supplier_name"));
                            ProductCombo.setEnabled(false);
                            SupplierCombo.setEnabled(false);
                            ColorCombo.setEnabled(false);
                            SizeCombo.setEnabled(false);
                            qtyInput.setEnabled(false);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            Loggers.logServe(e.getMessage(), cls);
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
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ProductCombo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        SupplierCombo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        Rprice = new javax.swing.JTextField();
        Sprice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Atime = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        StatusCombo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        SizeCombo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        ColorCombo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        qtyInput = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1128, 850));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel1.setText("Manage Stocks");

        jScrollPane1.setFocusable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel2.setText("Add New Stock");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setText("Select Product: ");

        ProductCombo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel4.setText("Select Supplier:");

        SupplierCombo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel5.setText("Received Price");

        Rprice.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        Sprice.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel6.setText("Selling Price");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel7.setText("Added Time");

        Atime.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        Atime.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel8.setText("Stock Status: ");

        StatusCombo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel9.setText("Select Size:");

        SizeCombo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel10.setText("Select Color");

        ColorCombo.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel11.setText("Quantity");

        qtyInput.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 112, 92));
        jButton1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add Stock");
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProductCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SupplierCombo, 0, 235, Short.MAX_VALUE)
                            .addComponent(SizeCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Sprice)
                            .addComponent(Rprice)
                            .addComponent(ColorCombo, 0, 192, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Atime, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StatusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qtyInput, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ProductCombo)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Rprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Atime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SupplierCombo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Sprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(StatusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SizeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ColorCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qtyInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator2)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1128, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 952, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(173, 173, 173)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ProductCombo.setSelectedIndex(1);
        SupplierCombo.setSelectedIndex(1);
        StatusCombo.setSelectedIndex(1);
        ColorCombo.setSelectedIndex(1);
        SizeCombo.setSelectedIndex(1);
        qtyInput.setText(" ");
        Rprice.setText("");
        Sprice.setText("");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        Atime.setText(dtf.format(time));
        ProductCombo.setEnabled(true);
        SupplierCombo.setEnabled(true);
        ColorCombo.setEnabled(true);
        SizeCombo.setEnabled(true);
        qtyInput.setEnabled(true);
        jButton1.setText("Add Stock");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String rPrice = Rprice.getText();
        String sPrice = Sprice.getText();
        int status = StatusCombo.getSelectedIndex()+1;
        int color = ColorCombo.getSelectedIndex()+1;
        int productId = ProductCombo.getSelectedIndex() + 1;
        int size = SizeCombo.getSelectedIndex()+1;
        int supplier = SupplierCombo.getSelectedIndex()+1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        String now = dtf.format(time);
        int qty = Integer.parseInt(qtyInput.getText());
        
        if(jButton1.getText().equalsIgnoreCase("add stock")){
          
            String insertStock = "INSERT INTO stock (received_price, selling_price, added_time, updated_time, status_id) VALUES (?, ?, ?, ?, ?)";
            String insertStockProduct = "INSERT INTO stock_has_product (stock_id, product_id) VALUES (?, ?)";
            String insertVariation = "INSERT INTO variation (quantity, color_id, size_id) VALUES (?, ?, ?)";
            String insertStockVariation = "INSERT INTO stock_has_variation (stock_id, variation_id, supplier_id) VALUES (?, ?, ?)";

            try (Connection conn = Database.createConnection()) {
                conn.setAutoCommit(false);

                // 1. Insert stock
                try (PreparedStatement psStock = conn.prepareStatement(insertStock, Statement.RETURN_GENERATED_KEYS)) {
                    psStock.setString(1, rPrice);
                    psStock.setString(2, sPrice);
                    psStock.setString(3, now);
                    psStock.setString(4, now);
                    psStock.setInt(5, status);
                    psStock.executeUpdate();

                    ResultSet rsStock = psStock.getGeneratedKeys();
                    if (!rsStock.next()) throw new SQLException("Failed to insert stock");
                    int stockId = rsStock.getInt(1);

                    // 2. Link stock to product
                    try (PreparedStatement psStockProd = conn.prepareStatement(insertStockProduct)) {
                        psStockProd.setInt(1, stockId);
                        psStockProd.setInt(2, productId);
                        psStockProd.executeUpdate();
                    }

                    // 3. Insert variation
                    try (PreparedStatement psVariation = conn.prepareStatement(insertVariation, Statement.RETURN_GENERATED_KEYS)) {
                        psVariation.setInt(1, qty);
                        psVariation.setInt(2, color);
                        psVariation.setInt(3, size);
                        psVariation.executeUpdate();

                        ResultSet rsVar = psVariation.getGeneratedKeys();
                        if (!rsVar.next()) throw new SQLException("Failed to insert variation");
                        int variationId = rsVar.getInt(1);

                        // 4. Link stock to variation and supplier
                        try (PreparedStatement psStockVar = conn.prepareStatement(insertStockVariation)) {
                            psStockVar.setInt(1, stockId);
                            psStockVar.setInt(2, variationId);
                            psStockVar.setInt(3, supplier);
                            psStockVar.executeUpdate();
                        }
                    }

                    conn.commit();
                    Message.showSucess(this, "Stock successfully added!");
                    loadTable();

                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManageStocks.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else{
        
            String query =  "UPDATE stock st " +
                        "INNER JOIN stock_has_product shp ON st.id = shp.stock_id " +
                        "INNER JOIN product p ON shp.product_id = p.id " +
                        "INNER JOIN stock_has_variation shv ON st.id = shv.stock_id " +
                        "INNER JOIN variation v ON shv.variation_id = v.id " +
                        "INNER JOIN color c ON v.color_id = c.id " +
                        "INNER JOIN size s ON v.size_id = s.id " +
                        "SET " +
                        "    st.received_price = ?, " +
                        "    st.selling_price = ?, " +
                        "    st.updated_time = ?, " +
                        "    st.status_id = ? " +
                        "WHERE st.id = ? " +
                        "  AND p.title = ? ";
        
            try(Connection conn = Database.createConnection()){
                if(conn != null){
                   try(PreparedStatement stmt = conn.prepareStatement(query)){
                      stmt.setString(1, rPrice);
                      stmt.setString(2, sPrice);
                      stmt.setString(3, now);
                      stmt.setInt(4, status);
                      stmt.setInt(5, Integer.parseInt(id.getText())); 
                      stmt.setString(6, name.getText());

                      int update = stmt.executeUpdate();
                      if(update == 1){
                          Message.showSucess(this, "Successfully updated");
                          loadTable();
                      }
                   }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManageStocks.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Atime;
    private javax.swing.JComboBox<String> ColorCombo;
    private javax.swing.JComboBox<String> ProductCombo;
    private javax.swing.JTextField Rprice;
    private javax.swing.JComboBox<String> SizeCombo;
    private javax.swing.JTextField Sprice;
    private javax.swing.JComboBox<String> StatusCombo;
    private javax.swing.JComboBox<String> SupplierCombo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField qtyInput;
    // End of variables declaration//GEN-END:variables
}
