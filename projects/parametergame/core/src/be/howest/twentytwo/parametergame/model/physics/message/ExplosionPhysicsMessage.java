package be.howest.twentytwo.parametergame.model.physics.message;

import java.util.ArrayList;
import java.util.List;

import be.howest.twentytwo.parametergame.model.physics.aabb.RetrievalQuery;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Generate forces on bodies to simulate an explosion.
 */
public class ExplosionPhysicsMessage extends SinglePhysicsMessage {

	private final Body sourceBody;
	private final float range;
	private final float force;
	private final short mask;

	public ExplosionPhysicsMessage(Body sourceBody, float range, float force, short physicsMask) {
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
		sourceBody.getWorld().QueryAABB(retrieval, pos.x - range, pos.y - range, pos.x + range, pos.y + range);
		List<Body> bodies = new ArrayList<Body>();
		Body body;
		for (Fixture fix : retrieval.getFixtures()) {
			body = fix.getBody();
			if (!bodies.contains(body) && sourceBody.getWorldCenter().dst(body.getWorldCenter()) <= range) {
				bodies.add(body);
			}
		}
		// Apply force away from source
		for (Body targetBody : bodies) {
			Vector2 forceVector = new Vector2(targetBody.getPosition());
			forceVector.sub(sourceBody.getPosition()).nor().scl(force);
//			Gdx.app.debug("EPE",
//					String.format("%s , %s", sourceBody.getPosition().toString(), targetBody.getPosition().toString()));
//			Gdx.app.debug("EPE", forceVector.toString());
			IPhysicsMessage instantEvent = new LinearForceMessage(targetBody, forceVector);
			instantEvent.execute();
			// Can be shortened to one line but leaving this for clarity.
		}
	}

}
