package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.factory.ProjectileFactory;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;
import be.howest.twentytwo.parametergame.model.spawn.message.ISpawnMessage;
import be.howest.twentytwo.parametergame.model.spawn.message.SpawnProjectileMessage;
import be.howest.twentytwo.parametergame.utils.VectorMath;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class WeaponSystem extends IntervalIteratingSystem {

	public static final int PRIORITY = 1;

	public Collection<ISpawnMessage> spawnMessages;

	public WeaponSystem(Collection<ISpawnMessage> spawnMessages) {
		super(Family.all(WeaponComponent.class, BodyComponent.class).get(), PhysicsSystem.PHYSICS_TIMESTEP, PRIORITY);
		this.spawnMessages = spawnMessages;
	}

	@Override
	protected void processEntity(Entity entity) {
		Body body = BodyComponent.MAPPER.get(entity).getBody();
		WeaponComponent wc = WeaponComponent.MAPPER.get(entity);

		if (wc.isFirePrimary()) {
			// Create new entity with:
			// startpos, startrotation, startvel(linear and angular)
			// some bullet sprite
			// ...
			// spawnMessage?
			// spawn.add(new SpawnProjectileEvent(weaponID, startpos, ...)
			WeaponDataI primary = wc.getPrimary();
			Vector2 velocity = new Vector2(1, 0).rotateRad(body.getAngle()).scl(primary.getBulletSpeed());
			spawnMessages.add(new SpawnProjectileMessage(primary.getID(), body.getPosition(), velocity, body.getAngle(),
					wc.getPhysicsCategory(), wc.getPhysicsMask()));
		}
		if (wc.isFireSecondary()) {

		}
	}

}
