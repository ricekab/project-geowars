package be.howest.twentytwo.parametergame.dataTypes;

public class PlayerShipData implements PlanetDataI{
	
	private float xCoord;
	private float yCoord;
	private float planetRadius;	//TODO whut??
	private String texture;
	private float mass;
	
	public PlayerShipData(float xCoord, float yCoord, float planetRadius, String texture, float mass) {
		setXCoord(xCoord);
		setYCoord(yCoord);
		setPlanetRadius(planetRadius);
		setTexture(texture);
		setMass(mass);
	}
	
	//	SETTERS

	public void setXCoord(float xCoord) {
		
	}

	public void setYCoord(float yCoord) {
		
	}

	public void setPlanetRadius(float planetRadius) {
		
	}

	public void setTexture(String texture) {
		
	}

	public void setMass(float mass) {
		
	}
	
	//	GETTERS

	public float getXCoord() {
		return 0;
	}

	public float getYCoord() {
		return 0;
	}

	public float getPlanetRadius() {
		return 0;
	}

	public String getTexture() {
		return null;
	}

	public float getMass() {
		return 0;
	}

	public float getGravityRadius() {
		return 0;
	}

}
