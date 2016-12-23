package be.howest.twentytwo.parametergame.screen;

import be.howest.twentytwo.parametergame.ScreenContext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;

public class ControllerTestScreen extends BaseScreen {

	public ControllerTestScreen(ScreenContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		System.out.println("show");
		for(Controller c : Controllers.getControllers()){
			System.out.println(c.getName());
		}
		Controllers.addListener(new ControllerListener() {
			
			@Override
			public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
				System.out.println(String.format("ySliderMoved: %d, %b", sliderCode, value));
				return false;
			}
			
			@Override
			public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
				System.out.println(String.format("xSliderMoved: %d, %b", sliderCode, value));
				return false;
			}
			
			@Override
			public boolean povMoved(Controller controller, int povCode, PovDirection value) {
				System.out.println(String.format("povMoved: %d", povCode));
				System.out.println(value.toString());
				return false;
			}
			
			@Override
			public void disconnected(Controller controller) {
				System.out.println("disco");
			}
			
			@Override
			public void connected(Controller controller) {
				System.out.println("conn");
			}
			
			@Override
			public boolean buttonUp(Controller controller, int buttonCode) {
				System.out.println(String.format("btnUp: %d", buttonCode));
				return false;
			}
			
			@Override
			public boolean buttonDown(Controller controller, int buttonCode){
				System.out.println(String.format("btnDown: %d", buttonCode));
				return false;
			}
			
			@Override
			public boolean axisMoved(Controller controller, int axisCode, float value) {
				System.out.println(String.format("axisMoved: %d, %f", axisCode, value));
				return false;
			}
			
			@Override
			public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
				System.out.println(String.format("accelerometerMoved: %d, %s", accelerometerCode, value.toString()));
				return false;
			}
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

}
