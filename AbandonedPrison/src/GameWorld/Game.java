package GameWorld;

import java.util.ArrayList;
import java.util.List;

import Persistence.ItemComponent;
import Persistence.RoomComponent;
import Persistence2.GameMap;
import Persistence2.InventoryComponent;
import Persistence2.PlayerComponent;

/**
 * Game class controls the creation of the game.
 * Handles logic of game
 * @author Michael Vincent
 *
 */
public class Game {
	
	// Fields for storing the game components
	List<RoomComponent> roomComponents = new ArrayList<RoomComponent>();
	List<Room> roomList = new ArrayList<Room>();
	Player player;
	
	/**
	 * 	Constructor for Game class.
	 * @param GameMap gameMap
	 */
	public Game(GameMap setup) {
		// Null check
		if(setup != null) {
			// Player setup
			this.player = initialisePlayer(setup);
			// Inventory setup
			this.player.setInventory(initialiseInventory(setup));
			// Creates rooms & map setup
			initialiseMap(setup);
			
		}else {
			System.out.println("GameMap is empty");
		}
	}
	
	/**
	 * Initialises the Map, building all the rooms.
	 * Created rooms, from a parsed XML file
	 * @param roomComponents
	 */
	public void initialiseMap(GameMap setup) {
		for(Persistence2.RoomComponent rc : setup.getRooms()) {
			
			Location loc = rc.getLocation();
		}
		
	}
	
	/** Responsible for initialising a player
	 *  Gathers information about the player from the parsing setup file.
	 * 
	 * @param setup
	 * @return Player
	 */
	private Player initialisePlayer(GameMap setup) {
		
		// Parse setup
		int id = Integer.parseInt(setup.getPlayer().getId());
		String name = setup.getPlayer().getName();
		int roomId = Integer.parseInt(setup.getPlayer().getRoomId());
		Location location = setup.getPlayer().getLocation();
		
		// Create player
		return new Player(id,name,roomId,location);
	}
	
	/** Responsible for initialising the inventory
	 *  Gathers informaiton about the inventory from the parsing setup file.
	 * 
	 * @param setup
	 * @return Inventory
	 */
	private Inventory initialiseInventory(GameMap setup) {
		Inventory inv = new Inventory();
		
		// For each slot in inventory gamefile
		for(String slot : setup.getInventory()) {
			inv.addItemToInventory(slot);			
			}
			return inv;
		}

	
	private void initialiseGameState() {
		// Responsible for Game Time Limit
		
		// Setting up conditions i.e door open,locked,unlocked.
		// Has puzzle been completed.
		// Win conditions, has exit been found.
		
	}
	public void initialiseRoom(GameMap setup){
		
		// Iterate over components of the GameMap
		// Find map components and adds them to 	
		
		for(RoomComponent rc : setup.getRooms()) {
			
				// This need to not be Integers (N,W,S,E) 1,2,3,4 makes very little sense.
				List<Integer> walls = new ArrayList<Integer>();
				walls = rc.getWalls();
				
				// Loop through all items and create items based on what they are.
				List<AbstractItem> aitems = new ArrayList<AbstractItem>();
				for(ItemComponent ic : rc.getItems()) {	// mc.GetRoomObjects() should return an arraylist
					if(ic.getItem().equals("Key")) { // If it's a key
					// Need to have information about the key in the XML File.
					// KeyID, KeyName, KeyDescription, Image to use			
					aitems.add(new Key("Key1", "keyImage", "Description of key1" , new Location(ic.getPosX(),ic.getPosY())));		
				}
				
				int roomId = rc.getId();					// Get room ID
				Location location = new Location(rc.getLocX(),rc.getLocY());

				// Create new Room with: ID
				//						 Walls
				//						 Location
				//						 Items
				
				Room r = new Room(roomId, walls, location, aitems);
				roomList.add(r);

				
			}
		}

	}
}