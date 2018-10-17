package GameWorld;

import java.util.List;

/**
 * Basic creation of player, nothing added yet.
 * @author Michael Vincent 300140128
 *
 */
public class Player {

	private int id;
	private String name;
	private Location location; // Location inside room
	private int roomId;
	private Inventory inventory;
	
	private String direction = "N";
	
	public Player(int id, String name, int roomId, Location location) {
		this.id = id;
		this.name = name;
		this.roomId = roomId;
		this.location = location;
	}

	
	
	// Public SETTERS as they're used out side of construction //
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public void setRoomID(int roomId) {
		this.roomId = roomId;
	}

	// Public GETTERS to be accessed outside of class //
	public int getPlayerId() {
		return id;
	}
	
	public String getPlayerName() {
		return name;	
	}
	public Location getPlayerLocation() {
		return location;
	}
	
	public int getRoomId() {
		return roomId;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	// MOVEMENT HANDLING //
	public void setPlayerLocation(Location loc) {
		this.location = loc;
	}
	
	
	

	// DIRECTION HANDLING //
	
	/**
	 * Sets direction of player
	 * @param direction
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
	 * @param dir
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
		}else {
		}
		
	}



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
		System.out.println("Current direction: "+getDirection());
		
	}
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
		System.out.println("Current direction: "+getDirection());
	}

	public boolean playerMove(List<Room> roomList) {
		playerMove(roomList);
		
		
		// Step forward or backward	
		// Get the direction the player is facing
		String dir = getDirection();
			// Check if wall is in the way
		
			// Iterate through all rooms
			for(Room r : roomList) {
				// find room player is in
				if(r.getRoomID() == getRoomId()){
							
					// If the walls array contains the direction the player is facing
					// Can't use ".contains()" as it's different objects, whoops.
					for(String s : r.getWalls()) {
						if(s.equals(dir)) {
							System.out.println("Direction: "+dir+ " Wall: "+s);
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
							System.out.println("Current location: "+ getPlayerLocation().getX() +"," + getPlayerLocation().getY());
							// Moves the player
							move(dir);
							System.out.println("After move: "+ getPlayerLocation().getX() +"," + getPlayerLocation().getY());
							// Sets the players new roomId
							
							for(Room room : roomList) {	
								if(getPlayerLocation().getX() == room.getLocation().getX() &&
								   getPlayerLocation().getY() == room.getLocation().getY()) {		
									// Set room id of player
									setRoomID(room.getRoomID());
									return true;
								}
							}		
						}
					}
				}
			}
		return false;
	}
		
		
		
		
		
		
		
	}


		

