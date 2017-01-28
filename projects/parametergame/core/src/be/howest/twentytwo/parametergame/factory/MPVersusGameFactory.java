package be.howest.twentytwo.parametergame.factory;

import java.util.Map;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.dataTypes.SettingsDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.model.component.PlayerComponent;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.screen.MPSplitGameScreen;
import be.howest.twentytwo.parametergame.ui.data.LoadoutSelectionData;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MPVersusGameFactory extends BaseGameFactory {

	private LoadoutSelectionData playerOneSelections, playerTwoSelections;

	public MPVersusGameFactory(ScreenContext context, String levelFile) {
		super(context, levelFile);
	}

	@Override
	public void setSelections(LoadoutSelectionData selections) {
		// This is obviously very bad architecture. But Time > Quality in this case I'm afraid...
		if(playerOneSelections == null) {
			// GET PLAYER 1 STUFF
			playerOneSelections = selections;
		} else {
			// GET PLAYER 2 STUFF
			playerTwoSelections = selections;
		}
	}

	@Override
	public Screen createGameScreen() {
		// LEFT SIDE
		ScreenViewport sv = new ScreenViewport();
		sv.setUnitsPerPixel(0.35f);
		Viewport leftGameViewport = sv;

		Viewport leftUIViewport = new ScreenViewport();
		EventQueue leftEvtQueue = new EventQueue();

		LevelFactory levelFactory = new LevelFactory();
		PooledEngine leftEngine = levelFactory.createWorld(getContext(), leftGameViewport,
				leftUIViewport, leftEvtQueue, getLevelFile(), playerOneSelections);

		// INPUT
		UserDataI user = getContext().getUser();
		SettingsDataI settings = getContext().getFileService().loadSettings("settings.ini", user);
		settings.addPlayer(user);
		Map<String, String> keyActionMap = settings.getKeyBinds(user);
		levelFactory.attachKeyboardInput(leftEngine, keyActionMap);

		// RIGHT SIDE
		sv = new ScreenViewport();
		sv.setUnitsPerPixel(0.35f);
		Viewport rightGameViewport = sv;

		Viewport rightUIViewport = new ScreenViewport();
		EventQueue rightEvtQueue = new EventQueue();

		PooledEngine rightEngine = levelFactory.createWorld(getContext(), rightGameViewport,
				rightUIViewport, rightEvtQueue, getLevelFile(), playerTwoSelections);

		levelFactory.attachControllerInput(rightEngine);
		
		return new MPSplitGameScreen(getContext(), leftEngine, leftEvtQueue, leftGameViewport,
				leftUIViewport, rightEngine, rightEvtQueue, rightGameViewport, rightUIViewport);
	}
}
