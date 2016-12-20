package be.howest.twentytwo.parametergame.model.component.ai;

// @author Ward Van den Berghe

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.MovementComponent;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;
import be.howest.twentytwo.parametergame.model.physics.message.LinearForceMessage;
import be.howest.twentytwo.parametergame.screen.GameScreen;
import be.howest.twentytwo.parametergame.utils.VectorMath;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;


public class AIScoutComponent implements Component, Poolable {

    
    public static final ComponentMapper<AIScoutComponent> MAPPER = ComponentMapper.getFor(AIScoutComponent.class);
    public float Speed = 200.0f;
    private float minDistance = 30.0f;
    private float maxDistance = 40.0f;
    
    
    @Override
    public void reset() {
    }

    public void ProcessAI(Entity entity, Collection<IPhysicsMessage> events) {
        
        MovementComponent mc = MovementComponent.MAPPER.get(entity);
        Body body = BodyComponent.MAPPER.get(entity).getBody();
        
        // Mainplayer used for testing, 
        Body playerBody = BodyComponent.MAPPER.get(GameScreen.mainPlayer).getBody();
        Vector2 playerPos = playerBody.getPosition();
        
        // Move enemy in direction of player
        
        // Turn enemy to look in player direction
        Vector2 directionToPlayer = VectorMath.subtract(playerPos, body.getPosition());
        Vector2 normalizedDirectionToPlayer = directionToPlayer.cpy().nor();
        
        body.setTransform(body.getPosition(), (float)Math.atan2(-normalizedDirectionToPlayer.x, normalizedDirectionToPlayer.y));
        
        //Move toward player if within minimum distance
        float distance = (directionToPlayer.add(new Vector2(-normalizedDirectionToPlayer.x * maxDistance,-normalizedDirectionToPlayer.y * maxDistance))).cpy().len()/40.0f;
        //To close
        if(directionToPlayer.x * directionToPlayer.x + directionToPlayer.y * directionToPlayer.y < minDistance * minDistance )
        {            
            events.add(new LinearForceMessage(body, normalizedDirectionToPlayer.scl(-Speed*distance)));
        }
        //To far
        else if(directionToPlayer.x * directionToPlayer.x + directionToPlayer.y * directionToPlayer.y > maxDistance * maxDistance )
        { 
            events.add(new LinearForceMessage(body, normalizedDirectionToPlayer.scl(Speed*distance)));
        }
                
        
        //When dies turns into suicider
    }    
}
