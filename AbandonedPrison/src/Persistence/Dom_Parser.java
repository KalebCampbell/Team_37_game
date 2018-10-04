package Persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import GameWorld.Player;
import GameWorld.Room;
import GameWorld.Wall;

public class Dom_Parser {



	public static void main(String[] args) {
	
		
		//North = 0 
		//East = 1
		//West = 2
		//South = 3
		
		
		//Character player = new Character();
		//player.addLocation(0, 0);
		 
			
		Room room  = new Room();
		room.setRoomID(0);
		//room.setLocation(0,0);
		//room.hasPlayer(true);
		
		List<Integer> walls = new ArrayList<>();
		walls.add(1);
		walls.add(2);
		walls.add(3);
		
		//the item needs to have the name of the item and the location. 
		//List<Item> items = new ArrayList<>();
		//items.add(Key, 0, 0);
		
		
		//sets the walls of the room to an array of [0, 1, 2, 3,] depending on the walls. 
		room.setWalls(walls);
		
		//room.setItems(items);
		xmlRooms(room);
	}
	public static void xmlRooms(Room room) {
		try {
			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Room.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			//output printed pretty
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			jaxbMarshaller.marshal(room, file);
			jaxbMarshaller.marshal(room, System.out);
			
		 } catch (JAXBException e) {
				e.printStackTrace();
		 	}
	}
}