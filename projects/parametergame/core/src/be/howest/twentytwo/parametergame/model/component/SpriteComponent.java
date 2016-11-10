package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Sprite / Texture data
 */
public class SpriteComponent implements Component, Poolable {
	// TODO: Should I rename this TextureRegionComponent? This could create confusion with actual Sprite class

	public static final ComponentMapper<SpriteComponent> MAPPER = ComponentMapper.getFor(SpriteComponent.class);

	private TextureRegion region;	// TextureRegion for sprite sheet reasons
	
	public TextureRegion getRegion() {
		return region;
	}

	public void setRegion(TextureRegion region) {
		this.region = region;
	}

	@Override
	public void reset() {
		// Need to reset? Should be set by factory anyway.
	}
}
