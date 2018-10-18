package Renderer;

import static org.junit.Assert.assertTrue;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.junit.Test;

import GameWorld.Location;

public class RendererTests {
	@Test
	public void moveBackwardTest() {
		Renderer renderer = new Renderer();
		Room room = new Room(new Point3D(0, 0, 0), new ArrayList<AbstractWall>(), new AbstractItem[4][4],
				new Floor(new Point3D(0, 0, 0)));
		renderer.addRoom(room);
		renderer.moveBackward();
		assertTrue(renderer.getRooms().get(0).getPosition().getRealZ() == Renderer.WHOLE_ROOM);
	}

	@Test
	public void moveForwardTest() {
		Renderer renderer = new Renderer();
		Room room = new Room(new Point3D(0, 0, 0), new ArrayList<AbstractWall>(), new AbstractItem[4][4],
				new Floor(new Point3D(0, 0, 0)));
		renderer.addRoom(room);
		renderer.moveForward();
		assertTrue(renderer.getRooms().get(0).getPosition().getRealZ() == -Renderer.WHOLE_ROOM);
	}

	@Test
	public void rotateLeftTest() {
		Renderer renderer = new Renderer();
		Room room = new Room(new Point3D(0, 0, 0), new ArrayList<AbstractWall>(), new AbstractItem[4][4],
				new Floor(new Point3D(0, 0, 0)));
		renderer.addRoom(room);
		renderer.moveForward();
		renderer.rotateLeft();
		assertTrue(renderer.getRooms().get(0).getPosition().getRealX() == -Renderer.WHOLE_ROOM);
	}

	@Test
	public void rotateRightTest() {
		Renderer renderer = new Renderer();
		Room room = new Room(new Point3D(0, 0, 0), new ArrayList<AbstractWall>(), new AbstractItem[4][4],
				new Floor(new Point3D(0, 0, 0)));
		renderer.addRoom(room);
		renderer.moveForward();
		renderer.rotateRight();
		assertTrue(renderer.getRooms().get(0).getPosition().getRealX() == Renderer.WHOLE_ROOM);
	}

	@Test
	public void orderingRoomsTest() {
		Renderer renderer = new Renderer();
		Room room1 = new Room(new Point3D(0, 0, 10), new ArrayList<AbstractWall>(), new AbstractItem[4][4],
				new Floor(new Point3D(0, 0, 0)));
		Room room2 = new Room(new Point3D(0, 0, 0), new ArrayList<AbstractWall>(), new AbstractItem[4][4],
				new Floor(new Point3D(0, 0, 0)));
		renderer.addRoom(room1);
		renderer.addRoom(room2);
		PriorityQueue<Room> rooms = renderer.orderRooms();
		assertTrue(rooms.poll().equals(room1));
	}

	@Test
	public void createRoomTest() {
		Application.Controller controller = new Application.Controller(new Application.Window(700, 760, "Test"));
		assertTrue(controller.getWindow().getRenderer().getRooms().get(0) != null);
	}
}
