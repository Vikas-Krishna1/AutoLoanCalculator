package src.services;

import src.models.AutoLoan;

public class LoanCalculator
{
    // Loan amount after down payment and cash incentive
    public static double calculateLoanAmount(AutoLoan loan)
    {
        double priceAfterIncentive =
                loan.getAutoPrice() - loan.getCashIncentive();

        double loanAmount =
                priceAfterIncentive - loan.getDownPayment();

        return Math.max(0, loanAmount);
    }

    // Monthly payment using standard amortization formula
    public static double calculateMonthlyPayment(AutoLoan loan)
    {
        double loanAmount = calculateLoanAmount(loan);

        double monthlyRate =
                loan.getInterestRate() / 100.0 / 12.0;

        int numberOfPayments =
                loan.getLoanTerm() * 12;

        // If no interest
        if (monthlyRate == 0)
        {
            return loanAmount / numberOfPayments;
        }

        double denominator =
                1 - Math.pow(1 + monthlyRate, -numberOfPayments);

        return loanAmount * monthlyRate / denominator;
    }

    // Total upfront tax + fees (optional helper)
    public static double calculateUpfrontCosts(AutoLoan loan)
    {
        double taxAmount =
                loan.getAutoPrice() * (loan.getSalesTax() / 100.0);

        return taxAmount + loan.getFees();
    }

    // Total cost of loan over time
    public static double calculateTotalPayment(AutoLoan loan)
    {
        double monthly =
                calculateMonthlyPayment(loan);

        int months =
                loan.getLoanTerm() * 12;

        return monthly * months;
    }
}