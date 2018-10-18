package testing;

import Persistence.GameMapComponent;
import Persistence.LoadXml;
import gameworld.Crowbar;
import gameworld.Game;
import gameworld.Item;
import gameworld.Key;
import gameworld.KeyCard;
import gameworld.Location;
import gameworld.Room;

import java.io.File;
import javax.xml.bind.JAXBException;
import org.junit.Test;



public class GameWorldPickupTests {

  @Test
  public void pickUpTest1() {
    Game game = loadHelper("GameWorldTestMap1.xml");
    Item item = new Key("TESTKEY", 9, "TESTDESCRIPTION", new Location(1, 1));
    game.itemPickUp(item);
    assert (game.getPlayer().inventoryContains(item));
  }

  @Test
  public void pickUpTest2() {
    Game game = loadHelper("GameWorldTestMap1.xml");
    Item item = new KeyCard("TESTKEYCARD", 9, "TESTDESCRIPTION", new Location(1, 1));
    game.itemPickUp(item);
    assert (game.getPlayer().inventoryContains(item));
  }

  @Test
  public void pickUpTest3() {
    Game game = loadHelper("GameWorldTestMap1.xml");
    Item item = new Crowbar("TESTCROWBAR", 9, "TESTDESCRIPTION", new Location(1, 1));
    game.itemPickUp(item);
    assert (game.getPlayer().inventoryContains(item));
  }

  @Test
  public void dropItemTest1() {
    Game game = loadHelper("GameWorldTestMap1.xml");
    Item item = new Key("TESTKEY", 9, "TESTDESCRIPTION", new Location(1, 1));
    // Drop an item in players room
    game.itemDrop(item);
    // Find where player is
    int roomId = game.getPlayer().getRoomId();
    // See if item is in the room
    Room r = game.findRoom(roomId);
    assert (r.itemOnGrid(item));

  }

  @Test
  public void pickUpFromRoomTest1() {
    @SuppressWarnings("unused")
    Game game = loadHelper("Map3.xml");

  }
  /**
   * Loadhelper for loading test maps.
   * @param testMap game map
   * @return game
   */
  
  @SuppressWarnings("static-access")
  public Game loadHelper(String testMap) {
    // Autoload
    LoadXml load = new LoadXml();
    GameMapComponent gameComp = null;
    try {
      gameComp = load.unMarshal(new File(testMap));
    } catch (JAXBException e) {
      return null;
    }
    return new Game(gameComp);

  }
}
