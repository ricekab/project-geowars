package be.howest.twentytwo.parametergame.dataTypes;

import java.io.Serializable;

public class PlanetData implements PlanetDataI, Serializable{
	
	private float xCoord;
	private float yCoord;
	private float planetRadius;
	private String texture;
	private float mass;
	private float gravityRadius;
	private final float minPlanetRadius = 1f;
	
	public PlanetData(float xCoord, float yCoord, float planetRadius, String texture, float mass, float gravityRadius) {
		setXCoord(xCoord);
		setYCoord(yCoord);
		setPlanetRadius(planetRadius);
		setTexture(texture);
		setMass(mass);
		setGravityRadius(gravityRadius);
	}
	
	//	SETTERS
	
	public void setXCoord(float xCoord) {
		// @NICK: World can go into negative coordinates.
//		float minXCoord = planetRadius / 2;
//		if(xCoord >= minXCoord) {
//			this.xCoord = xCoord;
//			} else {
//			this.xCoord = minXCoord;
//		}	
		
		this.xCoord = xCoord;
	}
	
	public void setYCoord (float yCoord) {
//		float minYCoord = planetRadius / 2;
//		if(yCoord >= minYCoord) {
//			this.yCoord = yCoord;
//		} else {
//			this.yCoord = minYCoord;
//		}
		
		this.yCoord = yCoord;
	}
	
	public void setPlanetRadius(float planetRadius) {
		if(planetRadius >= minPlanetRadius) {
			this.planetRadius = planetRadius;
		} else {
			this.planetRadius = minPlanetRadius;
		}
	}
	
	public void setTexture(String texture) {
		this.texture = texture;
	}
	
	public void setMass(float mass) {
		this.mass = mass;
	}
	
	public void setGravityRadius(float gravityRadius) {
		// Add a minimum gravity radius? YES -> minimum gravity radius assured NO -> planet w/o gravity can be used as an obstacle
		if(gravityRadius >= planetRadius) {
			this.gravityRadius = gravityRadius;
		} else {
			this.gravityRadius = planetRadius;
		}
	}
	
	//	GETTERS
	
	public float getXCoord() {
		return xCoord;
	};
	
	public float getYCoord() {
		return yCoord;
	};
	
	public float getPlanetRadius() {
		return planetRadius;
	};
	
	public String getTextureString() {
		return texture;
	};
	
	public float getMass() {
		return mass;
	};
	
	public float getGravityRadius() {
		return gravityRadius;
	};

}
