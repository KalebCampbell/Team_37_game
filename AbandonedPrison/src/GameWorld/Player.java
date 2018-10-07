package GameWorld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Basic creation of player, nothing added yet.
 * @author Michael Vincent
 *
 */
public class Player extends Character{

	private List<AbstractItem> inventory = new ArrayList<AbstractItem>();
	
	public Player(String playerName, Location playerLocation, Inventory inventory) {
		setPlayerName(playerName);
		setPlayerLocation(playerLocation);
		setInventory(inventory);
			
	}

	private void setInventory(Inventory inventory) {

	}

	private void setPlayerLocation(Location playerLocation) {
		
	}

	private void setPlayerName(String playerName) {
		
	}
	
	
	
}