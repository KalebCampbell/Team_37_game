package MapEditor;

import java.awt.Color;
import java.awt.Graphics;

public class Treasure {
	int x;
	int y;
	public Treasure(int x, int y) {
		this.x=x;
		this.y=y;
	}
	//draw the Treasures
	public void draw(Graphics g) {
		g.setColor(Color.gray);
		g.fillOval(x+12, y, 5, 5);
	}
}
