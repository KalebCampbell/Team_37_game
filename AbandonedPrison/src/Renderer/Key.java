package Renderer;

import java.awt.Color;

/**
 * Represents an Key in a Room. To be used in a 3D Renderer.
 *
 * @author Joel Harris
 */
public class Key extends Item {

	/**
	 * Key constructor.
	 * 
	 * @param mesh
	 *            the mesh of this key
	 * @param position
	 *            the center position of this object
	 */
	public Key(Mesh mesh, Point3D position) {
		this.mesh = mesh.translate(position.getX(), position.getY(), position.getZ());
		this.position = position;
		this.color = new Color(255, 255, 0);
		setPolygonColors(color);
	}
}
