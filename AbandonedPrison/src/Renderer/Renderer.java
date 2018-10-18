package Renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

import GameWorld.Game;

/**
 * Renderer for a simple 3D game.
 *
 * @author Joel Harris
 */
public class Renderer {

	/**
	 * Width of the Canvas.
	 */
	public static final int CANVAS_WIDTH = 600;
	/**
	 * Height of the Canvas.
	 */
	public static final int CANVAS_HEIGHT = 600;
	/**
	 * Length of a Room.
	 */
	public static final int WHOLE_ROOM = 16;
	/**
	 * Half the length of a Room.
	 */
	public static final int HALF_ROOM = 8;
	/**
	 * One grid square in a Room.
	 */
	public static final int WHOLE_BLOCK = 4;
	/**
	 * Half of one grid square in a Room.
	 */
	public static final int HALF_BLOCK = 2;
	/**
	 * The number of Items that can be placed across a Room.
	 */
	public static final int ITEMS_SIZE = 4;

	private Point3D light;
	private JFrame frame;
	private Map<String, Mesh> meshes = new HashMap<String, Mesh>();
	private ArrayList<Room> rooms = new ArrayList<Room>();

	/**
	 * Renderer constructor.
	 */
	public Renderer() {
		this.light = new Point3D(0.49056706f, -0.63019001f, 0.4113221f);
	}

	/**
	 * Creates a Room at the given point with 0 - 4 walls and a 2d array of Items on
	 * the floor.
	 *
	 * @param position
	 *            center of the room
	 * @param northWall
	 *            whether there is a north wall
	 * @param southWall
	 *            whether there is a south wall
	 * @param eastWall
	 *            whether there is a east wall
	 * @param westWall
	 *            whether there is a west wall
	 * @param items
	 *            the 2d array of Items to be put on the floor
	 */
	public void createRoom(GameWorld.Room room, Point3D position, boolean northWall, boolean southWall,
			boolean eastWall, boolean westWall, ArrayList<GameWorld.Door> doors, AbstractItem[][] items) {
		ArrayList<AbstractWall> walls = new ArrayList<AbstractWall>();
		if (eastWall)
			walls.add(new SideWall(
					new Point3D(position.getRealX() + HALF_ROOM, position.getRealY(), position.getRealZ())));
		if (westWall)
			walls.add(new SideWall(
					new Point3D(position.getRealX() - HALF_ROOM, position.getRealY(), position.getRealZ())));
		if (northWall)
			walls.add(new FrontWall(
					new Point3D(position.getRealX(), position.getRealY(), position.getRealZ() + HALF_ROOM)));
		if (southWall)
			walls.add(new FrontWall(
					new Point3D(position.getRealX(), position.getRealY(), position.getRealZ() - HALF_ROOM)));
		for (GameWorld.Door door : room.getDoors()) {
			String str = door.getDirection();
			if (str.equals("N")) {
				FrontDoor currDoor = new FrontDoor(
						new Point3D(position.getRealX(), position.getRealY(), position.getRealZ() + HALF_ROOM));
				currDoor.setDoor(door);
				walls.add(currDoor);
			}
			if (str.equals("E")) {
				SideDoor currDoor = new SideDoor(
						new Point3D(position.getRealX() + HALF_ROOM, position.getRealY(), position.getRealZ()));
				currDoor.setDoor(door);
				walls.add(currDoor);
			}
			if (str.equals("S")) {
				FrontDoor currDoor = new FrontDoor(
						new Point3D(position.getRealX(), position.getRealY(), position.getRealZ() - HALF_ROOM));
				currDoor.setDoor(door);
				walls.add(currDoor);
			}
			if (str.equals("W")) {
				SideDoor currDoor = new SideDoor(
						new Point3D(position.getRealX() - HALF_ROOM, position.getRealY(), position.getRealZ()));
				currDoor.setDoor(door);
				walls.add(currDoor);
			}
		}
		for (int i = 0; i < ITEMS_SIZE; i++) {
			for (int j = 0; j < ITEMS_SIZE; j++) {
				if (items[i][j] != null)
					items[i][j].translate(position.getRealX(), 4, position.getRealZ());
			}
		}
		rooms.add(new Room(position, walls, items, new Floor(position)));
	}

