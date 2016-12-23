package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.HealthComponent;
import be.howest.twentytwo.parametergame.model.component.PlayerComponent;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.game.EnemyKilledEvent;
import be.howest.twentytwo.parametergame.model.event.game.PlayerKilledEvent;
import be.howest.twentytwo.parametergame.utils.VectorMath;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;

public class HealthSystem extends IteratingSystem {

	public static final int PRIORITY = 0;

	private final EventQueue events;
	
	public HealthSystem(EventQueue eventQueue) {
		super(Family.all(HealthComponent.class, BodyComponent.class).get(), PRIORITY);
		this.events = eventQueue;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		HealthComponent hp = HealthComponent.MAPPER.get(entity);
		if(hp.getHealthData().getHealth() < VectorMath.EPSILON){
			if(PlayerComponent.MAPPER.get(entity) != null){
				events.send(new PlayerKilledEvent());
			} else{
				events.send(new EnemyKilledEvent());
			}
		}
	}

}
