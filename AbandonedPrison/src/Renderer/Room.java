package Renderer;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Represents a Room in a 3D environment, with 0 - 4 walls, and a 2d array of
 * Items within the Room.
 * 
 * @author Joel Harris
 */
public class Room implements Comparable<Room> {

	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private Item[][] items = new Item[4][4];
	private Floor floor;
	private Point3D position;

	/**
	 * Room constructor.
	 * 
	 * @param position
	 *            center position
	 * @param walls
	 *            walls
	 * @param items
	 *            items on the floor
	 * @param floor
	 *            floor
	 */
	public Room(Point3D position, ArrayList<Wall> walls, Item[][] items, Floor floor) {
		this.position = position;
		this.walls = walls;
		this.items = items;
		this.floor = floor;
		translate(position.getX(), position.getY(), position.getZ());
	}

	/**
	 * Translates the Room by the delta values.
	 *
	 * @param x
	 *            delta x
	 * @param y
	 *            delta y
	 * @param z
	 *            delta z
	 */
	public void translate(int x, int y, int z) {
		for (Wall wall : walls) {
			wall.translate(x, y, z);
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (items[i][j] != null)
					items[i][j].translate(x, y, z);
			}
		}
		floor.translate(x, y, z);
		position.translate(x, y, z);
	}

	/**
	 * Rotates the Room 90 degrees to left.
	 */
	public void rotateLeft() {
		for (Wall wall : walls) {
			wall.rotateLeft();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (items[i][j] != null)
					items[i][j].rotateLeft();
			}
		}
		floor.rotateLeft();
		position.rotateLeft();
	}

	/**
	 * Rotates the Room 90 degrees to right.
	 */
	public void rotateRight() {
		for (Wall wall : walls) {
			wall.rotateRight();
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (items[i][j] != null)
					items[i][j].rotateRight();
			}
		}
		floor.rotateRight();
		position.rotateRight();
	}

	/**
	 * Uses the compareTo method in Item to order the Items within a Room using a
	 * PriorityQueue.
	 * 
	 * @return PriorityQueue of Items
	 */
	public PriorityQueue<Item> orderItems() {
		PriorityQueue<Item> orderedItems = new PriorityQueue<Item>();
		Item[][] items = this.items;
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items.length; j++) {
				if (items[i][j].getPosition().getZ() >= 0) {
					orderedItems.add(items[i][j]);
				}
			}
		}
		return orderedItems;
	}

	/**
	 * @return the walls
	 */
	public ArrayList<Wall> getWalls() {
		return walls;
	}

	/**
	 * @return the items
	 */
	public Item[][] getItems() {
		return items;
	}

	/**
	 * @return the floor
	 */
	public Floor getFloor() {
		return floor;
	}

	/**
	 * @return the position
	 */
	public Point3D getPosition() {
		return position;
	}

	@Override
	public int compareTo(Room other) {
		if (this.position.getRealZ() > other.getPosition().getRealZ())
			return 1;
		else if (this.position.getRealZ() < other.getPosition().getRealZ())
			return -1;
		if (Math.abs(this.position.getRealX()) > Math.abs(other.getPosition().getRealX()))
			return 1;
		if (Math.abs(this.position.getRealX()) < Math.abs(other.getPosition().getRealX()))
			return -1;
		return 0;
	}
}
