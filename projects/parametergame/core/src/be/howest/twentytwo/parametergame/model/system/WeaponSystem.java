package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;

public class WeaponSystem extends IntervalIteratingSystem {

	public static final int PRIORITY = 1;

	public Collection<IPhysicsMessage> events;

	public WeaponSystem(Collection<IPhysicsMessage> events) {
		super(Family.all(WeaponComponent.class, BodyComponent.class).get(),
				PhysicsSystem.PHYSICS_TIMESTEP, PRIORITY);
		this.events = events;
	}

	@Override
	protected void processEntity(Entity entity) {
		Body body = BodyComponent.MAPPER.get(entity).getBody();
		WeaponComponent wc = WeaponComponent.MAPPER.get(entity);
		// TODO: Do weapon stuff
	}

}
