package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.model.event.EventQueue;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SPGameScreen extends BaseScreen {

	private World world;
	private PooledEngine engine;
	private Viewport viewport; // Needs to be saved for resizes
	private EventQueue eventQueue;

	public static Entity mainPlayer = null;

	public SPGameScreen(ScreenContext context, PooledEngine engine, Viewport vp,
			EventQueue eventQueue) {
		super(context);
		this.engine = engine;
		this.viewport = vp;
		this.eventQueue = eventQueue;
	}

	@Override
	public void show() {
		// Music m = Gdx.audio.newMusic(Gdx.files.internal("hsmain.mp3"));
		// m.setVolume(0.2f);
		// m.play();
	}

	@Override
	public void render(float delta) {
		engine.update(delta);
		eventQueue.dispatch();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void pause() {
		// TODO: pause boolean --> stop engine update?

	}

	@Override
	public void resume() {
		// TODO: See pause();
	}

	@Override
	public void hide() {
		// TODO: GameScreen - hide()
	}

	@Override
	public void dispose() {
		super.dispose();
		world.dispose();
	}

}
