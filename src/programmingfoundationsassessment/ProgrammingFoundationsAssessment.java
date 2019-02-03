/**
 * Author: Dayle Angus
 * Location: Edinburgh College - Sighthill
 * Date: 31/10/18
 * Version: 1.0
 * Notes: n/a
 */
package programmingfoundationsassessment;
import ControlPanel.*;
import java.util.Scanner;
/**
 *
 * @author Dayle Angus
 */
public class ProgrammingFoundationsAssessment 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // Declare variables
        Administrator admin;
        Scanner scan;
        ControlPanel cPanel;
        ControlPanelOption[] cPanelOptions = new ControlPanelOption[4];        
        
        ControlPanelOption  fileMetaOption, 
                            generateUsernameOption, 
                            operationStatsOption,
                            quitOption;
        
        String[] menuOptionNames = {"Metadata checker",
                                    "Generate Username",
                                    "Operation Data",
                                    "Quit"};
        

        
        // Request The name of the Administrator
        System.out.print("Please Enter Administrator Name : ");
        
        //Initialise an Administrator
        while (true) 
        {            
            try 
            {
                scan = new Scanner(System.in);
                admin = Administrator.getInstance(scan.nextLine());
                break;
            } 
            catch (Administrator.AdministratorNamingException ex) 
            {
                System.out.println(ex.getMessage());
                System.out.print("Try Again  :  ");
            }
        }
        
        // Initialise a control panel
        cPanel = new ControlPanel(admin, "MAIN MENU");
        
        // Initialise control panel options
        fileMetaOption = new FileMetaCheckerOption(cPanel);
        generateUsernameOption = new GenerateUsernameOption(cPanel);
        operationStatsOption = new OperationDataOption(cPanel);
        quitOption = new QuitOption(cPanel);
        
        // fill cPanelOptions[] with initialised options
        cPanelOptions[0] = fileMetaOption;
        cPanelOptions[1] = generateUsernameOption;
        cPanelOptions[2] = operationStatsOption;
        cPanelOptions[3] = quitOption;
        
        // build a menu
        cPanel.setMenuOptions(menuOptionNames, cPanelOptions);
        
        // welcome the administrator
        cPanel.welcomeAdministrator();
        
        // Print a menu and allow the user to select from options
        cPanel.printMenuOptions();
        cPanel.selectMenuOption();
        
    }

    
    
}
