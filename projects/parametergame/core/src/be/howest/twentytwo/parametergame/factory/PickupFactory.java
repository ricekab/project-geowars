package be.howest.twentytwo.parametergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class PickupFactory implements ISpawnFactory {

	// TODO: IMPLEMENT PICKUPFACTORY FOR PICKUPS
	
	@Override
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity, short physicsCategory,
			short physicsMask) {
		return null;
	}

	@Override
	public Entity spawnEntity(Vector2 pos, float rotation, Vector2 initialVelocity) {
		return null;
	}

	@Override
	public String getType() {
		return null;
	}
	
	
	@Override
	public int hashCode() {
		return getType().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof PickupFactory){
			PickupFactory other = (PickupFactory) obj;
			if(this.getType().equals(other.getType())){
				return true;
			}
		}
		return false;
	}

}
