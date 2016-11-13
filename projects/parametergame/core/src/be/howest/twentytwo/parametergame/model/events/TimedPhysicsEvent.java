package be.howest.twentytwo.parametergame.model.events;

import be.howest.twentytwo.parametergame.model.system.PhysicsSystem;

/**
 * Represents a physics event that happens for a period of time (multiple timesteps). In the event the provided time is
 * less than a normal time step a single fire event is assumed.
 */
public abstract class TimedPhysicsEvent implements IPhysicsEvent {

	private int remainingSteps;

	/**
	 * Creates a Timed Physics Event with specified number of engine steps. Note that {@link #TimedPhysicsEvent(float)}
	 * is the preferred method of instantiation since it's directly related to the timestep.
	 * 
	 * @param numberOfSteps
	 *            - Number of Physics engine time steps.
	 */
	public TimedPhysicsEvent(int numberOfSteps) {

	}

	public TimedPhysicsEvent(float timeInSeconds) {
		// Round to nearest number of physics time steps
		if(timeInSeconds < PhysicsSystem.PHYSICS_TIMESTEP) {
			this.remainingSteps = Math.round(timeInSeconds / PhysicsSystem.PHYSICS_TIMESTEP);	// TODO: Maybe want to round up?	
		} else{
			this.remainingSteps = 1;
		}
	}

	@Override
	public boolean isConsumed() {
		if(remainingSteps <= 0){
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
