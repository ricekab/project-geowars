package be.howest.twentytwo.parametergame.model.physics.collision;

import java.util.Collection;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import be.howest.twentytwo.parametergame.model.component.EnemyComponent;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.game.DestroyEntityEvent;
import be.howest.twentytwo.parametergame.model.event.game.EnemyKilledEvent;
import be.howest.twentytwo.parametergame.model.event.game.PlayerKilledEvent;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

public class PlanetContactProcessor extends BaseContactProcessor {

	public PlanetContactProcessor(ContactListener next, EventQueue eventQueue,
			Collection<IPhysicsMessage> events) {
		super(next, eventQueue, events);
	}

	public PlanetContactProcessor(EventQueue eventQueue, Collection<IPhysicsMessage> events) {
		super(eventQueue, events);
	}

	@Override
	protected boolean handleBeginContact(Contact contact) {
		short categoryA = contact.getFixtureA().getFilterData().categoryBits;
		short categoryB = contact.getFixtureB().getFilterData().categoryBits;
		if(categoryA == Collision.PLANET_CATEGORY) {
			return processBeginContact(contact.getFixtureA(), contact.getFixtureB());
		} else if(categoryB == Collision.PLANET_CATEGORY) {
			return processBeginContact(contact.getFixtureB(), contact.getFixtureA());
		}
		return false;
	}

	private boolean processBeginContact(Fixture planet, Fixture target) {
		// Anything a planet touches dies
		short targetCategory = target.getFilterData().categoryBits;
		if((targetCategory & Collision.PLAYER_CATEGORY) > 0) {
			getEventQueue().send(new PlayerKilledEvent());
			return true; // Player entity destruction has to be handled more delicately
		} else if((targetCategory & Collision.ENEMY_CATEGORY) > 0) {
			EnemyComponent ec = EnemyComponent.MAPPER.get((Entity)target.getBody().getUserData());
			getEventQueue().send(new EnemyKilledEvent(target.getBody().getPosition(), ec.getScoreValue(), ec.getGeomDropRate()));
		}
		getEventQueue().send(new DestroyEntityEvent((Entity) target.getBody().getUserData()));
		return true;
	};

	@Override
	protected boolean handleEndContact(Contact contact) {
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
