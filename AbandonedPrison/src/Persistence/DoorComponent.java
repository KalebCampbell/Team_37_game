package Persistence;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "door")
public class DoorComponent {
	

	int id; 
	String direction;
	boolean locked;
	String door;
	
	//To be marshalled the objects all need an empty constructor. 
	public DoorComponent(){	
	}
	
	
	public DoorComponent(String door,int id, String direction, boolean locked) {
		
		this.id = id;
		this.direction = direction;
		this.locked = locked;
		this.door = door;
	}
	

	public String getDoor() {
		return door;
	}
	@XmlElement
	public void setDoor(String door) {
		this.door = door;
	}
	
	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDirection() {
		return direction;
	}
	@XmlElement
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public boolean isLocked() {
		return locked;
	}
	@XmlElement
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
