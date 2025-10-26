package org.pluralsight;

import java.io.*;
import java.util.Scanner;

public class DealershipFileManager {
    private static final String FILE_PATH = "src/main/resources/inventory.csv";

    public static Dealership getDealership() {
        Dealership dealership = null;
        FileInputStream fs = null;
        Scanner scanner = null;

        try {
        fs = new FileInputStream(FILE_PATH);
        scanner = new Scanner(fs);

        // Read first line: dealership info
        if (scanner.hasNextLine()) {
            String header = scanner.nextLine();
            String[] dealershipInfo = header.split("\\|");

            if (dealershipInfo.length != 3) {
                System.out.println("ERROR: Dealership header line is malformed");
                return null;
            }

            dealership = new Dealership(
                    dealershipInfo[0].trim(),
                    dealershipInfo[1].trim(),
                    dealershipInfo[2].trim()
            );
        } else {
            System.out.println("ERROR: File is empty. Program cannot continue.");
            return null;
        }


        while (scanner.hasNextLine() ) {
                String input = scanner.nextLine();
                String[] data = input.split("\\|");

                int vin = Integer.parseInt(data[0]);
                int year = Integer.parseInt(data[1]);
                String make = data[2];
                String model = data[3];
                String vehicleType = data[4];
                String color = data[5];
                int odometer = Integer.parseInt(data[6]);
                double price = Double.parseDouble(data[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);

            }


        } catch (FileNotFoundException e) {
        System.out.println("ERROR: File not found - " + FILE_PATH);
    } catch (Exception e) {
        System.out.println("ERROR: Problem reading file - " + e.getMessage());
    } finally {
        try {
            if (scanner != null) scanner.close();
            if (fs != null) fs.close();
        } catch (Exception e) {
        }
    }

        return dealership;
}

public static void saveDealership(Dealership dealership) {
    try {
        // This is not in append mode so it will overwrite the whole file
        FileWriter fw = new FileWriter("src/main/resources/inventory.csv");

        // Write the header
        String headerRow = String.format("%s|%s|%s",
                dealership.getName(),
                dealership.getAddress(),
                dealership.getPhone());
        fw.write(headerRow);
        fw.write("\n");  // newline after header

        // Write each vehicle
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            String row = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
            fw.write(row);
            fw.write("\n");  // newline after each vehicle
        }

        fw.close();

    } catch (IOException e) {
        System.out.println("ERROR: Failed to save dealership - " + e.getMessage());
    }
}
}