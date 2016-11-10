package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Box2D Physics body.
 */
public class BodyComponent implements Component, Poolable {
	private Body body;

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	@Override
	public void reset() {
		// Need to reset? Should be set by factory anyway.
	}
}
