package be.howest.twentytwo.parametergame.model.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ShapeComponent implements Component, Poolable {

	public enum ShapeDraw {
		CIRCLE, BOX;
	}

	public static final ComponentMapper<ShapeComponent> MAPPER = ComponentMapper.getFor(ShapeComponent.class);

	private ShapeType fillType;
	private ShapeDraw drawType;
	private float width, height;

	public ShapeType getFillType() {
		return fillType;
	}

	public void setFillType(ShapeType fillType) {
		this.fillType = fillType;
	}

	public ShapeDraw getDrawType() {
		return drawType;
	}

	public void setDrawType(ShapeDraw drawType) {
		this.drawType = drawType;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	@Override
	public void reset() {
	}

}
