/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.audio.SoundSequencer;
import be.howest.twentytwo.parametergame.model.event.game.GameLoseEvent;

/**
 *
 * @author Floris
 */
public class GameEndSoundHandler extends BaseGameEndHandler {
    
        private SoundSequencer sounds;

	public GameEndSoundHandler(SoundSequencer soundSequencer) {
		this.sounds = soundSequencer;
	}
    @Override
    public void handleEvent(GameLoseEvent event) {
        sounds.addSound("sound/victory.wav");
    }
    
}
