import javax.swing.SwingUtilities;

import src.ui.LoanForm;
public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            new LoanForm();
        });
    }
}