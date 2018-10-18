package Application;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.xml.bind.JAXBException;

import GameWorld.Item;
import GameWorld.Game;
import GameWorld.Inventory;
import GameWorld.Player;
import GameWorld.Room;
import Persistence.GameMapComponent;
import Persistence.LoadXml;

/**
 * Controller to communicate with game world, and manipulate the Window based on
 * this communication.
 *
 * @author liam
 *
 */
public class Controller {

	private Window window; // view
	private Game game; // model
	private Player player;
	private String response = null;
	public GameWorld.Door door = null;
	public GameWorld.Item item = null;


	public Controller(Window window) {


		GameMapComponent gameComp = null;
		try {
			gameComp = LoadXml.unMarshal(new File("Map4.xml"));
		} catch (JAXBException e) {
			System.out.println("Parsing failed");
		}
		System.out.println("Parsing complete");
		this.game = new Game(gameComp);
		System.out.println("Load complete");

		this.window = window;
		this.player = game.getPlayer();
		window.getRenderer().setGame(game);
		window.getCanvas().repaint();
		window.getFrame().addKeyListener(new Input());
		window.getUse().addActionListener(new UseButton());
		window.getSave().addActionListener(new SaveAction());
		window.getLoad().addActionListener(new LoadAction());
		window.getCanvas().addMouseListener(new MouseInput());
		window.getInventory().addMouseListener(new InvenClick());
		window.getUp().addActionListener(new ButtonUp());
		window.getDown().addActionListener(new ButtonDown());
		window.getLeft().addActionListener(new ButtonLeft());
		window.getRight().addActionListener(new ButtonRight());
		window.getDrop().addActionListener(new DropListener());
		window.getPopUp().getCancel().addActionListener(new PopUpListener());
		window.getPopUp().getPickup().addActionListener(new PopUpListener());
		window.getFrame().requestFocus();

	}

	// main method for testing
	public static void main(String[] args) {
		new Controller(new Window(700, 760, "The Acid Adventure"));
	}

	// method used to set the text output
	public void setText(String text) {
		window.getText().setText(text);
	}

