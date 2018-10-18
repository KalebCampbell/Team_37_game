package Persistence;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "container")
/**
 * @author kalebcampbell
 * 
 * An ContainerComponent holds the x and y position in which it is in its rooms
 *  and the container name of which it is.
 *
 */
public class ContainerComponent {

	int posX;
	int posY;
	int id;
	String container;
	String locked;
	
	//To be marshalled the objects all need an empty constructor. 
	public ContainerComponent() {
	}
	public ContainerComponent(String container,int posX, int posY,int id,String locked) {
		this.container = container;
		this.posX = posX;
		this.posY = posY;  
		this.id = id;
		this.locked = locked; 
	}

	/**
	 * Get X position. 
	 * @return
	 */
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

	public String getContainer() {
		return container;
	}
	//@XmlElement is needed for all setter methods to be able to be marshalled. 
	@XmlElement
	public void setContainer(String container) {
		this.container = container;
	}
	public int getId() {
		return id;
	}
	//@XmlElement is needed for all setter methods to be able to be marshalled. 
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLocked() {
		return locked;
	}
	//@XmlElement is needed for all setter methods to be able to be marshalled. 
	@XmlElement
	public void setLocked(String locked) {
		this.locked = locked;
	}
	
	
	
}
