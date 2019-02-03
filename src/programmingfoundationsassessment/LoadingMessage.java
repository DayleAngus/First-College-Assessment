/**
 * Author: Dayle Angus
 * Location: Edinburgh College - Sighthill
 * Date: 31/10/18
 * Version: 1.0
 * Notes: n/a
 */
package programmingfoundationsassessment;

/**
 * Displays a loading message to the user while operations are running
 * @author Dayle Angus
 */
public class LoadingMessage extends Thread
{
    // Declare Variables
    public boolean loading;
    
    @Override
    public void run()
    {
        // Display a loading message while loading is true
        loading = true;
        while(loading)
        {
            System.out.print("\rLoading ....................");
            System.out.print("\rLoading ");
            for(int i = 0; i < 20;i++)
            {
                if(loading)
                {
                    try 
                    {
                        System.out.print("|");
                        LoadingMessage.sleep(500);
                    } 
                    catch (InterruptedException ex) 
                    {
                        ex.getMessage();
                    }
                }
                else break;
            }
            System.out.print("\rLoading ");
            for(int i = 0; i < 20;i++)
            {
                if(loading)
                {
                    try 
                    {
                        System.out.print(".");
                        LoadingMessage.sleep(500);
                    } 
                    catch (InterruptedException ex) 
                    {
                        ex.getMessage();
                    }
                }
                else break;
            }
            
        }
        System.out.println("\rLoading Complete                                                                     ");
    }
}
