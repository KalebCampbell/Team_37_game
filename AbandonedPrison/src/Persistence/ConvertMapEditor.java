package Persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import GameWorld.Container;
import GameWorld.Door;
import GameWorld.Game;
import GameWorld.Item;
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
	public ConvertMapEditor(List<Room> rooms, File file){
		
		RoomsComponent roomMap = new RoomsComponent();
		roomMap.setRooms(new ArrayList<RoomComponent>());
		List<ItemComponent> items = new ArrayList<>();
		List<DoorComponent> doors = new ArrayList<>();
		for(Room room :rooms) {
			int i = 0;
			
			for(int k = 0; k < room.getitems().size(); k++) {
				items.add(new ItemComponent(room.getX()/60, room.getY()/60, room.getitems().get(k), i));
			}
			
			for(int k = 0; k < room.getdoors().size(); k++) {
				doors.add(new DoorComponent("Wooden", i, room.getwalls().get(k), true));
			}
			roomMap.Rooms().add(new RoomComponent(i, room.getX()/60, room.getY()/60, room.getwalls(), null,items, null));
			
			i ++;
		}
		
		//SETS STUFF NOT RELEVANT FOR SAVE FROM MAPEDITOR
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
			toXml(gameMap, file);
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

		
		//iterates through all the rooms and translates them all into roomComponents and then put into a roomsComponent.
		RoomsComponent roomMap = new RoomsComponent();
		for( GameWorld.Room room : game.getRooms()) {
			int i = 0;
			
			List<ItemComponent> items= new ArrayList<>();
			List<String> walls = new ArrayList<String>();
			List<DoorComponent> doors = new ArrayList<>();
			List<ContainerComponent> containers = new ArrayList<>();
			
			//iterate over the items 
			for(int k = 0; k < room.getItems().size(); k++) {
				Item currentItem = room.getItems().get(k);
				items.add(new ItemComponent(currentItem.getItemLocation().getX(), currentItem.getItemLocation().getY(), currentItem.getItemName(),currentItem.getItemId()));
			}
			//iterate over the walls
			for(int k = 0; k < room.getWalls().size(); k++) {
				walls.add(room.getWalls().get(k));
			}		
			//iterate over the doors 
			for(int k = 0; k < room.getDoors().size(); k++) {
				Door currentRoom = room.getDoors().get(k);
				//check that this works
				doors.add(new DoorComponent(currentRoom.getType(), currentRoom.getId(), currentRoom.getDirection(), currentRoom.isLocked()));
			}
			//iiterate over containers 
			for(int k = 0; k < room.getContainer().size(); k++) {
				Container currentContainer = room.getContainer().get(k);
				containers.add(new ContainerComponent(currentContainer.getcontainerName(), currentContainer.getContainerLocation().getX(), currentContainer.getContainerLocation().getY(), currentContainer.getId(), "locked"));
			}

			roomMap.Rooms().add(new RoomComponent(room.getRoomID(), room.getLocation().getX(), room.getLocation().getY(), walls, doors, items, containers));
			
			
		}
		
		//Player player = new PlayerComponent(game.getPlayer().getPlayerId(), game.getPlayer().getRoomId(), game.getPlayer().getPlayerName(), game.getPlayer().getPlayerLocation());
		//Player playerlol = new PlayerComponent(id, roomid, name, location)
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
	public static void toXml(GameMapComponent game, File file)  throws JAXBException{
		
		
		//Creates the marshaller.
		JAXBContext jaxbContext = JAXBContext.newInstance(GameMapComponent.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    
	    //Makes the XML file properly indentated and foramtted. 
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    
	    //Prints the XML to the console and into a new file.
	    jaxbMarshaller.marshal(game, System.out);
	    //uncomment to make a new file saved. 
	    jaxbMarshaller.marshal(game, file);
	}
}
