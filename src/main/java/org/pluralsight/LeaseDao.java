package org.pluralsight;

import java.sql.*;

public class LeaseDao {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;

    public LeaseDao(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public void addLeaseContract(LeaseContract contract) {
        String sql = "INSERT INTO lease_contracts (vin, lease_date, price, expected_ending_value, lease_fee, monthly_payment) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, contract.getVin());
            ps.setString(2, contract.getDate());
            ps.setDouble(3, contract.getPrice());
            ps.setDouble(4, contract.getExpectedEndingValue());
            ps.setDouble(5, contract.getLeaseFee());
            ps.setDouble(6, contract.getMonthlyPayment());

            ps.executeUpdate();
            System.out.println("Lease contract saved successfully!");

        } catch (SQLException e) {
            System.out.println("Error saving lease contract");
            e.printStackTrace();
        }
    }
}
