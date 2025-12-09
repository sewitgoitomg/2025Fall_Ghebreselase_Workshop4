package org.pluralsight;

public class SalesContract extends Contract {

private double salesTaxAmount;
private double recordingFee;
private double processingFee;
private boolean isFinanced;

    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = calculateSalesTax(vehicleSold.getPrice());
        this.recordingFee = 100;
        this.processingFee = calculateProcessingFee(vehicleSold.getPrice());
        this.isFinanced = isFinanced;
    }
// Helper method for sales tax
    private double calculateSalesTax(double price) {
        return price * 0.05;
    }

//Helper method for processing fee
    private double calculateProcessingFee(double price) {
        if (price < 10000) {
            return 295;

        }else{
            return 495;
        }
    }

    @Override
    public double getTotalPrice() {
      double vehiclePrice= getVehicleSold().getPrice();
      return vehiclePrice+salesTaxAmount+recordingFee+processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0;
        }
        double totalPrice = getTotalPrice();
        double interestRate;
        int months;

        if (totalPrice >= 10000) {
            interestRate = 0.0425;
            months = 48;
        } else {
            interestRate = 0.0525;
            months = 24;
        }
        // Monthly payment formula: P * [r(1+r)^n] / [(1+r)^n - 1]
        // P = total price, r = monthly interest rate, n = number of months
        double monthlyRate = interestRate / 12;
        double payment = totalPrice * (monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);

        return payment;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }
    public int getVin() {
        return getVehicleSold().getVin();
    }

    public String getDate() {
        return getDateOfContract();
    }

    public double getPrice() {
        return getVehicleSold().getPrice();
    }

    public double getSalesTax() {
        return salesTaxAmount;
    }

    public boolean isFinanceOption() {
        return isFinanced;
    }
}
