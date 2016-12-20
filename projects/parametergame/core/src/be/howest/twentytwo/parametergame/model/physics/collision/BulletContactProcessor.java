package be.howest.twentytwo.parametergame.model.physics.collision;

import java.util.Collection;

import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.physics.message.IPhysicsMessage;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean handleEndContact(Contact contact) {
		// TODO Auto-generated method stub
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
