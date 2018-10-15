package GameWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an inventory for the player
 * Has a maximum size of 10
 * ArrayList is used as the collection
 * @author Michael Vincent 300140128
 *
 */
public class Inventory {
	public final int MAX_SIZE = 8;
	private List<AbstractItem> inventory = new ArrayList<AbstractItem>(MAX_SIZE);
	
	/**
	 * Constructor for Inventory
	 * @param player
	 */
	public Inventory() {}

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
	 * @return 
	 */
	public boolean addItemToInventory(AbstractItem item) {
		if(item != null && inventory.size() != MAX_SIZE) {
			inventory.add(item);
			return true;
		}else {
			System.out.println("Unable to add "+item+" to inventory");
			return false;
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
	public boolean itemExistsInInventory(AbstractItem item) {
		return inventory.contains(item);	
	}
	
}
