package be.howest.twentytwo.parametergame.factory;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.FixtureDataI;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;

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

public class PlayerShipFactory {

	private FixtureFactory fixtureFactory;

	public PlayerShipFactory() {
		this.fixtureFactory = new FixtureFactory();
	}

	public Entity createPlayerShip(PooledEngine engine, World world, AssetManager assets,
			PlayerShipDataI ship, Vector2 pos, Vector2 size) {
		Entity player = engine.createEntity();

		ShipDataI shipData = ship.getShipData();
		PhysicsDataI physicsData = shipData.getPhysicsData();
		Collection<FixtureDataI> fixturesData = physicsData.getFixtures();
		Collection<WeaponDataI> weaponsData = shipData.getWeapons();

		// TRANSFORM
		TransformComponent transform = engine.createComponent(TransformComponent.class);
		transform.setPos(pos);
		transform.setWorldSize(size);
		transform.setRotation(0f);
		player.add(transform);

		// MOVEMENT
		MovementComponent movement = engine.createComponent(MovementComponent.class);
		movement.setMaxLinearVelocity(shipData.getMaxLinearSpeed());
		movement.setMaxAngularVelocity(shipData.getMaxAngularSpeed());
		movement.setLinearAcceleration(shipData.getLinearAcceleration());
		movement.setAngularAcceleration(shipData.getAngularAcceleration());
		movement.setLinearDampStrength(1f);
		player.add(movement);

		// PHYSICS BODY
		BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		// bodyDef.fixedRotation = true; --> Should be true for all/player ships?
		bodyDef.position.set(pos.x, pos.y);
		Body rigidBody = world.createBody(bodyDef); // Put in world
		bodyComponent.setBody(rigidBody);

		rigidBody.setLinearDamping(shipData.getLinearDamping());
		rigidBody.setAngularDamping(shipData.getAngularDamping());

		FixtureDef fixtureDef;
		for (FixtureDataI fd : fixturesData) {
			fixtureDef = fixtureFactory.createFixtureDef(fd.getShape(), fd.getWidth(),
					fd.getHeight(), fd.getOffsetX(), fd.getOffsetY(), fd.getDensity(),
					fd.getFriction(), fd.getRestitution());
			fixtureDef.filter.categoryBits = physicsData.getPhysicsCategory();
			fixtureDef.filter.maskBits = physicsData.getPhysicsMask();
			rigidBody.createFixture(fixtureDef);
			fixtureDef.shape.dispose();
		}
		rigidBody.setUserData(player);	// TODO: Entity as object data?
		player.add(bodyComponent);

		// TEXTURE/SPRITE
		SpriteComponent sprite = engine.createComponent(SpriteComponent.class);

		TextureAtlas spritesheet = assets.get("sprites/ships.pack", TextureAtlas.class);
		TextureRegion region = spritesheet.findRegion(shipData.getName());
		sprite.setRegion(region);

		player.add(sprite);

		return player;
	}

	public Entity createPlayerShip(PooledEngine engine, World world, AssetManager assets,
			PlayerShipDataI ship, float xPos, float yPos, float xSize, float ySize) {
		return createPlayerShip(engine, world, assets, ship, new Vector2(xPos, yPos), new Vector2(
				xSize, ySize));
	}

}
