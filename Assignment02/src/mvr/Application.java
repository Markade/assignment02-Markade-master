package mvr;

import javax.swing.JFrame;
import mvr.FileIO.FileManager;

/**
 * @author Mark
 * Date: 22/09/2020
 * Purpose: Application class to create the FileManager and initial JFrame initialization
 */
public class Application {
    
    private static FileManager fileManager;     // The File Manager
    private static MVR mainFrame;               // The main JFrame
    
    /**
     *      main method
     *      @param args The arguments 
     */
    public static void main(String[] args) 
    {
        fileManager = new FileManager();
        initializeMainFrame(null);
    }
    
    /**
     *      getFileManager method
     *      @return The application's file manager instance
     */
    public static FileManager getFileManager()
    {
        return fileManager;
    }
    
    /**
     *      getMainFrame
     *      @return The main MVR JFrame instance 
     */
    public static MVR getMainFrame()
    {
        return mainFrame;
    }
    
    /**
     *      initializeMainFrame method
     *      @param previousFrame The previous frame to set the location of the mainFrame
     */
    public static void initializeMainFrame(JFrame previousFrame)
    {
        mainFrame = new MVR();
        mainFrame.setSize(430, 360);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if (previousFrame != null)
            mainFrame.setLocation(previousFrame.getLocation().x, previousFrame.getLocation().y);        
    }
    
}
