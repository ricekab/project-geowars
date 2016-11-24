package be.howest.twentytwo.parametergame.model.system;

import java.util.Map;

import be.howest.twentytwo.parametergame.model.component.CameraComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

public class CameraSystem extends IteratingSystem {
	
	public static final int PRIORITY = 0;

	public CameraSystem() {
		super(Family.all(CameraComponent.class).get(), PRIORITY);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		CameraComponent cc = CameraComponent.MAPPER.get(entity);
		
		Vector2 cameraPos = new Vector2();
		Vector2 point;
		for(Map.Entry<TransformComponent, Integer> entry : cc.getTrackingPoints().entrySet()){
			// Normalized weight vector
			point = new Vector2(entry.getKey().getPos()).scl(entry.getValue()).scl(1.0f/cc.getTotalWeight());
			cameraPos.add(point);
		}
		
		cc.getCamera().position.x = cameraPos.x;
		cc.getCamera().position.y = cameraPos.y;
		cc.getCamera().update();
		
	}

}
