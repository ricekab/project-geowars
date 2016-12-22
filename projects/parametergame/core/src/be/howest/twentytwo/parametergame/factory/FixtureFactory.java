package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.model.physics.collision.Collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class FixtureFactory {

	public FixtureDef createFixtureDef(String shapeString, float width, float height,
			float offsetX, float offsetY, float density, float friction, float restitution) {
		FixtureDef fix = new FixtureDef();

		switch (shapeString) {
			case "circle":
				CircleShape circle = new CircleShape();
				circle.setRadius(width/2f);
				fix.shape = circle;
				break;
			case "rect":
			case "rectangle":
				PolygonShape box = new PolygonShape();
				box.setAsBox(width, height);
				fix.shape = box;
				break;
			default:
				Gdx.app.error("FixtureFact",
						String.format("ERR: Unknown fixture shape: %s", shapeString));
		}

		fix.density = density;
		fix.friction = friction;
		fix.restitution = restitution;

		fix.filter.categoryBits = Collision.PLAYER_CATEGORY;
		fix.filter.maskBits = Collision.PLAYER_MASK;

		return fix;
	}
}
