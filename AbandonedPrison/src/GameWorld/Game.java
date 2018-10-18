package GameWorld;

import java.util.ArrayList;
import java.util.List;

import Persistence.RoomComponent;
import Persistence.RoomsComponent;
import Persistence.ContainerComponent;
import Persistence.DoorComponent;
import Persistence.GameMapComponent;
import Persistence.ItemComponent;

/**
 * - The gameworld is responsibile for objects in the game - Furniture -.
 * Containers - Keys.
 * 
 * 
 * @author Michael Vincent
 *
 */
public class Game {
  // Stores everything about the game as room objects.
  List<Room> roomList = new ArrayList<Room>();
  List<Item> allitems = new ArrayList<Item>();
  List<Container> allContainers = new ArrayList<Container>();
  // Stores everything about the player including inventory.
  Player player;
  boolean demonstration = false;

  /**
   * Constructor for Game class. When called performs an initialisation of all
   * components from the map Player setup,Inventory setup,Map setup.
   * 
   * @param setup gameMap object from persistence package
   */

  public Game(GameMapComponent setup) {
    // Null check
    if (setup != null) {
      // Player setup
      this.player = initialisePlayer(setup);
      // Inventory setup
      this.player.setInventory(initialiseInventory(setup));
      // Creates rooms & map setup
      initialiseMap(setup);
    }

  }

  // CONTAINER FUNCTIONS //

  public boolean openContainer(Container container) {
    return container.open();
  }

  public boolean unlockContainer(Container container, Item item) {
    return container.unlock();

  }

  // ROOM FUNCTIONS //

  /**
   * location of item using player.
   * @param p player
   * @param loc location
   * @return item
   */
  public Item locOfItemUsingPlayer(Player p, Location loc) {
    Room room = findRoom(p.getRoomId());
    for (Item i : room.getItems()) {
      if (i.getItemLocation() == loc) {
        return i;
      }
    }
    return null;
  }

  /**
   * Pickup item.
   * @param itemName in string format
   * @return true/false if item can be picked up
   */
  public boolean itemPickUp(Item item) {
    item.pickUp(); // Demonstrate strategy.
    return player.getInventory().addItemToInventory(item);
  }

  /**
   * Attempts to place item in front of the player.
   * 
   * @param item item object
   * @return boolean true/false
   */
  public boolean itemDrop(Item item) {
    Room room = findRoom(player.getRoomId());
    String dir = player.getDirection();
    return room.addItemToGrid(item, dir);
  }

  /**
   * Gets all the items in the map.
   * 
   * @return
   */
  public List<Item> getAllitems() {
    return allitems;
  }

  /**
   * Getter method to return rooms.
   * 
   * @return List of rooms
   */
  public List<Room> getRooms() {
    return roomList;
  }

  /**
   * Method to find a room based on an id.
   * @param roomId int
   * @return room object or null if no room.
   */
  
  public Room findRoom(int roomId) {
    for (Room r : roomList) {
      if (r.getRoomID() == roomId) {
        return r;
      }
    }
    return null;
  }

  // PLAYER FUNCTIONS
  /**
   * Turn player left.
   * @param direction.
   */
  
  public void playerTurnLeft() {
    player.turnLeft();
  }

  /**
   * Turn player Right.
   * @param direction.
   */
  
  public void playerTurnRight() {
    player.turnRight();
  }

  /**
   * Call for player to move. Checks player direction against player movement.
   * 
   * @return true/false.
   */
  public boolean playerMove() {
    return player.playerMove(roomList);
  }

  /**
   * Call for player to move backwards. Check player direction against player
   * movement.
   * 
   * @return true/false.
   */

  /**
   * Get player.
   * 
   * @return player object
   */
  public Player getPlayer() {
    return player;
  }

  // INITIALISATION FUNCTIONS //
  /**
   * Initialises the Map, building all the rooms. Created rooms, from a parsed XML
   * file.
   * 
   * @param roomComponents
   *          setup
   */
  private void initialiseMap(GameMapComponent setup) {

    // iterate over all rooms.
    // create items
    RoomsComponent rc = setup.getRooms();
    for (RoomComponent roomC : rc.Rooms()) {
      // List<ItemComponent> ic = roomC.getItems();
      List<Container> containerList = initialiseContainers(roomC.getContainers());
      List<Item> itemList = initialiseItems(roomC.getItems());
      List<Door> doorList = initialiseDoors(roomC.getDoors());
      // Build room
      Room room = new Room(roomC.getId(), roomC.getWalls(),
          new Location(roomC.getLocX(), roomC.getLocY()), itemList, containerList, doorList);
      // Add room to roomList
      roomList.add(room);
    }
  }

