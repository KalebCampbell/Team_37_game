package GameWorld;

public class WoodenBox extends AbstractContainer{

	public WoodenBox(String containerName, int containerId, String containerImage, String containerDescription,
			Location containerLocation) {
		super(containerName, containerId, containerImage, containerDescription, containerLocation);
	}
	
	/**
	 * own implementation of the open method
	 * @return boolean
	 */
	public boolean open() {
		
		if(this.isLocked) {
			System.out.println("A wooden Clunk the chest is locked");
			return false;
		}else {
			System.out.println("A wooden Clunk as the chest opens");
			return true;
		}
	}
	
	public boolean unlock(AbstractItem item) {
		if(this.isLocked) {
			System.out.println("A wooden Clunk the chest is locked");
			if(item.getItemId() == this.getId()) {
				this.isLocked = false;
				System.out.println("The key fits! The Wooden chest is open!");
			}else if(item.getItemId() != this.getId()) {
				this.isLocked = true;
				System.out.println("The key does not fit, try another!");
			}	
		}
		return isLocked;
	}

}
