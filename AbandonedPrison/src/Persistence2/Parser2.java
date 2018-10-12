package Persistence2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class Parser2{
	
	List<RoomComponent> rooms = new ArrayList<RoomComponent>();
	List<InventoryComponent> inventory = new ArrayList<InventoryComponent>();
	private PlayerComponent player;
	
	public Parser2(){
		
	}

		public GameMap setup(String mapName) throws ParserConfigurationException, SAXException, IOException {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db = dbf.newDocumentBuilder();
		    Document dom =  db.parse(new File(mapName));
		    Element docEle = dom.getDocumentElement();
		    
		    // GETS ALL THE NODES IN THE XML DOCUMENT
		    NodeList nl = docEle.getChildNodes();
		    
		    // IF THERE IS NODES
		    if (nl != null) {
		    	// GET HOW MANY NODES THERE ARE
		        int length = nl.getLength();
		        // ITERATE OVER NODES
		        for (int i = 0; i < length; i++) {
		        	// IF NODE TYPE IS AN ELEMENT NODE (ROOM)
		            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
		            	// CONVERT THE XML (ROOM) INTO AN ELEMENT OBJECT
		                Element el = (Element) nl.item(i);
		                
		                // IF THE ELEMENT NAME = ROOM
		                if (el.getNodeName().contains("room")) {
		                	
		                	// FIND SUBTAGS
		                    String roomid = el.getElementsByTagName("roomid").item(0).getTextContent();
		                    String location = el.getElementsByTagName("location").item(0).getTextContent();
		                    String wall = el.getElementsByTagName("wall").item(0).getTextContent();
		                    String item = el.getElementsByTagName("item").item(0).getTextContent();
		                    String door = el.getElementsByTagName("door").item(0).getTextContent();
		                    
		                    // CREATE ROOM
		                    Persistence2.RoomComponent rc = new Persistence2.RoomComponent(roomid,location,wall,item,door);
		                    // ADD ROOM
		                    rooms.add(rc);
		                }
		                
		                // IF ELEMENT NAME = PLAYER
		                if (el.getNodeName().contains("player")) {
		                	
		                	// FIND SUBTAGS
		                	String id = el.getElementsByTagName("id").item(0).getTextContent();
		                	String name = el.getElementsByTagName("name").item(0).getTextContent();
		                	String roomid = el.getElementsByTagName("roomid").item(0).getTextContent();
		                	String location = el.getElementsByTagName("location").item(0).getTextContent();
		                	
		                    // CREATE PLAYER
		                    PlayerComponent pc = new PlayerComponent(id,name,roomid,location);
		                    // ADD PLAYER
		                    this.player = pc;
		                }
		                
		                // IF ELEMENT NAME = PLAYER INVENTORY
		                if(el.getNodeName().contains("inventory")) {
		                	// FIND SUBTAGS
		                	String slot1 = el.getElementsByTagName("slot1").item(0).getTextContent();
		                	String slot2 = el.getElementsByTagName("slot2").item(0).getTextContent();
		                	String slot3 = el.getElementsByTagName("slot3").item(0).getTextContent();
	                    // CREATE INVENTORY
	                    InventoryComponent ic = new InventoryComponent(slot1,slot2,slot3);
	                    // ADD INVENTORY
	                    inventory.add(ic);
		                }
		            }
		        }
		    }
		    return createGameMap();
		}
		
	/**
	 * Creates gameMap from rooms,inventory,player
	 * @return GameMap object
	 */
	public GameMap createGameMap(){
		GameMap gm = new GameMap(rooms,inventory,player);	
		return gm;
	}
	
	
	
}
	

		



