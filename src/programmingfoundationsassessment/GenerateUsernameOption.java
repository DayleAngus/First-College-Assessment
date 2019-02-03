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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * GenerateUsernameOption takes an employees name and returns a username 
 * using the first letter of the first name and the last name
 * @author Dayle Angus
 * @version 1.1
 */
public class GenerateUsernameOption extends ControlPanelOption 
{
    //Declare Variables
    private final ControlPanel cPanel;
    private String firstName;
    private String secondName;
    private Scanner scan;
    
    
    /**
     * Constructor for GenerateUsernameOption
     * @param cPanel required for creating new ControlPanelOption
     */
    public GenerateUsernameOption(ControlPanel cPanel)
    {
        super(cPanel);
        this.cPanel = cPanel;
    }
    
    

    /**
     * Requests a staff name from the user
     */
    private void setFullName() 
    {
        
        System.out.print("\nWhat is the Staff Members First Name and Surname?  :  ");
        scan = new Scanner(System.in);
        firstName = scan.next();            
        secondName = scan.next();
        while(stringContainsIllegalCharacters(firstName)|| stringContainsIllegalCharacters(secondName))
        {
            System.out.println("Staff Name must constist only of Letters or hyphens(-)");
            System.out.print("Try Again : ");
            firstName = scan.next();
            secondName = scan.next();
        }
    }
    
    
    /**
     * Checks for Illegal Characters
     * @param string a string to be checked for illegal characters
     * @return true if string contains illegal characters
     */
    private boolean stringContainsIllegalCharacters(String string)
    {
        Pattern pattern = Pattern.compile("[^a-zA-Z-]");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
    
    /**
     * Prints the staff member's Username to the console
     */
    private void getUsername() 
    {
        System.out.println("\nUsername  :  " + username());
    }
    
    /**
     * Generates a username from the staff members first and second names
     * @return A staff username
     */
    private String username() 
    {
        char[] firstNameasCharArray = this.firstName.toCharArray();
        char[] secondNameAsCharArray = this.secondName.toCharArray();
        char[] subSecondName = new char[secondNameAsCharArray.length - 1];
        
        for(int i = 0;i<subSecondName.length;i++)
        {
            subSecondName[i] = Character.toLowerCase(secondNameAsCharArray[i+1]);
        }
        String username = "" + Character.toUpperCase(firstNameasCharArray[0]) 
                + Character.toUpperCase(secondNameAsCharArray[0]) + new String(subSecondName);
        
        return username;
    }
    

    /**
     * Used from the ControlPanel to start the GenerateUsernameOption
     */
    public @Override void start() 
    {
        long startTime, endTime;
        double operationTime;
        
        setFullName();
        
        startTime = System.nanoTime();
        
        getUsername();
        
        endTime = System.nanoTime();
        
        operationTime = endTime - startTime;
        
        System.out.println("Total time to Generate Username : " + operationTime + " NanoSeconds");
        
        OperationTimekeeper.addNewOperation("Generate a Username", 
                cPanel.getAdministratorName(), operationTime/1000000);

        recurMenu();

    }
    
}
