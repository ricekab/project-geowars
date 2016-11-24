package be.howest.twentytwo.parametergame.dataTypes;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;

public class ShipData implements ShipDataI{
	
	private Set<Vector2> weaponPositions;
	
	public ShipData() {
		weaponPositions = new HashSet<>();
	}
	
	public void addWeaponPosition(Vector2 pos) {
		weaponPositions.add(pos);
	}
	
	public Set<Vector2> getWeaponPositions() {
		return weaponPositions;
	}

}
