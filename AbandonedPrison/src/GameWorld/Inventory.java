package GameWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an inventory for the player
 * Has a maximum size of 10
 * ArrayList is used as the collection
 * @author Michael Vincent
 *
 */
public class Inventory {
	public final int MAX_SIZE = 10;
	
	private Player player;
	private List<Item> inventory = new ArrayList<Item>(MAX_SIZE);
	
	public Inventory(Player player) {
		this.setPlayer(player);
		
	}
	
	/**
	 * Get player that inventory belongs to
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Part of constructor for setting the player in which the inventory belongs to
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Getter for getting inventory.
	 * @return List of items in inventory
	 */
	public List<Item> getInventory() {
		return inventory;
	}

	/**
	 * Adds item to inventory.
	 * @param item
	 */
	public void addItemToInventory(Item item) {
		if(item != null) {
			inventory.add(item);
		}else {
			//error
		}	
	}
	
	/**
	 * Removes items from inventory if it exists.
	 * @param item
	 */
	public void removeItemFromInventory(Item item) {
		if(inventory.contains(item)) {
			inventory.remove(item);
		}else {
			//error
		}
		
	}
	
}
