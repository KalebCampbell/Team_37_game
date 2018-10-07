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
	private List<AbstractItem> inventory = new ArrayList<AbstractItem>(MAX_SIZE);
	
	/**
	 * Constructor for Inventory
	 * @param player
	 */
	public Inventory(Player player) {
		this.setPlayer(player);	
	}
	
	/**
	 * Get player that inventory belongs to
	 * Used in testing cases mainly
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Part of constructor for setting the player in which the inventory belongs to
	 * Allows for updates too.
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Getter for getting inventory.
	 * @return List of items in inventory
	 */
	public List<AbstractItem> getInventory() {
		return inventory;
	}

	/**
	 * Adds item to inventory.
	 * If Fails, give message.
	 * @param item
	 */
	public void addItemToInventory(AbstractItem item) {
		if(item != null) {
			inventory.add( item);
		}else {
			System.out.println("Unable to add "+item.getItemName()+" to inventory");
		}	
	}
	
	/**
	 * Removes items from inventory if it exists.
	 * @param item
	 */
	public void removeItemFromInventory(AbstractItem item) {
		if(inventory.contains(item)) {
			inventory.remove(item);
		}else {
			System.out.println("Unable to remove "+item.getItemName()+" to inventory");
		}		
	}
	
	/**
	 * The parameters can change here.
	 * The assumption is the user will select item in game world and it'll be "Found"
	 * within the working game object.
	 * @param itemName
	 * @return boolean if item exists
	 */
	public boolean itemExistsInInventory(AbstractItem item) {
		return inventory.contains(item);	
	}
	
}
