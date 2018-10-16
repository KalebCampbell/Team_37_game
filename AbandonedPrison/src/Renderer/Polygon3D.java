package Renderer;

import java.awt.Color;
import java.awt.Polygon;
import java.util.PriorityQueue;

/**
 * Represents a polygon in 3D space.
 * 
 * @author Joel Harris
 */
@SuppressWarnings("serial")
public class Polygon3D implements Comparable<Polygon3D> {

	/**
	 * Center of canvas.
	 */
	public static final int CANVAS_MIDDLE = 300;

	private float[] xPoints;
	private float[] yPoints;
	private float[] zPoints;
	private int nPoints;
	private Color color;
	private Point3D position;

	/**
	 * Polygon3D constructor.
	 * 
	 * @param xPoints
	 *            x points
	 * @param yPoints
	 *            y points
	 * @param zPoints
	 *            z points
	 * @param nPoints
	 *            number of points
	 */
	public Polygon3D(float[] xPoints, float[] yPoints, float[] zPoints, int nPoints) {
		this.xPoints = xPoints;
		this.yPoints = yPoints;
		this.zPoints = zPoints;
		this.nPoints = nPoints;
		calculatePosition();
	}

	/**
	 * Calculates the center position of this Polygon.
	 */
	private void calculatePosition() {
		float minX = Float.MAX_VALUE;
		float maxX = Float.MIN_VALUE;
		float minY = Float.MAX_VALUE;
		float maxY = Float.MIN_VALUE;
		float minZ = Float.MAX_VALUE;
		float maxZ = Float.MIN_VALUE;
		float[] xPoints = getxPoints();
		float[] yPoints = getyPoints();
		float[] zPoints = getzPoints();
		for (int i = 0; i < xPoints.length; i++) {
			if (xPoints[i] > maxX)
				maxX = xPoints[i];
			else if (xPoints[i] < minX)
				minX = xPoints[i];
			if (yPoints[i] > maxY)
				maxY = yPoints[i];
			else if (yPoints[i] < minY)
				minY = yPoints[i];
			if (zPoints[i] > maxZ)
				maxZ = zPoints[i];
			else if (zPoints[i] < minZ)
				minZ = zPoints[i];
		}
		float midX = ((minX + maxX) / 2);
		float midY = ((minY + maxY) / 2);
		float midZ = ((minZ + maxZ) / 2);
		position = new Point3D(midX, midY, midZ);
	}

