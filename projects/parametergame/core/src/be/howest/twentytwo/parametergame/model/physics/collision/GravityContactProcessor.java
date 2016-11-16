package be.howest.twentytwo.parametergame.model.physics.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class GravityContactProcessor extends ContactProcessor {
	
	public GravityContactProcessor(ContactListener next) {
		super(next);
	}

	public GravityContactProcessor() {
		super();
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
