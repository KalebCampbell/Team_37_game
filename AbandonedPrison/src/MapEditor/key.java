package MapEditor;

import java.awt.Color;
import java.awt.Graphics;

public class key {
	int x;
	int y;
	public key(int x, int y) {
		this.x=x;
		this.y=y;
	}
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
	
	/**
	 * Get room image
	 * @return room image
	 */
	public String getImage() {
		return null;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawLine(x+2, y+2, x+5, y+5);
	}
}
