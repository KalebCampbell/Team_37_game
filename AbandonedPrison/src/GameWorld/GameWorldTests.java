package GameWorld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import Persistence.GameMapComponent;
import Persistence.LoadXml;

public class GameWorldTests {
	
	
	
	
	@Test
	public void movementTest1() {
		Game game = loadHelper();
		
		game.playerMove();
		
		int x = game.player.getPlayerLocation().getX();
		int y = game.player.getPlayerLocation().getY();

		assertEquals(x,0);
		assertEquals(y,-1);
		
	}
	@Test
	public void movementTest2() {
		
	}
	@Test
	public void movementTest3() {
		
	}
	@Test
	public void pickUpTest1() {
		
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
	
	

	
	public Game loadHelper(){
		// Autoload
		LoadXml load = new LoadXml();
		GameMapComponent gameComp = null;
		try {
			gameComp = load.unMarshal(new File("Map3.xml"));
		} catch (JAXBException e) {
			System.out.println("Parsing failed");
		}
		return new Game(gameComp);
		
	}
}
