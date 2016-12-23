package be.howest.twentytwo.parametergame.model.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.EnemyComponent;
import be.howest.twentytwo.parametergame.model.component.HealthComponent;
import be.howest.twentytwo.parametergame.model.component.PlayerComponent;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.game.DestroyEntityEvent;
import be.howest.twentytwo.parametergame.model.event.game.EnemyKilledEvent;
import be.howest.twentytwo.parametergame.model.event.game.PlayerKilledEvent;
import be.howest.twentytwo.parametergame.utils.VectorMath;

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
		if (hp.getHealthData().getHealth() < VectorMath.EPSILON) {
			if (PlayerComponent.MAPPER.get(entity) != null) {
				Gdx.app.debug("HealthSys", "Player died: " + hp.getHealthData().getHealth());
				events.send(new PlayerKilledEvent());
			} else {
				Gdx.app.debug("HealthSys", "Enemy died: " + hp.getHealthData().getHealth());
				EnemyComponent ec = EnemyComponent.MAPPER.get(entity);
				BodyComponent bc = BodyComponent.MAPPER.get(entity);
				events.send(new EnemyKilledEvent(bc.getBody().getPosition(), ec.getScoreValue(), ec.getGeomDropRate()));
				events.send(new DestroyEntityEvent(entity));
			}
		}
	}

}
