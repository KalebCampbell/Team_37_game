package testing;

import Persistence.GameMapComponent;
import Persistence.LoadXml;
import gameworld.Game;

import java.io.File;
import javax.xml.bind.JAXBException;
import org.junit.Test;




public class GameWorldInitialisationTests {

  @Test
  public void initialisationTestPlayer() {
    @SuppressWarnings("unused")
    Game game = loadHelper("GameWorldTestMap.xml");
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
