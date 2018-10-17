package Testing;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import GameWorld.Game;
import Persistence.GameMapComponent;
import Persistence.LoadXml;

public class GameWorldInitialisationTests {
	
	
	@Test
	public void initialisationTestPlayer() {
		Game game = loadHelper("GameWorldTestMap.xml");
	}

	
	public Game loadHelper(String testMap){
		// Autoload
		LoadXml load = new LoadXml();
		GameMapComponent gameComp = null;
		try {
			gameComp = load.unMarshal(new File(testMap));
		} catch (JAXBException e) {
		}
		return new Game(gameComp);
		
	}

}
