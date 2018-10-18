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
	int x1;
	int y1;

	public magic(int x, int y, int x1, int y1) {
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;

	}

	/**
	 * draw the magic
	 */
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x + 1 + x1 * 15, y + 1 + y1 * 15, 7, 7);
	}
}
