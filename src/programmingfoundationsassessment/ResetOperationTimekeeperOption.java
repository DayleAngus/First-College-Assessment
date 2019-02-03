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
import java.util.Scanner;

/**
 * Allows the user to reset the operation timekeeper
 * @author Dayle Angus
 */
public class ResetOperationTimekeeperOption extends ControlPanelOption
{
    /**
     * Constructor for ResetOperationTimekeeperOption
     * @param cPanel is required when creating new ControlPanelOption objects
     */
    public ResetOperationTimekeeperOption(ControlPanel cPanel) 
    {
        super(cPanel);
    }

    /**
     *  Allows this option to be selected from a control panel
     */
    public @Override void start() 
    {
        // Declare variables
        Scanner scan;
        String userInput;
        boolean userWantsToReset = false;
        
        // Check if the users selection was correct
        while (true) 
        {            
            System.out.print("\nAre you sure you want to reset the operation list [Y/N] : ");
            scan = new Scanner(System.in);
            userInput = scan.nextLine();
            if(userInput.trim().equalsIgnoreCase("y")||userInput.trim().equalsIgnoreCase("n"))
            {
                if(userInput.trim().equalsIgnoreCase("y"))
                {
                    userWantsToReset = true;
                }
                break;
            }
            System.out.println("\nInvalid Input : Please enter Y for yes or N for no");
            
        }
        
        // If the users selection was correct - reset the operation timekeeper
        if (userWantsToReset) 
        {
            OperationTimekeeper.resetOperationTimekeeper();
            System.out.println("\nOperation List has been reset\n");
        }
        
        // recur the menu that this option was called from 
        recurMenu();
    }
    
}
