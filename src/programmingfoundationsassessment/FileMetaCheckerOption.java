/**
 * Author: Dayle Angus
 * Location: Edinburgh College - Sighthill
 * Date: 29/10/18
 * Version: 1.0
 * Notes: n/a
 */

package programmingfoundationsassessment;

import ControlPanel.ControlPanel;
import ControlPanel.ControlPanelOption;

/**
 * FileMetaCheckerOption creates a sub menu which provides the user options to do various operations 
 * on file meta-data.
 * @author Dayle Angus
 */
public class FileMetaCheckerOption<T> extends ControlPanelOption<T> 
{
    // Declare Variables
    private final ControlPanel cPanel;

    /**
     * FileMetaChecker Constructor 
     * @param cPanel is required when creating new ControlPanelOption objects
     */
    public FileMetaCheckerOption(ControlPanel cPanel)
    {
        super(cPanel);
        this.cPanel = cPanel;
    }
    
    /**
     * start function is used by a control panel to begin the FileMetaCheckerOption
     */
    public @Override T start() 
    {
        // Create a new Control Panel
        ControlPanel metaCheckerCP = new ControlPanel(this.cPanel.getAdministrator(), "METACHECKER");
        
        // Create a string array which holds the names of the options displayed to the user
        String[] menuOptionNames = {"Open Directory with File Explorer", "Input File sizes to the Console", "Back"};
        
        // Create the options that the user will select from
        ControlPanelOption getFolderFromFileExplorer = new FileMetaChecker_GetFromDirOption(metaCheckerCP);
        ControlPanelOption getArrayOfSizes = new FileMetaChecker_GetFromArrayOption(metaCheckerCP);
        ControlPanelOption goBack = new GoBackOption(metaCheckerCP);
        
        // Place the options in an array which is required to build a menu
        ControlPanelOption[] menuOptions = {getFolderFromFileExplorer,
            getArrayOfSizes, goBack};
        
        // Use the control panel to build a menu
        metaCheckerCP.setMenuOptions(menuOptionNames, menuOptions);
        
        // Print the menu and allow the user to select an option
        metaCheckerCP.printMenuOptions();
        metaCheckerCP.selectMenuOption();
        
        // returns the menu of the control panel that was used to initialise this object
        recurMenu();
        return null;
    }
    
}
