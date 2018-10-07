package Persistence2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlayerComponent {

	int posX;
	int posY;
	String name;
	List<String> playerInventory = new ArrayList<String>();

	public PlayerComponent(int posX, int posY, String name, List<String> inventory) {
		this.posX = posX;
		this.posY = posY;
		this.name = name;
		this.playerInventory = inventory;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public String getName() {
		return name;
	}
	
	public List<String> getInventory(){
		return this.playerInventory;
	}

	
	
	
}