  /**
   * Private method only called within game object. Creates items based on input
   * string.
   * 
   * @param list
   *          arraylist of string items.
   * @return List of abstractItems.
   */
  private List<Item> initialiseItems(List<ItemComponent> list) {
    Item item = null;
    List<Item> returnItems = new ArrayList<Item>();

    if (list != null) {
      for (ItemComponent ic : list) {
        String itemName = ic.getItem();
        if (itemName != null) {
          if (itemName.equals("Key")) {
            item = new Key(itemName, ic.getId(), "KeyDescription",
                new Location(ic.getPosX(), ic.getPosY()));
            allitems.add(item);
            returnItems.add(item);
          } else if (itemName.equals("Keycard")) {
            item = new KeyCard(itemName, ic.getId(), "KeyCardDescription",
                new Location(ic.getPosX(), ic.getPosY()));
            allitems.add(item);
            returnItems.add(item);
          } else if (itemName.equals("Crowbar")) {
            item = new Crowbar(itemName, ic.getId(), "CrowbarDescription",
                new Location(ic.getPosX(), ic.getPosY()));
            allitems.add(item);
            returnItems.add(item);
          }
        }
      }
    }
    return returnItems;
  }

  /**
   * Responsible for initialising a player Gathers information about the player
   * from the parsing setup file.
   * 
   * @param setup game map component
   * @return Player player object
   */
  private Player initialisePlayer(GameMapComponent setup) {
    // Parse setup
    int id = Integer.parseInt(setup.getPlayer().getId());
    String name = setup.getPlayer().getName();
    int roomId = Integer.parseInt(setup.getPlayer().getRoomid());
    Location location = setup.getPlayer().getCord();

    if (demonstration) {
      System.out.println("Player created!");
      System.out.println("Name: " + name);
      System.out.println("ID: " + id);
      System.out.println("CurrentRoomID: " + roomId);
      System.out.println("Location: " + location.getX() + "," + location.getY());
    }
    // Create player
    return new Player(id, name, roomId, location);
  }

  /**
   * Responsible for initialising the inventory Gathers informaiton about the
   * inventory from the parsing setup file.
   * 
   * @param setup game map component
   * @return Inventory object
   */
  private Inventory initialiseInventory(GameMapComponent setup) {

    Inventory inv = new Inventory();
    // Iterate over inventory
    for (String s : setup.getInventory()) {
      // split inventory up
      String[] itemArr = s.split(",");
      Item item = null;

      if (itemArr[0].equals("Key")) {
        item = new Key(itemArr[0], Integer.parseInt(itemArr[1]), "KeyDescription",
            new Location(Integer.parseInt(itemArr[2]), Integer.parseInt(itemArr[3])));
        inv.addItemToInventory(item);
      } 
    }
    return inv;
  }

  private List<Container> initialiseContainers(List<ContainerComponent> list) {
    List<Container> returnContainer = new ArrayList<Container>();

    for (ContainerComponent cc : list) {
      if (cc.getContainer().equals("WoodenBox")) {
        returnContainer.add(new WoodenBox(cc.getContainer(), cc.getId(), "containerDescription",
            new Location(cc.getPosX(), cc.getPosY())));
      } else if (cc.getContainer().equals("MetalBox")) {
        returnContainer.add(new MetalBox(cc.getContainer(), cc.getId(), "containerDescription",
            new Location(cc.getPosX(), cc.getPosY())));
      }
    }
    return returnContainer;
  }

  private List<Door> initialiseDoors(List<DoorComponent> list) {
    List<Door> returnDoor = new ArrayList<Door>();

    for (DoorComponent dc : list) {
      returnDoor.add(new Door(dc.getId(), dc.getDirection(), dc.isLocked(), dc.getDoor()));
    }
    return returnDoor;
  }

}