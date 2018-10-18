package GameWorld;

/**
 * Represents a crowbar item.
 * 
 * @author Michael Vincent
 *
 */

public class Crowbar extends Item {

  public Crowbar(String itemName, int itemId, String itemDescription, Location itemLocation) {
    super(itemName, itemId, itemDescription, itemLocation);
  }

  /**
   * own implementation of the lock method.
   * 
   * @return boolean if the item was picked up
   */
  public boolean use() {
    System.out.println("Pry with crowbar");
    return true;
  }

  /**
   * Own implementation of pickup for crowbar.
   * 
   * @return boolean if the item was picked up
   */
  public boolean pickUp() {
    System.out.println("Crowbar picked up and added to inventory");
    return false;
  }

  public String getType() {
    return "Crowbar";
  }

}
