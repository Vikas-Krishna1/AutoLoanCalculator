package src.ui.applicant;

import src.db.DatabaseManager;
import src.models.Applicant;
import src.models.AutoLoan;
import src.models.Vehicle;
import src.services.LoanCalculator;

import javax.swing.*;
import java.awt.*;

public class LoanForm extends JFrame
{
    // Applicant Fields
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField dobField;
    private JTextField ssnField;
    private JTextField employerField;

    // Vehicle Fields
    private JTextField makeField;
    private JTextField modelField;
    private JTextField yearField;

    // Loan Fields
    private JTextField autoPriceField;
    private JTextField downPaymentField;
    private JTextField loanTermField;
    private JTextField interestRateField;
    private JTextField salesTaxField;
    private JTextField feesField;
    private JTextField cashIncentiveField;

    // Results
    private JLabel loanAmountLabel;
    private JLabel monthlyPaymentLabel;

    private Applicant currentApplicant;
    private Vehicle currentVehicle;
    private AutoLoan currentLoan;

    public LoanForm()
    {
        setTitle("Auto Loan Application System");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));

        // ==========================
        // Applicant Information
        // ==========================
        panel.add(new JLabel("Full Name"));
        fullNameField = new JTextField();
        panel.add(fullNameField);

        panel.add(new JLabel("Email"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Phone"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Address"));
        addressField = new JTextField();
        panel.add(addressField);

        panel.add(new JLabel("Date of Birth"));
        dobField = new JTextField();
        panel.add(dobField);

        panel.add(new JLabel("SSN"));
        ssnField = new JTextField();
        panel.add(ssnField);

        panel.add(new JLabel("Employer"));
        employerField = new JTextField();
        panel.add(employerField);

        // ==========================
        // Vehicle Information
        // ==========================
        panel.add(new JLabel("Make"));
        makeField = new JTextField();
        panel.add(makeField);

        panel.add(new JLabel("Model"));
        modelField = new JTextField();
        panel.add(modelField);

        panel.add(new JLabel("Year"));
        yearField = new JTextField();
        panel.add(yearField);

        // ==========================
        // Loan Information
        // ==========================
        panel.add(new JLabel("Auto Price"));
        autoPriceField = new JTextField();
        panel.add(autoPriceField);

        panel.add(new JLabel("Down Payment"));
        downPaymentField = new JTextField();
        panel.add(downPaymentField);

        panel.add(new JLabel("Loan Term (Months)"));
        loanTermField = new JTextField();
        panel.add(loanTermField);

        panel.add(new JLabel("Interest Rate (%)"));
        interestRateField = new JTextField();
        panel.add(interestRateField);

        panel.add(new JLabel("Sales Tax (%)"));
        salesTaxField = new JTextField();
        panel.add(salesTaxField);

        panel.add(new JLabel("Fees"));
        feesField = new JTextField();
        panel.add(feesField);

        panel.add(new JLabel("Cash Incentive"));
        cashIncentiveField = new JTextField();
        panel.add(cashIncentiveField);

        // ==========================
        // Results
        // ==========================
        panel.add(new JLabel("Loan Amount"));
        loanAmountLabel = new JLabel("$0.00");
        panel.add(loanAmountLabel);

        panel.add(new JLabel("Monthly Payment"));
        monthlyPaymentLabel = new JLabel("$0.00");
        panel.add(monthlyPaymentLabel);

        JButton calculateButton =
                new JButton("Calculate");

        JButton saveButton =
                new JButton("Save Application");

        panel.add(calculateButton);
        panel.add(saveButton);

        add(new JScrollPane(panel));

        // ==========================
        // Calculate
        // ==========================
        calculateButton.addActionListener(e ->
        {
            try
            {
                currentApplicant =
                        new Applicant(
                                0,
                                fullNameField.getText(),
                                emailField.getText(),
                                phoneField.getText(),
                                addressField.getText(),
                                dobField.getText(),
                                ssnField.getText(),
                                employerField.getText()
                        );

                currentVehicle =
                        new Vehicle(
                                0,
                                makeField.getText(),
                                modelField.getText(),
                                Integer.parseInt(
                                        yearField.getText())
                        );

                currentLoan =
                        new AutoLoan(
                                Double.parseDouble(
                                        autoPriceField.getText()),
                                Double.parseDouble(
                                        downPaymentField.getText()),
                                Integer.parseInt(
                                        loanTermField.getText()),
                                Double.parseDouble(
                                        interestRateField.getText()),
                                Double.parseDouble(
                                        salesTaxField.getText()),
                                Double.parseDouble(
                                        feesField.getText()),
                                Double.parseDouble(
                                        cashIncentiveField.getText())
                        );

                double loanAmount =
                        LoanCalculator.calculateLoanAmount(
                                currentLoan);

                double monthlyPayment =
                        LoanCalculator.calculateMonthlyPayment(
                                currentLoan);

                loanAmountLabel.setText(
                        String.format("$%.2f", loanAmount));

                monthlyPaymentLabel.setText(
                        String.format("$%.2f", monthlyPayment));
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // ==========================
        // Save
        // ==========================
        saveButton.addActionListener(e ->
        {
            try
            {
                if (currentLoan == null)
                {
                    JOptionPane.showMessageDialog(
                            this,
                            "Calculate first."
                    );
                    return;
                }

                DatabaseManager db =
                        new DatabaseManager();

                int applicationId =
                        db.saveLoanApplication(
                                currentApplicant,
                                currentVehicle,
                                currentLoan
                        );

                JOptionPane.showMessageDialog(
                        this,
                        "Application Saved!\nID: "
                                + applicationId
                
                );

                clearForm();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        setVisible(true);
    }
    private void clearForm()
{
    fullNameField.setText("");
    emailField.setText("");
    phoneField.setText("");
    addressField.setText("");
    dobField.setText("");
    ssnField.setText("");
    employerField.setText("");

    makeField.setText("");
    modelField.setText("");
    yearField.setText("");

    autoPriceField.setText("");
    downPaymentField.setText("");
    loanTermField.setText("");
    interestRateField.setText("");
    salesTaxField.setText("");
    feesField.setText("");
    cashIncentiveField.setText("");

    loanAmountLabel.setText("$0.00");
    monthlyPaymentLabel.setText("$0.00");

    currentApplicant = null;
    currentVehicle = null;
    currentLoan = null;
}
}