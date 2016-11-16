package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.dataTypes.PlanetData;
import be.howest.twentytwo.parametergame.dataTypes.ShipData;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

public class NPCShipFactory {
	
	// TODO: Not complete
	
	/**
	 * Creates a ship entity using the given ship data. The entity and its components are created from the supplied
	 * {@link PooledEngine}.
	 */
	public Entity createShip(ShipData data, PooledEngine engine) {
		Entity ship = engine.createEntity();
		TransformComponent transform = engine.createComponent(TransformComponent.class);
		
		ship.add(transform);
		
		SpriteComponent sprite = engine.createComponent(SpriteComponent.class);
		// TODO: Retrieve texture based on getTexture() string
		ship.add(sprite);
		
		BodyComponent body = engine.createComponent(BodyComponent.class);
		
		ship.add(body);
		
		return ship;
	}
}