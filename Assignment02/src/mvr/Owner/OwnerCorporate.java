package mvr.Owner;

/**
 * @author Mark Windler (12143085)
 * Date: 25/09/2020
 * Purpose: An owner subclass for a corporate vehicle owner
 */
public class OwnerCorporate extends Owner {
    
    private int businessNumber;    // The corporate owner's business number
    
    /**
     *      Constructor
     *      @param id The corporate owner's license ID
     *      @param firstName The corporate owner's first name
     *      @param lastName The corporate owner's last name
     *      @param address The corporate owner's address
     *      @param phoneNumber The corporate owner's phone number
     *      @param businessNumber The corporate owner's business number
     */
    public OwnerCorporate(int id, String firstName, String lastName, String address,
            String phoneNumber, int businessNumber) 
    {
        super(id, firstName, lastName, address, phoneNumber);
        this.businessNumber = businessNumber;
    }
    
    /**
     *      Default Constructor
     */
    public OwnerCorporate(){}
    
    /**
     *      getBusinessNumber method
     *      @return The corporate owner's business number 
     */
    public int getBusinessNumber()
    {
        return businessNumber;
    }
    
    /**
     *      setBusinessNumber method
     *      @param newBusinessNumber The corporate owner's new business number 
     */
    public void setBusinessNumber(int newBusinessNumber)
    {
        businessNumber = newBusinessNumber;
    }
    
    /**
     *      toString overridden method
     *      @return The corporate owner's information 
     */
    @Override
    public String toString()
    {
        return "Owner type: Corporate\n" 
                + super.toString() 
                + "Business Number: " + businessNumber + "\n";
    }
    
}
