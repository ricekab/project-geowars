package be.howest.twentytwo.parametergame.model.system;

import com.badlogic.ashley.systems.IntervalSystem;

public class SpawnSystem extends IntervalSystem{
	
	public static final int PRIORITY = 1;

	public SpawnSystem(float interval) {
		super(interval, PRIORITY);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void updateInterval() {
		// TODO: Process spawn messages
	}
}