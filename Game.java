/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes and Long Vinh Nguyen
 * @version 2018.1.30
 * 
 * /////////Modified 2018.1.16/////////////
 * Added new rooms
 * Added new exits to the outside room
 * 
 * /////////Modified 2018.1.23/////////////
 * Added new printLocationInfo private method to print out the current room information
 * Modified printWelcome and goRoom method to call the printLocationInfo method
 * Modified shoppingMall room to have no south exit to outside room
 * Deleted the following from the createRooms method: outside, theater, pub, lab, office
 * Modified goRoom method to have the option to go up and down
 * Modified the initiate rooms in the createRoom method to work with the new structure of
 *      room class
 * 
 * /////////Modified 2018.1.30/////////////
 * Added private "look" and "eat" methods
 * Modified the "processCommand" method to proccess the newly added command to
 *      execute its respective method in the Game(this) class
 * Modified the createRoom method to create the item in each room when the rooms are created
 * 
 * 

 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and and link their exits together.
     */
    private void createRooms()
    {
        // added rooms
        Room shoppingMall, foodStands, snacksAndDrinks, asianCuisins,
             gameArcade, clothingStore, shoesStore, fittingRoom;
      
           
        // additional rooms (add in as of 1-16-2018)
        shoppingMall    = new Room("a shopping center");
        foodStands      = new Room("section of the shopping center where all the food stand is at");
        snacksAndDrinks = new Room("food court selling popcorn and soft drinks");
        asianCuisins    = new Room("restaurant selling asian foods");
        gameArcade      = new Room("arcade game center");
        clothingStore   = new Room("store selling cloths and uniform");
        shoesStore      = new Room("store selling shoes");
        fittingRoom     = new Room("cloth fitting room");
        
        //create rooms' item
        shoppingMall.setItem("mall sign", 200);
        foodStands.setItem("ATM machine", 200);     
        snacksAndDrinks.setItem("popcorn", 1);
        asianCuisins.setItem("food sample", 2);   
        gameArcade.setItem("arcade machine", 150);     
        clothingStore.setItem("mannequin", 10);  
        shoesStore.setItem("shoes", 2);     
        fittingRoom.setItem("stool", 1);    
        
        
        
        //create rooms' exits
        shoppingMall.setExits("north", foodStands);
        shoppingMall.setExits("east", gameArcade);
        shoppingMall.setExits("west", clothingStore);
        
        foodStands.setExits("north", snacksAndDrinks);
        foodStands.setExits("east", asianCuisins); 
        foodStands.setExits("south", shoppingMall);
        
        snacksAndDrinks.setExits("south", foodStands);
        
        asianCuisins.setExits("west", foodStands);
        gameArcade.setExits("west", shoppingMall);
        
        clothingStore.setExits("north", fittingRoom);
        clothingStore.setExits("east", shoppingMall);
        clothingStore.setExits("west", shoesStore);
        
        shoesStore.setExits("east", clothingStore);
        fittingRoom.setExits("south", clothingStore);

        currentRoom = shoppingMall;  // start game at shopping mall
    }
    
       

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }
    

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("eat")) {
            eat();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
                

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    
    /** 
     * Print out the current room information including room
     * description and room's exits
     * 
     * Example
     * 
     * You are section of the shopping center where all the food stand is at
     * Exits: north east south
     */
    private void printLocationInfo() 
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * look
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * eat
     */
    private void eat()
    {
        System.out.println("You have eaten and you are not hungry anymore");
    }
    
    
    public void print()
    {
        String s = "a";
        
        if( s instanceof Object) {
            System.out.println(s instanceof Object);
        }
    }
}
