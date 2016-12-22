package be.howest.twentytwo.parametergame.factory;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;

import be.howest.twentytwo.parametergame.model.ai.BasicAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.IAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.IAIShootBehaviour;
import be.howest.twentytwo.parametergame.model.ai.NullAIMoveBehaviour;

public class AIMoveBehaviourFactory {

	private final Map<String, IAIMoveBehaviour> map;

	public AIMoveBehaviourFactory() {
		map = new HashMap<String, IAIMoveBehaviour>();
	}

	public IAIMoveBehaviour createBehaviour(String behaviour) {
		if(map.containsKey(behaviour)) {
			return map.get(behaviour);
		}
		switch (behaviour) {
		// Add cases here
			case "ggwp":
				// Add cases here, for each case:
				// create new behaviour for string
				// add behaviour to the map
				// return the behaviour
				return new BasicAIMoveBehaviour(9999f);
			default:
				Gdx.app.error("IAIMoveFactory",
						"ERR: Could not produce behaviour, returned NullBehaviour instead");
				return new NullAIMoveBehaviour();
		}
	}
}
