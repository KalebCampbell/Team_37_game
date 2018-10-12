package Persistence2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import GameWorld.Location;

public class RoomComponent {
	
	private String roomid;
	private String location;
	private String wall;
	private String door;
	private String item;
	private ArrayList<String> walls = new ArrayList<String>();
	private ArrayList<String> doors = new ArrayList<String>();
	private ArrayList<String> items = new ArrayList<String>();
	
	
	public RoomComponent(String roomid, String location, String wall, String item, String door) {
		this.roomid = roomid;
		this.location = location;
		this.wall = wall;
		this.item = item;
		this.door = door;
	}
	
	public String getRoomid() {
		return roomid;
	}
	public Location getLocation() {
		
		String[] arr = this.location.split(",");	
		int x =Integer.parseInt(arr[0]);
		int y = Integer.parseInt(arr[1]);
		return new Location(x,y);
	}
	
	// Spaghetti
	public ArrayList<String> getWall() {
		
		String[] wallsArr = this.wall.split(",");
		
		for(String s : wallsArr) {
			walls.add(s);
		}
		return walls;
	}
	public ArrayList<String> getDoor() {
		String[] doorArr = this.door.split(",");
		
		for(String s : doorArr) {
			doors.add(s);
		}
		return doors;
		
	}
	public ArrayList<String> getItem() {
		String[] itemArr = this.item.split(",");
		
		for(String s : itemArr) {
			items.add(s);
		}
		
		return items;
	}
	
}