package be.howest.twentytwo.parametergame.model.event.listener;

import com.badlogic.gdx.Gdx;

import be.howest.twentytwo.parametergame.audio.SoundSequencer;
import be.howest.twentytwo.parametergame.model.event.game.WeaponFiredEvent;

public class WeaponFiredSoundHandler extends BaseWeaponFiredHandler {

	private SoundSequencer sounds;

	public WeaponFiredSoundHandler(SoundSequencer soundSequencer) {
		this.sounds = soundSequencer;
	}

	@Override
	public void handleEvent(WeaponFiredEvent event) {
		// TODO: @Floris -- Play sound based on name
		String weaponName = event.getWeaponName();
		switch (weaponName) {
		case "bullet_basic":
			// STUFF
			break;
		default:
			Gdx.app.error("LF/WeaponFiredSoundHandler", "ERR: Could not find sound for: " + weaponName);
		}
	}

}