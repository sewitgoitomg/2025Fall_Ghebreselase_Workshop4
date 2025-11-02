package org.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalesContractTest {

    @Test
    public void testSaleWithFinancing() {

        Vehicle car = new Vehicle(111, 2020, "Honda", "civic", "car", "Blue", 25000, 20000.00);
        SalesContract contract = new SalesContract("20241102", "John", "john@email.com", car, true);


        double totalPrice = contract.getTotalPrice();
        double monthlyPayment = contract.getMonthlyPayment();


        assertEquals(21595.0, totalPrice, "Total should be $21,595");
        assertTrue(monthlyPayment > 0, "Monthly payment should be greater than $0");
    }

    @Test
    public void testSaleWithoutFinancing() {

        Vehicle car = new Vehicle(222, 2015, "Toyota", "Corolla", "car", "White", 80000, 8000.00);
        SalesContract contract = new SalesContract("20241102", "Jane", "jane@email.com", car, false);


        double totalPrice = contract.getTotalPrice();
        double monthlyPayment = contract.getMonthlyPayment();


        assertEquals(8795.0, totalPrice, "Total should be $8,795");
        assertEquals(0.0, monthlyPayment, "Monthly should be $0 (not financed)");
    }
}