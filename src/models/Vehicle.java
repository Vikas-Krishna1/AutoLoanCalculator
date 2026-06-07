package src.models;
public class Vehicle
{
    private int applicationId;

    private String make;
    private String model;
    private int year;

    public Vehicle(int applicationId, String make,String model,int year)
    {
        this.applicationId = applicationId;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Getters

    public int getApplicationId()
    {
        return applicationId;
    }

    public String getMake()
    {
        return make;
    }

    public String getModel()
    {
        return model;
    }

    public int getYear()
    {
        return year;
    }

    // Setters

    public void setApplicationId(int applicationId)
    {
        this.applicationId = applicationId;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    @Override
    public String toString()
    {
        return "Application ID: " + applicationId +
               "\nMake: " + make +
               "\nModel: " + model +
               "\nYear: " + year;
    }
}