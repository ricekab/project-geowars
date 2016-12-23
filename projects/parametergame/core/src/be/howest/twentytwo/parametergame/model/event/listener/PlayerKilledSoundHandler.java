/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.twentytwo.parametergame.model.event.listener;

import be.howest.twentytwo.parametergame.audio.SoundSequencer;
import be.howest.twentytwo.parametergame.model.event.game.PlayerKilledEvent;

/**
 *
 * @author Floris
 */
public class PlayerKilledSoundHandler extends BasePlayerKilledHandler{
        boolean hasPlayed;
        private SoundSequencer sounds;
        public PlayerKilledSoundHandler(SoundSequencer soundSequencer){
            this.sounds = soundSequencer;       
            hasPlayed = false;
        }

    @Override
    public void handleEvent(PlayerKilledEvent event) {
        if(!hasPlayed){
            sounds.addSound("sound/player_death.mp3");
        }
        hasPlayed = true;
    }
}