	public void setGame(Game game) {
		ArrayList<GameWorld.Room> rooms = (ArrayList<GameWorld.Room>) game.getRooms();
		for (GameWorld.Room room : rooms) {
			ArrayList<GameWorld.Door> doors = new ArrayList<GameWorld.Door>();
			boolean n = false, e = false, s = false, w = false;
			int roomX = room.getLocation().getX() * HALF_ROOM;
			int roomZ = room.getLocation().getY() * HALF_ROOM;
			for (String str : room.getWalls()) {
				if (str.equals("N"))
					n = true;
				if (str.equals("E"))
					e = true;
				if (str.equals("S"))
					s = true;
				if (str.equals("W"))
					w = true;
			}
			AbstractItem[][] items = new AbstractItem[4][4];
			for (GameWorld.Item item : room.getItems()) {
				int itemX = item.getItemLocation().getX();
				int itemY = item.getItemLocation().getY();
				items[itemX][itemY] = createItem(item);
			}
			createRoom(room, new Point3D(roomX, 0, -roomZ), n, s, e, w, doors, items);
		}
	}
	
	public AbstractItem createItem(GameWorld.Item item) {
		int itemX = item.getItemLocation().getX();
		int itemY = item.getItemLocation().getY();
		AbstractItem currItem = null;
		if (item.getItemName().equals("Key")) {
			currItem = new Key(new Point3D(-(WHOLE_BLOCK + HALF_BLOCK) + (WHOLE_BLOCK * itemX), -1,
					-(WHOLE_BLOCK + HALF_BLOCK) + (WHOLE_BLOCK * itemY)));
		}
		if (item.getItemName().equals("WoodenCrate")) {
			currItem = new WoodenCrate(
					new Point3D(-(WHOLE_BLOCK + HALF_BLOCK) + (WHOLE_BLOCK * itemX), 0,
							-(WHOLE_BLOCK + HALF_BLOCK) + (WHOLE_BLOCK * itemY)));
		}
		if (item.getItemName().equals("MetalCrate")) {
			currItem = new MetalCrate(
					new Point3D(-(WHOLE_BLOCK + HALF_BLOCK) + (WHOLE_BLOCK * itemX), 0,
							-(WHOLE_BLOCK + HALF_BLOCK) + (WHOLE_BLOCK * itemY)));
		}
		currItem.setItem(item);
		return currItem;
	}

	public AbstractItem clickItem(Point click) {
		AbstractItem item = null;
		Room room = getCurrentRoom();
		if (room != null) {
			PriorityQueue<AbstractItem> items = room.orderItems();
			while (!items.isEmpty()) {
				AbstractItem currItem = items.poll();
				if (currItem.getPosition().getRealZ() >= 0) {
					PriorityQueue<Polygon3D> polys = currItem.getMesh().orderPolygons();
					while (!polys.isEmpty()) {
						Polygon3D poly = polys.poll();
						Polygon currPoly = new Polygon(poly.xPoints3D(), poly.yPoints3D(), 3);
						if (currPoly.contains(click)) {
							item = currItem;
						}
					}
				}
			}
		}
		return item;
	}

	public AbstractDoor clickDoor(Point click) {
		AbstractDoor door = null;
		Room room = getCurrentRoom();
		if (room != null) {
			for (AbstractWall wall : room.getWalls()) {
				if (wall instanceof AbstractDoor) {
					AbstractDoor currDoor = (AbstractDoor) wall;
					if (currDoor.getPosition().getRealZ() >= 0) {
						PriorityQueue<Polygon3D> polys = currDoor.getMesh().orderPolygons();
						while (!polys.isEmpty()) {
							Polygon3D poly = polys.poll();
							Polygon currPoly = new Polygon(poly.xPoints3D(), poly.yPoints3D(), 3);
							if (currPoly.contains(click)) {
								door = currDoor;
							}
						}
					}
				}
			}
		}
		return door;
	}
	
