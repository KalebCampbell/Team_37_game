package Application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JMenuBar;

/**
 * Extends JMenuBar to customize the mennu
 * 
 * @author liam
 *
 */
public class ColoredMenu extends JMenuBar {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(new Color(58, 58, 58));
		g2D.fillRect(0, 0, getWidth(), getHeight());
	}

}
