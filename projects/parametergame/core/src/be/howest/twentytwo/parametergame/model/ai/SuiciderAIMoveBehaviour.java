package be.howest.twentytwo.parametergame.model.ai;

// @author Ward Van den Berghe

import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.physics.message.LinearForceMessage;
import be.howest.twentytwo.parametergame.utils.VectorMath;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;


public class SuiciderAIMoveBehaviour implements IAIMoveBehaviour{
    private final float Speed; // = 200.0f;
    private final float explodingDistance; // = 5.0f;
    private final float minDistance; // = 20.0f;

    public SuiciderAIMoveBehaviour(float Speed, float explodingDistance, float minDistance) {
        this.Speed = Speed;
        this.explodingDistance = explodingDistance;
        this.minDistance = minDistance;
    }
    
    @Override
    public float getOptimalDistance() {
        return explodingDistance;
    }

    @Override
    public void calculateMove(MovementComponent movement, Body ai, Vector2 target) {       
        // Mainplayer used for testing,                
        Vector2 directionToPlayer = VectorMath.subtract(target, ai.getPosition());
        Vector2 normalizedDirectionToPlayer = directionToPlayer.cpy().nor();
        
        ai.setTransform(ai.getPosition(), (float)Math.atan2(-normalizedDirectionToPlayer.x, normalizedDirectionToPlayer.y));
        
        //Explode when in explosionrange of the player
        if(directionToPlayer.x * directionToPlayer.x + directionToPlayer.y * directionToPlayer.y > explodingDistance * explodingDistance )
        {
            //Explode
        }
        
        
        if(directionToPlayer.x * directionToPlayer.x + directionToPlayer.y * directionToPlayer.y > minDistance * minDistance )
        {            
            //events.add(new LinearForceMessage(ai, normalizedDirectionToPlayer.scl(Speed)));
        }
    }

}
