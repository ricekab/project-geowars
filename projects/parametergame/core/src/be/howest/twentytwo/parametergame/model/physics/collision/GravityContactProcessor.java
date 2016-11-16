package be.howest.twentytwo.parametergame.model.physics.collision;

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;
import be.howest.twentytwo.parametergame.model.system.GravityPhysicsEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class GravityContactProcessor extends ContactProcessor {
	
	private Collection<IPhysicsEvent> events;
	
	public GravityContactProcessor(ContactListener next, Collection<IPhysicsEvent> events) {
		super(next);
		this.events = events;
	}

	public GravityContactProcessor(Collection<IPhysicsEvent> events) {
		super();
		this.events = events;
	}

	@Override
	protected boolean handleBeginContact(Contact contact) {
		Gdx.app.log("GravityContact", "beginContact");
		short categoryA = contact.getFixtureA().getFilterData().categoryBits;
		short categoryB = contact.getFixtureB().getFilterData().categoryBits;
		if(categoryA == Constants.GRAVITY_CATEGORY) {
			this.events.add(new GravityPhysicsEvent(contact.getFixtureA().getBody(), contact.getFixtureB()
					.getBody()));
			return true;
		} else if(categoryB == Constants.GRAVITY_CATEGORY) {
			this.events.add(new GravityPhysicsEvent(contact.getFixtureB().getBody(), contact.getFixtureA()
					.getBody()));
			return true;
		}
		return false;
	}

	@Override
	protected boolean handleEndContact(Contact contact) {
		Gdx.app.log("GravityContact", "endContact");
		short categoryA = contact.getFixtureA().getFilterData().categoryBits;
		short categoryB = contact.getFixtureB().getFilterData().categoryBits;
		if(categoryA == Constants.GRAVITY_CATEGORY) {
			// TODO: REMOVE GRAVITY FROM EVENTS --> Needs lookup => HashSet?
			Gdx.app.log("ContactListener", "Fixture A is a gravity field");
			return true;
		} else if(categoryB == Constants.GRAVITY_CATEGORY) {
			// TODO: REMOVE GRAVITY FROM EVENTS (See above)
			Gdx.app.log("ContactListener", "Fixture B is a gravity field");
			return true;
		}
		return false;
	}

	@Override
	protected boolean handlePreSolve(Contact contact, Manifold oldManifold) {
		return false;
	}

	@Override
	protected boolean handlePostSolve(Contact contact, ContactImpulse impulse) {
		return false;
	}

}
