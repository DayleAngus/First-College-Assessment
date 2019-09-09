/**
 * Author: Dayle Angus
 * Location: Edinburgh College - Sighthill
 * Date: 10/10/18
 * Version: 1.0
 * Notes: n/a
 */

package programmingfoundationsassessment;

import ControlPanel.ControlPanel;
import ControlPanel.ControlPanelOption;

/**
 * Control Panel Option that quits the program
 * @author Dayle Angus
 */
public class QuitOption<T> extends ControlPanelOption
{
    //Declare Variables
    private final ControlPanel cPanel;
    
    
    /**
     * Constructor for QuitOption
     * @param cPanel Required to create new ControlPanelOptions
     */
    public QuitOption(ControlPanel cPanel) 
    {
        super(cPanel);
        this.cPanel = cPanel;
    }
    

    /**
     * Used from the ControlPanel to start the QuitOption
     */
    public @Override T start() 
    {
        System.exit(0);
        return null;
    }
    
}
