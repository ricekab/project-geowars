package be.howest.twentytwo.parametergame.screen;

import java.util.ArrayList;
import java.util.Collection;

import be.howest.twentytwo.parametergame.ScreenContext;
import be.howest.twentytwo.parametergame.dataTypes.DifficultyDataI;
import be.howest.twentytwo.parametergame.dataTypes.DroneDataI;
import be.howest.twentytwo.parametergame.dataTypes.PlayerShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.ShipDataI;
import be.howest.twentytwo.parametergame.dataTypes.UserDataI;
import be.howest.twentytwo.parametergame.factory.BaseGameFactory;
import be.howest.twentytwo.parametergame.service.db.IDataService;
import be.howest.twentytwo.parametergame.ui.data.LoadoutSelectionData;
import be.howest.twentytwo.parametergame.ui.factory.CheckBoxFactory;
import be.howest.twentytwo.parametergame.ui.factory.TextButtonFactory;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

public class ShipLoadoutScreen extends BaseUIBackgroundScreen {

	private ButtonGroup<CheckBox> shipGroup, droneGroup, difficultyGroup;

	private final BaseGameFactory factory;

	public ShipLoadoutScreen(ScreenContext context, Engine engine, ChangeListener confirmListener,
			BaseGameFactory gameFactory, String title) {
		super(context, engine);
		this.factory = gameFactory;

		TextButtonFactory tbf = new TextButtonFactory(getSkin().get(TextButtonStyle.class));
		CheckBoxFactory cbf = new CheckBoxFactory(getSkin().get(CheckBoxStyle.class));

		UserDataI user = getContext().getUser();
		IDataService dataService = getContext().getDataService();

		Table root = getRoot();

		Table rowTable;
		// Title label
		rowTable = new Table();
		rowTable.add(new Label(title, getSkin().get("pressed", LabelStyle.class))).fill().align(Align.center);
		root.add(rowTable);
		root.row();
		
		// Retrieve and show ships
		Collection<PlayerShipDataI> ships = dataService.getPlayerShips(user);
		shipGroup = new ButtonGroup<CheckBox>();
		rowTable = new Table();
		root.add(rowTable);
		rowTable.add(new Label("Ship: ", getSkin().get(LabelStyle.class)));
		for (PlayerShipDataI ship : ships) {
			CheckBox c = cbf.createCheckBox(String.format("%s [%s]", ship.getId(), ship
					.getShipData().getName()));
			c.setUserObject(ship);
			shipGroup.add(c);
			rowTable.add(c);
		}
		root.row();

		// Retrieve and show drones
		Collection<DroneDataI> drones = dataService.getDrones(user);
		droneGroup = new ButtonGroup<CheckBox>();
		droneGroup.setMaxCheckCount(2); // TODO: Based on ship level
		rowTable = new Table();
		root.add(rowTable);
		rowTable.add(new Label("Drones: ", getSkin().get(LabelStyle.class)));
		for (DroneDataI drone : drones) {
			CheckBox c = cbf.createCheckBox(drone.getID());
			c.setUserObject(drone);
			droneGroup.add(c);
			rowTable.add(c);
		}
		root.row();

		// Retrieve difficulties
		Collection<DifficultyDataI> difficulties = dataService.getDifficulties();
		difficultyGroup = new ButtonGroup<CheckBox>();
		rowTable = new Table();
		root.add(rowTable);
		rowTable.add(new Label("Difficulty: ", getSkin().get(LabelStyle.class)));
		for (DifficultyDataI diff : difficulties) {
			CheckBox c = cbf.createCheckBox(diff.getID());
			c.setUserObject(diff);
			difficultyGroup.add(c);
			rowTable.add(c);
		}
		root.row();

		rowTable = new Table();
		root.add(rowTable);

		TextButton back = tbf.createButton("Back");
		back.addListener(new BackChangeListener());
		rowTable.add(back);
		TextButton next = tbf.createButton("Continue");
		next.addListener(new NextChangeListener());
		next.addListener(confirmListener);
		rowTable.add(next);
	}

	public ShipLoadoutScreen(ScreenContext context, Engine engine, ChangeListener confirmListener,
			BaseGameFactory gameFactory){
		this(context, engine, confirmListener, gameFactory, "Loadout selection");
	}
	
	@Override
	public void show() {
	}

	private class NextChangeListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			PlayerShipDataI ship = (PlayerShipDataI) shipGroup.getChecked().getUserObject();
			Collection<DroneDataI> drones = new ArrayList<DroneDataI>();
			for (CheckBox cb : droneGroup.getAllChecked()) {
				drones.add((DroneDataI) cb.getUserObject());
			}
			DifficultyDataI difficulty = (DifficultyDataI) difficultyGroup.getChecked()
					.getUserObject();
			factory.setSelections(new LoadoutSelectionData(ship, drones, difficulty));
		}
	}

	private class BackChangeListener extends ChangeListener {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			getContext().setScreen(new MenuScreen(getContext(), getEngine()));
			dispose();
		}
	}

}
