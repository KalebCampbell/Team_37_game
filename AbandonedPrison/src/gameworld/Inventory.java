package gameworld;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an inventory for the player Has a maximum size of 8 ArrayList is
 * used as the collection.
 * 
 * @author Michael Vincent.
 *
 */
public class Inventory {
  public final int maxSize = 8;
  private List<Item> inventory = new ArrayList<Item>(maxSize);

  /**
   * Empty Constructor for Inventory.
   * 
   */
  public Inventory() {
  }

  /**
   * Getter for getting inventory.
   * 
   * @return List of items in inventory
   */
  public List<Item> getInventory() {
    return inventory;
  }

  /**
   * Adds item to inventory. If Fails, give message.
   * 
   * @param item Item to be added
   * @return
   */
  public boolean addItemToInventory(Item item) {

    if (item != null && inventory.size() != maxSize) {
      inventory.add(item);
      return true;
    } else {
      System.out.println("Unable to add " + item + " to inventory");
      return false;
    }
  }

  /**
   * Removes items from inventory if it exists.
   * 
   * @param item Item to be removed
   */
  public void removeItemFromInventory(Item item) {
    if (inventory.contains(item)) {
      inventory.remove(item);
    } else {
      System.out.println("Unable to remove " + item + " to inventory");
    }
  }

  /**
   * The parameters can change here. The assumption is the user will select item
   * in game world and it'll be "Found" within the working game object.
   * 
   * @param item item to compare
   * @return boolean if item exists
   */
  public boolean itemExistsInInventory(Item item) {
    return inventory.contains(item);
  }

}
