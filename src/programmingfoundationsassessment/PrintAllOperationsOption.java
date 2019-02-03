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
 * Prints a list of all operations stored in the Operation Timekeeper
 * @author Dayle Angus
 */
public class PrintAllOperationsOption extends ControlPanelOption
{
    // Declare Variables
    private String[] operationList;
    private final ControlPanel cPanel;
    
    /**
     * Constructor for PrintAllOperationOption
     * @param cPanel required to Construct control panel options
     */
    public PrintAllOperationsOption(ControlPanel cPanel) 
    {
        super(cPanel);
        this.cPanel = cPanel;
    }

    /**
     * Allows this option to be selected from a control panel
     */
    public @Override void start() 
    {
        // Declare variables
        long startTime, endTime;
        double operationTime;
        
        // Option title
        System.out.println("\nOPERATION TIMES\n");
        
        // If there is operations stored in the operation timekeeper print the operations
        if(OperationTimekeeper.getNumberOfOperationsPerformed() != 0)
        {
            startTime = System.nanoTime();
            operationList = OperationTimekeeper.getAllResults();
            for(String s : operationList)
            {
                System.out.println(s);
            }
            endTime = System.nanoTime();
        
            operationTime = endTime - startTime;
        
            OperationTimekeeper.addNewOperation("Printed a list of Operation Times", 
                cPanel.getAdministratorName(), operationTime/1000000);
        }
        else
        {
            System.out.println("No operation Data. Please Try again Later.");
        }
        
        // Recur the menu that this option was called from
        recurMenu();
    }
    
}
