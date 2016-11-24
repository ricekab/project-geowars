package be.howest.twentytwo.parametergame.dataTypes;

public interface FixtureDataI {

	public String getShape();
	
	public float getWidth();
	public float getHeight();
	
	public float getOffsetX();
	public float getOffsetY();
	
	public float getDensity();
	public float getFriction();
	public float getRestitution();	// = Bounciness
}
