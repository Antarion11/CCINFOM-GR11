package com.ccinfom.view;

import com.ccinfom.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class addReturnRequest extends javax.swing.JFrame {

    private menu parentMenu;
    private int recordID = -1;

    public addReturnRequest(menu parent) {
        initComponents();
        this.parentMenu = parent;
        this.setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    public addReturnRequest(menu parent, int requestID) {
        this(parent);
        this.recordID = requestID;
        loadData(requestID);
        submitBttn.setText("Save");
        this.setTitle("Update Return Request");
    }

    public addReturnRequest() { initComponents(); }

    private void loadData(int id) {
        String sql = "SELECT OrderID, CustomerID, ProductID, ReturnReason, Status FROM ReturnRequests WHERE RequestID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    orderField.setText(String.valueOf(rs.getInt("OrderID")));
                    customerField.setText(String.valueOf(rs.getInt("CustomerID")));
                    productField.setText(String.valueOf(rs.getInt("ProductID")));
                    reasonArea.setText(rs.getString("ReturnReason"));
                    statusField.setText(rs.getString("Status"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void submitBttnActionPerformed(java.awt.event.ActionEvent evt) {
        String orderStr = orderField.getText().trim();
        String custStr = customerField.getText().trim();
        String prodStr = productField.getText().trim();
        String reason = reasonArea.getText().trim();
        String status = statusField.getText().trim();

        if (orderStr.isEmpty() || custStr.isEmpty() || prodStr.isEmpty() || reason.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill required fields.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            if (this.recordID == -1) {
                String sql = "INSERT INTO ReturnRequests (OrderID, CustomerID, ProductID, ReturnReason, Status) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, Integer.parseInt(orderStr));
                    ps.setInt(2, Integer.parseInt(custStr));
                    ps.setInt(3, Integer.parseInt(prodStr));
                    ps.setString(4, reason);
                    ps.setString(5, status.isEmpty() ? "Pending" : status);
                    ps.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, "Return request added.");
            } else {
                String sql = "UPDATE ReturnRequests SET OrderID=?, CustomerID=?, ProductID=?, ReturnReason=?, Status=? WHERE RequestID=?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, Integer.parseInt(orderStr));
                    ps.setInt(2, Integer.parseInt(custStr));
                    ps.setInt(3, Integer.parseInt(prodStr));
                    ps.setString(4, reason);
                    ps.setString(5, status.isEmpty() ? "Pending" : status);
                    ps.setInt(6, this.recordID);
                    ps.executeUpdate();
                }
                JOptionPane.showMessageDialog(this, "Return request updated.");
            }

            if (parentMenu != null) parentMenu.loadReturnRequestData();
            this.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- Generated UI ---
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        orderField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        customerField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        productField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reasonArea = new javax.swing.JTextArea();
        submitBttn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        statusField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Add Return Request");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel2.setText("Order ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel3.setText("Customer ID");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel4.setText("Product ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel5.setText("Return Reason");

        reasonArea.setColumns(20);
        reasonArea.setRows(5);
        jScrollPane1.setViewportView(reasonArea);

        submitBttn.setText("Submit");
        submitBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { submitBttnActionPerformed(evt); }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel6.setText("Status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(orderField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(customerField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(productField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitBttn))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(submitBttn)
                .addContainerGap(12, Short.MAX_VALUE))
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
    }

    // Variables
    private javax.swing.JButton submitBttn;
    private javax.swing.JTextField orderField;
    private javax.swing.JTextField customerField;
    private javax.swing.JTextField productField;
    private javax.swing.JTextArea reasonArea;
    private javax.swing.JTextField statusField;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
}
