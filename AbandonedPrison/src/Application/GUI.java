package Application;

import java.io.File;

import GameWorld.Game;
import Persistence.Dom_Parser;
import Persistence.GameMap;
import Persistence.RoomComponent;

public class GUI {
	
	//main method for testing
	//public static void main(String[] args) {
		//new Window(700,760,"Abandoned Prison");
	//}
	
	public GUI () {
		new Window(700,760,"Abandoned Prison");
		init();
		
	}

	public void init() {
		// Game game = new GameWorld.Game(Arguments) - Michael
		// Connect to Gameworld.Game.begin - Michael
		
		// New game button?
		// Basic connection between Application -> Persistence
		Dom_Parser parse = new Dom_Parser();
		GameMap setup = parse.setup(new File("file.xml"));
		Game game = new Game(setup);
		
		
	}
	
	//Handle events in GUI receiving the events from the window 
	
	
	
	
}
