package be.howest.twentytwo.parametergame.model.time;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.screen.MenuScreen;

public class MainMenuCallback implements ITimeoutCallback {

	private ScreenContext context;

	public MainMenuCallback(ScreenContext context) {
		this.context = context;
	}

	@Override
	public void execute() {
		context.setScreen(new MenuScreen(context));
	}

}
