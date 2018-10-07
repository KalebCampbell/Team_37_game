package Application;

public class GUI {
	
	private Window window;
	
	public GUI(Window window) {
		this.window = window;
	}
	
	//main method for testing
	public static void main(String[] args) {
		new Window(700,760,"Abandoned Prison");
	}

	public void init() {
		
	}
	
	//Handle events in GUI receiving the events from the window 
	

}
