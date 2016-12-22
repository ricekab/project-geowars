package be.howest.twentytwo.parametergame.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import be.howest.twentytwo.parametergame.model.ai.IAIShootBehaviour;
import be.howest.twentytwo.parametergame.model.ai.NullAIShootBehaviour;

import com.badlogic.gdx.Gdx;

public class AIShootBehaviourFactory {
	
	private final Map<String, IAIShootBehaviour> map;
	
	public AIShootBehaviourFactory() {
		map = new HashMap<String, IAIShootBehaviour>();
	}

	public IAIShootBehaviour createBehaviour(String behaviour){
		if(map.containsKey(behaviour)){
			return map.get(behaviour);
		}
		switch (behaviour){
			case "ggwp":
				// Add cases here, for each case:
				// create new behaviour for string
				// add behaviour to the map
				// return the behaviour
				default:
					Gdx.app.error("IAIMoveFactory", "ERR: Could not produce behaviour, returned NullBehaviour instead");
				
					return new NullAIShootBehaviour();
		}
	}
}
