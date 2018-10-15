package Renderer;
import java.awt.Color;

public class Wall extends Piece {

	public Wall(Mesh mesh, Point3D position) {
		this.mesh = mesh.translate(position.getX(), position.getY(), position.getZ());
		this.position = position;
		this.color = new Color(58, 40, 124);
		setPolygonColors(color);
	}
}
