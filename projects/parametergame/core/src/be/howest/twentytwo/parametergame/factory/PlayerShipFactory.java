package be.howest.twentytwo.parametergame.factory;

import java.util.Collection;

import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.model.component.PlayerComponent;
import be.howest.twentytwo.parametergame.model.gamedata.PlayerData;
import be.howest.twentytwo.parametergame.model.physics.collision.Collision;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public class PlayerShipFactory implements Disposable {

	private ShipFactory shipFactory;

	private PooledEngine engine;
	private World world;
	private AssetManager assets;
	
	private final float geomRadius;
	private Collection<DroneDataI> drones;

	public PlayerShipFactory(PooledEngine engine, World world, AssetManager assets, PlayerShipDataI playerShipData) {
		this.shipFactory = new ShipFactory(engine, world, assets, playerShipData.getShipData());
		this.engine = engine;
		this.world = world;
		this.assets = assets;
		this.drones = playerShipData.getDrones();
		this.geomRadius = playerShipData.getGeomRadius();
	}

	public Entity createPlayerShip(Vector2 pos) {
		Entity player = shipFactory.createShip(pos, 0f, Collision.BULLET_PLAYER_CATEGORY,
				Collision.BULLET_PLAYER_MASK);
		
		PlayerComponent pc = engine.createComponent(PlayerComponent.class);
		pc.setPlayerData(new PlayerData(geomRadius));
		player.add(pc);
		
		
		// TODO: DRONE FACTORY HERE
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
