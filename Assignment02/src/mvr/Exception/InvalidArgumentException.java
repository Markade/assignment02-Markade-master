package mvr.Exception;

import mvr.Utility.MVRUtility;

/** 
 * @author Mark Windler (12143085)
 * Date: 24/09/2020
 * Purpose: Exception for errors in GUI argument entered by the user
 */
public class InvalidArgumentException extends Exception {
    
    private final String ARGUMENT_ERROR;    // The errors text to display in a pop-up
    
    /**
     *      Constructor
     *      @param error The error text to display
     */
    public InvalidArgumentException(String error)
    {        
        ARGUMENT_ERROR = error;
    }
    
    /**
     *      displayErrod method
     */
    public void displayError()
    {
        MVRUtility.displayError(ARGUMENT_ERROR);
    }
    
}
