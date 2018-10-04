package Renderer;


public abstract class Piece {

	protected Mesh mesh;
	protected Point3D position;
	
	public void translate(int x, int y, int z) {
		mesh.translate(x, y, z);
		position.translate(x, y, z);
	}

	public void rotateLeft() {
		mesh.rotateLeft();
		position.rotateLeft();
	}

	public void rotateRight() {
		mesh.rotateRight();
		position.rotateRight();
	}

	public Point3D getPosition() {
		return position;
	}
	
	public Mesh getMesh() {
		return mesh;
	}
}
