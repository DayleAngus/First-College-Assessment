/**
 * Author: Dayle Angus
 * Location: Edinburgh College - Sighthill
 * Date: 10/10/18
 * Version: 1.0
 * Notes: n/a
 */

package ControlPanel;

/**
 * An abstract class which allows Control Panel Options to be created and Operated from the Control Panel
 * @author Dayle Angus
 */
public abstract class ControlPanelOption 
{
    // Declare variables
    private final ControlPanel cPanel;
    
    /**
     * Constructor for the ControlPanelOption class requires a ControlPanel parameter
     * @param cPanel A ControlPanel in which to operate
     */
    public ControlPanelOption(ControlPanel cPanel)
    {
        this.cPanel = cPanel;
    }
    
    
    /**
     * abstract method to be overridden by the subclasses in order to be selected from the menu
     */
    public abstract void start();
    
    
    /**
     * Returns the console to the menu after the option is complete
     */
    public void recurMenu()
    {
        cPanel.printMenuOptions();
        cPanel.selectMenuOption();
    }
}