	/**
	 * Uses the z value associated with each x value to calculate where the x value
	 * would be in 3D space.
	 * 
	 * @return the x points
	 */
	public int[] xPoints3D() {
		float[] xPointsOG = new float[] { xPoints[0], xPoints[1], xPoints[2] };
		int[] xPoints3D = new int[3];
		float[] zPoints = this.zPoints;
		for (int x = 0; x < xPoints.length; x++) {
			if (zPoints[x] != 0) {
				float f = 300.0f / zPoints[x];
				xPoints3D[x] = (int) (300 + (xPointsOG[x] * Math.abs(f)));
			} else {
				xPoints3D[x] = (int) (300 + (xPointsOG[x] * 300));
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
		float[] yPointsOG = new float[] { yPoints[0], yPoints[1], yPoints[2] };
		int[] yPoints3D = new int[3];
		float[] zPoints = this.zPoints;
		for (int y = 0; y < yPoints.length; y++) {
			if (zPoints[y] != 0) {
				float f = 300.0f / zPoints[y];
				yPoints3D[y] = (int) (300 + (yPointsOG[y] * Math.abs(f)));
			} else {
				yPoints3D[y] = (int) (300 + (yPointsOG[y] * 300));
			}
		}
		return yPoints3D;
	}

	/**
	 * Translates each vertex by the delta values provided.
	 * 
	 * @param x
	 *            delta x
	 * @param y
	 *            delta y
	 * @param z
	 *            delta z
	 */
	public void translate(float x, float y, float z) {
		for (int i = 0; i < xPoints.length; i++) {
			xPoints[i] += x;
			yPoints[i] += y;
			zPoints[i] += z;
		}
		position.translate(x, y, z);
	}

	/**
	 * Rotates each vertex 90 degrees to the left.
	 */
	public void rotateLeft() {
		for (int i = 0; i < xPoints.length; i++) {
			float x = xPoints[i];
			xPoints[i] = -zPoints[i];
			zPoints[i] = x;
		}
		position.rotateLeft();
	}

	/**
	 * Rotates each vertex 90 degrees to the right.
	 */
	public void rotateRight() {
		for (int i = 0; i < xPoints.length; i++) {
			float x = xPoints[i];
			xPoints[i] = zPoints[i];
			zPoints[i] = -x;
		}
		position.rotateRight();
	}

	/**
	 * Creates a clone of this Polygon3D.
	 * 
	 * @return a clone of this Polygon3D
	 */
	public Polygon3D clone() {
		float[] xPoints = new float[] { this.xPoints[0], this.xPoints[1], this.xPoints[2] };
		float[] yPoints = new float[] { this.yPoints[0], this.yPoints[1], this.yPoints[2] };
		float[] zPoints = new float[] { this.zPoints[0], this.zPoints[1], this.zPoints[2] };
		return new Polygon3D(xPoints, yPoints, zPoints, nPoints);
	}

	/**
	 * Returns a string representation of this object.
	 * 
	 * @return a string representation of this object.
	 */
	public String toString() {
		int[] xPoints3D = xPoints3D();
		int[] yPoints3D = yPoints3D();
		String str = "2dpoints: \nx1: " + xPoints[0] + " y1: " + yPoints[0] + "\nx2: " + xPoints[1] + " y2: "
				+ yPoints[1] + "\nx3: " + xPoints[2] + " y3: " + yPoints[2] + "\n";
		return str.concat("3dpoints: \nx1: " + xPoints3D[0] + " y1: " + yPoints3D[0] + " z1: " + zPoints[0] + "\nx2: "
				+ xPoints3D[1] + " y2: " + yPoints3D[1] + " z2: " + zPoints[1] + "\nx3: " + xPoints3D[2] + " y3: "
				+ yPoints3D[2] + " z3: " + zPoints[2]);
	}

	@Override
	public int compareTo(Polygon3D other) {
		if (Math.abs(this.position.getRealY()) > Math.abs(other.getPosition().getRealY()))
			return -1;
		if (Math.abs(this.position.getRealY()) < Math.abs(other.getPosition().getRealY()))
			return 1;
		if (this.position.getRealZ() > other.getPosition().getRealZ())
			return -1;
		if (this.position.getRealZ() < other.getPosition().getRealZ())
			return 1;
		return 0;
	}

	/**
	 * @return the xPoints
	 */
	public float[] getxPoints() {
		return xPoints;
	}

	/**
	 * @param xPoints
	 *            the xPoints to set
	 */
	public void setxPoints(float[] xPoints) {
		this.xPoints = xPoints;
	}

	/**
	 * @return the yPoints
	 */
	public float[] getyPoints() {
		return yPoints;
	}

	/**
	 * @param yPoints
	 *            the yPoints to set
	 */
	public void setyPoints(float[] yPoints) {
		this.yPoints = yPoints;
	}

	/**
	 * @return the zPoints
	 */
	public float[] getzPoints() {
		return zPoints;
	}

	/**
	 * @param zPoints
	 *            the zPoints to set
	 */
	public void setzPoints(float[] zPoints) {
		this.zPoints = zPoints;
	}

	/**
	 * @return the number of points
	 */
	public int getnPoints() {
		return nPoints;
	}

	/**
	 * @param color
	 *            the color to be set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * @return the position
	 */
	public Point3D getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Point3D position) {
		this.position = position;
	}
}
