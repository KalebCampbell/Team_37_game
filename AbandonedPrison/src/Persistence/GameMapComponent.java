package Persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "map")
/**
 * @author kalebcampbell
 *
 * GameComponent is the object to be used to marshall and unamrshall the XML files.
 * The GameComponent contains a RoomsComponent, PlayerComponent and an arraylist of Strings
 * which contains the current inventory. 
 */
public class GameMapComponent {
	RoomsComponent rooms;
	List<String> inventory = new ArrayList<String>();
	PlayerComponent player;
	
	public RoomsComponent getRooms() {
		return rooms;
	}
	
	//@XmlElement is needed for all setter emthods to be able to be marshalled. 
	@XmlElement
	public void setRooms(RoomsComponent rooms) {
		this.rooms = rooms;
	}
	
	public List<String> getInventory() {
		return inventory;
	}
	
	//@XmlElement is needed for all setter emthods to be able to be marshalled. 
	@XmlElement
	public void setInventory(List<String> inventory) {
		this.inventory = inventory;
	}
	
	public PlayerComponent getPlayer() {
		return player;
	}
	
	//@XmlElement is needed for all getter emthods to be able to be marshalled. 
	@XmlElement
	public void setPlayer(PlayerComponent player) {
		this.player = player;
	}
	
	
}
