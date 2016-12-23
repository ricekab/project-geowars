package be.howest.twentytwo.parametergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import be.howest.twentytwo.parametergame.model.component.HealthComponent;
import be.howest.twentytwo.parametergame.model.component.PlayerComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;

public class LevelUIFactory {

	public Stage createUI(Entity playerEntity){
		Stage stage = new Stage();
		
		HealthComponent hp = HealthComponent.MAPPER.get(playerEntity);
		WeaponComponent wc = WeaponComponent.MAPPER.get(playerEntity);
		PlayerComponent pc = PlayerComponent.MAPPER.get(playerEntity);
		
		
		Gdx.app.error("LevelFactory", "UI NOT IMPLEMENTED");
		return stage;
	}
}
