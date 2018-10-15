package Renderer;
import java.awt.Color;

public class Floor extends Piece {
	public Floor(Mesh mesh, Point3D position) {
		this.mesh = mesh.translate(position.getX(), position.getY(), position.getZ());
		this.position = position;
		this.color = new Color(200, 15, 15);
		setPolygonColors(color);
	}
}
