package MapEditor;

import java.awt.Color;
import java.awt.Graphics;

public class Room {
	int x;
	int y;
	int width;
	int height;
	boolean leftWall=false;
	boolean rightWall=false;
	boolean topWall=false;
	boolean downWall=false;
	boolean leftDoor=false;
	boolean rightDoor=false;
	boolean topDoor=false;
	boolean downDoor=false;
	boolean key=false;
	boolean magic1=false;
	boolean treasure1=false;
	boolean light=false;
	
	public Room(int x,int y,int width,int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	/**
	 * Getter method for getting room name
	 * @return room name
	 */
	public String getName() {
		return null;
	}
	
	/**
	 * Getting method for getting description of room
	 * @return room description
	 */
	public String getDescription() {
		return null;
	}
	public void leftwall() {
		leftWall=true;
	}
	public void rightwall() {
		rightWall=true;
	}
	public void topwall() {
		topWall=true;
	}
	public void downwall() {
		downWall=true;
	}
	public void leftdoor() {
		leftDoor=true;
	}
	public void light() {
		light=true;
	}
	public void rightdoor() {
		rightDoor=true;
	}
	public void topdoor() {
		topDoor=true;
	}
	public void downdoor() {
		downDoor=true;
	}
	public void haveKey() {
		key=true;
	}
	public void magic11() {
		magic1=true;
	}
	public void treasure11() {
		treasure1=true;
	}
	public void message() {
		System.out.println("This room elements:");
		if(leftWall) {
			System.out.println("left wall");
		}
		if(rightWall) {
			System.out.println("right wall");
		}
		if(topWall) {
			System.out.println("top wall");
		}
		if(downWall) {
			System.out.println("down wall");
		}
		if(leftDoor) {
			System.out.println("left door");
		}
		if(rightDoor) {
			System.out.println("right door");
		}
		if(topDoor) {
			System.out.println("top door");
		}
		if(downDoor) {
			System.out.println("down door");
		}
		if(key) {
			System.out.println("key");
		}
		if(magic1) {
			System.out.println("magic");
		}
		if(treasure1) {
			System.out.println("treasure");
		}
	}
	
	/**
	 * Get room image
	 * @return room image
	 */
	public String getImage() {
		return null;
	}
	
	public void draw(Graphics g) {
		if(light) {
			g.setColor(Color.white);
			g.fillRect(x, y, width, height);
			g.fillRect(x+width, y, width, height);
			g.fillRect(x, y+height, width, height);
			g.fillRect(x+width, y+height, width, height);
		}
		else {
			g.setColor(Color.white);
			g.drawRect(x, y, width, height);
			g.drawRect(x+width, y, width, height);
			g.drawRect(x, y+height, width, height);
			g.drawRect(x+width, y+height, width, height);
		}
		
	}
	public boolean contains(Point p) {
		return p.x>=x&&p.y>=y&&p.x<=x+width+width&&p.y<=y+height+height;
	}
	
}
