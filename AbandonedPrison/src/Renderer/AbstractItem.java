package Renderer;

/**
 * Represents an Item in a Room. To be used in a 3D Renderer.
 *
 * @author Joel Harris
 */
abstract public class AbstractItem extends AbstractPiece implements Comparable<AbstractItem> {
  protected GameWorld.Item value;

  @Override
  public int compareTo(AbstractItem other) {
    // first compare on z axis
    if (this.position.getRealZ() > other.getPosition().getRealZ())
      return -1;
    if (this.position.getRealZ() < other.getPosition().getRealZ())
      return 1;
    // then compare on x axis
    if (Math.abs(this.position.getRealX()) > Math.abs(other.getPosition().getRealX()))
      return -1;
    if (Math.abs(this.position.getRealX()) < Math.abs(other.getPosition().getRealX()))
      return 1;
    return 0;
  }

  /**
   * @param value
   *          item to set
   */
  public void setItem(GameWorld.Item value) {
    this.value = value;
  }

  /**
   * @return item
   */
  public GameWorld.Item getItem() {
    return value;
  }
}
