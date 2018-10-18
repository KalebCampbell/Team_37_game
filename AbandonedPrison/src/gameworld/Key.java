package gameworld;

public class Key extends Item implements ItemInterface {

  public Key(String keyName, int itemId, String itemDescription, Location keyLocation) {
    super(keyName, itemId, itemDescription, keyLocation);
  }

  /**
   * own implementation of the lock method.
   * 
   * @return boolean if lock was sucessful
   */
  public boolean lock() {
    System.out.println("Key unlock");
    return false;
  }

  /**
   * own implementation of the pickup method.
   * 
   * @return boolean if pickup was sucessful
   */
  public boolean pickUp() {
    System.out.println("Key picked up and added to inventory");
    return false;
  }

  /**
   * Own implementation of the unlock method.
   * 
   * @return boolean
   */
  public boolean unlock() {
    System.out.println("Key Unlock");
    return false;
  }

  public boolean use() {
    System.out.println("Using " + itemName);
    return false;
  }

  public boolean drop() {
    System.out.println("Dropped " + itemName);
    return false;
  }

  public boolean store() {
    System.out.println("Storing " + itemName);
    return false;
  }

  public String getType() {
    return "Key";
  }

}
