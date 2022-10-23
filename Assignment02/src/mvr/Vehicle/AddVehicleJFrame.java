package mvr.Vehicle;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import mvr.Owner.Owner;

/**
 * @author Mark Windler (12143085)
 * Date: 22/09/2020
 * Purpose: The JFrame to add vehicles to the MVR system
 */
public class AddVehicleJFrame extends JFrame implements ActionListener, FramePage {
    
    // Panels
    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 2, 3));
    private JPanel bottomPanel = new JPanel();
    
    // Top Panel
    private JLabel titleLabel = new JLabel("Add Vehicle Record                 ");
    
    // Middle Panel
    private JTextArea textArea = new JTextArea(5, 51);
    private JScrollPane scrollPane = new JScrollPane(textArea);
    
    private JPanel vehicleType = new JPanel();
    private JLabel vehicleTypeLabel = new JLabel("Select vehicle type: ");
    private String[] vehicleTypes = {"Please select...", "Motorcycle", "Heavy", "Light"};
    private JComboBox vehicleTypesComboBox = new JComboBox(vehicleTypes);
    
    // Vehicle panels
    private JPanel vehicleTypePanel = new JPanel();
    private JPanel vehiclePlatePanel = new JPanel();
    private JPanel vehicleEngineMakePanel = new JPanel();
    private JPanel vehicleModelYearPanel = new JPanel();
    private JPanel vehicleOwnerPanel = new JPanel();
    
    // Vehicle heavy fields
    private JLabel vehicleHeavyLoadCapacityLabel = new JLabel("Load capacity: ");
    private JTextField vehicleHeavyLoadCapacity = new JTextField(5);
    
    // Vehicle light fields
    private JLabel vehicleLightNumberOfSeatsLabel = new JLabel("Number of seats: ");
    private JTextField vehicleLightNumberOfSeats = new JTextField(2);
    
    // Vehicle fields
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
    private JButton addVehicle = new JButton("Add Vehicle");
    
    public AddVehicleJFrame(JFrame previousJFrame)
    {
        super("Motor Vehicle Registration - Add Vehicle");
        
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
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        middlePanel.add(scrollPane);
        
        vehicleTypesComboBox.addActionListener(this);
        vehicleType.add(vehicleTypeLabel);
        vehicleType.add(vehicleTypesComboBox);
        middlePanel.add(vehicleType);
        
        middlePanel.add(vehicleTypePanel);
        middlePanel.add(vehiclePlatePanel);
        middlePanel.add(vehicleEngineMakePanel);
        middlePanel.add(vehicleModelYearPanel);
        middlePanel.add(vehicleOwnerPanel);
        
        this.add(middlePanel);
        
        // Bottom Panel
        addVehicle.addActionListener(this);
        bottomPanel.add(addVehicle);
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
        if (e.getSource() instanceof JComboBox)
        {
            JComboBox jComboBox = (JComboBox)e.getSource();
            
            if (jComboBox.equals(vehicleTypesComboBox))
            {
                switch (jComboBox.getSelectedIndex()) 
                {
                    case 0:
                        hideVehicleFields();
                        clear();
                        break;
                    case 1:
                        clear();
                        showVehicleMotorcycleFields();
                        break;
                    case 2:
                        clear();
                        showVehicleHeavyFields();
                        break;
                    case 3:
                        clear();
                        showVehicleLightFields();
                        break;
                }
            }
            
        }
        else
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
        }
    }

    /**
     *      initialize overridden method
     *      @param previousPage The previous JFrame
     */
    @Override
    public void initialize(JFrame previousPage) 
    {
        this.setBounds(previousPage.getLocation().x, previousPage.getLocation().y, 400, 400);
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
        textArea.setText("");
        vehicleHeavyLoadCapacity.setText("");
        vehicleLightNumberOfSeats.setText("");
        vehiclePlate.setText("");
        vehicleEngineCapacity.setText("");
        vehicleMake.setText("");
        vehicleModel.setText("");
        vehicleYear.setText("");
    }
    
    /**
     *      addVehicleFields method
     */
    private void addVehicleFields()
    {
        vehiclePlatePanel.add(vehiclePlateLabel);
        vehiclePlatePanel.add(vehiclePlate);
        vehicleEngineMakePanel.add(vehicleEngineCapacityLabel);
        vehicleEngineMakePanel.add(vehicleEngineCapacity);
        vehicleEngineMakePanel.add(vehicleMakeLabel);
        vehicleEngineMakePanel.add(vehicleMake);
        vehicleModelYearPanel.add(vehicleModelLabel);
        vehicleModelYearPanel.add(vehicleModel);
        vehicleModelYearPanel.add(vehicleYearLabel);
        vehicleModelYearPanel.add(vehicleYear);
        
        if (!Owner.getOwners().isEmpty())
        {
            String[] owners = new String[Owner.getOwners().size() + 1];
        
            owners[0] = "Please select...";
            
            for (int x = 1; x < owners.length; ++x)
            {
                owners[x] = String.valueOf(Owner.getOwners().get(x - 1).getId());
            }
        
            vehicleOwner = new JComboBox(owners);
        }
        
        vehicleOwnerPanel.add(vehicleOwnerLabel);
        vehicleOwnerPanel.add(vehicleOwner);
        
        this.revalidate();
        this.repaint();
    }
    
    /**
     *      hideVehicleFields method
     */
    private void hideVehicleFields()
    {
        vehicleTypePanel.remove(vehicleHeavyLoadCapacityLabel);
        vehicleTypePanel.remove(vehicleHeavyLoadCapacity);
        vehicleTypePanel.remove(vehicleLightNumberOfSeatsLabel);
        vehicleTypePanel.remove(vehicleLightNumberOfSeats);
        vehiclePlatePanel.remove(vehiclePlateLabel);
        vehiclePlatePanel.remove(vehiclePlate);
        vehicleEngineMakePanel.remove(vehicleEngineCapacityLabel);
        vehicleEngineMakePanel.remove(vehicleEngineCapacity);
        vehicleEngineMakePanel.remove(vehicleMakeLabel);
        vehicleEngineMakePanel.remove(vehicleMake);
        vehicleModelYearPanel.remove(vehicleModelLabel);
        vehicleModelYearPanel.remove(vehicleModel);
        vehicleModelYearPanel.remove(vehicleYearLabel);
        vehicleModelYearPanel.remove(vehicleYear);
        vehicleOwnerPanel.remove(vehicleOwnerLabel);
        vehicleOwnerPanel.remove(vehicleOwner);
        
        this.revalidate();
        this.repaint();
    } 
    
    /**
     *      showOwnerPrivateFields method
     */
    private void showVehicleMotorcycleFields()
    {
        hideVehicleFields();
        addVehicleFields();
    }
    
    /**
     *      showOwnerPrivateFields method
     */
    private void showVehicleHeavyFields()
    {
        hideVehicleFields();
        vehicleTypePanel.add(vehicleHeavyLoadCapacityLabel);
        vehicleTypePanel.add(vehicleHeavyLoadCapacity);
        addVehicleFields();
    }
    
    /**
     *      showOwnerPrivateFields method
     */
    private void showVehicleLightFields()
    {
        hideVehicleFields();
        vehicleTypePanel.add(vehicleLightNumberOfSeatsLabel);
        vehicleTypePanel.add(vehicleLightNumberOfSeats);
        addVehicleFields();
    }
    
    /**
     *      createVehicle method
     */
    private void addVehicle() throws InvalidArgumentException
    {   
        if (vehicleTypesComboBox.getSelectedIndex() == 0)
            throw new InvalidArgumentException("Please select a vehicle type first.");
        
        if (vehicleOwner.getSelectedIndex() == 0)
            throw new InvalidArgumentException("Please select an owner's ID.");
        
        String ownerId = vehicleOwner.getSelectedItem().toString();
        
        String plateNumber = vehiclePlate.getText();
        
        if (plateNumber.length() != 6 || !MVRUtility.isLicensePlate(plateNumber))
        {
            vehiclePlate.requestFocus();
            throw new InvalidArgumentException("Plate numbers must be exactly 6 characters in length.\n"
                    + "Plates must also start with 3 characters and end with 3 digits.\n"
                    + "E.g. CAT400");
        }
        else 
        {
            if (Vehicle.vehicleExists(plateNumber))
            {
                vehiclePlate.requestFocus();
                throw new InvalidArgumentException("A vehicle is registered with the "
                        + "plate number [" + plateNumber.toUpperCase() + "] "
                                + "already.");
            }
        }
        
        String engineCapacity = vehicleEngineCapacity.getText();
        
        if (engineCapacity.isEmpty() || engineCapacity.isBlank() || !MVRUtility.isDouble(engineCapacity))
        {
            vehicleEngineCapacity.requestFocus();
            throw new InvalidArgumentException("Please enter the engine capacity correctly.\n"
                    + "E.g. 2.4");
        }
        
        String make = vehicleMake.getText();
        
        if (make.isEmpty() || make.isBlank() || !MVRUtility.isAlpha(make))
        {
            vehicleMake.requestFocus();
            throw new InvalidArgumentException("Please enter the vehicle make correctly");
        }
        
        if (make.split(" ").length > 1)
            throw new InvalidArgumentException("Vehicle make can only be one word.");
        
        String model = vehicleModel.getText();
        
        if (model.isEmpty() || model.isBlank() || !MVRUtility.isAlphaNumeric(model))
        {
            vehicleModel.requestFocus();
            throw new InvalidArgumentException("Please enter the vehicle model correctly.");
        }
        
        if (model.split(" ").length > 1)
            throw new InvalidArgumentException("Vehicle model can only be one word.");
        
        String year = vehicleYear.getText();
        
        if (year.isEmpty() || year.isBlank() || !MVRUtility.isInteger(year))
        {
            vehicleYear.requestFocus();
            throw new InvalidArgumentException("Please enter the vehicle's year made corretly\n"
                    + "E.g. 2012");
        }
        else 
        {
            Integer yearMade = Integer.parseInt(year);
            
            if (yearMade < 1884 || yearMade > 2020)
            {
                vehicleYear.requestFocus();
                throw new InvalidArgumentException("Please enter a real year of make.");
            }
        }
        
        int vehicle = vehicleTypesComboBox.getSelectedIndex();
        
        Vehicle vehicleCreated = null;
        
        switch (vehicle) 
        {
            case 1:
                {
                    vehicleCreated = new Motorcycle(plateNumber, Double.parseDouble(engineCapacity),
                            make, model, Integer.parseInt(year), Integer.parseInt(ownerId));
                    Application.getFileManager().saveVehicle(vehicleCreated);
                    break;
                    
                }
            case 2:
                {
                    String loadCapacity = vehicleHeavyLoadCapacity.getText();
                    
                    if (loadCapacity.isEmpty() || loadCapacity.isBlank() || !MVRUtility.isDouble(loadCapacity))
                    {
                        vehicleHeavyLoadCapacity.requestFocus();
                        throw new InvalidArgumentException("Please enter the load capacity correctly. E.g 24.5");
                    }
                    
                    vehicleCreated = new VehicleHeavy(plateNumber, Double.parseDouble(engineCapacity),
                            make, model, Integer.parseInt(year), Integer.parseInt(ownerId),
                            Double.parseDouble(loadCapacity));
                    Application.getFileManager().saveVehicle(vehicleCreated);
                    break;
                }
            case 3:
                {
                    String numberOfSeats = vehicleLightNumberOfSeats.getText();
                    
                    if (numberOfSeats.isEmpty() || !MVRUtility.isInteger(numberOfSeats))
                        throw new InvalidArgumentException("Please enter the number of seats correctly.");
                    
                    if (Integer.parseInt(numberOfSeats) > 7)
                    {
                        vehicleLightNumberOfSeats.requestFocus();
                        throw new InvalidArgumentException("Please enter a correct number of seats. (7 max)");
                    }
                    
                    vehicleCreated = new VehicleLight(plateNumber, Double.parseDouble(engineCapacity),
                            make, model, Integer.parseInt(year), Integer.parseInt(ownerId),
                            Integer.parseInt(numberOfSeats));
                    Application.getFileManager().saveVehicle(vehicleCreated);
                    break;
                }
        }
        
        clear();
        if (vehicleCreated != null)
        {
            MVRUtility.displayEntry(vehicleCreated, textArea);
        }
    }
    
}
