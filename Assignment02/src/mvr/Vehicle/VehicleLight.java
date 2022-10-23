package mvr.Vehicle;

/**
 * @author Mark Windler (12143085)
 * Date: 25/09/2020
 * Purpose: Vehicle subclass for a light vehicle
 */
public class VehicleLight extends Vehicle{
    
    public int numberOfSeats;   // The light vehicle's number of seats
    
    /**
     *      Constructor
     *      @param plateNumber The light vehicle's plate number
     *      @param engineCapacity The light vehicle's engine capacity
     *      @param make The light vehicle's make
     *      @param model The light vehicle's model
     *      @param year The light vehicle's Vehicle made
     *      @param ownerId The light vehicle's owner id
     *      @param numberOfSeats The light vehicle's number of seats
     */
    public VehicleLight(String plateNumber, double engineCapacity, String make,
            String model, int year, int ownerId, int numberOfSeats) 
    {
        super(plateNumber, engineCapacity, make, model, year, ownerId);
        this.numberOfSeats = numberOfSeats;
    }
    
    /**
     *      Default Constructor
     */
    public VehicleLight(){}
    
    /**
     *      getNumberOfSeats method
     *      @return The number of seats 
     */
    public int getNumberOfSeats()
    {
        return numberOfSeats;
    }
    
    /**
     *      setNumberOfSeats method
     *      @param newNumberOfSeats The light vehicle's new number of seats 
     */
    public void setNumberOfSeats(int newNumberOfSeats)
    {
        numberOfSeats = newNumberOfSeats;
    }
    
    /**
     *      toString overridden method
     *      @return The light vehicle's information 
     */
    @Override
    public String toString()
    {
        return  "Vehicle type: Light\n"
                + super.toString() 
                + "Number of seats: " + numberOfSeats + "\n";
    }
    
}
