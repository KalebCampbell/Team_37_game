package Persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import GameWorld.Game;
import MapEditor.GUI;
import MapEditor.Room;

public class ConvertMapEditor{
	
	/**
	 * This converts a list of rooms passed in by the map editor
	 * to be transformed into a GameMapComponent. The the toXml() method is called 
	 * to pass in the newly created GameMapComponent to transform it into xml. 
	 * 
	 * @author kalebcampbell
	 * @param rooms	The list of Room objects from MapEditor
	 */
	public ConvertMapEditor(List<Room> rooms){
		
		RoomsComponent roomMap = new RoomsComponent();
		roomMap.setRooms(new ArrayList<RoomComponent>());
		List<ItemComponent> items= new ArrayList<>();
		for(Room room :rooms) {
			int i = 0;
			
			for(int k = 0; k < room.getitems().size(); k++) {
				items.add(new ItemComponent(0, 0, room.getitems().get(k)));
			}
			roomMap.Rooms().add(new RoomComponent(i, room.getX(), room.getY(), room.getwalls(),room.getdoors(), items ));
			i ++;
		}
	
		ArrayList<String> inventory = new ArrayList<String>();
		PlayerComponent player = new PlayerComponent();
		player.setId("0");
		player.setName("playerName");
		player.setLocation("00");
		player.setRoomid("0");
		
		GameMapComponent gameMap = new GameMapComponent();
		gameMap.setInventory(inventory);
		gameMap.setPlayer(player);
		gameMap.setRooms(roomMap);
		
		
		try {
			InitialiseMap.toXml(gameMap);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	/**
	 * The Game object from GameWorld is passed in and turned into a GameMapComponent.
	 * Then it is moved to the toXML method to be marshalled into XML.
	 * 
	 * @author kalebcampbell
	 * @param game
	 */
	public void ConvertGame(Game game) {
	
		
		
//		try {
//			InitialiseMap.toXml(gameMap);
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
		
	}
}
