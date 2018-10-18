package GameWorld;

/**
 * Item class Super class for all other items. Implements an interface to ensure
 * these methods are handled.
 * 
 * @author Michael Vincent
 *
 */

public class Item implements ItemInterface {
  protected String itemName;
  protected String itemDescription;
  protected Location itemLocation;
  protected int itemId;

  /**
   * Constructor for creating an item.
   * 
   * @param itemName
   *          name of item
   * @param itemImage
   *          name of item image file name
   * @param itemDescription
   *          description of item
   */
  public Item(String itemName, int itemId, String itemDescription, Location itemLocation) {
    this.itemName = itemName;
    this.itemDescription = itemDescription;
    this.itemLocation = itemLocation;
    this.itemId = itemId;
  }

  /**
   * Empty constructor.
   */
  public Item() {
  }

  @Override
  public boolean use() {
    System.out.println("Using " + itemName + " nothing is happening.");
    return false;
  }

  @Override
  public boolean pickUp() {
    System.out.println("Cannot pickup: " + itemName);
    return false;
  }

  @Override
  public boolean drop() {
    System.out.println("Cannot drop: " + itemName);
    return false;
  }

  @Override
  public boolean store() {
    System.out.println("Cannot store: " + itemName);
    return false;
  }

  public void placeIn() {
    // Implementation for placing item into a container
  }

  public void takeOut() {
    // Implementation for taking item out of a container
  }

  // Getters //
  public String getItemName() {
    return this.itemName;
  }

  public String getItemDescription() {
    return this.itemDescription;
  }

  public int getItemId() {
    return this.itemId;
  }

  public Location getItemLocation() {
    return this.itemLocation;
  }

  @Override
  public String getType() {
    return "Empty";
  }

}