package Testing;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import GameWorld.Game;
import Persistence.GameMapComponent;
import Persistence.LoadXml;

public class GameWorldInitialisationTests {
	final int current_items_in_map = 3;
	final int current_rooms_in_map = 3;
	
	
	@Test
	public void initialisationTestPlayer() {
		Game game = loadHelper("GameWorldTestMap.xml");
		assert(game.getPlayer().getPlayerId()  == 99);
		assert(game.getPlayer().getPlayerName().equals("Player1"));
	}
	
	// Simple test to see if rooms are being correctly added to the game.
	@Test
	public void initialisationRooms() {
		Game game = loadHelper("GameWorldTestMap.xml");
		assert(game.getRooms().size() == current_rooms_in_map);
	}
	
	// Simple test to see if items are being correctly added to the game.
	@Test
	public void initialisationItems() {
		Game game = loadHelper("GameWorldTestMap.xml");
		assert(game.getAllitems().size() == current_items_in_map);
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
