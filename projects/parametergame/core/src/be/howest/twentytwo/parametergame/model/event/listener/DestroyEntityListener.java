package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.game.DestroyEntityEvent;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;

public class DestroyEntityListener implements IEventListener{

	private final PooledEngine engine;
	
	public DestroyEntityListener(PooledEngine engine) {
		this.engine = engine;
	}
	
	@Override
	public void handle(IEvent event) {
		DestroyEntityEvent evt = (DestroyEntityEvent) event;
		Gdx.app.debug("DestroyEntityListener", "Destroying entity " + evt.getEntity().toString());
		for(Component c : evt.getEntity().getComponents()){
			Gdx.app.debug("DestroyEntityListener", c.toString());
		}
		evt.getEntity().removeAll();
		engine.removeEntity(evt.getEntity());
	}

}
