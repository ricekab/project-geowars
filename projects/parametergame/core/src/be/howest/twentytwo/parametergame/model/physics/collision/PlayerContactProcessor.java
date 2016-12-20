package be.howest.twentytwo.parametergame.model.physics.collision;

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.collision.PlayerHitEvent;
import be.howest.twentytwo.parametergame.model.physics.message.ExplosionPhysicsMessage;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class PlayerContactProcessor extends ContactProcessor {

	public PlayerContactProcessor(ContactListener next, EventQueue eventQueue,
			Collection<IPhysicsMessage> physicsMessages) {
		super(next, eventQueue, physicsMessages);
	}

	public PlayerContactProcessor(EventQueue eventQueue, Collection<IPhysicsMessage> events) {
		this(new NullContactProcessor(), eventQueue, events);
	}

	@Override
	protected boolean handleBeginContact(Contact contact) {
		short categoryA = contact.getFixtureA().getFilterData().categoryBits;
		short categoryB = contact.getFixtureB().getFilterData().categoryBits;
		if(categoryA == Constants.PLAYER_CATEGORY) {
			return processBeginContact(contact.getFixtureA().getBody(), contact.getFixtureB());
		} else if(categoryB == Constants.PLAYER_CATEGORY) {
			return processBeginContact(contact.getFixtureB().getBody(), contact.getFixtureA());
		}
		return false;
	}

	private boolean processBeginContact(Body player, Fixture target) {
		if((target.getFilterData().categoryBits & Constants.PLAYER_HIT_FILTER_MASK) > 0) {
			Gdx.app.log("PlayerCP", "Player-Enemy contact");
			
			getEventQueue().send(new PlayerHitEvent());
			
			float pushRange = 50f;
			float pushForce = 15000f;

			getPhysicsQueue().add(
					new ExplosionPhysicsMessage(player, pushRange, pushForce,
							Constants.PLAYER_EXPLOSION_MASK));

			return true;
		}

		return false;
	}

	@Override
	protected boolean handleEndContact(Contact contact) {
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
