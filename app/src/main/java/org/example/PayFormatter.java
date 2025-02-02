package org.example;


public class PayFormatter {

    public static double[] deductions(double grossPay, int dependents) {
        double[] deductionsArr = new double[4];
        deductionsArr[0] = 0.06 * grossPay;
        deductionsArr[1] = 0.14 * grossPay;
        deductionsArr[2] = 0.05 * grossPay;
        deductionsArr[3] = (dependents >= 3) ? 35.00 : 15.00;
        return deductionsArr;
    }

    public String payrollStringOutput(double grossPay, double deductions, int dependents, double hoursWorked) {
        double[] deductionArr = deductions(grossPay, dependents);
        return String.format(
                """
                           Hours:   %.1f
                            Rate:   %.2f $/hr
                           Gross:   $ %.2f
                        
                          SocSec:   $ %.2f
                          FedTax:   $ %.2f
                           StTax:   $ %.2f
                           Union:   $ %.2f
                             Ins:   $ %.2f
                        
                             Net:   $ %.2f\
                        """,
                hoursWorked, 16.78, grossPay, deductionArr[0], deductionArr[1], deductionArr[2], 10.00, deductionArr[3], (grossPay - deductions)
        );
    }
}
