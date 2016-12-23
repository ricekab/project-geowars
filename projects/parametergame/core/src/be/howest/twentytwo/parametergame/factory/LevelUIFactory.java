package be.howest.twentytwo.parametergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

import be.howest.twentytwo.parametergame.model.component.HealthComponent;
import be.howest.twentytwo.parametergame.model.component.PlayerComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;

public class LevelUIFactory {
	
	private final SpriteBatch batch;
	private final Viewport viewport;
	private final Skin uiSkin;
	
	public LevelUIFactory(SpriteBatch spriteBatch, Viewport uiViewport, Skin uiSkin){
		this.batch = spriteBatch;
		this.viewport = uiViewport;
		this.uiSkin = uiSkin;
	}

	public Stage createUI(Entity playerEntity){
		Stage stage = new Stage(viewport, batch);
		
		HealthComponent hp = HealthComponent.MAPPER.get(playerEntity);
		WeaponComponent wc = WeaponComponent.MAPPER.get(playerEntity);
		PlayerComponent pc = PlayerComponent.MAPPER.get(playerEntity);
		
		Table rootTable = new Table();
		
		rootTable.add(new Label("$teststring", uiSkin));
		
		stage.addActor(rootTable);
		
		Gdx.app.error("LevelUIFactory", "UI NOT IMPLEMENTED");
		return stage;
	}
}
