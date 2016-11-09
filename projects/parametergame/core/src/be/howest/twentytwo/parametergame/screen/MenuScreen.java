package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ParameterGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen extends BaseScreen {

	private Stage uiStage;

	public MenuScreen(ParameterGame game) {
		super(game);
	}

	@Override
	public void show() {
		// Loading ui skin
		getGame().assetMgr.load("ui/uiskin.json", Skin.class);
		getGame().assetMgr.finishLoading();
		Skin skin = getGame().assetMgr.get("ui/uiskin.json", Skin.class);
		// Create main menu stage
		uiStage = new Stage(new ScreenViewport(), getGame().batch);
		Table root = new Table();
		root.setFillParent(true);
		uiStage.addActor(root);
		// Table - Children
		TextButtonStyle textBtnStyle = skin.get("default", TextButtonStyle.class);

		TextButton startBtn = new TextButton("Start Game", textBtnStyle);
		startBtn.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				getGame().setScreen(new GameScreen(getGame()));
				dispose();
			}
		});
		startBtn.pad(5f, 10f, 5f, 10f);
		root.add(startBtn).pad(5f, 5f, 5f, 5f);
		root.row();

		TextButton exitBtn = new TextButton("Exit Game", textBtnStyle);
		exitBtn.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		exitBtn.pad(5f, 10f, 5f, 10f);
		root.add(exitBtn).pad(5f);

		// Set stage to receive input events
		Gdx.input.setInputProcessor(uiStage);

		// Debug
		root.setDebug(ParameterGame.DEBUG_ENABLED);

		// Unload any resources we no longer need
		// game.assetMgr.unload("ui/uiskin.json"); // Or maybe keep for game screen?
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// stage.act(delta);
		uiStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("resize");
	}

	@Override
	public void pause() {
		System.out.println("pause");
	}

	@Override
	public void resume() {
		System.out.println("resume");
	}

	@Override
	public void hide() {
		System.out.println("hide");
		this.dispose();
	}

	@Override
	public void dispose() {
		System.out.println("dispose");
		uiStage.dispose();
	}

}
