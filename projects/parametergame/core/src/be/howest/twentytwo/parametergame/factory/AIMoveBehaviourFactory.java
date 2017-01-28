package be.howest.twentytwo.parametergame.factory;

import java.util.HashMap;
import java.util.Map;

import be.howest.twentytwo.parametergame.model.ai.BasicAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.IAIMoveBehaviour;
import be.howest.twentytwo.parametergame.model.ai.NullAIMoveBehaviour;

import com.badlogic.gdx.Gdx;

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
			case "Scouter":
                            // create new behaviour for string
                            // add behaviour to the map
                            // return the behaviour
                            IAIMoveBehaviour scouter = new BasicAIMoveBehaviour(75f);
                            map.put(behaviour, scouter);
                            return scouter;
                        case "Drone":
                            IAIMoveBehaviour drone = new BasicAIMoveBehaviour(5f);
                            map.put(behaviour, drone);
                            return drone;
                        case "Brutalizer":
                            IAIMoveBehaviour brutalizer = new BasicAIMoveBehaviour(50f);
                            map.put(behaviour, brutalizer);
                            return brutalizer;
                        case "Suicider":
                            IAIMoveBehaviour suicider = new BasicAIMoveBehaviour(0f);
                            map.put(behaviour, suicider);
                            return suicider;
                        case "SuicideSquad":
                            IAIMoveBehaviour suicideSquad = new BasicAIMoveBehaviour(0f);
                            map.put(behaviour, suicideSquad);
                            return suicideSquad;
			default:
                            Gdx.app.error("AIMoveFactory",
                            		"ERR: Could not produce behaviour, returned NullBehaviour instead. BehaviourString: " + behaviour);
                            return new NullAIMoveBehaviour();
		}
	}
}
