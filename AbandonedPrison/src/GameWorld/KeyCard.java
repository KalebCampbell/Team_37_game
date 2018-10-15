package GameWorld;

public class KeyCard extends AbstractItem{

	public KeyCard(String itemName,int itemId, String itemImage, String itemDescription, Location keyCardLocation) {
		super(itemName,itemId, itemImage, itemDescription, keyCardLocation);
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
