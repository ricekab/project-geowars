package be.howest.twentytwo.parametergame.model.event.pickup;

import be.howest.twentytwo.parametergame.model.component.PlayerComponent;
import be.howest.twentytwo.parametergame.model.gamedata.PlayerData;

import com.badlogic.ashley.core.Entity;

public class GeomPickupCallback extends BasePickupCallback {

	private final float score;
	
	public GeomPickupCallback(float geomScore) {
		this.score = geomScore;
	}

	@Override
	public void handle(Entity entity) {
		PlayerData pData = PlayerComponent.MAPPER.get(entity).getPlayerData();
		pData.setScore(pData.getScore() + score);
	}

}
