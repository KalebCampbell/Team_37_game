package GameWorld;

import java.util.List;

/**
 * Player class
 * Handles the creation,Movement, Inventory of a player.
 * 
 * @author Michael Vincent 
 */
public class Player {

	private int id;
	private String name;
	private Location location; 
	private int roomId;
	private Inventory inventory;
	private String direction = "N";
	
	/**
	 * Player constructor
	 * @param id player id
	 * @param name string
	 * @param roomId 
	 * @param location
	 */
	public Player(int id, String name, int roomId, Location location) {
		this.id = id;
		this.name = name;
		this.roomId = roomId;
		this.location = location;
	} 

	// Public SETTERS as they're used outside of construction //
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public void setRoomID(int roomId) {
		this.roomId = roomId;
	}

	/**
	 * Getter for player ID
	 * @return return player ID
	 */
	public int getPlayerId() {
		return id;
	}
	/**
	 * Getter for player Name
	 * @return return player name
	 */
	public String getPlayerName() {
		return name;	
	}
	
	/**
	 * Getter for player Location.
	 * @return return player location
	 */
	public Location getPlayerLocation() {
		return location;
	}
	
	/**
	 * Getter for Room ID
	 * @return return room id;
	 */
	public int getRoomId() {
		return roomId;
	}
	
	/**
	 * Getter for Inventory
	 * @return return inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * Public setter for player location
	 * @param loc location
	 */
	public void setPlayerLocation(Location loc) {
		this.location = loc;
	}
	
	/**
	 * Sets direction of player
	 * @param direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	/**
	 * get direction of player
	 * @return Direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Move method moves the player to a new location ready to application to redraw
	 * @param Direction the player is facing
	 */
	public void move(String dir) {
			//NORTH (0,-1)		
		if(dir.equals("N")) {	
			setPlayerLocation(new Location(getPlayerLocation().getX(), getPlayerLocation().getY()-1));	
			//SOUTH (0,1)	
		}else if(dir.equals("S")) {
			setPlayerLocation(new Location(getPlayerLocation().getX(), getPlayerLocation().getY()+1));
			//EAST (1,0)
		}else if(dir.equals("E")) {
			setPlayerLocation(new Location(getPlayerLocation().getX()+1, getPlayerLocation().getY()));
			//WEST (-1,0)
		}else if(dir.equals("W")) {
			setPlayerLocation(new Location(getPlayerLocation().getX()-1, getPlayerLocation().getY()));
		}
		
	}

	/**
	 * Method for turning the player to the left.
	 */
	public void turnLeft() {
		String dir = this.direction;
		if(dir.equals("N")) {
			setDirection("W");	
		}else if(dir.equals("W")){
			setDirection("S");
		}else if(dir.equals("S")) {
			setDirection("E");
		}else if(dir.equals("E")) {
			setDirection("N");
		}	
		//Debug System.out.println("Current direction: "+getDirection());
	}
	/**
	 * Method for turning the player to the right.
	 */
	public void turnRight() {
		String dir = this.direction;
		if(dir.equals("N")) {
			setDirection("E");
		}else if(dir.equals("E")){
			setDirection("S");
		}else if(dir.equals("S")) {
			setDirection("W");
		}else if(dir.equals("W")) {
			setDirection("N");
		}	
		//Debug System.out.println("Current direction: "+getDirection());
	}

	/**
	 * Move player forward
	 * @param roomList
	 * @return
	 */
	public boolean playerMove(List<Room> roomList) {
		
		// Step forward or backward	
		// Get the direction the player is facing
		String dir = getDirection();
		Room currentRoom = null;
			// Check if wall is in the way
		
			// Iterate through all rooms
			for(Room r : roomList) {
				// find room player is in
				if(r.getRoomID() == getRoomId()){							
					// Sets the room the player is in
					currentRoom = r;
				}
			}
			
			// WALL CHECK
			for(String s : currentRoom.getWalls()) {
				// Forwards
				if(s.equals(dir)) {
				System.out.println("Cannot move into a wall");
				// Illegal move E.G PLAYER FACING WEST, WALLS WEST
				return false;
				}	
			}
					
			// DOOR CHECK
			for(String s : currentRoom.getDoors()) {
				if(s.equals(dir)) {
				move(dir);
				System.out.println("Move through: "+dir+" door to"+ getPlayerLocation().getX() +"," + getPlayerLocation().getY());
				}
			}
			
			// FIND NEW ROOM
			for(Room room : roomList) {	
				if(getPlayerLocation().getX() == room.getLocation().getX() &&
				   getPlayerLocation().getY() == room.getLocation().getY()) {		
					setRoomID(room.getRoomID());
							return true;
					}
			}
		return false;
	}

	private String getOpposite(String dir) {
		switch (dir) {
			case "N": return "S";
			case "S": return "N";
			case "E": return "W";
			case "W": return "E";
			default: return "";
	}
	}

	public boolean inventoryContains(Item item) {
		for(Item i : inventory.getInventory()) {
			if(i.getItemId() == item.getItemId()) {
				return true;
			}
		}
		return false;
	}
}



		

