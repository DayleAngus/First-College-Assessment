/**
 * Author: Dayle Angus
 * Location: Edinburgh College - Sighthill
 * Date: 31/10/18
 * Version: 1.0
 * Notes: n/a
 */
package programmingfoundationsassessment;

import java.util.ArrayList;


/**
 * Static utility that allows the storing of operation times and descriptions
 * @author Dayle Angus
 */
public class OperationTimekeeper
{
    // Declare Variables
    private static final ArrayList<String> DESCRIPTIONOFOPERATION = new ArrayList<>();;
    private static final ArrayList<String> OPERATIONSPERFORMEDBY = new ArrayList<>();
    private static final ArrayList<Double> TOTALTIMEOFOPERATIONS = new ArrayList<>();
    
    // Private constructor to prevent initialisation of objects
    private OperationTimekeeper(){}
    
    /**
     * Adds an operation to the Operation Timekeeper
     * @param operationDescription a description of the operation performed
     * @param performedBy the name of the user
     * @param totalTimeInMillis the time taken by the operation
     */
    public static void addNewOperation(String operationDescription, String performedBy, double totalTimeInMillis)
    {
        OperationTimekeeper.DESCRIPTIONOFOPERATION.add(operationDescription);
        OperationTimekeeper.OPERATIONSPERFORMEDBY.add(performedBy);
        OperationTimekeeper.TOTALTIMEOFOPERATIONS.add(totalTimeInMillis);
        
    }
    
    /**
     * 
     * @return returns a list of all operations stored in the operation timekeeper
     */
    public static String[] getAllResults()
    {
        String[] allResults = new String[DESCRIPTIONOFOPERATION.size()];
        for(int i = 0; i < DESCRIPTIONOFOPERATION.size();i++)
        {
            allResults[i] = DESCRIPTIONOFOPERATION.get(i) + " \nPerformed by : "+ 
            OPERATIONSPERFORMEDBY.get(i)+ " \nTotal time of Operation : "+ 
            TOTALTIMEOFOPERATIONS.get(i) + " Milliseconds\n";
        }
        return allResults;
    }
    
    /**
     * resets the operation timekeeper
     */
    public static void resetOperationTimekeeper()
    {
        DESCRIPTIONOFOPERATION.removeAll(DESCRIPTIONOFOPERATION);
        OPERATIONSPERFORMEDBY.removeAll(OPERATIONSPERFORMEDBY);
        TOTALTIMEOFOPERATIONS.removeAll(TOTALTIMEOFOPERATIONS);
    }
    
    /**
     * 
     * @return returns the number of operations stored in the operation timekeeper
     */
    public static int getNumberOfOperationsPerformed()
    {
        return DESCRIPTIONOFOPERATION.size();
    }
    
    /**
     *
     * @return returns the total time taken for every operation stored in the operation timekeeper
     */
    public static String getTotalTime()
    {
        double total = 0;
        for(double d : TOTALTIMEOFOPERATIONS)
        {
            total += d;
        }
        return "\nTotal Time of Operations : " + total + " Milliseconds";
    }
    
    /**
     *
     * @return returns the longest operation stored in the operation timekeeper
     */
    public static String getLongestTime()
    {
        // Declare variables
        double longestTime = 0;
        String descriptionOfOperation = "";
        String performedBy = "";
        
        // Iterate through all elements within the Operation timekeeper and set the longest time
        for(int i = 0; i < TOTALTIMEOFOPERATIONS.size(); i++)
        {
            if(TOTALTIMEOFOPERATIONS.get(i) > longestTime)
            {
                longestTime = TOTALTIMEOFOPERATIONS.get(i);
                descriptionOfOperation = DESCRIPTIONOFOPERATION.get(i);
                performedBy = OPERATIONSPERFORMEDBY.get(i);
            }
        }
        
        return "\nLongest Operation : \n" + descriptionOfOperation + "\nPerformed by : " + performedBy + 
                "\nTotal time of Operation : " + longestTime + " Milliseconds";
        
    }
    
    /**
     *
     * @return returns a sorted array of all operations stored in the operation timekeeper
     */
    public static String[] getSortedResults()
    {
        // Declare variables
        double [] sortedTimes = new double[DESCRIPTIONOFOPERATION.size()];
        String [] sortedNames = new String[DESCRIPTIONOFOPERATION.size()];
        String [] sortedPerformedBy = new String[DESCRIPTIONOFOPERATION.size()];
        String [] allSortedResults = new String[DESCRIPTIONOFOPERATION.size()];
        double currentTime;
        String currentName;
        String currentOperator;
        double tempTime;
        String tempName;
        String tempOperator;
        
        // Fill sortedTimes[] with max values
        for(int i = 0; i < sortedTimes.length; i++)
        {
            sortedTimes[i] = Double.MAX_VALUE;
        }
        
        // Add the elements of the OperationTimekeeper to sorted arrays
        for(int i = 0; i < DESCRIPTIONOFOPERATION.size();i++)
        {
            currentTime = TOTALTIMEOFOPERATIONS.get(i);
            currentName = DESCRIPTIONOFOPERATION.get(i);
            currentOperator = OPERATIONSPERFORMEDBY.get(i);
            
            //check from index 0 till end of array if its larger
            for(int j = 0;j < sortedTimes.length; j++)
            {
                if(sortedTimes[j] >= currentTime)
                {
                    tempTime = currentTime;
                    tempName = currentName;
                    tempOperator = currentOperator;
                    
                    currentTime = sortedTimes[j];
                    currentName = sortedNames[j];
                    currentOperator = sortedPerformedBy[j];
                    
                    sortedTimes[j] = tempTime;
                    sortedNames[j] = tempName;
                    sortedPerformedBy[j] = tempOperator;
                    
                }
            }
            
        }
        for(int i = 0; i <sortedTimes.length;i++)
        {
            allSortedResults[i] = sortedNames[i] + "\nPerformed by : " + sortedPerformedBy[i] + 
                    "\nTotal time of Operation : " + sortedTimes[i] + " Milliseconds\n";
        }
        return allSortedResults;
    }
}
