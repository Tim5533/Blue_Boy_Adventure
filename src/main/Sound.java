package main;

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    File sounfFiles[] = new File[30];

    public Sound() {
        sounfFiles[0] = new File("res\\sound\\BlueBoyAdventure.wav");
        sounfFiles[1] = new File("res\\sound\\coin.wav");
        sounfFiles[2] = new File("res\\sound\\powerup.wav");
        sounfFiles[3] = new File("res\\sound\\unlock.wav");
        sounfFiles[4] = new File("res\\sound\\fanfare.wav");
    }

    public void setFile(int i) {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sounfFiles[i]);
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
