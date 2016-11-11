package be.howest.twentytwo.parametergame.dataTypes;

public class Box {
	
	private float width;
	private float height;
	private float xCoord;
	private float yCoord;
	private final float minWidth = 100f;
	private final float minHeight = 100f;
	
	public Box(float width, float height, float xCoord, float yCoord) {
		setWidth(width);
		setHeight(height);
		setXCoord(xCoord);
		setYCoord(yCoord);
	}
	
	public void setWidth(float Width) {
		if(width >= minWidth) {
			this.width = width;
		} else {
			this.width = minWidth;
		}
	}
	
	public void setHeight(float Height) {
		if(height >= minHeight) {
			this.height = height;
		} else {
			this.height = minHeight;
		}
	}
	
	public void setXCoord(float xCoord) {
		this.xCoord = xCoord;
	}
	
	public void setYCoord(float yCoord) {
		this.yCoord = yCoord;
	}
	
	public float getWidth(){
		return width;
	};
	
	public float getHeight(){
		return height;
	};
	
	public float getXCoord(){
		return xCoord;
	};
	
	public float getYCoord(){
		return yCoord;
	};

}
