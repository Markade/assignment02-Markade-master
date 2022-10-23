package mvr.Vehicle;

/**
 * @author Mark Windler (12143085)
 * Date: 25/09/2020
 * Purpose: Vehicle subclass for a motorcycle
 */
public class Motorcycle extends Vehicle {
    
    /**
     *      Constructor
     *      @param plateNumber The motorcycle's plate number
     *      @param engineCapacity The motorcycle's engine capacity
     *      @param make The motorcycle's make
     *      @param model The motorcycle's model
     *      @param year The motorcycle's Vehicle made
     *      @param ownerId The motorcycle's owner id
     */
    public Motorcycle(String plateNumber, double engineCapacity, String make,
            String model, int year, int ownerId) 
    {
        super(plateNumber, engineCapacity, make, model, year, ownerId);
    }
    
    /**
     *      Default Constructor
     */
    public Motorcycle(){}
    
    /**
     *      toString overridden method
     *      @return The motorcycle's information 
     */
    @Override
    public String toString()
    {
        return "Vehicle type: Motorcycle\n" + super.toString();
    }
    
}
