package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool.Poolable;

public class EnemyComponent implements Component, Poolable {
	
	public static final ComponentMapper<EnemyComponent> MAPPER = ComponentMapper
			.getFor(EnemyComponent.class);
	
	private float scoreValue, geomDropRate;

	public float getGeomDropRate() {
		return geomDropRate;
	}

	public void setGeomDropRate(float geomDropRate) {
		this.geomDropRate = geomDropRate;
	}

	public float getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(float scoreValue) {
		this.scoreValue = scoreValue;
	}

	@Override
	public void reset() {

	}
}
