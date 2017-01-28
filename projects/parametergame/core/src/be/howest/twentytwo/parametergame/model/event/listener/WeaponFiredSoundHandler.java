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
		String weaponName = event.getWeaponName();
		switch (weaponName) {
			case "bullet_basic":
				sounds.addSound("sound/primary.wav");
				break;
			case "bullet_drone":
				sounds.addSound("sound/secondary.mp3");
				break;
			case "bullet_shooter":
				sounds.addSound("sound/shooter.mp3");
				break;
			case "missile_projectile":
				sounds.addSound("sound/missile.mp3");
				break;
			default:
				Gdx.app.error("LF/WeaponFiredSoundHandler", "ERR: Could not find sound for: "
						+ weaponName);
		}
	}

}