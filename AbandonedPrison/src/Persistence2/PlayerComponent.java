package Persistence2;

import GameWorld.Location;

public class PlayerComponent {

	private String id;
	private String roomid;
	private String name;
	private String location;

	public PlayerComponent(String id, String name, String roomid, String location) {
		this.id = id;
		this.roomid = roomid;
		this.name = name;
		this.location = location;
	}

	public String getId() {
		return id;		
	}

	public String getName() {
		return name;
	}
	
	public String getRoomId() {
		return roomid;
	}

	public Location getLocation() {
		
		String[] arr = this.location.split(",");	
		int x =Integer.parseInt(arr[0]);
		int y = Integer.parseInt(arr[1]);
		return new Location(x,y);
	}

}