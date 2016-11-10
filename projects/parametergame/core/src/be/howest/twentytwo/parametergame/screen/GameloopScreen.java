package be.howest.twentytwo.parametergame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import be.howest.twentytwo.parametergame.ParameterGame;

@Deprecated
public class GameloopScreen extends BaseScreen {

	/** Time (in seconds) between game logic updates. */
	private static final float TIMESTEP = 1 / 50f;
	private static final int MAX_FRAME_SKIPS = 5;

	private int frameSkipCount;
	/** Time (in seconds) since last game logic update */
	private float elapsed;

	// UI & View
	private Viewport viewport;
	private Camera cam;
	private Stage uiStage;

	// Statistics
	private static final float STATS_UPDATE_PERIOD = 1 / 10f;
	private float statisticsDelta;
	private long startTime;
	private long drawTimes[];
	private int drawTimesIdx;
	private static int FRAME_TIMES_STORED = 10;
	private int drawCalls;
	private int updateCalls;

	public GameloopScreen(ParameterGame game) {
		super(game);
		frameSkipCount = 0;
		elapsed = 0f;
		drawCalls = 0;
		updateCalls = 0;
		drawTimesIdx = 0;
		statisticsDelta = 0f;
		drawTimes = new long[FRAME_TIMES_STORED];
	}

	@Override
	public void show() {
		System.out.println("GLScreen - Show");
		// Load stuff
		// Assetmgr stuff --> If a lot of assets, move loading to some intermediary load screen

		// Setup game world
		viewport = new FitViewport(50f, 50f); // Viewport size (in world units)
		cam = viewport.getCamera();
		initScene2DUI();

		startTime = System.nanoTime();
	}

	private void initScene2DUI() {
		uiStage = new Stage(viewport, getGame().batch);
		Skin skin = getGame().assetMgr.get("ui/uiskin.json", Skin.class);
		Table root = new Table();
	}

	@Override
	public void render(float delta) {
		elapsed += delta;
		// INPUT
		processInput();
		// UPDATE
		while (elapsed > TIMESTEP && frameSkipCount++ < MAX_FRAME_SKIPS) {
			update();
			elapsed -= TIMESTEP;
		}
		frameSkipCount = 0;
		// DRAW / RENDER
		// If we're drawing due to too many frame skips, elapsed may be too large.
		if(elapsed > TIMESTEP) {
			draw(0f);
		} else {
			draw(elapsed);
		}

		// STATISTICS
		statisticsDelta += delta;
		if(statisticsDelta > STATS_UPDATE_PERIOD) {
			statisticsDelta = 0;
			long currentTime = System.nanoTime();
			long runningTime = (currentTime - startTime);
			getGame().logger.info("UPS: " + (float) updateCalls / runningTime * 1000000000 + "(Target: "
					+ (1f / TIMESTEP) + ")");
			getGame().logger.info("FPS: " + (float) drawCalls / runningTime * 1000000000);
			float avgFPS = (float) FRAME_TIMES_STORED / (currentTime - drawTimes[drawTimesIdx]) * 1000000000;
			getGame().logger.info("AVG FPS: " + avgFPS);
			getGame().logger.info("===");
		}
	}

	protected void processInput() {
		// Only applicable if we're going to be polling. Will probably switch to
		// InputProcessors (Stage + game)
	}

	protected void update() {
		updateCalls++;
	}

	protected void draw(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Update statistics
		drawTimes[drawTimesIdx++] = System.nanoTime();
		drawTimesIdx %= FRAME_TIMES_STORED;
		drawCalls++;
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void pause() {
		System.out.println("GLScreen - Pause");
	}

	@Override
	public void resume() {
		System.out.println("GLScreen - Resume");

	}

	@Override
	public void hide() {
		this.dispose();
		System.out.println("GLScreen - Hide");
	}

	@Override
	public void dispose() {
		uiStage.dispose();
		System.out.println("GLScreen - Dispose");
	}

}
