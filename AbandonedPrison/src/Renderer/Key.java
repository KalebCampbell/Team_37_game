package Renderer;
import java.awt.Color;

public class Key extends Item {
	public Key(Mesh mesh, Point3D position) {
		this.mesh = mesh.translate(position.getX(), position.getY(), position.getZ());
		this.position = position;
		this.color = new Color(255, 255, 0);
		setPolygonColors(color);
	}
}
