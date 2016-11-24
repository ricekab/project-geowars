package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.dataTypes.PlanetData;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

public class PlanetFactory {

	public PlanetFactory() {
	}

	/**
	 * Creates a planet entity using the given planet data. The entity and its components are created from the supplied
	 * {@link PooledEngine}.
	 */
	public Entity createPlanet(PlanetData data, PooledEngine engine) {
		Entity planet = engine.createEntity();
		TransformComponent transform = engine.createComponent(TransformComponent.class);
		transform.setPos(data.getXCoord(), data.getYCoord());
		transform.setRotation((float)Math.random() * 360f);	// Random rotation
		planet.add(transform);
		
		SpriteComponent sprite = engine.createComponent(SpriteComponent.class);
		// TODO: Retrieve texture based on getTexture() string
		planet.add(sprite);
		
		BodyComponent body = engine.createComponent(BodyComponent.class);
		
		
		planet.add(body);
		
		return planet;
	}

}
