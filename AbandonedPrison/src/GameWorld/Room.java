package GameWorld;

import java.util.ArrayList;
import java.util.List;

public class Room {
	
	private int roomID;
	private List<Integer> walls = new ArrayList <Integer>();	// Maybe change to map Map<Cardinal, Boolean> walls; (walls.add(new Cardinal("NORTH"),True)
	private List<AbstractItem> items = new ArrayList <AbstractItem>();
	private Location location;
	

	/**
	 * Constructor for Room Objects
	 * @param roomID Identity of room
	 * @param walls List of all walls in the room
	 * 
	 */
	public Room (int roomID, List<Integer> walls, Location loc, List<AbstractItem> aitems){
		this.setRoomID(roomID);
		this.setWalls(walls);
		this.setLocation(loc);
		this.setRoomObject(aitems);		
	}
	
	private void setWalls(List<Integer> walls) {
		this.walls = walls;
	}

	// Rooms //
	public int getRoomID() {
		return roomID;
	}
	
	public void setRoomID(int id) {
		this.roomID = id;
	}
	
	private void setRoomObject(List<AbstractItem> aitems) {
		this.items = aitems;
	}
	
	// Items //
	public List<AbstractItem> getItems() {
		return items;
	}
	
	public void setItems(List<AbstractItem> items){
		this.items = items;
	}

	// Location
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	
	
	
}