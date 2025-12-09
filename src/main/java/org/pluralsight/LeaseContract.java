package org.pluralsight;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        // calculating lease values
        double originalPrice = vehicleSold.getPrice();
        this.expectedEndingValue = originalPrice * 0.50;  // for 50%
        this.leaseFee = originalPrice * 0.07; // for 7%
    }


    @Override
    public double getTotalPrice() {
        double originalPrice = getVehicleSold().getPrice();
        // total = (price - ending value) + lease fee
        return (originalPrice - expectedEndingValue) + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        // All leases are financed at 4.0% for 36 months
        double totalPrice = getTotalPrice();
        double interestRate = 0.04;
        int months = 36;

        // Monthly payment formula
        double monthlyRate = interestRate / 12;
        double payment = totalPrice * (monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);

        return payment;
    }


    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
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
}
