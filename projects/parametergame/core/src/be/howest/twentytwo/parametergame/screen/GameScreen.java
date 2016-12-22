package be.howest.twentytwo.parametergame.screen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.dataTypes.SettingsDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.factory.InputFactory;
import be.howest.twentytwo.parametergame.input.PlayerInputProcessor;
import be.howest.twentytwo.parametergame.model.PhysicsBodyEntityListener;
import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.CameraComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.component.SpriteComponent;
import be.howest.twentytwo.parametergame.model.component.TransformComponent;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.physics.collision.Constants;
import be.howest.twentytwo.parametergame.model.physics.collision.BaseContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.GravityContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.collision.PlayerContactProcessor;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;
import be.howest.twentytwo.parametergame.model.system.BackgroundRenderSystem;
import be.howest.twentytwo.parametergame.model.system.CameraSystem;
import be.howest.twentytwo.parametergame.model.system.MovementSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsDebugRenderSystem;
import be.howest.twentytwo.parametergame.model.system.PhysicsSystem;
import be.howest.twentytwo.parametergame.model.system.RenderSystem;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends BaseScreen {

	private World world;
	private PooledEngine engine;
	private Viewport viewport; // Needs to be saved for resizes
	private EventQueue eventQueue; // Have event queue out of engine for some ui
									// events.

	public static Entity mainPlayer = null;

	public GameScreen(ScreenContext context, PooledEngine engine, Viewport vp, EventQueue eventQueue) {
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
