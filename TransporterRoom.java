/**
 * TransporterRoom class is an extension of the Room class.
 * It is used to build special room that transport the player to a random room.
 *
 * @author Cara Tang and Long Vinh Nguyen
 * @version 2018.02.27
 *
 *
 * Moved code from Game.createRooms to Scenario default constructor to create game scenario
 * Added Transporter Room into the scenario
 * Added Shopping Mall up exit to go to Transporter Room
 * Added Transporter Room down exit to go to Shopping Mall
 * Created getStartRoom method to return the starting room
 * Create getRandomRoom method to return a random room
 *
 */


public class TransporterRoom extends Room {

    private Scenario scenario;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */
    public TransporterRoom(String description) {
        super(description);
    }

    /**
     * Return a random room, independent of the direction
     *
     * parameter
     *
     * @param direction Ignored
     * @return A random Room
     */

    public Room getExit (String direction) {
        return findRandomRoom();
    }

    /**
     * Choose a random rom
     * @return A random room
     *
     */
    private Room findRandomRoom() {

        return scenario.getRandomRoom();
    }

}
