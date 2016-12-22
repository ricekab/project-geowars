package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool.Poolable;

public class PlayerComponent implements Component, Poolable {

	public static final ComponentMapper<PlayerComponent> MAPPER = ComponentMapper
			.getFor(PlayerComponent.class);
	
	private float score;
	private float geomRadius;

	public float getScore() {
		return score;
	}

	public float getGeomRadius() {
		return geomRadius;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public void setGeomRadius(float geomRadius) {
		this.geomRadius = geomRadius;
	}

	@Override
	public void reset() {
	}

}
