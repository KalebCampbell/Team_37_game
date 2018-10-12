package Persistence2;

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

}