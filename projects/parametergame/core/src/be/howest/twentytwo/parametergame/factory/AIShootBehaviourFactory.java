package be.howest.twentytwo.parametergame.factory;

import be.howest.twentytwo.parametergame.model.ai.BasicAIShootBehaviour;
import java.util.HashMap;
import java.util.Map;

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
			case "Scouter":
                            // Add cases here, for each case:
                            // create new behaviour for string
                            // add behaviour to the map
                            // return the behaviour
                            IAIShootBehaviour scouter = new BasicAIShootBehaviour(60, 80);
                            map.put(behaviour, scouter);
                            return scouter;
                        case "Brutalizer":
                            IAIShootBehaviour brutalizer = new BasicAIShootBehaviour(120, 50);
                            map.put(behaviour, brutalizer);
                            return brutalizer;
                        case "Drone":
                            IAIShootBehaviour drone = new BasicAIShootBehaviour(60, 70);
                            map.put(behaviour, drone);
                            return drone;
                        default:
                            Gdx.app.error("IAIMoveFactory", "ERR: Could not produce behaviour, returned NullBehaviour instead");

                            return new NullAIShootBehaviour();
		}
	}
}
