package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayFormatterTest {

    @Test
    public void testDeductions_LessThanThreeDependents() {
        double grossPay = 1000.00;
        int dependents = 2;
        double[] expectedDeductions = {0.06 * grossPay, 0.14 * grossPay, 0.05 * grossPay, 15.00};
        assertArrayEquals(expectedDeductions, PayFormatter.deductions(grossPay, dependents), 0.01, "Deductions for 2 dependents should include $15 for health insurance.");
    }

    @Test
    public void testDeductions_ThreeOrMoreDependents() {
        double grossPay = 1000.00;
        int dependents = 3;
        double[] expectedDeductions = {0.06 * grossPay, 0.14 * grossPay, 0.05 * grossPay, 35.00};
        assertArrayEquals(expectedDeductions, PayFormatter.deductions(grossPay, dependents), 0.01, "Deductions for 3 dependents should include $35 for health insurance.");
    }

    @Test
    public void testPayrollStringOutput() {
        PayFormatter payFormatter = new PayFormatter();
        double grossPay = 1000.00;
        double deductions = 300.00;
        int dependents = 2;
        double hoursWorked = 40.0;

        String expectedOutput = """
                           Hours:   40.0
                            Rate:   16.78 $/hr
                           Gross:   $ 1000.00
                        
                          SocSec:   $ 60.00
                          FedTax:   $ 140.00
                           StTax:   $ 50.00
                           Union:   $ 10.00
                             Ins:   $ 15.00
                        
                             Net:   $ 700.00\
                        """;

        assertEquals(expectedOutput, payFormatter.payrollStringOutput(grossPay, deductions, dependents, hoursWorked), "The payroll string output should match the expected format.");
    }

    @Test
    public void testPayrollStringOutput_WithOvertime() {
        PayFormatter payFormatter = new PayFormatter();
        double grossPay = 1500.00;
        double deductions = 450.00;
        int dependents = 3;
        double hoursWorked = 45.0;

        String expectedOutput = """
                           Hours:   45.0
                            Rate:   16.78 $/hr
                           Gross:   $ 1500.00
                        
                          SocSec:   $ 90.00
                          FedTax:   $ 210.00
                           StTax:   $ 75.00
                           Union:   $ 10.00
                             Ins:   $ 35.00
                        
                             Net:   $ 1050.00\
                        """;

        assertEquals(expectedOutput, payFormatter.payrollStringOutput(grossPay, deductions, dependents, hoursWorked), "The payroll string output should match the expected format with overtime.");
    }
}