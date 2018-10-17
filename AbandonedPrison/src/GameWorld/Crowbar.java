package GameWorld;

public class Crowbar extends Item {

	public Crowbar(String itemName, int itemId, String itemImage, String itemDescription, Location itemLocation) {
		super(itemName, itemId, itemImage, itemDescription, itemLocation);
	}

	/**
	 * own implementation of the lock method
	 * @return boolean
	 */
	public boolean use() {
		System.out.println("Pry with crowbar");
		return true;
	}
	
	public boolean pickUp() {
		System.out.println("Crowbar picked up and added to inventory");
		return false;		
	}

}
