package CreditCalculator;

import java.util.Scanner;
import java.lang.Math;

public class CreditCalculator {
    public static void main(String[] args) {
        String loanType = System.getProperty("type");
        if (loanType == null || (!loanType.equals("annuity") && !loanType.equals("diff"))) {
            System.out.println("Invalid or missing loan type specified.");
            return;
        }

        String principalStr = System.getProperty("principal");
        String periodsStr = System.getProperty("periods");
        String interestStr = System.getProperty("interest");
        String paymentStr = System.getProperty("payment");

        if (principalStr == null || periodsStr == null || interestStr == null) {
            System.out.println("Incomplete parameters. Please provide principal, periods, and interest.");
            return;
        }

        double initialAmount = Double.parseDouble(principalStr);
        int loanPeriods = Integer.parseInt(periodsStr);
        double monthlyInterestRate = Double.parseDouble(interestStr) / 100 / 12;

        if (loanType.equals("diff")) {
            if (paymentStr != null) {
                System.out.println("Incorrect parameters. Payment should not be specified for differentiated payments.");
                return;
            }

            double totalPayments = 0;
            for (int month = 1; month <= loanPeriods; month++) {
                double monthlyPaymentDiff = initialAmount / loanPeriods + monthlyInterestRate * (initialAmount - (initialAmount * (month - 1)) / loanPeriods);
                System.out.println("Month " + month + ": payment is " + (int) Math.ceil(monthlyPaymentDiff));
                totalPayments += monthlyPaymentDiff;
            }
            System.out.println("\nTotal Overpayment = " + (int) Math.ceil(totalPayments - initialAmount));
        } else if (loanType.equals("annuity")) {
            if (paymentStr == null) {
            } else {
            }
        } else {
            System.out.println("Invalid loan type specified.");
        }
    }
}