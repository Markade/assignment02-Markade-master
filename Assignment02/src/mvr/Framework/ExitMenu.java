package mvr.Framework;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import mvr.Utility.MVRUtility;

/**
 * @author Mark Windler (12143085)
 * Date: 22/09/2020
 * Purpose: Custom menu bar that contains exit button
 */
public class ExitMenu extends JMenuBar {
    
    /**
     *      Constructor
     *      @param saveData Whether or not to save the data to file
     */
    public ExitMenu(boolean saveData)
    {
        JMenu fileMenu = new JMenu("File");
        JMenuItem exit = fileMenu.add("Exit");
        exit.addActionListener(e -> exitProgram(saveData));
        this.add(fileMenu);
    }
    
    /**
     *      exitProgram method
     */
    private void exitProgram(boolean saveData)
    {
        MVRUtility.exitProgram(saveData);
    }
    
}
