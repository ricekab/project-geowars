/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.twentytwo.parametergame.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 *
 * @author Floris
 */
public class SoundEngine {
    public SoundEngine(){
        
    }
    
    public void playMusic(String file, boolean loop){
        Music music = Gdx.audio.newMusic(Gdx.files.internal(file));
        music.setLooping(loop);
        music.play();
    }
}
