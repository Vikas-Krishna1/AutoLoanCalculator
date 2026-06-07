package src.models;
public class LoanApplication
{
    private int applicationId;

    private Applicant applicant;
    private Vehicle vehicle;
    private AutoLoan loan;

    public LoanApplication(
            int applicationId,
            Applicant applicant,
            Vehicle vehicle,
            AutoLoan loan)
    {
        this.applicationId = applicationId;
        this.applicant = applicant;
        this.vehicle = vehicle;
        this.loan = loan;
    }

    // getters
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

     @Override
    public String toString() {
        return "Loan Application ID: " + applicationId +
                "\n\nApplicant Information:\n" + applicant +
                "\n\nVehicle Information:\n" + vehicle +
                "\n\nLoan Details:\n" + loan;
    }   
}