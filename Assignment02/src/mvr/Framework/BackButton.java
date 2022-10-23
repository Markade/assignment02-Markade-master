package mvr.Framework;

import javax.swing.JButton;
import javax.swing.JFrame;
import mvr.Interface.FramePage;

/**
 * @author Mark
 * Date: 22/09/2020
 * Purpose: Custom back button that takes the user to the main JFrame
 */
public class BackButton extends JButton {
    
    /**
     *      Constructor
     *      @param previous The previous frame page
     *      @param current The current JFrame
     */
    public BackButton(FramePage previous, JFrame current)
    {
        super("Back");
        this.addActionListener(e -> 
        {
            previous.initialize(current);
            current.dispose();
        });
        this.setFocusPainted(false);
    }
    
}
