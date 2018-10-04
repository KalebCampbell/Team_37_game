package Persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import GameWorld.Room;
import GameWorld.Wall;

public class Dom_Parser {

	public static void main(String[] args) {
	
		Room room  = new Room();
		
		List<Wall> walls = new ArrayList<>();
		walls.add(new Wall(2));
		walls.add(new Wall(3));
		walls.add(new Wall(4));
		
		room.setRoomID(0);
		room.setWalls(walls);
	
		//room.setLocation(0,0);
	
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
