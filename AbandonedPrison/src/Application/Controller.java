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

import Application.Controller.ButtonDown;
import Application.Controller.ButtonLeft;
import Application.Controller.ButtonRight;
import Application.Controller.ButtonUp;
import Application.Controller.DoorPopUpListener;
import Application.Controller.DropPopUpListener;
import Application.Controller.Input;
import Application.Controller.InvenClick;
import Application.Controller.LoadAction;
import Application.Controller.MouseInput;
import Application.Controller.PopUpListener;
import Application.Controller.SaveAction;
import GameWorld.Item;
import GameWorld.Game;
import GameWorld.Inventory;
import GameWorld.Player;
import GameWorld.Room;
import Persistence.ConvertMapEditor;
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

	/**
	 * Important objects and response variables to be passed between listeners
	 */
	private Window window; // view
	private Game game; // model
	private Player player;
	private String response = null;
	private String dropResponse = null;
	private String doorResponse = null;
	public Renderer.AbstractDoor door = null; // TODO Auto-generated method stub
	public GameWorld.Item item = null;
	public GameWorld.Item dropItem = null;

	public Controller(Window window) {

		GameMapComponent gameComp = null;
		try {
			gameComp = LoadXml.unMarshal(new File("Final.xml"));
		} catch (JAXBException e) {
			System.out.println("Parsing failed");
		}
		System.out.println("Parsing complete");
		this.game = new Game(gameComp);
		System.out.println("Load complete");

		this.window = window;
		this.player = game.getPlayer();

		// initialise all action and mouse listeners
		window.getRenderer().setGame(game);
		window.getCanvas().repaint();
		window.getFrame().addKeyListener(new Input());
		window.getSave().addActionListener(new SaveAction());
		window.getLoad().addActionListener(new LoadAction());
		window.getCanvas().addMouseListener(new MouseInput());
		window.getInventory().addMouseListener(new InvenClick());
		window.getUp().addActionListener(new ButtonUp());
		window.getDown().addActionListener(new ButtonDown());
		window.getLeft().addActionListener(new ButtonLeft());
		window.getRight().addActionListener(new ButtonRight());
		window.getDoorPopUp().getDrop().addActionListener(new DoorPopUpListener());
		window.getDoorPopUp().getCancel().addActionListener(new DoorPopUpListener());
		window.getDropPopUp().getCancel().addActionListener(new DropPopUpListener());
		window.getDropPopUp().getDrop().addActionListener(new DropPopUpListener());
		window.getPopUp().getCancel().addActionListener(new PopUpListener());
		window.getPopUp().getPickup().addActionListener(new PopUpListener());
		window.getFrame().requestFocus();
	}

	// run game
	public static void main(String[] args) {
		new Controller(new Window(700, 760, "The Acid Adventure"));
	}

	/**
	 * Method to add players current items to the inventory JPanel
	 */
	public void initialiseInventory() {
		// clear panel
		window.getInventory().removeAll();
		// add all items currently in player inventory into the inventory panel
		for (GameWorld.Item i : player.getInventory().getInventory()) {
			JLabel current = new JLabel();
			current.setIcon(window.getIcon(i.getItemName()));
			window.getInventory().add(current);
		}
		// repaint component
		window.getInventory().repaint();
	}

	/**
	 * MouseListener for clicking an inventory item.
	 */
	public class InvenClick implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// click coords
			int x = e.getX();
			int y = e.getY();

			if (x >= 14 && x <= 66 && y >= 27 && y <= 77) {
				if (window.getInventory().getItems().get(0) != null) {
					window.getInventory().select(0);
					window.setSelectedItem(window.getInventory().getItem(0));
					window.getDropPopUp().show(window.getInventory(), e.getX(), e.getY());
					dropItem = window.getSelectedItem().item;
					window.getDropPopUp().setVisible(true);
				}
			}
			if (x >= 14 && x <= 66 && y >= 91 && y <= 143) {
				if (window.getInventory().getItems().get(1) != null) {
					window.getInventory().select(1);
					window.setSelectedItem(window.getInventory().getItem(1));
					window.getDropPopUp().show(window.getInventory(), e.getX(), e.getY());
					dropItem = window.getSelectedItem().item;
					window.getDropPopUp().setVisible(true);
				}
			}
			if (x >= 14 && x <= 66 && y >= 156 && y <= 208) {
				if (window.getInventory().getItems().get(2) != null) {
					window.getInventory().select(2);
					window.setSelectedItem(window.getInventory().getItem(2));
					window.getDropPopUp().show(window.getInventory(), e.getX(), e.getY());
					dropItem = window.getSelectedItem().item;
					window.getDropPopUp().setVisible(true);
				}
			}
			if (x >= 14 && x <= 66 && y >= 221 && y <= 274) {
				if (window.getInventory().getItems().get(3) != null) {
					window.getInventory().select(3);
					window.setSelectedItem(window.getInventory().getItem(3));
					window.getDropPopUp().show(window.getInventory(), e.getX(), e.getY());
					dropItem = window.getSelectedItem().item;
					window.getDropPopUp().setVisible(true);
				}
			}
			if (x >= 14 && x <= 66 && y >= 287 && y <= 340) {
				if (window.getInventory().getItems().get(4) != null) {
					window.getInventory().select(4);
					window.setSelectedItem(window.getInventory().getItem(4));
					window.getDropPopUp().show(window.getInventory(), e.getX(), e.getY());
					dropItem = window.getSelectedItem().item;
					window.getDropPopUp().setVisible(true);
				}
			}
			if (x >= 14 && x <= 66 && y >= 352 && y <= 405) {
				if (window.getInventory().getItems().get(5) != null) {
					window.getInventory().select(5);
					window.setSelectedItem(window.getInventory().getItem(5));
					window.getDropPopUp().show(window.getInventory(), e.getX(), e.getY());
					dropItem = window.getSelectedItem().item;
					window.getDropPopUp().setVisible(true);
				}
			}
			if (x >= 14 && x <= 66 && y >= 416 && y <= 470) {
				if (window.getInventory().getItems().get(6) != null) {
					window.getInventory().select(6); // TODO Auto-generated method stub
					window.setSelectedItem(window.getInventory().getItem(6));
					window.getDropPopUp().show(window.getInventory(), e.getX(), e.getY());
					dropItem = window.getSelectedItem().item;
					window.getDropPopUp().setVisible(true);
				}
			}
		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {

		}

	}

	/**
	 * ActionLister to respond to inventory drop menu response.
	 */
	public class DropPopUpListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// get input
			dropResponse = e.getActionCommand();

			if (dropResponse.equals("Cancel")) {
				window.getText().setText("");
				return;
			} else if (dropResponse.equals("Drop")) {
				window.getText().setText("You dropped your " + dropItem.getItemName());
				window.getDropPopUp().setVisible(false);
				window.getRenderer().putDownItem(dropItem);// display dropped item in renderer
				player.getInventory().removeItemFromInventory(dropItem);// remove from player inventory
				window.getInventory().removeItem(dropItem);// remove from list of items in inventory panel
				initialiseInventory();// refresh inventory display
			}
		}

	}

	/**
	 * ActionListener to respond to item click menu response.
	 */
	public class PopUpListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// get input
			response = e.getActionCommand();

			if (response.equals("Cancel")) {
				window.getText().setText("");
				return;
			} else if (response.equals("Pickup")) {
				window.getText().setText("You picked up a " + item.getItemName());
				window.getPopUp().setVisible(false);// hide menu
				window.getRenderer().pickupItem(item);// remove item from renderer floor
				window.getCanvas().repaint();
				player.getInventory().addItemToInventory(item);// add to player inventory
				window.getInventory().addItem(new ItemLabel(window.getIcon(item.getItemName()), item));// add to
																										// inventory
																										// panel
				initialiseInventory();// refresh panel display
			}
		}

	}

	/**
	 * ActionListener to respond to door click menu response
	 */
	public class DoorPopUpListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// get input
			doorResponse = e.getActionCommand();

			if (doorResponse.equals("Unlock")) {
				// check player holds the corresponding key
				for (Item i : player.getInventory().getInventory()) {
					if (i.getItemId() == door.getDoor().getId()) {
						window.getRenderer().unlockDoor(door);
						window.getText().setText("You unlocked the door");
						return;
					}
				}
				// do not have the key
				window.getText().setText("You do not have a key to unlock this door :-(");
				return;
			} else {
				window.getText().setText("");
			}
		}

	}

	/**
	 * Mouse listener class to provide co-ordinates of mouse clicks on canvas
	 */
	public class MouseInput implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// Receives item and door that was clicked on from the renderer.
			Renderer.AbstractItem clickedItem = window.getRenderer().clickItem(e.getPoint());
			Renderer.AbstractDoor clickedDoor = window.getRenderer().clickDoor(e.getPoint());

			// null checks
			if (clickedItem != null)
				item = clickedItem.getItem();
			if (clickedDoor != null)
				door = clickedDoor;

			if (clickedItem == null) {// no item clicked thus check for door click
				if (clickedDoor == null) {
					window.getOutput().setText("You clicked... nothing");
					return;
				} else {
					if (door.getDoor().isLocked()) {// if door is locked display pop up menu with unlock option
						window.getDoorPopUp().show(window.getCanvas(), e.getX(), e.getY());
						window.getDoorPopUp().setVisible(true);
					}
				}
			} else {
				// display menu with the option to pickup the clicked item
				window.getPopUp().show(window.getCanvas(), e.getX(), e.getY());
				window.getPopUp().setVisible(true);
			}
		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

	}

	/**
	 * ActionListener class for load button in top menu
	 */
	public class LoadAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JFileChooser load = new JFileChooser();
			load.setDialogTitle("Select file to Load");
			int result = load.showOpenDialog(window.getFrame());
			File LoadFile;
			// check if a file has been selected
			if (result == JFileChooser.APPROVE_OPTION) {
				LoadFile = load.getSelectedFile();
				GameMapComponent loadGame = null;
				try {
					loadGame = LoadXml.unMarshal(LoadFile);
				} catch (JAXBException e1) {
					System.out.println("Parsing failed");
				}
				game = new Game(loadGame);
				System.out.println("file" + LoadFile.getAbsolutePath());
			}
		}

	}

	/**
	 * ActionListener class for save button in top menu
	 */
	public class SaveAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JFileChooser save = new JFileChooser();
			save.setDialogTitle("Save file");
			int result = save.showSaveDialog(window.getFrame());
			File SaveFile = null;
			// check for a file
			if (result == JFileChooser.APPROVE_OPTION) {
				SaveFile = save.getSelectedFile();
				System.out.println("Saved File: " + SaveFile.getAbsolutePath());
			}
			// save current game to a file
			ConvertMapEditor editor = new ConvertMapEditor();
			editor.ConvertGame(game, SaveFile);

		}

	}

	/**
	 * Key input listener implementing player movement
	 */
	public class Input implements KeyListener {

		public void keyTyped(KeyEvent e) {

		}

		public void keyPressed(KeyEvent e) {

			if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
				if (game.playerMove()) {
					window.getRenderer().moveForward();
					// player.move(player.getDirection());
					updateCompass(player.getDirection());
				}
				window.getCanvas().repaint();
			}

			if (e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
				if (game.playerMove()) {
					window.getRenderer().moveBackward();
					updateCompass(player.getDirection());
				}
				window.getCanvas().repaint();
			}

			if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
				if (game.playerMove()) {
					window.getRenderer().rotateLeft();
					player.turnLeft();
					updateCompass(player.getDirection());
				}
				window.getCanvas().repaint();
			}

			if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
				if (game.playerMove()) {
					window.getRenderer().rotateRight();
					player.turnRight();
					updateCompass(player.getDirection());
				}
				window.getCanvas().repaint();
			}

			// standing + crouching icon change on key click
			if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
				if (window.standing) {
					window.standing = false;
					window.crouching = true;
					// waiting for behavior implemention
					window.getStatus().setIcon(window.crouch);
				} else {
					window.standing = true;
					window.crouching = false;
					// waiting for behavior implemention
					window.getStatus().setIcon(window.stand);
				}
			}
		}

		public void keyReleased(KeyEvent e) {

		}

	}

	/**
	 * ActionListener for the up arrow JButton
	 */
	public class ButtonUp implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// move foward
			window.getRenderer().moveForward();
			updateCompass(player.getDirection());
			window.getCanvas().repaint();
			window.getFrame().requestFocus();
		}

	}

	/**
	 * ActionListener for the down arrow JButton
	 */
	public class ButtonDown implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// move back
			window.getRenderer().moveBackward();
			updateCompass(player.getDirection());
			window.getCanvas().repaint();
			window.getFrame().requestFocus();
		}

	}

	/**
	 * ActionListener for the left arrow JButton
	 */
	public class ButtonLeft implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// move left
			window.getRenderer().rotateLeft();
			updateCompass(player.getDirection());
			window.getCanvas().repaint();
			window.getFrame().requestFocus();
		}

	}

	/**
	 * ActionListener for the right arrow JButton
	 */
	public class ButtonRight implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// move right
			window.getRenderer().rotateRight();
			updateCompass(player.getDirection());
			window.getCanvas().repaint();
			window.getFrame().requestFocus();
		}

	}

	/**
	 * Method for updating the compass icon based on the players current direction
	 */
	public void updateCompass(String dir) {
		if (dir == "N") {
			window.getCompass().setIcon(window.north);
		}
		if (dir == "S") {
			window.getCompass().setIcon(window.south);
		}
		if (dir == "E") {
			window.getCompass().setIcon(window.east);
		}
		if (dir == "W") {
			window.getCompass().setIcon(window.west);
		}
	}

	// getters and setters
	public Player getPlayer() {
		return player;
	}

	public Window getWindow() {
		return window;
	}
}
