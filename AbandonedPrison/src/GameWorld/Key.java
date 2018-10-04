package GameWorld;

public class Key extends AbstractItem{
	
	public Key(String keyName, String keyImage, String keyDescription) {
		super(keyName,keyImage,keyDescription);	
	}
	
	/**
	 * own implementation of the lock method
	 * @return boolean
	 */
	public boolean lock() {
		System.out.println("Key unlock");
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
