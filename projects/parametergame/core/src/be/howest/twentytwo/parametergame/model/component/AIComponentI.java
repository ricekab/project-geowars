package be.howest.twentytwo.parametergame.model.component;

// @author Ward Van den Berghe

import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;
import java.util.Collection;


public interface AIComponentI extends Component, Pool.Poolable {
    
    public static final ComponentMapper<AIComponentI> MAPPER = ComponentMapper.getFor(AIComponentI.class);
	
    public void ProcessAI(Entity entity, Collection<IPhysicsEvent> events);
}
