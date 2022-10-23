package mvr.Records;

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
import mvr.Owner.Owner;
import mvr.Vehicle.Vehicle;

/**
 * @author Mark Windler (12143085)
 * Date: 22/09/2020
 * Purpose: The JFrame for handling any searching in the MVR program
 */
public class SearchJFrame extends JFrame implements ActionListener, FramePage {
    
    // Selected entry
    private Object selectedRecord;
    
    // Panels
    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel();
    private JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 2, 1));
    
    // Top Panel
    private JLabel titleLabel = new JLabel("Owner & Vehicle Search                 ");
    
    // Middle Panel
    private JTextArea textArea = new JTextArea(8, 50);
    private JScrollPane scrollPane = new JScrollPane(textArea);
    
    // Bottom Panel
    private JButton showAllRecords = new JButton("Show All Records");
    private JButton editRecord = new JButton("Edit Record");
    
    private JPanel recordPanel = new JPanel();
    private JLabel searchCriteriaLabel = new JLabel("Search Criteria: ");
    private String[] searchCriteriaChoices = {"Please select...", "Owner by License Number", "Vehicle by Number Plate"};
    private JComboBox searchCriteria = new JComboBox(searchCriteriaChoices);
    
    private JLabel recordSelect = new JLabel("Record Quick Select: ");
    private String[] recordChoices = {"Please select..."};
    private JComboBox records = new JComboBox(recordChoices);
    
    private JPanel searchPanel = new JPanel();
    private JLabel searchLabel = new JLabel("Search: ");
    private JTextField searchTextField = new JTextField(15);
    
    private JPanel buttonsPanel = new JPanel();
    private JButton searchButton = new JButton("Search");
    
    public SearchJFrame(JFrame previous)
    {
        super("Motor Vehicle Registration - Search");
        
        // Initialization
        initialize(previous);
        
        // Exit Menu
        this.setJMenuBar(new ExitMenu(true));
        
        // Top Panel
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        topPanel.add(titleLabel);
        topPanel.add(new BackButton(Application.getMainFrame(), this));
        
        // Middle Panel
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        middlePanel.add(scrollPane);
        
        // Bottom Panel
        bottomPanel.setPreferredSize(new Dimension(200, 150));
        
        JPanel criteriaPanel = new JPanel();
        searchCriteria.addActionListener(this);
        criteriaPanel.add(searchCriteriaLabel);
        criteriaPanel.add(searchCriteria);
        bottomPanel.add(criteriaPanel);
        
        
        recordPanel.add(recordSelect);
        recordPanel.add(records);
        bottomPanel.add(recordPanel);
        
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(new ClearButton(this));
        bottomPanel.add(searchPanel);
        
        showAllRecords.addActionListener(this);
        searchButton.addActionListener(this);
        editRecord.addActionListener(this);
        buttonsPanel.add(searchButton);
        buttonsPanel.add(editRecord);
        buttonsPanel.add(showAllRecords);
        bottomPanel.add(buttonsPanel);
        
        this.add(topPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
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
            
            if (jComboBox.equals(searchCriteria))
            {
                switch (jComboBox.getSelectedIndex()) 
                {
                    case 0:
                        clear();
                        resetRecords();
                        break;
                    case 1:
                        showOwnerRecords();
                        break;
                    case 2:
                        showVehicleRecords();
                        break;
                }
            }
        }
        else
        {
            String action = e.getActionCommand(); 
        
            if (action.equals("Show All Records"))
                showAllRecords();
        
            if (action.equals("Search"))
            {
                try
                {
                    search();
                }
                catch (InvalidArgumentException exception)
                {
                    exception.displayError();
                }
            }
                
        
            if (action.equals("Edit Record"))
                editRecord(selectedRecord);
        }      
    }

    /**
     *      initialize method
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
        textArea.setCaretPosition(0);
        searchTextField.setText("");
        records.setSelectedIndex(0);
        searchCriteria.setSelectedIndex(0);
    }
    
    /**
     *      resetRecords method
     */
    private void resetRecords()
    {
        hideRecords();
        records = new JComboBox(recordChoices);
        recordPanel.add(records);
        
        this.revalidate();
        this.repaint();
    }
    
    /**
     *      hide records method
     */
    private void hideRecords()
    {
        recordPanel.remove(records);
        
        this.revalidate();
        this.repaint();
    }
    
    /**
     *      showOwnerRecords method
     */
    private void showOwnerRecords()
    {
        hideRecords();
        
        if (!Owner.getOwners().isEmpty())
        {
            String[] owners = new String[Owner.getOwners().size() + 1];
        
            owners[0] = "Please select...";
            
            for (int x = 1; x < owners.length; ++x)
            {
                owners[x] = String.valueOf(Owner.getOwners().get(x - 1).getId());
            }
        
            records = new JComboBox(owners);
        }
        
        recordPanel.add(records);
        
        this.revalidate();
        this.repaint();
    }
    
    /**
     *      showVehicleRecords method
     */
    private void showVehicleRecords()
    {
        hideRecords();
        
        if (!Vehicle.getVehicles().isEmpty())
        {
            String[] vehicles = new String[Vehicle.getVehicles().size() + 1];
        
            vehicles[0] = "Please select...";
            
            for (int x = 1; x < vehicles.length; ++x)
            {
                vehicles[x] = Vehicle.getVehicles().get(x - 1).getPlateNumber();
            }
        
            records = new JComboBox(vehicles);
        }
        
        recordPanel.add(records);
        
        this.revalidate();
        this.repaint();
    }
    
    /**
     *      showAllRecords method
     */
    private void showAllRecords()
    {
        int owners = Owner.getOwners().size();
        int vehicles = Vehicle.getVehicles().size();
        
        textArea.setText("");
        MVRUtility.printLine(textArea);
        textArea.append("Owners [" + owners + "]:\n\n");
        
        if (owners == 0)
        {
            textArea.append("No owner records found.\n");
        }
        else
        {
            Owner.getOwners().forEach(owner -> MVRUtility.displayEntry(owner, textArea));
        }
        
        textArea.append("\n\n");
        MVRUtility.printLine(textArea);
        textArea.append("Vehicles [" + vehicles + "]:\n\n");
        
        if (vehicles == 0)
        {
            textArea.append("No vehicle records found.\n");
        }
        else
        {
            Vehicle.getVehicles().forEach(vehicle -> MVRUtility.displayEntry(vehicle, textArea)); 
        }
        
        textArea.setCaretPosition(0);
        showAllRecords.setFocusPainted(false);
        
        if (selectedRecord != null)
            selectedRecord = null;
    }
    
    /**
     *      search method
     *      @throws InvalidArgumentException
     */
    public void search() throws InvalidArgumentException
    {
        if (searchCriteria.getSelectedIndex() == 0)
            throw new InvalidArgumentException("Please select a search criteria.");
        
        if (records.getSelectedIndex() == 0 && (searchTextField.getText().isEmpty() 
                || searchTextField.getText().isBlank()))
            throw new InvalidArgumentException("Please select a record or write in the search field.");
        
        String search = searchTextField.getText();
        boolean searchOwner = false;
        boolean matchFound = false;
        
        if (records.getSelectedIndex() != 0)
            search = records.getSelectedItem().toString();
        
        if (searchCriteria.getSelectedIndex() == 1)
            searchOwner = true;
        
        if (searchOwner)
        {
            if (Owner.getOwners().isEmpty())
            {
                MVRUtility.displayError("No owners exist on the system "
                        + "- please add a record.");
                return;
            }
            
            if (!MVRUtility.isInteger(search))
                throw new InvalidArgumentException("Please enter a valid licence number.");
            
            for (Owner owner : Owner.getOwners())
            {
                if (owner.getId() == Integer.parseInt(search))
                {
                    matchFound = true;
                    selectedRecord = owner;
                    break;
                }
            }
        }
        else
        {
            if (Vehicle.getVehicles().isEmpty())
            {
                MVRUtility.displayError("No vehicles exist on the system "
                        + "- please add a record.");
                return;
            }
            
            if (!MVRUtility.isLicensePlate(search))
                throw new InvalidArgumentException("Please enter a valid plate number, e.g. CAT123");
            
            for (Vehicle vehicle : Vehicle.getVehicles())
            {
                if (vehicle.getPlateNumber().equalsIgnoreCase(search))
                {
                    matchFound = true;
                    selectedRecord = vehicle;
                    System.out.print("Match: " + vehicle.getPlateNumber() + " - search: " + search);
                    break;
                }
            }
        }
        
        if (!matchFound)
        {
            MVRUtility.displayError("No match found for: " + search);
            return;
        }

        clear();
        MVRUtility.displayEntry(selectedRecord, textArea);
    }
    
    /**
     *      editRecord method
     *      @param selectedRecord The selected record to edit
     */
    private void editRecord(Object selectedRecord) 
    {
        if (selectedRecord == null)
        {
            MVRUtility.displayError("Please search for the record first, before editing.");
            searchTextField.requestFocus();
            return;
        }
        
        if (selectedRecord instanceof Owner)
        {
            EditRecordJFrame editOwner = new EditRecordJFrame(this, this, (Owner)selectedRecord);
            this.dispose();
        }
        
        if (selectedRecord instanceof Vehicle)
        {
            EditRecordJFrame editVehicle = new EditRecordJFrame(this, this, (Vehicle)selectedRecord);
            this.dispose();
        }
    }
    
}
