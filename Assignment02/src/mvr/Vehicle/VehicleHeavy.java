package mvr.Vehicle;

/**
 * @author Mark Windler (12143085)
 * Date: 25/09/2020
 * Purpose: Vehicle subclass for a heavy vehicle
 */
public class VehicleHeavy extends Vehicle{
    
    private double loadCapacity;     // The heavy vehicle's load capacity
    
    /**
     *      Constructor
     *      @param plateNumber The heavy vehicle's plate number
     *      @param engineCapacity The heavy vehicle's engine capacity
     *      @param make The heavy vehicle's make
     *      @param model The heavy vehicle's model
     *      @param year The heavy vehicle's Vehicle made
     *      @param ownerId The heavy vehicle's owner id
     *      @param loadCapacity The heavy vehicle's load capacity
     */
    public VehicleHeavy(String plateNumber, double engineCapacity, String make,
            String model, int year, int ownerId, double loadCapacity) 
    {
        super(plateNumber, engineCapacity, make, model, year, ownerId);
        this.loadCapacity = loadCapacity;
    }
    
    /**
     *      Default Constructor
     */
    public VehicleHeavy(){}
    
    /**
     *      getLoadCapacity method
     *      @return The load capacity
     */
    public double getLoadCapacity()
    {
        return loadCapacity;
    }
    
    /**
     *      setLoadCapacity method
     *      @param newLoadCapacity The heavy vehicle's new load capacity 
     */
    public void setLoadCapacity(double newLoadCapacity)
    {
        loadCapacity = newLoadCapacity;
    }
    
    /**
     *      toString overridden method
     *      @return The heavy vehicle's information
     */
    @Override
    public String toString()
    {
        return  "Vehicle type: Heavy\n"
                + super.toString() 
                + "Load capacity: " + loadCapacity + " tonne(s)\n";
    }
    
}
