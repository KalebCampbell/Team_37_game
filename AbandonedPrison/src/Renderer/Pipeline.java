package Renderer;
import java.awt.Color;

/**
 * Contains useful static methods for colouring and printing polygons. Uses
 * method stubs provided in COMP 261.
 *
 * @author Joel Harris
 */
public class Pipeline {

	/**
	 * Works out if a polygon is facing away from the viewer and whether it should
	 * be drawn.
	 *
	 * @param poly
	 * @return whether the polygon is hidden
	 */
	public static boolean isHidden(Polygon3D poly) {
		int[] xPoints = poly.xPoints3D();
		int[] yPoints = poly.yPoints3D();
		float[] zPoints = poly.getzPoints();
		float[] a = new float[] { xPoints[1] - xPoints[0], yPoints[1] - yPoints[0], zPoints[1] - zPoints[0] };
		float[] b = new float[] { xPoints[2] - xPoints[1], yPoints[2] - yPoints[1], zPoints[2] - zPoints[1] };
		float[] normal = new float[3];
		normal[0] = a[1] * b[2] - a[2] * b[1];
		normal[1] = a[2] * b[0] - a[0] * b[2];
		normal[2] = a[0] * b[1] - a[1] * b[0];
		return normal[2] > 0;
	}

	/**
	 * Calculates the colour for a polygon to be drawn in based on the incident and
	 * ambient lighting.
	 *
	 * @param poly
	 * @param lightDirection
	 * @param lightColor
	 * @param ambientLight
	 * @return the color of this polygon
	 */
	public static Color getShading(Polygon3D poly, float[] lightDirection, Color lightColor, Color ambientLight) {
		float[] incidentLight = lightDirection;
		float[] ambientColour = new float[3];
		ambientColour[0] = 0.5f;
		ambientColour[1] = 0.5f;
		ambientColour[2] = 0.5f;
		float[] incidentColour = new float[3];
		incidentColour[0] = 0.5f;
		incidentColour[1] = 0.5f;
		incidentColour[2] = 0.5f;
		int[] polyColour = new int[3];
		polyColour[0] = poly.getColor().getRed();
		polyColour[1] = poly.getColor().getGreen();
		polyColour[2] = poly.getColor().getBlue();
		float[] xPoints = poly.getxPoints();
		float[] yPoints = poly.getyPoints();
		float[] zPoints = poly.getzPoints();
		float[] a = new float[] { xPoints[1] - xPoints[0], yPoints[1] - yPoints[0], zPoints[1] - zPoints[0] };
		float[] b = new float[] { xPoints[2] - xPoints[1], yPoints[2] - yPoints[1], zPoints[2] - zPoints[1] };
		float[] normal = new float[3];
		normal[0] = a[1] * b[2] - a[2] * b[1];
		normal[1] = a[2] * b[0] - a[0] * b[2];
		normal[2] = a[0] * b[1] - a[1] * b[0];
		float normalMag = (float) Math
				.sqrt((normal[0] * normal[0]) + (normal[1] * normal[1]) + (normal[2] * normal[2]));
		float lightMag = (float) Math.sqrt((incidentLight[0] * incidentLight[0]) + (incidentLight[1] * incidentLight[1])
				+ (incidentLight[2] * incidentLight[2]));
		float dotProd = (normal[0] * incidentLight[0]) + (normal[1] * incidentLight[1])
				+ (normal[2] * incidentLight[2]);
		float cosTheta = ((dotProd) / normalMag) / lightMag;
		int[] colour = new int[3];
		for (int i = 0; i < 3; i++) {
			colour[i] = (int) ((float) (ambientColour[i] * polyColour[i])
					+ ((float) ((polyColour[i] * incidentColour[i]) * (cosTheta))));
			if (colour[i] > 255)
				colour[i] = 255;
			if (colour[i] < 0)
				colour[i] = 0;
		}
		return new Color(colour[0], colour[1], colour[2]);
	}
}
