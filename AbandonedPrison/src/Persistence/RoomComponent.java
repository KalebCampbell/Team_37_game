package Persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoomComponent {
	
	private int id;
	private boolean hasPlayer;
	private int locX;
	private int locY;
	List<Integer> walls = new ArrayList<>();
	List<ItemComponent> items = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	public boolean getHasPlayer() {
		return hasPlayer;
	}
	@XmlElement
	public void setHasPlayer(boolean hasPlayer) {
		this.hasPlayer = hasPlayer;
	}
	public int getLocX() {
		return locX;
	}
	@XmlElement
	public void setLocX(int locX) {
		this.locX = locX;
	}
	public int getLocY() {
		return locY;
	}
	@XmlElement
	public void setLocY(int locY) {
		this.locY = locY;
	}
	public List<Integer> getWalls() {
		return walls;
	}
	@XmlElement
	public void setWalls(List<Integer> walls) {
		this.walls = walls;
	}
	public List<ItemComponent> getItems() {
		return items;
	}
	@XmlElement
	public void setItems(List<ItemComponent> items) {
		this.items = items;
	}
	
	
}
