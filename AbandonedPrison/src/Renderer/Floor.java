package Renderer;

import java.awt.Color;

/**
 * Represents a Floor in a Room. To be used in a 3D Renderer.
 *
 * @author Joel Harris
 */
public class Floor extends Piece {

	/**
	 * Floor constructor.
	 * 
	 * @param mesh
	 *            the mesh of this Floor
	 * @param position
	 *            the center position of this object
	 */
	public Floor(Mesh mesh, Point3D position) {
		this.mesh = mesh.translate(position.getX(), position.getY(), position.getZ());
		this.position = position;
		this.color = new Color(200, 15, 15);
		setPolygonColors(color);
	}
}
