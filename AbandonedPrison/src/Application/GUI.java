package Application;

import java.io.File;

import GameWorld.Game;
import Persistence2.GameMap;
import Persistence2.Parser;

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
		Parser parse = new Parser();
		GameMap setup = parse.setup(new File("file.xml"));
		Game game = new Game(setup);
		
	}
	
	//Handle events in GUI receiving the events from the window 
	
	
	
	
}
