/**
 * @author Pai Zhou
 * id: 300335146
 *
 */
package MapEditor;

import java.awt.Color;
import java.awt.Graphics;

public class key {
	int x;
	int y;
	int x1;
	int y1;
	public key(int x, int y,int x1,int y1) {
		this.x=x;
		this.y=y;
		this.x1=x1;
		this.y1=y1;
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

	/**
	 *redraw the key
	 */
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawLine(x+1+x1*15, y+1+y1*15, x+10+x1*15, y+10+y1*15);
	}
}
