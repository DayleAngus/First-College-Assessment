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
 * Prints statistics found by the operation timekeeper
 * @author Dayle Angus
 */
public class PrintOperationStatisticsOption<T> extends ControlPanelOption
{
    /**
     * Constructor for printOperationStatisticsOption
     * @param cPanel is required when creating new ControlPanelOption objects
     */
    public PrintOperationStatisticsOption(ControlPanel cPanel) 
    {
        super(cPanel);
    }

    /**
     * Allows this option to be selected from a control panel
     */
    public @Override T start() 
    {
        // Option title
        System.out.println("\nOPERATION STATISTICS\n");
        
        // If there is operations stored in the operation timekeeper print statistics that it can generate
        if (OperationTimekeeper.getNumberOfOperationsPerformed() != 0) 
        {
            System.out.println("Total Number Of Operations Performed : " 
                    + OperationTimekeeper.getNumberOfOperationsPerformed());
            
            System.out.println(OperationTimekeeper.getTotalTime());
            System.out.println(OperationTimekeeper.getLongestTime());
        }
        else
        {
            System.out.println("No Operation data. Please Try Again Later.");
        }
        
        // recur the menu that this option was called from
        recurMenu();
        return null;
    }
    
}
