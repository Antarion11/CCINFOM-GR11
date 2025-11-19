package com.ccinfom.view;

import com.ccinfom.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class addStockLog extends javax.swing.JFrame {
    private menu parentMenu;
    private int recordID = -1;

    public addStockLog(menu parent) {
        initComponents();
        this.parentMenu = parent;
        this.setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    public addStockLog(menu parent, int id) {
        this(parent);
        this.recordID = id;
        loadData(id);
        submitBttn.setText("Save");
    }

    public addStockLog() { initComponents(); }

    private void loadData(int id) { /* not implemented for now */ }

    private void submitBttnActionPerformed(java.awt.event.ActionEvent evt) {
        String supp = supplierField.getText().trim();
        String prod = productField.getText().trim();
        String qty = quantityField.getText().trim();
        String type = typeField.getText().trim();
        if (supp.isEmpty() || prod.isEmpty() || qty.isEmpty() || type.isEmpty()) { JOptionPane.showMessageDialog(this, "Fill all fields."); return; }
        try (Connection conn = DBConnection.getConnection()) {
            if (this.recordID == -1) {
                String sql = "INSERT INTO StockLogs (SupplierID, ProductID, Quantity, TransactionType) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, Integer.parseInt(supp));
                    ps.setInt(2, Integer.parseInt(prod));
                    ps.setInt(3, Integer.parseInt(qty));
                    ps.setString(4, type);
                    ps.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, "Stock log added.");
            } else {
                String sql = "UPDATE StockLogs SET SupplierID=?, ProductID=?, Quantity=?, TransactionType=? WHERE StockLogID=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, Integer.parseInt(supp));
                    ps.setInt(2, Integer.parseInt(prod));
                    ps.setInt(3, Integer.parseInt(qty));
                    ps.setString(4, type);
                    ps.setInt(5, this.recordID);
                    ps.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, "Stock log updated.");
            }
            if (parentMenu != null) parentMenu.loadStockData();
            this.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        supplierField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        productField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        quantityField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        typeField = new javax.swing.JTextField();
        submitBttn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); jLabel1.setText("Add Stock Log");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); jLabel2.setText("Supplier ID");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); jLabel3.setText("Product ID");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); jLabel4.setText("Quantity");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); jLabel5.setText("Type (new_stock/restock)");
        submitBttn.setText("Submit"); submitBttn.addActionListener(e -> submitBttnActionPerformed(e));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(20)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1)
                .addComponent(jLabel2).addComponent(supplierField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3).addComponent(productField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4).addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5).addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(submitBttn))
            .addContainerGap(20, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(20)
            .addComponent(jLabel1).addGap(10).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(supplierField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10).addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(productField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10).addComponent(jLabel4).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10).addComponent(jLabel5).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(12).addComponent(submitBttn).addContainerGap(12, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        pack();
    }

    // Variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
    private javax.swing.JTextField supplierField, productField, quantityField, typeField;
    private javax.swing.JButton submitBttn;
}
