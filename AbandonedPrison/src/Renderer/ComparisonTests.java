package Renderer;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.PriorityQueue;

import org.junit.Test;

public class ComparisonTests {
	@Test
	public void roomComparisonTest1() {
		Renderer renderer = new Renderer();
		Room room1 = new Room(new Point3D(0, 0, 10), new ArrayList<Wall>(), new Item[4][4],
				new Floor(renderer.getMeshes().get("floor"), new Point3D(0, 0, 0)));
		Room room2 = new Room(new Point3D(0, 0, 0), new ArrayList<Wall>(), new Item[4][4],
				new Floor(renderer.getMeshes().get("floor"), new Point3D(0, 0, 0)));
		PriorityQueue<Room> rooms = new PriorityQueue<Room>();
		rooms.add(room1);
		rooms.add(room2);
		assertTrue(rooms.poll().equals(room1));
	}

	@Test
	public void roomComparisonTest2() {
		Renderer renderer = new Renderer();
		Room room1 = new Room(new Point3D(0, 0, 0), new ArrayList<Wall>(), new Item[4][4],
				new Floor(renderer.getMeshes().get("floor"), new Point3D(0, 0, 0)));
		Room room2 = new Room(new Point3D(0, 0, 10), new ArrayList<Wall>(), new Item[4][4],
				new Floor(renderer.getMeshes().get("floor"), new Point3D(0, 0, 0)));
		PriorityQueue<Room> rooms = new PriorityQueue<Room>();
		rooms.add(room1);
		rooms.add(room2);
		assertTrue(rooms.poll().equals(room2));
	}

	@Test
	public void roomComparisonTest3() {
		Renderer renderer = new Renderer();
		Room room1 = new Room(new Point3D(0, 0, 0), new ArrayList<Wall>(), new Item[4][4],
				new Floor(renderer.getMeshes().get("floor"), new Point3D(0, 0, 0)));
		Room room2 = new Room(new Point3D(10, 0, 0), new ArrayList<Wall>(), new Item[4][4],
				new Floor(renderer.getMeshes().get("floor"), new Point3D(0, 0, 0)));
		PriorityQueue<Room> rooms = new PriorityQueue<Room>();
		rooms.add(room1);
		rooms.add(room2);
		assertTrue(rooms.poll().equals(room1));
	}

	@Test
	public void roomComparisonTest4() {
		Renderer renderer = new Renderer();
		Room room1 = new Room(new Point3D(10, 0, 0), new ArrayList<Wall>(), new Item[4][4],
				new Floor(renderer.getMeshes().get("floor"), new Point3D(0, 0, 0)));
		Room room2 = new Room(new Point3D(0, 0, 0), new ArrayList<Wall>(), new Item[4][4],
				new Floor(renderer.getMeshes().get("floor"), new Point3D(0, 0, 0)));
		PriorityQueue<Room> rooms = new PriorityQueue<Room>();
		rooms.add(room1);
		rooms.add(room2);
		assertTrue(rooms.poll().equals(room2));
	}

	@Test
	public void polyComparisonTest1() {
		Polygon3D poly1 = new Polygon3D(new float[] { 2, -1, 0 }, new float[] { 1, 1, 0 }, new float[] { 1, 1, 1 }, 3);
		Polygon3D poly2 = new Polygon3D(new float[] { 2, -1, 0 }, new float[] { 1, 1, 0 }, new float[] { 2, 2, 2 }, 3);
		PriorityQueue<Polygon3D> polys = new PriorityQueue<Polygon3D>();
		polys.add(poly1);
		polys.add(poly2);
		assertTrue(polys.poll().equals(poly2));
	}

	@Test
	public void polyComparisonTest2() {
		Polygon3D poly1 = new Polygon3D(new float[] { 2, -1, 0 }, new float[] { 1, 1, 0 }, new float[] { 2, 2, 2 }, 3);
		Polygon3D poly2 = new Polygon3D(new float[] { 2, -1, 0 }, new float[] { 1, 1, 0 }, new float[] { 1, 1, 1 }, 3);
		PriorityQueue<Polygon3D> polys = new PriorityQueue<Polygon3D>();
		polys.add(poly1);
		polys.add(poly2);
		assertTrue(polys.poll().equals(poly1));
	}

	@Test
	public void polyComparisonTest3() {
		Polygon3D poly1 = new Polygon3D(new float[] { 2, -1, 0 }, new float[] { 1, 1, 0 }, new float[] { 1, 1, 1 }, 3);
		Polygon3D poly2 = new Polygon3D(new float[] { 2, -1, 0 }, new float[] { 2, 2, 1 }, new float[] { 1, 1, 1 }, 3);
		PriorityQueue<Polygon3D> polys = new PriorityQueue<Polygon3D>();
		polys.add(poly1);
		polys.add(poly2);
		assertTrue(polys.poll().equals(poly2));
	}

	@Test
	public void polyComparisonTest4() {
		Polygon3D poly1 = new Polygon3D(new float[] { 2, -1, 0 }, new float[] { 2, 2, 1 }, new float[] { 1, 1, 1 }, 3);
		Polygon3D poly2 = new Polygon3D(new float[] { 2, -1, 0 }, new float[] { 1, 1, 0 }, new float[] { 1, 1, 1 }, 3);
		PriorityQueue<Polygon3D> polys = new PriorityQueue<Polygon3D>();
		polys.add(poly1);
		polys.add(poly2);
		assertTrue(polys.poll().equals(poly1));
	}

	@Test
	public void itemComparionTest1() {
		Renderer renderer = new Renderer();
		Key key1 = new Key(renderer.getMeshes().get("key"), new Point3D(0, 0, 10));
		Key key2 = new Key(renderer.getMeshes().get("key"), new Point3D(0, 0, 0));
		PriorityQueue<Item> items = new PriorityQueue<Item>();
		items.add(key1);
		items.add(key2);
		assertTrue(items.poll().equals(key1));
	}

	@Test
	public void itemComparionTest2() {
		Renderer renderer = new Renderer();
		Key key1 = new Key(renderer.getMeshes().get("key"), new Point3D(0, 0, 0));
		Key key2 = new Key(renderer.getMeshes().get("key"), new Point3D(0, 0, 10));
		PriorityQueue<Item> items = new PriorityQueue<Item>();
		items.add(key1);
		items.add(key2);
		assertTrue(items.poll().equals(key2));
	}

	@Test
	public void itemComparionTest3() {
		Renderer renderer = new Renderer();
		Key key1 = new Key(renderer.getMeshes().get("key"), new Point3D(10, 0, 0));
		Key key2 = new Key(renderer.getMeshes().get("key"), new Point3D(0, 0, 0));
		PriorityQueue<Item> items = new PriorityQueue<Item>();
		items.add(key1);
		items.add(key2);
		assertTrue(items.poll().equals(key1));
	}
	
	@Test
	public void itemComparionTest4() {
		Renderer renderer = new Renderer();
		Key key1 = new Key(renderer.getMeshes().get("key"), new Point3D(0, 0, 0));
		Key key2 = new Key(renderer.getMeshes().get("key"), new Point3D(10, 0, 0));
		PriorityQueue<Item> items = new PriorityQueue<Item>();
		items.add(key1);
		items.add(key2);
		assertTrue(items.poll().equals(key2));
	}
}
