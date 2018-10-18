package Renderer;

/**
 * Used for certain objects that need their polygons drawn based on the Y axis.
 * 
 * @author Joel Harris
 *
 */
public class YAxisPolygon3D extends Polygon3D {

	public YAxisPolygon3D(float[] xPoints, float[] yPoints, float[] zPoints, int nPoints) {
		super(xPoints, yPoints, zPoints, nPoints);
	}

	@Override
	public int compareTo(Polygon3D other) {
		// compares on y axis first
		if (Math.abs(this.position.getRealY()) > Math.abs(other.getPosition().getRealY()))
			return -1;
		if (Math.abs(this.position.getRealY()) < Math.abs(other.getPosition().getRealY()))
			return 1;
		// then on z axis
		if (this.position.getRealZ() > other.getPosition().getRealZ())
			return -1;
		if (this.position.getRealZ() < other.getPosition().getRealZ())
			return 1;
		return 0;
	}
}
