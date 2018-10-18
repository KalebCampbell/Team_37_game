package Application;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

import GameWorld.Item;

/**
 * Object to represent a slot in the inventory panel
 * 
 * @author royalliam
 *
 */
public class ItemLabel {

	public JLabel label;
	public Item item;

	public ItemLabel(ImageIcon image, Item item) {
		this.label = new JLabel();
		label.setIcon(image);
		this.item = item;
	}
}
