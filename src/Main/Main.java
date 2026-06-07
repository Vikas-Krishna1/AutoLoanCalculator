package src.Main;
import javax.swing.SwingUtilities;
import src.db.DatabaseManager;
import src.ui.applicant.LoanForm;
public class Main
{
    public static void main(String[] args)
    {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.printEnvVariables();
        SwingUtilities.invokeLater(() ->
        {
            new LoanForm();
        });
    }
}