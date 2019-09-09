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
import java.util.Arrays;
import java.util.Scanner;

/**
 * Allows the user to enter an array of integers for various operations to be performed
 * @author Dayle Angus
 */
public class FileMetaChecker_GetFromArrayOption<T> extends ControlPanelOption{

    // Declare Variables
    private final ControlPanel cPanel;
    private Scanner scan;
    
    /**
     * Constructor for FileMetaChecker_GetFromArrayOption
     * @param cPanel required to create ControlPanelOptions
     */
    public FileMetaChecker_GetFromArrayOption(ControlPanel cPanel) 
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
        String userInput;
        double[] fileSizes;
        String[] splitArray;
        double total = 0;
        long startTime, endTime;
        double operationTime;
        
        // Request user input
        System.out.println("\nEnter file sizes (In MegaBytes) using a comma to separate sizes");
        System.out.println("example     : 45.4, 500.32, 220, 32.66, 21.4..................................");
        System.out.print("Enter Here : ");
        scan = new Scanner(System.in);
        userInput = scan.nextLine();
        
        // Set the start time of the operation
        startTime = System.nanoTime();
        
        // split up the array into numerical segments for further operation
        splitArray = userInput.split("\\,");
      
        // set the file size array length equal to the amount of elements in the split array
        fileSizes = new double[splitArray.length];
        
        // iterate through the split array and fill the fileSizes[] with the numerical data entered by the user
        for(int i = 0; i < splitArray.length; i++)
        {
            fileSizes[i] = Double.valueOf(splitArray[i].trim());
        }
        
        // sort the array from smallest to largest
        Arrays.sort(fileSizes);
        
        // add all elements in fileSizes to a total
        for(double i : fileSizes)
        {
            total += i;
        }
        
        // Set the end time of the operation
        endTime = System.nanoTime();
        
        // Calculate the total time of the operation
        operationTime = endTime - startTime;
        
        // Add the operation to a timekeeper
        OperationTimekeeper.addNewOperation("Metadata statistics calculated from Console Entry ", 
                cPanel.getAdministratorName(), operationTime/1000000);
        
        // Print out the findings of the array
        System.out.println("\nNumber of files : " + fileSizes.length);
        System.out.println("The largest file is " + fileSizes[fileSizes.length - 1]+ "Mb");
        System.out.println("The total size of all files is " + total+ "Mb");
        System.out.println("The average file size is : " + (total/fileSizes.length)+ "Mb");
        System.out.println("Total time of Statistical Evaluation : " + (operationTime) + " Nanoseconds");
        recurMenu();
        return null;
    }
    
}
