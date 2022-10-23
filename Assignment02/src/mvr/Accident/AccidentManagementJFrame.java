package mvr.Accident;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import mvr.Application;
import mvr.Exception.InvalidArgumentException;
import mvr.Framework.BackButton;
import mvr.Framework.ClearButton;
import mvr.Framework.ExitMenu;
import mvr.Interface.FramePage;
import mvr.Utility.MVRUtility;
import mvr.Vehicle.Vehicle;

/**
 * @author Mark Windler (12143085)
 * Date: 23/09/2020
 * Purpose: The JFrame for when a user wants to view the accident management screen
 */
public class AccidentManagementJFrame extends JFrame implements ActionListener, FramePage {

    // Panels
    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 4));
    private JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 10));
    
    // Top Panel
    private JLabel titleLabel = new JLabel("Accident Management                 ");
    
    // Middle Panel
    private JPanel accidentSearchPanel = new JPanel();
    private JLabel accidentVehicleLabel = new JLabel("Vehicle: ");
    private String[] vehicles = {"Please select..."};
    private JComboBox vehicleAccidents = new JComboBox(vehicles);
    private JLabel accidentDateLabel = new JLabel("Date: ");
    private JTextField accidentDate = new JTextField(10);
    private JPanel showAccidentsPanel = new JPanel();
    private JButton showAccidents = new JButton("Show Accidents");
    
    private JPanel accidentTextAreaPanel = new JPanel();
    private JTextArea textArea = new JTextArea(12, 102);
    private JScrollPane scrollPane = new JScrollPane(textArea);
    
    // Bottom Panel
    private JButton createRecord = new JButton("Create Record");
    private JButton deleteRecord = new JButton("Delete Record");
    
    /**
     *      Constructor
     *      @param previousJFrame The previous JFrame
     */
    public AccidentManagementJFrame(JFrame previousJFrame)
    {
        super("Motor Vehicle Registration - Accident Management");
        
        // Initialization
        initialize(previousJFrame);
        
        // Exit Menu
        this.setJMenuBar(new ExitMenu(true));
        
        // Top Panel
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        topPanel.add(titleLabel);
        topPanel.add(new BackButton(Application.getMainFrame(), this));
        this.add(topPanel, BorderLayout.NORTH);
        
        // Middle Panel
        accidentSearchPanel.add(accidentVehicleLabel);
        
        if (!Vehicle.getVehicles().isEmpty())
        {
            String[] vehicleRecords = new String[Vehicle.getVehicles().size() + 1];
        
            vehicleRecords[0] = "Please select...";
            
            for (int x = 1; x < vehicleRecords.length; ++x)
            {
                vehicleRecords[x] = Vehicle.getVehicles().get(x - 1).getPlateNumber();
            }
        
            vehicleAccidents = new JComboBox(vehicleRecords);
        }
        
        accidentSearchPanel.add(vehicleAccidents);
        accidentSearchPanel.add(accidentDateLabel);
        accidentSearchPanel.add(accidentDate);
        showAccidents.addActionListener(this);
        accidentSearchPanel.add(showAccidents);
        middlePanel.add(accidentSearchPanel);
        
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        accidentTextAreaPanel.add(scrollPane);
        middlePanel.add(accidentTextAreaPanel);
        this.add(middlePanel, BorderLayout.CENTER);
        
        // Bottom Panel
        createRecord.addActionListener(this);
        bottomPanel.add(createRecord);
        deleteRecord.addActionListener(this);
        bottomPanel.add(deleteRecord);
        bottomPanel.add(new ClearButton(this));
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
        
        if (pressed.equals("Show Accidents"))
        {
            try
            {
                showAccidents();
            }
            catch (InvalidArgumentException exception)
            {
                exception.displayError();
            }
        }   
        
        if (pressed.equals("Create Record"))
            createRecord();
        
        if (pressed.equals("Delete Record"))
        {
            try
            {
                deleteRecord();
            }
            catch (InvalidArgumentException exception)
            {
                exception.displayError();
            }
        }
    }

    /**
     *      initialize method
     *      @param previousPage The previous JFrame
     */
    @Override
    public void initialize(JFrame previousPage) 
    {
        this.setBounds(previousPage.getLocation().x, previousPage.getLocation().y, 800, 400);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    /**
     *      clear method
     */
    @Override
    public void clear() 
    {
        textArea.setText("");
        accidentDate.setText("");
        vehicleAccidents.setSelectedIndex(0);
    }
    
    /**
     *      printAccidentsTitle
     */
    private void printAccidentTitle()
    {
        clear();
        textArea.append(String.format("%-15s%-20s%-20s%-25s%-20s",
                "ID", "Location", "Date", "Vehicles Involved", "Comments"));
        textArea.append("\n-----------------------------------------------------"
                + "-------------------------------------------------------------"
                + "-------------------------------------------------------------\n");
    }
    
    /**
     *      printAccident method
     *      @param accident The accident to print
     */
    private void printAccident(Accident accident)
    {
        textArea.append(accident.toString() + "\n");
    }
    
    /**
     *      showAccidents method
     */
    private void showAccidents() throws InvalidArgumentException
    {
        String date = accidentDate.getText();
        
        if (vehicleAccidents.getSelectedIndex() == 0 && (date.isEmpty() || date.isBlank()))
            throw new InvalidArgumentException("Please select a vehicle or type a date to search.");
        
        Vehicle vehicleSelected = null;
        
        if (vehicleAccidents.getSelectedIndex() > 0)
            vehicleSelected = Vehicle.getVehicle(vehicleAccidents.getSelectedItem().toString());
        
        ArrayList<Accident> matchedAccidents = new ArrayList();
        String matchString = "";
        
        if (vehicleSelected != null && !(date.isEmpty() || date.isBlank()))
        {
            if (!MVRUtility.isDate(date))
                throw new InvalidArgumentException("Please enter a correct date using the format: DD/MM/YYYY");
            
            for (Accident a : Accident.getAccidentsForVehicle(vehicleSelected.getPlateNumber()))
            {
                if (a.getDate().equalsIgnoreCase(date))
                    matchedAccidents.add(a);
            }
            
            matchString = "Vehicle [" + vehicleSelected.getPlateNumber() + "] and Date [" + date + "]";
        }
        else if (vehicleSelected != null && (date.isEmpty() || date.isBlank()))
        {
            matchedAccidents = Accident.getAccidentsForVehicle(vehicleSelected.getPlateNumber());
            matchString = "Vehicle [" + vehicleSelected.getPlateNumber() + "]";
        }
        else if (vehicleSelected == null && !(date.isEmpty() || date.isBlank()))
        {
            if (!MVRUtility.isDate(date))
                throw new InvalidArgumentException("Please enter a correct date using the format: DD/MM/YYYY");
            
            matchedAccidents = Accident.getAccidentsForDate(date, Accident.getAccidents());
            matchString = "Date [" + date + "]";
        }
        
            if (matchedAccidents.isEmpty())
            {
                printAccidentTitle();
                textArea.append("No accidents found matching: " + matchString);
            }
            else
            {
                printAccidentTitle();
                for (Accident a : matchedAccidents)
                    printAccident(a);
            }
        
    }
    
    /**
     *      createRecord method
     */
    private void createRecord()
    {
        
        if (Vehicle.getVehicles().isEmpty())
        {
            MVRUtility.displayError("Adding a new accident requires a vehicle record.\n"
                    + "There are currently no vehicles registered in the system.");
            return;
        }
        
        AddAccidentJFrame addAccident = new AddAccidentJFrame(this, this);
        clear();
        this.dispose();
    }

    /**
     *      deleteRecord method
     *      @throws InvalidArgumentException
     */
    private void deleteRecord() throws InvalidArgumentException 
    {
        String accidentRecordID = MVRUtility.displayInput("Please enter the accident ID of the record you would like to delete.");
        
        if (accidentRecordID == null)
            return;
            
        if (accidentRecordID.isEmpty() || accidentRecordID.isBlank())
            throw new InvalidArgumentException("The accident record ID must be numeric and 7-digits long.");
        
        if (!MVRUtility.isAccidentID(accidentRecordID))
            throw new InvalidArgumentException("The accident record ID must be numeric and 7-digits long.");
        
        if (!Accident.accidentExists(Integer.parseInt(accidentRecordID)))
            throw new InvalidArgumentException("No accident record exists for: " + accidentRecordID);
        
        Accident accidentToRemove = Accident.getAccident(Integer.parseInt(accidentRecordID));
        Application.getFileManager().updateRecord(accidentToRemove, true);
    }
    
}
