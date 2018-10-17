package Renderer;

import java.awt.Color;
import java.io.File;

/**
 * Represents a Floor in a Room. To be used in a 3D Renderer.
 *
 * @author Joel Harris
 */
public class Floor extends Piece {

	/**
	 * Floor constructor.
	 * 
	 * @param position
	 *            the center position of this object
	 */
	public Floor(Point3D position) {
		this.mesh = Renderer.loadMesh(new File("Models/floor.txt"));
		this.mesh.yAxisPolygons();
		this.mesh = mesh.translate(position.getRealX(), position.getRealY(), position.getRealZ());
		this.position = position;
		this.color = new Color(15, 50, 150);
		setPolygonColors(color);
	}
}
