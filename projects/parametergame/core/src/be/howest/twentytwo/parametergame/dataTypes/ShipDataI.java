package be.howest.twentytwo.parametergame.dataTypes;

import java.util.Set;

import com.badlogic.gdx.math.Vector2;

public interface ShipDataI {
	
	public Set<Vector2> getWeaponPositions();	//ship has multiple positions weapons can be fired from
	public void addWeaponPosition(Vector2 pos);
	
}
