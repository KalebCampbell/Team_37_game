package Persistence;

import java.util.ArrayList;
import java.util.List;

import MapEditor.GUI;
import MapEditor.Room;

public class SaveXml{

	public SaveXml(List<Room> rooms){
		
		RoomsComponent mapNew = new RoomsComponent();
		mapNew.setRooms(new ArrayList<RoomComponent>());
	
		for(Room room :rooms) {
			int i = 0; 
			for(int k = 0; k < room.getitems().size(); k++) {
				room.getitems().get(k);
			}
			mapNew.Rooms().add(new RoomComponent(i, room.getX(), room.getY(), room.getwalls(), ));
			i ++;
		}
		
		
		
	}
}
