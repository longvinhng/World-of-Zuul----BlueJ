import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a game scenario including connected rooms and items
 * 
 * @author Cara Tang and Long Vinh Nguyen
 * @version 2018.02.27
 *
 *
 */
public class Scenario
{
    private ArrayList<Room> rooms;
    private Room startRoom;
    private Random random;

    /**
     * Constructor for objects of class Scenario
     */
    public Scenario()
    {
        random = new Random();
        
        // Set up your rooms, exits, and items
        // Move code from Game.createRooms here
        // added room to rooms array list
        Room shoppingMall, foodStands, snacksAndDrinks, asianFoods,
                gameArcade, clothingStore, shoesStore, fittingRoom;


        // additional rooms (add in as of 1-16-2018)
        shoppingMall    = new Room("a shopping center");
        foodStands      = new Room("section of the shopping center where all the food stand is at");
        snacksAndDrinks = new Room("food court selling popcorn and soft drinks");
        asianFoods    = new Room("restaurant selling asian foods");
        gameArcade      = new Room("arcade game center");
        clothingStore   = new Room("store selling cloths and uniform");
        shoesStore      = new Room("store selling shoes");
        fittingRoom     = new Room("cloth fitting room");

        //create rooms' item
        shoppingMall.setItem("mall sign", 200);
        foodStands.setItem("ATM machine", 200);
        snacksAndDrinks.setItem("popcorn", 1);
        asianFoods.setItem("food sample", 2);
        gameArcade.setItem("arcade machine", 150);
        clothingStore.setItem("mannequin", 10);
        shoesStore.setItem("shoes", 2);
        fittingRoom.setItem("stool", 1);



        //create rooms' exits
        shoppingMall.setExits("north", foodStands);
        shoppingMall.setExits("east", gameArcade);
        shoppingMall.setExits("west", clothingStore);

        foodStands.setExits("north", snacksAndDrinks);
        foodStands.setExits("east", asianFoods);
        foodStands.setExits("south", shoppingMall);

        snacksAndDrinks.setExits("south", foodStands);

        asianFoods.setExits("west", foodStands);
        gameArcade.setExits("west", shoppingMall);

        clothingStore.setExits("north", fittingRoom);
        clothingStore.setExits("east", shoppingMall);
        clothingStore.setExits("west", shoesStore);

        shoesStore.setExits("east", clothingStore);
        fittingRoom.setExits("south", clothingStore);


        startRoom = shoppingMall;  // start game at shopping mall


        // Create the rooms ArrayList and add all your rooms to it
        rooms = new ArrayList<>();

        rooms.add( shoppingMall);
        rooms.add( foodStands);
        rooms.add( snacksAndDrinks);
        rooms.add( asianFoods);
        rooms.add( gameArcade);
        rooms.add( clothingStore);
        rooms.add( shoesStore);
        rooms.add( fittingRoom);
    }


    /**
     * @return  the start room for this scenario
     */
    public Room getStartRoom()
    {
        return startRoom;
    }
    
    /**
     * @return  a random room from this scenario
     */
    public Room getRandomRoom()
    {
        //variable to identify the index number of the room inside the rooms array list
        int roomIndex;

        roomIndex = random.nextInt(rooms.size());
        return rooms.get(roomIndex);
    }
}
