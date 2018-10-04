package GameWorld;

import java.util.ArrayList;
import java.util.List;

import Persistence.GameMap;

/**
 * Game class controls the creation of the game.
 * Handles logic of game
 * @author Michael Vincent
 *
 */
public class Game {
	
	List<MapComponent> components = new ArrayList<MapComponent>();
	List<Room> roomList = new ArrayList<Room>();
	List<AbstractItem> Item = new ArrayList<AbstractItem>();
	Player player;
	
	/**
	 * 	Constructor for Game class.
	 * @param GameMap gameMap
	 */
	public Game(GameMap gameMap) {
		// Null check
		if(gameMap != null) {
			initialiseMap(gameMap); // Initialise
		}else {
			//error
		}
	}
	/**
	 * Initialises the Map
	 * Created rooms, from a parsed XML file
	 * @param gameMap
	 */
	public void initialiseMap(GameMap gameMap) {
		// gameMap should give me access to ArrayList<MapComponent> components 
		components = gameMap.getComponents();	// Get all components
		
		// Intialise map
		initialiseMap(components);
		// Intialise player
		intialisePlayer();
		// Intialise gamestate
		initialiseGameState();
		
	}
	
	private void intialisePlayer() {
		
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
	public void initialiseMap(List<MapComponent> components){
		
		// Iterate over components of the Map
		// Add rooms to roomList
		// Find map components and make game objects out of them.

		for(MapComponent mc : components) {
			if(mc.getComponentType == "Room") { 

				// Loop through all walls and create wall objects (Maybe unneeded)
				List<Wall> walls = new ArrayList<Wall>();
				for(WallComponent wc : mc.getRoomWalls()) {		// mc.getRoomWalls() should return an ArrayList
					walls.add(mc);	// Add all walls (Hopefully some naming convention for each cardinal of wall) 
				}		
				
				// Loop through all items and create abstract items
				List<AbstractItem> aitems = new ArrayList<AbstractItem>();
				for(ObjectComponent oc : mc.getRoomObjects()) {	// mc.GetRoomObjects() should return an arraylist
					aitems.add(oc);
				}
				
				int roomId = mc.getRoomId();					// Get room ID
				Location location = mc.getLocation();			// Get room location

				// Create new Room with: ID
				//						 Walls
				//						 Location
				//						 Items
				
				Room r = new Room(roomID, walls, location, aitems);
				roomList.add(r); // add rooms
			}else {
				//Other MapComponents
				// DOOR
				// WINDOW
				// 
			}

		}
	}
}
