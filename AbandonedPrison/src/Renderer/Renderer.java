package Renderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * GUI for a 3D renderer.
 * 
 * @author Joel Harris
 */
public class Renderer {

	/**
	 * Width of the canvas.
	 */
	public static final int CANVAS_WIDTH = 600;
	/**
	 * Height of the canvas.
	 */
	public static final int CANVAS_HEIGHT = 600;

	public static final int HALF_ROOM = 8;

	private Light light;
	private JFrame frame;
	private static final Dimension DRAWING_SIZE = new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
	private Map<String, Mesh> meshes = new HashMap<String, Mesh>();
	private ArrayList<Room> rooms = new ArrayList<Room>();

	/**
	 * Renderer constructor.
	 */
	public Renderer() {
		this.light = new Light(0.29056706f, -0.43019001f, -0.9113221f);
		init();
		createRoom();
		initialiseGUI();
	}

	public void createRoom(Point3D position, boolean northWall, boolean southWall, boolean eastWall, boolean westWall,
			Item[][] items) {
		ArrayList<Wall> walls = new ArrayList<Wall>();
		if (eastWall)
			walls.add(new Wall(meshes.get("sidewall").getCopy(),
					new Point3D((int) position.getX() + HALF_ROOM, (int) position.getY(), position.getZ())));
		if (westWall)
			walls.add(new Wall(meshes.get("sidewall").getCopy(),
					new Point3D((int) position.getX() - HALF_ROOM, (int) position.getY(), position.getZ())));
		if (northWall)
			walls.add(new Wall(meshes.get("frontwall").getCopy(),
					new Point3D((int) position.getX(), (int) position.getY(), position.getZ() + HALF_ROOM)));
		if (southWall)
			walls.add(new Wall(meshes.get("frontwall").getCopy(),
					new Point3D((int) position.getX(), (int) position.getY(), position.getZ() - HALF_ROOM)));
		rooms.add(new Room(position, walls, items, new Floor(meshes.get("floor").getCopy(), position)));
	}

	public void createRoom() {
		Floor floor = new Floor(meshes.get("floor").getCopy(), new Point3D(0, 0, 0));
		Wall northWall = new Wall(meshes.get("frontwall").getCopy(), new Point3D(0, 0, 8));
		Mesh mesh = meshes.get("sidewall").getCopy();
		Wall eastWall = new Wall(meshes.get("sidewall").getCopy(), new Point3D(8, 0, 0));
		Wall westWall = new Wall(mesh.getCopy(), new Point3D(-8, 0, 0));
		ArrayList<Wall> walls = new ArrayList<Wall>();
		walls.add(northWall);
		walls.add(eastWall);
		walls.add(westWall);
		Item[][] items = new Item[4][4];
		Room room = new Room(new Point3D(0, 0, 16), walls, items, floor);
		rooms.add(room);
	}

	private void init() {
		meshes.put("frontwall", loadMesh(new File("frontwall.txt")));
		meshes.put("sidewall", loadMesh(new File("sidewall.txt")));
		meshes.put("floor", loadMesh(new File("floor.txt")));
		meshes.put("key", loadMesh(new File("key.txt")));
	}

	/**
	 * Is called when the program starts and is used to preload meshes.
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
				int x = (int) Float.parseFloat(tokens[0]);
				int y = (int) Float.parseFloat(tokens[1]);
				int z = (int) Float.parseFloat(tokens[2]);
				vertices[i] = new Point3D(x, y, z);
			}
			for (int i = 0; i < polys; i++) {
				line = br.readLine();
				tokens = line.split("\\ ");
				Point3D a = vertices[Integer.parseInt(tokens[1])];
				Point3D b = vertices[Integer.parseInt(tokens[2])];
				Point3D c = vertices[Integer.parseInt(tokens[3])];
				int[] xPoints = new int[] { a.x, b.x, c.x };
				int[] yPoints = new int[] { a.y, b.y, c.y };
				int[] zPoints = new int[] { a.z, b.z, c.z };
				Polygon3D poly = new Polygon3D(xPoints, yPoints, zPoints, 3);
				polygons[i] = poly;
			}
			br.close();

			for (int i = 0; i < polygons.length; i++) {
				for (int j = 0; j < polygons.length; j++) {

				}
			}
			return new Mesh(polygons);
		} catch (IOException e) {
			throw new RuntimeException("file reading failed.");
		}
	}

	/**
	 * Forces a redraw of the drawing canvas. This is called for you, so you don't
	 * need to call this unless you modify this GUI.
	 */
	public void redraw() {
		frame.repaint();
	}

