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
 * Allows the sorting of a list of operation times entered by the user
 * @author Dayle Angus
 */
public class TimeSortingOption extends ControlPanelOption
{
    // Declare variables
    private Scanner scan;
    private final ControlPanel cPanel;
    
    /**
     * Constructor for TimeSortingOption
     * @param cPanel is required when creating new ControlPanelOption objects
     */
    public TimeSortingOption(ControlPanel cPanel) 
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
        String userInput;
        int[] times;
        int[] sortedTimes;
        String[] splitArray;
        long startTime, endTime;
        double operationTime;
        
        // Option title
        System.out.println("\nTIME SORTER\n");
        
        // Request user input
        System.out.println("\nEnter Operation times(in seconds) using a comma to separate integers");
        System.out.println("example     : 45, 500, 220, 32, 21..................................");
        System.out.print("Enter Here : ");
        scan = new Scanner(System.in);
        userInput = scan.nextLine();
        
        // initialise a start time
        startTime = System.nanoTime();
        
        // Split the user input into elements
        splitArray = userInput.split("\\,");
      
        // initialise the times array
        times = new int[splitArray.length];
        
        // Fill the array with the user input elements
        for(int i = 0; i < splitArray.length; i++)
        {
            times[i] = Integer.valueOf(splitArray[i].trim());
        }
        
        // sort the times array
        sortedTimes = bubbleSortAsc(times);
        
        // Print the sorted operation times
        System.out.println("\nSorted Operation times : ");
        
        for(int i = 0; i < (sortedTimes.length-1);i++)
        {
            System.out.print(sortedTimes[i] + " , ");
        }
        System.out.println(sortedTimes[sortedTimes.length-1]);
        
        endTime = System.nanoTime();
        
        operationTime = endTime - startTime;
        
        OperationTimekeeper.addNewOperation("Sorting operation times using Console Input", 
                this.cPanel.getAdministratorName(), operationTime/1000000);
        
        System.out.println("\nNumber of Operations : " + sortedTimes.length);
        System.out.println("The Longest Operation is " + sortedTimes[sortedTimes.length - 1]+ " Seconds");
        System.out.println("Total time to Sort : " + operationTime + " Nanoseconds");
        recurMenu();
    }
    
    /**
     * Sorts an integer array from smallest to largest using the bubble sort algorithm
     * @param array the array to be sorted
     * @return returns the sorted array
     */
    private static int[] bubbleSortAsc(int[] array)
    {
        int temp;
        for(int i = 0; i < array.length - 1; i++)
        {
            for(int j = 1; j < array.length - i; j++)
            {
                if(array[j - 1] > array[j])
                {
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
    
}
