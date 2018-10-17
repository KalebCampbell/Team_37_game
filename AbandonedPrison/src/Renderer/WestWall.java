package Renderer;

import java.awt.Color;
import java.io.File;

/**
 * Represents a WestWall in 3D space.
 * 
 * @author Joel Harris
 */
public class WestWall extends Wall {

	/**
	 * WestWall constructor.
	 * 
	 * @param position
	 *            center position of this wall
	 */
	public WestWall(Point3D position) {
		this.mesh = Renderer.loadMesh(new File("Models/sidewall.txt"));
		this.mesh = mesh.translate(position.getRealX(), position.getRealY(), position.getRealZ());
		this.position = position;
		this.color = new Color(58, 40, 124);
		setPolygonColors(color);
	}
}