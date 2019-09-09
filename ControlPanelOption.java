/**
 * Author: Dayle Angus
 * Location: Edinburgh College - Sighthill
 * Date: 10/10/18
 * Version: 1.0
 * Notes: n/a
 */

package ControlPanel;

import java.util.ArrayList;

/**
 * An abstract class which allows Control Panel Options to be created and Operated from the Control Panel
 * @author Dayle Angus
 * @param <T> return type of the option
 */
public abstract class ControlPanelOption <T>
{
    // Declare variables
    private final ControlPanel cPanel;
    private final ArrayList<T> optionResponseList;
    
    /**
     * Constructor for the ControlPanelOption class requires a ControlPanel parameter
     * @param cPanel A ControlPanel in which to operate
     */
    public ControlPanelOption(ControlPanel cPanel)
    {
        this.cPanel = cPanel;
        this.optionResponseList = new ArrayList<>();
    }
    
    public void addResponse(T response)
    {
        this.optionResponseList.add(response);
    }
    
    public ArrayList<T> getOptionResponseList()
    {
        return this.optionResponseList;
    }
    /**
     * abstract method to be overridden by the subclasses in order to be selected from the menu
     * @return 
     */
    public abstract T start();
    
    
    /**
     * Returns the console to the menu after the option is complete
     */
    public void recurMenu()
    {
        cPanel.printMenuOptions();
        cPanel.selectMenuOption();
    }
}