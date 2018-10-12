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
	
	
	
}