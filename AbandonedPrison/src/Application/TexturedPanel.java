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

import GameWorld.Item;

/**
 * Extends JPanel to make colored JPanels.
 *
 * @author liam
 *
 */
public class TexturedPanel extends JPanel {

	private ArrayList<ItemLabel> items = new ArrayList<ItemLabel>();
	private Image image = Toolkit.getDefaultToolkit().createImage("src/Application/inventory.png");

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);

	}

	public void addItem(ItemLabel item) {
		this.add(item.label);
		items.add(item);
		item.label.setVisible(true);
	}

	public void removeItem(Item i ) {
		items.remove(i);
	}

	public void clear() {
		this.items = new ArrayList<ItemLabel>();
	}

	public void select(int i) {
		System.out.println("yofusssk");
		for(ItemLabel j : items) {
			j.label.setBorder(null);
		}

	}

	public ItemLabel getItem(int i) {
		return items.get(i);
	}

	public List<ItemLabel> getItems(){
		return items;
	}

}
