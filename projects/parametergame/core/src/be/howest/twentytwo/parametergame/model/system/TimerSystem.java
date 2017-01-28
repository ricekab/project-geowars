package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.model.component.TimedLifeComponent;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.game.DestroyEntityEvent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * System responsible for processing time delayed entities.
 */
public class TimerSystem extends IteratingSystem{

	public static final int PRIORITY = 0;
	private EventQueue events;
	
	public TimerSystem(EventQueue evtQueue) {
		super(Family.all(TimedLifeComponent.class).get(), PRIORITY);
		events = evtQueue;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		TimedLifeComponent tc = TimedLifeComponent.MAPPER.get(entity);
		tc.setTimeRemaining(tc.getTimeRemaining() - deltaTime);
		if(tc.getTimeRemaining() <= 0f && !tc.isFinished()){
			tc.getCallback().execute();
			tc.setFinished();
			events.send(new DestroyEntityEvent(entity));
		}
	}

}
