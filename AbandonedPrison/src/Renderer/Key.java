package Renderer;

import java.awt.Color;
import java.io.File;

/**
 * Represents an Key in a Room. To be used in a 3D Renderer.
 *
 * @author Joel Harris
 */
public class Key extends AbstractItem {

	/**
	 * Key constructor.
	 * 
	 * @param position
	 *            the center position of this object
	 */
	public Key(Point3D position) {
		this.mesh = Renderer.loadMesh(new File("Models/key.txt"));
		this.mesh = mesh.translate(position.getRealX(), position.getRealY(), position.getRealZ());
		this.position = position;
		this.color = new Color(255, 255, 0);
		setPolygonColors(color);
	}
}
