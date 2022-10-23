package mvr.Owner;

import java.util.ArrayList;

/**
 * @author Mark Windler (12143085)
 * Date: 25/09/2020
 * Purpose: The generic owner class for all owners to inherit
 */
public class Owner {
    
    // All owners
    private static ArrayList<Owner> OWNERS = new ArrayList<>();
    
    private int id;                          // The owner's id
    private String firstName;                // The owner's first name
    private String lastName;                 // The owner's last name
    private String address;                  // The owner's address
    private String phoneNumber;              // The owner's phone number
    
    /**
     *      Constructor
     *      @param id The owner's license ID
     *      @param firstName The owner's first name
     *      @param lastName The owner's last name
     *      @param address The owner's address
     *      @param phoneNumber The owner's phone number
     */
    public Owner(int id, String firstName, String lastName, String address, String phoneNumber)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    /**
     *      Default Constructor
     */
    public Owner(){}
    
    /**
     *      getId method
     *      @return The owner's name 
     */
    public int getId()
    {
        return id;
    }
    
    /**
     *      getFirstName method
     *      @return The owner's first name 
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     *      getLastName method
     *      @return The owner's last name
     */
    public String getLastName()
    {
        return lastName;
    }
    
    /**
     *      getAddress method
     *      @return The owner's address 
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     *      getPhoneNumber method
     *      @return The owner's phone number 
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    
    /**
     *      setId method
     *      @param newId The new owner ID 
     */
    public void setId(int newId)
    {
        id = newId;
    }
    
    /**
     *      setFirstName method
     *      @param newFirstName The owner's new first name 
     */
    public void setFirstName(String newFirstName)
    {
        firstName = newFirstName;
    }
    
    /**
     *      setLastName method
     *      @param newLastName The owner's new last name 
     */
    public void setLastName(String newLastName)
    {
        lastName = newLastName;
    }
    
    /**
     *      setAddress method
     *      @param newAddress The owner's new address 
     */
    public void setAddress(String newAddress)
    {
        address = newAddress;
    }
    
    /**
     *      setPhoneNumber method
     *      @param newPhoneNumber The owner's new phone number 
     */
    public void setPhoneNumber(String newPhoneNumber)
    {
        phoneNumber = newPhoneNumber;
    }
    
    /**
     *      toString overridden method
     *      @return The owner's information
     */
    @Override
    public String toString()
    {
        return "Licence number: " + id
                + "\nFirst name: " + firstName
                + "\nLast name: " + lastName
                + "\nAddress: " + address
                + "\nPhone number: " + phoneNumber + "\n";
    }
    
    /**
     *      getOwners method
     *      @return All owner's 
     */
    public static ArrayList<Owner> getOwners()
    {
        return OWNERS;
    }
    
    /**
     *      ownerExists method
     *      @param ownerId The owner id to search for
     *      @return Whether the owner exists
     */
    public static boolean ownerExists(int ownerId)
    {
        if (OWNERS.isEmpty())
            return false;
        
        return OWNERS.stream().anyMatch((owner) -> (owner.getId() == ownerId));
    }
    
    /**
     *      getOwner method
     *      @param id The ID to search
     *      @return The owner that matches the searched license ID
     */
    public static Owner getOwner(int id)
    {
        Owner owner = null;
        
        for (Owner o : OWNERS)
        {
            if (o.getId() == id)
            {
                owner = o;
                break;
            }
        }
        
        return owner;
    }
    
    /**
     *      getNextLicenseID
     *      @return The next auto-incremented license ID
     */
    public static int getNextLicenseID()
    {
        if (!OWNERS.isEmpty())
        {
            int highestID = OWNERS.get(0).getId();
            
            for (Owner owner : OWNERS)
            {
                if (owner.getId() > highestID)
                    highestID = owner.getId();
            }
            
            return highestID + 1;
        }
        
        return 1;
    }
    
}
