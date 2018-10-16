package Persistence;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import GameWorld.Location;

@XmlRootElement(name = "player")
public class PlayerComponent {
	private String id;
	private String roomid;
	private String name;
	//x and y location. 
	private String location;
	
	public String getId() {
		return id;
	}
	//@XmlElement is needed for all setter emthods to be able to be marshalled. 
	@XmlElement
	public void setId(String id) {
		this.id = id;
	}
	public String getRoomid() {
		return roomid;
	}
	//@XmlElement is needed for all setter emthods to be able to be marshalled. 
	@XmlElement
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	
	
	public String getName() {
		return name;
	}
	//@XmlElement is needed for all setter emthods to be able to be marshalled. 
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public Location getCord() {
		String[] arr = this.location.split(",");	
		int x =Integer.parseInt(arr[0]);
		int y = Integer.parseInt(arr[1]);
		return new Location(x,y);
	}
	
	public String getLocation() {
		return location;
	}

	//@XmlElement is needed for all setter emthods to be able to be marshalled. 
	@XmlElement
	public void setLocation(String location) {
		this.location = location;
		
	}


	
}
