package GameWorld;

/**
 * Basic creation of player, nothing added yet.
 * @author Michael Vincent
 *
 */
public class Player {

	private int id;
	private String name;
	private Location location; // Location inside room
	private int roomId;
	private Inventory inventory;
	
	private String direction = "SOUTH";
	
	public Player(int id, String name, int roomId, Location location) {
		this.id = id;
		this.name = name;
		this.roomId = roomId;
		this.location = location;
	}

	
	
	// SETTERS //
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	// GETTERS //
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
			//NORTH (0,1)	
		
		
		if(dir.equals("NORTH")) {	
			setPlayerLocation(new Location(getPlayerLocation().getX(), getPlayerLocation().getY()+1));	
			//SOUTH (0,-1)	
		}else if(dir.equals("SOUTH")) {
			setPlayerLocation(new Location(getPlayerLocation().getX(), getPlayerLocation().getY()-1));
			//EAST (1,0)
		}else if(dir.equals("EAST")) {
			setPlayerLocation(new Location(getPlayerLocation().getX()+1, getPlayerLocation().getY()));
			//WEST (-1,0)
		}else if(dir.equals("WEST")) {
			setPlayerLocation(new Location(getPlayerLocation().getX()-1, getPlayerLocation().getY()));
		}else {
		}
		
	}
	
	
	
	
	
}
		

