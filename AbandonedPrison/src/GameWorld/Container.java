package GameWorld;

/** Container class
 * Stores information about the container
 * @author Michael Vincent
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
	
	// Setters //
	private void setcontainerId(int containerId) {
		this.containerId = containerId;	
	}

	private void setcontainerLocation(Location containerLocation) {
		this.containerLocation = containerLocation;
	}

	public void setcontainerName(String containerName) {
		this.containerName = containerName;
	}

	public void setcontainerDescription(String containerDescription) {
		this.containerDescription = containerDescription;
	}

	/*
	 * Getter method for container Name.
	 * @return containerName
	 */
	public String getcontainerName() {
		return this.containerName;
	}
	/*
	 * Getter method for container Description.
	 * @return container Description
	 */
	public String getcontainerDescription() {
		return this.containerDescription;
		
	}
	/*
	 * Getter method for container Location.
	 * @return containerLocation
	 */
	public Location getContainerLocation() {
		return this.containerLocation;
	}
	
	/**
	 * Getter method for container id.
	 * @return container id
	 */
	public int getId() {
		return this.containerId;
	}
	
	/**
	 * Generic open method
	 */
	public boolean open() {
		System.out.println("Cannot find container to oepn");
		return false;
	}
	/**
	 * Generic unlock method
	 */
	public boolean unlock() {
		System.out.println("Cannot find container to open");
		return false;
	}
	/**
	 * Generic store method
	 */
	@Override
	public boolean store() {
		return false;
	}

	@Override
	public boolean pickUp() {
		return false;
	}

	@Override
	public boolean lock() {
		return false;
	}



}