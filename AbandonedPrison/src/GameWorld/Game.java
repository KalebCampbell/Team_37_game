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
	 * 	When called performs an initialisation of all components from the map
	 *  Player setup,Inventory setup,Map setup.
	 * 
	 * @param GameMap gameMap object from persistence package
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
			
			List<AbstractItem> itemList = createItems(rc.getItem());		
			// Build room
			Room room = new Room(Integer.parseInt(rc.getRoomid()), rc.getWall(), rc.getLocation(),rc.getDoor(), itemList);
			// Add room to roomList
			roomList.add(room);
		}	
	}
	
	/**
	 * Private method only called within game object
	 * Creates items based on input string
	 * @param items arraylist of string items
	 * @return List of abstractItems
	 */
	private List<AbstractItem> createItems(ArrayList<String> items) {
		AbstractItem item = null;
		
		if(items != null) {
		
			if(items.get(0).equals("KEY")){
				 item = new Key(items.get(0), "DefaultImage", "A key for opening doors",
					    new Location(Integer.parseInt(items.get(2)),Integer.parseInt(items.get(3))));
			}else if(items.get(0).equals("KEYCARD")) {
				 item = new KeyCard(items.get(0), "DefaultImage", "A keycard for swiping at doors",
					    new Location(Integer.parseInt(items.get(2)),Integer.parseInt(items.get(3))));
			}else if(items.get(0).equals("CROWBAR")) {
				
			}else {
				//error check
			}
		}
		
		List<AbstractItem> returnItems = new ArrayList<AbstractItem>();
		returnItems.add(item);
		return returnItems;
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
}