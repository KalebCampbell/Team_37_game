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
  private Map<String, Mesh> meshes = new HashMap<String, Mesh>();
  private ArrayList<Room> rooms = new ArrayList<Room>();

  /**
   * Renderer constructor.
   */
  public Renderer() {
    this.light = new Point3D(0.49056706f, -0.63019001f, 0.4113221f);
  }

  /**
   * Creates a Room at the given point with 0 - 4 walls and a 2d array of Items on the floor.
   *
   * @param room
   *          the GameWorld room associated with this room
   * @param position
   *          center of the room
   * @param northWall
   *          whether there is a north wall
   * @param southWall
   *          whether there is a south wall
   * @param eastWall
   *          whether there is a east wall
   * @param westWall
   *          whether there is a west wall
   * @param doors
   *          the doors associated with this room
   * @param items
   *          the 2d array of Items to be put on the floor
   */
  public void createRoom(GameWorld.Room room, Point3D position, boolean northWall,
      boolean southWall, boolean eastWall, boolean westWall, ArrayList<GameWorld.Door> doors,
      AbstractItem[][] items) {
    ArrayList<AbstractWall> walls = new ArrayList<AbstractWall>();
    // checks if there is a wall in each direction and if there is, makes one
    if (eastWall) {
      walls.add(new SideWall(
          new Point3D(position.getRealX() + HALF_ROOM, position.getRealY(), position.getRealZ())));
    }
    if (westWall) {
      walls.add(new SideWall(
          new Point3D(position.getRealX() - HALF_ROOM, position.getRealY(), position.getRealZ())));
    }
    if (northWall) {
      walls.add(new FrontWall(
          new Point3D(position.getRealX(), position.getRealY(), position.getRealZ() + HALF_ROOM)));
    }
    if (southWall) {
      walls.add(new FrontWall(
          new Point3D(position.getRealX(), position.getRealY(), position.getRealZ() - HALF_ROOM)));
    }
    // iterates through the GameWorld.Door's and creates the Renderer versions as
    // necessary
    for (GameWorld.Door door : room.getDoors()) {
      String str = door.getDirection();
      Boolean open = !door.isLocked();
      if (str.equals("N")) {
        FrontDoor currDoor = new FrontDoor(
            new Point3D(position.getRealX(), position.getRealY(), position.getRealZ() + HALF_ROOM));
        currDoor.setDoor(door);
        if (open) {
          currDoor.openDoor();
        }
        walls.add(currDoor);
      }
      if (str.equals("E")) {
        SideDoor currDoor = new SideDoor(
            new Point3D(position.getRealX() + HALF_ROOM, position.getRealY(), position.getRealZ()));
        currDoor.setDoor(door);
        if (open) {
          currDoor.openDoor();
        }
        walls.add(currDoor);
      }
      if (str.equals("S")) {
        FrontDoor currDoor = new FrontDoor(
            new Point3D(position.getRealX(), position.getRealY(), position.getRealZ() - HALF_ROOM));
        currDoor.setDoor(door);
        if (open) {
          currDoor.openDoor();
        }
        walls.add(currDoor);
      }
      if (str.equals("W")) {
        SideDoor currDoor = new SideDoor(
            new Point3D(position.getRealX() - HALF_ROOM, position.getRealY(), position.getRealZ()));
        currDoor.setDoor(door);
        if (open) {
          currDoor.openDoor();
        }
        walls.add(currDoor);
      }
    }
    // translates each item to match the rooms position
    for (int i = 0; i < ITEMS_SIZE; i++) {
      for (int j = 0; j < ITEMS_SIZE; j++) {
        if (items[i][j] != null) {
          items[i][j].translate(position.getRealX(), 4, position.getRealZ());
        }
      }
    }
    Room newRoom = new Room(position, walls, items, new Floor(position));
    newRoom.setRoom(room);
    rooms.add(newRoom);
  }

  /**
   * Used to load a map from a game object.
   * 
   * @param game
   *          the game to be loaded
   */
  public void setGame(Game game) {
    ArrayList<GameWorld.Room> rooms = (ArrayList<GameWorld.Room>) game.getRooms();
    for (GameWorld.Room room : rooms) {
      ArrayList<GameWorld.Door> doors = new ArrayList<GameWorld.Door>();
      boolean n = false;
      boolean e = false;
      boolean s = false;
      boolean w = false;
      // translate from GameWorld coords to Renderer coords
      int roomX = room.getLocation().getX() * HALF_ROOM;
      int roomZ = room.getLocation().getY() * HALF_ROOM;
      // checks for walls in each direction
      for (String str : room.getWalls()) {
        if (str.equals("N")) {
          n = true;
        }
        if (str.equals("E")) {
          e = true;
        }
        if (str.equals("S")) {
          s = true;
        }
        if (str.equals("W")) {
          w = true;
        }
      }
      AbstractItem[][] items = new AbstractItem[4][4];
      // creates items
      for (GameWorld.Item item : room.getItems()) {
        int itemX = item.getItemLocation().getX();
        int itemY = item.getItemLocation().getY();
        items[itemX][itemY] = createItemFromGame(item);
      }
      createRoom(room, new Point3D(roomX, 0, -roomZ), n, s, e, w, doors, items);
    }
    GameWorld.Player player = game.getPlayer();
    GameWorld.Location loc = player.getPlayerLocation();
    int x = loc.getX();
    int z = -loc.getY();
    for (Room room : this.rooms) {
      room.translate(-x * WHOLE_ROOM, 0, -z * WHOLE_ROOM);
    }
  }

  /**
   * Creates and returns an item associated with a GameWorld.Item and translates to position.
   * 
   * @param item
   *          item to be made
   * @return created item
   */
  public AbstractItem createItemFromGame(GameWorld.Item item) {
    int itemX = item.getItemLocation().getX();
    int itemY = item.getItemLocation().getY();
    AbstractItem currItem = null;
    // translates the item based on its GameWorld coords to fit the grid system
    if (item.getItemName().equals("Key")) {
      currItem = new Key(new Point3D(-(WHOLE_BLOCK + HALF_BLOCK) + (WHOLE_BLOCK * itemX), -1,
          -(WHOLE_BLOCK + HALF_BLOCK) + (WHOLE_BLOCK * itemY)));
    }
    if (item.getItemName().equals("WoodenCrate")) {
      currItem = new WoodenCrate(new Point3D(-(WHOLE_BLOCK + HALF_BLOCK) + (WHOLE_BLOCK * itemX), 0,
          -(WHOLE_BLOCK + HALF_BLOCK) + (WHOLE_BLOCK * itemY)));
    }
    if (item.getItemName().equals("MetalCrate")) {
      currItem = new MetalCrate(new Point3D(-(WHOLE_BLOCK + HALF_BLOCK) + (WHOLE_BLOCK * itemX), 0,
          -(WHOLE_BLOCK + HALF_BLOCK) + (WHOLE_BLOCK * itemY)));
    }
    currItem.setItem(item);
    return currItem;
  }

  /**
   * Creates and returns an item associated with a GameWorld.Item without translating.
   * 
   * @param item
   *          item to be made
   * @return created item
   */
  public AbstractItem createItem(GameWorld.Item item) {
    AbstractItem currItem = null;
    // translates the item without translating at all
    if (item.getItemName().equals("Key")) {
      currItem = new Key(new Point3D(0, -1, 0));
    }
    if (item.getItemName().equals("WoodenCrate")) {
      currItem = new WoodenCrate(new Point3D(0, 0, 0));
    }
    if (item.getItemName().equals("MetalCrate")) {
      currItem = new MetalCrate(new Point3D(0, 0, 0));
    }
    currItem.setItem(item);
    return currItem;
  }

  /**
   * Returns the item at the given Point on the screen - if there is one.
   * 
   * @param click
   *          the coords to be checked
   * @return the item at the clicks coords on the screen
   */
  public AbstractItem clickItem(Point click) {
    AbstractItem item = null;
    Room room = getCurrentRoom();
    if (room != null) {
      // iterates through items from farthest to closest
      PriorityQueue<AbstractItem> items = room.orderItems();
      while (!items.isEmpty()) {
        AbstractItem currItem = items.poll();
        if (currItem.getPosition().getRealZ() >= 0) {
          PriorityQueue<Polygon3D> polys = currItem.getMesh().orderPolygons();
          while (!polys.isEmpty()) {
            // converts to Java Polygon to make use of .contains() method
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

  /**
   * Returns the door at the given Point on the screen - if there is one.
   * 
   * @param click
   *          the coords to be checked
   * @return the door at the clicks coords on the screen
   */
  public AbstractDoor clickDoor(Point click) {
    AbstractDoor door = null;
    for (Room room : rooms) {
      for (AbstractWall wall : room.getWalls()) {
        // checks if a wall is door
        if (wall instanceof AbstractDoor) {
          AbstractDoor currDoor = (AbstractDoor) wall;
          // if the door is visible
          if (currDoor.getPosition().getRealZ() >= 0) {
            PriorityQueue<Polygon3D> polys = currDoor.getMesh().orderPolygons();
            while (!polys.isEmpty()) {
              // converts to Java Polygon to make use of .contains() method
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

  /**
   * Removes item from current Room.
   * 
   * @param item
   *          to be removed
   */
  public void pickupItem(GameWorld.Item item) {
    Room room = getCurrentRoom();
    room.removeItem(item);
  }

  /**
   * Adds item to Room in free space.
   * 
   * @param item
   *          to be added.
   */
  public void putDownItem(GameWorld.Item item) {
    Room room = getCurrentRoom();
    AbstractItem currItem = createItem(item);
    room.addItem(currItem);
  }

  /**
   * Unlocks door.
   * 
   * @param door
   *          to be unlocked
   */
  public void unlockDoor(AbstractDoor door, Game game) {
    door.openDoor();
  }

  /**
   * Parses and loads a Mesh from a .txt File.
   *
   * @param file
   *          the File to be parsed
   * @return the Mesh
   */
  public static Mesh loadMesh(File file) {
    Polygon3D[] polygons = new Polygon3D[0];
    Point3D[] vertices;
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line = br.readLine();
      if (line != null) {
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
          // axis values for vertex
          float x = Float.parseFloat(tokens[0]);
          float y = Float.parseFloat(tokens[1]);
          float z = Float.parseFloat(tokens[2]);
          vertices[i] = new Point3D(x, y, z);
        }
        for (int i = 0; i < polys; i++) {
          line = br.readLine();
          tokens = line.split("\\ ");
          // gets vertices for this polygon from previously loaded vertices
          Point3D a = vertices[Integer.parseInt(tokens[1])];
          Point3D b = vertices[Integer.parseInt(tokens[2])];
          Point3D c = vertices[Integer.parseInt(tokens[3])];
          // transforms into correct structure for creating Polygon3D
          float[] pointsX = new float[] { a.getRealX(), b.getRealX(), c.getRealX() };
          float[] pointsY = new float[] { a.getRealY(), b.getRealY(), c.getRealY() };
          float[] pointsZ = new float[] { a.getRealZ(), b.getRealZ(), c.getRealZ() };
          Polygon3D poly = new Polygon3D(pointsX, pointsY, pointsZ, 3);
          polygons[i] = poly;
        }
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
   * Is called every time the drawing canvas is drawn. This will print the polygons on the screen.
   * 
   * @param g
   *          graphics object to be drawn on
   */
  public void render(Graphics g) {
    PriorityQueue<Room> orderedRooms = orderRooms();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, Renderer.CANVAS_WIDTH, Renderer.CANVAS_HEIGHT);
    while (!orderedRooms.isEmpty()) {
      Room room = orderedRooms.poll();
      // renders walls, then floors, then items
      renderWalls(g, room);
      renderFloor(g, room);
      renderItems(g, room);
    }
  }

  /**
   * Renders the Floor of a Room to a Graphics object. Utilizes methods in Pipeline.
   *
   * @param g
   *          canvas for drawing
   * @param room
   *          room whose floor is to be drawn
   */
  private void renderFloor(Graphics g, Room room) {
    // uses compareTo method to ensure painters algorithm works properly
    PriorityQueue<Polygon3D> orderedPolygons = room.getFloor().getMesh().orderPolygons();
    while (!orderedPolygons.isEmpty()) {
      Polygon3D poly = orderedPolygons.poll();
      // checks if the polygon is facing away from the viewer or if it behind the
      // viewer
      if (!Pipeline.isHidden(poly) && poly.getPosition().getRealZ() > 0) {
        // get coloring
        g.setColor(Pipeline.getShading(poly,
            new float[] { light.getRealX(), light.getRealY(), light.getRealZ() }));
        // fill
        g.fillPolygon(poly.xPoints3D(), poly.yPoints3D(), poly.getnPoints());
      }
    }
  }

  /**
   * Renders the Walls of a Room to a Graphics object. Utilizes methods in Pipeline.
   *
   * @param g
   *          canvas for drawing
   * @param room
   *          room whose walls are to be drawn
   */
  private void renderWalls(Graphics g, Room room) {
    for (AbstractWall wall : room.getWalls()) {
      // uses compareTo method to ensure painters algorithm works properly
      PriorityQueue<Polygon3D> polys = wall.getMesh().orderPolygons();
      while (!polys.isEmpty()) {
        Polygon3D poly = polys.poll();
        // checks if the polygon is facing away from the viewer or if it behind the
        // viewer
        if (!Pipeline.isHidden(poly) && poly.getPosition().getRealZ() > 0) {
          // get coloring
          g.setColor(Pipeline.getShading(poly,
              new float[] { light.getRealX(), light.getRealY(), light.getRealZ() }));
          // fill
          g.fillPolygon(poly.xPoints3D(), poly.yPoints3D(), poly.getnPoints());
        }
      }
    }
  }

  /**
   * Renders the Items in a Room to a Graphics object. Utilizes methods in Pipeline.
   *
   * @param g
   *          canvas for drawing
   * @param room
   *          room whose items are to be drawn
   */
  private void renderItems(Graphics g, Room room) {
    // uses compareTo method to ensure painters algorithm works properly
    PriorityQueue<AbstractItem> items = room.orderItems();
    while (!items.isEmpty()) {
      AbstractItem item = items.poll();
      if (item != null) {
        // uses compareTo method to ensure painters algorithm works properly
        PriorityQueue<Polygon3D> orderedPolygons = item.getMesh().orderPolygons();
        while (!orderedPolygons.isEmpty()) {
          Polygon3D poly = orderedPolygons.poll();
          // checks if the polygon is facing away from the viewer or if it behind the
          // viewer
          if (!Pipeline.isHidden(poly) && poly.getPosition().getRealZ() > 0) {
            // get coloring
            g.setColor(Pipeline.getShading(poly,
                new float[] { light.getRealX(), light.getRealY(), light.getRealZ() }));
            // fill
            g.fillPolygon(poly.xPoints3D(), poly.yPoints3D(), poly.getnPoints());
          }
        }
      }
    }
  }

  /**
   * Gets the room at the coords (0, 0).
   * 
   * @return the current origin room
   */
  public Room getCurrentRoom() {
    for (Room room : rooms) {
      Point3D pos = room.getPosition();
      if (pos.getRealX() == 0 && pos.getRealZ() == 0) {
        return room;
      }
    }
    return null;
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
   *          the room to add
   */
  public void addRoom(Room room) {
    this.rooms.add(room);
  }
}
