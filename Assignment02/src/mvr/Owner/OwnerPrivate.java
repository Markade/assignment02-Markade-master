package mvr.Owner;

/**
 * @author Mark Windler (12143085)
 * Date: 25/09/2020
 * Purpose: An owner subclass for a private vehicle owner
 */
public class OwnerPrivate extends Owner {
    
    private String dateOfBirth;     // The private owner's date of birth
    
    /**
     *      Constructor
     *      @param id The private owner's license ID
     *      @param firstName The private owner's first name
     *      @param lastName The private owner's last name
     *      @param address The private owner's address
     *      @param phoneNumber The private owner's phone number
     *      @param dateOfBirth The private owner's date of birth
     */
    public OwnerPrivate(int id, String firstName, String lastName, String address,
            String phoneNumber, String dateOfBirth) {
        super(id, firstName, lastName, address, phoneNumber);
        this.dateOfBirth = dateOfBirth;
    }
    
    /**
     *      Default Constructor
     */
    public OwnerPrivate(){}
    
    /**
     *      getDateOfBirth method
     *      @return The private owner's date of birth 
     */
    public String getDateOfBirth()
    {
        return dateOfBirth;
    }
    
    /**
     *      setDateOfBirth method
     *      @param newDateOfBirth  The private owner's new date of birth 
     */
    public void setDateOfBirth(String newDateOfBirth)
    {
        dateOfBirth = newDateOfBirth;
    }
    
    /**
     *      toString overridden method
     *      @return The private owner's information 
     */
    @Override
    public String toString()
    {
        return  "Owner type: Private\n" 
                + super.toString() 
                + "Date of birth: " + dateOfBirth + "\n";
    }
    
}
