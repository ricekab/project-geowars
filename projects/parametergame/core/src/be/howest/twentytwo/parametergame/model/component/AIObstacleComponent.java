package be.howest.twentytwo.parametergame.model.component;

// @author Ward Van den Berghe

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool.Poolable;


public class AIObstacleComponent implements Component, Poolable {

    
    public static final ComponentMapper<AIObstacleComponent> MAPPER = ComponentMapper.getFor(AIObstacleComponent.class);
        
    @Override
    public void reset() {
    }

    public void ProcessAI(Entity entity, Collection<IPhysicsMessage> events) {
                    
    }    
}
