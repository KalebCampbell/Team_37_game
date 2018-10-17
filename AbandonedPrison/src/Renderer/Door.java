package Renderer;

import java.awt.Color;
import java.io.File;
/**
 * Represents a Door in 3D space.
 * 
 * @author Harris Joel
 */
public class Door extends Piece {

	/**
	 * Door constructor.
	 * 
	 * @param position
	 *            the center position of this object
	 */
	public Door(Point3D position) {
		this.mesh = Renderer.loadMesh(new File("Models/door.txt"));
		this.mesh = mesh.translate(position.getRealX(), position.getRealY(), position.getRealZ());
		this.position = position;
		this.color = new Color(100, 15, 100);
		setPolygonColors(color);
	}
}
