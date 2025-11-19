package com.ccinfom.view;

import com.ccinfom.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class addOrder extends javax.swing.JFrame {
    private menu parentMenu;
    private int recordID = -1;

    public addOrder(menu parent) {
        initComponents();
        this.parentMenu = parent;
        this.setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    public addOrder(menu parent, int orderID) {
        this(parent);
        this.recordID = orderID;
        loadData(orderID);
        submitBttn.setText("Save");
        this.setTitle("Update Order");
    }

    public addOrder() { initComponents(); }

    private void loadData(int id) {
        String sql = "SELECT CustomerID, OrderStatus FROM Orders WHERE OrderID=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customerField.setText(String.valueOf(rs.getInt("CustomerID")));
                    statusField.setText(rs.getString("OrderStatus"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void submitBttnActionPerformed(java.awt.event.ActionEvent evt) {
        String custStr = customerField.getText().trim();
        String status = statusField.getText().trim();
        if (custStr.isEmpty()) { JOptionPane.showMessageDialog(this, "Customer ID required."); return; }
        try (Connection conn = DBConnection.getConnection()) {
            if (this.recordID == -1) {
                String sql = "INSERT INTO Orders (CustomerID, OrderStatus) VALUES (?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, Integer.parseInt(custStr));
                    ps.setString(2, status.isEmpty() ? "Pending" : status);
                    ps.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, "Order added.");
            } else {
                String sql = "UPDATE Orders SET CustomerID=?, OrderStatus=? WHERE OrderID=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, Integer.parseInt(custStr));
                    ps.setString(2, status.isEmpty() ? "Pending" : status);
                    ps.setInt(3, this.recordID);
                    ps.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, "Order updated.");
            }
            if (parentMenu != null) parentMenu.loadOrderData();
            this.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        customerField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        statusField = new javax.swing.JTextField();
        submitBttn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); jLabel1.setText("Add Order");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); jLabel2.setText("Customer ID");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); jLabel3.setText("Status");
        submitBttn.setText("Submit"); submitBttn.addActionListener(e -> submitBttnActionPerformed(e));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup().addGap(20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1).addComponent(jLabel2).addComponent(customerField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3).addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitBttn))
                .addContainerGap(20, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup().addGap(20).addComponent(jLabel1).addGap(10)
                .addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(customerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10).addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12).addComponent(submitBttn).addContainerGap(12, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        pack();
    }

    // Variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3;
    private javax.swing.JTextField customerField;
    private javax.swing.JTextField statusField;
    private javax.swing.JButton submitBttn;
}
