/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes and Long Vinh Nguyen
 * @version 2018.01.30
 * 
 * /////////Modified 2018.1.30/////////////
 * Added "look" and "eat" commands in the array of valid commands
 * Added "showAll" method to print out the list of valid commands
 * Modified "showAll" method to return a string of valid commands
 * Renamed the "showAll" method to getCommandList
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "eat"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    /**
     * Print all the valid command to System.out
     */
    public String getCommandList()
    {
        String commandList = "";
        
        for(String command : validCommands) {
            commandList += command;
            commandList += " ";
        }
        
        return commandList;
                  
    } 
}
