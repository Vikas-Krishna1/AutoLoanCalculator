package src.ui.applicant;
import javax.swing.JFrame;
public class ApplicantView extends JFrame
{
    public ApplicantView()
    {
        setTitle("Applicant Portal");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new LoanForm());
    }
}
