package Persistence2;

import java.util.ArrayList;
import java.util.List;

import Persistence2.RoomComponent;

// Michael Vincent

public class GameMap {
	List<Persistence2.RoomComponent> rooms = new ArrayList<Persistence2.RoomComponent>();
	List<String> inventory = new ArrayList<String>();
	PlayerComponent player;

	
	public GameMap(List<Persistence2.RoomComponent> rooms2, List<String> inventory, PlayerComponent player) {
		this.rooms = rooms2;
		this.inventory = inventory;
		this.player = player;
	}
	
	public List<Persistence2.RoomComponent> getRooms() {
		return rooms;
	}
	
	public List<String> getInventory() {
		return inventory;
	}
	
	public PlayerComponent getPlayer() {
		return player;
	}
	

	
	
	
	

}
