package be.howest.twentytwo.parametergame.model.system;

import java.util.Collection;

import be.howest.twentytwo.parametergame.ui.message.UIMessage;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class UISystem extends EntitySystem {

	public static final int PRIORITY = 1;
	
	private final Collection<UIMessage> uiMessages;
	private final Stage stage;
	
	public UISystem(Collection<UIMessage> uiMessages, Stage stage) {
		super(PRIORITY);
		this.uiMessages = uiMessages;
		this.stage = stage;
	}
	
	@Override
	public void update(float deltaTime) {
		for(UIMessage msg : uiMessages){
			// TODO: Handle UI Messages 
		}
		stage.act(deltaTime);
		stage.draw();
	}
	
}
