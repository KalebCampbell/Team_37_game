package Renderer;


public class Wall extends Piece {
	
	public Wall(Mesh mesh, Point3D position) {
		this.mesh = mesh.translate(position.x, position.y, position.z);
		this.position = position;
	}
}
