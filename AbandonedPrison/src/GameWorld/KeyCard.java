package GameWorld;

public class KeyCard extends AbstractItem{

	public KeyCard(String itemName, String itemImage, String itemDescription) {
		super(itemName, itemImage, itemDescription);
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
