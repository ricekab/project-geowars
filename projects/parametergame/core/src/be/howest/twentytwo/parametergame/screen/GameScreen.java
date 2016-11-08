package be.howest.twentytwo.parametergame.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import be.howest.twentytwo.parametergame.ParameterGame;

public class GameScreen extends BaseScreen {
	
	private World world;
	private PooledEngine engine;

	public GameScreen(ParameterGame game) {
		super(game);
		init();
	}
	
	private void init(){
		engine = new PooledEngine();	// NOTE: engine.createEntity() to get the pooled object.
		world = new World(new Vector2(0f , 0f), true);	// 0g world
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
