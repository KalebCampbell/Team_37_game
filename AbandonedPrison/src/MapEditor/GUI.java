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
	private JButton rightroom;
	private JButton leftroom;
	private JButton toproom;
	private JButton downroom;
	private JButton leftdoor;
	private JButton rightdoor;
	private JButton topdoor;
	private JButton downdoor;
	private JButton key;
	private JButton wall;
	private JButton rightwall;
	private JButton topwall;
	private JButton downwall;
	boolean roomExist=false;
	private ArrayList<Room> rooms=new ArrayList<Room>();
	private ArrayList<Door> doors=new ArrayList<Door>();
	private ArrayList<Wall> walls=new ArrayList<Wall>();

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
	int x1=265;
	int y1=290;
	
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
		for(Door door : doors) {
			door.draw(g);
		}
		for(Wall wall : walls) {
			wall.draw(g);
		}
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
		
		rightroom=new JButton("rightRoom");
		rightroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				x1=x1+25;
				if(x1>CANVAS_WIDTH-25) {
					y1=y1+25;
					x1=0;
				}
				else {
					Room room1=new Room(x1,y1,10,10);
					rooms.add(room1);
				}
				
				
			}
		});
		leftroom=new JButton("leftRoom");
		leftroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				x1=x1-25;
				if(x1<0) {
					System.out.print("no empty space");
				}
				else {
					Room room1=new Room(x1,y1,10,10);
					rooms.add(room1);
				}
			}
		});
		toproom=new JButton("topRoom");
		toproom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				y1=y1-25;
				if(y1<0) {
					System.out.print("no empty space");
				}
				else {
					Room room1=new Room(x1,y1,10,10);
					rooms.add(room1);
				}
			}
		});
		downroom=new JButton("downRoom");
		downroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				y1=y1+25;
				if(y1<0) {
					System.out.print("no empty space");
				}
				else {
					Room room1=new Room(x1,y1,10,10);
					rooms.add(room1);
				}
			}
		});
		leftdoor=new JButton("leftDoor");
		leftdoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// add key to the current room
				Door door1=new Door(x1,y1,2,20);
				doors.add(door1);
			}
		});
		rightdoor=new JButton("rightDoor");
		rightdoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// add key to the current room
				Door door1=new Door(x1+20,y1,2,20);
				doors.add(door1);
			}
		});
		topdoor=new JButton("topDoor");
		topdoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// add key to the current room
				Door door1=new Door(x1,y1,20,2);
				doors.add(door1);
			}
		});
		downdoor=new JButton("downDoor");
		downdoor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// add key to the current room
				Door door1=new Door(x1,y1+20,20,2);
				doors.add(door1);
			}
		});
		wall=new JButton("leftwall");
		wall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// add key to the current room
				Wall wall1=new Wall(x1,y1,2,20);
				walls.add(wall1);
			}
		});
		rightwall=new JButton("rightwall");
		rightwall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// add key to the current room
				Wall wall1=new Wall(x1+20,y1,2,20);
				walls.add(wall1);
			}
		});
		topwall=new JButton("topwall");
		topwall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// add key to the current room
				Wall wall1=new Wall(x1,y1,20,2);
				walls.add(wall1);
			}
		});
		downwall=new JButton("downwall");
		downwall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// add key to the current room
				Wall wall1=new Wall(x1,y1+20,20,2);
				walls.add(wall1);
			}
		});
		key=new JButton("key");
		key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// add key to the current room
				
			}
		});
		
		JPanel roompanel = new JPanel(new BorderLayout());
		roompanel.setMaximumSize(new Dimension(1000, 25));
		roompanel.setPreferredSize(new Dimension(1000, 25));
		JPanel lroompanel = new JPanel(new BorderLayout());
		lroompanel.setMaximumSize(new Dimension(1000, 25));
		lroompanel.setPreferredSize(new Dimension(1000, 25));
		JPanel troompanel = new JPanel(new BorderLayout());
		troompanel.setMaximumSize(new Dimension(1000, 25));
		troompanel.setPreferredSize(new Dimension(1000, 25));
		JPanel droompanel = new JPanel(new BorderLayout());
		droompanel.setMaximumSize(new Dimension(1000, 25));
		droompanel.setPreferredSize(new Dimension(1000, 25));
		JPanel doorpanel = new JPanel(new BorderLayout());
		doorpanel.setMaximumSize(new Dimension(1000, 25));
		doorpanel.setPreferredSize(new Dimension(1000, 25));
		JPanel rdoorpanel = new JPanel(new BorderLayout());
		rdoorpanel.setMaximumSize(new Dimension(1000, 25));
		rdoorpanel.setPreferredSize(new Dimension(1000, 25));
		JPanel tdoorpanel = new JPanel(new BorderLayout());
		tdoorpanel.setMaximumSize(new Dimension(1000, 25));
		tdoorpanel.setPreferredSize(new Dimension(1000, 25));
		JPanel ddoorpanel = new JPanel(new BorderLayout());
		ddoorpanel.setMaximumSize(new Dimension(1000, 25));
		ddoorpanel.setPreferredSize(new Dimension(1000, 25));
		JPanel wallpanel = new JPanel(new BorderLayout());
		wallpanel.setMaximumSize(new Dimension(1000, 25));
		wallpanel.setPreferredSize(new Dimension(1000, 25));
		JPanel rwallpanel = new JPanel(new BorderLayout());
		rwallpanel.setMaximumSize(new Dimension(1000, 25));
		rwallpanel.setPreferredSize(new Dimension(1000, 25));
		JPanel twallpanel = new JPanel(new BorderLayout());
		twallpanel.setMaximumSize(new Dimension(1000, 25));
		twallpanel.setPreferredSize(new Dimension(1000, 25));
		JPanel dwallpanel = new JPanel(new BorderLayout());
		dwallpanel.setMaximumSize(new Dimension(1000, 25));
		dwallpanel.setPreferredSize(new Dimension(1000, 25));
		JPanel keypanel = new JPanel(new BorderLayout());
		keypanel.setMaximumSize(new Dimension(1000, 25));
		keypanel.setPreferredSize(new Dimension(1000, 25));
		roompanel.add(rightroom, BorderLayout.CENTER);
		lroompanel.add(leftroom, BorderLayout.CENTER);
		troompanel.add(toproom, BorderLayout.CENTER);
		droompanel.add(downroom, BorderLayout.CENTER);
		wallpanel.add(wall, BorderLayout.CENTER);
		rwallpanel.add(rightwall, BorderLayout.CENTER);
		twallpanel.add(topwall, BorderLayout.CENTER);
		dwallpanel.add(downwall, BorderLayout.CENTER);
		keypanel.add(key, BorderLayout.CENTER);
		doorpanel.add(leftdoor, BorderLayout.CENTER);
		rdoorpanel.add(rightdoor, BorderLayout.CENTER);
		tdoorpanel.add(topdoor, BorderLayout.CENTER);
		ddoorpanel.add(downdoor, BorderLayout.CENTER);

		// make the panel on the right, fix its size, give it a border!
		JPanel controls = new JPanel();
		controls.setPreferredSize(CONTROLS_SIZE);
		controls.setMinimumSize(CONTROLS_SIZE);
		controls.setMaximumSize(CONTROLS_SIZE);
		controls.setLayout(new BoxLayout(controls, BoxLayout.PAGE_AXIS));
		Border edge = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		controls.setBorder(edge);
		controls.add(roompanel);
		controls.add(lroompanel);
		controls.add(troompanel);
		controls.add(droompanel);
		controls.add(doorpanel);
		controls.add(rdoorpanel);
		controls.add(tdoorpanel);
		controls.add(ddoorpanel);
		controls.add(wallpanel);
		controls.add(rwallpanel);
		controls.add(twallpanel);
		controls.add(dwallpanel);
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
