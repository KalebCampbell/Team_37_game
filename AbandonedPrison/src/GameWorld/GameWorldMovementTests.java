package GameWorld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import Persistence.GameMapComponent;
import Persistence.LoadXml;

public class GameWorldMovementTests {
	
	
	
	/**
	 * Basic test to ensure that the player moves one room down.
	 */
	@Test
	public void movementTest1() {
		Game game = loadHelper("GameWorldTestMap.xml");
		
		game.player.setDirection("N");
		game.playerMove();
		
		assertEquals(game.player.getPlayerLocation().getX(),0);
		assertEquals(game.player.getPlayerLocation().getY(),-1);
		
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
		
		assertEquals(game.player.getPlayerLocation().getX(),1);
		assertEquals(game.player.getPlayerLocation().getY(),-2);
		
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
	
	
	
	@Test
	public void pickUpTest1() {
		Game game = loadHelper("GameWorldTestMap1.xml");
		
		Item i = game.locOfItemUsingPlayer(game.player,new Location(1,1));
		
		assertEquals(i.getItemId(),1);
	}
	

	@Test
	public void pickUpTest2() {
		
	}
	@Test
	public void openContainerTest1() {
		
	}
	@Test
	public void openLockedContainerTest1() {
		
	}
	
	

	
	public Game loadHelper(String testMap){
		// Autoload
		LoadXml load = new LoadXml();
		GameMapComponent gameComp = null;
		try {
			gameComp = load.unMarshal(new File(testMap));
		} catch (JAXBException e) {
			System.out.println("Parsing failed");
		}
		return new Game(gameComp);
		
	}
}
