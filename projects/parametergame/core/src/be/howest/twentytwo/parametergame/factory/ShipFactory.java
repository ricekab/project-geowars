package be.howest.twentytwo.parametergame.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import be.howest.twentytwo.parametergame.dataTypes.FixtureDataI;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipData;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponData;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;

public class ShipFactory {
	private FixtureFactory fixtureFactory;

	private PooledEngine engine;
	private World world;
	private AssetManager assets;

	private ShipDataI shipData;
	private BodyDef bodyDef;
	private Collection<FixtureDef> fixtureDefs;
	private SpriteComponent spriteComponent;

	public ShipFactory(PooledEngine engine, World world, AssetManager assets, ShipDataI shipData) {
		this.fixtureFactory = new FixtureFactory();
		this.engine = engine;
		this.world = world;
		this.assets = assets;
		this.shipData = shipData;
		this.fixtureDefs = new ArrayList<FixtureDef>();
		collectSharedDefinitions(shipData);
	}

	private void collectSharedDefinitions(ShipDataI shipData) {
		PhysicsDataI physicsData = shipData.getPhysicsData();
		Collection<FixtureDataI> fixturesData = physicsData.getFixtures();

		// BODY DEF
		bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		// bodyDef.fixedRotation = true;

		// FIXTURE DEFS
		FixtureDef fixtureDef;
		for (FixtureDataI fd : fixturesData) {
			fixtureDef = fixtureFactory.createFixtureDef(fd.getShape(), fd.getWidth(), fd.getHeight(), fd.getOffsetX(),
					fd.getOffsetY(), fd.getDensity(), fd.getFriction(), fd.getRestitution());
			fixtureDef.filter.categoryBits = physicsData.getPhysicsCategory();
			fixtureDef.filter.maskBits = physicsData.getPhysicsMask();
			fixtureDefs.add(fixtureDef);
		}

		// TODO: Calculate world size based on fixtures (bounding box for all
		// fixtures, maybe aabb all fixtures)

		// TEXTURE/SPRITE
		spriteComponent = engine.createComponent(SpriteComponent.class);
		TextureAtlas spritesheet = assets.get("sprites/ships.pack", TextureAtlas.class);
		TextureRegion region = spritesheet.findRegion(shipData.getName());
		spriteComponent.setRegion(region);

	}

	public Entity createShip(Vector2 pos, Vector2 size, float rotation) {
		Entity ship = engine.createEntity();

		// TRANSFORM
		TransformComponent transform = engine.createComponent(TransformComponent.class);
		transform.setPos(pos);
		transform.setWorldSize(size);
		transform.setRotation(rotation);
		ship.add(transform);

		// MOVEMENT
		MovementComponent movement = engine.createComponent(MovementComponent.class);
		movement.setMaxLinearVelocity(shipData.getMaxLinearSpeed());
		movement.setMaxAngularVelocity(shipData.getMaxAngularSpeed());
		movement.setLinearAcceleration(shipData.getLinearAcceleration());
		movement.setAngularAcceleration(shipData.getAngularAcceleration());
		movement.setLinearDampStrength(1f);
		ship.add(movement);

		// WEAPON
		List<WeaponDataI> weaponsData = shipData.getWeapons();
		if (weaponsData.size() > 0) {
			WeaponComponent weapon = engine.createComponent(WeaponComponent.class);
			WeaponDataI primary = weaponsData.get(0);
			weapon.setPrimary(primary);
			weaponsData.remove(primary);
			if (weaponsData.size() == 0) {
				weaponsData.add(new WeaponData("NULL", 0f, 0f, 1f, 0, 0f, 0f, 0f, 0f, 0f, 0f, 0, new Vector2(0f, 0f)));
			}
			weapon.setSecondaryWeapons(weaponsData);
			ship.add(weapon);
		}

		// PHYSICS BODY
		BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);

		bodyDef.position.set(pos.x, pos.y);
		bodyDef.angle = rotation;
		Body rigidBody = world.createBody(bodyDef); // Put in world
		bodyComponent.setBody(rigidBody);

		rigidBody.setLinearDamping(shipData.getLinearDamping());
		rigidBody.setAngularDamping(shipData.getAngularDamping());

		for (FixtureDef fixture : fixtureDefs) {
			rigidBody.createFixture(fixture);
		}

		rigidBody.setUserData(ship);

		ship.add(bodyComponent);

		// TEXTURE/SPRITE
		ship.add(spriteComponent);

		return ship;
	}
}
