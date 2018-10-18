package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import GameWorld.Crowbar;
import GameWorld.Game;
import GameWorld.Item;
import GameWorld.Key;
import GameWorld.KeyCard;
import GameWorld.Location;
import GameWorld.Room;
import Persistence.GameMapComponent;
import Persistence.LoadXml;

public class GameWorldPickupTests { 

	@Test
	public void pickUpTest1() {
		Game game = loadHelper("GameWorldTestMap1.xml");
		Item item = new Key("TESTKEY", 9, "TESTDESCRIPTION", new Location(1,1));
		game.itemPickUp(item);
		assert(game.getPlayer().inventoryContains(item));
	}	
	
	@Test
	public void pickUpTest2() {
		Game game = loadHelper("GameWorldTestMap1.xml");
		Item item = new KeyCard("TESTKEYCARD", 9, "TESTDESCRIPTION", new Location(1,1));
		game.itemPickUp(item);
		assert(game.getPlayer().inventoryContains(item));
	}
	
	@Test
	public void pickUpTest3() {
		Game game = loadHelper("GameWorldTestMap1.xml");
		Item item = new Crowbar("TESTCROWBAR", 9, "TESTDESCRIPTION", new Location(1,1));
		game.itemPickUp(item);
		assert(game.getPlayer().inventoryContains(item));
	}
	
	@Test
	public void dropItemTest1() {
		Game game = loadHelper("GameWorldTestMap1.xml");
		Item item = new Key("TESTKEY", 9, "TESTDESCRIPTION", new Location(1,1));
		// Drop an item in players room
		game.itemDrop(item);
		// Find where player is
		int roomId = game.getPlayer().getRoomId();
		// See if item is in the room
		Room r = game.findRoom(roomId);
		assert(r.itemOnGrid(item));
		
		
	}
	
	@Test
	public void pickUpFromRoomTest1() {
		Game game = loadHelper("Map3.xml");
		
		
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
