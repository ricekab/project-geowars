package be.howest.twentytwo.parametergame.model.physics.collision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import be.howest.twentytwo.parametergame.model.physics.events.GravityPhysicsEvent;
import be.howest.twentytwo.parametergame.model.physics.events.IPhysicsEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class GravityContactProcessor extends ContactProcessor {

	private Collection<GravityPhysicsEvent> gravityEvents;

	public GravityContactProcessor(ContactListener next, Collection<IPhysicsEvent> events) {
		super(next, events);
		this.gravityEvents = new ArrayList<GravityPhysicsEvent>();
	}

	public GravityContactProcessor(Collection<IPhysicsEvent> events) {
		this(new NullContactProcessor(), events);
	}

	@Override
	protected boolean handleBeginContact(Contact contact) {
		short categoryA = contact.getFixtureA().getFilterData().categoryBits;
		short categoryB = contact.getFixtureB().getFilterData().categoryBits;
		if (categoryA == Constants.GRAVITY_CATEGORY) {
			addEvent(contact.getFixtureA().getBody(), contact.getFixtureB().getBody());
			// eventQueue.addEvent(new GravityEvent(...));
			return true;
		} else if (categoryB == Constants.GRAVITY_CATEGORY) {
			addEvent(contact.getFixtureB().getBody(), contact.getFixtureA().getBody());
			return true;
		}
		return false;
	}

	private void addEvent(Body planet, Body target) {
		GravityPhysicsEvent evt = new GravityPhysicsEvent(planet, target);
		getEvents().add(evt);
		this.gravityEvents.add(evt);
	}

	@Override
	protected boolean handleEndContact(Contact contact) {
		short categoryA = contact.getFixtureA().getFilterData().categoryBits;
		short categoryB = contact.getFixtureB().getFilterData().categoryBits;
		if (categoryA == Constants.GRAVITY_CATEGORY) {
			removeEvent(contact.getFixtureA().getBody(), contact.getFixtureB().getBody());
			Gdx.app.log("ContactListener", "Fixture A is a gravity field");
			return true;
		} else if (categoryB == Constants.GRAVITY_CATEGORY) {
			removeEvent(contact.getFixtureB().getBody(), contact.getFixtureA().getBody());
			Gdx.app.log("ContactListener", "Fixture B is a gravity field");
			return true;
		}
		return false;
	}

	/**
	 * Remove the {@link GravityPhysicsEvent} from the event list and sets it to
	 * consumed.
	 */
	private void removeEvent(Body planet, Body target) {
		Iterator<GravityPhysicsEvent> it = gravityEvents.iterator();
		GravityPhysicsEvent evt;
		while (it.hasNext()) {
			evt = it.next();
			if (evt.getSourceBody().equals(planet) && evt.getTargetBody().equals(target)) {
				evt.setConsumed(); // Notifies the event system that this event
									// is completed.
				it.remove();
				return;
			}
		}
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
