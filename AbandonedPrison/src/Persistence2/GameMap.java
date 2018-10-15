package Persistence2;

import java.util.ArrayList;
import java.util.List;

// Michael Vincent

public class GameMap {
	List<Persistence2.RoomComponent> rooms = new ArrayList<Persistence2.RoomComponent>();
	List<String> inventory = new ArrayList<String>();
	PlayerComponent player;

	/**
	 * GameMap constructor.
	 * @param rooms Takes room paramater
	 * @param inventory Takes inventory paramater
	 * @param player Takes player paramater
	 */
	public GameMap(List<Persistence2.RoomComponent> rooms, List<String> inventory, PlayerComponent player) {
		this.rooms = rooms;
		this.inventory = inventory;
		this.player = player;
	}
	
	/** 
	 * Gets the rooms associated with the GameMap as a list
	 * @return List<Persistence2.RoomComponent>
	 */
	public List<Persistence2.RoomComponent> getRooms() {
		return rooms;
	}
	
	/**
	 * Gets the inventory associated with the GameMap as a list
	 * @return List<String>
	 */
	public List<String> getInventory() {
		return inventory;
	}
	
	/**
	 * Get player 
	 * @return PlayerComponent
	 */
	public PlayerComponent getPlayer() {
		return player;
	}
}
