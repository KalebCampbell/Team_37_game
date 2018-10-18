package Application;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.xml.bind.JAXBException;

import GameWorld.AbstractItem;
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
	public String item = null;

	public Controller(Window window) {

		LoadXml load = new LoadXml();
		GameMapComponent gameComp = null;
		try {
			gameComp = load.unMarshal(new File("Map4.xml"));
		} catch (JAXBException e) {
			System.out.println("Parsing failed");
		}
		System.out.println("Parsing complete");
		this.game = new Game(gameComp);
		System.out.println("Load complete");

		this.window = window;
		this.player = game.getPlayer();
		window.getRenderer().setGame(game);
		window.getFrame().addKeyListener(new Input());
		window.getUse().addActionListener(new UseButton());
		window.getSave().addActionListener(new SaveAction());
		window.getLoad().addActionListener(new LoadAction());
		window.getCanvas().addMouseListener(new MouseInput());
		window.getInventory().addMouseListener(new InvenClick());
		window.getListPanel().getList().addActionListener(new SelectItem());
		window.getFrame().requestFocus();

	}

	// main method for testing
	public static void main(String[] args) {
		new Controller(new Window(700, 760, "Abandoned Prison"));
	}

	// method used to set the text output
	public void setText(String text) {
		window.getText().setText(text);
	}

	/**
	 * ActionListener for the pickup button picks up item and adds to inventory slot
	 *
	 * @author liam
	 *
	 */
	public class PickUpButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int roomID = player.getRoomId();
			Room room = null;

			// find players room in the room list
			for (Room r : game.getRooms()) {
				if (r.getRoomID() == roomID) {
					room = r;
				}
			}

			// null check
			if (room == null) {
				System.out.println("Room equals null!");
			}

			// get all items in the room and add them to the pop up listpanel
			for (AbstractItem item : room.getItems()) {
				window.getListPanel().addItem(item.toString());
			}

			window.getListPanel().setVisible(true);
			item = null;
		}
		//
	}

	/**
	 * Method to add players current items to the inventory JPanel
	 */
	public void initialiseInventory() {
		for (AbstractItem i : player.getInventory().getInventory()) {
			JLabel x = new JLabel();
			x.setIcon(window.getIcon(i.getItemName()));
			window.getInventory().add(x);
		}
	}

	/**
	 * Method for removing a item from the inventory after drop/use
	 */
	public void removeItem(String itemName) {

		Inventory i = player.getInventory();

		for (AbstractItem a : i.getInventory()) {
			if (a.getItemName() == itemName) {
				i.removeItemFromInventory(a);
			}
		}
	}

	/**
	 * Action listener for picking up an item.
	 */
	public class SelectItem implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			item = (String) window.getListPanel().getList().getSelectedItem();
			AbstractItem i;
			for (Room r : game.getRooms()) {
				if (r.getRoomID() == player.getRoomId()) {
					for (AbstractItem x : r.getItems()) {

					}
				}
			}

			window.getListPanel().setVisible(false);
			window.getText().setText("You picked up a " + item);
			// JLabel inventoryItem = new JLabel();
			// inventoryItem.setIcon(window.getIcon(item));
			// window.getInventory().add(inventoryItem);
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
			//dehighlight every slot

				if(x >= 14 && x <= 66 && y >= 27 && y <= 77) {
					//highlight slot1
					window.getInventory().select(0);
					window.setSelectedItem(window.getInventory().getItem(0));
				}
				if(x >= 14 && x <= 66 && y >= 91 && y <= 143) {
					//highlight slot2
					window.getInventory().select(1);
					window.setSelectedItem(window.getInventory().getItem(1));
				}
				if(x >= 14 && x <= 66 && y >= 156 && y <= 208) {
					//highlight slot3
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
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * Mouse listener class to provide co-ordinates of mouse clicks on canvas
	 */
	public class MouseInput implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			window.getRenderer().click(e.getPoint());
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
				window.getCompass().setIcon(window.north);
				window.getCanvas().repaint();
			}

			if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
				window.getRenderer().moveBackward();
				window.getCompass().setIcon(window.south);
				window.getCanvas().repaint();
			}

			if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
				window.getRenderer().rotateLeft();
				window.getCompass().setIcon(window.west);
				window.getCanvas().repaint();
			}

			if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
				window.getRenderer().rotateRight();
				window.getCompass().setIcon(window.east);
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

	// Action listeners for movement buttons
	public class ButtonUp implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// move foward
			//window.getFrame().requestFocus(); after every move to set focus back to frame
		}

	}

	public class ButtonDown implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// move back
		}

	}

	public class ButtonLeft implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// move left
		}

	}

	public class ButtonRight implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// move right
		}

	}

}
