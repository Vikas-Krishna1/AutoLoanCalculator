package src.ui.applicant;
import javax.swing .*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import src.db.DatabaseManager;
import src.models.Applicant;
import src.models.AutoLoan;
import src.models.LoanApplication;
import src.models.Vehicle;

public class ViewForm extends JFrame {
    public ViewForm(int applicationId)
{
    DatabaseManager db = new DatabaseManager();

    LoanApplication application =
            db.getLoanApplicationById(applicationId);

    if(application == null)
    {
        JOptionPane.showMessageDialog(
                this,
                "Application not found.");

        dispose();
        return;
    }

    setTitle("Application Details");
    setSize(850,850);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    Applicant applicant =
            application.getApplicant();

    Vehicle vehicle =
            application.getVehicle();

    AutoLoan loan =
            application.getLoan();

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(
            new BoxLayout(
                    mainPanel,
                    BoxLayout.Y_AXIS));

    // ==========================
    // Applicant Section
    // ==========================

    JPanel applicantPanel =
            new JPanel(
                    new GridLayout(7,2,5,5));

    applicantPanel.setBorder(
            BorderFactory.createTitledBorder(
                    "Applicant Information"));

    JTextField fullNameField =
            new JTextField(
                    applicant.getFullName());

    JTextField emailField =
            new JTextField(
                    applicant.getEmail());

    JTextField phoneField =
            new JTextField(
                    applicant.getPhone());

    JTextField addressField =
            new JTextField(
                    applicant.getAddress());

    JTextField dobField =
            new JTextField(
                    applicant.getDateOfBirth());

    JTextField ssnField =
            new JTextField(
                    applicant.getSSN());

    JTextField employerField =
            new JTextField(
                    applicant.getEmployerName());

    fullNameField.setEditable(false);
    emailField.setEditable(false);
    phoneField.setEditable(false);
    addressField.setEditable(false);
    dobField.setEditable(false);
    ssnField.setEditable(false);
    employerField.setEditable(false);

    applicantPanel.add(new JLabel("Full Name"));
    applicantPanel.add(fullNameField);

    applicantPanel.add(new JLabel("Email"));
    applicantPanel.add(emailField);

    applicantPanel.add(new JLabel("Phone"));
    applicantPanel.add(phoneField);

    applicantPanel.add(new JLabel("Address"));
    applicantPanel.add(addressField);

    applicantPanel.add(new JLabel("Date Of Birth"));
    applicantPanel.add(dobField);

    applicantPanel.add(new JLabel("SSN"));
    applicantPanel.add(ssnField);

    applicantPanel.add(new JLabel("Employer"));
    applicantPanel.add(employerField);

    // ==========================
    // Vehicle Section
    // ==========================

    JPanel vehiclePanel =
            new JPanel(
                    new GridLayout(3,2,5,5));

    vehiclePanel.setBorder(
            BorderFactory.createTitledBorder(
                    "Vehicle Information"));

    JTextField makeField =
            new JTextField(
                    vehicle.getMake());

    JTextField modelField =
            new JTextField(
                    vehicle.getModel());

    JTextField yearField =
            new JTextField(
                    String.valueOf(
                            vehicle.getYear()));

    makeField.setEditable(false);
    modelField.setEditable(false);
    yearField.setEditable(false);

    vehiclePanel.add(new JLabel("Make"));
    vehiclePanel.add(makeField);

    vehiclePanel.add(new JLabel("Model"));
    vehiclePanel.add(modelField);

    vehiclePanel.add(new JLabel("Year"));
    vehiclePanel.add(yearField);

    // ==========================
    // Loan Section
    // ==========================

    JPanel loanPanel =
            new JPanel(
                    new GridLayout(7,2,5,5));

    loanPanel.setBorder(
            BorderFactory.createTitledBorder(
                    "Loan Information"));

    JTextField autoPriceField =
            new JTextField(
                    String.valueOf(
                            loan.getAutoPrice()));

    JTextField downPaymentField =
            new JTextField(
                    String.valueOf(
                            loan.getDownPayment()));

    JTextField loanTermField =
            new JTextField(
                    String.valueOf(
                            loan.getLoanTerm()));

    JTextField interestRateField =
            new JTextField(
                    String.valueOf(
                            loan.getInterestRate()));

    JTextField salesTaxField =
            new JTextField(
                    String.valueOf(
                            loan.getSalesTax()));

    JTextField feesField =
            new JTextField(
                    String.valueOf(
                            loan.getFees()));

    JTextField cashIncentiveField =
            new JTextField(
                    String.valueOf(
                            loan.getCashIncentive()));

    autoPriceField.setEditable(false);
    downPaymentField.setEditable(false);
    loanTermField.setEditable(false);
    interestRateField.setEditable(false);
    salesTaxField.setEditable(false);
    feesField.setEditable(false);
    cashIncentiveField.setEditable(false);

    loanPanel.add(new JLabel("Auto Price"));
    loanPanel.add(autoPriceField);

    loanPanel.add(new JLabel("Down Payment"));
    loanPanel.add(downPaymentField);

    loanPanel.add(new JLabel("Loan Term"));
    loanPanel.add(loanTermField);

    loanPanel.add(new JLabel("Interest Rate"));
    loanPanel.add(interestRateField);

    loanPanel.add(new JLabel("Sales Tax"));
    loanPanel.add(salesTaxField);

    loanPanel.add(new JLabel("Fees"));
    loanPanel.add(feesField);

    loanPanel.add(new JLabel("Cash Incentive"));
    loanPanel.add(cashIncentiveField);

    JButton closeButton =
            new JButton("Close");

    closeButton.addActionListener(
            e -> dispose());

    JPanel buttonPanel =
            new JPanel();

    buttonPanel.add(closeButton);

    mainPanel.add(applicantPanel);
    mainPanel.add(vehiclePanel);
    mainPanel.add(loanPanel);
    mainPanel.add(buttonPanel);

    add(new JScrollPane(mainPanel));

    setVisible(true);
}
}