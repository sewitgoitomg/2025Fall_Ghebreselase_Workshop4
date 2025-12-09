//package org.pluralsight;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class ContractFileManager {
//    private static final String FILE_PATH = "src/main/resources/contracts.csv";
//    public static void saveContract(Contract contract) {
//        try {
//            FileWriter fw = new FileWriter(FILE_PATH, true);
//            BufferedWriter writer = new BufferedWriter(fw);
//
//                if (contract instanceof SalesContract){
//                    writesSalesContract(writer,(SalesContract) contract);
//                } else if (contract instanceof LeaseContract) {
//                    writeLeaseContract(writer,(LeaseContract) contract);
//
//                }
//                writer.close();
//                fw.close();
//            System.out.println("Contract saved as CSV");
//
//
//        } catch (IOException e) {
//            System.out.println("Failed to save contract");
//        }
//    }
//
//    public static void writesSalesContract(BufferedWriter writer, SalesContract contract) throws IOException {
//        Vehicle vehicle = contract.getVehicleSold();
//
//        String line = "Sale" + "|" +
//                contract.getDateOfContract() + "|" +
//                contract.getCustomerName() + "|" +
//                contract.getCustomerEmail() + "|" +
//                vehicle.getVin() + "|" +
//                vehicle.getYear() + "|" +
//                vehicle.getMake() + "|" +
//                vehicle.getModel() + "|" +
//                vehicle.getVehicleType() + "|" +
//                vehicle.getColor() + "|" +
//                vehicle.getOdometer() + "|" +
//                vehicle.getPrice() + "|" +
//                contract.getSalesTaxAmount() + "|" +
//                contract.getRecordingFee() + "|" +
//                contract.getProcessingFee() + "|" +
//                contract.getTotalPrice() + "|" +
//                (contract.isFinanced() ? "YES" : "NO") + "|" +
//                contract.getMonthlyPayment();
//
//
//            writer.write(line);
//
//            writer.newLine();
//
//
//    }
//
//    private static void writeLeaseContract(BufferedWriter writer, LeaseContract contract) throws IOException {
//        Vehicle vehicle = contract.getVehicleSold();
//
//        String line = "LEASE" + "|" +
//                contract.getDateOfContract() + "|" +
//                contract.getCustomerName() + "|" +
//                contract.getCustomerEmail() + "|" +
//                vehicle.getVin() + "|" +
//                vehicle.getYear() + "|" +
//                vehicle.getMake() + "|" +
//                vehicle.getModel() + "|" +
//                vehicle.getVehicleType() + "|" +
//                vehicle.getColor() + "|" +
//                vehicle.getOdometer() + "|" +
//                vehicle.getPrice() + "|" +
//                contract.getExpectedEndingValue() + "|" +
//                contract.getLeaseFee() + "|" +
//                contract.getTotalPrice() + "|" +
//                contract.getMonthlyPayment();
//
//
//            writer.write(line);
//
//            writer.newLine();
//
//    }
//
//
//}
