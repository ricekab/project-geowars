package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Collection;

public interface PhysicsDataI {
	
	public short getPhysicsCategory();
	public short getPhysicsMask();
	
	public Collection<FixtureDataI> getFixtures();
	
	public void addFixture(FixtureDataI fixture);

}
