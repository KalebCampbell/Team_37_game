package Persistence2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemComponent {

	int posX;
	int posY;
	String item;
	public ItemComponent() {
		
	}
	public ItemComponent(int posX, int posY, String item) {
		this.posX = posX;
		this.posY = posY;
		this.item = item;
	}

	public int getPosX() {
		return posX;
	}
	@XmlElement
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}
	@XmlElement
	public void setPosY(int posY) {
		this.posY = posY;
	}

	public String getItem() {
		return item;
	}
	@XmlElement
	public void setItem(String item) {
		this.item = item;
	}
	
	
	
}