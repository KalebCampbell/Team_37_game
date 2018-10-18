package Renderer;

import java.awt.Color;
import java.io.File;

/**
 * Represents a Door in 3D space.
 *
 * @author Joel Harris
 */
abstract public class AbstractDoor extends AbstractWall {

	GameWorld.Door door;
	public void openDoor() {
		Point3D pos = this.mesh.getPosition();
		Direction dir = this.mesh.getDirection();
		this.mesh = Renderer.loadMesh(new File("Models/opendoor.txt"));
		if (dir == Direction.SIDE)
			mesh.rotateLeft();
		mesh.translate(pos.getRealX(), 0, pos.getRealZ());
	}

	public void setDoor(GameWorld.Door door) {
		this.door = door;
	}

	public GameWorld.Door getDoor() {
		return this.door;
	}
}
