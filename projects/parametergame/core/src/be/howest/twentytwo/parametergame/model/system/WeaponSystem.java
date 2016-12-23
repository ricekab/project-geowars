package be.howest.twentytwo.parametergame.model.system;

import be.howest.twentytwo.parametergame.audio.SoundSequencer;
import java.util.Collection;

import be.howest.twentytwo.parametergame.model.component.BodyComponent;
import be.howest.twentytwo.parametergame.model.component.WeaponComponent;
import be.howest.twentytwo.parametergame.model.event.EventQueue;
import be.howest.twentytwo.parametergame.model.event.game.WeaponFiredEvent;
import be.howest.twentytwo.parametergame.model.gamedata.WeaponGameData;
import be.howest.twentytwo.parametergame.model.spawn.message.ISpawnMessage;
import be.howest.twentytwo.parametergame.model.spawn.message.SpawnEntityMessage;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class WeaponSystem extends IteratingSystem {

	public static final int PRIORITY = 1;
        SoundSequencer weaponSound = new SoundSequencer();
	private final Collection<ISpawnMessage> spawnMessages;
	private final EventQueue eventQueue;

	public WeaponSystem(Collection<ISpawnMessage> spawnMessages, EventQueue eventQueue) {
		super(Family.all(WeaponComponent.class, BodyComponent.class).get(), PRIORITY);
		this.spawnMessages = spawnMessages;
		this.eventQueue = eventQueue;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		Body body = BodyComponent.MAPPER.get(entity).getBody();
		WeaponComponent wc = WeaponComponent.MAPPER.get(entity);

		for(WeaponGameData wpn : wc.getAllWeapons()){
			wpn.cooldown(deltaTime);
		}
		
		// Input / action processing
		if(wc.isFirePrimary()) {
			WeaponGameData primary = wc.getPrimary();
			fireWeapon(primary, body, wc.getPhysicsCategory(), wc.getPhysicsMask());
                        weaponSound.addSound("sound/secondary.mp3");
                        weaponSound.play(1,0.2);
		}
		if(wc.isFireSecondary()) {
			WeaponGameData secondary = wc.getActiveSecondaryWeapon();
			fireWeapon(secondary, body, wc.getPhysicsCategory(), wc.getPhysicsMask());
                        weaponSound.addSound("sound/primary.wav");
                        weaponSound.play(1,0.2);
		}

	}

	private void fireWeapon(WeaponGameData weapon, Body source, short category, short mask) {
		if(weapon.fire()){
			Vector2 velocity = new Vector2(1, 0).rotateRad(source.getAngle()).scl(
					weapon.getBulletSpeed());
			spawnMessages.add(new SpawnEntityMessage(weapon.getID(), source.getPosition(),
					velocity, source.getAngle(), category, mask));
			eventQueue.send(new WeaponFiredEvent());
		}
	}

}
