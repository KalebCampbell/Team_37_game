package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Persistence.GameMapComponent;
import Persistence.LoadXml;
import gameworld.Game;
import java.io.File;
import javax.xml.bind.JAXBException;
import org.junit.Test;



public class GameWorldMovementTests {

  // Turn right
  @Test
  public void movementTestTurnRight1() {
    Game game = loadHelper("GameWorldTestMap.xml");
    game.playerTurnRight();
    assert (game.getPlayer().getDirection().equals("E"));
  }

  // Turn left
  @Test
  public void movementTestTurnLeft1() {
    Game game = loadHelper("GameWorldTestMap.xml");
    game.playerTurnLeft();
    assert (game.getPlayer().getDirection().equals("W"));
  }

  // Turn around
  @Test
  public void movementTestTurnLeft2() {
    Game game = loadHelper("GameWorldTestMap.xml");
    game.playerTurnLeft();
    game.playerTurnLeft();
    game.playerTurnLeft();
    game.playerTurnLeft();
    assert (game.getPlayer().getDirection().equals("N"));
  }

  @Test
  public void movementTestTurnRight2() {
    Game game = loadHelper("GameWorldTestMap.xml");
    game.playerTurnRight();
    game.playerTurnRight();
    game.playerTurnRight();
    game.playerTurnRight();
    assert (game.getPlayer().getDirection().equals("N"));
  }

  /**
   * Basic test to ensure that the player moves one room down.
   */
  @Test
  public void movementTest1() {
    Game game = loadHelper("GameWorldTestMap.xml");

    game.getPlayer().setDirection("N");
    game.playerMove();

    assertEquals(game.getPlayer().getPlayerLocation().getX(), 0);
    assertEquals(game.getPlayer().getPlayerLocation().getY(), -1);

  }

  /*
   * Navigates 3 rooms.
   */
  @Test
  public void movementTest2() {
    Game game = loadHelper("GameWorldTestMap.xml");

    game.playerMove(); // North 1 room
    game.playerMove(); // North 1 room
    game.playerTurnRight(); // turn N->E
    game.playerMove(); // East 1 room

    assertEquals(game.getPlayer().getPlayerLocation().getX(), 1);
    assertEquals(game.getPlayer().getPlayerLocation().getY(), -2);

  }

  /*
   * Cannot move into wall.
   */
  @Test
  public void movementTestFail1() {
    Game game = loadHelper("GameWorldTestMap.xml");

    game.playerTurnLeft();
    // Facing wall.
    assertFalse(game.playerMove());
  }

  /*
   * Cannot move into wall.
   */
  @Test
  public void movementTestFail2() {
    Game game = loadHelper("GameWorldTestMap.xml");

    game.playerTurnLeft(); // N->W
    game.playerTurnLeft(); // W->S
    // Facing wall.
    assertFalse(game.playerMove());
  }

  /*
   * Cannot move into wall.
   */
  @Test
  public void movementTestFail3() {
    Game game = loadHelper("GameWorldTestMap.xml");

    game.playerMove();
    game.playerTurnLeft(); // N->W
    // Facing wall.
    assertFalse(game.playerMove());
  }

  /*
   * Coverage navigate
   */
  @Test
  public void movementTestNavigate1() {
    Game game = loadHelper("GameWorldTestMap.xml");

    game.playerMove(); // -> N
    game.playerMove(); // -> N
    game.playerTurnRight(); // N-> E
    game.playerMove(); // -> E
    game.playerTurnRight(); // E -> S
    game.playerMove(); // -> S

    assertFalse(game.playerMove());
  }

  /*
   * Coverage navigate
   */
  @Test
  public void movementTestNavigate2() {
    Game game = loadHelper("GameWorldTestMap.xml");

    game.playerMove(); // -> N
    game.playerMove(); // -> N
    game.playerTurnRight(); // N-> E
    game.playerMove(); // -> E
    game.playerTurnRight(); // E -> S
    game.playerTurnRight(); // S -> W
    game.playerMove(); // -> W

    assertFalse(game.playerMove());
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
