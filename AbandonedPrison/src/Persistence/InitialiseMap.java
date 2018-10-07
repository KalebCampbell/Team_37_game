package Persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InitialiseMap {
		
	static RoomsComponent mapRooms = new RoomsComponent();
	static {
		mapRooms.setRooms(new ArrayList<RoomComponent>());
		
		//===============room one===============
		RoomComponent room1  = new RoomComponent();
		room1.setId(1);
		room1.setLocX(0);
		room1.setLocY(0);
		room1.setHasPlayer(true);
		//list of walls
		List<Integer> walls1 = new ArrayList<>();
		walls1.add(1);
		walls1.add(2);
		walls1.add(3);
		//list of items
		List<ItemComponent> items1 = new ArrayList<>();
		items1.add(new ItemComponent(0, 0, "null"));
		room1.setWalls(walls1);
		room1.setItems(items1);
		
		//===============room two===============
		RoomComponent room2  = new RoomComponent();
		room2.setId(2);
		room2.setLocX(0);
		room2.setLocY(0);
		room2.setHasPlayer(false);
		//list of walls
		List<Integer> walls2 = new ArrayList<>();
		walls2.add(1);
		walls2.add(2);
		walls2.add(3);
		//list of items
		List<ItemComponent> items2 = new ArrayList<>();
		items2.add(new ItemComponent(0, 0, "null"));
		room2.setWalls(walls2);
		room2.setItems(items2);
		
		mapRooms.getRooms().add(room1);
		mapRooms.getRooms().add(room2);
		
	}
	public static void main(String[] args) throws JAXBException{
		toXml();
	}
	private static void toXml()  throws JAXBException{
		
		JAXBContext jaxbContext = JAXBContext.newInstance(RoomsComponent.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    
	    jaxbMarshaller.marshal(mapRooms, System.out);
	    jaxbMarshaller.marshal(mapRooms, new File("employees.xml"));
	}
}