	public void pickupItem(GameWorld.Item item) {
		Room room = getCurrentRoom();
		room.removeItem(item);
	}
	
	public void putDownItem(GameWorld.Item item) {
		Room room = getCurrentRoom();
		AbstractItem currItem = createItem(item);
		room.addItem(currItem);
	}
	
	public void unlockDoor(AbstractDoor door) {
		door.openDoor();
	}

	/**
	 * Parses and loads a Mesh from a .txt File.
	 *
	 * @param file
	 *            the File to be parsed
	 * @return the Mesh
	 */
	static public Mesh loadMesh(File file) {
		Polygon3D[] polygons;
		Point3D[] vertices;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			String[] tokens = line.split("\\ ");
			int verts = Integer.parseInt(tokens[2]);
			vertices = new Point3D[verts];
			line = br.readLine();
			tokens = line.split("\\ ");
			int polys = Integer.parseInt(tokens[2]);
			polygons = new Polygon3D[polys];
			for (int i = 0; i < verts; i++) {
				line = br.readLine();
				tokens = line.split("\\ ");
				float x = Float.parseFloat(tokens[0]);
				float y = Float.parseFloat(tokens[1]);
				float z = Float.parseFloat(tokens[2]);
				vertices[i] = new Point3D(x, y, z);
			}
			for (int i = 0; i < polys; i++) {
				line = br.readLine();
				tokens = line.split("\\ ");
				Point3D a = vertices[Integer.parseInt(tokens[1])];
				Point3D b = vertices[Integer.parseInt(tokens[2])];
				Point3D c = vertices[Integer.parseInt(tokens[3])];
				float[] xPoints = new float[] { a.getRealX(), b.getRealX(), c.getRealX() };
				float[] yPoints = new float[] { a.getRealY(), b.getRealY(), c.getRealY() };
				float[] zPoints = new float[] { a.getRealZ(), b.getRealZ(), c.getRealZ() };
				Polygon3D poly = new Polygon3D(xPoints, yPoints, zPoints, 3);
				polygons[i] = poly;
			}
			br.close();
			return new Mesh(polygons);
		} catch (IOException e) {
			throw new RuntimeException("file reading failed.");
		}
	}

	/**
	 * Moves the Perspective forward by one Room.
	 */
	public void moveForward() {
		for (Room room : rooms) {
			room.translate(0, 0, -WHOLE_ROOM);
		}
	}

	/**
	 * Moves the Perspective backward by one Room.
	 */
	public void moveBackward() {
		for (Room room : rooms) {
			room.translate(0, 0, WHOLE_ROOM);
		}
	}

	/**
	 * Rotates the Perspective by 90 degrees to the left.
	 */
	public void rotateLeft() {
		light.rotateRight();
		for (Room room : rooms) {
			room.rotateRight();
		}
	}

	/**
	 * Rotates the Perspective by 90 degrees to the right.
	 */
	public void rotateRight() {
		light.rotateLeft();
		for (Room room : rooms) {
			room.rotateLeft();
		}
	}

	/**
	 * Uses the compareTo method in Room to order the Rooms using a PriorityQueue.
	 *
	 * @return PriorityQueue of Rooms
	 */
	public PriorityQueue<Room> orderRooms() {
		PriorityQueue<Room> orderedRooms = new PriorityQueue<Room>();
		for (Room room : rooms) {
			orderedRooms.add(room);
		}
		return orderedRooms;
	}

	/**
	 * Is called every time the drawing canvas is drawn. This will print the
	 * polygons on the screen.
	 */
	public void render(Graphics g) {
		PriorityQueue<Room> orderedRooms = orderRooms();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Renderer.CANVAS_WIDTH, Renderer.CANVAS_HEIGHT);
		while (!orderedRooms.isEmpty()) {
			Room room = orderedRooms.poll();
			renderWalls(g, room);
			renderFloor(g, room);
			renderItems(g, room);
		}
	}

	/**
	 * Renders the Floor of a Room to a Graphics object. Utilizes methods in
	 * Pipeline.
	 *
	 * @param g
	 *            canvas for drawing
	 * @param room
	 *            room whose floor is to be drawn
	 */
	private void renderFloor(Graphics g, Room room) {
		PriorityQueue<Polygon3D> orderedPolygons = room.getFloor().getMesh().orderPolygons();
		while (!orderedPolygons.isEmpty()) {
			Polygon3D poly = orderedPolygons.poll();
			if (!Pipeline.isHidden(poly) && poly.getPosition().getRealZ() > 0) {
				g.setColor(Pipeline.getShading(poly,
						new float[] { light.getRealX(), light.getRealY(), light.getRealZ() }));
				g.fillPolygon(poly.xPoints3D(), poly.yPoints3D(), poly.getnPoints());
			}
		}
	}

	/**
	 * Renders the Walls of a Room to a Graphics object. Utilizes methods in
	 * Pipeline.
	 *
	 * @param g
	 *            canvas for drawing
	 * @param room
	 *            room whose walls are to be drawn
	 */
	private void renderWalls(Graphics g, Room room) {
		for (AbstractWall wall : room.getWalls()) {
			PriorityQueue<Polygon3D> polys = wall.getMesh().orderPolygons();
			while (!polys.isEmpty()) {
				Polygon3D poly = polys.poll();
				if (!Pipeline.isHidden(poly) && poly.getPosition().getRealZ() > 0) {
					g.setColor(Pipeline.getShading(poly,
							new float[] { light.getRealX(), light.getRealY(), light.getRealZ() }));
					g.fillPolygon(poly.xPoints3D(), poly.yPoints3D(), poly.getnPoints());
				}
			}
		}
	}

	/**
	 * Renders the Items in a Room to a Graphics object. Utilizes methods in
	 * Pipeline.
	 *
	 * @param g
	 *            canvas for drawing
	 * @param room
	 *            room whose items are to be drawn
	 */
	private void renderItems(Graphics g, Room room) {
		PriorityQueue<AbstractItem> items = room.orderItems();
		while (!items.isEmpty()) {
			AbstractItem item = items.poll();
			if (item != null) {
				PriorityQueue<Polygon3D> orderedPolygons = item.getMesh().orderPolygons();
				while (!orderedPolygons.isEmpty()) {
					Polygon3D poly = orderedPolygons.poll();
					if (!Pipeline.isHidden(poly) && poly.getPosition().getRealZ() > 0) {
						g.setColor(Pipeline.getShading(poly,
								new float[] { light.getRealX(), light.getRealY(), light.getRealZ() }));
						g.fillPolygon(poly.xPoints3D(), poly.yPoints3D(), poly.getnPoints());
					}
				}
			}
		}
	}

	public Room getCurrentRoom() {
		for (Room room : rooms) {
			Point3D pos = room.getPosition();
			if (pos.getRealX() == 0 && pos.getRealZ() == 0)
				return room;
		}
		return null;
	}

	/**
	 * @return the drawing canvas
	 */
	public JFrame getDrawing() {
		return frame;
	}

	/**
	 * @return the meshes
	 */
	public Map<String, Mesh> getMeshes() {
		return this.meshes;
	}

	/**
	 * @return the rooms
	 */
	public ArrayList<Room> getRooms() {
		return rooms;
	}

	/**
	 * @param rooms
	 *            the room to add
	 */
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
}
