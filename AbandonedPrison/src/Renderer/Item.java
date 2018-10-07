
public class Item extends Piece {
	public Item(Mesh mesh, Point3D position) {
		this.mesh = mesh.translate(position.x, position.y, position.z);
		this.position = position;
	}
}
