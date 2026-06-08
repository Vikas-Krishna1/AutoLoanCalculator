package src.Main;
import javax.swing.SwingUtilities;
import src.db.DatabaseManager;
import src.ui.applicant.LoanForm;
import src.ui.officer.LoanOfficerView;
import javax.swing.JOptionPane;
import java.sql.*;
public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            String[] options =
            {
                "Applicant",
                "Loan Officer"
            };

            int choice =
                    JOptionPane.showOptionDialog(
                            null,
                            "Select View",
                            "Auto Loan System",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            options,
                            options[0]);

            if(choice == 0)
            {
                new LoanForm();
            }
            else if(choice == 1)
            {
                new LoanOfficerView();
            }
        });
    }
}