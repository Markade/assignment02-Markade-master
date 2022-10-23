package mvr.Accident;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
 * Purpose: The JFrame for when the user wants to add an accident record
 */
public class AddAccidentJFrame extends JFrame implements ActionListener, FramePage {

    // Prvious Frame Page
    private FramePage previous;
    
    // Panels
    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 10));
    private JPanel bottomPanel = new JPanel();
    
    // Top Panel
    private JLabel titleLabel = new JLabel("Add Accident Record                 ");
    
    // Middle Panel
    private JPanel accidentIDPanel = new JPanel();
    private JLabel accidentIDLabel = new JLabel("Accident ID:");
    private JTextField accidentID = new JTextField(14);
    private JPanel locationPanel = new JPanel();
    private JLabel locationLabel = new JLabel("Location:      ");
    private JTextField location = new JTextField(14);
    private JPanel datePanel = new JPanel();
    private JLabel dateLabel = new JLabel("Date:             ");
    private JTextField date = new JTextField(14);
    private JPanel commentsPanel = new JPanel();
    private JLabel commentsLabel = new JLabel("Comments: ");
    private JTextArea commentsTextArea = new JTextArea(10, 35);
    private JScrollPane commentsScrollPane = new JScrollPane(commentsTextArea);
    
    // Bottom Panel
    private JPanel vehiclePanel = new JPanel();
    private JLabel selectVehicleLabel = new JLabel("Select vehicle:");
    private String[] vehicles = {"Please select..."};
    private JComboBox selectVehicle = new JComboBox(vehicles);
    private JButton addVehicle = new JButton("Add Vehicle");
    private JLabel listOfVehiclesLabel = new JLabel("List of vehicles:");
    private JTextArea textArea = new JTextArea(3, 45);
    private JScrollPane scrollPane = new JScrollPane(textArea);
    private JButton addAccident = new JButton("Add Accident");
    
    /**
     *      Constructor
     *      @param previousJFrame The previous JFrame
     *      @param previousPage The previous application page
     */
    public AddAccidentJFrame(JFrame previousJFrame, FramePage previousPage)
    {
        super("Motor Vehicle Registration - Add Accident");
        
        // Previous Frame Page
        previous = previousPage;
        
        // Initialization
        initialize(previousJFrame);
        
        // Exit Menu
        this.setJMenuBar(new ExitMenu(true));
        
        // Top Panel
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        topPanel.add(titleLabel);
        topPanel.add(new BackButton(previousPage, this));
        this.add(topPanel, BorderLayout.NORTH);
        
        // Middle panel
        accidentIDPanel.add(accidentIDLabel);
        accidentIDPanel.add(accidentID);
        middlePanel.add(accidentIDPanel);
        
        locationPanel.add(locationLabel);
        locationPanel.add(location);
        middlePanel.add(locationPanel);
        
        datePanel.add(dateLabel);
        datePanel.add(date);
        middlePanel.add(datePanel);
        
        commentsPanel.add(commentsLabel);
        commentsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        commentsTextArea.setEditable(true);
        commentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        commentsPanel.add(commentsScrollPane);
        middlePanel.add(commentsPanel);
        this.add(middlePanel, BorderLayout.CENTER);
        
        // Bottom Panel
        bottomPanel.setPreferredSize(new Dimension(400, 160));
        
        if (!Vehicle.getVehicles().isEmpty())
        {
            String[] vehicleRecords = new String[Vehicle.getVehicles().size() + 1];
        
            vehicleRecords[0] = "Please select...";
            
            for (int x = 1; x < vehicleRecords.length; ++x)
            {
                vehicleRecords[x] = Vehicle.getVehicles().get(x - 1).getPlateNumber();
            }
        
            selectVehicle = new JComboBox(vehicleRecords);
        }
        
        vehiclePanel.add(selectVehicleLabel);
        vehiclePanel.add(selectVehicle);
        addVehicle.addActionListener(this);
        vehiclePanel.add(addVehicle);
        bottomPanel.add(vehiclePanel);
        
        bottomPanel.add(listOfVehiclesLabel);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bottomPanel.add(scrollPane);
        addAccident.addActionListener(this);
        bottomPanel.add(addAccident);
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
        
        if (pressed.equals("Add Vehicle"))
        {
            try
            {
                addVehicle();
            }
            catch (InvalidArgumentException exception)
            {
                exception.displayError();
            }
        }
        
        if (pressed.equals("Add Accident"))
        {
            try
            {
                addAccident();
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
        this.setBounds(previousPage.getLocation().x, previousPage.getLocation().y, 400, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     *      clear method
     */
    @Override
    public void clear() 
    {
        
    }
    
    /**
     *      addVehicle method
     *      @throws InvalidArgumentException 
     */
    private void addVehicle() throws InvalidArgumentException
    {
         if (selectVehicle.getSelectedIndex() == 0)
             throw new InvalidArgumentException("Please select a vehicle to add.");
         
         String plateSelected = selectVehicle.getSelectedItem().toString();
         
         if (vehicleAlreadyAdded(plateSelected))
             throw new InvalidArgumentException("That vehicle has already been added.");
         
         if (textArea.getText().isEmpty() || textArea.getText().isBlank())
         {
             textArea.append(plateSelected);
         }
         else 
         {
             textArea.append(", " + plateSelected);
         }
         
    }
    
    /**
     *      addAccident method
     *      @throws InvalidArgumentException 
     */
    public void addAccident() throws InvalidArgumentException
    {
        String accidentIDString = accidentID.getText();
        
        if ((accidentIDString.isEmpty() || accidentIDString.isBlank()) || !MVRUtility.isAccidentID(accidentIDString))
        {
            throw new InvalidArgumentException("Accident ID must be a 7-digit number.");
        }
        else
        {
            if (Accident.accidentExists(Integer.parseInt(accidentIDString)))
                throw new InvalidArgumentException("An accident with the id [" 
                        + accidentIDString + "] already exists.");
        }
        
        String locationString = location.getText();
        
        if ((locationString.isEmpty() || locationString.isBlank()) || !MVRUtility.isAlphaNumeric(locationString))
            throw new InvalidArgumentException("Location cannot be blank and must be alpha-numeric.");
        
        String dateString = date.getText();
        
        if ((dateString.isEmpty() || dateString.isBlank()) || !MVRUtility.isDate(dateString))
            throw new InvalidArgumentException("Date must be in the format of: DD/MM/YYYY");
        
        String comments = commentsTextArea.getText();
        
        if ((comments.isEmpty() || comments.isBlank()) || !comments.contains("."))
            throw new InvalidArgumentException("Comments cannot be blank and must be alpha-numeric.\n"
                    + "Please use fullstops (.) to end sentences.");
        
        String vehicleString = textArea.getText();
        
        if ((vehicleString.isEmpty() || vehicleString.isBlank()) || vehicleString.split(" ").length == 0)
            throw new InvalidArgumentException("Please select at least 1 vehicle for an accident record.");
        
        String[] commentsString = comments.replace("\n", " ").split("\\. ");
        
        ArrayList<String> commentsWritten = new ArrayList();
        
        for (String s : commentsString)
            commentsWritten.add(s.trim());
        
        String[] vehiclesString = vehicleString.split(", ");
        
        ArrayList<String> vehiclesInvolved = new ArrayList();
        
        for (String s : vehiclesString)
            vehiclesInvolved.add(s.trim());
        
        Accident accident = new Accident(Integer.parseInt(accidentIDString),
        locationString, dateString, vehiclesInvolved, commentsWritten);
        
        Application.getFileManager().saveAccident(accident);
        previous.clear();
        previous.initialize(this);
        this.dispose();
    }
    
    /**
     *      vehicleAlreadyAdded method
     *      @param plate The plate to search for
     *      @return Whether the vehicle has already been added to the accident.
     */
    private boolean vehicleAlreadyAdded(String plate)
    {
        return textArea.getText().contains(plate);
    }
}
