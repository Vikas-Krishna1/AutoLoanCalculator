package src.Main;
import javax.swing.SwingUtilities;
import src.db.DatabaseManager;
import src.ui.applicant.ApplicationDashboard;
import src.ui.applicant.LoanForm;
import src.ui.officer.ApplicationQueueView;
import src.ui.officer.LoanOfficerView;
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