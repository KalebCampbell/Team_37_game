package GameWorld;

public class DoorStrategy extends Wall{
	public interface Strategy {

	}

	/**
	 * Strategy pattern for door
	 */
	DoorStrategy.Strategy strategy;
	
	public DoorStrategy(int Location) {
		super(Location);
	}

	
}
