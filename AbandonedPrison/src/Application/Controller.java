package Application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFileChooser;

import GameWorld.Game;

/**
 * Controller to communicate with game world, and manipulate the Window 
 * based on this communication.
 * 
 * @author liam
 *
 */
public class Controller {
	
	private Window window; //view
	private Game game; //model
	public String item;
	
	public Controller(Window window) {
	
		this.window = window;
		window.getFrame().addKeyListener(new Input());
		window.getPickUp().addActionListener(new PickUpButton());
		window.getUse().addActionListener(new UseButton());
		window.getSave().addActionListener(new SaveAction());
		window.getLoad().addActionListener(new LoadAction());
	
		
	}
	
	//main method for testing
	public static void main(String[] args) {
		new Controller(new Window(700,760,"Abandoned Prison"));
	}
		
	//method used to set the text output
	public void setText(String text) {
		window.getText().setText(text);
	}
		
		
		/**
		 * ActionListener for the pickup button
		 * picks up item and adds to inventory slot
		 * @author liam
		 *
		 */
		public class PickUpButton implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				window.getListPanel().setVisible(true);
//				int roomID = game.getPlayer().getRoomId();
//				Room currentRoom = null;
//				
//					for(Room r : game.roomList) {
//						if(r.getRoomID() == roomID) {
//							currentRoom = r;
//						} else {
//							System.out.println("You are not in a room with items in it");
//							currentRoom = null;
//						}
//					}
//					//add list of items in room to pop up menu
//					for(AbstractItem i : currentRoom.getItems()) {
//						window.getItemPopUp().addItem(i.getItemName());
//					}
//					
//					window.getItemPopUp().addActionListener(new ActionListener() {
//						@Override
//						public void actionPerformed(ActionEvent e) {
//							item = (String) window.getItemPopUp().getSelectedItem();
//						}
//						
//					});
//				
//					System.out.println(item);
//					//add new JLabel with corrosponding image to item
			}
//			
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
					//check if a file has been selected
					if(result == JFileChooser.APPROVE_OPTION) {
						LoadFile = load.getSelectedFile();
						System.out.println("file"+LoadFile
								.getAbsolutePath());
					}
			}
			
		}
		
		public class SaveAction implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				JFileChooser save = new JFileChooser();
				save.setDialogTitle("Save file");
				int result = save.showSaveDialog(window.getFrame());
				File SaveFile;
					//check for a file
					if(result == JFileChooser.APPROVE_OPTION) {
						SaveFile = save.getSelectedFile();
						System.out.println("Saved File: "+SaveFile.getAbsolutePath());
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
			
			if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W') {
				window.getRenderer().moveForward();
				window.getCompass().setIcon(window.north);
				window.getCanvas().repaint();
			}
			
			if(e.getKeyChar() == 's' || e.getKeyChar() == 'S') {
				window.getRenderer().moveBackward();
				window.getCompass().setIcon(window.south);
				window.getCanvas().repaint();
			}
			
			if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
				window.getRenderer().rotateLeft();
				window.getCompass().setIcon(window.west);
				window.getCanvas().repaint();
			}
			
			if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
				window.getRenderer().rotateRight();
				window.getCompass().setIcon(window.east);
				window.getCanvas().repaint();
			}
			
			if(e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
				if(window.standing) {
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
	
	//Action listeners for movement buttons
	public class ButtonUp implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//move foward
		}
		
	}
	
	public class ButtonDown implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//move back
		}
		
	}
	
	public class ButtonLeft implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//move left
		}
		
	}
	
	public class ButtonRight implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//move right
		}
		
	}

}
