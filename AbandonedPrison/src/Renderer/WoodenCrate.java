package Renderer;

import java.awt.Color;
import java.io.File;

/**
 * Respresents a wooden crate in a 3D environment.
 * 
 * @author Joel Harris
 */
public class WoodenCrate extends Crate {

	/**
	 * WoodenCrate constructor.
	 * 
	 * @param position
	 *            the center position of this object
	 */
	public WoodenCrate(Point3D position) {
		this.mesh = Renderer.loadMesh(new File("Models/crate.txt"));
		this.mesh = mesh.translate(position.getX(), position.getY(), position.getZ());
		this.position = position;
		this.color = new Color(117, 70, 43);
		setPolygonColors(color);
	}
}
