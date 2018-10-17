package Renderer;

import java.awt.Color;
import java.io.File;

/**
 * Represents a SouthWall in 3D space.
 * 
 * @author Joel Harris
 */
public class SouthWall extends Wall {

	/**
	 * NorthWall constructor.
	 * 
	 * @param position
	 *            center position of this wall
	 */
	public SouthWall(Point3D position) {
		this.mesh = Renderer.loadMesh(new File("Models/frontwall.txt"));
		this.mesh = mesh.translate(position.getRealX(), position.getRealY(), position.getRealZ());
		this.position = position;
		this.color = new Color(80, 255, 255);
		setPolygonColors(color);
	}
}