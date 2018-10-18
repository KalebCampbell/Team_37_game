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

	public void removeItem(int i) {
		items.remove(i);
	}

	public void select(int i) {
		System.out.println("yofusssk");
		for(ItemLabel j : items) {
			j.label.setBorder(null);
		}
		Border border = BorderFactory.createLineBorder(Color.RED);
		System.out.println(items.get(i));
		items.get(i).label.setBorder(border);
		items.get(i).label.repaint();
	}

	public ItemLabel getItem(int i) {
		return items.get(i);
	}

	public List<ItemLabel> getItems(){
		return items;
	}

}
