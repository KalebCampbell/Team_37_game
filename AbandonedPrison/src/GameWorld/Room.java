package GameWorld;

import java.util.ArrayList;
import java.util.List;

public class Room {
	
	private int roomID;
	private List<String> walls;
	private List<AbstractItem> items = new ArrayList <AbstractItem>();
	private Location location;
	private List<String> doors;
	

	/**
	 * Constructor for Room Objects
	 * @param roomID Identity of room
	 * @param walls List of all walls in the room
	 * 
	 */
	public Room (int roomID, List<String> walls, Location loc,List<String>doors, List<AbstractItem> aitems){
		this.setRoomID(roomID);
		this.setWalls(walls);
		this.setLocation(loc);
		this.setDoors(doors);
		this.setRoomObject(aitems);		
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
	
	// Walls //
	private void setWalls(List<String> walls) {
		this.walls = walls;
	}
	
	public List<String> getWalls() {
		return walls;
	}
	
	// Doors //
	private void setDoors(List<String> doors) {
		this.doors = doors;
	}
	
	public List<String> getDoors() {
		return doors;
	}

	
	
	
}