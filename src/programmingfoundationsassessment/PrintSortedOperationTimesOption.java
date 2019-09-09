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
 * Print a sorted list of operations stored in the operation timekeeper
 * @author Dayle Angus
 */
public class PrintSortedOperationTimesOption<T> extends ControlPanelOption
{
    // Declare Variables
    private final ControlPanel cPanel;
   
    /**
     * Constructor for PrintSortedOperationTimesOption
     * @param cPanel required to construct a new ControlPanelOption
     */
    public PrintSortedOperationTimesOption(ControlPanel cPanel) 
    {
        super(cPanel);
        this.cPanel = cPanel;
    }

    /**
     * Allows this option to be selected from a control panel
     */
    public @Override T start() 
    {
        // Declare variables
        long startTime, endTime;
        double operationTime;
        String[] operationList;
        
        // Option title
        System.out.println("\nSORTED LIST OF OPERATIONS\n");
        
        // If there is operations stored in the operation timekeeper print a sorted list of operations
        if(OperationTimekeeper.getNumberOfOperationsPerformed() != 0)
        {
            startTime = System.nanoTime();
            
            operationList = OperationTimekeeper.getSortedResults();
            for(String s : operationList)
            {
                System.out.println(s);
            }
            
            endTime = System.nanoTime();
        
            operationTime = endTime - startTime;
        
            OperationTimekeeper.addNewOperation("Print a sorted list of operation times ", 
                cPanel.getAdministratorName(), operationTime/1000000);
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