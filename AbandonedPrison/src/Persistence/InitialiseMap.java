package Persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InitialiseMap {
		
	//Creates the original player object
	static PlayerComponent player = new PlayerComponent();
	static {
		player.setId("0");
		player.setLocation("30");
		player.setName("TestName");
		player.setRoomid("0");
	}
	
	//Creates a RoomsComponent called map which holds all the differetn initial room layouts. 
	static RoomsComponent map = new RoomsComponent();
	static {
		map.setRooms(new ArrayList<RoomComponent>());
		
		//layout for how to initilaise the starting map layout for the rooms. 
		//list of walls
				List<String> walls1 = new ArrayList<>();
				walls1.add("E");
				walls1.add("S");
				walls1.add("W");
		//list of items
				List<ItemComponent> items1 = new ArrayList<>();
				items1.add(new ItemComponent(0, 0, "null"));
		RoomComponent room1  = new RoomComponent(1, 0, 0, walls1, items1);
		//Room 2.
		List<String> walls2 = new ArrayList<>();
			walls2.add("E");
			walls2.add("W");
		List<ItemComponent> items2 = new ArrayList<>();
			items2.add(new ItemComponent(0, 0, "null"));
		RoomComponent room2  = new RoomComponent(2, 0, 1, walls2, items2);
		//Room 3.
		List<String> walls3 = new ArrayList<>();
			walls3.add("N");
			walls3.add("W");
		List<ItemComponent> items3= new ArrayList<>();
			items3.add(new ItemComponent(0, 0, "null"));
		RoomComponent room3  = new RoomComponent(3, 0, 2, walls3, items3);
		//Room 4.
		List<String> walls4 = new ArrayList<>();
			walls4.add("N");
			walls4.add("S");
		List<ItemComponent> items4= new ArrayList<>();
			items4.add(new ItemComponent(0, 0, "null"));
		RoomComponent room4  = new RoomComponent(4, 1, 2, walls4, items4);
		//Room 5.
		List<String> walls5 = new ArrayList<>();
			walls5.add("N");
			walls5.add("E");
			walls5.add("S");
		List<ItemComponent> items5= new ArrayList<>();
			items5.add(new ItemComponent(0, 0, "null"));
			items5.add(new ItemComponent(1, 1, "Key"));
		RoomComponent room5  = new RoomComponent(5, 2, 2, walls5, items5);
		
		//gets the Rooms section and adds it. 
		map.Rooms().add(room1);
		map.Rooms().add(room2);
		map.Rooms().add(room3);
		map.Rooms().add(room4);
		map.Rooms().add(room5);
	}
	
	//This creates a gameMap object wich contains a Player object, a Rooms component 
	//and a list of Strings which is used as the inventory. 
	static GameMapComponent gameMap = new GameMapComponent();
	static {
		//Adds the player object created above and the map object created above. 
		gameMap.setPlayer(player);
		gameMap.setRooms(map);
		
		//Makes a list of Strings and then adds the starting inventory,
		//which will most likely be nothing at the start and adds it to the gameMap.
		ArrayList<String> inventory = new ArrayList<String>();
		inventory.add("Key");
		inventory.add(null);
		inventory.add(null);
		inventory.add(null);
		inventory.add(null);
		inventory.add(null);
		inventory.add(null);
		inventory.add(null);
		gameMap.setInventory(inventory);
		
	}
	
	/**
	 * @param args
	 * @throws JAXBException
	 * @author kalebcampbell
	 * 
	 * This method calls the toXml method.
	 */
	public static void main(String[] args) throws JAXBException{
		toXml();
		
		//This calls the LoadXml class to start unmarshalling. 
		LoadXml load = new LoadXml();
		load.unMarshal();
	}
	
	/**
	 * @throws JAXBException
	 * @author kalebcampbell
	 * 
	 * This method creates a jaxbContext and then creates a marshaller to convert the 
	 * gameMap obejct into an XML file named "Starting map"
	 */
	private static void toXml()  throws JAXBException{
		
		//Creates the marshaller.
		JAXBContext jaxbContext = JAXBContext.newInstance(GameMapComponent.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    
	    //Makes the XML file properly indentated and foramtted. 
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    
	    //Prints the XML to the console and into a new file name "StartingMap.xml"
	    jaxbMarshaller.marshal(gameMap, System.out);
	    jaxbMarshaller.marshal(gameMap, new File("StartingMap.xml"));
	}
}
