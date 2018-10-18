package Renderer;

import java.awt.Color;
import java.util.PriorityQueue;

/**
 * Respresents one object in a 3D environment. Holds all of the polygons for the
 * object.
 *
 * @author Joel Harris
 */
public class Mesh {

	private Polygon3D[] polygons;
	private Point3D position;
	private Direction dir;

	/**
	 * Mesh constructor.
	 *
	 * @param polygons
	 *            Polygons in this Mesh.
	 */
	public Mesh(Polygon3D[] polygons) {
		this.polygons = polygons;
		dir = Direction.FRONT;
		calculatePosition();
	}

	/**
	 * Calculates the center position of this Mesh.
	 */
	private void calculatePosition() {
		float minX = Float.MAX_VALUE;
		float maxX = Float.MIN_VALUE;
		float minY = Float.MAX_VALUE;
		float maxY = Float.MIN_VALUE;
		float minZ = Float.MAX_VALUE;
		float maxZ = Float.MIN_VALUE;
		for (Polygon3D poly : polygons) {
			float[] xPoints = poly.getxPoints();
			float[] yPoints = poly.getyPoints();
			float[] zPoints = poly.getzPoints();
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
		}
		int midX = (int) ((minX + maxX) / 2);
		int midY = (int) ((minY + maxY) / 2);
		int midZ = (int) ((minZ + maxZ) / 2);
		position = new Point3D(midX, midY, midZ);
	}

	/**
	 * Translates the Mesh by the delta values.
	 *
	 * @param x
	 *            delta x
	 * @param y
	 *            delta y
	 * @param z
	 *            delta z
	 * @return the updated Mesh
	 */
	public Mesh translate(float x, float y, float z) {
		for (int i = 0; i < polygons.length; i++) {
			polygons[i].translate(x, y, z);
		}
		position.translate(x, y, z);
		return this;
	}

	/**
	 * Rotates the Mesh 90 degrees to left.
	 * 
	 * @return the updated Mesh
	 */
	public Mesh rotateLeft() {
		for (int i = 0; i < polygons.length; i++) {
			polygons[i].rotateLeft();
		}
		position.rotateLeft();
		if(dir == Direction.FRONT)
			dir = Direction.SIDE;
		if(dir == Direction.SIDE)
			dir = Direction.FRONT;
		return this;
	}
	
	/**
	 * Rotates the Mesh 90 degrees to right.
	 * 
	 * @return the updated Mesh
	 */
	public Mesh rotateRight() {
		for (int i = 0; i < polygons.length; i++) {
			polygons[i].rotateRight();
		}
		position.rotateRight();
		if(dir == Direction.FRONT)
			dir = Direction.SIDE;
		if(dir == Direction.SIDE)
			dir = Direction.FRONT;
		return this;
	}

	/**
	 * Converts this meshes polygons into YAxisPolygon's which uses a compareTo
	 * method based on the polygons Y axis.
	 */
	public void yAxisPolygons() {
		for (int i = 0; i < polygons.length; i++) {
			polygons[i] = new YAxisPolygon3D(polygons[i].getxPoints(), polygons[i].getyPoints(),
					polygons[i].getzPoints(), 3);
		}
	}

	/**
	 * Uses the compareTo method in Polygon3D to order the Polygons3D using a
	 * PriorityQueue.
	 * 
	 * @return PriorityQueue of Polygon3Ds
	 */
	public PriorityQueue<Polygon3D> orderPolygons() {
		PriorityQueue<Polygon3D> orderedPolygons = new PriorityQueue<Polygon3D>();
		for (Polygon3D poly : polygons) {
			orderedPolygons.add(poly);
		}
		return orderedPolygons;
	}

	public Mesh getCopy() {
		Polygon3D[] copy = new Polygon3D[polygons.length];
		for (int i = 0; i < polygons.length; i++) {
			copy[i] = polygons[i].clone();
		}
		return new Mesh(copy);
	}
	
	/**
	 * @return direction
	 */
	public Direction getDirection() {
		return dir;
	}

	/**
	 * Returns the polygons that make up this Mesh.
	 *
	 * @return the polygons that make up this Mesh
	 */
	public Polygon3D[] getPolygons() {
		return polygons;
	}

	/**
	 * Returns the center position of this Mesh.
	 * 
	 * @return the center position
	 */
	public Point3D getPosition() {
		return position;
	}
}
