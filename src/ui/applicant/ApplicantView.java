package src.ui.applicant;
import javax.swing.JFrame;
public class ApplicantView extends JFrame
{
    private int user_id;
    public ApplicantView(int user_id)
    {
        this.user_id = user_id;
        setTitle("Applicant Portal");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new LoanForm(user_id));
    }
}
