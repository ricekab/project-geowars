package be.howest.twentytwo.parametergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;

public class GeomFactory implements ISpawnFactory {
	private static final String GEOM_SPRITE_PACK = "sprites/game.pack";

	private static final float GEOM_SIZE = 6f;

	private final PooledEngine engine;
	private final World world;
	private final AssetManager assets;

	private final BodyDef bodyDef;
	private final FixtureDef fixtureDef;
	private final DifficultyDataI modifiers;
	private final TextureRegion sprite;

	public GeomFactory(PooledEngine engine, World world, AssetManager assets, DifficultyDataI difficulty) {
		this.engine = engine;
		this.world = world;
		this.assets = assets;
		bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		fixtureDef = new FixtureDef();
		Shape circle = new CircleShape();
		circle.setRadius(GEOM_SIZE / 2f);
		fixtureDef.shape = circle;
		this.modifiers = difficulty;
		TextureAtlas spritesheet = assets.get(GEOM_SPRITE_PACK, TextureAtlas.class);
		TextureRegion region = spritesheet.findRegion("geom");
		this.sprite = region;
	}

	@Override
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity, short physicsCategory,
			short physicsMask) {
		Entity geomPickup = engine.createEntity();

		// TRANSFORM
		TransformComponent transform = engine.createComponent(TransformComponent.class);
		transform.setPos(pos);
		transform.setWorldSize(GEOM_SIZE, GEOM_SIZE);
		transform.setRotation(rotation);
		geomPickup.add(transform);

		// BODY
		BodyComponent bc = engine.createComponent(BodyComponent.class);
		bodyDef.position.set(pos.x, pos.y);
		bodyDef.angle = rotation;
		Body body = world.createBody(bodyDef);
		body.createFixture(fixtureDef);
		body.setUserData(geomPickup);
		bc.setBody(body);
		geomPickup.add(bc);

		// TEXTURE
		SpriteComponent sc = engine.createComponent(SpriteComponent.class);
		sc.setRegion(sprite);
		geomPickup.add(sc);

		return geomPickup;
	}

	@Override
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity) {
		return spawnEntity(pos, rotation, initialVelocity, Collision.PLAYER_PICKUPS, Collision.PICKUP_MASK);
	}

	@Override
	public String getType() {
		return "geom";
	}

}
