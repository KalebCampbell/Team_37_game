package Application;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import GameWorld.Game;
import Persistence2.GameMap;
import Persistence2.Parser2;

public class GUI {
	
	//main method for testing
	//public static void main(String[] args) {
		//new Window(700,760,"Abandoned Prison");
	//}
	
	public GUI () {
		new Window(700,760,"Abandoned Prison");
		init();
	}

	// TEMPORARY SETUP FOR YOU TO FIX
	public void init() {
		
		Parser2 parse = new Parser2();
		GameMap setup = null;
		try {
			setup = parse.setup("map.xml");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("MAP PARSING DONE");
		System.out.println("CREATING GAME WORLD");
		Game game = new Game(setup);
		
	}
	
	//Handle events in GUI receiving the events from the window 
	
	
	
	
}
