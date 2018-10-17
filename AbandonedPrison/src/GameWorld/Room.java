package GameWorld;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Michael Vincent 300140128
 *
 */
public class Room {
	
	private int roomID;
	private List<String> walls;
	private List<AbstractItem> items = new ArrayList <AbstractItem>();
	private Location location;
	private List<String> doors;
	private AbstractItem[][] itemGrid = new AbstractItem[4][4];

	

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
		this.setItem(aitems);
		createItemGrid();
		
		itemSetup(aitems);
		

	}
	
	private void itemSetup(List<AbstractItem> aitems) {
		for(AbstractItem i : aitems) {
			int x = i.getItemLocation().getX();
			int y = i.getItemLocation().getY();		
			itemGrid[x][y] = i;
		}
		
	}

	private void createItemGrid() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j <4; j++) {
				itemGrid[i][j] = new emptyItem("empty", -1, "empty", "empty", new Location(i,j));
			}
		}
	}
	
	/*
	 * Attempts to place an item in front of the player.
	 * It does this by determining which way the player is facing.
	 * Finding the 2x2 grid that the user can see.
	 * Finding an empty item slot on the ground in that area and place it.
	 * @param item Abstract item 
	 * @param direction direction of player
	 * @return true or false is item was placed.
	 */
	public boolean addItemToGrid(AbstractItem item, String direction) {
		
		if(direction.equals("N")) {
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 4; j++ ) {
					if(itemGrid[i][j].getItemName().equals("empty")) {
						itemGrid[i][j] = item;
						return true;
					}
				}
			}
		}
		
		if(direction.equals("W")) {
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 2; j++ ) {
					if(itemGrid[i][j].getItemName().equals("empty")) {
						itemGrid[i][j] = item;
						return true;
					}
				}
			}
		}
		
		if(direction.equals("S")) {
			for(int i = 2; i < 4; i++) {
				for(int j = 0; j < 4; j++ ) {
					if(itemGrid[i][j].getItemName().equals("empty")) {
						itemGrid[i][j] = item;
						return true;
					}
				}
			}
		}
		
		if(direction.equals("E")) {
			for(int i = 0; i < 4; i++) {
				for(int j = 2; j < 4; j++ ) {
					if(itemGrid[i][j].getItemName().equals("empty")) {
						itemGrid[i][j] = item;
						return true;
					}
				}
			}
		}
		// Shouldn't get here unless 16 items in a room.
		System.out.println("No item slots left in room");
		return false;
		
		
	}
	
	public AbstractItem[][] getItemGrid(){
		return this.itemGrid;
	}

	// Rooms //
	public int getRoomID() {
		return roomID;
	}
	
	public void setRoomID(int id) {
		this.roomID = id;
	}
	
	private void setItem(List<AbstractItem> aitems) {
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

	public void addItem(AbstractItem item) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}