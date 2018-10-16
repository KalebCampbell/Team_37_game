package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		this.window = window;
		this.window.getFrame().addKeyListener(new Input());
	}
	
	//main method for testing
	public static void main(String[] args) {
		new Controller(new Window(700,760,"Abandoned Prison"));
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
			System.out.println("key was pressed");
		}

		public void keyReleased(KeyEvent e) {
			
		}
		
	}

}
