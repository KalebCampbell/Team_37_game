package Application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ListPanel extends JPanel {
	
	private JComboBox<String> list;
	
	private Image texture = Toolkit.getDefaultToolkit().createImage("src/Application/panelTexture.png");
	
	ListPanel(){
		
		Border blackline = BorderFactory.createLineBorder(Color.WHITE);
		TitledBorder border = BorderFactory.createTitledBorder(blackline, "Pick Up item");
		border.setTitleColor(Color.WHITE);
		border.setTitleJustification(TitledBorder.CENTER);
		this.setBorder(border);
		this.list = new JComboBox<String>();
		this.add(list);
	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(texture, 0 ,0, this);
	}
	
	public void addItem(String item) {
		this.list.addItem(item);
	}

}
