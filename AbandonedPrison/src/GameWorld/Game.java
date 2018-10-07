package GameWorld;

import java.util.ArrayList;
import java.util.List;

import Persistence.ItemComponent;
import Persistence.RoomComponent;
import Persistence2.GameMap;

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
	List<AbstractItem> Item = new ArrayList<AbstractItem>();
	Player player;
	
	/**
	 * 	Constructor for Game class.
	 * @param GameMap gameMap
	 */
	public Game(GameMap setup) {
		// Null check
		if(setup != null) {
			// Intialise map
			initialiseRoom(setup);
			// Intialise player
			//initialisePlayer(player);
			// initialise Game State
			//initialiseGameState();
		}else {
			//error
		}
	}
	
	
	/**
	 * Game logic
	 */
	public boolean pickUpItem(AbstractItem item) {
		item.pickUp();
		
		return false;
		
		
		
		
	}
	
	
	
	
	
	
	
	
	/**
	 * Initialises the Map, building all the rooms.
	 * Created rooms, from a parsed XML file
	 * @param roomComponents
	 */
	public void initialiseMap(List<RoomComponent> roomComponents) {
		// gameMap should give me access to ArrayList<MapComponent> components
		// Placeholder names until I find out how XML file is parsed.	
		//roomComponents = roomComponents.getComponents();	// Get all components	
		
	}
	
	private void initialisePlayer() {
		// Responsible for setting up Player location (Default starting area vs loaded starting area)
		// Player inventory intialised for use (What have they picked up so far from load state) (Nothing if default start)
		
		// Player player = new Player(Location, Room, Inventory)
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
			
			//if(rc.getHasPlayer()) { Player player = new Player // get player if found in room

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