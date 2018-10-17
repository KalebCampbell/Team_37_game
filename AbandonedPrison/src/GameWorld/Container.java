package GameWorld;

/** AbstractItem abstract class
 *  
 * @author Michael Vincent 300140128
 *
 */

public class Container{
	protected String containerName;
	protected String containerDescription;
	protected String containerImage;
	protected Location containerLocation;
	protected int containerId;
	protected boolean isLocked = true;
	protected boolean isOpen = false;
	
	/**
	 * Constructor for creating a container.
	 * 
	 * @param containerName name of container
	 * @param containerImage name of container image file name
	 * @param containerDescription description of container
	 */
	public Container(String containerName,int containerId,String containerImage, String containerDescription, Location containerLocation) {
		this.setcontainerName(containerName);
		this.setcontainerDescription(containerImage);
		this.setcontainerImage(containerDescription);	
		this.setcontainerLocation(containerLocation);
		this.setcontainerId(containerId);
	}
	
	private void setcontainerId(int containerId) {
		this.containerId = containerId;
		
	}

	private void setcontainerLocation(Location containerLocation) {
		this.containerLocation = containerLocation;
	}

	
	// Setters //
	public void setcontainerName(String containerName) {
		this.containerName = containerName;
	}

	public void setcontainerDescription(String containerDescription) {
		this.containerDescription = containerDescription;
	}

	public void setcontainerImage(String containerImage) {
		this.containerImage = containerImage;
	}
	
	// Getters //
	public String getcontainerName() {
		return this.containerName;
	}
	public String getcontainerDescription() {
		return this.containerDescription;
		
	}
	public String getcontainerImage() {
		return this.containerImage;
	
	}
	public Location getContainerLocation() {
		return this.containerLocation;
	}

	public int getId() {
		return this.containerId;
	}

	public boolean open() {
		System.out.println("Cannot find container to oepn");
		return false;
	}

	public boolean unlock() {
		System.out.println("Cannot find container to oepn");
		return false;
	}



}