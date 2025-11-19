package com.ccinfom.view;

import com.ccinfom.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class addTransportLog extends javax.swing.JFrame {
    private menu parentMenu;
    private int recordID = -1;

    public addTransportLog(menu parent) {
        initComponents();
        this.parentMenu = parent;
        this.setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    public addTransportLog(menu parent, int id) {
        this(parent);
        this.recordID = id;
        // loadData(id);
    }

    public addTransportLog() { initComponents(); }

    private void submitBttnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String transStr = transportField.getText().trim();
            String reqStr = requestField.getText().trim();
            String delStr = deliveryField.getText().trim();
            String arrStr = arrivalField.getText().trim();
            String status = statusField.getText().trim();
            if (transStr.isEmpty()||reqStr.isEmpty()) { JOptionPane.showMessageDialog(this, "Transport ID and Request ID required."); return; }

            try (Connection conn = DBConnection.getConnection()) {
                if (this.recordID == -1) {
                    String sql = "INSERT INTO TransportLogs (TransportID, RequestID, DeliveryDate, ArrivalDate, Status) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, Integer.parseInt(transStr));
                        ps.setInt(2, Integer.parseInt(reqStr));
                        ps.setDate(3, Date.valueOf(LocalDate.parse(delStr)));
                        ps.setDate(4, Date.valueOf(LocalDate.parse(arrStr)));
                        ps.setString(5, status.isEmpty()?"Pending":status);
                        ps.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(this, "Transport log added.");
                } else {
                    String sql = "UPDATE TransportLogs SET TransportID=?, RequestID=?, DeliveryDate=?, ArrivalDate=?, Status=? WHERE LogID=?";
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, Integer.parseInt(transStr));
                        ps.setInt(2, Integer.parseInt(reqStr));
                        ps.setDate(3, Date.valueOf(LocalDate.parse(delStr)));
                        ps.setDate(4, Date.valueOf(LocalDate.parse(arrStr)));
                        ps.setString(5, status.isEmpty()?"Pending":status);
                        ps.setInt(6, this.recordID);
                        ps.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(this, "Transport log updated.");
                }
                if (parentMenu != null) parentMenu.loadTransportLogData();
                this.dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        transportField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        requestField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        deliveryField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        arrivalField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        statusField = new javax.swing.JTextField();
        submitBttn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); jLabel1.setText("Add Transport Log");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); jLabel2.setText("Transport ID");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); jLabel3.setText("Request ID");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); jLabel4.setText("Delivery Date (YYYY-MM-DD)");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); jLabel5.setText("Arrival Date (YYYY-MM-DD)");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); jLabel6.setText("Status");
        submitBttn.setText("Submit"); submitBttn.addActionListener(e -> submitBttnActionPerformed(e));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(20)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1)
                .addComponent(jLabel2).addComponent(transportField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3).addComponent(requestField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4).addComponent(deliveryField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5).addComponent(arrivalField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel6).addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(submitBttn))
            .addContainerGap(20, Short.MAX_VALUE)));

        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(20)
            .addComponent(jLabel1).addGap(10).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(transportField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10).addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(requestField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10).addComponent(jLabel4).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(deliveryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10).addComponent(jLabel5).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(arrivalField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(10).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(12).addComponent(submitBttn).addContainerGap(12, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        pack();
    }

    // Variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
    private javax.swing.JTextField transportField, requestField, deliveryField, arrivalField, statusField;
    private javax.swing.JButton submitBttn;
}
