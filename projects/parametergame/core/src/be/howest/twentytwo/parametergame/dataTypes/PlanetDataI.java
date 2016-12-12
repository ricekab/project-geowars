package be.howest.twentytwo.parametergame.dataTypes;

public interface PlanetDataI {
	
	public void setXCoord(float xCoord);
	public void setYCoord(float yCoord);
	public void setPlanetRadius(float planetRadius);
	public void setTexture(String texture);
	public void setMass(float mass);
	
	public float getXCoord();
	public float getYCoord();
	public float getPlanetRadius();
	public String getTextureString();
	public float getMass();
	public float getGravityRadius();
	
}
