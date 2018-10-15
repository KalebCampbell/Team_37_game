package GameWorld;

// Work in progress

public class DoorStrategy extends Wall{
	
	public interface Strategy {
		public boolean isLocked();
		public boolean isJammed();
	}

	/**
	 * Strategy pattern for door
	 */
	DoorStrategy.Strategy strategy;
	
	public DoorStrategy(int Location) {
		super(Location);
	}

	
}
