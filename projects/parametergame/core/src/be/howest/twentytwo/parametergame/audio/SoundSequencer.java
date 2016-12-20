/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.twentytwo.parametergame.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Floris
 */
public class SoundSequencer {

   List<Sound> sounds = new ArrayList<Sound>();
   int index = 0;
   double delay;
   double lastPlayed;

   
   public void addSound(String path) {
      Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
      //if (sounds.contains(sound, true))
      //   return;
      sounds.add(sound);
   }

   /**
    * Play the sound sequence with a given delay
    * 
    * @param volume 0 - 1
    * @param delay
    *            in seconds
    */
   public void play(float volume, double delay) {
      if (TimeUtils.nanoTime() - lastPlayed > delay * 1000000000) {
          //System.out.println(TimeUtils.nanoTime());
         play(volume);
      }
      else{
          nextTrack();
      }
      
   }

   /**
    * Play the sound track
    * 
    * @param volume 0 - 1
    */
   public void play(float volume) {
      if (sounds.get(index) != null) {
         sounds.get(index).play(volume);
         sounds.remove(index);
         System.out.println(index);
         lastPlayed = TimeUtils.nanoTime();
      }
      nextTrack();
   }

   /** Move to the next track on the play list */
   private void nextTrack() {
      
      if (index + 1 > sounds.size() - 1) {
         index = 0;
      } else {
         index += 1;
      }
      
      //index += 1;
      
   }
   
   public int getSize(){
       return sounds.size();
   }
}