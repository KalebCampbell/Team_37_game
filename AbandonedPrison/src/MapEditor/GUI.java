package MapEditor;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GUI {
	private JButton west;
	private JButton east;
	private JButton north;
	private JButton south;
	private JButton room;
	private JButton door;
	private JButton key;
	private JButton wall;
	boolean roomExist=false;
	private ArrayList<Room> rooms=new ArrayList<Room>();
	/**
	 * Width of the canvas.
	 */
	public static final int CANVAS_WIDTH = 600;
	/**
	 * Height of the canvas.
	 */
	public static final int CANVAS_HEIGHT = 600;

	private JFrame frame;
	
	private static final Dimension DRAWING_SIZE = new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
	private static final Dimension CONTROLS_SIZE = new Dimension(150, 600);
	int x1=10;
	int y1=10;
	
	/**
	 * GUI constructor.
	 */
	public GUI() {
		initialise();
	}
	
	/**
	 * Is called every time the drawing canvas is drawn. 
	 */
	private void render(Graphics g,int x, int y,int width,int height) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
		for(Room room : rooms) {
			room.draw(g);
		}
	}
	
	private void roomRender(Graphics g) {
		
	}
	private void doorRender() {
		
	}
	private void wallRender() {
		
	}
	private void keyRender() {
		
	}
	
	/**
	 * Is called every time the user presses a key. This can be used for moving the
	 * camera around. It is passed a KeyEvent object, whose methods of interest are
	 * getKeyChar() and getKeyCode().
	 */
	private void onKeyPress(KeyEvent ev) {
		
	}

	private void initialise() {
		frame = new JFrame();
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
		frame.setSize(new Dimension(DRAWING_SIZE.width + CONTROLS_SIZE.width, DRAWING_SIZE.height));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set up the drawing canvas, hook it into the render() method, and give
		// it a nice default if render() returns null.
		JComponent drawing = new JComponent() {
			protected void paintComponent(Graphics g) {
				render(g,0,0,CANVAS_WIDTH, CANVAS_HEIGHT);
//				if(roomExist) {
//					render(g,30,30,100,100);
//				}
				redraw();
			}
		};
		
		drawing.setPreferredSize(DRAWING_SIZE);
		drawing.setMinimumSize(DRAWING_SIZE);
		drawing.setMaximumSize(DRAWING_SIZE);
		drawing.setVisible(true);
		
		room=new JButton("Room");
		room.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Room room1=new Room(x1,y1,10,10);
				x1=x1+25;
				if(x1>CANVAS_WIDTH-25) {
					y1=y1+25;
					x1=0;
				}
				rooms.add(room1);
				
			}
		});
		door=new JButton("Door");
		door.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// add key to the current room
				doorRender();
			}
		});
		wall=new JButton("wall");
		wall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// add key to the current room
				wallRender();
			}
		});
		key=new JButton("key");
		key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// add key to the current room
				keyRender();
			}
		});
		
		JPanel roompanel = new JPanel(new BorderLayout());
		roompanel.setMaximumSize(new Dimension(1000, 25));
		roompanel.setPreferredSize(new Dimension(1000, 25));
		JPanel doorpanel = new JPanel(new BorderLayout());
		doorpanel.setMaximumSize(new Dimension(1000, 25));
		doorpanel.setPreferredSize(new Dimension(1000, 25));
		JPanel wallpanel = new JPanel(new BorderLayout());
		wallpanel.setMaximumSize(new Dimension(1000, 25));
		wallpanel.setPreferredSize(new Dimension(1000, 25));
		JPanel keypanel = new JPanel(new BorderLayout());
		keypanel.setMaximumSize(new Dimension(1000, 25));
		keypanel.setPreferredSize(new Dimension(1000, 25));
		roompanel.add(room, BorderLayout.CENTER);
		wallpanel.add(wall, BorderLayout.CENTER);
		keypanel.add(key, BorderLayout.CENTER);
		doorpanel.add(door, BorderLayout.CENTER);

		// make the panel on the right, fix its size, give it a border!
		JPanel controls = new JPanel();
		controls.setPreferredSize(CONTROLS_SIZE);
		controls.setMinimumSize(CONTROLS_SIZE);
		controls.setMaximumSize(CONTROLS_SIZE);
		controls.setLayout(new BoxLayout(controls, BoxLayout.PAGE_AXIS));
		Border edge = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		controls.setBorder(edge);
		controls.add(roompanel);
		controls.add(doorpanel);
		controls.add(wallpanel);
		controls.add(keypanel);
		controls.add(Box.createRigidArea(new Dimension(0, 15)));
		// if i were going to add more GUI components, i'd do it here.
		controls.add(Box.createVerticalGlue());

		// put it all together.
		frame.add(drawing);
		frame.add(controls);

		frame.pack();
		frame.setVisible(true);
	}
	
	public void redraw() {
		frame.repaint();
	}
	
	public static void main(String[] args) {
		new GUI();
	}
}
