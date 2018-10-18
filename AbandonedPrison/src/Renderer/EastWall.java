package Renderer;

import java.awt.Color;
import java.io.File;

/**
 * Represents a EastWall in 3D space.
 * 
 * @author Joel Harris
 */
public class EastWall extends Wall {

	/**
	 * EastWall constructor.
	 * 
	 * @param position
	 *            center position of this wall
	 */
	public EastWall(Point3D position) {
		this.mesh = Renderer.loadMesh(new File("Models/sidewall.txt"));
		this.mesh = mesh.translate(position.getRealX(), position.getRealY(), position.getRealZ());
		this.position = position;
		this.color = new Color(80, 255, 255);
		setPolygonColors(color);
	}
}