package GameWorld;

/** AbstractItem abstract class
 *  
 * @author Michael Vincent 300140128
 *
 */

public class Container implements ContainerInterface{
	protected String containerName;
	protected String containerDescription;
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
	public Container(String containerName,int containerId, String containerDescription, Location containerLocation) {
		this.setcontainerName(containerName);
		this.setcontainerDescription(containerDescription);	
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


	
	// Getters //
	public String getcontainerName() {
		return this.containerName;
	}
	public String getcontainerDescription() {
		return this.containerDescription;
		
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
		System.out.println("Cannot find container to open");
		return false;
	}

	@Override
	public boolean store() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pickUp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lock() {
		// TODO Auto-generated method stub
		return false;
	}



}