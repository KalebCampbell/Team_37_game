package GameWorld;

/** Item interface
 *  All items should implement the Item interface, using all methods.
 * 
 * @author Michael Vincent
 *
 */

public interface Item {

	/**
	 * Getter method for getting item name
	 * @return Item name
	 */
	public String getName();
	
	/**
	 * Getting method for getting description of item
	 * @return Item description
	 */
	public String getDescription();
	
	/**
	 * Get item image
	 * @return Item image
	 */
	public String getImage();
	
	/**
	 * Checks if item is in use
	 * @return True if in use (Picked up) False if not
	 */
	public boolean inUse();
	
	
	public void remove();
	
	
}
