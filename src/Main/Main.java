package Main;
import javax.swing.SwingUtilities;
import db.DatabaseManager;
import ui.applicant.ApplicationDashboard;
import ui.officer.loanOfficerDashboard;
import ui.applicant.LoanForm;
import ui.officer.ApplicationQueueView;
import ui.officer.LoanOfficerView;
import javax.swing.JOptionPane;
import java.sql.*;
//MAIN CLASS
//This Class is the entry point of the application
public class Main
{
    //hardcored User ID until Login is implemented
     static int user_id=1;
    public static void main(String[] args)
    {
       // DatabaseManager db = new DatabaseManager();
        DatabaseManager db = new DatabaseManager();
        //Check if env variables are loaded correctly
        db.printEnvVariables();
        //Opens the view
        openView();
    }
    //Opens the view
    public static void openView()
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
                new ApplicationDashboard(user_id);
            }
            else if(choice == 1)
            {
                new ApplicationQueueView();
            }
        });
    }
}