package Renderer;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class TranslationTests {
	@Test
	public void translateRoomTest1() {
		Room room = new Room(new Point3D(0, 0, 0), new ArrayList<Wall>(), new Item[4][4],
				new Floor(new Point3D(0, 0, 0)));
		assertTrue(room.getPosition().getRealX() == 0);
		assertTrue(room.getPosition().getRealY() == 0);
		assertTrue(room.getPosition().getRealZ() == 0);
	}

	@Test
	public void translateRoomTest2() {
		Room room = new Room(new Point3D(0, 0, 0), new ArrayList<Wall>(), new Item[4][4],
				new Floor(new Point3D(0, 0, 0)));
		room.translate(10, 0, 0);
		assertTrue(room.getPosition().getRealX() == 10);
		assertTrue(room.getPosition().getRealY() == 0);
		assertTrue(room.getPosition().getRealZ() == 0);
	}

	@Test
	public void translateRoomTest3() {
		Room room = new Room(new Point3D(0, 0, 0), new ArrayList<Wall>(), new Item[4][4],
				new Floor(new Point3D(0, 0, 0)));
		room.translate(0, 10, 0);
		assertTrue(room.getPosition().getRealX() == 0);
		assertTrue(room.getPosition().getRealY() == 10);
		assertTrue(room.getPosition().getRealZ() == 0);
	}

	@Test
	public void translateRoomTest4() {
		Room room = new Room(new Point3D(0, 0, 0), new ArrayList<Wall>(), new Item[4][4],
				new Floor(new Point3D(0, 0, 0)));
		room.translate(0, 0, 10);
		assertTrue(room.getPosition().getRealX() == 0);
		assertTrue(room.getPosition().getRealY() == 0);
		assertTrue(room.getPosition().getRealZ() == 10);
	}

	@Test
	public void translateWallTest1() {
		ArrayList<Wall> walls = new ArrayList<Wall>();
		walls.add(new NorthWall(new Point3D(0,0,8)));
		Room room = new Room(new Point3D(0, 0, 0), walls, new Item[4][4],
				new Floor(new Point3D(0, 0, 0)));
		walls = room.getWalls();
		assertTrue(room.getWalls().get(0).getPosition().getRealX() == 0);
		assertTrue(room.getWalls().get(0).getPosition().getRealY() == 0);
		assertTrue(room.getWalls().get(0).getPosition().getRealZ() == Renderer.HALF_ROOM);
	}

	@Test
	public void translateWallTest2() {
		ArrayList<Wall> walls = new ArrayList<Wall>();
		walls.add(new NorthWall(new Point3D(0,0,8)));
		Room room = new Room(new Point3D(0, 0, 0), walls, new Item[4][4],
				new Floor(new Point3D(0, 0, 0)));
		room.translate(10, 0, 0);
		walls = room.getWalls();
		assertTrue(room.getWalls().get(0).getPosition().getRealX() == 10);
		assertTrue(room.getWalls().get(0).getPosition().getRealY() == 0);
		assertTrue(room.getWalls().get(0).getPosition().getRealZ() == Renderer.HALF_ROOM);
	}

	@Test
	public void translateFloorTest1() {
		ArrayList<Wall> walls = new ArrayList<Wall>();
		walls.add(new NorthWall(new Point3D(0,0,8)));
		Room room = new Room(new Point3D(0, 0, 0), walls, new Item[4][4],
				new Floor(new Point3D(0, 0, 0)));
		assertTrue(room.getFloor().getPosition().getRealX() == 0);
		assertTrue(room.getFloor().getPosition().getRealY() == 0);
		assertTrue(room.getFloor().getPosition().getRealZ() == 0);
	}

	@Test
	public void translateFloorTest2() {
		ArrayList<Wall> walls = new ArrayList<Wall>();
		walls.add(new NorthWall(new Point3D(0,0,8)));
		Room room = new Room(new Point3D(0, 0, 0), walls, new Item[4][4],
				new Floor(new Point3D(0, 0, 0)));
		room.translate(10, 0, 0);
		assertTrue(room.getFloor().getPosition().getRealX() == 10);
		assertTrue(room.getFloor().getPosition().getRealY() == 0);
		assertTrue(room.getFloor().getPosition().getRealZ() == 0);
	}

	@Test public void translateMesh1() {
		Mesh mesh = Renderer.loadMesh(new File("Models/frontwall.txt"));
		assertTrue(mesh.getPosition().getRealX() == 0);
		assertTrue(mesh.getPosition().getRealY() == 0);
		assertTrue(mesh.getPosition().getRealZ() == 0);
	}

	@Test public void translateMesh2() {
		Mesh mesh = Renderer.loadMesh(new File("Models/frontwall.txt"));
		mesh.translate(10, 0, 0);
		assertTrue(mesh.getPosition().getRealX() == 10);
		assertTrue(mesh.getPosition().getRealY() == 0);
		assertTrue(mesh.getPosition().getRealZ() == 0);
	}

	@Test public void translateMesh3() {
		Mesh mesh = Renderer.loadMesh(new File("Models/frontwall.txt"));
		mesh.translate(0, 10, 0);
		assertTrue(mesh.getPosition().getRealX() == 0);
		assertTrue(mesh.getPosition().getRealY() == 10);
		assertTrue(mesh.getPosition().getRealZ() == 0);
	}

	@Test public void translateMesh4() {
		Mesh mesh = Renderer.loadMesh(new File("Models/frontwall.txt"));
		mesh.translate(0, 0, 10);
		assertTrue(mesh.getPosition().getRealX() == 0);
		assertTrue(mesh.getPosition().getRealY() == 0);
		assertTrue(mesh.getPosition().getRealZ() == 10);
	}
}
