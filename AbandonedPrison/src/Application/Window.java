package Application;
import Renderer.Renderer;
import Renderer.Room;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
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

public class Window {
	
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
	ImageIcon blueprints = new ImageIcon("blueprints.png");
	ImageIcon crouch = new ImageIcon("crouch-icon.png");
	ImageIcon stand = new ImageIcon("standing-symbol.png");
	ImageIcon north = new ImageIcon("compassNorth.png");
	ImageIcon south = new ImageIcon("compassSouth.png");
	ImageIcon east = new ImageIcon("compassEast.png");
	ImageIcon west = new ImageIcon("compassWest.png");

	
	/**
	 * Width of the canvas.
	 */
	public static final int CANVAS_WIDTH = 600;
	/**
	 * Height of the canvas.
	 */
	public static final int CANVAS_HEIGHT = 600;
	/**
	 * Canvas Dimension.
	 */
	public static final Dimension CANVAS_SIZE = new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
	
	
	
	private Color background = new Color(58, 58, 58);
	
	//reference to a renderer object
	//made public so gui can handle key input
	public JFrame frame;
	public Renderer renderer;
	public JComponent canvas;
	public JLabel status;
	public JLabel compass;
	boolean crouching = false;
	boolean standing = true;
	
	//could need a reference to the game in the constructor
	public Window(int width, int height, String title) {
		
		//initialize frame
		Dimension fs = new Dimension(width, height);
		frame = new JFrame();
		frame.setTitle(title);
		frame.setPreferredSize(fs);
		frame.setMaximumSize(fs);
		frame.setMinimumSize(fs);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setFocusable(true);
		frame.setLayout(null);
		
		
		//use this to change the background later on
		Container c = frame.getContentPane();
		c.setBackground(background);
		
	//-------COMPONENTS--------
		
		//rendering window
		this.renderer = new Renderer();
		this.canvas = new JComponent() {
			protected void paintComponent(Graphics g) {
				renderer.render(g);
			}
		};
		Dimension canvasBounds = new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.setPreferredSize(canvasBounds);
		canvas.setMinimumSize(canvasBounds);
		canvas.setMaximumSize(canvasBounds);
		compass = new JLabel();
		compass.setVisible(true);
		compass.setBounds(445,5,150,150);
		compass.setIcon(north);
		status = new JLabel();
		status.setVisible(true);
		status.setBounds(10,530, 60, 60);
		status.setIcon(stand);
		canvas.add(compass);
		canvas.add(status);
		
		
		frame.add(canvas);
		
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
		
		frame.setJMenuBar(menubar);
		
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
		
			for(int i = 0; i < 7; i++) {
					slots.add(new JLabel());
				}
			
			for(JLabel j : slots) {
				j.setIcon(blueprints);
				inven.add(j);
			}
			
		frame.add(inven);
		
		//movement buttons
		JPanel move = new JPanel();
		move.setBackground(background);
		move.setLayout(new GridLayout(2,3));
		move.setBounds(10, 610, width/3, 100);	
		//invisible buttons to arrange the grid 
		JButton e1 = new JButton("Pick up"); e1.setVisible(true);
		JButton up = new JButton(upA);
		JButton e2 = new JButton("Use"); e2.setVisible(true);
		JButton left = new JButton(leftA);
		JButton down = new JButton(downA);
		JButton right = new JButton(rightA);
		move.add(e1); move.add(up); move.add(e2);
		move.add(left);	move.add(down); move.add(right);
		
		frame.add(move);
		
		//text output area
		JTextArea output = new JTextArea(30,20);
		output.setKeymap(null);
		output.setBackground(background);
		output.setForeground(Color.white);
		Border etched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder outputBorder = BorderFactory.createTitledBorder(etched, "Game output");
		outputBorder.setTitleColor(Color.white);
		outputBorder.setTitleJustification(TitledBorder.CENTER);
		output.setBorder(outputBorder);
		output.setBounds(20+width/3, 608, 440, 102);
		output.setText(" You picked up a key...");
		frame.add(output);
		
		
	//-------------------------
		
		frame.setVisible(true);
		frame.pack();
	}
	
		
	
}
