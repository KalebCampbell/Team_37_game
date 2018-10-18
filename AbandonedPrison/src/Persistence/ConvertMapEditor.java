package Persistence;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
				items.add(new ItemComponent(0, 0, room.getitems().get(k), 0));
			}
			//roomMap.Rooms().add(new RoomComponent(i, room.getX(), room.getY(), room.getwalls(),room.getdoors(), items, null ));
			i ++;
		}
	
		ArrayList<String> inventory = new ArrayList<String>();
		PlayerComponent player = new PlayerComponent();
		player.setId("0");
		player.setName("playerName");
		player.setLocation("00");
		player.setRoomid("0");
		
		//sets the gameMap
		GameMapComponent gameMap = new GameMapComponent();
		gameMap.setInventory(inventory);
		gameMap.setPlayer(player);
		gameMap.setRooms(roomMap);
		
		
		try {
			toXml(gameMap);
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
		
		RoomsComponent roomMap = new RoomsComponent();
		for( GameWorld.Room room : game.getRooms()) {
			int i = 0;
			
			//iterate over the items 
			
			
			
			
			
			
			//iterate over the walls
			
			
			
			
			
			//iterate over the doors 
			
			
			
			
			//iiterate over containers 
			//needs to be containers
		
			
			
			
			
			System.out.println(room.getLocation());
		}
		
//		try {
//			InitialiseMap.toXml(gameMap);
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
		
	}

	/**
	 * This method creates a jaxbContext and then creates a marshaller to convert the 
	 * gameMap obejct into an XML file named "Starting map"
	 * 
	 * @throws JAXBException
	 * @author kalebcampbell
	 */
	public static void toXml(GameMapComponent game)  throws JAXBException{
		
		//Creates the marshaller.
		JAXBContext jaxbContext = JAXBContext.newInstance(GameMapComponent.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    
	    //Makes the XML file properly indentated and foramtted. 
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    
	    //Prints the XML to the console and into a new file.
	    jaxbMarshaller.marshal(game, System.out);
	    //uncomment to make a new file saved. 
	    //jaxbMarshaller.marshal(game, new File("NewMap.xml"));
	}
}
