package be.howest.twentytwo.parametergame.model.time;

import be.howest.twentytwo.parametergame.model.component.TimedLifeComponent;

/**
 * Interface that exposes something to be executed upon completion of a {@link TimedLifeComponent}.
 */
public interface ITimeoutCallback {

	public void execute();
}
