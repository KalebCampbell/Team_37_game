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
	
	
	public PlayerComponent() {	
	}
	
	public PlayerComponent(String id, String roomid, String name, String location) {
		this.id = id;
		this.roomid = roomid;
		this.name = name;
		this.location = location;
	}
	
	/**
	 * Returns the id of the player.
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id of the player.
	 *  
	 * @param id
	 */
	@XmlElement
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Returns the id of the room the player is currently in. 
	 * 
	 * @return roomid
	 */
	public String getRoomid() {
		return roomid;
	}
	/**
	 * Sets the roomid of the player.
	 * @param roomid
	 */
	@XmlElement
	public void setRoomid(String roomid) {
		this.roomid = roomid;
	}
	
	/**
	 * Returns the name of the player.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the player.
	 * 
	 * @param name
	 */
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the coordinates of the player by spliting it by "," and turning 
	 * it into a location.
	 * 
	 * @return Location		The location of the player
	 */
	public Location getCord() {
		String[] arr = this.location.split(",");	
		int x =Integer.parseInt(arr[0]);
		int y = Integer.parseInt(arr[1]);
		return new Location(x,y);
	}
	
	/**
	 * Returns the location of the player. 
	 * 
	 * @return location 
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location of the player. 
	 * 
	 * @param location
	 */
	@XmlElement
	public void setLocation(String location) {
		this.location = location;
		
	}


	
}
