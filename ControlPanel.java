/**
 * Author: Dayle Angus
 * Location: Edinburgh College - Sighthill
 * Date: 10/10/18
 * Version: 1.0
 * Notes: n/a
 */

package ControlPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * ControlPanel class creates a menu containing options that can be selected by the user
 * @author Dayle Angus
 */
public class ControlPanel 
{
    //Declare Variables
    private final Administrator administrator;
    private ArrayList<String> menuOptionNames = null;
    private byte userSelectedOption;
    private static Scanner scan;
    private ArrayList<ControlPanelOption> menuOptions = null;
    private final String nameOfMenu;
    private boolean stackTrace;
    
    
    /**
     * ControlPanel constructor creates a ControlPanel object which allows a menu to be constructed and displayed
     * @param admin Administrator is a required parameter for Constructing ControlPanel Objects
     */
    public ControlPanel(Administrator admin)
    {
        this.administrator = admin;
        this.nameOfMenu = "MENU";
        this.stackTrace = false;
    }

    /**
     *
     * @param admin
     * @param menuName
     */
    public ControlPanel(Administrator admin, String menuName)
    {
        this.administrator = admin;
        this.nameOfMenu = menuName;
        this.stackTrace = false;
    }
    
    
    /**
     * setMenuOptions will initialise a menu for the ControlPanel Object if both array parameters are the same length
     * Options should have matching indexes e.g. optionNames[0] should point to the ControlPanelOption[0] that will be accessed when the user selects it
     * 
     * The menu will display [index+1] e.g optionNames[0] will be displayed as Option 1
     * @param optionNames will be displayed in the form "Option " + the option number + "  :  " optionName
     * @param options accessible to the user through the input of the array index + 1
     * @throws ControlPanel.MenuNotSupportedException is thrown when the array parameters are not the same length
     */
    public void setMenuOptions(String[] optionNames,ControlPanelOption[] options) throws MenuNotSupportedException
    {
        if (optionNames.length == options.length) 
        {
            this.menuOptionNames = new ArrayList<>(Arrays.asList(optionNames));
            this.menuOptions = new ArrayList<>(Arrays.asList(options));
        } 
        else 
        {
            throw new MenuNotSupportedException();
        }
    }
    
    
    /**
     * OUTPUT - outputs the menu options using System.out to print to a console
     * Menu is printed in the form 
     * \n    MENU
     * "Option " + the option number + "  :  " optionName
     * @throws ControlPanel.MenuNotInitialisedException if the menu has not been initialised
     */
    public void printMenuOptions() throws MenuNotInitialisedException
    {
        if (this.menuOptionNames != null) 
        {
            System.out.println("\n" + this.nameOfMenu);
            for (int option = 1; option <= this.menuOptionNames.size(); option++)
            {
                System.out.println("Option " + (option) + "  :  " + this.menuOptionNames.get(option-1));
            }
        } 
        else 
        {
            throw new MenuNotInitialisedException();
        }
    }
    
    
    /**
     * Welcomes the administrator in the form 
     * "\nWelcome " + [AdministratorName]
     */
    public final void welcomeAdministrator() 
    {
        System.out.println("\nWelcome " + getAdministratorName());
    }
    
    
    /**
     * 
     * @return Returns the ControlPanel Administrators name
     */
    public final String getAdministratorName()
    {
        return administrator.getName();
    }
    
