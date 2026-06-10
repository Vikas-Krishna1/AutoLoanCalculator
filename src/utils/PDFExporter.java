package utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import models.LoanApplication;

import java.io.FileOutputStream;

public class PDFExporter
{
    public static void exportApplication(
            LoanApplication application,
            String fileName)
    {
        try
        {
            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    new FileOutputStream(fileName));

            document.open();

            document.add(
                    new Paragraph(
                            "AUTO LOAN APPLICATION REPORT"));

            document.add(
                    new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "Application ID: "
                                    + application.getApplicationId()));

            document.add(
                    new Paragraph(
                            "Status: "
                                    + application.getStatus()));

            document.add(
                    new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "Applicant Information"));

            document.add(
                    new Paragraph(
                            "Name: "
                                    + application
                                    .getApplicant()
                                    .getFullName()));

            document.add(
                    new Paragraph(
                            "Email: "
                                    + application
                                    .getApplicant()
                                    .getEmail()));

            document.add(
                    new Paragraph(
                            "Phone: "
                                    + application
                                    .getApplicant()
                                    .getPhone()));

            document.add(
                    new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "Vehicle Information"));

            document.add(
                    new Paragraph(
                            application
                                    .getVehicle()
                                    .getYear()
                                    + " "
                                    + application
                                    .getVehicle()
                                    .getMake()
                                    + " "
                                    + application
                                    .getVehicle()
                                    .getModel()));

            document.add(
                    new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "Loan Information"));

            document.add(
                    new Paragraph(
                            "Auto Price: $"
                                    + application
                                    .getLoan()
                                    .getAutoPrice()));

            document.add(
                    new Paragraph(
                            "Down Payment: $"
                                    + application
                                    .getLoan()
                                    .getDownPayment()));

            document.add(
                    new Paragraph(
                            "Interest Rate: "
                                    + application
                                    .getLoan()
                                    .getInterestRate()
                                    + "%"));

            document.add(
                    new Paragraph(
                            "Loan Term: "
                                    + application
                                    .getLoan()
                                    .getLoanTerm()
                                    + " months"));

            document.add(
                    new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "Officer Notes"));

            document.add(
                    new Paragraph(
                            application.getReviewNotes() == null
                                    ? "No notes available"
                                    : application.getReviewNotes()));

            document.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
