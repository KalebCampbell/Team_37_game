package Application;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * Extends JPanel to make colored JPanels.
 * 
 * @author liam
 *
 */
public class TexturedPanel extends JPanel {
	
	private Image image = Toolkit.getDefaultToolkit().createImage("src/Application/inventory.png");
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		
	}
	
}
