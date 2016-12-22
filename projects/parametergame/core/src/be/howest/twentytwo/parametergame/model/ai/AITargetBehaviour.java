package be.howest.twentytwo.parametergame.model.ai;

// @author Ward Van den Berghe

import be.howest.twentytwo.parametergame.model.component.AIComponent;
import be.howest.twentytwo.parametergame.model.physics.aabb.RetrievalQuery;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.List;


public class AITargetBehaviour {
    private final Body sourceBody;
    private final float range;
    private final short mask;
    private final AIComponent ai;

    public AITargetBehaviour(Body sourceBody, float range, short mask, AIComponent ai) {
        /**
         * expects a mask to filter out enemies of world (short)
         */
        this.sourceBody = sourceBody;
        this.range = range;
        this.mask = mask;
        this.ai = ai;
    }
    
    private World getWorld(){
        return sourceBody.getWorld();
    }
    
    public void killAi(AIComponent ai){
        List<Body> enemies = retrieveAIinRange();
        if(!enemies.isEmpty()){
            ai.getShootBehaviour().setCanShoot(true);
        }
    }
    
    // Retrieve bodies in range
    public List<Body> retrieveAIinRange() {
        Vector2 pos = sourceBody.getPosition();
        RetrievalQuery retrieval = new RetrievalQuery(mask);
        sourceBody.getWorld().QueryAABB(retrieval, pos.x - range, pos.y - range, pos.x + range, pos.y + range);
        List<Body> enemies = new ArrayList<>();
        Body body;
        for (Fixture fix : retrieval.getFixtures()) {
            body = fix.getBody();
            if (!enemies.contains(body) && sourceBody.getWorldCenter().dst(body.getWorldCenter()) <= range) {
                    enemies.add(body);                    
            }
        }
        return enemies;
    }
}
