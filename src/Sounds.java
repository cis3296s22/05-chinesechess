package src;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {
    static Sounds sound = new Sounds();
    boolean ending;

    //constructor
    public static Sounds getSound(){
        return sound;
    }

    public void playSound(String path){
        try {
            Clip soundClip = AudioSystem.getClip();
            soundClip.open(AudioSystem.getAudioInputStream(new File(path)));
            soundClip.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playBGM(String path){
        try {
            ending=false;
            Clip BGM = AudioSystem.getClip();
            BGM.open(AudioSystem.getAudioInputStream(new File(path)));
            while (!ending)
            {
                BGM.loop(-1);
            }
            BGM.stop();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void stopMusic(){
        this.ending=true;
    }
}
