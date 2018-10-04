package GameWorld;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Room {
	
	private int roomID;
	private List<Wall> walls = new ArrayList <Wall>();
	private List<Item> items = new ArrayList <Item>();
	
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	
	
	public List<Wall> getWalls() {
		return walls;
	}
	@XmlElement
	public void setWalls(List<Wall> walls) {
		this.walls = walls;
	}
	
	public int getRoomID() {
		return roomID;
	}	
	@XmlAttribute
	public void setRoomID(int id) {
		this.roomID = id;
	}

	/**
	 * Constructor for Room Objects
	 * @param roomID Identity of room
	 * @param walls List of all walls in the room
	 * 
	 */
	
	public Room() {
		
	}
	
	public Room (int roomID, List<Wall> walls){
		//this.roomID = roomID;
		//this.walls = walls;
		
		buildRoom();
		
	}
	
	public void buildRoom() {
		for(Wall w : walls) {
			w.getLocation();
		}
		
		
	}
	
	
	
	
}
