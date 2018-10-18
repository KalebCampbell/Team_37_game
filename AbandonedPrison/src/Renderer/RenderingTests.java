package Renderer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

public class RenderingTests {
	@Test
	public void hiddenPolyTest() {
		Polygon3D poly = new Polygon3D(new float[] { 2, -1, 0 }, new float[] { 1, 1, 0 }, new float[] { 1, 1, 1 }, 3);
		assertTrue(Pipeline.isHidden(poly));
	}

	@Test
	public void shownPolyTest() {
		Polygon3D poly = new Polygon3D(new float[] { 0, -1, 2 }, new float[] { 0, 1, 1 }, new float[] { 1, 1, 1 }, 3);
		assertFalse(Pipeline.isHidden(poly));
	}
	
	@Test
	public void noLightingTest() {
		Polygon3D poly = new Polygon3D(new float[] { 0, -1, 2 }, new float[] { 0, 1, 1 }, new float[] { 1, 1, 1 }, 3);
		poly.setColor(new Color(255, 255, 255));
		float[] light = new float[]{0, 0, 1};
		Color shaded = Pipeline.getShading(poly, light);
		assertTrue(shaded.getRed() == 0);
		assertTrue(shaded.getGreen() == 0);
		assertTrue(shaded.getBlue() == 0);
	}
	
	@Test
	public void fullLightingTest() {
		Polygon3D poly = new Polygon3D(new float[] { 0, -1, 2 }, new float[] { 0, 1, 1 }, new float[] { 1, 1, 1 }, 3);
		poly.setColor(new Color(255, 255, 255));
		float[] light = new float[]{0, 0, -1};
		Color shaded = Pipeline.getShading(poly, light);
		assertTrue(shaded.getRed() == 255);
		assertTrue(shaded.getGreen() == 255);
		assertTrue(shaded.getBlue() == 255);
	}
}
