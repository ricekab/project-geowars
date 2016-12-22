package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.dataTypes.PlanetData;
import be.howest.twentytwo.parametergame.dataTypes.PlanetDataI;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.ShapeComponent;
import be.howest.twentytwo.parametergame.model.component.ShapeComponent.ShapeDraw;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class PlanetFactory {
	private static final String PLANET_SPRITE_PACK = "sprites/game.pack";

	private PooledEngine engine;
	private World world;
	private AssetManager assets;

	public PlanetFactory(PooledEngine engine, World world, AssetManager assets) {
		this.engine = engine;
		this.world = world;
		this.assets = assets;
	}

	/**
	 * Creates a planet entity using the given planet data. The entity and its
	 * components are created from the supplied {@link PooledEngine}.
	 */
	public Entity createPlanet(PlanetDataI pdata) {
		Entity planet = engine.createEntity();

		TransformComponent transform = engine.createComponent(TransformComponent.class);
		transform.setPos(pdata.getXCoord(), pdata.getYCoord());
		transform.setWorldSize(pdata.getPlanetRadius() * 2, pdata.getPlanetRadius() * 2);
		transform.setRotation((float) Math.random() * 360f); // Random rotation
		planet.add(transform);

		// PHYSICS BODY
		BodyComponent bodyComponent = engine.createComponent(BodyComponent.class);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		// bodyDef.fixedRotation = true; --> Should be true for all/player
		// ships?
		bodyDef.position.set(pdata.getXCoord(), pdata.getYCoord());
		Body rigidBody = world.createBody(bodyDef); // Put in world
		rigidBody.setUserData(pdata);
		bodyComponent.setBody(rigidBody);

		FixtureFactory fixtureFactory = new FixtureFactory();

		// PLANET
		FixtureDef fixtureDef = fixtureFactory.createFixtureDef("circle", pdata.getPlanetRadius() * 2,
				pdata.getPlanetRadius() * 2, 0f, 0f, 5f, 0.5f, 0f);
		fixtureDef.filter.categoryBits = Collision.PLANET_CATEGORY;
		fixtureDef.filter.maskBits = Collision.PLANET_MASK;
		rigidBody.createFixture(fixtureDef);
		fixtureDef.shape.dispose();

		// GRAVITY
		fixtureDef = fixtureFactory.createFixtureDef("circle", pdata.getGravityRadius() * 2f,
				pdata.getGravityRadius() * 2f, 0f, 0f, 1f, 1f, 0f);
		fixtureDef.isSensor = true;
		fixtureDef.filter.categoryBits = Collision.GRAVITY_CATEGORY;
		fixtureDef.filter.maskBits = Collision.PLANET_MASK;
		Fixture grav = rigidBody.createFixture(fixtureDef);
		grav.setUserData(pdata);
		fixtureDef.shape.dispose();

		planet.add(bodyComponent);

		// TEXTURE/SPRITE
		SpriteComponent sprite = engine.createComponent(SpriteComponent.class);
		TextureAtlas spritesheet = assets.get(PLANET_SPRITE_PACK, TextureAtlas.class);
		// TextureRegion region =
		// spritesheet.findRegion(pdata.getTextureString());
		TextureRegion region = spritesheet.findRegion(pdata.getTextureString());
		sprite.setRegion(region);
		planet.add(sprite);

		ShapeComponent shape = engine.createComponent(ShapeComponent.class);
		shape.setFillType(ShapeType.Line);
		shape.setDrawType(ShapeDraw.CIRCLE);
		shape.setWidth(pdata.getGravityRadius());
		shape.setHeight(pdata.getGravityRadius());
		planet.add(shape);

		return planet;
	}

}
