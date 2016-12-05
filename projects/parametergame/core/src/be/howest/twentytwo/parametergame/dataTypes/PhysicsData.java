package be.howest.twentytwo.parametergame.dataTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PhysicsData implements PhysicsDataI{
	
	private short physicsCategory;
	private short physicsMask;
	private List<FixtureDataI> fixtures;
	
	public PhysicsData(short physicsCategory, short physicsMask) {
		this.physicsCategory = physicsCategory;
		this.physicsMask = physicsMask;
		fixtures = new ArrayList<>();
	}
	
	//	SETTERS
	
	private void addFixture(FixtureDataI fixture) {
		fixtures.add(fixture);
	}
	
	//	GETTERS

	public short getPhysicsCategory() {
		return physicsCategory;
	}

	public short getPhysicsMask() {
		return physicsMask;
	}

	public Collection<FixtureDataI> getFixtures() {
		return fixtures;
	}

}
