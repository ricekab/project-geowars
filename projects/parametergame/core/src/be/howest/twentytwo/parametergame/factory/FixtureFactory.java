package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.model.physics.collision.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class FixtureFactory {

	public FixtureDef createFixtureDef(String shapeString, float width, float height,
			float offsetX, float offsetY, float density, float friction, float restitution) {
		FixtureDef fix = new FixtureDef();

		Shape shape = null;
		switch (shapeString) {
			case "circle":
				CircleShape circle = new CircleShape();
				circle.setRadius(width);
				shape = circle;
				break;
			case "rect":
			case "rectangle":
				PolygonShape box = new PolygonShape();
				box.setAsBox(width, height);
				break;
			default:
				Gdx.app.error("FixtureFact",
						String.format("ERR: Unknown fixture shape: %s", shapeString));
		}

		fix.shape = shape;
		fix.density = density;
		fix.friction = friction;
		fix.restitution = restitution;

		fix.filter.categoryBits = Constants.PLAYER_CATEGORY;
		fix.filter.maskBits = Constants.PLAYER_MASK;

		// Cleanup
		shape.dispose();

		return fix;
	}
}
