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
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x+12, y+12, 5, 5);
	}
}
