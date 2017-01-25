package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.model.event.EventQueue;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MPSplitGameScreen extends BaseScreen {

	// If you see this, sorry for the duplication, but deadlines...
	// Maybe this cat will make you feel better about this mess.
	// |\___/|
	// ) ( . '
	// =\ /=
	// )===( *
	// / \
	// | |
	// / \
	// \ /
	// _/\_/\_/\__ _/_/\_/\_/\_/\_/\_/\_/\_/\_/\_
	// | | | |( ( | | | | | | | | | |
	// | | | | ) ) | | | | | | | | | |
	// | | | |(_( | | | | | | | | | |
	// | | | | | | | | | | | | | | |
	// | | | | | | | | | | | | | | |
	private World world;
	private PooledEngine engineLeft, engineRight;
	private Viewport viewportLeft, viewportRight, uiViewportLeft, uiViewportRight;
	private EventQueue eventQueueLeft, eventQueueRight;

	public static Entity mainPlayer = null;

	public MPSplitGameScreen(ScreenContext context, PooledEngine engineLeft,
			EventQueue eventQueueLeft, Viewport vpLeft, Viewport uiVpLeft,
			PooledEngine engineRight, EventQueue eventQueueRight, Viewport vpRight,
			Viewport uiVpRight) {
		super(context);
		this.engineLeft = engineLeft;
		this.viewportLeft = vpLeft;
		this.uiViewportLeft = uiVpLeft;
		this.eventQueueLeft = eventQueueLeft;
		this.engineRight = engineRight;
		this.viewportRight = vpRight;
		this.uiViewportRight = uiVpRight;
		this.eventQueueRight = eventQueueRight;
	}

	@Override
	public void show() {
		// Music m = Gdx.audio.newMusic(Gdx.files.internal("hsmain.mp3"));
		// m.setVolume(0.2f);
		// m.play();
	}

	@Override
	public void render(float delta) {
		engineLeft.update(delta);
		eventQueueLeft.dispatch();
//		engineRight.update(delta);
//		eventQueueRight.dispatch();
	}

	@Override
	public void resize(int width, int height) {
		/*
		 * 
		 * gameViewport.update(width/2, height); uiViewport.update(width/2, height, true);
		 * gameViewport.setScreenX(width/2);
		 */

		viewportLeft.update(width / 2, height);
		uiViewportLeft.update(width / 2, height, true);
		viewportLeft.setScreenX(0);
		viewportLeft.apply();
		viewportRight.update(width / 2, height);
		uiViewportRight.update(width / 2, height, true);
		viewportRight.setScreenX(width / 2);
		viewportRight.apply();

	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		super.dispose();
		world.dispose();
	}

}
