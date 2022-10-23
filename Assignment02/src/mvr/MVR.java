package mvr;

import mvr.Records.SearchJFrame;
import mvr.Utility.MVRUtility;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import mvr.Accident.Accident;
import mvr.Accident.AccidentManagementJFrame;
import mvr.Framework.ExitMenu;
import mvr.Interface.FramePage;
import mvr.Owner.AddOwnerJFrame;
import mvr.Owner.Owner;
import mvr.Vehicle.AddVehicleJFrame;
import mvr.Vehicle.Vehicle;

/**
 * @author Mark Windler (12143085)
 * Date: 22/09/2020
 * Purpose: The main MVR JFrame opened upon initialization of the program
 */
public class MVR extends JFrame implements ActionListener, FramePage {

    // Owner and Vehicle variables
    private Object selectedEntry = null;
    
    // Panels
    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 1));
    
    // Top Panel
    private JLabel titleLabel = new JLabel("Motor Vehicle Registration");
    
    // Middle Panel
    private JTextArea textArea = new JTextArea(10, 50);
    private JScrollPane scrollPane = new JScrollPane(textArea);
    
    // Bottom Panel
    private JButton addOwner = new JButton("Add Owner");
    private JButton addVehicle = new JButton("Add Vehicle");
    private JButton ownerAndVehicleRecords = new JButton("Owner & Vehicle Records");
    private JButton accidentManagement = new JButton("Accident Management");
    
    /**
     *      Constructor
     */
    public MVR()
    {
        // Call the super constructor to name the JFrame
        super("Queensland Road and Transport Authority");
        
        // Exit Menu
        this.setJMenuBar(new ExitMenu(true));
        
        // Top Panel
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        topPanel.add(titleLabel);
        this.add(topPanel, BorderLayout.NORTH);
        
        // Middle Panel
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.append("Welcome to,\n\n"
                + "QLD RTA's Motor Vehicle & Registration program.\n\n"
                + "This program is used for recording and updating\n"
                + "road user, vehicle, and accident information.\n\n"
                + "Records currently in the system:\n\n"
                + "Owners [" + Owner.getOwners().size() + "]  "
                + "Vehicles [" + Vehicle.getVehicles().size() + "]  "
                + "Accidents [" + Accident.getAccidents().size() + "]");
        middlePanel.add(scrollPane);
        this.add(middlePanel, BorderLayout.CENTER);
        
        // Bottom Panel
        bottomPanel.setPreferredSize(new Dimension(200, 80));
        
        JPanel addOwnerVehiclePanel = new JPanel();
        addOwner.addActionListener(this);
        addVehicle.addActionListener(this);
        addOwnerVehiclePanel.add(addOwner);
        addOwnerVehiclePanel.add(addVehicle);
        bottomPanel.add(addOwnerVehiclePanel);
        
        JPanel searchAndAccidentsPanel = new JPanel();
        ownerAndVehicleRecords.addActionListener(this);
        accidentManagement.addActionListener(this);
        searchAndAccidentsPanel.add(ownerAndVehicleRecords);
        searchAndAccidentsPanel.add(accidentManagement);
        bottomPanel.add(searchAndAccidentsPanel);
        
        this.add(bottomPanel, BorderLayout.SOUTH);
        
        // Window close override
        this.addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                MVRUtility.exitProgram(true);
            }
        });
    }
    
    /**
     *      actionPerformed method
     *      @param e The action event
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String pressed = e.getActionCommand();
        
        if (pressed.equals("Add Owner"))
        {
            AddOwnerJFrame addOwnerJFrame = new AddOwnerJFrame(this);
            this.dispose();
        }
        
        if (pressed.equals("Add Vehicle"))
        {
            if (Owner.getOwners().isEmpty())
            {
                MVRUtility.displayError("Plase create an owner record first.\n"
                        + "Owner records are required to create a vehicle record.");
                return;
            }
            
            AddVehicleJFrame addVehicleJFrame = new AddVehicleJFrame(this);
            this.dispose();
        }
        
        if (pressed.equals("Owner & Vehicle Records"))
        {
            SearchJFrame searchJFrame = new SearchJFrame(this);
            this.dispose();
        }
        
        if (pressed.equals("Accident Management"))
        {
            AccidentManagementJFrame accidentManagementJFrame = new AccidentManagementJFrame(this);
            this.dispose();
        }
    }
    
    /**
     *      initialize method
     *      @param previousPage The previous JFrame
     */
    @Override
    public void initialize(JFrame previousPage) 
    {
        Application.initializeMainFrame(previousPage);
    }

    /**
     *      clear method
     */
    @Override
    public void clear() 
    {
        // Not required for this frame
    }
    
}
