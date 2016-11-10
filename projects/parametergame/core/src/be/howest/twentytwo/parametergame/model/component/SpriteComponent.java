package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Sprite / Texture data
 */
public class SpriteComponent implements Component, Poolable {
	
	public static final ComponentMapper<SpriteComponent> MAPPER = ComponentMapper.getFor(SpriteComponent.class);

	// TODO: TextureRegion / Texture? Probably TextureRegion if we're doing spritesheets
	
	@Override
	public void reset() {
		// Need to reset? Should be set by factory anyway.
	}
}
