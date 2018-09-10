
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * An "Item" represents an item placed in each room of the game.
 * Each item has a description and weight.
 *
 * @author LongVinh Nguyen
 * @version 2018.1.30
 * 
 * Added item class constructor
 * Added setter and getter method for item weight
 * Added getter method for item description
 * 
 * 
 * -------NOTES--------
 * Item weight is in pounds
 * 
 */
public class Item
{
    private String description;
    private int weight;
    

    /**
     * Default constructor for creating an item object
     * the user must supply the item description and weight
     * 
     * @para description
     * @para weight
     * 
     * example Item(apple, 0.25);
     * 
     */
    public Item(String itemDescription, int itemWeight)
    {
        setDescription(itemDescription);
        setWeight(itemWeight);
    }

    
    /**
     * setter (mutator) method for modified the description of an item object
     *
     * @param  user supplies item weight
     */
    public void setDescription(String itemDescription)
    {
        description = itemDescription;
    }
    
    
    /**
     * setter (mutator) method for modified the weight of an item object
     *
     * @param  user supplies item weight
     */
    public void setWeight(int itemWeight)
    {
        weight = itemWeight;
    }
    
    /**
     * getter method for accessing the weight of an item object
     *
     * @return weight
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * getter method for accessing the description of an item object
     *
     * @return description
     */
    public String getDescription()
    {
        return description;
    }
}
