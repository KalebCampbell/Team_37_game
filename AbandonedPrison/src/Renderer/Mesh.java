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

	/**
	 * Mesh constructor.
	 *
	 * @param polygons
	 */
	public Mesh(Polygon3D[] polygons) {
		this.polygons = polygons;
		calculatePosition();
	}

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
	 * Translates the mesh by the delta values.
	 *
	 * @param x
	 * @param y
	 * @param z
	 */
	public Mesh translate(int x, int y, int z) {
		for (int i = 0; i < polygons.length; i++) {
			polygons[i].translate(x, y, z);
		}
		position.translate(x, y, z);
		return this;
	}

	public Mesh rotateLeft() {
		for (int i = 0; i < polygons.length; i++) {
			polygons[i].rotateLeft();
		}
		position.rotateLeft();
		return this;
	}

	public Mesh rotateRight() {
		for (int i = 0; i < polygons.length; i++) {
			polygons[i].rotateRight();
		}
		position.rotateRight();
		return this;
	}
	
	public PriorityQueue<Polygon3D> orderPolygons(){
		PriorityQueue<Polygon3D> orderedPolygons = new PriorityQueue<Polygon3D>();
		for (Polygon3D poly : polygons) {
			orderedPolygons.add(poly);
		}
		return orderedPolygons;
	}

	public Mesh getCopy() {
		Polygon3D[] copy = new Polygon3D[polygons.length];
		for(int i = 0; i < polygons.length; i++) {
			copy[i] = polygons[i].clone();
		}
		return new Mesh(copy);
	}

	/**
	 * Returns the polygons that make up this mesh.
	 *
	 * @return polygons
	 */
	public Polygon3D[] getPolygons() {
		return polygons;
	}

	public Point3D getPosition() {
		return position;
	}
}
