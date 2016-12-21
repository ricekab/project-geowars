package be.howest.twentytwo.parametergame.model.ai;

// @author Ward Van den Berghe

import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.physics.message.LinearForceMessage;
import be.howest.twentytwo.parametergame.utils.VectorMath;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


public class BrutalizerAIMoveBehaviour implements IAIMoveBehaviour{
    private final float Speed; //225.0f;
    private final float minDistance; //20.0f
    private final float maxDistance; //25.0f

    public BrutalizerAIMoveBehaviour(float Speed, float minDistance, float maxDistance) {
        this.Speed = Speed;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
    }
    
    @Override
    public float getOptimalDistance() {
        float averageDistance = maxDistance - ((maxDistance - minDistance)/2);
        return averageDistance;
    }

    @Override
    public void calculateMove(MovementComponent movement, Body ai, Vector2 target) {        
        //Collection<IPhysicsMessage> events --> not available here TODO fix
        // Move enemy in direction of player
        
        // Turn enemy to look in player direction
        Vector2 directionToPlayer = VectorMath.subtract(target, ai.getPosition());
        Vector2 normalizedDirectionToPlayer = directionToPlayer.cpy().nor();
        
        ai.setTransform(ai.getPosition(), (float)Math.atan2(-normalizedDirectionToPlayer.x, normalizedDirectionToPlayer.y));
        
        //Move toward player if within minimum distance
        //To close
        if(directionToPlayer.x * directionToPlayer.x + directionToPlayer.y * directionToPlayer.y < minDistance * minDistance )
        {            
            //events.add(new LinearForceMessage(ai, normalizedDirectionToPlayer.scl(-Speed)));
        }
        //To far
        else if(directionToPlayer.x * directionToPlayer.x + directionToPlayer.y * directionToPlayer.y > maxDistance * maxDistance )
        {
            //events.add(new LinearForceMessage(ai, normalizedDirectionToPlayer.scl(Speed)));
        }
    }

}
