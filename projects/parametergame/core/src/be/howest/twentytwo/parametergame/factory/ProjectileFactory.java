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
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import be.howest.twentytwo.parametergame.dataTypes.FixtureDataI;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponData;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;

public class ProjectileFactory implements ISpawnFactory, Disposable {
	private static final String PROJECTILE_SPRITE_PACK = "sprites/ships.pack";

	private final PooledEngine engine;
	private final World world;
	private final AssetManager assets;

	private WeaponDataI weaponData;
	private BodyDef bodyDef;
	private FixtureDef fixtureDef;
	private SpriteComponent spriteComponent;

	public ProjectileFactory(PooledEngine engine, World world, AssetManager assets, WeaponDataI weaponData) {
		this.engine = engine;
		this.world = world;
		this.assets = assets;
		this.weaponData = weaponData;
		collectSharedDefinitions(weaponData);
	}

	private void collectSharedDefinitions(WeaponDataI weaponData) {
		// BODY DEF
		bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.linearDamping = 0f;
		bodyDef.angularDamping = 0f;
		bodyDef.bullet = true; // Only used for fast projectiles.
		// bodyDef.fixedRotation = true;

		// FIXTURE DEFS
		fixtureDef = new FixtureDef();
		PolygonShape box = new PolygonShape();
		box.setAsBox(weaponData.getBulletSize().x, weaponData.getBulletSize().y);
		fixtureDef.shape = box;

		// TEXTURE/SPRITE
		spriteComponent = engine.createComponent(SpriteComponent.class);
		TextureAtlas spritesheet = assets.get(PROJECTILE_SPRITE_PACK, TextureAtlas.class);
		TextureRegion region = spritesheet.findRegion(weaponData.getID());
		spriteComponent.setRegion(region);
	}

	/** Returns the name of the weapons this creates projectiles for. */
	@Override
	public String getType() {
		return weaponData.getID();
	}

	@Override
	public Entity createEntity(Vector2 pos, float rotation, Vector2 initialVelocity, short physicsCategory,
			short physicsMask) {
		Entity projectile = engine.createEntity();

		// TRANSFORM
		TransformComponent transform = engine.createComponent(TransformComponent.class);
		transform.setPos(pos);
		transform.setWorldSize(weaponData.getBulletSize());
		transform.setRotation(rotation);
		projectile.add(transform);

		// PHYSICS BODY
		BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);

		bodyDef.position.set(pos.x, pos.y);
		bodyDef.angle = rotation;
		bodyDef.linearVelocity.set(initialVelocity);
		
		Body rigidBody = world.createBody(bodyDef); // Put in world
		bodyComponent.setBody(rigidBody);

		fixtureDef.filter.categoryBits = physicsCategory;
		fixtureDef.filter.maskBits = physicsMask;

		rigidBody.createFixture(fixtureDef);

		rigidBody.setUserData(projectile);

		projectile.add(bodyComponent);

		// TEXTURE/SPRITE
		projectile.add(spriteComponent);

		return projectile;
	}

	@Override
	public Entity createEntity(Vector2 pos, float rotation, Vector2 initialVelocity) {
		return createEntity(pos, rotation, initialVelocity, fixtureDef.filter.categoryBits, fixtureDef.filter.maskBits);
	}

	@Override
	public void dispose() {
		fixtureDef.shape.dispose();
	}
}
