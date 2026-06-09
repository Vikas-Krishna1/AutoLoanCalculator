package src.ui.officer;
import src.db.DatabaseManager;
import src.models.LoanApplication;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
//LOAN OFFICER APPLICATION QUEUE
//This class represents the loan officer application queue view
//It allows the loan officer to view and manage loan applications   
//and assign loan officers to applications
public class ApplicationQueueView extends JFrame
{
    //Components/Variables
    private JTable applicationTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton;
    private JButton openButton;
    private JComboBox<String> statusFilter;
    private DatabaseManager db;
//Constructor
    public ApplicationQueueView()
    {
        //DatabaseManager db = new DatabaseManager();
        db = new DatabaseManager();

        setTitle("Loan Officer - Application Queue");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //Filters based on status of the loan application
    statusFilter = new JComboBox<>(new String[] { "Pending", "Approved", "Declined" });

     statusFilter.addActionListener(e -> 
        loadApplications()
    );
   

        setLayout(new BorderLayout());

        JLabel title =
                new JLabel(
                        "Loan Application Queue",
                        SwingConstants.CENTER);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22));

        add(title, BorderLayout.NORTH);

        String[] columns =
        {
            "Application ID",
            "Applicant",
            "Vehicle",
            "Loan Amount",
            "Status"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0)
                {
                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column)
                    {
                        return false;
                    }
                };

        applicationTable =
                new JTable(tableModel);

        applicationTable.setRowHeight(25);

        JScrollPane scrollPane =
                new JScrollPane(applicationTable);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel =
                new JPanel();

        refreshButton =
                new JButton("Refresh");

        openButton =
                new JButton("Open Application");

        buttonPanel.add(refreshButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(openButton);
        buttonPanel.add(statusFilter);
        buttonPanel.add(openButton);

        add(buttonPanel, BorderLayout.SOUTH);

        refreshButton.addActionListener(e ->
        {
            loadApplications();
        });

        openButton.addActionListener(e ->
        {
            openSelectedApplication();
        });

        loadApplications();

        setVisible(true);
    }

private void loadApplications()
{
    tableModel.setRowCount(0);

    try
    {
        List<LoanApplication> applications =
                db.getAllApplications();

        String selectedStatus =
                (String) statusFilter.getSelectedItem();

        for(LoanApplication application : applications)
        {
            // Apply filter
            if(selectedStatus != null
                    && !selectedStatus.equalsIgnoreCase("ALL")
                    && !application.getStatus().equalsIgnoreCase(selectedStatus))
            {
                continue;
            }

            String applicantName =
                    application
                            .getApplicant()
                            .getFullName();

            String vehicleName =
                    application
                            .getVehicle()
                            .getYear()
                            + " "
                            + application
                            .getVehicle()
                            .getMake()
                            + " "
                            + application
                            .getVehicle()
                            .getModel();

            tableModel.addRow(
                    new Object[]
                    {
                        application.getApplicationId(),
                        applicantName,
                        vehicleName,
                        String.format(
                                "$%.2f",
                                application
                                        .getLoan()
                                        .getAutoPrice()),
                        application.getStatus()
                    });
        }
    }
    catch(Exception e)
    {
        e.printStackTrace();

        JOptionPane.showMessageDialog(
                this,
                "Unable to load applications.");
    }
}
    private void openSelectedApplication()
    {
        int selectedRow =
                applicationTable.getSelectedRow();

        if(selectedRow == -1)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Select an application first.");

            return;
        }

        int applicationId =
                (Integer)
                        tableModel.getValueAt(
                                selectedRow,
                                0);

         System.out.println(
            "Opening application "
                    + applicationId);

        //Open loan officer view
        new LoanOfficerView(applicationId);
    }
}