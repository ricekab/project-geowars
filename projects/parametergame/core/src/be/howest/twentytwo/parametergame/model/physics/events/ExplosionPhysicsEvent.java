package be.howest.twentytwo.parametergame.model.physics.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

import be.howest.twentytwo.parametergame.model.physics.aabb.RetrievalQuery;

public class ExplosionPhysicsEvent extends SinglePhysicsEvent {

	private final Body sourceBody;
	private final float range;
	private final float force;
	private final short mask;

	public ExplosionPhysicsEvent(Body sourceBody, float range, float force, short physicsMask) {
		this.sourceBody = sourceBody;
		this.range = range;
		this.force = force;
		this.mask = physicsMask;
	}

	@Override
	public void execute() {
		super.execute();
		// Retrieve bodies in range
		Vector2 pos = sourceBody.getPosition();
		RetrievalQuery retrieval = new RetrievalQuery(mask);
		sourceBody.getWorld().QueryAABB(retrieval, pos.x - range, pos.y - range, pos.x + range,
				pos.y + range);
		List<Body> bodies = new ArrayList<Body>();
		Body body;
		for (Fixture fix : retrieval.getFixtures()) {
			body = fix.getBody();
			if(!bodies.contains(body)
					&& sourceBody.getWorldCenter().dst(body.getWorldCenter()) <= range) {
				bodies.add(body);
			}
		}
		// Apply force away from source
		for (Body targetBody : bodies) {
			Vector2 forceVector = new Vector2(targetBody.getPosition());
			forceVector.sub(sourceBody.getPosition()).nor().scl(force);
			Gdx.app.log("EPE", String.format("%s , %s", sourceBody.getPosition().toString(),
					targetBody.getPosition().toString()));
			Gdx.app.log("EPE", forceVector.toString());
			IPhysicsEvent instantEvent = new LinearForceEvent(targetBody, forceVector);
			instantEvent.execute();
			// Can be shortened to one line but leaving this for clarity.
		}
	}

}
