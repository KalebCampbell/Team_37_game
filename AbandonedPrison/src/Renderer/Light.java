package Renderer;


public class Light {
	
	private float[] coords = new float[3];

	public Light(float x, float y, float z) {
		coords[0] = x;
		coords[1] = y;
		coords[2] = z;
	}
	
	public void translate(int x, int y, int z) {
		coords[0] += x;
		coords[1] += y;
		coords[2] += z;
	}
	
	public void rotateLeft() {
		float oldx = coords[0];
		coords[0] = -coords[2];
		coords[2] = oldx;
	}
	
	public void rotateRight() {
		float oldx = coords[0];
		coords[0] = coords[2];
		coords[2] = -oldx;
	}
	
	public float getX() {
		return coords[0];
	}
	public float getY() {
		return coords[1];
	}
	public float getZ() {
		return coords[2];
	}

}
