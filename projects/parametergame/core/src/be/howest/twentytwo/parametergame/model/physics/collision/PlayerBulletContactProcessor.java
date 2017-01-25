package be.howest.twentytwo.parametergame.model.physics.collision;

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.collision.EnemyHitEvent;
import be.howest.twentytwo.parametergame.model.event.collision.PlayerHitEvent;
import be.howest.twentytwo.parametergame.model.event.game.DestroyEntityEvent;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class PlayerBulletContactProcessor extends BaseContactProcessor {
	
	public PlayerBulletContactProcessor(ContactListener next, EventQueue eventQueue, Collection<IPhysicsMessage> events) {
		super(next, eventQueue, events);
	}

	public PlayerBulletContactProcessor(EventQueue eventQueue, Collection<IPhysicsMessage> events) {
		this(new NullContactProcessor(), eventQueue, events);
	}

	@Override
	protected boolean handleBeginContact(Contact contact) {
		short categoryA = contact.getFixtureA().getFilterData().categoryBits;
		short categoryB = contact.getFixtureB().getFilterData().categoryBits;
		if (categoryA == Collision.BULLET_PLAYER_CATEGORY) {
			return handlePlayerBullet(contact.getFixtureA(), contact.getFixtureB());
		} else if (categoryB == Collision.BULLET_PLAYER_CATEGORY) {
			return handlePlayerBullet(contact.getFixtureB(), contact.getFixtureA());
		}
		return false;
	}

	private boolean handlePlayerBullet(Fixture playerBullet, Fixture target) {
		short targetCategory = target.getFilterData().categoryBits;
		if ((targetCategory & Collision.PLANET_CATEGORY) > 0) {
			getEventQueue().send(new DestroyEntityEvent((Entity) playerBullet.getBody().getUserData()));
			return true;
		}
		if ((targetCategory & Collision.ENEMY_CATEGORY) > 0) {
			getEventQueue().send(new EnemyHitEvent(target, playerBullet));
			getEventQueue().send(new DestroyEntityEvent((Entity) playerBullet.getBody().getUserData()));
		}
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
