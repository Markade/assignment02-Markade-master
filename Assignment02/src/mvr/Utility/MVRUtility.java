package mvr.Utility;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import mvr.Application;
import mvr.Owner.Owner;
import mvr.Owner.OwnerCorporate;
import mvr.Owner.OwnerPrivate;
import mvr.Vehicle.Motorcycle;
import mvr.Vehicle.Vehicle;
import mvr.Vehicle.VehicleHeavy;
import mvr.Vehicle.VehicleLight;

/**
 * @author Mark Windler (12143085)
 * Date: 25/09/2020
 * Purpose: The MVR utility class used for message displaying and error checking
 */
public class MVRUtility {
    
    /**    
     *      displayInput method
     *      @param message The message to display
     *      @return The user input
     */
    public static String displayInput(String message)
    {
        return JOptionPane.showInputDialog(null, message,
                "Motor Vehicle Registration", JOptionPane.QUESTION_MESSAGE);
    }
    
    /**
     *     displayError method
     *     @param message The error message 
     */
    public static void displayError(String message) 
    {
        JOptionPane.showMessageDialog(null, message,
                "Motor Vehicle Registration", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     *      isAlpha method
     *      @param stringToCheck The string to check
     *      @return Whether the string is alpha 
     */
    public static boolean isAlpha(String stringToCheck)
    {
        return stringToCheck.matches("[a-zA-Z\\s]+");
    }
    
    /**
     *      isAlphaNumeric method
     *      @param stringToCheck The string to check
     *      @return Whether the string is alphanumeric 
     */
    public static boolean isAlphaNumeric(String stringToCheck)
    {
        return stringToCheck.matches("^[0-9a-zA-Z\\s,]+$");
    }
    
    /**
     *      isDate method
     *      @param stringToCheck The string to check
     *      @return Whether the string is a date
     */
    public static boolean isDate(String stringToCheck)
    {
        return stringToCheck.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/((19|2[0-9])[0-9]{2})$");
    }
    
    /**
     *      isLicensePlate method
     *      @param string The string to check
     *      @return Whether the string is a license plate, e.g. ABD758 
     */
    public static boolean isLicensePlate(String string)
    {
        return string.matches("\\b[a-zA-Z]{3}\\d{3}\\b");
    }
    
    /**
     *      isAccidentID method
     *      @param string The string to check
     *      @return Whether or not the string is a 7 digit number
     */
    public static boolean isAccidentID(String string)
    {
        return isInteger(string) && string.length() == 7;
    }
    
    /**
    *      isInteger method
    *      @param string The string to check
    *      @return Whether or not the string is an integer
    */
    public static boolean isInteger(String string)
    {
        try
        {
            Integer.parseInt(string);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    
    /**
    *      isDouble method
    *      @param string The string to check
    *      @return Whether or not the string is a double
    */
    public static boolean isDouble(String string)
    {
        try
        {
            Double.parseDouble(string);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    
    /**
     *      printLine method
     *      @param textArea The text area to print to
     */
    public static void printLine(JTextArea textArea)
    {
        textArea.append("------------------------------------------\n");
    }
    
    /**
     *      displayEntry method
     *      @param selectedEntry The selected entry
     *      @param textArea The text area to display to
     */
    public static void displayEntry(Object selectedEntry, JTextArea textArea)
    {
        String entryInformation = "";
        
        if (selectedEntry instanceof Owner)
        {
            Owner owner = (Owner)selectedEntry;
            
            if (owner instanceof OwnerPrivate)
            {
                OwnerPrivate ownerPrivate = (OwnerPrivate)owner;
                entryInformation = ownerPrivate.toString();
            }
            else if(owner instanceof OwnerCorporate)
            {
                OwnerCorporate ownerCorporate = (OwnerCorporate)owner;
                entryInformation = ownerCorporate.toString();
            }
        
        }
        else if (selectedEntry instanceof Vehicle)
        {
            Vehicle vehicle = (Vehicle)selectedEntry;
        
            if (vehicle instanceof VehicleHeavy)
            {
                VehicleHeavy heavyVehicle = (VehicleHeavy)vehicle;
                entryInformation = heavyVehicle.toString();
            }
            else if (vehicle instanceof VehicleLight)
            {
                VehicleLight lightVehicle = (VehicleLight)vehicle;
                entryInformation = lightVehicle.toString();
            }
            else if (vehicle instanceof Motorcycle)
            {
                Motorcycle motorcycle = (Motorcycle)vehicle;
                entryInformation = motorcycle.toString();
            }
        }
        
        textArea.append(entryInformation);
        MVRUtility.printLine(textArea);
        textArea.setCaretPosition(0);
    }
    
    /**
     *      exitProgram method
     *      Saves all record data then exits the program
     *      @param saveData Whether or not to save the data to file
     */
    public static void exitProgram(boolean saveData)
    {
        int exit = JOptionPane.showConfirmDialog(null, "Are you sure you would"
            + " like to exit the program?",
            "Motor Vehicle Registration", JOptionPane.YES_NO_OPTION);
        
        if (exit == 0)
        {
            Application.getFileManager().saveAllData(saveData);
            JOptionPane.showMessageDialog(null, "Thank you for using QLD's Motor Vehicle Registration program.\n\n"
                    + "By Mark Windler (12143085)",
                    "Queensland Road and Transport Authority", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
    }
    
}
