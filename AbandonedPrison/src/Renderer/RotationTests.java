package Renderer;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class RotationTests {
	@Test
	public void rotateRoomTest1() {
		Room room = new Room(new Point3D(10, 0, 0), new ArrayList<Wall>(), new Item[4][4],
				new Floor(new Point3D(0, 0, 0)));
		room.rotateLeft();
		assertTrue(room.getPosition().getRealX() == 0);
		assertTrue(room.getPosition().getRealY() == 0);
		assertTrue(room.getPosition().getRealZ() == 10);
	}

	@Test
	public void rotateRoomTest2() {
		Room room = new Room(new Point3D(10, 0, 0), new ArrayList<Wall>(), new Item[4][4],
				new Floor(new Point3D(0, 0, 0)));
		room.rotateRight();
		assertTrue(room.getPosition().getRealX() == 0);
		assertTrue(room.getPosition().getRealY() == 0);
		assertTrue(room.getPosition().getRealZ() == -10);
	}

	@Test
	public void rotateMeshTest1() {
		Mesh mesh = Renderer.loadMesh(new File("Models/frontwall.txt"));
		mesh.translate(10, 0, 0);
		mesh.rotateLeft();
		assertTrue(mesh.getPosition().getRealX() == 0);
		assertTrue(mesh.getPosition().getRealY() == 0);
		assertTrue(mesh.getPosition().getRealZ() == 10);
	}

	@Test
	public void rotateMeshTest2() {
		Mesh mesh = Renderer.loadMesh(new File("Models/frontwall.txt"));
		mesh.translate(10, 0, 0);
		mesh.rotateRight();
		assertTrue(mesh.getPosition().getRealX() == 0);
		assertTrue(mesh.getPosition().getRealY() == 0);
		assertTrue(mesh.getPosition().getRealZ() == -10);
	}
}