	/**
	 * Listener for drop button
	 */
	public class DropListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(window.getSelectedItem()!=null) {
				for(JLabel i : window.getInventory().getItems()) {

					System.out.println(i.getIcon());
				}
			}

		}

	}


	/**
	 * Method to add players current items to the inventory JPanel
	 */
	public void initialiseInventory() {
		window.getInventory().removeAll();
		for (GameWorld.Item i : player.getInventory().getInventory()) {
			System.out.println(i.getItemName());
			JLabel x = new JLabel();
			x.setIcon(window.getIcon(i.getItemName()));


			window.getInventory().add(x);
			window.getInventory().repaint();
		}
	}


	/**
	 * Class for highlighting inventory items on click.
	 */
	public class InvenClick implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			System.out.println(x+" "+y);
			//dehighlight every slot

				if(x >= 14 && x <= 66 && y >= 27 && y <= 77) {
					//highlight slot1
					System.out.println("yofuk");
					window.getInventory().select(0);
					window.setSelectedItem(window.getInventory().getItem(0));
					Border b = BorderFactory.createLineBorder(Color.red);
					window.getSelectedItem().label.setBorder(b);
				}
				if(x >= 14 && x <= 66 && y >= 91 && y <= 143) {
					//highlight slot2
					window.getInventory().select(1);
					window.setSelectedItem(window.getInventory().getItem(1));
				}
				if(x >= 14 && x <= 66 && y >= 156 && y <= 208) {
					//highlight slot3l
					window.getInventory().select(2);
					window.setSelectedItem(window.getInventory().getItem(2));
				}
				if(x >= 14 && x <= 66 && y >= 221 && y <= 274) {
					//highlight slot4
					window.getInventory().select(3);
					window.setSelectedItem(window.getInventory().getItem(3));
				}
				if(x >= 14 && x <= 66 && y >= 287 && y <= 340) {
					//highlight slot5
					window.getInventory().select(4);
					window.setSelectedItem(window.getInventory().getItem(4));
				}
				if(x >= 14 && x <= 66 && y >= 352 && y <= 405) {
					//highlight slot6
					window.getInventory().select(5);
					window.setSelectedItem(window.getInventory().getItem(5));
				}
				if(x >= 14 && x <= 66 && y >= 416 && y <= 470) {
					//highlight slot7
					window.getInventory().select(6);
					window.setSelectedItem(window.getInventory().getItem(6));
				}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stplayer.getInventory().addItemToInventory(item);ub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stubadd(new JMenuItem(""));


		}

	}

	public class PopUpListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			response = e.getActionCommand();
			if(response == "Cancel") {
				window.getText().setText("");
				return;
			} else if(response == "Pickup") {
				window.getText().setText("You picked up a "+item.getItemName());
				window.getPopUp().setVisible(false);
				//render.removeItem(item);
				//add item to players inventory and update the inventory slot.
				player.getInventory().addItemToInventory(item);

				window.getInventory().addItem(new ItemLabel(window.getIcon(item.getItemName()), item));
				initialiseInventory();
				window.getText().setText("You picked up an item");
			}
		}

	}

	/**
	 * Mouse listener class to provide co-ordinates of mouse clicks on canvas
	 */
	public class MouseInput implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			Renderer.AbstractItem clickedItem = window.getRenderer().clickItem(e.getPoint());
			Renderer.AbstractDoor clickedDoor = window.getRenderer().clickDoor(e.getPoint());

			item = clickedItem.getItem();
			//pop up menu if they choose yes
			if(clickedItem == null) {
				if(clickedDoor == null) {
					window.getOutput().setText("You clicked... nothing");
				}else {
					door = clickedDoor.getDoor();
					if(door.isLocked()) {

					}
				}
				window.getOutput().setText("You clicked... nothing");
			} else {
				window.getPopUp().show(window.getCanvas(),e.getX(),e.getY());
				window.getPopUp().setVisible(true);
			}


		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public class UseButton implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			System.out.println("you used and item");
		}

	}

	public class LoadAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JFileChooser load = new JFileChooser();
			load.setDialogTitle("Select file to Load");
			int result = load.showOpenDialog(window.getFrame());
			File LoadFile;
			// check if a file has been selected
			if (result == JFileChooser.APPROVE_OPTION) {
				LoadFile = load.getSelectedFile();
				System.out.println("file" + LoadFile.getAbsolutePath());
			}
		}

	}

	public class SaveAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JFileChooser save = new JFileChooser();
			save.setDialogTitle("Save file");
			int result = save.showSaveDialog(window.getFrame());
			File SaveFile;
			// check for a file
			if (result == JFileChooser.APPROVE_OPTION) {
				SaveFile = save.getSelectedFile();
				System.out.println("Saved File: " + SaveFile.getAbsolutePath());
			}
		}

	}

	/**
	 * Inner class to process keyInput from the Window.
	 *
	 * @author liam
	 */
	public class Input implements KeyListener {

		public void keyTyped(KeyEvent e) {

		}

		public void keyPressed(KeyEvent e) {

			if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
				window.getRenderer().moveForward();
				if(game.playerMove()) {
					player.move(player.getDirection());
					updateCompass(player.getDirection());
				}
				System.out.println(player.getDirection());
				window.getCanvas().repaint();
			}

			if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
				window.getRenderer().moveBackward();
				//need to add player move backward here
				updateCompass(player.getDirection());
				window.getCanvas().repaint();
			}

			if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
				window.getRenderer().rotateLeft();
				window.getCompass().setIcon(window.west);
				player.turnLeft();
				updateCompass(player.getDirection());
				window.getCanvas().repaint();
			}

			if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
				window.getRenderer().rotateRight();
				window.getCompass().setIcon(window.east);
				player.turnRight();
				updateCompass(player.getDirection());
				window.getCanvas().repaint();
			}

			if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
				if (window.standing) {
					window.standing = false;
					window.crouching = true;
					window.getStatus().setIcon(window.crouch);
				} else {
					window.standing = true;
					window.crouching = false;
					window.getStatus().setIcon(window.stand);
				}
			}
		}

		public void keyReleased(KeyEvent e) {

		}

	}

	//***********ADD COMPASS UPDATES FOR THESE MOVES********************
	// Action listeners for movement buttons
	public class ButtonUp implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// move foward
			window.getRenderer().moveForward();
			window.getCanvas().repaint();
			window.getFrame().requestFocus();
			//window.getFrame().requestFocus(); after every move to set focus back to frame
		}

	}

	public class ButtonDown implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// move back
			window.getRenderer().moveBackward();
			window.getCanvas().repaint();
			window.getFrame().requestFocus();
		}

	}

	public class ButtonLeft implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// move left
			window.getRenderer().rotateLeft();
			window.getCanvas().repaint();
			window.getFrame().requestFocus();
		}

	}

	public class ButtonRight implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// move right
			window.getRenderer().rotateRight();
			window.getCanvas().repaint();
			window.getFrame().requestFocus();
		}

	}

	public void updateCompass(String dir) {
		if(dir == "N") {
			window.getCompass().setIcon(window.north);
		}
		if(dir == "S") {
			window.getCompass().setIcon(window.south);
		}
		if(dir == "E") {
			window.getCompass().setIcon(window.east);
		}
		if(dir == "W") {
			window.getCompass().setIcon(window.west);
		}
	}

	//some getters for testing
	public Player getPlayer() {
		return player;
	}

	public Window getWindow() {
		return window;
	}
}
