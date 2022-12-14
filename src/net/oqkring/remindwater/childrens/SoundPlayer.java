package net.oqkring.remindwater.childrens;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import net.oqkring.remindwater.childrens.ananke.Ananke;

public class SoundPlayer extends Ananke {

	static AudioInputStream audioInputStream;
	static Long currentFrame;
	static Clip clip;

	public static void play() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		audioInputStream = AudioSystem.getAudioInputStream(fileAudio);
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(20f * (float) Math.log10(VOLUME));
		clip.start();
	}

	public static void stop() {
		currentFrame = 0L;
		clip.stop();
	}

	private SoundPlayer() {

	}

}
