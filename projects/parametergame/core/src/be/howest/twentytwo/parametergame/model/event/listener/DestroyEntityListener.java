package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.game.DestroyEntityEvent;

import com.badlogic.ashley.core.PooledEngine;

public class DestroyEntityListener implements IEventListener{

	private final PooledEngine engine;
	
	public DestroyEntityListener(PooledEngine engine) {
		this.engine = engine;
	}
	
	@Override
	public void handle(IEvent event) {
		DestroyEntityEvent evt = (DestroyEntityEvent) event;
		evt.getEntity().removeAll();
		engine.removeEntity(evt.getEntity());
	}

}
