package com.example.accountservice;

import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class StoreDB {

    public static void main(String[] args) {
        savepoint();
    }

    public static void insert() {
        String url = "jdbc:sqlite:C:/db/store.db";

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        String insertInvoiceSQL = "INSERT INTO \"invoice\" " +
                "(id, shipping_address, total_cost) VALUES (?, ?, ?)";

        String insertOrderSQL = "INSERT INTO \"order\" " +
                "(id, invoice_id, product_name) VALUES (?, ?, ?)";

        try (Connection con = dataSource.getConnection()) {

            // Disable auto-commit mode
            con.setAutoCommit(false);

            try (PreparedStatement insertInvoice = con.prepareStatement(insertInvoiceSQL);
                 PreparedStatement insertOrder = con.prepareStatement(insertOrderSQL)) {

                // Insert an invoice
                int invoiceId = 1;
                insertInvoice.setInt(1, invoiceId);
                insertInvoice.setString(2, "Dearborn, Michigan");
                insertInvoice.setInt(3, 100500);
                insertInvoice.executeUpdate();

                // Insert an order
                int orderId = 1;
                insertOrder.setInt(1, orderId);
                insertOrder.setInt(2, invoiceId);
                insertOrder.setString(3, "Ford Model A");
                insertOrder.executeUpdate();

                con.commit();
            } catch (SQLException e) {
                if (con != null) {
                    try {
                        System.err.print("Transaction is being rolled back");
                        con.rollback();
                    } catch (SQLException excep) {
                        excep.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void savepoint() {
        String url = "jdbc:sqlite:C:/db/store.db";

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        String insertInvoiceSQL = "INSERT INTO \"invoice\" " +
                "(id, shipping_address, total_cost) VALUES (?, ?, ?)";

        String selectAddressSQL = "SELECT shipping_address " +
                "FROM \"invoice\" WHERE id = ?";

        try (Connection con = dataSource.getConnection()) {

            // Disable auto-commit mode
            con.setAutoCommit(false);

            try (PreparedStatement insertInvoice = con.prepareStatement(insertInvoiceSQL)) {

                // Create a savepoint
                Savepoint savepoint = con.setSavepoint();

                // Insert an invoice
                int invoiceId = 1;
                insertInvoice.setInt(1, invoiceId);
                insertInvoice.setString(2, "Dearborn, Michigan");
                insertInvoice.setInt(3, 100500);
                insertInvoice.executeUpdate();

                PreparedStatement selectAddress = con.prepareStatement(selectAddressSQL);
                selectAddress.setInt(1, invoiceId);
                ResultSet resultSet = selectAddress.executeQuery();

                if (resultSet.getString(1).equals("Dearborn, Michigan")) {
                    con.rollback(savepoint);
                }

                con.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
