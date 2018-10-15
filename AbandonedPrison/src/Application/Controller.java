package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Renderer.Room;




public class Controller {
	
	private Window window;
	
	public Controller(Window window) {
		this.window = window;
		this.window.frame.addKeyListener(new Input());
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

			@Override
			public void actionPerformed(ActionEvent e) {
				//use an item
			}
			
		}
	
	public class Input implements KeyListener {
		

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyChar() == 'w') {
				for(Renderer.Room r : window.renderer.rooms) {
					r.translate(0, 0, -8);
				}
				window.canvas.repaint();
				window.compass.setIcon(window.north);
			}
			if(e.getKeyChar() == 's') {
				for(Renderer.Room r : window.renderer.rooms) {
					r.translate(0, 0, 8);
				}
				window.canvas.repaint();
				window.compass.setIcon(window.south);
			}
			if(e.getKeyChar() == 'a') { //rotate left
			
			}
			
			if(e.getKeyChar() == 'd') { //rotate right
				
			}
			
			if(e.getKeyChar() == 'c') { //crouch/stand
				if(window.standing) {
					window.status.setIcon(window.crouch);
					window.standing = false;
					window.crouching = true;
				} else {
					window.status.setIcon(window.stand);
					window.standing = true;
					window.crouching = false;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
	}

}
