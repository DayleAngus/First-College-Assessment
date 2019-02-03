/**
 * Author: Dayle Angus
 * Location: Edinburgh College - Sighthill
 * Date: 10/10/18
 * Version: 1.0
 * Notes: n/a
 */
package ControlPanel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A singleton class which creates an administrator
 * @author Dayle Angus
 */
public final class Administrator 
{
    //Declare Variables
    private final String AdminName;
    private static Administrator AdminInstance = null;
    
    
    /**
     * Administrator Constructor Requires the name of the administrator
     */
    private Administrator(String name)
    {
        this.AdminName = name;
    }

    
    /**
     * Returns an instance of Administrator
     * @param administratorName the name of the administrator
     * @return an instance of Administrator if there is not already an instance of Administrator
     * @throws assessmentone.Administrator.AdministratorInstanceException if there is already an instance of Administrator
     * @throws assessmentone.Administrator.AdministratorNamingException if String administratorName is empty 
     */
    public static Administrator getInstance(String administratorName) throws AdministratorInstanceException, AdministratorNamingException
    {
        if(AdminInstance == null)
        {
            
            if ((!administratorName.isEmpty()) && (!stringContainsIllegalCharacters(administratorName))) 
            {
                AdminInstance = new Administrator(administratorName);
                return AdminInstance;
            } 
            else 
            {
                throw new AdministratorNamingException();
            }
        }
        else
        {
            throw new AdministratorInstanceException();
        }
    }
    
    
    /**
     * Checks for Illegal Characters
     * @param string a string to be checked for illegal characters
     * @return true if string contains illegal characters
     */
    private static boolean stringContainsIllegalCharacters(String string)
    {
        Pattern pattern = Pattern.compile("[^a-zA-Z\\s]");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    
    /**
     *
     * @return the name of the Administrator
     */
    public String getName()
    {
        return this.AdminName;
    }
    
    
    /**
     * AdministratorNamingException is thrown if the Parameter Adminname is empty or contains Illegal characters
     */
    public static class AdministratorNamingException extends RuntimeException {

        public AdministratorNamingException() 
        {
            super("Administrator must be named : No numbers or non-alphanumeric characters are permitted");
        }
    }

    /**
     * AdministratorInstanceException is thrown when an Instance of Administrator already exists
     */
    public static class AdministratorInstanceException extends RuntimeException {

        public AdministratorInstanceException() 
        {
            super("Administrator Instance Already Exists : Administrator Class cannot have more than one instance");
        }
    }
}
