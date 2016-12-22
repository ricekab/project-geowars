package be.howest.twentytwo.parametergame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import be.howest.twentytwo.parametergame.ParameterGame;
import be.howest.twentytwo.parametergame.ScreenContext;

/**
 * Base class for screens that only use scene2d.ui for displaying information and handle input.
 * 
 * Handles setting up input, viewport and the root table.
 */
public abstract class BaseUIScreen extends BaseScreen {

	private Stage stage;
	private Viewport viewport;
	private Skin skin;
	private Table root;

	public BaseUIScreen(ScreenContext context) {
		super(context);
		if(!getContext().getAssetManager().isLoaded(ParameterGame.UI_SKIN, Skin.class)){
			getContext().getAssetManager().load(ParameterGame.UI_SKIN, Skin.class);
			getContext().getAssetManager().finishLoading();
		}
		skin = getContext().getAssetManager().get(ParameterGame.UI_SKIN, Skin.class);
		viewport = new ScreenViewport();
		stage = new Stage(viewport, getContext().getSpriteBatch());

		root = new Table();
		root.setFillParent(true);
		root.setDebug(ParameterGame.DEBUG_ENABLED, true);
		stage.addActor(root);

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		if(!preDraw(delta)){
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		};
		stage.act(delta);
		stage.draw();
		postDraw(delta);
	}
	
	/**
	 * Return true if pre stage drawing is done so the screen doesn't have to clear.
	 */
	public boolean preDraw(float delta){
		return false;
	}
	
	public void postDraw(float delta){
		return;
	}

	@Override
	public void hide() {
		this.dispose();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	public Stage getStage() {
		return stage;
	}

	public Viewport getViewport() {
		return viewport;
	}

	public Skin getSkin() {
		return skin;
	}

	public Table getRoot() {
		return root;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public void setRoot(Table root) {
		this.root = root;
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

}
