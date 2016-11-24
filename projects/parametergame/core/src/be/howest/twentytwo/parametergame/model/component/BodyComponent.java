package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Box2D Physics body.
 */
public class BodyComponent implements Component, Poolable {
	
	public static final ComponentMapper<BodyComponent> MAPPER = ComponentMapper.getFor(BodyComponent.class);
	
	private Body body;
	// private BodyDef bodyDef; // ??
	// What about fixture def(s)?

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	@Override
	public void reset() {
		body = null;
		// Need to reset? Should be set by factory anyway.
	}
}
