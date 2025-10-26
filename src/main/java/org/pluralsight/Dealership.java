package org.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;


    public Dealership() {

    }

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> result = new ArrayList<>();

        for (Vehicle v : inventory) {
            if (v.getMake() != null && v.getModel() != null &&
                    v.getMake().equalsIgnoreCase(make) &&
                    v.getModel().equalsIgnoreCase(model)) {
                result.add(v);
            }
        }
        return result;
        }

        public List<Vehicle> getVehiclesByYear ( int min, int max){
            List<Vehicle> result = new ArrayList<>();
            for (Vehicle v : inventory) {
                if (v.getYear() >= min && v.getYear() <= max) {
                    result.add(v);
                }
            }
            return result;
        }

        public List<Vehicle> getVehiclesByColor (String color){
            List<Vehicle> result = new ArrayList<>();
            for (Vehicle v : inventory) {
                if (v.getColor() != null && v.getColor().equalsIgnoreCase(color))  {
                    result.add(v);
                }
            }
            return result;
        }

        public List<Vehicle> getVehiclesByMileage ( double min, double max){
            List<Vehicle> result = new ArrayList<>();
            for (Vehicle v : inventory) {
                if (v.getOdometer() >= min && v.getOdometer() <= max) {
                    result.add(v);
                }
            }
            return result;
        }

        public List<Vehicle> getVehiclesByType (String vehicleType){
            List<Vehicle> result = new ArrayList<>();
            for (Vehicle v : inventory) {
                if (v.getVehicleType().equalsIgnoreCase(vehicleType)) {
                    result.add(v);
                }
            }
            return result;
        }

        public List<Vehicle> getAllVehicles () {
            return new ArrayList<>(inventory);
        }

        public void addVehicle (Vehicle vehicle){
            inventory.add(vehicle);
        }

        public void removeVehicle (Vehicle vehicle){
            inventory.remove(vehicle);
        }

        public String getName () {
            return name;
        }

        public void setName (String name){
            this.name = name;
        }

        public String getAddress () {
            return address;
        }

        public void setAddress (String address){
            this.address = address;
        }

        public String getPhone () {
            return phone;
        }

        public void setPhone (String phone){
            this.phone = phone;
        }
        public List<Vehicle> getVehicles () {
            return inventory;
        }
        public void setVehicles (List < Vehicle > inventory) {
            this.inventory = inventory;
        }
    }
