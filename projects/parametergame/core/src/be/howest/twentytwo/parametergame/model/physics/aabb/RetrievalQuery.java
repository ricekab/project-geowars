package be.howest.twentytwo.parametergame.model.physics.aabb;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;

/**
 * Retrieve all the fixtures that match against the given mask.
 */
public class RetrievalQuery implements QueryCallback {

	private final Collection<Fixture> fixtures;
	private final short mask;

	public RetrievalQuery(short physicsMask) {
		this.fixtures = new ArrayList<Fixture>();
		this.mask = physicsMask;
	}

	@Override
	public boolean reportFixture(Fixture fixture) {
		if((fixture.getFilterData().categoryBits & mask) > 0) {
			fixtures.add(fixture);
			return true;
		}
		return true;
	}

	public Collection<Fixture> getFixtures() {
		return this.fixtures;
	}

}
