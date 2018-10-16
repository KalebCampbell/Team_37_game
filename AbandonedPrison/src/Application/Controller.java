package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.xml.bind.JAXBException;

import Application.Window.Direction;
import GameWorld.Game;
import Persistence.GameMapComponent;
import Persistence.LoadXml;
import Renderer.Room;
/**
 * Controller to communicate with game world, and manipulate the Window 
 * based on this communication.
 * 
 * @author liam
 *
 */
public class Controller {
	
	private Window window;
	
	public Controller(Window window) {
		// Autoload
		LoadXml load = new LoadXml();
		GameMapComponent gameComp = null;
		try {
			gameComp = load.unMarshal(new File("Map1.xml"));
		} catch (JAXBException e) {
			System.out.println("Parsing failed");
		}
		System.out.println("Parsing complete");
		Game game = new Game(gameComp);
		System.out.println("Load complete");
		
		
		
		
		
		this.window = window;
		this.window.getFrame().addKeyListener(new Input());
	}
	
	//main method for testing
	//public static void main(String[] args) {
	//	new Controller(new Window(700,760,"Abandoned Prison"));
	//}
	
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
		
		public class PickUpButton implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				//list of items in room with a pickup option
				//add and remove items from inventory
			}
			
		}
		
		public class UseButton implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				//use an item
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

}
