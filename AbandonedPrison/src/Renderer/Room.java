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

	private ArrayList<AbstractWall> walls = new ArrayList<AbstractWall>();
	private AbstractItem[][] items = new AbstractItem[4][4];
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
	public Room(Point3D position, ArrayList<AbstractWall> walls, AbstractItem[][] items, Floor floor) {
		this.position = new Point3D(0, 0, 0);
		this.walls = walls;
		this.items = items;
		this.floor = floor;
		translate(position.getRealX(), position.getRealY(), position.getRealZ());
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
	public void translate(float x, float y, float z) {
		// translate walls
		for (AbstractWall wall : walls) {
			wall.translate(x, y, z);
		}
		// translate items
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (items[i][j] != null)
					items[i][j].translate(x, y, z);
			}
		}
		// translate floor and pos
		floor.translate(x, y, z);
		position.translate(x, y, z);
	}

	/**
	 * Rotates the Room 90 degrees to left.
	 */
	public void rotateLeft() {
		// rotate walls left
		for (AbstractWall wall : walls) {
			wall.rotateLeft();
		}
		// rotate items left
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (items[i][j] != null)
					items[i][j].rotateLeft();
			}
		}
		// rotate floor and pos left
		floor.rotateLeft();
		position.rotateLeft();
	}

	/**
	 * Rotates the Room 90 degrees to right.
	 */
	public void rotateRight() {
		// rotate walls right
		for (AbstractWall wall : walls) {
			wall.rotateRight();
		}
		// rotate items right
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (items[i][j] != null)
					items[i][j].rotateRight();
			}
		}
		// rotate floor and pos right
		floor.rotateRight();
		position.rotateRight();
	}

	/**
	 * Uses the compareTo method in Item to order the Items within a Room using a
	 * PriorityQueue.
	 * 
	 * @return PriorityQueue of Items
	 */
	public PriorityQueue<AbstractItem> orderItems() {
		PriorityQueue<AbstractItem> orderedItems = new PriorityQueue<AbstractItem>();
		AbstractItem[][] items = this.items;
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items.length; j++) {
				//for each item, checks if it isn't null and adds it to queue
				if (items[i][j] != null) {
					if (items[i][j].getPosition().getRealZ() >= 0) {
						orderedItems.add(items[i][j]);
					}
				}
			}
		}
		return orderedItems;
	}

	/**
	 * @return the walls
	 */
	public ArrayList<AbstractWall> getWalls() {
		return walls;
	}

	/**
	 * @return the items
	 */
	public AbstractItem[][] getItems() {
		return items;
	}

	/**
	 * Removes an item from a Room, if it's present.
	 * 
	 * @param item
	 *            to be removed
	 */
	public void removeItem(GameWorld.Item item) {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items.length; j++) {
				if (items[i][j] != null) {
					if (items[i][j].getItem().equals(item))
						items[i][j] = null;
				}
			}
		}
	}

	/**
	 * Adds an item to a Room.
	 * 
	 * @param item
	 *            to be added
	 */
	public void addItem(AbstractItem item) {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items.length; j++) {
				if (items[i][j] == null) {
					item.translate(-(Renderer.WHOLE_BLOCK + Renderer.HALF_BLOCK) + (Renderer.WHOLE_BLOCK * i), 4,
					-(Renderer.WHOLE_BLOCK + Renderer.HALF_BLOCK) + (Renderer.WHOLE_BLOCK * i));
					items[i][j] = item;
					return;
				}
			}
		}
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
		// compares on z axis first
		if (this.position.getRealZ() > other.getPosition().getRealZ())
			return -1;
		if (this.position.getRealZ() < other.getPosition().getRealZ())
			return 1;
		// then compares on x axis
		if (Math.abs(this.position.getRealX()) > Math.abs(other.getPosition().getRealX()))
			return 1;
		if (Math.abs(this.position.getRealX()) < Math.abs(other.getPosition().getRealX()))
			return -1;
		return 0;
	}
}
