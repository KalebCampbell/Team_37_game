package Persistence2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoomComponent {
	
	private String roomid;
	private String location;
	private String wall;
	private String door;
	private String item;
	
	
	public RoomComponent(String roomid, String location, String wall, String item, String door) {
		this.roomid = roomid;
		this.location = location;
		this.wall = wall;
		this.item = item;
		this.door = door;
	}
	
}