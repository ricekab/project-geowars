package be.howest.twentytwo.parametergame.factory;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.Viewport;

import be.howest.twentytwo.parametergame.ParameterGame;
import be.howest.twentytwo.parametergame.model.component.HealthComponent;
import be.howest.twentytwo.parametergame.model.component.PlayerComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;
import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.collision.PlayerHitEvent;
import be.howest.twentytwo.parametergame.model.event.game.EnemyKilledEvent;
import be.howest.twentytwo.parametergame.model.event.listener.BaseEnemyKilledHandler;
import be.howest.twentytwo.parametergame.model.event.listener.BasePlayerHitHandler;
import be.howest.twentytwo.parametergame.model.event.listener.IEventListener;
import be.howest.twentytwo.parametergame.model.gamedata.HealthData;
import be.howest.twentytwo.parametergame.model.gamedata.PlayerData;

public class LevelUIFactory {

	private final SpriteBatch batch;
	private final Viewport viewport;
	private final Skin uiSkin;
	private final EventQueue events;

	public LevelUIFactory(SpriteBatch spriteBatch, EventQueue eventQueue, Viewport uiViewport, Skin uiSkin) {
		this.batch = spriteBatch;
		this.viewport = uiViewport;
		this.uiSkin = uiSkin;
		this.events = eventQueue;
	}

	public Stage createUI(Entity playerEntity) {
		Stage stage = new Stage(viewport, batch);
		stage.setDebugAll(ParameterGame.DEBUG_ENABLED);

		HealthComponent hp = HealthComponent.MAPPER.get(playerEntity);
		WeaponComponent wc = WeaponComponent.MAPPER.get(playerEntity);
		PlayerComponent pc = PlayerComponent.MAPPER.get(playerEntity);

		LabelStyle labelStyle = uiSkin.get("pressed", LabelStyle.class);

		Table root = new Table();
		root.setFillParent(true);

		Table time = new Table();
		time.top().left();

		root.add(time).expand().top().left();

		Table score = new Table();
		Label scoreLabel = new Label("Score: 0", labelStyle);
		events.register(EventEnum.ENEMY_KILLED, new ScoreUpdateHandler(scoreLabel, pc.getPlayerData()));
		events.register(EventEnum.PLAYER_PICKUP, new ScoreUpdateHandler(scoreLabel, pc.getPlayerData()));
		pc.getPlayerData().addObserver(new ScoreLabelObserver(scoreLabel));
		score.add(scoreLabel);

		root.add(score).expand().top();

		Table counter = new Table();
		counter.top().right();

		root.add(counter).expand().top().right();

		root.row();

		Table fill = new Table();
		root.add(fill).expand();

		root.row();

		Table map = new Table();
		map.bottom().left();

		root.add(map).expand();

		Table notifications = new Table();
		notifications.center();

		root.add(notifications).expand().bottom();

		Window shipStatusWindow = new Window("Ship Status", uiSkin);
		shipStatusWindow.getTitleLabel().setStyle(uiSkin.get("over", LabelStyle.class));
		shipStatusWindow.getTitleTable().padBottom(10f);
		shipStatusWindow.bottom().right();
		Table shipStatusTable = new Table();
		shipStatusTable.bottom().right();
		Label healthLabel = new Label("Health: " + hp.getHealthData().getHealth(), labelStyle);
		shipStatusTable.add(healthLabel);
		shipStatusTable.row();
		events.register(EventEnum.PLAYER_HIT, new PlayerHitHealthHandler(healthLabel));
		hp.getHealthData().addObserver(new HealthLabelObserver(healthLabel)); // -->
																				// doesn't
																				// work?
		// TODO: Weapons + DAMPING STATUS IN UI
		shipStatusWindow.add(shipStatusTable);

		root.add(shipStatusWindow).expand().bottom().right();

		stage.addActor(root);

		return stage;
	}

	private class PlayerHitHealthHandler extends BasePlayerHitHandler {

		private Label label;

		public PlayerHitHealthHandler(Label l) {
			this.label = l;
		}

		@Override
		public void handleEvent(PlayerHitEvent event) {
			if (event.getPlayerHealth().isInvulnerable()) {
				return;
			}
			label.setText("Health: " + (event.getPlayerHealth().getHealth() - event.getDamage()));
		}

	}

	private class ScoreUpdateHandler implements IEventListener {

		private Label label;
		private PlayerData playerData;

		public ScoreUpdateHandler(Label l, PlayerData playerData) {
			this.label = l;
			this.playerData = playerData;
		}
		
		@Override
		public void handle(IEvent event) {
			label.setText("Score: " + playerData.getScore());
		}

	}

	private class HealthLabelObserver implements Observer {

		private Label label;

		public HealthLabelObserver(Label label) {
			this.label = label;
		}

		@Override
		public void update(Observable o, Object arg) {
			System.out.println("HEALTH UPDATE");
			HealthData health = (HealthData) o;
			label.setText("Health: " + health.getHealth());
		}

	}

	private class ScoreLabelObserver implements Observer {
		private Label label;

		public ScoreLabelObserver(Label label) {
			this.label = label;
		}

		@Override
		public void update(Observable o, Object arg) {
			System.out.println("SCORE UPDATE (OBSERVER)");
			PlayerData player = (PlayerData) o;
			int score = Math.round(player.getScore());
			label.setText("Score: " + score);
		}
	}
}
