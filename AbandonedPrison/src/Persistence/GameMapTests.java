package Persistence;

import static org.junit.Assert.assertSame;
import org.junit.jupiter.api.Test;

import GameWorld.Location;

public class GameMapTests {

	@Test
	public void testGetCoord() {
		PlayerComponent player = new PlayerComponent();
		player.setLocation("3,3");
		
		assertSame(new Location(3, 3).getX(), player.getCord().getX());
	}
}
