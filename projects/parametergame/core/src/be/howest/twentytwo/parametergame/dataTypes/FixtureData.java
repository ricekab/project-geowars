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
		return shape;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getOffsetX() {
		return offsetX;
	}

	public float getOffsetY() {
		return offsetY;
	}

	public float getDensity() {
		return density;
	}

	public float getFriction() {
		return friction;
	}

	public float getRestitution() {
		return restitution;
	}
	

}
