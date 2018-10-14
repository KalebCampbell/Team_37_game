package Persistence;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LoadXml {

	public static GameMapComponent unMarshal() throws JAXBException {
		
	    JAXBContext jaxbContext = JAXBContext.newInstance(GameMapComponent.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	     
	    GameMapComponent gameMap = (GameMapComponent) jaxbUnmarshaller.unmarshal( new File("StartingMap.xml") );
	    
	    
	    //An Example of access all room items.
	    for(RoomComponent room : gameMap.getRooms().Rooms()) {
	    		System.out.println(room.getId());
	    		for(ItemComponent item : room.getItems()) {
	    			
	    			System.out.println(item.getItem());
	    			
	    		}
	    		System.out.println("=======");
	    }
	
	    
	    return gameMap;
	}
}
