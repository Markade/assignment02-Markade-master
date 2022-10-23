package mvr.Framework;

import javax.swing.JButton;
import mvr.Interface.Clearable;

/**
 * @author Mark Windler (12143085)
 * Date: 22/09/2020
 * Purpose: Custom clear button for Clearable JFrame fields and selections 
 */
public class ClearButton extends JButton {

    /**
     *      Constructor
     *      @param clearableJFrame The Clearable JFrame
     */
    public ClearButton(Clearable clearableJFrame)
    {
        super("Clear Fields");
        this.addActionListener(e -> 
        {
            clearableJFrame.clear();
        });
        this.setFocusPainted(false);
    }
    
}
