package utils;

import models.LoanApplication;

import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

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

        Font titleFont =
                new Font(
                        Font.HELVETICA,
                        18,
                        Font.BOLD);

        Font sectionFont =
                new Font(
                        Font.HELVETICA,
                        14,
                        Font.BOLD);

        Paragraph title =
                new Paragraph(
                        "AUTO LOAN APPLICATION REPORT",
                        titleFont);

        title.setAlignment(
                Element.ALIGN_CENTER);

        document.add(title);

        document.add(new Paragraph(" "));

        // =====================================
        // SUMMARY TABLE
        // =====================================

        PdfPTable summaryTable =
                new PdfPTable(2);

        summaryTable.setWidthPercentage(100);

        summaryTable.addCell(
                headerCell("Application ID"));

        summaryTable.addCell(
                valueCell(
                        String.valueOf(
                                application.getApplicationId())));

        summaryTable.addCell(
                headerCell("Status"));

        summaryTable.addCell(
                valueCell(
                        application.getStatus()));

        document.add(summaryTable);

        document.add(new Paragraph(" "));

        // =====================================
        // APPLICANT INFORMATION
        // =====================================

        document.add(
                new Paragraph(
                        "Applicant Information",
                        sectionFont));

        PdfPTable applicantTable =
                new PdfPTable(2);

        applicantTable.setWidthPercentage(100);

        applicantTable.addCell(
                headerCell("Name"));

        applicantTable.addCell(
                valueCell(
                        application
                                .getApplicant()
                                .getFullName()));

        applicantTable.addCell(
                headerCell("Email"));

        applicantTable.addCell(
                valueCell(
                        application
                                .getApplicant()
                                .getEmail()));

        applicantTable.addCell(
                headerCell("Phone"));

        applicantTable.addCell(
                valueCell(
                        application
                                .getApplicant()
                                .getPhone()));

        applicantTable.addCell(
                headerCell("Address"));

        applicantTable.addCell(
                valueCell(
                        application
                                .getApplicant()
                                .getAddress()));

        document.add(applicantTable);

        document.add(new Paragraph(" "));

        // =====================================
        // VEHICLE INFORMATION
        // =====================================

        document.add(
                new Paragraph(
                        "Vehicle Information",
                        sectionFont));

        PdfPTable vehicleTable =
                new PdfPTable(2);

        vehicleTable.setWidthPercentage(100);

        vehicleTable.addCell(
                headerCell("Year"));

        vehicleTable.addCell(
                valueCell(
                        String.valueOf(
                                application
                                        .getVehicle()
                                        .getYear())));

        vehicleTable.addCell(
                headerCell("Make"));

        vehicleTable.addCell(
                valueCell(
                        application
                                .getVehicle()
                                .getMake()));

        vehicleTable.addCell(
                headerCell("Model"));

        vehicleTable.addCell(
                valueCell(
                        application
                                .getVehicle()
                                .getModel()));

        document.add(vehicleTable);

        document.add(new Paragraph(" "));

        // =====================================
        // LOAN INFORMATION
        // =====================================

        document.add(
                new Paragraph(
                        "Loan Information",
                        sectionFont));

        PdfPTable loanTable =
                new PdfPTable(2);

        loanTable.setWidthPercentage(100);

        loanTable.addCell(
                headerCell("Auto Price"));

        loanTable.addCell(
                valueCell(
                        "$" +
                        application
                                .getLoan()
                                .getAutoPrice()));

        loanTable.addCell(
                headerCell("Down Payment"));

        loanTable.addCell(
                valueCell(
                        "$" +
                        application
                                .getLoan()
                                .getDownPayment()));

        loanTable.addCell(
                headerCell("Interest Rate"));

        loanTable.addCell(
                valueCell(
                        application
                                .getLoan()
                                .getInterestRate()
                                + "%"));

        loanTable.addCell(
                headerCell("Loan Term"));

        loanTable.addCell(
                valueCell(
                        application
                                .getLoan()
                                .getLoanTerm()
                                + " Months"));

        loanTable.addCell(
                headerCell("Sales Tax"));

        loanTable.addCell(
                valueCell(
                        application
                                .getLoan()
                                .getSalesTax()
                                + "%"));

        loanTable.addCell(
                headerCell("Fees"));

        loanTable.addCell(
                valueCell(
                        "$" +
                        application
                                .getLoan()
                                .getFees()));

        loanTable.addCell(
                headerCell("Cash Incentive"));

        loanTable.addCell(
                valueCell(
                        "$" +
                        application
                                .getLoan()
                                .getCashIncentive()));

        document.add(loanTable);

        document.add(new Paragraph(" "));

        // =====================================
        // OFFICER REVIEW
        // =====================================

        document.add(
                new Paragraph(
                        "Officer Review",
                        sectionFont));

        PdfPTable reviewTable =
                new PdfPTable(2);

        reviewTable.setWidthPercentage(100);

        reviewTable.addCell(
                headerCell("Status"));

        reviewTable.addCell(
                valueCell(
                        application.getStatus()));

        reviewTable.addCell(
                headerCell("Officer Notes"));

        reviewTable.addCell(
                valueCell(
                        application.getReviewNotes() == null
                                ? "No notes available"
                                : application.getReviewNotes()));

        document.add(reviewTable);

        document.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
}

private static PdfPCell headerCell(
        String text)
{
    Font bold =
            new Font(
                    Font.HELVETICA,
                    11,
                    Font.BOLD);

    return new PdfPCell(
            new Phrase(
                    text,
                    bold));
}

private static PdfPCell valueCell(
        String text)
{
    return new PdfPCell(
            new Phrase(text));
}


}
