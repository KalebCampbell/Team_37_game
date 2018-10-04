package Application;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * @author Liam Royal
 *	
 *Represents the application window.
 */

public class Window extends JFrame {
	
	/**
	 * Icons for movement buttons.
	 */
	ImageIcon upA = new ImageIcon("up.png");
	ImageIcon downA = new ImageIcon("down.png");
	ImageIcon leftA = new ImageIcon("left.png");
	ImageIcon rightA = new ImageIcon("right.png");
	
	/**
	 * Icons for items.
	 */
	ImageIcon key = new ImageIcon("key.png");

	
	/**
	 * Width of the canvas.
	 */
	public static final int CANVAS_WIDTH = 600;
	/**
	 * Height of the canvas.
	 */
	public static final int CANVAS_HEIGHT = 600;
	
	private Color background = new Color(58, 58, 58);
	
	//reference to a renderer object
	
	//could need a reference to the game in the constructor
	public Window(int width, int height, String title) {
		
		//initialize frame
		Dimension fs = new Dimension(width, height);
		this.setTitle(title);
		this.setPreferredSize(fs);
		this.setMaximumSize(fs);
		this.setMinimumSize(fs);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		//use this to change the background later on
		Container c = this.getContentPane();
		c.setBackground(background);
		
	//-------COMPONENTS--------
		
		//rendering window
		JPanel canvas = new JPanel();
		//change size of rendering window here
		canvas.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.setBackground(Color.WHITE);
		//*** actual code for renderer ***
//		JComponent canvas = new JComponent() {
//		protected void paintComponent(Graphics g) {
//				render(g);
//			}
//		};
		
		this.add(canvas);
		
		//menu
		ColoredMenu menubar = new ColoredMenu();
		//tabs
		JMenu mainMenu = new JMenu("File");
		JMenu help = new JMenu("Help");
		help.setOpaque(true);
		help.setForeground(Color.BLACK);
		//menu items
		JMenuItem exit, save, load, newGame;
		save = mainMenu.add("Save");
		load = mainMenu.add("Load");
		newGame = mainMenu.add("New game");
		exit = mainMenu.add("Exit");
		//add to menu
		menubar.add(mainMenu);
		menubar.add(help);
		
		this.setJMenuBar(menubar);
		
		//inventory panel
		GridLayout grid = new GridLayout(6,1);
		grid.setHgap(10);
		TexturedPanel inven = new TexturedPanel();
		inven.setBounds(610, 10, 80, 590);
		inven.setBackground(Color.LIGHT_GRAY);
		Border blackline = BorderFactory.createLineBorder(Color.WHITE);
		TitledBorder border = BorderFactory.createTitledBorder(blackline, "Inventory");
		border.setTitleColor(Color.BLACK);
		border.setTitleJustification(TitledBorder.CENTER);
		inven.setBorder(border);
		//inventory slots
		ArrayList<JLabel> slots = new ArrayList<JLabel>();
			
			for(int i = 0; i < 8; i++) {
					slots.add(new JLabel());
				}
			
			for(JLabel j : slots) {
				j.setIcon(key);
				inven.add(j);
			}
		
		this.add(inven);
		
		//movement buttons
		JPanel move = new JPanel();
		move.setBackground(background);
		move.setLayout(new GridLayout(2,3));
		move.setBounds(10, 610, width/3, 100);	
		//invisible buttons to arrange the grid 
		JButton e1 = new JButton(); e1.setVisible(false);
		JButton up = new JButton(upA);
		JButton e2 = new JButton(); e2.setVisible(false);
		JButton left = new JButton(leftA);
		JButton down = new JButton(downA);
		JButton right = new JButton(rightA);
		move.add(e1); move.add(up); move.add(e2);
		move.add(left);	move.add(down); move.add(right);
		
		this.add(move);
		
		//text output area
		JTextArea output = new JTextArea(30,20);
		output.setBackground(background);
		output.setForeground(Color.white);
		Border etched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder outputBorder = BorderFactory.createTitledBorder(etched, "Game output");
		outputBorder.setTitleColor(Color.white);
		outputBorder.setTitleJustification(TitledBorder.CENTER);
		output.setBorder(outputBorder);
		output.setBounds(20+width/3, 608, 440, 102);
		output.setText(" You picked up a key...");
		this.add(output);
		
		
	//-------------------------
		
		this.setVisible(true);
		this.pack();
	}
		
	

}
