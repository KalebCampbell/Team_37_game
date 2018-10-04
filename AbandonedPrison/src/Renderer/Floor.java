package Renderer;


public class Floor extends Piece {
	public Floor(Mesh mesh, Point3D position) {
		this.mesh = mesh.translate(position.x, position.y, position.z);
		this.position = position;
	}
}
