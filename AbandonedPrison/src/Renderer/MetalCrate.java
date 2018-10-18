package Renderer;

import java.awt.Color;
import java.io.File;

/**
 * Respresents a metal crate in a 3D environment.
 * 
 * @author Joel Harris
 */
public class MetalCrate extends AbstractCrate {
	
	/**
	 * MetalCrate constructor.
	 * 
	 * @param position
	 *            the center position of this object
	 */
	public MetalCrate(Point3D position) {
		this.mesh = Renderer.loadMesh(new File("Models/crate.txt"));
		this.mesh = mesh.translate(position.getX(), position.getY(), position.getZ());
		this.position = position;
		this.color = new Color(70, 70, 70);
		setPolygonColors(color);
	}
}
