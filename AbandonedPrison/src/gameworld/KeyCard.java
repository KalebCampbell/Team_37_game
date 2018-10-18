package gameworld;

public class KeyCard extends Item {

  public KeyCard(String itemName, int itemId, String itemDescription, Location keyCardLocation) {
    super(itemName, itemId, itemDescription, keyCardLocation);
  }

  /**
   * Own implementation of the swipe.
   *
   */
  public void swipe() {
    // Swipe keycard implementation
    System.out.println("Key card Swipe");
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
    return "KeyCard";
  }
}
