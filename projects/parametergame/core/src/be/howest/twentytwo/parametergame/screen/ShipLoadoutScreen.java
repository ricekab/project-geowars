package be.howest.twentytwo.parametergame.screen;

import java.util.ArrayList;
import java.util.Collection;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.service.db.IDataService;
import be.howest.twentytwo.parametergame.ui.factory.CheckBoxFactory;
import be.howest.twentytwo.parametergame.ui.factory.TextButtonFactory;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ShipLoadoutScreen extends BaseUIBackgroundScreen {

	private ButtonGroup<CheckBox> shipGroup, droneGroup, difficultyGroup;

	public ShipLoadoutScreen(ScreenContext context, Engine engine, ChangeListener confirmListener) {
		super(context, engine);
		init(confirmListener);
	}

	public ShipLoadoutScreen(ScreenContext context, ChangeListener confirmListener) {
		super(context);
		init(confirmListener);
	}

	private void init(ChangeListener confirmListener) {
		TextButtonFactory tbf = new TextButtonFactory(getSkin().get(TextButtonStyle.class));
		CheckBoxFactory cbf = new CheckBoxFactory(getSkin().get(CheckBoxStyle.class));

		UserDataI user = getContext().getUser();
		IDataService dataService = getContext().getDataService();

		Table root = getRoot();

		// Retrieve and show ships
		Collection<ShipDataI> ships = dataService.getShips(user);
		shipGroup = new ButtonGroup<CheckBox>();
		// shipGroup.setMaxCheckCount(1); // These are default values
		// shipGroup.setMinCheckCount(1);
		for (ShipDataI ship : ships) {
			CheckBox c = cbf.createCheckBox(ship.getName());
			shipGroup.add(c);
			root.add(c);
		}
		root.row();

		// Retrieve and show drones
		Collection<DroneDataI> drones = dataService.getDrones(user);
		droneGroup = new ButtonGroup<CheckBox>();
		for (DroneDataI drone : drones) {
			CheckBox c = cbf.createCheckBox(drone.getID());
			droneGroup.add(c);
			root.add(c);
		}
		root.row();

		// Retrieve difficulties
		// TODO: DIFFICULTIES WHERE?
		Collection<DifficultyDataI> difficulties = new ArrayList<DifficultyDataI>();
		difficultyGroup = new ButtonGroup<CheckBox>();
		for (DifficultyDataI diff : difficulties) {
			CheckBox c = cbf.createCheckBox(diff.getID());
			difficultyGroup.add(c);
			root.add(c);
		}
		root.row();


		TextButton next = tbf.createButton("Continue", confirmListener);
		root.add(next);
	}

	@Override
	public void show() {

	}

}
