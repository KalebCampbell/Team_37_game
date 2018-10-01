package GameWorld;

/**
 * Abstract class representing a character in the game world
 * @author Michael Vincent
 *
 */
public abstract class Character {
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	
	private int dirFacing;
	

	/** Method to get which direction a character is currently facing.
	 * 
	 * @return Direction character is facing.
	 */
	public int getDirFacing() {
		return dirFacing;
	}
	
	/** Method to set which direction a character is facing.
	 * @param direction
	 */
	public void setDirFacing(int direction) {
		dirFacing = direction;
	}
	
	
}