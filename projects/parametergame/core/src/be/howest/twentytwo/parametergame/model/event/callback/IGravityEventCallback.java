package be.howest.twentytwo.parametergame.model.event.callback;

import com.badlogic.gdx.physics.box2d.Body;

public interface IGravityEventCallback extends IEventCallback{
	
	public void onGravityStart(Body planet, Body target);
	
	public void onGravityEnd(Body planet, Body target);

}
