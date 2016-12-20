package be.howest.twentytwo.parametergame.model.physics.collision;

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.game.DestroyEntityEvent;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class BulletContactProcessor extends ContactProcessor {

	public BulletContactProcessor(ContactListener next, EventQueue eventQueue,
			Collection<IPhysicsMessage> events) {
		super(next, eventQueue, events);
		// TODO Auto-generated constructor stub
	}

	public BulletContactProcessor(EventQueue eventQueue, Collection<IPhysicsMessage> events) {
		this(new NullContactProcessor(), eventQueue, events);
	}

	@Override
	protected boolean handleBeginContact(Contact contact) {
		short categoryA = contact.getFixtureA().getFilterData().categoryBits;
		short categoryB = contact.getFixtureB().getFilterData().categoryBits;
		if(categoryA == Constants.BULLET_PLAYER_CATEGORY) {
			return handlePlayerBullet(contact.getFixtureA(), contact.getFixtureB());
		} else if(categoryB == Constants.BULLET_PLAYER_CATEGORY) {
			return handlePlayerBullet(contact.getFixtureB(), contact.getFixtureA());
		}
		if(categoryA == Constants.BULLET_ENEMY_CATEGORY) {
			return handleEnemyBullet(contact.getFixtureA(), contact.getFixtureB());
		} else if(categoryB == Constants.BULLET_ENEMY_CATEGORY) {
			return handleEnemyBullet(contact.getFixtureB(), contact.getFixtureA());
		}
		return false;
	}

	private boolean handlePlayerBullet(Fixture playerBullet, Fixture target) {
		short targetCategory = target.getFilterData().categoryBits;
		if((targetCategory & Constants.PLANET_CATEGORY) > 0){
			getEventQueue().send(new DestroyEntityEvent((Entity)playerBullet.getBody().getUserData()));
		}
		return false;
	}

	private boolean handleEnemyBullet(Fixture enemyBullet, Fixture target) {
		// TODO
		return false;
	}

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
