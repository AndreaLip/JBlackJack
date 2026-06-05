package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The AudioManager class is responsible for managing and playing audio files.
 * It provides methods to play and stop audio clips.
 */
public class AudioManager 
{
	/** 
	 * The singleton instance of the AudioManager. 
	 */
    private static AudioManager instance;

    /** 
     * The Clip object for playing background music. 
     */
    private static Clip backgroundClip;

    /** 
     * The Clip object for playing short sound effects. 
     */
    private static Clip shortClip;

    /**
     * Gets the singleton instance of the AudioManager.
     * 
     * @return the singleton instance of AudioManager
     */
    public static AudioManager getInstance() 
    {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private AudioManager() 
    {
    }

    /**
     * Plays the specified audio file.
     * 
     * @param filename the name of the audio file to play
     * @param loop whether the audio should loop continuously
     */
    public static synchronized void play(String filename, boolean loop) 
    {
        try {
            if (loop) 
            {
                if (backgroundClip != null && backgroundClip.isRunning()) 
                {
                    backgroundClip.stop();
                    backgroundClip.close();
                }
                ClassLoader classLoader = AudioManager.class.getClassLoader();
                InputStream in = classLoader.getResourceAsStream("songs/" + filename);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
                backgroundClip = AudioSystem.getClip();
                backgroundClip.open(audioIn);
                backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
                backgroundClip.start();
            } 
            else 
            {
                if (shortClip != null && shortClip.isRunning()) 
                {
                    shortClip.stop();
                    shortClip.close();
                }
                ClassLoader classLoader = AudioManager.class.getClassLoader();
                InputStream in = classLoader.getResourceAsStream("songs/" + filename);                
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
                shortClip = AudioSystem.getClip();
                shortClip.open(audioIn);
                shortClip.start();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Stops the currently playing audio clip.
     */
    public synchronized void stop() 
    {
        if (backgroundClip != null && backgroundClip.isRunning()) 
        {
            backgroundClip.stop();
            backgroundClip.close();
            backgroundClip = null;
        }

        if (shortClip != null && shortClip.isRunning()) 
        {
            shortClip.stop();
            shortClip.close();
            shortClip = null;
        }
    }
}
