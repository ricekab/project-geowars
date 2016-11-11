package be.howest.twentytwo.parametergame.dataTypes;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class SpawnPoolData {
	
	private List<ClusterData> clusters;	//Put one of each kind of cluster in here, with amount X. If X is decreased, check the value. if it reaches 0, remove from here, and also from chances. recalculate total chance
	private Map<ClusterData, Float > chances;	//keeps track of each clusters chance to get elected
	
	public ClusterData getRandomCluster() {
		
	}

}
