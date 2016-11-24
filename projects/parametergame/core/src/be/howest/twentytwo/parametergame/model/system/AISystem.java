package be.howest.twentytwo.parametergame.model.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import be.howest.twentytwo.parametergame.model.component.AIComponent;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;

public class AISystem extends IteratingSystem {
	
	public static final int PRIORITY = 0;

	public AISystem() {
		super(Family.all(AIComponent.class, BodyComponent.class, MovementComponent.class).get(), PRIORITY);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		AIComponent aic = AIComponent.MAPPER.get(entity);
		BodyComponent bc = BodyComponent.MAPPER.get(entity);
		MovementComponent mc = MovementComponent.MAPPER.get(entity);
		
		Body self = bc.getBody();
		Body target = aic.getTarget();
		
		Vector2 direction = target.getPosition().cpy().sub(self.getPosition()).nor();
		
		Vector2 forward = new Vector2(self.getLinearVelocity().nor());
		
		float angle = self.getAngle() - MathUtils.cos(direction.dot(Vector2.X));
		
		Gdx.app.log("AISys", "Direction Vec" + direction.toString());
		Gdx.app.log("AISys", "Forward Vec" + forward.toString());
		// TURNING LOGIC
		Gdx.app.log("AISys", String.format("Angle is %f", angle));
		if(angle > MathUtils.degreesToRadians*10f){
			mc.setTurnRight(true);
			mc.setTurnLeft(false);
		} else if (angle < MathUtils.degreesToRadians*-10f){
			mc.setTurnLeft(true);
			mc.setTurnRight(false);
		} else {
			mc.setTurnLeft(false);
			mc.setTurnRight(false);
		}
		
		// FORWARD LOGIC
		if(Math.abs(angle) < MathUtils.degreesToRadians*30f){
			mc.setAccelerateForward(true);
		} else {
			mc.setAccelerateForward(false);
		}
	}

}
