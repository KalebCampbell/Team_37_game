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
	public void translate(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;

	}

	public void rotateLeft() {
		float oldx = x;
		x = -z;
		z = oldx;
	}

	public void rotateRight() {
		float oldx = x;
		x = z;
		z = -oldx;
	}

	public int getX() {
		return Math.round(x);
	}

	public float getRealX() {
		return x;
	}

	public int getY() {
		return Math.round(y);
	}

	public float getRealY() {
		return y;
	}

	public int getZ() {
		return Math.round(z);
	}

	public float getRealZ() {
		return z;
	}
}
