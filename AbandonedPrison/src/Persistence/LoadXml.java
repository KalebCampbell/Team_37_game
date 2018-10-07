package Persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import GameWorld.Room;

public class LoadXml {

	public static void unMarshal() throws JAXBException {
		
	    JAXBContext jaxbContext = JAXBContext.newInstance(RoomsComponent.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	     
	    RoomsComponent rooms = (RoomsComponent) jaxbUnmarshaller.unmarshal( new File("StartingMap.xml") );
	    
	    		List<Room> listRooms = new ArrayList<>();
	    for(RoomComponent room : rooms.getRooms()) {
	    		
	    		System.out.println("=========");
	    		System.out.println(room.getId());
	    		System.out.println("=========");
	    		
	    		//call the methods to make the objects.?
	    		//make a list of rooms and pass them to be drawn. 
	    		//listRooms.add(new Room(room.getId(), room.getLocX(), room.getLocY(), room.getWalls()))
	    		
	    		//how to get individual item
	    		System.out.println(room.getItems().get(0).item);
	    		System.out.println(room.getItems().get(0).posX);
	    		System.out.println(room.getItems().get(0).posY);
	    		
	    		//prints [1, 2, 3]
	    		System.out.println(room.getWalls());
	    }
	    
	}
}
