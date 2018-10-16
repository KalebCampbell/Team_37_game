package GameWorld;

import java.util.ArrayList;
import java.util.List;

import Persistence.RoomComponent;
import Persistence.RoomsComponent;
import Persistence.GameMapComponent;
import Persistence.ItemComponent;
//import Persistence2.GameMap;


/**
 * Game class controls the creation of the game.
 * Handles logic of game
 * @author Michael Vincent 300140128
 *
 */
public class Game {
	// Stores everything about the game as room objects.
	List<Room> roomList = new ArrayList<Room>();
	// Stores everythinhg about the player including inventory.
	Player player;
	
	/**
	 * 	Constructor for Game class.
	 * 	When called performs an initialisation of all components from the map
	 *  Player setup,Inventory setup,Map setup.
	 * 
	 * @param GameMap gameMap object from persistence package
	 */
	public Game(GameMapComponent setup) {
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
	
	public void itemDrop(String itemName) {
			
		
	}
	
	
	
	
	/**
	 * Method to find item in a room
	 * Will be extended for multiple items
	 * @return Item, or null if none
	 */
	public AbstractItem findItemInRoom() {
		// Find which room player is in
		int roomId = player.getRoomId();
		Room room = findRoom(roomId);
		// Iterate over the room list
		for(Room r : roomList) {
			// If ID's match
			if(r.getRoomID() == room.getRoomID()) {
				return r.getItems().get(0);
			}
		}
		return null;
	}
	
	/**
	 * Method to find a room based on an id
	 * @param roomId
	 * @return room object or null if no room
	 */
	public Room findRoom(int roomId) {
		for(Room r : roomList) {
			if(r.getRoomID() == roomId) {
				return r;
			}
		}
		return null;
	}

	/**
	 * Pickup item
	 * @param itemName in string format
	 * @return true/false if item can be picked up
	 */
	public boolean itemPickUp(AbstractItem item) {	
		item.pickUp(); // Demonstrate strategy.
		return player.getInventory().addItemToInventory(item);
	}
	
	/**
	 * Sets the players direction
	 * @param direction
	 */
	public void playerTurn(String direction) {
		player.setDirection(direction);
	}
	
	/**
	 *  Call for player to move.
	 *  Checks player direction against player movement
	 *  
	 *  
	 * @param move
	 * @return true/false
	 */
	public boolean playerMove() {
		
		// Step forward or backward
		
		// Get the direction the player is facing
		String dir = player.getDirection();
			// Check if wall is in the way
		
			// Iterate through all rooms
			for(Room r : roomList) {
				// find room player is in
				if(r.getRoomID() == player.getRoomId()){
							
					// If the walls array contains the direction the player is facing
					// Can't use ".contains()" as it's different objects, whoops.
					for(String s : r.getWalls()) {
						if(s.equals(dir)) {
							// Illegal move E.G PLAYER FACING WEST, WALLS WEST
							return false;
						}
					}
					
					// Can't use ".contains()" as it's different objects, whoops.
					for(String s : r.getDoors()) {
						if(s.equals(dir)) {
							// MISSING: CHECK IF DOOR IS LOCKED
							// ASSUME DOOR IS UNLOCKED & OPEN
							// Update player location
							System.out.println("Current location: "+ player.getPlayerLocation().getX() +"," + player.getPlayerLocation().getY());
							// Moves the player
							player.move(dir);
							System.out.println("After move: "+ player.getPlayerLocation().getX() +"," + player.getPlayerLocation().getY());
							// Sets the players new roomId
							
							for(Room room : roomList) {	
								if(player.getPlayerLocation().getX() == room.getLocation().getX() &&
								   player.getPlayerLocation().getY() == room.getLocation().getY()) {		
									// Set room id of player
									player.setRoomID(room.getRoomID());
								}
							}		
						}
					}
				}
			}
		return false;
	}
	

	/**
	 * Initialises the Map, building all the rooms.
	 * Created rooms, from a parsed XML file
	 * @param roomComponents
	 */
	private void initialiseMap(GameMapComponent setup) {
		
		// iterate over all rooms
		// create items
		
		RoomsComponent rc = setup.getRooms();
		
		for(RoomComponent roomC : rc.Rooms()) {
			List<ItemComponent> ic = roomC.getItems();
			
			List<AbstractItem> itemList = initialiseItems(roomC.getItems());		
			// Build room
			Room room = new Room(roomC.getId(), roomC.getWalls(), new Location(roomC.getLocX(),roomC.getLocY()),roomC.getDoors(), itemList);
			// Add room to roomList
			roomList.add(room);
		}	
	}
	
	/**
	 * Private method only called within game object
	 * Creates items based on input string
	 * @param list arraylist of string items
	 * @return List of abstractItems
	 */
	private List<AbstractItem> initialiseItems(List<ItemComponent> list) {
		AbstractItem item = null;
		List<AbstractItem> returnItems = new ArrayList<AbstractItem>();
		
		if(list != null) {
			
			for(ItemComponent ic : list) {
				
				String itemName = ic.getItem();
				if(itemName != null) {
					if(itemName.equals("Key")) {
						item = new Key(itemName, 99,"KeyDescription","KeyImage", new Location(ic.getPosX(), ic.getPosY()));
						returnItems.add(item);
					}
				}
			}
		}
		return returnItems;
	}

	/** Responsible for initialising a player
	 *  Gathers information about the player from the parsing setup file.
	 * 
	 * @param setup
	 * @return Player
	 */
	private Player initialisePlayer(GameMapComponent setup) {
		
		// Parse setup
		int id = Integer.parseInt(setup.getPlayer().getId());
		String name = setup.getPlayer().getName();
		int roomId = Integer.parseInt(setup.getPlayer().getRoomid());
		Location location = setup.getPlayer().getCord();
		
		// Create player
		return new Player(id,name,roomId,location);
	}
	
	/** Responsible for initialising the inventory
	 *  Gathers informaiton about the inventory from the parsing setup file.
	 * 
	 * @param setup
	 * @return Inventory
	 */
	private Inventory initialiseInventory(GameMapComponent setup) {
		
		Inventory inv = new Inventory();
		
		// Iterate over inventory
		for(String s : setup.getInventory()) {
			// split inventory up
			String[] itemArr = s.split(",");
			AbstractItem item = null;
			
			if(itemArr[0].equals("Key")) {
				 item = new Key(itemArr[0], Integer.parseInt(itemArr[1]), "image","description", new Location(Integer.parseInt(itemArr[2]),Integer.parseInt(itemArr[3])));
				 inv.addItemToInventory(item);
			}else {
				
			}
		}
		return inv;
	}
		
		//List<AbstractItem> itemList = initialiseItems(ArrayList<String> items);	
		
		

	
	private void initialiseGameState() {
		// Responsible for Game Time Limit		
		// Setting up conditions i.e door open,locked,unlocked.
		// Has puzzle been completed.
		// Win conditions, has exit been found.	
	}
}