package mvr.Owner;

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

/**
 * @author Mark Windler (12143085)
 * Date: 22/09/2020
 * Purpose: The JFrame to add an owner to the system
 */
public class AddOwnerJFrame extends JFrame implements ActionListener, FramePage {
      
    // Panels
    private JPanel topPanel = new JPanel();
    private JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 2, 4));
    private JPanel bottomPanel = new JPanel();
    
    // Top Panel
    private JLabel titleLabel = new JLabel("Add Owner Record                 ");
    
    // Middle Panel
    private JTextArea textArea = new JTextArea(5, 51);
    private JScrollPane scrollPane = new JScrollPane(textArea);
    
    private JPanel ownerType = new JPanel();
    private JLabel ownerTypeLabel = new JLabel("Select owner type: ");
    private String[] ownerTypes = {"Please select...", "Private", "Corporate"};
    private JComboBox ownerTypesComboBox = new JComboBox(ownerTypes);
    
    // Owner panel
    private JPanel ownerTypePanel = new JPanel();
    private JPanel ownerNamePanel = new JPanel();
    private JPanel ownerAddressPanel = new JPanel();
    private JPanel ownerPhoneNumberPanel = new JPanel();
    
    // Owner private fields
    private JLabel ownerPrivateDOBLabel = new JLabel("Date of birth: ");
    private JTextField  ownerPrivateDOB = new JTextField(10);
    
    // Owner corporate fields
    private JLabel ownerCorporateBusinessNumberLabel = new JLabel("Business number: ");
    private JTextField ownerCorporateBusinessNumber = new JTextField(11);
    
    // Owner fields
    private JLabel ownerNameLabel = new JLabel("First name: ");
    private JTextField ownerName = new JTextField(10);
    private JLabel ownerLastNameLabel = new JLabel("Last name: ");
    private JTextField ownerLastName = new JTextField(10);
    private JLabel ownerAddressLabel = new JLabel("Address: ");
    private JTextField ownerAddress = new JTextField(30);
    private JLabel ownerPhoneNumberLabel = new JLabel("Phone Number: ");
    private JTextField ownerPhoneNumber = new JTextField(10);
    
    // Bottom Panel
    private JButton addOwner = new JButton("Add Owner");
    
    
    /**
     *      Constructor
     *      @param previousJFrame The previous JFrame
     */
    public AddOwnerJFrame(JFrame previousJFrame)
    {
        super("Motor Vehicle Registration - Add Owner");
        
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
        
        ownerTypesComboBox.addActionListener(this);
        ownerType.add(ownerTypeLabel);
        ownerType.add(ownerTypesComboBox);
        middlePanel.add(ownerType);
        
        middlePanel.add(ownerTypePanel);
        middlePanel.add(ownerNamePanel);
        middlePanel.add(ownerAddressPanel);
        middlePanel.add(ownerPhoneNumberPanel);
        
        this.add(middlePanel);
        
        // Bottom Panel
        addOwner.addActionListener(this);
        bottomPanel.add(addOwner);
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
            
            if (jComboBox.equals(ownerTypesComboBox))
            {
                switch (jComboBox.getSelectedIndex()) 
                {
                    case 0:
                        hideOwnerFields();
                        clear();
                        break;
                    case 1:
                        clear();
                        showOwnerPrivateFields();
                        break;
                    case 2:
                        clear();
                        showOwnerCorporateFields();
                        break;
                }
            }
            
        }
        else
        {
            String pressed = e.getActionCommand();
            
            if (pressed.equals("Add Owner"))
            {
                try
                {
                    addOwner();
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
        ownerPrivateDOB.setText("");
        ownerCorporateBusinessNumber.setText("");
        ownerName.setText("");
        ownerLastName.setText("");
        ownerAddress.setText("");
        ownerPhoneNumber.setText("");
    }
    
    /**
     *      addOwnerFields method
     */
    private void addOwnerFields()
    {
        ownerNamePanel.add(ownerNameLabel);
        ownerNamePanel.add(ownerName);
        ownerNamePanel.add(ownerLastNameLabel);
        ownerNamePanel.add(ownerLastName);
        ownerAddressPanel.add(ownerAddressLabel);
        ownerAddressPanel.add(ownerAddress);
        ownerPhoneNumberPanel.add(ownerPhoneNumberLabel);
        ownerPhoneNumberPanel.add(ownerPhoneNumber);
        
        this.revalidate();
        this.repaint();
    }
    
    /**
     *      hideOwnerFields method
     */
    private void hideOwnerFields()
    {
        ownerTypePanel.remove(ownerPrivateDOBLabel);
        ownerTypePanel.remove(ownerPrivateDOB);
        ownerTypePanel.remove(ownerCorporateBusinessNumberLabel);
        ownerTypePanel.remove(ownerCorporateBusinessNumber);
        ownerNamePanel.remove(ownerNameLabel);
        ownerNamePanel.remove(ownerName);
        ownerNamePanel.remove(ownerLastNameLabel);
        ownerNamePanel.remove(ownerLastName);
        ownerAddressPanel.remove(ownerAddressLabel);
        ownerAddressPanel.remove(ownerAddress);
        ownerPhoneNumberPanel.remove(ownerPhoneNumberLabel);
        ownerPhoneNumberPanel.remove(ownerPhoneNumber);
        
        this.revalidate();
        this.repaint();
    }        
    
    /**
     *      showOwnerPrivateFields method
     */
    private void showOwnerPrivateFields()
    {
        hideOwnerFields();
        ownerTypePanel.add(ownerPrivateDOBLabel);
        ownerTypePanel.add(ownerPrivateDOB);
        addOwnerFields();
    }
    
    /**
     *      showOwnerCorporateFields method
     */
    private void showOwnerCorporateFields()
    {
        hideOwnerFields();
        ownerTypePanel.add(ownerCorporateBusinessNumberLabel);
        ownerTypePanel.add(ownerCorporateBusinessNumber);
        addOwnerFields();
    }
    
    /**
     *      addOwner method
     */
    private void addOwner() throws InvalidArgumentException
    {   
        if (ownerTypesComboBox.getSelectedIndex() == 0)
            throw new InvalidArgumentException("Please select an owner type to add a record for.");
        
        boolean addOwnerPrivate = true;
        
        if (ownerTypesComboBox.getSelectedIndex() == 2)
            addOwnerPrivate = false;
        
        String firstName = ownerName.getText();
        String lastName = ownerLastName.getText();
        
        if ((firstName.isEmpty() || lastName.isEmpty()) 
                || (!MVRUtility.isAlpha(firstName) || !MVRUtility.isAlpha(lastName)) 
                || (firstName.startsWith(" ") || lastName.startsWith(" ")))
        {
            ownerName.requestFocus();
            throw new InvalidArgumentException("Please enter both the first and last name correctly.");
        }
        
        String address = ownerAddress.getText();
        
        if (address.isEmpty() || !MVRUtility.isAlphaNumeric(address))
        {
            ownerAddress.requestFocus();
            throw new InvalidArgumentException("Please enter an address correctly. E.g 1 Harold Street, Brisbane, QLD, 4000");
        }
        
        String phoneNumber = ownerPhoneNumber.getText();
        
        if (phoneNumber.isEmpty() || !MVRUtility.isInteger(phoneNumber))
        {
            ownerPhoneNumber.requestFocus();
            throw new InvalidArgumentException("Please enter a phone number correctly. E.g 35940950");
        }
        
        Owner ownerCreated;
        
        if (addOwnerPrivate)
        {
            String dateOfBirth = ownerPrivateDOB.getText();
            
            if (!MVRUtility.isDate(dateOfBirth))
            {
                ownerPrivateDOB.requestFocus();
                throw new InvalidArgumentException("Please enter the owner's date of birth correctly. E.g 15/03/1954");
            }
            
            ownerCreated = new OwnerPrivate(Owner.getNextLicenseID(), firstName, lastName, address,
                    phoneNumber, dateOfBirth);
            Application.getFileManager().saveOwner(ownerCreated);
        } 
        else
        {
            String businessNumber = ownerCorporateBusinessNumber.getText();
            
            if (!MVRUtility.isInteger(businessNumber))
                throw new InvalidArgumentException("Please enter a numeric business number.");
            
            if (businessNumber.length() != 8)
                throw new InvalidArgumentException("Please enter an 8-digit business number.");
            
            ownerCreated = new OwnerCorporate(Owner.getNextLicenseID(), firstName, lastName, address,
            phoneNumber, Integer.parseInt(businessNumber));
            Application.getFileManager().saveOwner(ownerCreated);
        }
        
        clear();
        hideOwnerFields();
        ownerTypesComboBox.setSelectedIndex(0);
        textArea.append("Successfully added an owner entry.\n\n");
        MVRUtility.displayEntry(ownerCreated, textArea);
    }

}
