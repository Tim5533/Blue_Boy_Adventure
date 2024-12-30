package main;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    File soundfFiles[] = new File[30];

    public Sound() {
        soundfFiles[0] = new File("res\\sound\\BlueBoyAdventure.wav");
        soundfFiles[1] = new File("res\\sound\\coin.wav");
        soundfFiles[2] = new File("res\\sound\\powerup.wav");
        soundfFiles[3] = new File("res\\sound\\unlock.wav");
        soundfFiles[4] = new File("res\\sound\\fanfare.wav");
        soundfFiles[5] = new File("res\\sound\\gameover.wav");
        soundfFiles[6] = new File("res\\sound\\hitmonster.wav");
    }

    public void setFile(int i) {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundfFiles[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }

}
