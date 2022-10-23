package mvr.Interface;

import javax.swing.JFrame;

/**
 * @author Mark Windler (12143085)
 * Date: 23/09/2020
 * Purpose: Allows JFrame to re-initialize, helps with back button usage
 */
public interface FramePage extends Clearable {
    void initialize(JFrame previousPage);
}
