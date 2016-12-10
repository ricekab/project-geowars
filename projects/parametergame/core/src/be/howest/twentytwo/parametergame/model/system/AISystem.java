package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;

import be.howest.twentytwo.parametergame.model.component.AIBrutalizerComponent;
import be.howest.twentytwo.parametergame.model.component.AIObstacleComponent;
import be.howest.twentytwo.parametergame.model.component.AIScoutComponent;
import be.howest.twentytwo.parametergame.model.component.AISuiciderComponent;
import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import com.badlogic.ashley.systems.IteratingSystem;

public class AiSystem extends IteratingSystem {

	public final static int PRIORITY = 1;

	public Collection<IPhysicsEvent> events;

	public AiSystem(Collection<IPhysicsEvent> events) {
		super(Family.all(AIScoutComponent.class).get(), PRIORITY);
		this.events = events;
	}

	@Override
	protected void processEntity(Entity entity, float f) {
		// Gdx.app.log("AISystem", String.format(""));

		if(AISuiciderComponent.MAPPER.has(entity)) {
			AISuiciderComponent suiciderComp = AISuiciderComponent.MAPPER.get(entity);
			suiciderComp.ProcessAI(entity, events);
		}

		if(AIScoutComponent.MAPPER.has(entity)) {
			AIScoutComponent scoutComp = AIScoutComponent.MAPPER.get(entity);
			scoutComp.ProcessAI(entity, events);
		}

		if(AIBrutalizerComponent.MAPPER.has(entity)) {
			AIBrutalizerComponent brutalizerComp = AIBrutalizerComponent.MAPPER.get(entity);
			brutalizerComp.ProcessAI(entity, events);
		}

		if(AIObstacleComponent.MAPPER.has(entity)) {
			AIObstacleComponent obstacleComp = AIObstacleComponent.MAPPER.get(entity);
			obstacleComp.ProcessAI(entity, events);
		}

	}
}
