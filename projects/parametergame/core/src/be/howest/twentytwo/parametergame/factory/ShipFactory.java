package be.howest.twentytwo.parametergame.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import be.howest.twentytwo.parametergame.dataTypes.FixtureDataI;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;
import be.howest.twentytwo.parametergame.model.dataExtension.NullWeaponData;
import be.howest.twentytwo.parametergame.model.dataExtension.WeaponGameData;

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
import com.badlogic.gdx.utils.Disposable;

public class ShipFactory implements ISpawnFactory, Disposable {
	private static final String SHIP_SPRITE_PACK = "sprites/game.pack";

	private final PooledEngine engine;
	private final World world;
	private final AssetManager assets;

	private ShipDataI shipData;
	private BodyDef bodyDef;
	private Collection<FixtureDef> fixtureDefs;
	private TextureRegion sprite;

	public ShipFactory(PooledEngine engine, World world, AssetManager assets, ShipDataI shipData) {
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
		FixtureFactory fixtureFactory = new FixtureFactory();
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
		TextureAtlas spritesheet = assets.get(SHIP_SPRITE_PACK, TextureAtlas.class);
		TextureRegion region = spritesheet.findRegion(shipData.getTexture());
		this.sprite = region;
	}

	public Entity createShip(Vector2 pos, float rotation, short bulletCategory, short bulletMask) {
		Entity ship = engine.createEntity();

		// TRANSFORM
		TransformComponent transform = engine.createComponent(TransformComponent.class);
		transform.setPos(pos);
		transform.setWorldSize(new Vector2(shipData.getShipSizeX(), shipData.getShipSizeY()));
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
		List<WeaponDataI> weaponsData = new ArrayList<WeaponDataI>();
		weaponsData.addAll(shipData.getWeapons());
		if (weaponsData.size() > 0) {
			WeaponComponent weapon = engine.createComponent(WeaponComponent.class);
			weapon.setPhysicsCategory(bulletCategory);
			weapon.setPhysicsMask(bulletMask);
			WeaponDataI primary = weaponsData.get(0);
			weapon.setPrimary(new WeaponGameData(primary));
			weaponsData.remove(primary);
			if (weaponsData.size() == 0) {
				WeaponDataI nullWeapon = new NullWeaponData();
				weaponsData.add(nullWeapon);
			}
			List<WeaponGameData> weaponGameData = new ArrayList<WeaponGameData>();
			for (WeaponDataI w : weaponsData) {
				weaponGameData.add(new WeaponGameData(w));
			}
			weapon.setSecondaryWeapons(weaponGameData);
			ship.add(weapon);
		}

		// PHYSICS BODY
		BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);

		bodyDef.position.set(pos.x, pos.y);
		bodyDef.angle = rotation;
		Body rigidBody = world.createBody(bodyDef); // Put in world
		rigidBody.setUserData(ship);
		bodyComponent.setBody(rigidBody);

		rigidBody.setLinearDamping(shipData.getLinearDamping());
		rigidBody.setAngularDamping(shipData.getAngularDamping());

		for (FixtureDef fixture : fixtureDefs) {
			rigidBody.createFixture(fixture);
		}

		ship.add(bodyComponent);

		// TEXTURE/SPRITE
		SpriteComponent sc = engine.createComponent(SpriteComponent.class);
		sc.setRegion(sprite);
		ship.add(sc);

		return ship;
	}

	/**
	 * Returns the name of the {@link ShipDataI} it is responsible for.
	 */
	public String getShipType() {
		return shipData.getName();
	}

	@Override
	public void dispose() {
		for (FixtureDef fix : fixtureDefs) {
			if (fix.shape != null) {
				fix.shape.dispose();
			}
		}

	}

	@Override
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity, short physicsCategory,
			short physicsMask) {
		Entity ship = createShip(pos, rotation, physicsCategory, physicsMask);
		engine.addEntity(ship);
		return ship;
	}

	@Override
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity) {
		FixtureDef fd = fixtureDefs.iterator().next();
		return spawnEntity(pos, rotation, initialVelocity, fd.filter.categoryBits, fd.filter.maskBits);
	}

	@Override
	public String getType() {
		return getShipType();
	}
}
