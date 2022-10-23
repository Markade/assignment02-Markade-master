package mvr.Records;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import mvr.Utility.MVRUtility;
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
import javax.swing.JTextField;
import mvr.Application;
import mvr.Exception.InvalidArgumentException;
import mvr.Framework.BackButton;
import mvr.Framework.ClearButton;
import mvr.Framework.ExitMenu;
import mvr.Interface.FramePage;
import mvr.Owner.Owner;
import mvr.Owner.OwnerCorporate;
import mvr.Owner.OwnerPrivate;
import mvr.Vehicle.Vehicle;
import mvr.Vehicle.VehicleHeavy;
import mvr.Vehicle.VehicleLight;

/**
 * @author Mark Windler (12143085)
 * Date: 23/09/2020
 * Purpose: The JFrame shown and used when the user requests to edit an owner record
 */
public class EditRecordJFrame extends JFrame implements ActionListener, FramePage {
    
    private FramePage previous;
    
    // Selected record
    private Object selectedRecord;
    
    // Panels
    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 2));
    private JPanel bottomPanel = new JPanel();
    
    // Top Panel
    private JLabel titleLabel = new JLabel("Record Editor                 ");
    
    // Middle Panel
    
    // Owners
    
    // Panels
    private JPanel ownerTypePanel = new JPanel();
    private JPanel ownerNamePanel = new JPanel();
    private JPanel ownerAddressPanel = new JPanel();
    private JPanel ownerPhoneNumberPanel = new JPanel();
    
    // Private
    private JLabel ownerPrivateDOBLabel = new JLabel("Date of birth: ");
    private JTextField  ownerPrivateDOB = new JTextField(10);
    
    // Corporate
    private JLabel ownerCorporateBusinessNumberLabel = new JLabel("Business number: ");
    private JTextField ownerCorporateBusinessNumber = new JTextField(11);
    
    private JLabel ownerNameLabel = new JLabel("First name: ");
    private JTextField ownerName = new JTextField(10);
    private JLabel ownerLastNameLabel = new JLabel("Last name: ");
    private JTextField ownerLastName = new JTextField(10);
    private JLabel ownerAddressLabel = new JLabel("Address: ");
    private JTextField ownerAddress = new JTextField(30);
    private JLabel ownerPhoneNumberLabel = new JLabel("Phone Number: ");
    private JTextField ownerPhoneNumber = new JTextField(10);
    
    // Vehicles
    
    // Panels
    private JPanel vehicleTypePanel = new JPanel();
    private JPanel vehiclePlatePanel = new JPanel();
    private JPanel vehicleEngineMakePanel = new JPanel();
    private JPanel vehicleModelYearPanel = new JPanel();
    private JPanel vehicleOwnerPanel = new JPanel();
    
    // Heavy
    private JLabel vehicleHeavyLoadCapacityLabel = new JLabel("Load capacity: ");
    private JTextField vehicleHeavyLoadCapacity = new JTextField(5);
    
    // Light
    private JLabel vehicleLightNumberOfSeatsLabel = new JLabel("Number of seats: ");
    private JTextField vehicleLightNumberOfSeats = new JTextField(2);
    
    private JLabel vehiclePlateLabel = new JLabel("Plate number: ");
    private JTextField vehiclePlate = new JTextField(6);
    private JLabel vehicleEngineCapacityLabel = new JLabel("Engine capacity: ");
    private JTextField vehicleEngineCapacity = new JTextField(5);
    private JLabel vehicleMakeLabel = new JLabel("Make: ");
    private JTextField vehicleMake = new JTextField(10);
    private JLabel vehicleModelLabel = new JLabel("Model: ");
    private JTextField vehicleModel = new JTextField(10);
    private JLabel vehicleYearLabel = new JLabel("Year: ");
    private JTextField vehicleYear = new JTextField(4);
    private JLabel vehicleOwnerLabel = new JLabel("Vehicle owner: "); 
    private JComboBox vehicleOwner = new JComboBox();
    
    // Bottom Panel
    private JButton updateRecord = new JButton("Update Record");
    private JButton deleteRecord = new JButton("Delete Record");
    
    /**
     *      Constructor
     *      @param previousJFrame The previous JFrame
     *      @param previousPage The previous application page 
     *      @param selectedRecord The selected record
     */
    public EditRecordJFrame(JFrame previousJFrame, FramePage previousPage,
            Object selectedRecord)
    {
        super("Motor Vehicle Registration - Edit Record");
        
        previous = previousPage;
        
        // Assign the selected owner
        this.selectedRecord = selectedRecord;
        
        // Initilization
        initialize(previousJFrame);
        
        // Exit Menu
        this.setJMenuBar(new ExitMenu(false));
        
        // Top Panel
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        topPanel.add(titleLabel);
        topPanel.add(new BackButton(previousPage, this));
        this.add(topPanel, BorderLayout.NORTH);
        
        // Middle Panel
        addEntryFields();
        this.add(middlePanel, BorderLayout.CENTER);
        
        // Bottom Panel
        updateRecord.addActionListener(this);
        bottomPanel.add(updateRecord);
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
                MVRUtility.exitProgram(false);
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
        
        if (pressed.equals("Update Record"))
        {
            try
            {
                updateRecord(false);
            }
            catch (InvalidArgumentException exception)
            {
                exception.displayError();
            }
        }
            
        
        if (pressed.equals("Delete Record"))
        {
            try
            {
                updateRecord(true);
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
        this.setBounds(previousPage.getLocation().x, previousPage.getLocation().y, 400, 300);
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
        ownerPrivateDOB.setText("");
        ownerCorporateBusinessNumber.setText("");
        ownerName.setText("");
        ownerLastName.setText("");
        ownerAddress.setText("");
        ownerPhoneNumber.setText("");
        
        vehiclePlate.setText("");
        vehicleEngineCapacity.setText("");
        vehicleMake.setText("");
        vehicleModel.setText("");
        vehicleYear.setText("");
        
        if (vehicleOwner.getItemCount() > 1)
            vehicleOwner.setSelectedIndex(0);
    }
    
    /**
     *      updateRecord method
     *      @param delete Whether or not to delete the record
     *      @throws InvalidArgumentException
     */
    public void updateRecord(boolean delete) throws InvalidArgumentException
    {   
        if (selectedRecord == null)
            return;
        
        if (!delete)
        {
            if (selectedRecord instanceof Owner)
            {
                Owner owner = (Owner)selectedRecord;
                
                String firstName = ownerName.getText();
                String lastName = ownerLastName.getText();
                
                if ((firstName.isEmpty() || lastName.isEmpty()) 
                    || (!MVRUtility.isAlpha(firstName) || !MVRUtility.isAlpha(lastName)) 
                    || (firstName.startsWith(" ") || lastName.startsWith(" ")))
                    throw new InvalidArgumentException("Please enter both the first and last name correctly.");
                
                String address = ownerAddress.getText();
                
                if (address.isEmpty() || !MVRUtility.isAlphaNumeric(address))
                    throw new InvalidArgumentException("Please enter an address correctly. E.g 1 Harold Street, Brisbane, QLD, 4000");
                
                String phoneNumber = ownerPhoneNumber.getText();
                
                if (phoneNumber.isEmpty() || !MVRUtility.isInteger(phoneNumber))
                    throw new InvalidArgumentException("Please enter a phone number correctly. E.g 35940950");
                
                if (owner instanceof OwnerPrivate)
                {
                    String newDOB = ownerPrivateDOB.getText();
                    
                    if (!MVRUtility.isDate(newDOB))
                        throw new InvalidArgumentException("The new owner date of birth must be a date with the format: DD/MM/YYYY");
                    
                    OwnerPrivate ownerPrivate = (OwnerPrivate)owner;
                    ownerPrivate.setAddress(newDOB);
                }
                else
                {
                    String newBusinessNumber = ownerCorporateBusinessNumber.getText();
                    
                    if (!MVRUtility.isInteger(newBusinessNumber) || newBusinessNumber.trim().length() != 8)
                        throw new InvalidArgumentException("The new business number must be numeric and 8 digits.");
                    
                    OwnerCorporate ownerCorporate = (OwnerCorporate)owner;
                    ownerCorporate.setBusinessNumber(Integer.parseInt(newBusinessNumber));
                }
                
                owner.setFirstName(firstName);
                owner.setLastName(lastName);
                owner.setAddress(address);
                owner.setPhoneNumber(phoneNumber);
            }
            else if (selectedRecord instanceof Vehicle)
            {
                Vehicle vehicle = (Vehicle)selectedRecord;
                
                String plateNumber =  vehiclePlate.getText();
                
                if (plateNumber.length() != 6 || !MVRUtility.isLicensePlate(plateNumber))
                {
                    throw new InvalidArgumentException("Plate numbers must be exactly 6 characters in length.\n"
                            + "Plates must also start with 3 characters and end with 3 digits.\n"
                            + "E.g. CAT400");
                }
                else 
                {
                    if (Vehicle.vehicleExists(plateNumber) && (!plateNumber.equals(vehicle.getPlateNumber())))
                        throw new InvalidArgumentException("A vehicle is registered with the "
                                + "plate number [" + plateNumber.toUpperCase() + "] already.");
                }
                
                String engineCapacity = vehicleEngineCapacity.getText();
                
                if (engineCapacity.isEmpty() || engineCapacity.isBlank() || !MVRUtility.isDouble(engineCapacity))
                    throw new InvalidArgumentException("Please enter the engine capacity correctly.\n"
                    + "E.g. 2.4");
                
                String make = vehicleMake.getText();
                
                if (make.isEmpty() || make.isBlank() || !MVRUtility.isAlpha(make))
                    throw new InvalidArgumentException("Please enter the vehicle make correctly");
                
                if (make.split(" ").length > 1)
                    throw new InvalidArgumentException("Vehicle make can only be one word.");
                
                String model = vehicleModel.getText();
                
                if (model.isEmpty() || model.isBlank() || !MVRUtility.isAlphaNumeric(model))
                    throw new InvalidArgumentException("Please enter the vehicle model correctly.");
                
                if (model.split(" ").length > 1)
                    throw new InvalidArgumentException("Vehicle model can only be one word.");
                
                String year = vehicleYear.getText();
                
                if (year.isEmpty() || year.isBlank() || !MVRUtility.isInteger(year))
                {
                    throw new InvalidArgumentException("Please enter the vehicle's year made corretly\n"
                    + "E.g. 2012");
                }
                else
                {
                    Integer yearMade = Integer.parseInt(year);
                    
                    if (yearMade < 1884 || yearMade > 2020)
                        throw new InvalidArgumentException("Please enter a real year of make.");
                }
                
                if (vehicle instanceof VehicleHeavy)
                {
                    String loadCapacity = vehicleHeavyLoadCapacity.getText();
                    
                    if (loadCapacity.isEmpty() || loadCapacity.isBlank() || !MVRUtility.isDouble(loadCapacity))
                        throw new InvalidArgumentException("Please enter the load capacity correctly. E.g 24.5");

                    VehicleHeavy vehicleHeavy = (VehicleHeavy)vehicle;
                    vehicleHeavy.setLoadCapacity(Double.parseDouble(loadCapacity));
                }
                else if (vehicle instanceof VehicleLight)
                {
                    String numberOfSeats = vehicleLightNumberOfSeats.getText();
                    
                    if (numberOfSeats.isEmpty() || !MVRUtility.isInteger(numberOfSeats))
                        throw new InvalidArgumentException("Please enter the number of seats correctly.");
                    
                    if (Integer.parseInt(numberOfSeats) > 7)
                        throw new InvalidArgumentException("Please enter a correct number of seats. (7 max)");
                    
                    VehicleLight vehicleLight = (VehicleLight)vehicle;
                    vehicleLight.setNumberOfSeats(Integer.parseInt(numberOfSeats));
                }
                
                vehicle.setPlateNumber(plateNumber);
                vehicle.setEngineCapacity(Double.parseDouble(engineCapacity));
                vehicle.setMake(make);
                vehicle.setModel(model);
                vehicle.setYear(Integer.parseInt(year));
                vehicle.setOwnerId(Integer.parseInt(vehicleOwner.getSelectedItem().toString()));
            }
        }
        
        Application.getFileManager().updateRecord(selectedRecord, delete);
        previous.clear();
        previous.initialize(this);
        this.dispose();
    }
    
    /**
     *      addEntryFields method
     */
    private void addEntryFields()
    {
        if (selectedRecord instanceof Owner)
        {
            Owner owner = (Owner)selectedRecord;
            
            if (owner instanceof OwnerPrivate)
            {
                OwnerPrivate ownerPrivate = (OwnerPrivate)owner;
                
                ownerPrivateDOB.setText(ownerPrivate.getDateOfBirth());
                ownerTypePanel.add(ownerPrivateDOBLabel);
                ownerTypePanel.add(ownerPrivateDOB);
            }
            else
            {
                OwnerCorporate ownerCorporate = (OwnerCorporate)owner;
                
                ownerCorporateBusinessNumber.setText(String.valueOf(ownerCorporate.getBusinessNumber()));
                ownerTypePanel.add(ownerCorporateBusinessNumberLabel);
                ownerTypePanel.add(ownerCorporateBusinessNumber);
            }
            
            ownerName.setText(owner.getFirstName());
            ownerLastName.setText(owner.getLastName());
            ownerNamePanel.add(ownerNameLabel);
            ownerNamePanel.add(ownerName);
            ownerNamePanel.add(ownerLastNameLabel);
            ownerNamePanel.add(ownerLastName);
            
            ownerAddress.setText(owner.getAddress());
            ownerAddressPanel.add(ownerAddressLabel);
            ownerAddressPanel.add(ownerAddress);
            
            ownerPhoneNumber.setText(owner.getPhoneNumber());
            ownerPhoneNumberPanel.add(ownerPhoneNumberLabel);
            ownerPhoneNumberPanel.add(ownerPhoneNumber);
            
            middlePanel.add(ownerTypePanel);
            middlePanel.add(ownerNamePanel);
            middlePanel.add(ownerAddressPanel);
            middlePanel.add(ownerPhoneNumberPanel);
        }
        else
        {
            Vehicle vehicle = (Vehicle)selectedRecord;
            
            boolean addTypePanel = false;
            
            if (vehicle instanceof VehicleHeavy)
            {
                addTypePanel = true;
                
                VehicleHeavy vehicleHeavy = (VehicleHeavy)vehicle;
                
                vehicleHeavyLoadCapacity.setText(String.valueOf(vehicleHeavy.getLoadCapacity()));
                vehicleTypePanel.add(vehicleHeavyLoadCapacityLabel);
                vehicleTypePanel.add(vehicleHeavyLoadCapacity);
            }
            else if (vehicle instanceof VehicleLight)
            {
                addTypePanel = true;
                
                VehicleLight vehicleLight = (VehicleLight)vehicle;
                
                vehicleLightNumberOfSeats.setText(String.valueOf(vehicleLight.getNumberOfSeats()));
                vehicleTypePanel.add(vehicleLightNumberOfSeatsLabel);
                vehicleTypePanel.add(vehicleLightNumberOfSeats);
            }
            
            vehiclePlate.setText(vehicle.getPlateNumber());
            vehiclePlatePanel.add(vehiclePlateLabel);
            vehiclePlatePanel.add(vehiclePlate);
            
            vehicleEngineCapacity.setText(String.valueOf(vehicle.getEngineCapacity()));
            vehicleMake.setText(vehicle.getMake());
            vehicleEngineMakePanel.add(vehicleEngineCapacityLabel);
            vehicleEngineMakePanel.add(vehicleEngineCapacity);
            vehicleEngineMakePanel.add(vehicleMakeLabel);
            vehicleEngineMakePanel.add(vehicleMake);
            
            vehicleModel.setText(vehicle.getModel());
            vehicleYear.setText(String.valueOf(vehicle.getYear()));
            vehicleModelYearPanel.add(vehicleModelLabel);
            vehicleModelYearPanel.add(vehicleModel);
            vehicleModelYearPanel.add(vehicleYearLabel);
            vehicleModelYearPanel.add(vehicleYear);
            
            ArrayList<Owner> allOwners = Owner.getOwners();
            String[] owners = new String[allOwners.size()];
            int vehicleOwnerIndex = 0;
            
            for (int x = 0; x < allOwners.size(); ++x)
            {
                if (allOwners.get(x).getId() == vehicle.getOwnerId())
                    vehicleOwnerIndex = x;
                owners[x] = String.valueOf(allOwners.get(x).getId());
            }
            
            vehicleOwner = new JComboBox(owners);
            vehicleOwner.setSelectedIndex(vehicleOwnerIndex);
            vehicleOwnerPanel.add(vehicleOwnerLabel);
            vehicleOwnerPanel.add(vehicleOwner);
            
            if (addTypePanel)
                middlePanel.add(vehicleTypePanel);
            
            middlePanel.add(vehiclePlatePanel);
            middlePanel.add(vehicleEngineMakePanel);
            middlePanel.add(vehicleModelYearPanel);
            middlePanel.add(vehicleOwnerPanel);
        }
    }
    
}
