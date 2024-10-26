package sound;

import javax.sound.sampled.*;
import java.net.URL;

/**
 * Sound handler
 */
public class Sound {
    private Clip clip;
    private static float masterVolume = 1.0f;

    /**
    * Loads an audio clip from the specified filename.
    *
    * @param filename the name of the audio file to load
    */
    public Sound(String filename) {
        try {
            URL url = this.getClass().getResource("/resources/sounds/" + filename);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    * Sets the master volume for all sounds.
    *
    * @param volume the master volume level (0.0f to 1.0f)
    */
    public static void setMasterVolume(float volume) {
        masterVolume = volume;
    }

    /**
    * Plays the sound from the beginning, adjusting the volume according to the master volume.
    */
    public void play() {
        if (clip == null) {
            return;
        }
        clip.stop();
        clip.setFramePosition(0);

        //Adjust the volume
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * masterVolume) + gainControl.getMinimum();
        gainControl.setValue(gain);

        clip.start();
    }
}
