package Persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "room")
/**
 * @author kalebcampbell
 * 
 * A RoomComponent is where the infomation from each room is stored 
 * and all rooms are then stored in a RoomsComponent
 */
public class RoomComponent {
	
	private int id;
	private int locX;
	private int locY;
	List<String> walls = new ArrayList<>();
	List<ItemComponent> items = new ArrayList<>();

	public RoomComponent(int id, int locX, int locY, List<String> walls,
			List<ItemComponent> items) {
		this.id = id;
		this.locX = locX;
		this.locY = locY;
		this.walls = walls;
		this.items = items;
	}
	//To be marshalled the objects all need an empty constructor. 
	public RoomComponent() {	
	}
	
	public int getId() {
		return id;
	}
	
	//@XmlElement is needed for all setter emthods to be able to be marshalled. 
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	
	public int getLocX() {
		return locX;
	}
	
	//@XmlElement is needed for all setter emthods to be able to be marshalled. 
	@XmlElement
	public void setLocX(int locX) {
		this.locX = locX;
	}
	
	public int getLocY() {
		return locY;
	}
	
	//@XmlElement is needed for all setter emthods to be able to be marshalled. 
	@XmlElement
	public void setLocY(int locY) {
		this.locY = locY;
	}
	
	public List<String> getWalls() {
		return walls;
	}
	
	//@XmlElement is needed for all setter emthods to be able to be marshalled. 
	@XmlElement
	public void setWalls(List<String> walls) {
		this.walls = walls;
	}
	
	public List<ItemComponent> getItems() {
		return items;
	}
	
	//@XmlElement is needed for all setter emthods to be able to be marshalled. 
	@XmlElement
	public void setItems(List<ItemComponent> items) {
		this.items = items;
	}
	
	
}
