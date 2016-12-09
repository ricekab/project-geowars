package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BackgroundComponent implements Component, Poolable {

	public static final ComponentMapper<BackgroundComponent> MAPPER = ComponentMapper
			.getFor(BackgroundComponent.class);
	
	private long seed;
	private TextureRegion tiles[][];
	
	public BackgroundComponent(long randomSeed) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void reset() {
	}

}
