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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * Allows the user to aquire statistics of file sizes within a directory using JFileChooser
 * @author Dayle Angus
 */
public class FileMetaChecker_GetFromDirOption<T> extends ControlPanelOption 
{
    // Declare variables
    private final ControlPanel cPanel;
    private final ArrayList<File> allFiles;

    
    /**
     * Constructor for FileMetaChecker_GetFromDirOption initialised cPanel and allFiles variables
     * @param cPanel is required when creating new ControlPanelOption objects
     */
    public FileMetaChecker_GetFromDirOption(ControlPanel cPanel) 
    {
        super(cPanel);
        this.cPanel = cPanel;
        allFiles = new ArrayList<>();
    }

    /**
     * Called from the control panel to begin operations
     */
    public @Override@SuppressWarnings("null") 
 T start() 
    {
        // Declare variables
        long[] fileSizes;
        long    totalSizeOfAllFiles,
                averageFileSize,
                largestFileSize,
                startTime, 
                endTime;
        double  operationTime;
        int largestFileIndex;
        LoadingMessage loadMessage = new LoadingMessage();
        File file;
        String largestFileName;
        
        //Clear the array list
        allFiles.clear();
        
        //Select a folder
        file = getDirectory();
        
        // Set start time at beginning of operation
        startTime = System.nanoTime();
        
        // Check if getDirectory returned null
        if(file != null)
        {
            // Display a loading message
            loadMessage.start();

            //Get a list of Every file under the directory
            addAllFilesToArrayList(file.listFiles());

            // Check if the directory has any files
            if(allFiles.isEmpty())
            {
                // Stop the loading message
                loadMessage.loading = false;

                // Loop until the loading message has stopped
                while(loadMessage.isAlive()){}

                System.out.println("\nDirectory has no files");

                // Go back to the menu which this option was selected from
                recurMenu();
            }


            //Get a list of sizes for every file
            fileSizes = getAllFileSizes();

            //Find the Largest File, the total size of all files and the average file size
            largestFileIndex = getlargestFileIndex(fileSizes);
            largestFileName = allFiles.get(largestFileIndex).getName();
            largestFileSize = fileSizes[largestFileIndex];
            totalSizeOfAllFiles = totalSizeOfFiles(fileSizes);
            averageFileSize = totalSizeOfAllFiles/fileSizes.length;

            //Stop the Loading Message
            loadMessage.loading = false;

            //Loop until the loading message has stopped
            while(loadMessage.isAlive()){}

            //Display the results of the analysis
            System.out.println("\nNumber of Files in " + file.getAbsoluteFile() + " : " + fileSizes.length);
            System.out.println("\nThe largest file in the Directory is " + largestFileName);
            System.out.println("The size of " + largestFileName + " is : " + largestFileSize + " Bytes");
            System.out.println("The total size of all files in the directory is " + totalSizeOfAllFiles + " Bytes");
            System.out.println("The average file size is : " + averageFileSize + " Bytes");

            // Calculate and display how long the operation has taken
            endTime = System.nanoTime();
            operationTime = endTime - startTime;
            System.out.println("Total time of Statistical Evaluation : " + operationTime + " Nanoseconds");

            // Add the operation to the Operation timekeeper for future analysis
            OperationTimekeeper.addNewOperation("Metadata statistics calculated from File Explorer", 
                    cPanel.getAdministratorName(), operationTime/1000000);   
        }

        // Go back to the menu which this option was selected from
        recurMenu();
        return null;
    }

    /**
     * Get a list of all files within a directory and all sub-directories
     * @param files The files within a directory
     */
    private void addAllFilesToArrayList(File[] files)
    {
        // Ckecks if the file array is empty
        if (files != null) 
        {
            // Loops through each file in a directory
            for (File f : files) 
            {
                try 
                {
                    // if the file is a file (a.k.a Not a directory) add the file to the array list of files
                    if (f.isFile()) 
                    {
                        allFiles.add(f);
                    } 
                    // if the file is a directory recall the function
                    else if (f.isDirectory()) 
                    {
                        addAllFilesToArrayList(f.listFiles());
                    }
                } 
                catch (Exception e) 
                {
                    System.out.println("Unable to aquire metadata on File : " + f.getAbsoluteFile());
                }
            }
        }
    }
    
    /**
     * Uses JFileChooser to allow a user to select a directory in which further operations can be performed
     * @return Returns the users selected directory
     */
    private File getDirectory()
    {
        // Declare variables
        JFrame frame = new JFrame();
        JFileChooser chooser = new JFileChooser();
        File usersSelectedFile;
        
        // Alter JFileChooser settings
        
        // Open the file chooser to the computers C drive
        chooser.setCurrentDirectory(new File("C:\\"));
        // Set the title of the file chooser
        chooser.setDialogTitle("Select a folder");
        // Allow the file chooser to only show directories
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // Disable the users ability to select files that are not directories
        chooser.setAcceptAllFileFilterUsed(false);
        // Set the file choosers visibilty
        chooser.setVisible(true);
        
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        
        // Open the filechooser and set usersSelectedFile as the chosen file or null if no file was selected
        if(chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
        {
            usersSelectedFile = chooser.getSelectedFile();
            
        }
        else 
        {
            usersSelectedFile = null;
        }
        
        frame.setVisible(false);
        
        return usersSelectedFile;
    }
    
    
    /**
     * Iterates through the allFiles array list and returns an array which holds the size of each file at a matching
     * index to allFiles
     * @return Returns an array of file sizes
     */
    private long[] getAllFileSizes()
    {
        // declare variables
        File file;
        long[] fileSizes = new long[allFiles.size()];
        BasicFileAttributes attr;
        Path filePath;
        
        /* 
        *  iterate through all of the elements in the file sizes array and fill with sizes retreived from the
        *  coresponding index of allFiles
        */
        for(int i = 0; i < fileSizes.length; i++)
        {
            try 
            {
                file = allFiles.get(i);
                filePath = Paths.get(file.getAbsolutePath());
                attr = Files.readAttributes(filePath, BasicFileAttributes.class);
                fileSizes[i] = attr.size();
            } 
            catch (IOException iOException) {}
        }
        return fileSizes;
    }
    
    /**
     * Finds the largest integer in an array
     * @param fileSizes an array of file sizes which is to be checked for the largest file
     * @return The index of the largest file size within fileSizes[]
     */
    private int getlargestFileIndex(long[] fileSizes)
    {
        // Declare variables
        int maxIndex = 0;
        long maxSize = 0;
        
        /*
        * iterate through the fileSizes array and check if the element is larger than the largest so far
        * if it is the largest element so far set max size and max integer as the new highest element
        */
        for(int i = 0; i < fileSizes.length; i++)
        {
            if(fileSizes[i] > maxSize)
            {
                maxIndex = i;
                maxSize = fileSizes[i];
            }
        }
        return maxIndex;
    }
    
    /**
     * Add up all elements within a long[]
     * @param allSizes the array in which a total is to be calculated
     * @return the total of all elements within an array
     */
    private long totalSizeOfFiles(long[] allSizes)
    {
        // declare variables
        long total = 0;
        
        // iterate through each array element and add it to a total
        for(long l : allSizes)
        {
            total += l;
        }
        
        return total;
    }
}
