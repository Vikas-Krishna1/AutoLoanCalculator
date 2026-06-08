package src.models;
public class LoanApplication
{
    private int applicationId;

    private String status;
    private Applicant applicant;
    private Vehicle vehicle;
    private AutoLoan loan;

    public LoanApplication(

            int applicationId,
            Applicant applicant,
            Vehicle vehicle,
            AutoLoan loan)
    {
        this.status = "Pending";
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