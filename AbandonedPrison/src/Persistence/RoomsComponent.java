package Persistence;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rooms")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomsComponent {

	@XmlElement(name = "room")
	private List<RoomComponent> rooms = null;

	public List<RoomComponent> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomComponent> rooms) {
		this.rooms = rooms;
	}
}
