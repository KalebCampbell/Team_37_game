package Renderer;

import java.awt.Color;
import java.io.File;

/**
 * Respresents a SideDoor object in 3D space.
 * 
 * @author Joel Harris
 */
public class SideDoor extends AbstractDoor {
	/**
	 * SideDoor constructor.
	 *
	 * @param position
	 *            the center position of this object
	 */
	public SideDoor(Point3D position) {
		this.mesh = Renderer.loadMesh(new File("Models/closeddoor.txt")).rotateRight();
		this.mesh = mesh.translate(position.getRealX(), position.getRealY(), position.getRealZ());
		this.position = position;
		this.color = new Color(80, 255, 255);
		setPolygonColors(color);
	}
}
