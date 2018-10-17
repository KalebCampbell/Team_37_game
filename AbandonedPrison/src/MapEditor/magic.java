/**
 * @author Pai Zhou
 * id: 300335146
 *
 */
package MapEditor;

import java.awt.Color;
import java.awt.Graphics;

public class magic {
	int x;
	int y;
	public magic(int x, int y) {
		this.x=x;
		this.y=y;
	}
	/**
	 *draw the magic
	 */
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x+32, y+32, 15, 15);
	}
}
