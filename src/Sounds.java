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
    } //throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//
//        //add sounds
//        File file = new File(".\\sound\\BGM.wav");
//
//        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//        Clip clip = AudioSystem.getClip();
//        clip.open(audioStream);
//        clip.loop(-1);
//
//        clip.start();
//
//    }
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
            Clip BGM = AudioSystem.getClip();
            BGM.open(AudioSystem.getAudioInputStream(new File(path)));
            while (!ending)
            {
                BGM.loop(-1);
            }
            BGM.stop();
            ending=false;

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean stopMusic(){
        return ending=true;
    }
}
