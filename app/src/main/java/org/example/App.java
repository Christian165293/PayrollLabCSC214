package org.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayCalculator payCalculator = new PayCalculator();
        PayFormatter payFormatter = new PayFormatter();
        System.out.println("Welcome to the Payroll Program!\n");
        System.out.print("How many hours did you work this week?");
        int hoursWorked = scanner.nextInt();
        System.out.print("How many children do you have?");
        int numberOfDependents = scanner.nextInt();
        scanner.close();

        double grossPay = payCalculator.grossPayCalc(hoursWorked);
        double deductions = payCalculator.deductions(grossPay, numberOfDependents);
        System.out.println("\nPayroll Stub:\n\n" + payFormatter.payrollStringOutput(grossPay, deductions, numberOfDependents, hoursWorked));
        System.out.println("\nThank you for using the Payroll Program!");
    }
}
