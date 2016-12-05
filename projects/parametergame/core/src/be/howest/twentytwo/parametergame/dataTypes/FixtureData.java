package be.howest.twentytwo.parametergame.dataTypes;

public class FixtureData implements FixtureDataI{
	
	private String shape;
	private float width;
	private float height;
	private float offsetX;
	private float offsetY;
	private float density;
	private float friction;
	private float restitution;
	
	public FixtureData(String shape, float width, float height, float offsetX, float offsetY, float density, float friction, float restitution) {
		this.shape = shape;
		this.width = width;
		this.height = height;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.density = density;
		this.friction = friction;
		this.restitution = restitution;
	}
	
	//	GETTERS

	public String getShape() {
		return null;
	}

	public float getWidth() {
		return 0;
	}

	public float getHeight() {
		return 0;
	}

	public float getOffsetX() {
		return 0;
	}

	public float getOffsetY() {
		return 0;
	}

	public float getDensity() {
		return 0;
	}

	public float getFriction() {
		return 0;
	}

	public float getRestitution() {
		return 0;
	}

}
