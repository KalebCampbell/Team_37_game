package Renderer;

import java.awt.Color;
import java.io.File;

/**
 * Represents a NorthWall in 3D space.
 * 
 * @author Joel Harris
 */
public class NorthWall extends Wall {
	
	/**
	 * NorthWall constructor.
	 * 
	 * @param position
	 *            center position of this wall
	 */
	public NorthWall(Point3D position) {
		door = new Door(position);
		this.mesh = Renderer.loadMesh(new File("Models/frontwall.txt"));
		this.mesh = mesh.translate(position.getRealX(), position.getRealY(), position.getRealZ());
		this.position = position;
		this.color = new Color(58, 40, 124);
		setPolygonColors(color);
	}
}