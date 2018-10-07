package GameWorld;

/**Implemtation of a Key class that extends the AbstractItem
 * By extending the Abstract Item, we can use specialised method in the Key class
 * and use generic/common methods of it's super class.
 * 
 * @author Michael Vincent
 *
 */
public class Key extends AbstractItem{
	
	public Key(String keyName, String keyImage, String keyDescription, Location keyLocation) {
		super(keyName,keyImage,keyDescription, keyLocation);	
	}
	
	/**
	 * Iwn implementation of the lock method
	 * @return boolean
	 */
	public boolean lock() {
		System.out.println("Key lock");
		return false;
	}

	/** 
	 * Own implementation of the unlock method.
	 * @return boolean
	 */
	public boolean unlock() {
		System.out.println("Key Unlock");
		return false;
		
	}



}
