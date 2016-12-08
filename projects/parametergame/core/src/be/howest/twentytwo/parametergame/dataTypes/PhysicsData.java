package be.howest.twentytwo.parametergame.dataTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PhysicsData implements PhysicsDataI{
	
	private short physicsCategory;
	private short physicsMask;
	private List<FixtureDataI> fixtures;
	
	public PhysicsData(short physicsCategory, short physicsMask, List<FixtureDataI> fixtures) {
		this.physicsCategory = physicsCategory;
		this.physicsMask = physicsMask;
		this.fixtures = fixtures;
	}
	
	public PhysicsData(short physicsCategory, short physicsMask) {
		this(physicsCategory, physicsMask, new ArrayList<FixtureDataI>());
	}
	
	//	SETTERS
	@Override
	public void addFixture(FixtureDataI fixture) {
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
