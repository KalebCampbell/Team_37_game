package GameWorld;

/** Combines x,y into a single object.
 * 
 * @author Michael Vincent
 *
 */
public class Location {
	
	private int x;
	private int y;

	public Location(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

}