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
		if (this.position.getRealZ() > other.getPosition().getRealZ())
			return -1;
		if (this.position.getRealZ() < other.getPosition().getRealZ())
			return 1;
		if (Math.abs(this.position.getRealX()) > Math.abs(other.getPosition().getRealX()))
			return -1;
		if (Math.abs(this.position.getRealX()) < Math.abs(other.getPosition().getRealX()))
			return 1;
		return 0;
	}
	
	public void setItem(GameWorld.Item value) {
		this.value = value;
	}

	public GameWorld.Item getItem() {
		return value;
	}
}
