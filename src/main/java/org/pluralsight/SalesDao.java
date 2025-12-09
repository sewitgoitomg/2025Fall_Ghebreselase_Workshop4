package org.pluralsight;


import java.sql.*;

public class SalesDao {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;

    public SalesDao(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public void addSalesContract(SalesContract contract) {
        String sql = "INSERT INTO sales_contracts (vin, sale_date, price, sales_tax, recording_fee, processing_fee, finance_option) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, contract.getVin());
            ps.setString(2, contract.getDate());
            ps.setDouble(3, contract.getPrice());
            ps.setDouble(4, contract.getSalesTax());
            ps.setDouble(5, contract.getRecordingFee());
            ps.setDouble(6, contract.getProcessingFee());
            ps.setBoolean(7, contract.isFinanceOption());

            ps.executeUpdate();
            System.out.println("Sales contract saved successfully!");

        } catch (SQLException e) {
            System.out.println("Error saving sales contract");
            e.printStackTrace();
        }
    }
}
