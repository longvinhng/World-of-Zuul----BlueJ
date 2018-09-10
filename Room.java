import java.util.HashMap;
import java.util.*;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes and Long Vinh Nguyen
 * @version 2018.01.23
 * 
 * /////////Modified 2018.1.23/////////////
 * Change all the public fields into private field
 * Created a public getExit method to provide user with a way to obtain the room's exits
 * Added up and down exits to rooms
 * Create a getRoomString method to out the list of room's exits
 * 
 * Removed all private exit field from the class and replace with a hashmap
 * Modified setExit method to accept only the direction of the exit and the exiting room 
 * Modified the getExitString method to work with the hash
 * 
 * Created getLongDescription public method to display room discription and its exits
 * 
 * /////////Modified 2018.1.30/////////////
 * Created placeRoomItem and showItemInfo method
 * Modified getLongDescription to display the information of the item in the room
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Item currentItem;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param direction of the exit
     * @param the room associate with that exit
     * 
     */
    public void setExits(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Return the room that is reached if we go from this room in that direction
     * "direction". If there is no room in that direction, return null
     * @para direction the user want to go
     * @return the room of the given direction
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

     /** 
     * Return a description of the room's exits
     * @return A description of the available exits direction
     */
    public String getExitString() 
    {
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        
        for(String exit : keys) {
            returnString += " " + exit;
        }
        
        return returnString;
    }
    
    /** 
     * Return a long description of this room in form of:
     *      You are at the kitchen 
     *      Exits: up down north west
     * @return a string of a description of the room and its exits
     * 
     */
    public String getLongDescription() 
    {
        return "You are at " + description + ".\n" 
                + showItemInfo() + ".\n" + getExitString();
    }
    
    /**
     * Placing an item into the room
     * 
     * @para item decription
     * @para item weight
     */
    public void setItem(String itemDescription, int weight)
    {
        currentItem = new Item(itemDescription, weight);        
    }
    
    /**
     * Return a string of text telling user what is the item in the room and its weight
     * @return string of text of item info
     * 
     * example "The room has 1 apple weighted 1 pounds.";
     */
    public String showItemInfo()
    {
        return "The room has 1 " + currentItem.getDescription() 
                    + " weighted " + currentItem.getWeight() + " pounds";  
    }    
}
