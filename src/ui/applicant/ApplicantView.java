package ui.applicant;
import javax.swing.JFrame;
//APPLICANT VIEW
//Load the loan form
public class ApplicantView extends JFrame
{
    //Private instance variables
    private int user_id;
    //Constructor
    public ApplicantView(int user_id)
    {
        this.user_id = user_id;
        setTitle("Applicant Portal");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Add the loan form
        add(new LoanForm(user_id));
    }
}
