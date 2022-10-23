package mvr.Vehicle;

import java.util.ArrayList;

/**
 * @author Mark Windler (12143085)
 * Date: 25/09/2020
 * Purpose: The generic vehicle class for all vehicles to inherit
 */
public class Vehicle {
    
    // All vehicles
    private static ArrayList<Vehicle> VEHICLES = new ArrayList<>();
    
    private String plateNumber;             // The vehicle plate number
    private double engineCapacity;          // The vehicle engine capacity
    private String make;                    // The vehicle make
    private String model;                   // The vehicle model
    private int year;                       // The vehicle year
    private int ownerId;                    // The vehicle owner id
    
    /**
     *      Constructor
     *      @param plateNumber The vehicle's plate number
     *      @param engineCapacity The vehicle's engine capacity
     *      @param make The vehicle's make
     *      @param model The vehicle's model
     *      @param year The vehicle's year made
     *      @param ownerId The vehicle's owner id
     */
    public Vehicle(String plateNumber, double engineCapacity, String make,
                    String model, int year, int ownerId)
    {
        this.plateNumber = plateNumber;
        this.engineCapacity = engineCapacity;
        this.make = make;
        this.model = model;
        this.year = year;
        this.ownerId = ownerId;
    }
    
    /**
     *      Default Constructor
     */
    public Vehicle(){}
    
    /**
     *      getPlateNumber method
     *      @return The vehicle's plate number 
     */
    public String getPlateNumber()
    {
        return plateNumber;
    }
    
    /**
     *      getEngineCapacity method
     *      @return The vehicle's engine capacity 
     */
    public double getEngineCapacity()
    {
        return engineCapacity;
    }
    
    /**
     *      getMake method
     *      @return The vehicle's make 
     */
    public String getMake()
    {
        return make;
    }
    
    /**
     *      getModel method
     *      @return The vehicle's model
     */
    public String getModel()
    {
        return model;
    }
    
    /**
     *      getYear method
     *      @return The vehicle's Vehicle made 
     */
    public int getYear()
    {
        return year;
    }
    
    /**
     *      getOwnerId method
     *      @return The vehicle's owner's Id
     */
    public int getOwnerId()
    {
        return ownerId;
    }
    
    /**
     *      setPlateNumber method
     *      @param newPlateNumber The vehicle's new plate number 
     */
    public void setPlateNumber(String newPlateNumber)
    {
        plateNumber = newPlateNumber;
    }
    
    /**
     *      setEngineCapacity method
     *      @param newEngineCapacity The vehicle's new engine capacity 
     */
    public void setEngineCapacity(double newEngineCapacity)
    {
        engineCapacity = newEngineCapacity;
    }
    
    /**
     *      setMake method
     *      @param newMake The vehicle's new make
     */
    public void setMake(String newMake)
    {
        make = newMake;
    }
    
    /**
     *      setModel method
     *      @param newModel The vehicle's new model
     */
    public void setModel(String newModel)
    {
        model = newModel;
    }
    
    /**
     *      setYear method
     *      @param newYear The vehicle's new year
     */
    public void setYear(int newYear)
    {
        year = newYear;
    }
    
    /**
     *      setOwnerId method
     *      @param newOwnerId The vehicle's new owner ID 
     */
    public void setOwnerId(int newOwnerId)
    {
        ownerId = newOwnerId;
    }
    
    /**
     *      toString overridden method
     *      @return The vehicle's information
     */
    @Override
    public String toString()
    {
        return "Plate number: " + plateNumber
                + "\nEngine capacity: " + engineCapacity + " litre(s)"
                + "\nMake: " + make
                + "\nModel: " + model
                + "\nYear: " + year
                + "\nOwner ID: " + ownerId + "\n";
    }
    
    /**
     *      getVehicles method
     *      @return All vehicle entries 
     */
    public static ArrayList<Vehicle> getVehicles()
    {
        return VEHICLES;
    }
    
    /**
     *      vehicleExists method
     *      @param plateNumber The plate number to check for
     *      @return Whether the vehicle exists 
     */
    public static boolean vehicleExists(String plateNumber)
    {
        if (VEHICLES.isEmpty())
            return false;
        
        return VEHICLES.stream().anyMatch((vehicle) 
                -> (vehicle.getPlateNumber().equalsIgnoreCase(plateNumber)));
    }
    
    /**
     *      getVehicle method
     *      @param plate The plate number to search
     *      @return The vehicle with the matching plate number
     */
    public static Vehicle getVehicle(String plate)
    {
        Vehicle vehicle = null;
        
        for (Vehicle v : VEHICLES)
        {
            if (v.getPlateNumber().equalsIgnoreCase(plate))
            {
                vehicle = v;
                break;
            }
        }
        
        return vehicle;
    }
    
    /**
     *      getVehicles
     *      @param ownerID The ownerID to search against
     *      @return The vehicles related to the owner ID
     */
    public static ArrayList<Vehicle> getVehiclesForOwner(int ownerID)
    {
        ArrayList<Vehicle> vehiclesRelated = new ArrayList();
        
        for (Vehicle v : VEHICLES)
        {
            if (v.getOwnerId() == ownerID)
                vehiclesRelated.add(v);
        }
        
        return vehiclesRelated;
    }
    
}
