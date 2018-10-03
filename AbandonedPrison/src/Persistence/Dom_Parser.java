package Persistence;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Dom_Parser {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("map.xml");
			//puts all the different walls into a node list
			NodeList wallList = doc.getElementsByTagName("wall");		
			//iterates through all the walls in the xml file
			for(int i = 0; i < wallList.getLength(); i++) {
				//makes all the items in the list to Nodes
				Node p = wallList.item(i);
				if(p.getNodeType() == Node.ELEMENT_NODE) {
					//sets the Node p to an element wall
					Element wall = (Element) p;
					//sets the value id to the value id of the specific wall in the xml file
					String id = wall.getAttribute("id");
					NodeList directionType = wall.getChildNodes();
					for(int k = 0; k < directionType.getLength(); k++) {
						//can use if statements in here to get specific value from the specific node type
						Node direc = directionType.item(k);
						if(direc.getNodeType() == Node.ELEMENT_NODE) {
							Element direction = (Element) direc;
							System.out.println("Wall " + id + ": " + direction.getTagName() + 
									" = " + direction.getTextContent());
						}
					}
				}
			}
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
