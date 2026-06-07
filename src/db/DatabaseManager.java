package src.db;
import src.models.Applicant;
import src.models.Vehicle;
import src.models.AutoLoan;
import src.models.LoanApplication;
import src.services.LoanCalculator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// ==========================
// Database Manager
// ==========================

public class DatabaseManager
{
//DB connection details
//URL format: jdbc:mysql://hostname:port/database_name
//DB credentials-USER

private static final String URL =
        System.getenv("DB_URL");


private static final String USER =
        System.getenv("DB_USER");

private static final String PASSWORD =
        System.getenv("DB_PASSWORD");
public void printEnvVariables()
{
    System.out.println("URL = " + URL);
    System.out.println("USER = " + USER);
    System.out.println("PASSWORD = " + PASSWORD);
}


private Connection connect() throws SQLException
{
    return DriverManager.getConnection(URL, USER, PASSWORD);
}

// ==========================
// Save Applicant
// ==========================
public int saveApplicant(Applicant applicant) throws SQLException
{
    String sql =
            "INSERT INTO applicant " +
            "(full_name, email, phone, address, date_of_birth, ssn, employer_name) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = connect();
         PreparedStatement stmt =
                 conn.prepareStatement(
                         sql,
                         Statement.RETURN_GENERATED_KEYS))
    {
        stmt.setString(1, applicant.getFullName());
        stmt.setString(2, applicant.getEmail());
        stmt.setString(3, applicant.getPhone());
        stmt.setString(4, applicant.getAddress());
        stmt.setString(5, applicant.getDateOfBirth());
        stmt.setString(6, applicant.getSSN());
        stmt.setString(7, applicant.getEmployerName());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();

        if (rs.next())
        {
            return rs.getInt(1);
        }
    }

    return -1;
}

// ==========================
// Save Vehicle
// ==========================
public int saveVehicle(Vehicle vehicle) throws SQLException
{
    String sql =
            "INSERT INTO vehicle " +
            "(make, model, year) " +
            "VALUES (?, ?, ?)";

    try (Connection conn = connect();
         PreparedStatement stmt =
                 conn.prepareStatement(
                         sql,
                         Statement.RETURN_GENERATED_KEYS))
    {
        stmt.setString(1, vehicle.getMake());
        stmt.setString(2, vehicle.getModel());
        stmt.setInt(3, vehicle.getYear());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();

        if (rs.next())
        {
            return rs.getInt(1);
        }
    }

    return -1;
}

// ==========================
// Save Loan Application
// ==========================
public int saveLoanApplication(
        Applicant applicant,
        Vehicle vehicle,
        AutoLoan loan)
{
    try
    {
        int applicantId = saveApplicant(applicant);

        int vehicleId = saveVehicle(vehicle);

        double loanAmount =
                LoanCalculator.calculateLoanAmount(loan);

        double monthlyPayment =
                LoanCalculator.calculateMonthlyPayment(loan);

        String sql =
                "INSERT INTO loan_application " +
                "(applicant_id, vehicle_id, auto_price, down_payment, " +
                "loan_term, interest_rate, sales_tax, fees, " +
                "cash_incentive, loan_amount, monthly_payment) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt =
                     conn.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setInt(1, applicantId);
            stmt.setInt(2, vehicleId);

            stmt.setDouble(3, loan.getAutoPrice());
            stmt.setDouble(4, loan.getDownPayment());
            stmt.setInt(5, loan.getLoanTerm());
            stmt.setDouble(6, loan.getInterestRate());
            stmt.setDouble(7, loan.getSalesTax());
            stmt.setDouble(8, loan.getFees());
            stmt.setDouble(9, loan.getCashIncentive());

            stmt.setDouble(10, loanAmount);
            stmt.setDouble(11, monthlyPayment);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next())
            {
                return rs.getInt(1);
            }
        }
    }
    catch (SQLException e)
    {
        e.printStackTrace();
    }

    return -1;
}

// ==========================
// Retrieve Loan Application
// ==========================
public LoanApplication getLoanApplicationById(int applicationId)
{
    String sql =
            "SELECT * " +
            "FROM loan_application " +
            "JOIN applicant " +
            "ON loan_application.applicant_id = applicant.applicant_id " +
            "JOIN vehicle " +
            "ON loan_application.vehicle_id = vehicle.vehicle_id " +
            "WHERE loan_application.application_id = ?";

    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(sql))
    {
        stmt.setInt(1, applicationId);

        ResultSet rs = stmt.executeQuery();

        if (rs.next())
        {
            Applicant applicant =
                    new Applicant(
                            rs.getInt("applicant_id"),
                            rs.getString("full_name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getString("date_of_birth"),
                            rs.getString("ssn"),
                            rs.getString("employer_name")
                    );

            Vehicle vehicle =
                    new Vehicle(
                            rs.getInt("application_id"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year")
                    );

            AutoLoan loan =
                    new AutoLoan(
                            rs.getDouble("auto_price"),
                            rs.getDouble("down_payment"),
                            rs.getInt("loan_term"),
                            rs.getDouble("interest_rate"),
                            rs.getDouble("sales_tax"),
                            rs.getDouble("fees"),
                            rs.getDouble("cash_incentive")
                    );

            return new LoanApplication(
                    applicationId,
                    applicant,
                    vehicle,
                    loan
            );
        }
    }
    catch (SQLException e)
    {
        e.printStackTrace();
    }

    return null;
}


}
