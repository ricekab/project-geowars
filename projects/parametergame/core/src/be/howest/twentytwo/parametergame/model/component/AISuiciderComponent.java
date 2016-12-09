package be.howest.twentytwo.parametergame.model.component;

// @author Ward Van den Berghe

import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import be.howest.twentytwo.parametergame.model.physics.events.LinearForceEvent;
import be.howest.twentytwo.parametergame.screen.GameScreen;
import be.howest.twentytwo.parametergame.utils.VectorMath;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool.Poolable;
import java.util.Collection;


public class AISuiciderComponent implements Component, Poolable {

    
    public static final ComponentMapper<AISuiciderComponent> MAPPER = ComponentMapper.getFor(AISuiciderComponent.class);
    
    public float Speed = 200.0f;
    
    @Override
    public void reset() {
    }

    public void ProcessAI(Entity entity, Collection<IPhysicsEvent> events) {
        
        MovementComponent mc = MovementComponent.MAPPER.get(entity);
        Body body = BodyComponent.MAPPER.get(entity).getBody();
        
        // Mainplayer used for testing, 
        Body playerBody = BodyComponent.MAPPER.get(GameScreen.mainPlayer).getBody();
        Vector2 playerPos = playerBody.getPosition();
                
        Vector2 directionToPlayer = VectorMath.subtract(playerPos, body.getPosition());
        Vector2 normalizedDirectionToPlayer = directionToPlayer.cpy().nor();
        
        body.setTransform(body.getPosition(), (float)Math.atan2(-normalizedDirectionToPlayer.x, normalizedDirectionToPlayer.y));
        
        //Explode when in explosionrange of the player
        float explodingDistance = 5.0f;
        if(directionToPlayer.x * directionToPlayer.x + directionToPlayer.y * directionToPlayer.y > explodingDistance * explodingDistance )
        {
            //Explode
        }
        
        float minDistance = 20.0f;
        if(directionToPlayer.x * directionToPlayer.x + directionToPlayer.y * directionToPlayer.y > minDistance * minDistance )
        {            
            events.add(new LinearForceEvent(body, normalizedDirectionToPlayer.scl(Speed)));
        }
                        			   
    }    
}
