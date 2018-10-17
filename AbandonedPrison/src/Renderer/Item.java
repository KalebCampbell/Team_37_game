package Renderer;

/**
 * Represents an Item in a Room. To be used in a 3D Renderer.
 *
 * @author Joel Harris
 */
abstract public class Item extends Piece implements Comparable<Item> {
	@Override
	public int compareTo(Item other) {
		if(this.position.getRealZ() > other.getPosition().getRealZ())
			return -1;
		if(this.position.getRealZ() < other.getPosition().getRealZ())
			return 1;
		if(Math.abs(this.position.getRealX()) > Math.abs(other.getPosition().getRealX()))
			return -1;
		if(Math.abs(this.position.getRealX()) < Math.abs(other.getPosition().getRealX()))
			return 1;
		return 0;
	}
}
