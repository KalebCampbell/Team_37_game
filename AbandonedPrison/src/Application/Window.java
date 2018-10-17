package Application;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Renderer.Renderer;

/**
 * @author Liam Royal
 *	
 *Builds GUI with SWING components, represents the application Window.
 */

public class Window {
	
	/**
	 * Icons for movement buttons.
	 */
	ImageIcon upA = new ImageIcon("src/Application/up.png");
	ImageIcon downA = new ImageIcon("src/Application/down.png");
	ImageIcon leftA = new ImageIcon("src/Application/left.png");
	ImageIcon rightA = new ImageIcon("src/Application/right.png");
	
	/**
	 * Icons for other application components.
	 */
	ImageIcon key = new ImageIcon("src/Application/key.png");
	ImageIcon blueprints = new ImageIcon("src/Application/blueprints.png");
	ImageIcon crouch = new ImageIcon("src/Application/crouch-icon.png");
	ImageIcon stand = new ImageIcon("src/Application/standing-symbol.png");
	ImageIcon north = new ImageIcon("src/Application/compassNorth.png");
	ImageIcon south = new ImageIcon("src/Application/compassSouth.png");
	ImageIcon east = new ImageIcon("src/Application/compassEast.png");
	ImageIcon west = new ImageIcon("src/Application/compassWest.png");
	ImageIcon texture = new ImageIcon("src/Application/inventory.png");

	
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
	
	/**
	 * Grey background color.
	 */
	private Color background = new Color(58, 58, 58);
	
	/**
	 * Enum to represent direction facing.
	 */
	public enum Direction{ 
		NORTH,
		EAST, 
		SOUTH,
		WEST
	}
	
	//Components to be accessed by controller.
	private JFileChooser fileChooser;
	private JFrame frame;
	private Renderer renderer;
	private JComponent canvas;
	private JLabel status;
	private JLabel compass;
	private JTextArea output;
	private JButton pickup;
	private JButton use;
	private ListPanel itemList;
	private TexturedPanel inven;
	private JMenuItem exit;
	private JMenuItem save;
	private JMenuItem load;
	private JMenuItem newGame;
	public boolean crouching = false;
	public boolean standing = true;
	protected Direction facing = Direction.NORTH;
	
	
	/**
	 * Constructor for a new Window.
	 * 
	 * @param width - width of the frame
	 * @param height - height of the frame
	 * @param title - title of the frame
	 */
	
	@SuppressWarnings("serial")
	public Window(int width, int height, String title) {
		
		//Setup JFrame.
		Dimension fs = new Dimension(width, height);
		this.frame = new JFrame();
			frame.setTitle(title);
			frame.setPreferredSize(fs);
			frame.setMaximumSize(fs);
			frame.setMinimumSize(fs);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setFocusable(true);
			frame.setLayout(null);
			//set the background color of frame
			Container c = frame.getContentPane();
			c.setBackground(background);
		
		
	//-------COMPONENTS--------
			
		//--File chooser testing.--
		
		//--Rendering canvas.--
		this.renderer = new Renderer();
		this.canvas = new JComponent() {
			protected void paintComponent(Graphics g) {
				renderer.render(g);
			}
		};
		//Setup canvas.
		canvas.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.setPreferredSize(CANVAS_SIZE);
		canvas.setMinimumSize(CANVAS_SIZE);
		canvas.setMaximumSize(CANVAS_SIZE);
		this.compass = new JLabel();
			compass.setVisible(true);
			compass.setBounds(445,5,150,150);
			compass.setIcon(north); //loaded facing north
		this.status = new JLabel();
			status.setVisible(true);
			status.setBounds(10,530, 60, 60);
			status.setIcon(stand);
		this.itemList = new ListPanel();
			itemList.setBounds(250,250,100,100);
			itemList.setVisible(false);
			itemList.addItem("Key");
			itemList.addItem("Box");
		
		canvas.add(itemList);
		canvas.add(compass);
		canvas.add(status);
		
		frame.add(canvas);
		
		//--Menu bar.--
		ColoredMenu menubar = new ColoredMenu();
			//Menu tabs.
			JMenu mainMenu = new JMenu("File");
			JMenu help = new JMenu("Help");
		//Menu items.
		this.save = mainMenu.add("Save");
		this.load = mainMenu.add("Load");
		this.newGame = mainMenu.add("New game");
		this.exit = mainMenu.add("Exit");
		menubar.add(mainMenu);
		menubar.add(help);
		
		frame.setJMenuBar(menubar);
		
		//--Inventory panel.--
		GridLayout grid = new GridLayout(6,1);
		grid.setHgap(10);
		inven = new TexturedPanel();
		inven.setBounds(610, 10, 80, 590);
		inven.setBackground(Color.LIGHT_GRAY);
		Border blackline = BorderFactory.createLineBorder(Color.WHITE);
		TitledBorder border = BorderFactory.createTitledBorder(blackline, "Inventory");
		border.setTitleColor(Color.BLACK);
		border.setTitleJustification(TitledBorder.CENTER);
		inven.setBorder(border);
		//Inventory slots.
		ArrayList<JLabel> slots = new ArrayList<JLabel>();
		
			for(int i = 0; i < 7; i++) {
					slots.add(new JLabel());
				}
			
			for(JLabel j : slots) {
				j.setIcon(blueprints);
				inven.add(j);
			}
			
		frame.add(inven);
		
		//--Movement buttons.--
		JPanel move = new JPanel();
		move.setBackground(background);
		move.setLayout(new GridLayout(2,3));
		move.setBounds(10, 610, width/3, 100);	
		pickup = new JButton("Pick up"); 
		JButton up = new JButton(upA);
		use = new JButton("Use");
		JButton left = new JButton(leftA);
		JButton down = new JButton(downA);
		JButton right = new JButton(rightA);
		move.add(pickup); move.add(up); move.add(use);
		move.add(left);	move.add(down); move.add(right);
		
		frame.add(move);
		
		//--Text output area.--
		this.output = new JTextArea(30,20);
			output.setKeymap(null); //no input
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
	
		//Getters for controller to access and manipulate components.
		public JFrame getFrame() {
			return frame;
		}
	
		public Renderer getRenderer() {
			return renderer;
		}
	
		public JLabel getStatus() {
			return status;
		}
	
		public JTextArea getOutput() {
			return output;
		}
		
		public JComponent getCanvas() {
			return canvas;
		}
		
		public JLabel getCompass() {
			return compass;
		}
		
		public JButton getPickUp() {
			return pickup;
		}
		
		public JButton getUse() {
			return use;
		}
		
		public TexturedPanel getInventory() {
			return inven;
		}
		
		public ListPanel getListPanel() {
			return itemList;
		}
		
		public JFileChooser getFileChooser() {
			return fileChooser;
		}
		
		public JMenuItem getSave() {
			return save;
		}
		
		public JMenuItem getLoad() {
			return load;
		}
		
		public JTextArea getText() {
			return output;
		}
}
