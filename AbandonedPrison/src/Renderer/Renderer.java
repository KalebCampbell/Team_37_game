package Renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
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
	 * Quarter the length of a Room.
	 */
	public static final int QUARTER_ROOM = 4;
	/**
	 * The number of Items that can be places across a Room.
	 */
	public static final int ITEMS_SIZE = 4;
	/**
	 * Dimension of the drawing canvas.
	 */
	private static final Dimension DRAWING_SIZE = new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);

	private Point3D light;
	private JFrame frame;
	private Map<String, Mesh> meshes = new HashMap<String, Mesh>();
	private ArrayList<Room> rooms = new ArrayList<Room>();

	/**
	 * Renderer constructor.
	 */
	public Renderer() {
		this.light = new Point3D(0.29056706f, -0.43019001f, -0.9113221f);
		init();
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
	public void createRoom(Point3D position, boolean northWall, boolean southWall, boolean eastWall, boolean westWall,
			Item[][] items) {
		ArrayList<Wall> walls = new ArrayList<Wall>();
		if (eastWall)
			walls.add(new Wall(meshes.get("sidewall").getCopy(),
					new Point3D(position.getRealX() + HALF_ROOM, position.getRealY(), position.getRealZ())));
		if (westWall)
			walls.add(new Wall(meshes.get("sidewall").getCopy(),
					new Point3D(position.getRealX() - HALF_ROOM, position.getRealY(), position.getRealZ())));
		if (northWall)
			walls.add(new Wall(meshes.get("frontwall").getCopy(),
					new Point3D(position.getRealX(), position.getRealY(), position.getRealZ() + HALF_ROOM)));
		if (southWall)
			walls.add(new Wall(meshes.get("frontwall").getCopy(),
					new Point3D(position.getRealX(), position.getRealY(), position.getRealZ() - HALF_ROOM)));
		for (int i = 0; i < ITEMS_SIZE; i++) {
			for (int j = 0; j < ITEMS_SIZE; j++) {
				if (items[i][j] != null)
					items[i][j].translate(position.getRealX(), 4, position.getRealZ());
			}
		}
		rooms.add(new Room(position, walls, items, new Floor(meshes.get("floor").getCopy(), position)));
	}

	/**
	 * Parses each File and puts them into a Map from String to Mesh. To be used for
	 * creating Rooms.
	 */
	private void init() {
		meshes.put("frontwall", loadMesh(new File("frontwall.txt")));
		meshes.put("sidewall", loadMesh(new File("sidewall.txt")));
		meshes.put("floor", loadMesh(new File("floor.txt")));
		meshes.put("key", loadMesh(new File("key.txt")));
	}

	/**
	 * Parses and loads a Mesh from a .txt File.
	 * 
	 * @param file
	 *            the File to be parsed
	 * @return the Mesh
	 */
	private Mesh loadMesh(File file) {
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
	 * Redraws the Canvas.
	 */
	public void redraw() {
		frame.repaint();
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
		g.setColor(Color.DARK_GRAY);
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
		for (Wall wall : room.getWalls()) {
			for (Polygon3D poly : wall.getMesh().getPolygons()) {
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
		for (Item[] items : room.getItems()) {
			for (Item item : items) {
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
	}

	@SuppressWarnings("serial")
	private void initialiseGUI() {
		
		frame = new JFrame();
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
		frame.setSize(new Dimension(DRAWING_SIZE.width, DRAWING_SIZE.height));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComponent drawing = new JComponent() {
			protected void paintComponent(Graphics g) {
				render(g);
			}
		};

		drawing.setPreferredSize(DRAWING_SIZE);
		drawing.setMinimumSize(DRAWING_SIZE);
		drawing.setMaximumSize(DRAWING_SIZE);
		drawing.setVisible(true);

		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent ev) {
				if (ev.getID() == KeyEvent.KEY_PRESSED) {
					redraw();
				}
				return true;
			}
		});

		frame.add(drawing);

		frame.pack();
		frame.setVisible(true);
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
	public Map<String, Mesh> getMeshes(){
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
