package Persistence;

import java.io.File;

import javax.swing.JFileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LoadXml {

	public static GameMapComponent unMarshal(File file) throws JAXBException {
		
		
		//check in here to make sure that the file is correct. 
		
		
	    JAXBContext jaxbContext = JAXBContext.newInstance(GameMapComponent.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	     
	    GameMapComponent gameMap = (GameMapComponent) jaxbUnmarshaller.unmarshal(file);
	    return gameMap;

//	    System.out.println("=======");
//	    //How to access all inventory items
//	    for(int i = 0; i < gameMap.getInventory().size(); i ++) {
//	    	System.out.println(gameMap.inventory.get(i));
//	    }
//	    
//	    
//	    System.out.println("=======");
//	    //An Example of access all room items.
//	    for(RoomComponent room : gameMap.getRooms().Rooms()) {
//	    
//	   // drawRoom(room.getX(), room.getY(), 
//	    		System.out.println(room.getId());
//	    		System.out.println(room.getLocX());
//	    		System.out.println(room.getLocY());
//	    		for(int i = 0; i < room.getWalls().size(); i++) {
//	    			System.out.println(room.getWalls().get(i));
//	    		}
//	    		for(int i = 0; i < room.getDoors().size(); i++) {
//	    		
//	    		}
//	    		for(ItemComponent item : room.getItems()) {
//	    			
//	    			System.out.println(item.getItem());
//	    			
//	    		}
//	    		System.out.println("=======");
//	    }
//	   
//	    System.out.println(gameMap.player.getLocation());
	    //return gameMap;
	}
}
