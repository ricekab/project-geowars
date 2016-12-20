package be.howest.twentytwo.parametergame.model.component;

import be.howest.twentytwo.parametergame.model.time.ITimeoutCallback;
import be.howest.twentytwo.parametergame.model.time.NullTimeout;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool.Poolable;

/** Represents a time life for the entity it is attached to. */
public class TimedLifeComponent implements Component, Poolable {

	public static final ComponentMapper<TimedLifeComponent> MAPPER = ComponentMapper
			.getFor(TimedLifeComponent.class);

	private float timeRemaining;
	private ITimeoutCallback callback;
	private boolean finished;

	public TimedLifeComponent() {
		reset();
	}

	public float getTimeRemaining() {
		return timeRemaining;
	}

	public ITimeoutCallback getCallback() {
		return callback;
	}

	public void setTimeRemaining(float timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public void setCallback(ITimeoutCallback callback) {
		this.callback = callback;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished() {
		this.finished = true;
	}


	@Override
	public void reset() {
		setTimeRemaining(0f);
		setCallback(new NullTimeout());
		this.finished = false;
	}
}
