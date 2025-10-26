package org.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private static Scanner scanner = new Scanner(System.in);

    public UserInterface() {
        init();
    }

    private void init() {
        this.dealership = DealershipFileManager.getDealership();

        if (this.dealership == null) {
            System.out.println("ERROR: The dealership data file is missing or malformed");
            System.out.println("Please ensure 'inventory.csv' exists and has the correct format");
            System.out.println("The application cannot continue.");
            System.exit(1);
        }
    }

    public void display() {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 99:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("      " + dealership.getName());
        System.out.println("========================================");
        System.out.println("1 - Find vehicles by price range");
        System.out.println("2 - Find vehicles by make/model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("99 - Quit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("\nNo vehicles found.");
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        System.out.println("\n" + "=".repeat(120));
        System.out.println("VIN      Year   Make         Model        Type       Color      Odometer   Price");
        System.out.println("=".repeat(120));

        for (Vehicle vehicle : vehicles) {
            System.out.println(
                    padRight(String.valueOf(vehicle.getVin()), 9) +
                            padRight(String.valueOf(vehicle.getYear()), 7) +
                            padRight(vehicle.getMake(), 13) +
                            padRight(vehicle.getModel(), 13) +
                            padRight(vehicle.getVehicleType(), 11) +
                            padRight(vehicle.getColor(), 11) +
                            padRight(String.valueOf(vehicle.getOdometer()), 11) +
                            "$" + vehicle.getPrice()
            );
        }
        System.out.println("=".repeat(120));
        System.out.println("Total vehicles: " + vehicles.size());
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private String padRight(String s, int n) {
        if (s.length() >= n) {
            return s;
        }
        int spacesNeeded = n - s.length();
        return s + " ".repeat(spacesNeeded);
    }

    private void processAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    private void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    private void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    private void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum mileage: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }

    private void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type (car/truck/SUV/van): ");
        String type = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByType(type);
        displayVehicles(vehicles);
    }

    private void processAddVehicleRequest() {
        System.out.println("\n--- Add New Vehicle ---");

        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.print("Enter vehicle type (car/truck/SUV/van): ");
        String type = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        System.out.print("Enter odometer reading: ");
        int odometer = scanner.nextInt();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(vehicle);

        DealershipFileManager.saveDealership(dealership);

        System.out.println("\nVehicle added successfully!");
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private void processRemoveVehicleRequest() {
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle remove = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                remove = v;
                break;
            }
        }

        if (remove != null) {
            dealership.removeVehicle(remove);
            DealershipFileManager.saveDealership(dealership);
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle with VIN " + vin + " not found.");
        }

        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}