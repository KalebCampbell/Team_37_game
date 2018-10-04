package Persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Dom_Parser {



	public static void main(String[] args) {
	
		
		//creates a room and sets all the details for it to be created into XML format.
		RoomComponent room  = new RoomComponent();
		room.setId(0);
		room.setLocX(0);
		room.setLocY(0);
		room.getHasPlayer();
		
		//sets the walls for the wall arraylist to be added to the specific room.
		List<Integer> walls = new ArrayList<>();
		walls.add(1);
		walls.add(2);
		walls.add(3);
		
		//adds the walls to the room
		room.setWalls(walls);
		
		xmlRooms(room);
		
	}
	public static void xmlRooms(RoomComponent room) {
		try {
			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(RoomComponent.class);
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