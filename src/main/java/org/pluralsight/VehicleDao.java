package org.pluralsight;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class VehicleDao {
        private final String dbUrl;
        private final String dbUser;
        private final String dbPass;

        public VehicleDao(String dbUrl, String dbUser, String dbPass) {
            this.dbUrl = dbUrl;
            this.dbUser = dbUser;
            this.dbPass = dbPass;
        }


        private Vehicle createVehicleFromResultSet(ResultSet rs) throws SQLException {
            int vin = rs.getInt("VIN");
            int year = rs.getInt("year");
            String make = rs.getString("make");
            String model = rs.getString("model");
            String vehicleType = rs.getString("vehicleType");
            String color = rs.getString("color");
            int odometer = rs.getInt("odometer");
            double price = rs.getDouble("price");

            return new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        }



        public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
            List<Vehicle> vehicles = new ArrayList<>();
            String sql = "SELECT * FROM vehicles WHERE price >= ? AND price <= ?";

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setDouble(1, minPrice);
                ps.setDouble(2, maxPrice);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }

            } catch (SQLException e) {
                System.out.println("Error searching by price range");
                e.printStackTrace();
            }
            return vehicles;
        }

        public List<Vehicle> searchByMakeModel(String make, String model) {
            List<Vehicle> vehicles = new ArrayList<>();
            String sql = "SELECT * FROM vehicles WHERE make = ? AND model = ?";

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, make);
                ps.setString(2, model);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }

            } catch (SQLException e) {
                System.out.println("Error searching by make/model");
                e.printStackTrace();
            }
            return vehicles;
        }

        public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
            List<Vehicle> vehicles = new ArrayList<>();
            String sql = "SELECT * FROM vehicles WHERE year >= ? AND year <= ?";

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, minYear);
                ps.setInt(2, maxYear);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }

            } catch (SQLException e) {
                System.out.println("Error searching by year range");
                e.printStackTrace();
            }
            return vehicles;
        }

        public List<Vehicle> searchByColor(String color) {
            List<Vehicle> vehicles = new ArrayList<>();
            String sql = "SELECT * FROM vehicles WHERE color = ?";

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, color);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }

            } catch (SQLException e) {
                System.out.println("Error searching by color");
                e.printStackTrace();
            }
            return vehicles;
        }

        public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
            List<Vehicle> vehicles = new ArrayList<>();
            String sql = "SELECT * FROM vehicles WHERE odometer >= ? AND odometer <= ?";

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, minMileage);
                ps.setInt(2, maxMileage);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }

            } catch (SQLException e) {
                System.out.println("Error searching by mileage range");
                e.printStackTrace();
            }
            return vehicles;
        }

        public List<Vehicle> searchByType(String vehicleType) {
            List<Vehicle> vehicles = new ArrayList<>();
            String sql = "SELECT * FROM vehicles WHERE vehicle_type = ?";

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, vehicleType);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }

            } catch (SQLException e) {
                System.out.println("Error searching by type");
                e.printStackTrace();
            }
            return vehicles;
        }

        public List<Vehicle> getAllVehicles() {
            List<Vehicle> vehicles = new ArrayList<>();
            String sql = "SELECT * FROM vehicles";

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    vehicles.add(createVehicleFromResultSet(rs));
                }

            } catch (SQLException e) {
                System.out.println("Error getting all vehicles");
                e.printStackTrace();
            }
            return vehicles;
        }

        // PHASE 2: Add and Remove methods

        public void addVehicle(Vehicle vehicle) {
            String sql = "INSERT INTO vehicles (vin, year, make, model, vehicle_type, color, odometer, price) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, vehicle.getVin());
                ps.setInt(2, vehicle.getYear());
                ps.setString(3, vehicle.getMake());
                ps.setString(4, vehicle.getModel());
                ps.setString(5, vehicle.getVehicleType());
                ps.setString(6, vehicle.getColor());
                ps.setInt(7, vehicle.getOdometer());
                ps.setDouble(8, vehicle.getPrice());

                ps.executeUpdate();
                System.out.println("Vehicle added successfully!");

            } catch (SQLException e) {
                System.out.println("Error adding vehicle");
                e.printStackTrace();
            }
        }

        public void removeVehicle(int vin) {
            String sql = "DELETE FROM vehicles WHERE vin = ?";

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, vin);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Vehicle removed successfully!");
                } else {
                    System.out.println("Vehicle not found.");
                }

            } catch (SQLException e) {
                System.out.println("Error removing vehicle");
                e.printStackTrace();
            }
        }

        public Vehicle findVehicleByVin(int vin) {
            String sql = "SELECT * FROM vehicles WHERE vin = ?";
            Vehicle vehicle = null;

            try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, vin);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    vehicle = createVehicleFromResultSet(rs);
                }

            } catch (SQLException e) {
                System.out.println("Error finding vehicle by VIN");
                e.printStackTrace();
            }
            return vehicle;
        }
    }

