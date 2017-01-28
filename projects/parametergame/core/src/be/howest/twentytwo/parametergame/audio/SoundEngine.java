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
   
    Music music;
    String file;
    public void playMusic(String file){
        this.file = file;
        this.music = Gdx.audio.newMusic(Gdx.files.internal(file));
        music.setLooping(true);
        // music.play(); -- TODO: TEMP DISABLE MUSIC
    }
    
    public void playMusic(String file, boolean loop){
        this.file = file;
        this.music = Gdx.audio.newMusic(Gdx.files.internal(file));
        music.setLooping(loop);
        music.play();
    }
    
    public void disposeMusic(){
        music.dispose();
    }
    
    public Music getMusic(){
        return music;
    }
    
    public String getFile(){
        return file;
    }
}
