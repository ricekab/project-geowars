package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.component.AIBrutalizerComponent;
import be.howest.twentytwo.parametergame.model.component.AIComponent;
import be.howest.twentytwo.parametergame.model.component.AIObstacleComponent;
import be.howest.twentytwo.parametergame.model.component.AIScoutComponent;
import be.howest.twentytwo.parametergame.model.component.AISuiciderComponent;
import be.howest.twentytwo.parametergame.model.component.AISuiciderSquadComponent;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

public class AiSystem extends IteratingSystem {
	
	public static final int PRIORITY = 0;

	public AiSystem() {
		super(Family.all(AIComponent.class, BodyComponent.class, MovementComponent.class).get(), PRIORITY);
	}

	public Collection<IPhysicsEvent> events;

	public AiSystem(Collection<IPhysicsEvent> events) {
		super(Family.all(TransformComponent.class)
                        .one(AIScoutComponent.class, AISuiciderComponent.class, AIBrutalizerComponent.class, AIObstacleComponent.class, AISuiciderSquadComponent.class)
                        .get(), PRIORITY);
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
