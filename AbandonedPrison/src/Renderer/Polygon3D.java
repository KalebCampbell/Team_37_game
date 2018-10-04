package Renderer;

import java.awt.Polygon;

/**
 * Represents a polygon in 3D space.
 * 
 * @author Joel Harris
 */
@SuppressWarnings("serial")
public class Polygon3D extends Polygon {

	/**
	 * Center of canvas.
	 */
	public static final int CANVAS_MIDDLE = 300;

	private int[] zPoints;

	/**
	 * Polygon3D constructor.
	 */
	public Polygon3D() {
	}

	/**
	 * Polygon3D constructor.
	 * 
	 * @param xPoints
	 * @param yPoints
	 * @param nPoints
	 */
	public Polygon3D(int[] xPoints, int[] yPoints, int nPoints) {
		super(xPoints, yPoints, nPoints);
		this.zPoints = new int[3];
	}

	/**
	 * Polygon3D constructor.
	 * 
	 * @param xPoints
	 * @param yPoints
	 * @param zPoints
	 * @param nPoints
	 */
	public Polygon3D(int[] xPoints, int[] yPoints, int[] zPoints, int nPoints) {
		this(xPoints, yPoints, nPoints);
		this.zPoints = zPoints;
	}

	/**
	 * Uses the z value associated with each x value to calculate where the x value
	 * would be in 3D space.
	 * 
	 * @return the x points
	 */
	public int[] xPoints3D() {
		int[] xPoints3D = new int[] { xpoints[0], xpoints[1], xpoints[2] };
		int[] zPoints = this.zPoints;
		for (int x = 0; x < xpoints.length; x++) {
			if (zPoints[x] != 0) {
				float f = 300.0f / zPoints[x];
				xPoints3D[x] = 300 + (int) (xPoints3D[x] * Math.abs(f));
			} else {
				xPoints3D[x] = 300 + (xPoints3D[x] * 300);
			}
		}
		return xPoints3D;
	}

	/**
	 * Uses the z value associated with each y value to calculate where the y value
	 * would be in 3D space.
	 * 
	 * @return the y points
	 */
	public int[] yPoints3D() {
		int[] yPoints3D = new int[] { ypoints[0], ypoints[1], ypoints[2] };
		int[] zPoints = this.zPoints;
		for (int y = 0; y < ypoints.length; y++) {
			if (zPoints[y] != 0) {
				float f = 300.0f / zPoints[y];
				yPoints3D[y] = 300 + (int) (yPoints3D[y] * Math.abs(f));
			} else {
				yPoints3D[y] = 300 + (yPoints3D[y] * 300);
			}
		}
		return yPoints3D;
	}

	/**
	 * Translates each vertex by the delta values provided.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void translate(int x, int y, int z) {
		for (int i = 0; i < xpoints.length; i++) {
			xpoints[i] += x;
			ypoints[i] += y;
			zPoints[i] += z;
		}
	}

	public void rotateLeft() {
		for (int i = 0; i < xpoints.length; i++) {
			int x = xpoints[i];
			xpoints[i] = -zPoints[i];
			zPoints[i] = x;
		}
	}

	public void rotateRight() {
		for (int i = 0; i < xpoints.length; i++) {
			int x = xpoints[i];
			xpoints[i] = zPoints[i];
			zPoints[i] = -x;
		}
	}

	/**
	 * @return the zPoints
	 */
	public int[] getxPoints() {
		return xpoints;
	}

	/**
	 * @param xPoints
	 *            the xPoints to set
	 */
	public void setxPoints(int[] xPoints) {
		this.xpoints = xPoints;
	}

	/**
	 * @return the zPoints
	 */
	public int[] getyPoints() {
		return ypoints;
	}

	/**
	 * @param yPoints
	 *            the yPoints to set
	 */
	public void setyPoints(int[] yPoints) {
		this.ypoints = yPoints;
	}

	/**
	 * @return the zPoints
	 */
	public int[] getzPoints() {
		return zPoints;
	}

	/**
	 * @param zPoints
	 *            the zPoints to set
	 */
	public void setzPoints(int[] zPoints) {
		this.zPoints = zPoints;
	}

	public Polygon3D clone() {
		int[] xPoints = new int[] { xpoints[0], xpoints[1], xpoints[2] };
		int[] yPoints = new int[] { ypoints[0], ypoints[1], ypoints[2] };
		int[] zPoints = new int[] { this.zPoints[0], this.zPoints[1], this.zPoints[2] };
		return new Polygon3D(xPoints, yPoints, zPoints, npoints);
	}

	public String toString() {
		int[] xPoints3D = xPoints3D();
		int[] yPoints3D = yPoints3D();
		String str = "2dpoints: \nx1: " + xpoints[0] + " y1: " + ypoints[0] + "\nx2: " + xpoints[1]
				+ " y2: " + ypoints[1] + "\nx3: " + xpoints[2] + " y3: " + ypoints[2] + "\n";
		return str.concat("3dpoints: \nx1: " + xPoints3D[0] + " y1: " + yPoints3D[0] + " z1: " + zPoints[0] + "\nx2: " + xPoints3D[1]
				+ " y2: " + yPoints3D[1] + " z2: " + zPoints[1] + "\nx3: " + xPoints3D[2] + " y3: " + yPoints3D[2]
				+ " z3: " + zPoints[2]);
	}

}
