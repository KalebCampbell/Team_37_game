package GameWorld;

/** 
 * Implementation of an interface to force all containers to behave the same.
 * 
 * @author Michael Vincent
 *
 */
public interface ContainerInterface {
	
	public boolean open();
	public boolean store();
	public boolean pickUp();
	public boolean lock();
	public boolean unlock();
}
