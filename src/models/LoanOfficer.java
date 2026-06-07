package src.models;

public class LoanOfficer
{
    private int officerId;
    private String fullName;
    private String email;

    public LoanOfficer(int officerId,
                       String fullName,
                       String email)
    {
        this.officerId = officerId;
        this.fullName = fullName;
        this.email = email;
    }

    public int getOfficerId()
    {
        return officerId;
    }

    public String getFullName()
    {
        return fullName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "LoanOfficer{" +
                "officerId=" + officerId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}