	/**
	 * Is called every time the user presses a key. This can be used for moving the
	 * player around.
	 */
	private void onKeyPress(KeyEvent ev) {
		if (ev.getKeyChar() == 'a') {
			light.rotateRight();
			for (Room room : rooms) {
				room.rotateRight();
			}
		}
		if (ev.getKeyChar() == 'd') {
			light.rotateLeft();
			for (Room room : rooms) {
				room.rotateLeft();
			}
		}
		if (ev.getKeyChar() == 'w') {
			for (Room room : rooms) {
				room.translate(0, 0, -8);
			}
		}
		if (ev.getKeyChar() == 's') {
			for (Room room : rooms) {
				room.translate(0, 0, 8);
			}
		}
	}

	private PriorityQueue<Room> orderRooms() {
		PriorityQueue<Room> orderedRooms = new PriorityQueue<Room>();
		for (Room room : rooms) {
			if (room.getPosition().getZ() >= 0) {
				orderedRooms.add(room);
			}
		}
		return orderedRooms;
	}

	/**
	 * Is called every time the drawing canvas is drawn. This will print the
	 * polygons on the screen.
	 */
	private void render(Graphics g) {
		PriorityQueue<Room> orderedRooms = orderRooms();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Renderer.CANVAS_WIDTH, Renderer.CANVAS_HEIGHT);
		while (!orderedRooms.isEmpty()) {
			Room room = orderedRooms.poll();
			for (Wall wall : room.getWalls()) {
				for (Polygon3D poly : wall.getMesh().getPolygons()) {
					if (!Pipeline.isHidden(poly) && wall.getMesh().getPosition().getZ() >= 0) {
						g.setColor(Pipeline.getShading(poly, new float[] { light.getX(), light.getY(), light.getZ() },
								null, null));
						g.fillPolygon(poly.xPoints3D(), poly.yPoints3D(), poly.npoints);
					}
				}
			}
			for (Polygon3D poly : room.getFloor().getMesh().getPolygons()) {
				g.setColor(Pipeline.getShading(poly, new float[] { light.getX(), light.getY(), light.getZ() }, null,
						null));
				g.fillPolygon(poly.xPoints3D(), poly.yPoints3D(), poly.npoints);
			}
			Item[][] items = room.getItems();
			for (Polygon3D poly : items[0][0].getMesh().getPolygons()) {
				if (!Pipeline.isHidden(poly)) {
					g.setColor(Pipeline.getShading(poly, new float[] { light.getX(), light.getY(), light.getZ() }, null,
							null));
					g.fillPolygon(poly.xPoints3D(), poly.yPoints3D(), poly.npoints);
				}
			}
		}
	}

	@SuppressWarnings("serial")
	private void initialiseGUI() {
		// make the frame
		frame = new JFrame();
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
		frame.setSize(new Dimension(DRAWING_SIZE.width, DRAWING_SIZE.height));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set up the drawing canvas, hook it into the render() method, and give
		// it a nice default if render() returns null.
		JComponent drawing = new JComponent() {
			protected void paintComponent(Graphics g) {
				render(g);
			}
		};
		// fix its size
		drawing.setPreferredSize(DRAWING_SIZE);
		drawing.setMinimumSize(DRAWING_SIZE);
		drawing.setMaximumSize(DRAWING_SIZE);
		drawing.setVisible(true);

		// this is not a best-practices way of doing key listening; instead you
		// should use either a KeyListener or an InputMap/ActionMap combo. but
		// this method neatly avoids any focus issues (KeyListener) and requires
		// less effort on your part (ActionMap).
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent ev) {
				if (ev.getID() == KeyEvent.KEY_PRESSED) {
					onKeyPress(ev);
					redraw();
				}
				return true;
			}
		});

		// put it all together.
		frame.add(drawing);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Renderer();
	}
}
