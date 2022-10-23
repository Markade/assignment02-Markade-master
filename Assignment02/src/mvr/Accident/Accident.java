package mvr.Accident;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Mark Windler (12143085)
 * Date: 23/09/2020
 * Purpose: To create and store and accident record for the MVR system
 */
public class Accident {
    
    private static ArrayList<Accident> ACCIDENTS = new ArrayList<>();   // All recorded accidents
    
    private int id;                                                     // The id of the accident record
    private String location;                                            // The location of the accident
    private String date;                                                // The date of the accident
    private ArrayList<String> vehicleRegistrations;                     // All vehicle registrations involved
    private ArrayList<String> comments;                                 // All comments about the accident
    
    /**
     *      Constructor
     *      @param id The id of the accident record
     *      @param location The location of the accident
     *      @param date The date of the accident
     *      @param vehicleRegistrations The vehicle registrations involved
     *      @param comments All comments about the accident
     */
    public Accident(int id, String location, String date,
            ArrayList<String> vehicleRegistrations, ArrayList<String> comments)
    {
        this.id = id;
        this.location = location;
        this.date = date;
        this.vehicleRegistrations = vehicleRegistrations;
        this.comments = comments;
    }
    
    /**
     *      Default Constructor
     */
    public Accident(){}
    
    /**
     *      getID method
     *      @return The accident record's id 
     */
    public int getID()
    {
        return id;
    }
    
    /**
     *      getLocation method
     *      @return The accident record's location 
     */
    public String getLocation()
    {
        return location;
    }
    
    /**
     *      getDate method
     *      @return The accident record's date 
     */
    public String getDate()
    {
        return date;
    }
    
    /**
     *      getVehicleRegistrations method
     *      @return The accident record's vehicle registrations 
     */
    public ArrayList<String> getVehicleRegistrations()
    {
        return vehicleRegistrations;
    }
    
    /**
     *      getComments method
     *      @return The accident record's comments 
     */
    public ArrayList<String> getComments()
    {
        return comments;
    }
    
    /**
     *      setID method
     *      @param newID The accident's new ID
     */
    public void setId(int newID)
    {
        id = newID;
    }
    
    /**
     *      setLocation method
     *      @param newLocation The accident's new location
     */
    public void setLocation(String newLocation)
    {
        location =  newLocation;
    }
    
    /**
     *      setDate method
     *      @param newDate The accident's new date
     */
    public void setDate(String newDate)
    {
        date = newDate;
    }
    
    /**
     *      setVehicleRegistrations method
     *      @param newVehicles The accident's new vehicles
     */
    public void setVehicleRegistrations(ArrayList<String> newVehicles)
    {
        vehicleRegistrations = newVehicles;
    }
    
    /**
     *      setComments method
     *      @param newComments The accident's new comments
     */
    public void setComments(ArrayList<String> newComments)
    {
        comments = newComments;
    }
    
    /**
     *      toString overridden method
     *      @return The accident's information
     */
    @Override
    public String toString()
    {
        return String.format("%-15s%-20s%-20s%-25s%-20s",
                id, location, date, convertVehiclesToString(), convertCommentsToString());
    }
    
    /**
     *      convertVehiclesToString
     *      @return A string of the vehicle registrations involved 
     */
    public String convertVehiclesToString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        boolean firstEntry = true;
        
        for (String registration : vehicleRegistrations)
        {
            if (firstEntry)
            {
                stringBuilder.append(registration);
                firstEntry = false;
            }
            else
            {
                stringBuilder.append(", ").append(registration);
            }    
        }
        
        return stringBuilder.toString();
    }
    
    /**
     *      convertCommentsToString
     *      @return A string of the comments
     */
    public String convertCommentsToString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        
        boolean firstEntry = true;
        
        for (String comment : comments)
        {
            if (firstEntry)
            {
                stringBuilder.append(comment);
                firstEntry = false;
            }
            else
            {
                stringBuilder.append(". ").append(comment);
            }    
        }
        
        return stringBuilder.toString();
    }
    
    /**
     *      getAccidents method
     *      @return All recorded accidents 
     */
    public static ArrayList<Accident> getAccidents()
    {
        return ACCIDENTS;
    }
    
    /**
     *      accidentExists method
     *      @param accidentID The accident ID to search for
     *      @return Whether the accident exists
     */
    public static boolean accidentExists(int accidentID)
    {
        if (ACCIDENTS.isEmpty())
            return false;
        
        return ACCIDENTS.stream().anyMatch((accident) -> (accident.getID() == accidentID));
    }
    
    /**
     *      getAccident method
     *      @param id The ID to search
     *      @return The accident that matches the searched accident ID
     */
    public static Accident getAccident(int id)
    {
        Accident accident = null;
        
        for (Accident a : ACCIDENTS)
        {
            if (a.getID() == id)
            {
                accident = a;
                break;
            }
        }
        
        return accident;
    }
    
    /**
     *      getAccidentsForDate method
     *      @param date The date to search for
     *      @param accidents The accident array to check from
     *      @return An array list of the accidents that match the date searched
     */
    public static ArrayList<Accident> getAccidentsForDate(String date, ArrayList<Accident> accidents)
    {
        ArrayList<Accident> matchedAccidents = new ArrayList();
        
        for (Accident a : accidents)
        {
            if (a.getDate().equalsIgnoreCase(date))
                matchedAccidents.add(a);
        }
        
        return matchedAccidents;
    }
    
    /**
     *      getAccidentsForVehicle method
     *      @param plateNumber The plate number to search against
     *      @return The accidents related to the vehicle plate number
     */
    public static ArrayList<Accident> getAccidentsForVehicle(String plateNumber)
    {
        ArrayList<Accident> accidents = new ArrayList();
        
        for (Accident a : ACCIDENTS)
        {
            for (String plates : a.getVehicleRegistrations())
            {
                if (plates.equals(plateNumber))
                {
                    accidents.add(a);
                    break;
                }
            }
        }
        
        return accidents;
    }
    
}
