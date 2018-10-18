package Persistence;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "item")
/**
 * @author kalebcampbell
 * 
 * An ItemComponent holds the x and y position in which it is in its rooms
 *  and the item name of which it is.
 *
 */
public class ItemComponent {

	int posX;
	int posY;
	int id;
	String item;
	
	//To be marshalled the objects all need an empty constructor. 
	public ItemComponent() {
	}
	public ItemComponent(int posX, int posY, String item, int id) {
		this.posX = posX;
		this.posY = posY;
		this.item = item;
		this.id = id; 
	}

	public int getPosX() {
		return posX;
	}
	//@XmlElement is needed for all setter methods to be able to be marshalled. 
	@XmlElement
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}
	//@XmlElement is needed for all setter methods to be able to be marshalled. 
	@XmlElement
	public void setPosY(int posY) {
		this.posY = posY;
	}

	public String getItem() {
		return item;
	}
	//@XmlElement is needed for all setter methods to be able to be marshalled. 
	@XmlElement
	public void setItem(String item) {
		this.item = item;
	}
	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
