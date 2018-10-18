/**
 * @author Pai Zhou
 * id: 300335146
 *
 */
package MapEditor;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class tests {
	@Test
	/**
	 * Just check the room items.
	 */
	public void test1() {
		Room room =new Room(50,50,10,10);
		room.downdoor();
		room.leftwall();
		room.topwall();
		room.haveKey(1,1);
		room.treasure11(2,1);
		assertEquals(true,room.downDoor);
		assertEquals(true,room.leftWall);
		assertEquals(true,room.topWall);
		assertEquals(true,room.key);
		assertEquals(true,room.treasure1);
		assertEquals(false,room.leftDoor);
		assertEquals(false,room.downWall);
		assertEquals(false,room.light); 
		assertEquals(false,room.leftDoor);
		assertEquals(false,room.topDoor);
		assertEquals(false,room.rightDoor);
		assertEquals(false,room.rightWall);
		assertEquals(false,room.magic1);
	}
	
	@Test
	/**
	 * Just check the rooms exist
	 */
	public void test2() {
		Room room=new Room(50,50,10,10);
		Point p=new Point(53,53);
		Point p2= new Point(58,78);
		assertEquals(true,room.contains(p));
		assertEquals(false,room.contains(p2));		
	}
	
	@Test
	/**
	 * Just check the door is exist and can not create wall at the same place
	 */
	public void test3() {
		Room room=new Room(50,50,10,10);
		room.leftdoor();
		room.leftwall();
		assertEquals(false,room.leftWall);
	
	}
	@Test
	/**
	 * Just check the key is exist when click key button;
	 */
	public void test4() {
		GUI a =new GUI();
		a.initialise();
		a.rightroom.doClick();
		a.key.doClick();
		assertEquals(true,a.rooms.get(0).key);
	
	}
	
	@Test
	/**
	 * Just check the room size;
	 */
	public void test5() {
		GUI a =new GUI();
		a.initialise();
		a.rightroom.doClick();
		a.rightroom.doClick();
		assertEquals(2,a.rooms.size());
	
	}
	
	@Test
	/**
	 * Just check the room size when there is a room and can not build room again;
	 */
	public void test6() {
		GUI a =new GUI();
		a.initialise();
		a.rightroom.doClick();
		a.rightroom.doClick();
		a.leftroom.doClick();
		assertEquals(2,a.rooms.size());
		a.toproom.doClick();
		a.downdoor.doClick();
		assertEquals(3,a.rooms.size());
	}
	
	@Test
	/**
	 * Just check the room item
	 */
	public void test7() {
		GUI a =new GUI();
		a.initialise();
		a.rightroom.doClick();
		a.leftdoor.doClick();
		a.rightroom.doClick();
		a.key.doClick();
		assertEquals(true,a.rooms.get(0).leftDoor);
		assertEquals(true,a.rooms.get(1).key);
		a.toproom.doClick();
		a.treasure.doClick();
		assertEquals(true,a.rooms.get(2).treasure1);
	}
	
	@Test
	/**
	 * Just check the room item for magic and wall
	 */
	public void test8() {
		GUI a =new GUI();
		a.initialise();
		a.rightroom.doClick();
		a.magic.doClick();
		a.rightroom.doClick();
		a.wall.doClick();
		assertEquals(true,a.rooms.get(0).magic1);
		assertEquals(true,a.rooms.get(1).leftWall);
		a.toproom.doClick();
		a.treasure.doClick();
		assertEquals(true,a.rooms.get(2).treasure1);
	}
	
	@Test
	/**
	 * Just check the room item
	 */
	public void test9() {
		GUI a =new GUI();
		a.initialise();
		a.rightroom.doClick();
		a.leftdoor.doClick();
		a.rightdoor.doClick();
		a.topdoor.doClick();
		a.downdoor.doClick();
		a.leftroom.doClick();
		a.wall.doClick();
		a.rightwall.doClick();
		a.topwall.doClick();
		a.downwall.doClick();
		assertEquals(true,a.rooms.get(0).leftDoor);
		assertEquals(true,a.rooms.get(0).rightDoor);
		assertEquals(true,a.rooms.get(0).topDoor);
		assertEquals(true,a.rooms.get(0).downDoor);
		assertEquals(true,a.rooms.get(1).leftWall);
		assertEquals(true,a.rooms.get(1).rightWall);
		assertEquals(true,a.rooms.get(1).topWall);
		assertEquals(true,a.rooms.get(1).downWall);
		a.toproom.doClick();
		a.treasure.doClick();
		assertEquals(true,a.rooms.get(2).treasure1);
	}
	
	@Test
	/**
	 * Just check wheather can build rooms and clear button
	 */
	public void test10() {
		GUI a =new GUI();
		a.initialise();
		a.rightroom.doClick();
		a.toproom.doClick();
		a.downroom.doClick();
		assertEquals(a.x1,a.rooms.get(1).x);
		a.clear.doClick();
		assertEquals(a.rooms.size(),0);
		a.leftroom.doClick();
		a.downdoor.doClick();
		a.toproom.doClick();
		assertEquals(a.x1,a.rooms.get(1).x);
		
	}
	@Test
	/**
	 * Just check wheather can build rooms and clear button
	 */
	public void test11() {
		GUI a =new GUI();
		a.initialise();
		a.leftroom.doClick();
		a.leftroom.doClick();
		a.rightroom.doClick();
		assertEquals(a.x1,a.rooms.get(1).x);
		
	}
	
	@Test
	/**
	 * Just check wheather can build rooms and clear button
	 */
	public void test12() {
		GUI a =new GUI();
		a.initialise();
		a.leftroom.doClick();
		a.downroom.doClick();
		a.downroom.doClick();
		a.toproom.doClick();
		assertEquals(a.x1,a.rooms.get(2).x);
		
	}
}
