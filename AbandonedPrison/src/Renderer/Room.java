package Renderer;

import java.util.ArrayList;

public class Room {

	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private Item[][] items = new Item[4][4];
	private Floor floor;
	private Point3D position;

	public Room(Point3D position, ArrayList<Wall> walls, Item[][] items, Floor floor) {
		this.position = new Point3D(0, 0, 0);
		this.walls = walls;
		this.items = items;
		this.floor = floor;
		translate((int)position.getX(), (int)position.getY(), position.getZ());
	}

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

	public int compareTo(Room other) {
		if (this.getPosition().getZ() > other.getPosition().getZ())
			return 1;
		else if (this.getPosition().getZ() < other.getPosition().getZ())
			return -1;
		return 0;
	}
}
