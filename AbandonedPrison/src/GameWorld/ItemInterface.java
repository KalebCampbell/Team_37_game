package GameWorld;

/**
 * Implementation of an interface to force all items to behave the same.
 * 
 * @author Michael Vincent
 *
 */
public interface ItemInterface {

  public String getType();

  public boolean use();

  public boolean pickUp();

  public boolean drop();

  public boolean store();
}
