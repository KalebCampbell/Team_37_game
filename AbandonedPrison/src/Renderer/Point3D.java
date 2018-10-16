package Renderer;

import java.awt.Point;

/**
 * Represents a point in 3D space.
 *
 * @author Joel Harris
 */
@SuppressWarnings("serial")
public class Point3D {

	private float x;
	private float y;
	private float z;

	/**
	 * Point3D constructor.
	 * 
	 * @param x
	 *            x value
	 * @param y
	 *            y value
	 * @param z
	 *            z value
	 */
	public Point3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Translates the point by the given delta values.
	 *
	 * @param x
	 *            delta x
	 * @param y
	 *            delta y
	 * @param z
	 *            delta z
	 */
	public void translate(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;

	}

	/**
	 * Rotates the point 90 degree to the left.
	 */
	public void rotateLeft() {
		float oldx = x;
		x = -z;
		z = oldx;
	}

	/**
	 * Rotates the point 90 degree to the right.
	 */
	public void rotateRight() {
		float oldx = x;
		x = z;
		z = -oldx;
	}

	/**
	 * @return rounded x value
	 */
	public int getX() {
		return Math.round(x);
	}

	/**
	 * @return float x value
	 */
	public float getRealX() {
		return x;
	}

	/**
	 * @return rounded y value
	 */
	public int getY() {
		return Math.round(y);
	}

	/**
	 * @return float y value
	 */
	public float getRealY() {
		return y;
	}

	/**
	 * @return rounded z value
	 */
	public int getZ() {
		return Math.round(z);
	}

	/**
	 * @return float z value
	 */
	public float getRealZ() {
		return z;
	}
}
