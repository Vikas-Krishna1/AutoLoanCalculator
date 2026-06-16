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
import ui.login.*;
import utils.passwordUtils;
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
        db.initializeTables();
        //Check if env variables are loaded correctly
        db.printEnvVariables();
        //Opens the view
        openView();
    }
    //Opens the view
    public static void openView()
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginView();
            }
        });
    }
}