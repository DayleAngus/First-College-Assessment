/**
 * Author: Dayle Angus
 * Location: Edinburgh College - Sighthill
 * Date: 31/10/18
 * Version: 1.0
 * Notes: n/a
 */
package programmingfoundationsassessment;

import ControlPanel.ControlPanel;
import ControlPanel.ControlPanelOption;

/**
 * Allows the user to return to a previous menu
 * @author Dayle Angus
 */
public class GoBackOption<T> extends ControlPanelOption
{
    /**
     * Constructor for GoBackOption
     * @param cPanel is required when creating new ControlPanelOption objects
     */
    public GoBackOption(ControlPanel cPanel) 
    {
        super(cPanel);
    }

    /**
     * allows this option to be selected from a control panel
     */
    public @Override T start() 
    {
        /*
        This method is empty.
        Because it does not contain a recurMenu function it will return to the previous menu
        */
        return null;
    }
    
}
