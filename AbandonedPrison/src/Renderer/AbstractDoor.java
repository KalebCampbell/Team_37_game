package Renderer;

import java.awt.Color;
import java.io.File;

/**
 * Represents a Door in 3D space.
 *
 * @author Joel Harris
 */
abstract public class AbstractDoor extends AbstractWall {

	protected GameWorld.Door door;

	/**
	 * Changes model to open door model.
	 */
	public void openDoor() {
		// get previous mesh info
		Point3D pos = this.mesh.getPosition();
		Direction dir = this.mesh.getDirection();
		// load open door mesh and rotate/translate if necessary
		this.mesh = Renderer.loadMesh(new File("Models/opendoor.txt"));
		if (dir == Direction.SIDE)
			mesh.rotateLeft();
		mesh.translate(pos.getRealX(), 0, pos.getRealZ());
		// set polygon colors
		this.setPolygonColors(this.color);
	}

	/**
	 * @param door
	 *            to be set
	 */
	public void setDoor(GameWorld.Door door) {
		this.door = door;
	}

	/**
	 * @return door
	 */
	public GameWorld.Door getDoor() {
		return this.door;
	}
}
