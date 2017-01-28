package be.howest.twentytwo.parametergame.factory;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.model.event.EventEnum;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.IEvent;
import be.howest.twentytwo.parametergame.model.event.game.EnemyKilledEvent;
import be.howest.twentytwo.parametergame.model.event.listener.IEventListener;
import be.howest.twentytwo.parametergame.model.event.listener.PlayerKilledEndGameListener;
import be.howest.twentytwo.parametergame.screen.SPGameScreen;
import be.howest.twentytwo.parametergame.ui.data.LoadoutSelectionData;

public class SPGameFactory extends BaseGameFactory {

	private LoadoutSelectionData loadout;

	public SPGameFactory(ScreenContext context, String levelFile) {
		super(context, levelFile);
	}

	@Override
	public void setSelections(LoadoutSelectionData selections) {
		this.loadout = selections;
	}

	@Override
	public Screen createGameScreen() {

		// VIEWPORT / CAM
		// A) Fitviewport = letterboxing (Also a bit easier to debug for atm)
		// 240 135 // 320 180 // 400 225
		// Size in world units
		Viewport gameViewport = new FitViewport(400f, 225f);

		/*
		 * B) ScreenViewport = full size without stretching, but shown field is different based on
		 * aspect ratio --> possible balance concern
		 */
		// ScreenViewport sv = new ScreenViewport();
		// sv.setUnitsPerPixel(0.35f);
		// gameViewport = sv;

		Viewport uiViewport = new ScreenViewport();

		LevelFactory levelFactory = new LevelFactory();
		EventQueue eventQueue = new EventQueue();

		PooledEngine engine = levelFactory.createWorld(getContext(), gameViewport, uiViewport,
				eventQueue, getLevelFile(), loadout);

		registerSoundEvents(eventQueue);
		registerGameEvents(eventQueue);

		return new SPGameScreen(getContext(), engine, eventQueue, gameViewport, uiViewport);
	}

	private void registerSoundEvents(EventQueue eventQueue) {
		// register event handlers on event queue to send sound messages.
		// Will need another chain of objects to filter the messages
		// Eg. PlayerHit --> BulletHitSound or CrashedWithEnemySound or ...
	}

	private void registerGameEvents(EventQueue eventQueue) {
		eventQueue.register(EventEnum.ENEMY_KILLED, new IEventListener() {

			@Override
			public void handle(IEvent event) {
				EnemyKilledEvent e = (EnemyKilledEvent) event;
				// Add score points and stuff.
			}
		});

		eventQueue.register(EventEnum.PLAYER_KILLED, new PlayerKilledEndGameListener());
	}
}
