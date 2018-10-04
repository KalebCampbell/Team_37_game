import java.awt.Point;

/**
 * Represents a point in 3D space.
 * 
 * @author Joel Harris
 */
@SuppressWarnings("serial")
public class Point3D extends Point {

	/**
	 * Z value of this point.
	 */
	public int z;

	/**
	 * Point3D constructor.
	 */
	public Point3D() {
		this.z = 1;
	}

	/**
	 * Point3D constructor.
	 */
	public Point3D(Point point) {
		super(point);
		this.z = 1;
	}

	/**
	 * Point3D constructor.
	 */
	public Point3D(int x, int y) {
		super(x, y);
		this.z = 1;
	}

	/**
	 * Point3D constructor.
	 */
	public Point3D(int x, int y, int z) {
		super(x, y);
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
		int oldx = (int)getX();
		x = -getZ();
		z = oldx;
	}
	
	public void rotateRight() {
		int oldx = (int)getX();
		x = getZ();
		z = -oldx;
	}
	
	public int getZ() {
		return z;
	}
	
	
}
