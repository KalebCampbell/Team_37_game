package Main;

import Application.GUI;

public class Main {
	
	public static void main(String[] args) {
	// ===========================================
	// ===== Parse in command-line arguments =====
	// ===== Optionally start from .exe or cmd====
	// ===========================================
	String fileName = null;
	int timeLimit = 0;
	
	// Loop over arguments
	// TODO: Need some error detection for invalid arguments
	for(int i = 0; i != args.length; i++) {
		if(args[i].startsWith("-")) {
			String arg = args[i];
			if(arg.equals("-help")) {
				// Go into a usage menu.
			}
		}else {
			fileName = args[0];
			timeLimit = Integer.parseInt(args[1]);
				// Should be filename(map), 1,2,3,4,5 for time limits in minutes
		}
	}
	//startGame(fileName,timeLimit);
	startGame();
	}
	
	public static void startGame(){
		Application.GUI gui = new GUI();
		
		
		// Main game loop here?
		
	}
	
	
	
	
	// Map argument
	
	
	// Time-limit argument (Complete the game in set time)
	// Cheats on/off
		
	
		
		
	// Main game loop
	// Do we need a tick rate? Game always running feel or game is reacting to command feel.
	
	
	}
	