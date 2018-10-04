package GameWorld;

/** Abstractitem abstract class
 *  
 * 
 * @author Michael Vincent
 *
 */

public abstract class AbstractItem implements Item{
	private String itemName;
	private String itemDescription;
	private String itemImage;
	
	/**
	 * Constructor for creating an item.
	 * 
	 * @param itemName name of item
	 * @param itemImage name of item image file name
	 * @param itemDescription description of item
	 */
	public AbstractItem(String itemName,String itemImage, String itemDescription) {
		this.setItemName(itemName);
		this.setItemDescription(itemImage);
		this.setItemImage(itemDescription);	
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

}
