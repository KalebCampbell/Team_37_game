package Renderer;

import java.awt.Color;
import java.io.File;

/**
 * Represents a Wall in 3D space.
 * 
 * @author Joel Harris
 */
abstract public class Wall extends Piece {

	protected Door door;

	public Door getDoor() {
		return door;
	}
	
	public void addDoor(Door door) {
		this.door = door;
	}

	/**
	 * Translates the object by the delta values provided.
	 * 
	 * @param x
	 *            delta x
	 * @param y
	 *            delta y
	 * @param z
	 *            delta z
	 */
	public void translate(float x, float y, float z) {
		mesh.translate(x, y, z);
		position.translate(x, y, z);
		if (door != null)
			door.translate(x, y, z);
	}

	/**
	 * Rotates the object 90 degrees to the left.
	 */
	public void rotateLeft() {
		mesh.rotateLeft();
		position.rotateLeft();
		if (door != null)
			door.rotateLeft();
	}

	/**
	 * Rotates the object 90 degrees to the right.
	 */
	public void rotateRight() {
		mesh.rotateRight();
		position.rotateRight();
		if (door != null)
			door.rotateRight();
	}
}
