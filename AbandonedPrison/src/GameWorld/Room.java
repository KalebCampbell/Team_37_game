package GameWorld;

import java.util.ArrayList;
import java.util.List;

public class Room {
	
	private final int roomID;
	private List<Wall> walls = new ArrayList <Wall>();
	private List<Item> items = new ArrayList <Item>();
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	
	

	/**
	 * Constructor for Room Objects
	 * @param roomID Identity of room
	 * @param walls List of all walls in the room
	 * 
	 */
	public Room (int roomID, List<Wall> walls){
		this.roomID = roomID;
		this.walls = walls;
		
		buildRoom();
		
	}
	
	public void buildRoom() {
		for(Wall w : walls) {
			w.getLocation();
		}
		
		
	}
	
	
	
	
}
