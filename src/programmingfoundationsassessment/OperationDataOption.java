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
 * A submenu which allows the user to select from a list of various options pertaining to operation times
 * @author Dayle Angus
 */
public class OperationDataOption extends ControlPanelOption
{
    // Declare Variables
    private final ControlPanel cPanel;
    
    /**
     * Constructor for OperationTimesOption
     * @param cPanel is required when creating new ControlPanelOption objects
     */
    public OperationDataOption(ControlPanel cPanel) 
    {
        super(cPanel);
        this.cPanel = cPanel;
    }

    /**
     * Allows this control panel option to be selected from a control panel
     */
    public @Override void start() 
    {
        // Declare variables
        ControlPanel operationTimesCPanel = new ControlPanel(this.cPanel.getAdministrator(), "OPERATION DATA");

        String[] optionNames = {"Operations", "Sorted Operations","Operation Statistics","Time Sorter"
                ,"Reset Operation Data","Back"};

        ControlPanelOption[] options = new ControlPanelOption[6];
        
        ControlPanelOption printOpTimes = new PrintAllOperationsOption(operationTimesCPanel);
        ControlPanelOption printSortedTimes= new PrintSortedOperationTimesOption(operationTimesCPanel);
        ControlPanelOption printOpStats = new PrintOperationStatisticsOption(operationTimesCPanel);
        ControlPanelOption timeSorter = new TimeSortingOption(operationTimesCPanel);
        ControlPanelOption resetOperationTimekeeper = new ResetOperationTimekeeperOption(operationTimesCPanel);
        ControlPanelOption back = new GoBackOption(operationTimesCPanel);

        // Add options to options array
        options[0] = printOpTimes;
        options[1] = printSortedTimes;
        options[2] = printOpStats;
        options[3] = timeSorter;
        options[4] = resetOperationTimekeeper;
        options[5] = back;
        
        // Build a menu
        operationTimesCPanel.setMenuOptions(optionNames, options);

        // Print the menu and allow the user to select an option
        operationTimesCPanel.printMenuOptions();
        operationTimesCPanel.selectMenuOption();
        
        // Recur the previous menu
        recurMenu();
    }
    
}
