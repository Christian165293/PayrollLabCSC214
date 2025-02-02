package org.example;

    public class PayCalculator {

    public double grossPayCalc(int hoursWorked) {
        if (hoursWorked <= 40) {
            return (hoursWorked * 16.78);
        } else {
            return ((hoursWorked - 40) * 16.78) * 1.5 + (40 * 16.78);
        }
    }

    public double deductions(double grossPay, int dependents) {
        double socialSec = 0.06 * grossPay;
        double federalIncomeTax = 0.14 * grossPay;
        double stateIncomeTax = 0.05 * grossPay;
        double healthInsurance = (dependents >= 3) ? 35.00 : 15.00;
        return socialSec + federalIncomeTax + stateIncomeTax + healthInsurance + 10;
    }
}
