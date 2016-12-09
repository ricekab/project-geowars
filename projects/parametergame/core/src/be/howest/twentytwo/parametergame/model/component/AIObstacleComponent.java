package be.howest.twentytwo.parametergame.model.component;

// @author Ward Van den Berghe

import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool.Poolable;
import java.util.Collection;


public class AIObstacleComponent implements Component, Poolable {

    
    public static final ComponentMapper<AIObstacleComponent> MAPPER = ComponentMapper.getFor(AIObstacleComponent.class);
        
    @Override
    public void reset() {
    }

    public void ProcessAI(Entity entity, Collection<IPhysicsEvent> events) {
                    
    }    
}
