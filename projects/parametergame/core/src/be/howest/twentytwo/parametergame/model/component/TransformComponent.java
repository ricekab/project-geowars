package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Position, scale and orientation component used to determine how to render the
 * object. Note that this does not track the physics object! This happens in the
 * {@link BodyComponent}.
 */
public class TransformComponent implements Component {
	private Vector2 position;
	private Vector2 scale;
	private float rotation; // 0 - 360
	
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public Vector2 getScale() {
		return scale;
	}
	public void setScale(Vector2 scale) {
		this.scale = scale;
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	

}
