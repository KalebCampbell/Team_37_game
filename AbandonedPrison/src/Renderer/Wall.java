package Renderer;

import java.awt.Color;

/**
 * Represents a Wall in 3D space.
 * 
 * @author Joel Harris
 */
public class Wall extends Piece {

	/**
	 * Wall constructor.
	 * 
	 * @param mesh
	 *            mesh of this wall
	 * @param position
	 *            center position of this wall
	 */
	public Wall(Mesh mesh, Point3D position) {
		this.mesh = mesh.translate(position.getX(), position.getY(), position.getZ());
		this.position = position;
		this.color = new Color(58, 40, 124);
		setPolygonColors(color);
	}
}
