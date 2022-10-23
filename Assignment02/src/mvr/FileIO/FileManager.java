package mvr.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import mvr.Accident.Accident;
import mvr.Owner.Owner;
import mvr.Owner.OwnerCorporate;
import mvr.Owner.OwnerPrivate;
import mvr.Vehicle.Motorcycle;
import mvr.Vehicle.Vehicle;
import mvr.Vehicle.VehicleHeavy;
import mvr.Vehicle.VehicleLight;

/**
 * @author Mark Windler (12143085)
 * Date: 22/09/2020
 * Purpose: The File Manager to handle all FileIO read and writes and file locations
 */
public class FileManager {
    
    public FileManager()
    {
        // Reload all data from file
        loadOwners();
        loadVehicles();
        loadAccidents();
    }
    
    /**
     *      loadOwners method
     */
    public void loadOwners()
    {
        try 
        {
            File ownersFile = new File("owners.txt");
            ownersFile.createNewFile();
            
            FileReader reader = new FileReader(ownersFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
 
            while ((line = bufferedReader.readLine()) != null) 
            {
                if (!line.isEmpty() && !line.isBlank())
                {
                    String[] ownerValues = line.split("\\|");
                    
                    StringBuilder stringBuilder = new StringBuilder();
                    
                    for (int x = 6; x < ownerValues.length; ++x)
                    {
                        stringBuilder.append(ownerValues[x]);
                        stringBuilder.append(" ");
                    }
                    
                    String address = stringBuilder.toString().trim();
                    
                    if (ownerValues[1].equals("PRIVATE"))
                    {
                        OwnerPrivate ownerPrivate = new OwnerPrivate();
                        ownerPrivate.setId(Integer.parseInt(ownerValues[0]));
                        ownerPrivate.setDateOfBirth(ownerValues[2]);
                        ownerPrivate.setFirstName(ownerValues[3]);
                        ownerPrivate.setLastName(ownerValues[4]);
                        ownerPrivate.setPhoneNumber(ownerValues[5]);
                        ownerPrivate.setAddress(address);
                        
                        Owner.getOwners().add(ownerPrivate);
                    }
                    else
                    {
                        OwnerCorporate ownerCorporate = new OwnerCorporate();
                        ownerCorporate.setId(Integer.parseInt(ownerValues[0]));
                        ownerCorporate.setBusinessNumber(Integer.parseInt(ownerValues[2]));
                        ownerCorporate.setFirstName(ownerValues[3]);
                        ownerCorporate.setLastName(ownerValues[4]);
                        ownerCorporate.setPhoneNumber(ownerValues[5]);
                        ownerCorporate.setAddress(address);
                        
                        Owner.getOwners().add(ownerCorporate);
                    }
                }
            }
            
            reader.close();
 
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     *      loadVehicles method
     */
    public void loadVehicles()
    {
        try 
        {
            File vehiclesFile = new File("vehicles.txt");
            vehiclesFile.createNewFile();
            
            FileReader reader = new FileReader(vehiclesFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
 
            while ((line = bufferedReader.readLine()) != null) 
            {
                if (!line.isEmpty() && !line.isBlank())
                {
                    String[] vehicleValues = line.split("\\|");
                    
                    if (vehicleValues[1].equals("MOTORCYCLE"))
                    {
                        Motorcycle motorcycle = new Motorcycle();
                        motorcycle.setPlateNumber(vehicleValues[0]);
                        motorcycle.setEngineCapacity(Double.valueOf(vehicleValues[2]));
                        motorcycle.setMake(vehicleValues[3]);
                        motorcycle.setModel(vehicleValues[4]);
                        motorcycle.setYear(Integer.valueOf(vehicleValues[5]));
                        motorcycle.setOwnerId(Integer.valueOf(vehicleValues[6]));
                        
                        Vehicle.getVehicles().add(motorcycle);
                    }
                    else if (vehicleValues[1].equals("HEAVY"))
                    {
                        VehicleHeavy heavyVehicle = new VehicleHeavy();
                        heavyVehicle.setPlateNumber(vehicleValues[0]);
                        heavyVehicle.setLoadCapacity(Double.valueOf(vehicleValues[2]));
                        heavyVehicle.setEngineCapacity(Double.valueOf(vehicleValues[3]));
                        heavyVehicle.setMake(vehicleValues[4]);
                        heavyVehicle.setModel(vehicleValues[5]);
                        heavyVehicle.setYear(Integer.valueOf(vehicleValues[6]));
                        heavyVehicle.setOwnerId(Integer.valueOf(vehicleValues[7]));
                        
                        Vehicle.getVehicles().add(heavyVehicle);
                    }
                    else
                    {
                        VehicleLight lightVehicle = new VehicleLight();
                        lightVehicle.setPlateNumber(vehicleValues[0]);
                        lightVehicle.setNumberOfSeats(Integer.valueOf(vehicleValues[2]));
                        lightVehicle.setEngineCapacity(Double.valueOf(vehicleValues[3]));
                        lightVehicle.setMake(vehicleValues[4]);
                        lightVehicle.setModel(vehicleValues[5]);
                        lightVehicle.setYear(Integer.valueOf(vehicleValues[6]));
                        lightVehicle.setOwnerId(Integer.valueOf(vehicleValues[7]));
                        
                        Vehicle.getVehicles().add(lightVehicle);
                    }
                }
            }
            
            reader.close();
 
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     *      loadAccidents method
     */
    public void loadAccidents()
    {
        try 
        {
            File accidentsFile = new File("accidents.txt");
            accidentsFile.createNewFile();
            
            FileReader reader = new FileReader(accidentsFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
 
            while ((line = bufferedReader.readLine()) != null) 
            {
                if (!line.isEmpty() && !line.isBlank())
                {
                    String[] accidentValues = line.split("\\|");
                    
                    Accident accident = new Accident();
                    
                    accident.setId(Integer.parseInt(accidentValues[0]));
                    accident.setLocation(accidentValues[1]);
                    accident.setDate(accidentValues[2]);
                    
                    String[] vehiclesInvolved = accidentValues[3].split(", ");
                    ArrayList<String> vehicles = new ArrayList();
                    
                    for (String vehicleString : vehiclesInvolved)
                        vehicles.add(vehicleString.trim());
                    
                    accident.setVehicleRegistrations(vehicles);
                    
                    String[] accidentComments = accidentValues[4].split("\\.");
                    ArrayList<String> comments = new ArrayList();
                    
                    for (String comment : accidentComments)
                        comments.add(comment.trim());
                    
                    accident.setComments(comments);
                    
                    Accident.getAccidents().add(accident);
                }
            }
            
            reader.close();
 
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     *      saveOwner method
     *      @param owner The owner record to save to file
     */
    public void saveOwner(Owner owner)
    {
        if (!Owner.getOwners().contains(owner))
            Owner.getOwners().add(owner);
        
        try 
        {
            FileWriter writer = new FileWriter("owners.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            bufferedWriter.write(String.valueOf(owner.getId()) + "|");
            
            if (owner instanceof OwnerPrivate)
            {
                OwnerPrivate ownerPrivate = (OwnerPrivate)owner;
                bufferedWriter.write("PRIVATE|");
                bufferedWriter.write(ownerPrivate.getDateOfBirth() + "|");
            }
            else if (owner instanceof OwnerCorporate)
            {
                OwnerCorporate ownerCorporate = (OwnerCorporate)owner;
                bufferedWriter.write("CORPORATE|");
                bufferedWriter.write(String.valueOf(ownerCorporate.getBusinessNumber()) + "|");
            }
            
            bufferedWriter.write(owner.getFirstName() + "|");
            bufferedWriter.write(owner.getLastName() + "|");
            bufferedWriter.write(owner.getPhoneNumber() + "|");
            bufferedWriter.write(owner.getAddress());
            bufferedWriter.newLine();
            
            bufferedWriter.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     *      saveVehicle method
     *      @param vehicle The vehicle record to save to file
     */
    public void saveVehicle(Vehicle vehicle)
    {
        if (!Vehicle.getVehicles().contains(vehicle))
            Vehicle.getVehicles().add(vehicle);
        
        try 
        {
            FileWriter writer = new FileWriter("vehicles.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
 
            bufferedWriter.write(vehicle.getPlateNumber() + "|");
            
            if (vehicle instanceof Motorcycle)
            {
                bufferedWriter.write("MOTORCYCLE|");
            }
            else if (vehicle instanceof VehicleHeavy)
            {
                VehicleHeavy vehicleHeavy = (VehicleHeavy)vehicle;
                bufferedWriter.write("HEAVY|");
                bufferedWriter.write(String.valueOf(vehicleHeavy.getLoadCapacity() + "|"));
            }
            else if (vehicle instanceof VehicleLight)
            {
                VehicleLight vehicleLight = (VehicleLight)vehicle;
                bufferedWriter.write("LIGHT|");
                bufferedWriter.write(String.valueOf(vehicleLight.getNumberOfSeats() + "|"));
            }
            
            bufferedWriter.write(String.valueOf(vehicle.getEngineCapacity() + "|"));
            bufferedWriter.write(vehicle.getMake() + "|");
            bufferedWriter.write(vehicle.getModel() + "|");
            bufferedWriter.write(String.valueOf(vehicle.getYear() + "|"));
            bufferedWriter.write(String.valueOf(vehicle.getOwnerId()));
            bufferedWriter.newLine();
            
            bufferedWriter.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     *      saveAccident method
     *      @param accident The accident record to save to file
     */
    public void saveAccident(Accident accident)
    {
        if (!Accident.getAccidents().contains(accident))
            Accident.getAccidents().add(accident);
        
        try 
        {
            FileWriter writer = new FileWriter("accidents.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
 
            bufferedWriter.write(accident.getID() + "|");
            bufferedWriter.write(accident.getLocation() + "|");
            bufferedWriter.write(accident.getDate() + "|");
            bufferedWriter.write(accident.convertVehiclesToString() + "|");
            bufferedWriter.write(accident.convertCommentsToString());
            bufferedWriter.newLine();
 
            bufferedWriter.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     *      saveAllData method
     *      @param saveData Whether or not to save the data to file
     */
    public void saveAllData(boolean saveData)
    {
        if (saveData)
        {
            reloadOwners();
            reloadVehicles();
            reloadAccidents();
        }     
    }
    
    /**
     *      reloadOwners method
     */
    private void reloadOwners()
    {
        File file = new File("owners.txt");
        file.delete();
        
        Owner.getOwners().forEach(owner -> saveOwner(owner));
    }
    
    /**
     *      reloadVehicles method
     */
    private void reloadVehicles()
    {
        File file = new File("vehicles.txt");
        file.delete();
        
        Vehicle.getVehicles().forEach(vehicle -> saveVehicle(vehicle));
    }
    
    /**
     *      reloadAccidents method
     */
    private void reloadAccidents()
    {
        File file = new File("accidents.txt");
        file.delete();
        
        Accident.getAccidents().forEach(accident -> saveAccident(accident));
    }
    
    /**
     *      updateRecord method
     *      @param record The record to update
     *      @param delete Whether or not to delete the record
     */
    public void updateRecord(Object record, boolean delete)
    {
        if (record instanceof Owner)
        {
            if (delete)
            {
                Owner owner = (Owner)record;
                Owner.getOwners().remove(owner);
                
                ArrayList<Vehicle> ownersVehicles = Vehicle.getVehiclesForOwner(owner.getId());
                
                if (!ownersVehicles.isEmpty())
                {
                    int accidentsRemoved = 0;
                    
                    for (Vehicle v : ownersVehicles)
                    {
                        ArrayList<Accident> vehicleAccidents = Accident.getAccidentsForVehicle(v.getPlateNumber());
                        
                        for (Accident a : vehicleAccidents)
                        {
                            if (a.getVehicleRegistrations().size() > 1)
                                continue;
                            
                            if (a.getVehicleRegistrations().get(0).equals(v.getPlateNumber()))
                            {
                                Accident.getAccidents().remove(a);
                                ++accidentsRemoved;
                            }
                        }
                        
                        if (accidentsRemoved > 0)
                            reloadAccidents();
                        
                        Vehicle.getVehicles().remove(v);
                    }
                
                    reloadVehicles();
                }
            }
            
            reloadOwners();         
        }
        else if (record instanceof Vehicle)
        {
            if (delete)
            {
                Vehicle vehicle = (Vehicle)record;
                Vehicle.getVehicles().remove(vehicle);
                
                ArrayList<Accident> vehicleAccidents = Accident.getAccidentsForVehicle(vehicle.getPlateNumber());
                
                int accidentsRemoved = 0;
                
                if (!vehicleAccidents.isEmpty())
                {
                    for (Accident a : vehicleAccidents)
                        {
                            if (a.getVehicleRegistrations().size() > 1)
                                continue;
                            
                            if (a.getVehicleRegistrations().get(0).equals(vehicle.getPlateNumber()))
                            {
                                Accident.getAccidents().remove(a);
                                ++accidentsRemoved;
                            }
                        }
                        
                        if (accidentsRemoved > 0)
                            reloadAccidents();
                    
                    reloadAccidents();
                }
                
            }
            
            reloadVehicles();
        }
        else
        {
            if (delete)
            {
                Accident accident = (Accident)record;
                Accident.getAccidents().remove(accident);
            }
            
            reloadAccidents();
        }
        
        StringBuilder updateMessage = new StringBuilder();
        updateMessage.append("Successfully ");
        if (delete)
        {
            updateMessage.append("deleted ");
        }
        else
        {
            updateMessage.append("updated ");
        }
        updateMessage.append("the record");
        
        JOptionPane.showMessageDialog(null, updateMessage.toString(),
                "Motor Vehicle Registration - Edit Record", JOptionPane.INFORMATION_MESSAGE);
    }
}
