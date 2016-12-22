package be.howest.twentytwo.parametergame.factory;

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
import com.badlogic.gdx.utils.Disposable;

import be.howest.twentytwo.parametergame.dataTypes.FixtureDataI;
import be.howest.twentytwo.parametergame.dataTypes.PhysicsDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.WeaponData;
import be.howest.twentytwo.parametergame.dataTypes.WeaponDataI;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;

public class PlayerShipFactory implements Disposable {

	private ShipFactory shipFactory;

	private PooledEngine engine;
	private World world;
	private AssetManager assets;

	public PlayerShipFactory(PooledEngine engine, World world, AssetManager assets, PlayerShipDataI playerShipData) {
		this.shipFactory = new ShipFactory(engine, world, assets, playerShipData.getShipData());
		this.engine = engine;
		this.world = world;
		this.assets = assets;
	}

	public Entity createPlayerShip(Vector2 pos) {
		Entity player = shipFactory.createShip(pos, 0f, Collision.BULLET_PLAYER_CATEGORY,
				Collision.BULLET_PLAYER_MASK);
		// TODO: Drone addon
		return player;
	}

	public Entity createPlayerShip(float xPos, float yPos) {
		return createPlayerShip(new Vector2(xPos, yPos));
	}

	@Override
	public void dispose() {
		shipFactory.dispose();
	}

}
