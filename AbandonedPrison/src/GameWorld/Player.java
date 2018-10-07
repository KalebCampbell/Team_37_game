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

	private Inventory inventory;
	private String playerName;
	private Location playerLocation;
	
	public Player(String name, Inventory inventory, Location playerLocation) {
		this.playerName = name;
		this.playerLocation = playerLocation;
		this.inventory = inventory;

		
	}

	/**
	 * Player logic for picking up an item
	 * @param player
	 * @param Item
	 */
	public boolean pickUpItem(Player player, AbstractItem item) {
		return false;		
	}
	
	
	
}