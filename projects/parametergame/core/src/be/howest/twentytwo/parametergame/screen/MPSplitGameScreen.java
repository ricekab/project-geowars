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
	//    |\___/|
	//    )     (             .              '
	//   =\     /=
	//     )===(       *
	//    /     \
	//    |     |
	//   /       \
	//   \       /
	//_/\_/\_/\__  _/_/\_/\_/\_/\_/\_/\_/\_/\_/\_
	//|  |  |  |( (  |  |  |  |  |  |  |  |  |  |
	//|  |  |  | ) ) |  |  |  |  |  |  |  |  |  |
	//|  |  |  |(_(  |  |  |  |  |  |  |  |  |  |
	//|  |  |  |  |  |  |  |  |  |  |  |  |  |  |
	//|  |  |  |  |  |  |  |  |  |  |  |  |  |  |
	private World world;
	private PooledEngine engineLeft, engineRight;
	private Viewport viewportLeft, viewportRight;
	private EventQueue eventQueueLeft, eventQueueRight;

	public static Entity mainPlayer = null;

	public MPSplitGameScreen(ScreenContext context, PooledEngine engineLeft, Viewport vpLeft,
			EventQueue eventQueueLeft, PooledEngine engineRight, Viewport vpRight,
			EventQueue eventQueueRight) {
		super(context);
		this.engineLeft = engineLeft;
		this.viewportLeft = vpLeft;
		this.eventQueueLeft = eventQueueLeft;
		this.engineRight = engineRight;
		this.viewportRight = vpRight;
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
		engineRight.update(delta);
		eventQueueLeft.dispatch();
		eventQueueRight.dispatch();
	}

	@Override
	public void resize(int width, int height) {
		viewportLeft.update(width/2, height);
		viewportRight.update(width/2, height);
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