    /**
     *
     * @return
     */
    public final Administrator getAdministrator()
    {
        return this.administrator;
    }
    
    
    /**
     * Uses the Scanner(System.in) Utility to request that the user pick an option from the menu
     * @throws ControlPanel.MenuNotInitialisedException if the menu has not been initialised
     */
    public final void selectMenuOption() throws MenuNotInitialisedException
    {
        if (this.menuOptionNames != null) 
        {
            System.out.print("Select Option  :  ");
            while (true) 
            {            
               try 
                {
                    scan = new Scanner(System.in);
                    this.userSelectedOption = scan.nextByte();
                    this.menuOptions.get(userSelectedOption - 1).start();
                    break;
                } 
                catch (Exception e) 
                {
                    System.out.println("\nSOMETHING WENT WRONG : Exception Type = " + e.getClass());
                    if(this.stackTrace)e.printStackTrace();
                    printMenuOptions();
                    System.out.print("Select Option  :  ");
                }
            }
        } 
        else 
        {
            throw new MenuNotInitialisedException();
        }
    }
    
    
    /**
     * Adds a new Menu Option to the menu
     * @param optionName will be displayed in the form "Option " + the option number + "  :  " optionName
     * @param option accessible to the user through the input of the array index + 1
     * @throws ControlPanel.MenuNotInitialisedException if the Menu has not been initialised
     */
    public final void addMenuOption(String optionName,ControlPanelOption option) throws MenuNotInitialisedException
    {
        if (this.menuOptionNames != null) 
        {
            this.menuOptionNames.add(optionName);
            this.menuOptions.add(option);
        } 
        else 
        {
            throw new MenuNotInitialisedException();
        }
        
    }
    
    
    /**
     * Removes a Menu Option (option and optionName)
     * @param option The option that is to be removed 
     * @throws ControlPanel.MenuNotInitialisedException if the menu has not been initialised
     */
    public final void removeMenuOption(ControlPanelOption option) throws MenuNotInitialisedException
    {
        if (this.menuOptionNames != null) 
        {
            int optionIndex = getMenuArrayIndex(option);
            this.menuOptionNames.remove(optionIndex);
            this.menuOptions.remove(optionIndex);
        } 
        else 
        {
            throw new MenuNotInitialisedException();
        }
        
    }
    
    
    /**
     * Removes an Option from the menu (option and optionName)
     * @param index The index of the Option to be removed - From the menu printout Option 1 would be index 0
     * @throws ControlPanel.MenuNotInitialisedException if the menu has not been initialised
     */
    public final void removeMenuOption(int index) throws MenuNotInitialisedException
    {
        if (this.menuOptionNames != null) 
        {
            this.menuOptionNames.remove(index);
            this.menuOptions.remove(index);
        } 
        else 
        {
            throw new MenuNotInitialisedException();
        }
        
    }
    
    
    /**
     * 
     * @param option The option which index is to be queried
     * @return Returns the Index of the option from the menu array e.g. Option 1 in the menu will return 0
     * @throws ControlPanel.MenuNotInitialisedException if the menu has not been initialised
     */
    public final int getMenuArrayIndex(ControlPanelOption option) throws MenuNotInitialisedException
    {
        if (this.menuOptionNames != null) 
        {
            return this.menuOptions.indexOf(option);
        } 
        else 
        {
            throw new MenuNotInitialisedException();
        }
        
    }
    
    /**
     * Prints an identification statement using System.out.println 
     * The identification statement is in the form "\nThis is Option " + (OptionIndex+1) + " " + (the console administrators name)
     * @param option Should be passed the parameter (this) by the ControlPanelOption
     */
    public final void identifyChoice(ControlPanelOption option) 
    {
        System.out.println("\nThis is Option " + (menuOptions.indexOf(option)+1) + " " + getAdministratorName());
    }

    /**
     *
     * @param setter
     */
    public void setStackTrace(boolean setter)
    {
        this.stackTrace = setter;
    }
    /**
     * Is thrown if there is an error in the Initialisation of the menu
     */
    public static class MenuNotSupportedException extends RuntimeException 
    {
        public MenuNotSupportedException() 
        {
            super("This Menu is not Supported : Please make sure both parameters are not empty and are the same length");
        }
    }

    
    /**
     * Is thrown if the menu has not been initialised
     */
    public static class MenuNotInitialisedException extends RuntimeException {

        public MenuNotInitialisedException() 
        {
            super("Menu options not initialised");
        }
    }

}
