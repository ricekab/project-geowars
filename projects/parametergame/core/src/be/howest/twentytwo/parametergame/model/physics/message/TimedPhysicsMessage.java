package be.howest.twentytwo.parametergame.model.physics.message;

import be.howest.twentytwo.parametergame.model.system.PhysicsSystem;

/**
 * Represents a physics event that happens for a period of time (multiple timesteps). In the event the provided time is
 * less than a normal time step a single fire event is assumed.
 */
public abstract class TimedPhysicsMessage implements IPhysicsMessage {

	private int remainingSteps;

	/**
	 * Creates a Timed Physics Event with specified number of engine steps. Note that {@link #TimedPhysicsEvent(float)}
	 * is the preferred method of instantiation since it's directly related to the time step.
	 * 
	 * @param numberOfSteps
	 *            - Number of Physics engine time steps.
	 */
	public TimedPhysicsMessage(int numberOfSteps) {
		this.remainingSteps = numberOfSteps;
	}

	/**
	 * Creates a Timed Physics Event with specified duration. There may be some deviation on the duration since physics
	 * steps are discrete.
	 * 
	 * @param timeInSeconds
	 *            - Time in seconds that this event is active. Gets rounded to nearest engine time step.
	 */
	public TimedPhysicsMessage(float timeInSeconds) {
		// Round to nearest number of physics time steps
		if(timeInSeconds < PhysicsSystem.PHYSICS_TIMESTEP) {
			this.remainingSteps = Math.round(timeInSeconds / PhysicsSystem.PHYSICS_TIMESTEP); 
			// TODO: Maybe want to round up?
		} else {
			this.remainingSteps = 1;
		}
	}

	@Override
	public boolean isConsumed() {
		if(remainingSteps <= 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean setConsumed() {
		remainingSteps = 0;
		return true;
	}

}
