package GameWorld;

public class WoodenContainer extends AbstractContainer{

	public WoodenContainer(String containerName, int containerId, String containerImage, String containerDescription,
			Location containerLocation) {
		super(containerName, containerId, containerImage, containerDescription, containerLocation);
	}
	
	/**
	 * own implementation of the open method
	 * @return boolean
	 */
	public boolean open() {
		System.out.println("A wooden Clunk as the chest opens");
		return false;
	}

}
