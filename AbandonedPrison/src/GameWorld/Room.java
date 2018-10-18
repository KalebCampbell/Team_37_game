package GameWorld;

import java.util.ArrayList;
import java.util.List;

import Persistence.DoorComponent;
/**
 * 
 * @author Michael Vincent 300140128
 *
 */
public class Room {
	
	private final int GRID_SIZE = 4;
	
	private int roomID;
	private List<String> walls;
	private List<Item> items = new ArrayList <Item>();
	private List<Container> containers = new ArrayList<Container>();
	private Location location;
	private List<Door> doors;
	private Object[][] itemGrid = new Object[4][4];


	/**
	 * Constructor for Room Objects
	 * @param roomID Identity of room
	 * @param walls List of all walls in the room
	 * @param doorList 
	 * @param containerList 
	 * 
	 */
	public Room (int roomID, List<String> walls, Location loc,List<Item> items, List<Container> containers, List<Door> doorList){
		this.setRoomID(roomID);
		this.setWalls(walls);
		this.setLocation(loc);
		this.setDoors(doorList);
		this.setItem(items); 
		this.setContainer(containers);
		createGrid();
		
		containerSetup(containers);
		itemSetup(items);
		System.out.println(" d");
		
	}
	

	/**
	 * Add containers to grid
	 * @param containers
	 */
	private void containerSetup(List<Container> containers) {
		for(Container ac : containers) {
			int x = ac.getContainerLocation().getX();
			int y = ac.getContainerLocation().getY();	
			itemGrid[x][y] = ac;
		}	
	}

	/**
	 * Add items to grid
	 * @param items
	 */
	private void itemSetup(List<Item> items) {
		for(Item i : items) {
			int x = i.getItemLocation().getX();
			int y = i.getItemLocation().getY();		
			itemGrid[x][y] = i;
		}
		
	}	

	private void createGrid() {
		for(int i = 0; i < GRID_SIZE; i++) {
			for(int j = 0; j < GRID_SIZE; j++) {
				itemGrid[i][j] = new Object();
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
	public boolean addItemToGrid(Item item, String direction) {
		 
		if(direction.equals("N")) {
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 4; j++ ) {
					if(itemGrid[i][j].getClass() != item.getClass()) {
						itemGrid[i][j] = item;
						items.add(item);
						return true;
					}
				}
			}
		}
		
		if(direction.equals("W")) {
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 2; j++ ) {
					if(itemGrid[i][j].getClass() != item.getClass()) {
						itemGrid[i][j] = item;
						items.add(item);
						return true;
					}
				}
			}
		}
		
		if(direction.equals("S")) {
			for(int i = 2; i < 4; i++) {
				for(int j = 0; j < 4; j++ ) {
					if(itemGrid[i][j].getClass() != item.getClass()) {
						itemGrid[i][j] = item;
						items.add(item);
						return true;
					}
				}
			}
		}
		
		if(direction.equals("E")) {
			for(int i = 0; i < 4; i++) {
				for(int j = 2; j < 4; j++ ) {
					if(itemGrid[i][j].getClass() != item.getClass()) {
						itemGrid[i][j] = item;
						items.add(item);
						return true;
					}
				}
			}
		}
		// Shouldn't get here unless 16 items in a room.
		System.out.println("No item slots left in room");
		return false;
		
		
	}
	
	/**
	 * Getter for object grid
	 * @return itemGrid
	 */
	public Object[][] getGrid(){
		return this.itemGrid;
	}
	
	public boolean itemOnGrid(Item item) {
		for(int i = 0; i < GRID_SIZE; i++) {
			for(int j = 0; j < GRID_SIZE; j++) {
				if(itemGrid[i][j] == item) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 *  Getter for room id
	 * @return id as integer
	 */
	public int getRoomID() {
		return roomID;
	}
	
	public void setRoomID(int id) {
		this.roomID = id;
	}

	private void setItem(List<Item> aitems) {
		this.items = aitems;
	}
	
	/**
	 *  Getter for item list
	 * @return list of items
	 */
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items){
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
	private void setDoors(List<Door> list) {
		this.doors = list;
	}
	
	public List<Door> getDoors() {
		return doors;
	}

	public List<Container> getContainer() {
		return containers;
	}

	public void setContainer(List<Container> container) {
		this.containers = container;
	}
	
}