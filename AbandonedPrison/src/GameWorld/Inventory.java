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
	public final int MAX_SIZE = 8;
	private List<String> inventory = new ArrayList<String>(MAX_SIZE);
	
	/**
	 * Constructor for Inventory
	 * @param player
	 */
	public Inventory() {}

	/**
	 * Getter for getting inventory.
	 * @return List of items in inventory
	 */
	public List<String> getInventory() {
		return inventory;
	}

	/**
	 * Adds item to inventory.
	 * If Fails, give message.
	 * @param item
	 */
	public void addItemToInventory(String item) {
		if(item != null) {
			inventory.add(item);
		}else {
			System.out.println("Unable to add "+item+" to inventory");
		}	
	}
	
	/**
	 * Removes items from inventory if it exists.
	 * @param item
	 */
	public void removeItemFromInventory(String item) {
		if(inventory.contains(item)) {
			inventory.remove(item);
		}else {
			System.out.println("Unable to remove "+item+" to inventory");
		}		
	}
	
	/**
	 * The parameters can change here.
	 * The assumption is the user will select item in game world and it'll be "Found"
	 * within the working game object.
	 * @param itemName
	 * @return boolean if item exists
	 */
	public boolean itemExistsInInventory(String item) {
		return inventory.contains(item);	
	}
	
}
