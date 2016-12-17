package be.howest.twentytwo.parametergame.model.physics.collision;

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.physics.events.ExplosionPhysicsEvent;
import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class PlayerContactProcessor extends ContactProcessor {

	public PlayerContactProcessor(ContactListener next, Collection<IPhysicsEvent> events) {
		super(next, events);
	}

	public PlayerContactProcessor(Collection<IPhysicsEvent> events) {
		this(new NullContactProcessor(), events);
	}

	@Override
	protected boolean handleBeginContact(Contact contact) {
		short categoryA = contact.getFixtureA().getFilterData().categoryBits;
		short categoryB = contact.getFixtureB().getFilterData().categoryBits;
		if (categoryA == Constants.PLAYER_CATEGORY) {
			return processBeginContact(contact.getFixtureA().getBody(), contact.getFixtureB());
		} else if (categoryB == Constants.PLAYER_CATEGORY) {
			return processBeginContact(contact.getFixtureB().getBody(), contact.getFixtureA());
		}
		return false;
	}

	private boolean processBeginContact(Body player, Fixture target) {
		Gdx.app.log("PlayerCP", "Player - beginContact called");
		if(target.getFilterData().categoryBits == Constants.ENEMY_CATEGORY){
			Gdx.app.log("PlayerCP", "Player-Enemy contact");
			float pushRange = 50f;
			float pushForce = 15000f;
			
			getEvents().add(new ExplosionPhysicsEvent(player, pushRange, pushForce, Constants.PLAYER_EXPLOSION_MASK));
			
			return true;
		}
		
		return false;
	}

	@Override
	protected boolean handleEndContact(Contact contact) {
		Gdx.app.log("PlayerCP", "Player - handleEndContact called");
		return false;
	}

	@Override
	protected boolean handlePreSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean handlePostSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		return false;
	}

}
