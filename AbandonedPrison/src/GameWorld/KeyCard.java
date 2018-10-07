package GameWorld;

/**Implemtation of a KeyCard class that extends the AbstractItem
 * By extending the Abstract Item, we can use specialised method in the KeyCard class
 * and use generic/common methods of it's super class.
 * 
 * @author Michael Vincent
 *
 */
public class KeyCard extends AbstractItem{

	public KeyCard(String itemName, String itemImage, String itemDescription, Location keyCardLocation) {
		super(itemName, itemImage, itemDescription, keyCardLocation);
	}
	
	/** 
	 * Own implementation of the swipe.
	 *
	 */
	public void swipe() {
		// Swipe keycard implementation
		System.out.println("Key card Swipe");
	}
	
	/** 
	 * Own implementation of the unlock method.
	 * @return boolean
	 */
	public boolean unlock() {
		// Key unlock implementation
		System.out.println("keycard unlock");
		return false;
		
	}

}
