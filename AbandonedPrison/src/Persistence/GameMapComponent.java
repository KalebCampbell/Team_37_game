package Persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "map")
/**
 * GameComponent is the object to be used to marshall and unamrshall the XML files.
 * The GameComponent contains a RoomsComponent, PlayerComponent and an arraylist of Strings
 * which contains the current inventory.
 * 
 *  @author kalebcampbell
 */
public class GameMapComponent {
	RoomsComponent rooms;
	List<String> inventory = new ArrayList<String>();
	PlayerComponent player;
	List<String> containers = new ArrayList<String>();
	List<String> doors = new ArrayList<String>();
	
	/**
	 * Returns the rooms RoomsComponent from the GameMapComponent.
	 * @return rooms	  
	 */
	public RoomsComponent getRooms() {
		return rooms;
	}
	 
	/**
	 * Set the rooms to rooms. 
	 * @param rooms
	 */
	@XmlElement
	public void setRooms(RoomsComponent rooms) {
		this.rooms = rooms;
	}
	
	/**
	 * Returns the inventory from the GameMapComponent.
	 * @return inventory
	 */
	public List<String> getInventory() {
		return inventory;
	}
	
	/**
	 * Sets the inventory to inventory.
	 * @param inventory
	 */
	@XmlElement
	public void setInventory(List<String> inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * Returns the player from the GameMapComponent.
	 * @return player
	 */
	public PlayerComponent getPlayer() {
		return player;
	}
	
	/**
	 * Sets the player from the GameMapComponent.
	 * @param player
	 */
	@XmlElement
	public void setPlayer(PlayerComponent player) {
		this.player = player;
	}
	/**
	 * Sets the containers to containers.
	 * @return 
	 */
	@XmlElement
	public void setContainers(List<String> containers) {
		this.containers = containers;
	}
	
	public List<String> getContainers(){
		return containers;
	}
	/**
	 * Sets the doors to doors.
	 * @return 
	 */
	@XmlElement
	public void setDoors(List<String> doors) {
		this.doors = doors;
	}
	
	public List<String> getDoors(){
		return doors;
	}
	
	
	
}
