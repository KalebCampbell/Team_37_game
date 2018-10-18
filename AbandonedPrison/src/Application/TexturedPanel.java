package Application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Extends JPanel to make colored JPanels.
 *
 * @author liam
 *
 */
public class TexturedPanel extends JPanel {

	private ArrayList<JLabel> items = new ArrayList<JLabel>();
	private Image image = Toolkit.getDefaultToolkit().createImage("src/Application/inventory.png");

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);

	}

	public void addItem(JLabel item) {
		this.add(item);
		items.add(item);
	}

	public void removeItem(int i) {
		items.remove(i);
	}

	public void select(int i) {
		for(JLabel j : items) {
			j.setBorder(null);
		}
		Border border = BorderFactory.createLineBorder(Color.RED);
		items.get(i).setBorder(border);;
	}

	public JLabel getItem(int i) {
		return items.get(i);
	}

	public List<JLabel> getItems(){
		return items;
	}

}
