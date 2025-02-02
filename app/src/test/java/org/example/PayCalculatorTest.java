package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayCalculatorTest {

    @Test
    public void testGrossPayCalc_NoOvertime() {
        PayCalculator payCalculator = new PayCalculator();
        int hoursWorked = 40;
        double expectedGrossPay = 40 * 16.78;
        assertEquals(expectedGrossPay, payCalculator.grossPayCalc(hoursWorked), 0.01, "Gross pay for 40 hours should be 40 * 16.78.");
    }

    @Test
    public void testGrossPayCalc_WithOvertime() {
        PayCalculator payCalculator = new PayCalculator();
        int hoursWorked = 45;
        double expectedGrossPay = (5 * 16.78 * 1.5) + (40 * 16.78);
        assertEquals(expectedGrossPay, payCalculator.grossPayCalc(hoursWorked), 0.01, "Gross pay for 45 hours should include overtime.");
    }

    @Test
    public void testDeductions_LessThanThreeDependents() {
        PayCalculator payCalculator = new PayCalculator();
        double grossPay = 1000.00;
        int dependents = 2;
        double expectedDeductions = (0.06 * grossPay) + (0.14 * grossPay) + (0.05 * grossPay) + 15.00 + 10.00;
        assertEquals(expectedDeductions, payCalculator.deductions(grossPay, dependents), 0.01, "Deductions for 2 dependents should include $15 for health insurance.");
    }

    @Test
    public void testDeductions_ThreeOrMoreDependents() {
        PayCalculator payCalculator = new PayCalculator();
        double grossPay = 1000.00;
        int dependents = 3;
        double expectedDeductions = (0.06 * grossPay) + (0.14 * grossPay) + (0.05 * grossPay) + 35.00 + 10.00;
        assertEquals(expectedDeductions, payCalculator.deductions(grossPay, dependents), 0.01, "Deductions for 3 dependents should include $35 for health insurance.");
    }

    @Test
    public void testDeductions_ZeroGrossPay() {
        PayCalculator payCalculator = new PayCalculator();
        double grossPay = 0.00;
        int dependents = 1;
        double expectedDeductions = (0.06 * grossPay) + (0.14 * grossPay) + (0.05 * grossPay) + 15.00 + 10.00;
        assertEquals(expectedDeductions, payCalculator.deductions(grossPay, dependents), 0.01, "Deductions for zero gross pay should only include fixed amounts.");
    }

    @Test
    public void testDeductions_NegativeDependents() {
        PayCalculator payCalculator = new PayCalculator();
        double grossPay = 1000.00;
        int dependents = -1;
        double expectedDeductions = (0.06 * grossPay) + (0.14 * grossPay) + (0.05 * grossPay) + 15.00 + 10.00;
        assertEquals(expectedDeductions, payCalculator.deductions(grossPay, dependents), 0.01, "Deductions for negative dependents should default to $15 for health insurance.");
    }
}