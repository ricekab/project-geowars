package be.howest.twentytwo.parametergame.model.component;

import be.howest.twentytwo.parametergame.model.gamedata.PlayerData;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool.Poolable;

public class PlayerComponent implements Component, Poolable {

	public static final ComponentMapper<PlayerComponent> MAPPER = ComponentMapper
			.getFor(PlayerComponent.class);

	private PlayerData playerData;

	public PlayerData getPlayerData() {
		return playerData;
	}

	public void setPlayerData(PlayerData playerData) {
		this.playerData = playerData;
	}

	@Override
	public void reset() {
	}

}
