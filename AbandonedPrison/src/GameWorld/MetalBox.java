package GameWorld;

public class MetalBox extends AbstractContainer{

	public MetalBox(String containerName, int containerId, String containerImage, String containerDescription,
			Location containerLocation) {
		super(containerName, containerId, containerImage, containerDescription, containerLocation);
	}
	
/**
 * own implementation of the open method
 * @return boolean
 */
public boolean open() {
	
	if(this.isLocked) {
		System.out.println("A metal Clunk the chest is locked");
		return false;
	}else {
		System.out.println("A metal Clunk as the chest opens");
		return true;
	}
}

public boolean unlock(AbstractItem item) {
	if(this.isLocked) {
		System.out.println("A metal Clunk the chest is locked");
		if(item.getItemId() == this.getId()) {
			this.isLocked = false;
			System.out.println("The key fits! The metal chest is open!");
		}else if(item.getItemId() != this.getId()) {
			this.isLocked = true;
			System.out.println("The key does not fit, try another!");
		}	
	}
	return isLocked;
}

}
