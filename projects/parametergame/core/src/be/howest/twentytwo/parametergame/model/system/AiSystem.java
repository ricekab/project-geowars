package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.AIComponentI;
import be.howest.twentytwo.parametergame.model.component.AISuiciderComponent;
import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import be.howest.twentytwo.parametergame.model.physics.events.LinearForceEvent;
import be.howest.twentytwo.parametergame.model.physics.events.AngularForceEvent;
import be.howest.twentytwo.parametergame.utils.VectorMath;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.systems.IteratingSystem;

public class AiSystem extends IteratingSystem {

	public final static int PRIORITY = 1;

	public Collection<IPhysicsEvent> events;
        
	public AiSystem(Collection<IPhysicsEvent> events) {
		super(Family.all(AISuiciderComponent.class).get(), PRIORITY);
		this.events = events;
	}

    @Override
    protected void processEntity(Entity entity, float f) {
        //Gdx.app.log("AISystem", String.format(""));
            AISuiciderComponent aiComp = AISuiciderComponent.MAPPER.get(entity);
            aiComp.ProcessAI(entity, events);

    }
}
