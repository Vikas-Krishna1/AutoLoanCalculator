package src.models;
//LoanApplication Class==================
//Represents a loan application with associated 
// applicant, vehicle, and loan details
//======================================
public class LoanApplication
{
    // instance variables
    private int applicationId;
    private String status;
    private Applicant applicant;
    private Vehicle vehicle;
    private AutoLoan loan;
    
    // constructor
    public LoanApplication(
        String status,
            int applicationId,
            Applicant applicant,
            Vehicle vehicle,
            AutoLoan loan)
    {
        this.status = status;
        this.applicationId = applicationId;
        this.applicant = applicant;
        this.vehicle = vehicle;
        this.loan = loan;
    }

    // getters
    public String getStatus() {
        return status;
    }
    public int getApplicationId()
    {
        return applicationId;
    }

    public Applicant getApplicant()
    {
        return applicant;
    }

    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public AutoLoan getLoan()
    {
        return loan;
    }
        // setters
    public void setApplicationId(int applicationId)
    {
        this.applicationId = applicationId;
    }

    public void setApplicant(Applicant applicant)
    {
        this.applicant = applicant;
    }

    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    public void setLoan(AutoLoan loan)
    {
        this.loan = loan;
    }
    public void setStatus(String status) {
        this.status = status;
    }   

     @Override
    public String toString() {
        return "Loan Application ID: " + applicationId +
                "\n\nApplicant Information:\n" + applicant +
                "\n\nVehicle Information:\n" + vehicle +
                "\n\nLoan Details:\n" + loan;
    }   
}