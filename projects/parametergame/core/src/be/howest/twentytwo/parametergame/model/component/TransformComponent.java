package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Position, scale and orientation component.
 */
public class TransformComponent implements Component {
	
	private Vector2 position;
	private Vector2 scale;
	private float rotation; // 0 - 360

}
