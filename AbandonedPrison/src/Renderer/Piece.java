package Renderer;
import java.awt.Color;

public abstract class Piece {

	protected Mesh mesh;
	protected Point3D position;
	protected Color color;
	
	public void translate(int x, int y, int z) {
		mesh.translate(x, y, z);
		position.translate(x, y, z);
	}

	public void rotateLeft() {
		mesh.rotateLeft();
		position.rotateLeft();
	}

	public void rotateRight() {
		mesh.rotateRight();
		position.rotateRight();
	}

	public Point3D getPosition() {
		return position;
	}
	
	public Mesh getMesh() {
		return mesh;
	}
	
	public void setPolygonColors(Color color) {
		Polygon3D[] polygons = mesh.getPolygons();
		for(int i = 0; i < polygons.length; i++) {
			polygons[i].setColor(color);
		}
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}
