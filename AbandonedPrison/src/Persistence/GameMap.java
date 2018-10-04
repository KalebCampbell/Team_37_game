package Persistence;

// Michael Vincent

// Parse everything from the XML file into easy to manage MapComponent objects
// Then an Object which is the GameMap can be sent to application
// Converted into a Game object by Application <-> GameWorld
// GameWorld expects an GameMap object which has fields about every component of the map
// GameWorld will convert all the GameMap objects into Game Objects ready to be used with their own algorithms

// Example: ArrayList<MapComponent> components = new ArrayList<MapComponent>();
// 			// Classes:
			// RoomMap extends MapComponent
					// Fields: roomID, ArrayList<RoomObject>, location, walls[]
					// methods: get roomID, set roomID
					// methods: get location, set location 
					// methods: get walls, set walls

			// RoomObject extends MapComponent
					// Fields: componentID, location, roomID(Which room it's in)
					// methods: get componentID, set componentID
					// methods: get location, set location
					// methods: get roomID, set roomID - which room it's in.
			


// This need to have decent error detection too:
// From assignment brief: HINT: The persistence package should be robust and report errors for incorrectly formed map
//						 and game world files.
// A class that checks validity of maps:
// Class mapValidity:
	// Does a pass on the MapComponents, and checks for things like; item, player, object out of bounds
	// Checks things like, room doesn't have 5 walls or 5 doors
	// Basic checks that ensure that the game file is valid to be processed by GameWorld

public class GameMap {

}
