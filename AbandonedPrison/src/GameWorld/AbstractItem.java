package GameWorld;

/** AbstractItem abstract class
 *  
 * 
 * @author Michael Vincent
 *
 */

public abstract class AbstractItem{
	protected String itemName;
	protected String itemDescription;
	protected String itemImage;
	protected Location itemLocation;
	
	/**
	 * Constructor for creating an item.
	 * 
	 * @param itemName name of item
	 * @param itemImage name of item image file name
	 * @param itemDescription description of item
	 */
	public AbstractItem(String itemName,String itemImage, String itemDescription, Location itemLocation) {
		this.setItemName(itemName);
		this.setItemDescription(itemImage);
		this.setItemImage(itemDescription);	
		this.setItemLocation(itemLocation);
	}
	
	private void setItemLocation(Location itemLocation) {
		this.itemLocation = itemLocation;
	}

	// Common item functionality //
	
	public void pickUp() {
	// Implementation for picking up an item
		System.out.println("pickup");
	}
	public void placeItem() {
	// Implementation for placing item on ground
	}
	
	public void useItem() {
		// Implementation for using generic item (Key & Keycard have own implementation SHOULD be overridden)
	}
	public void placeIn() {
		// Implementation for placing item into a container
	}
	public void takeOut() {
		// Implementation for taking item out of a container
	}
	
	
	// Setters //
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	
	// Getters //
	public String getItemName() {
		return this.itemName;
	}
	public String getItemDescription() {
		return this.itemDescription;
		
	}
	public String getItemImage() {
		return this.itemImage;
	
	}

}