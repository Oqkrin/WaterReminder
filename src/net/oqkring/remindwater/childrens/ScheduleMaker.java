package net.oqkring.remindwater.childrens;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import net.oqkring.remindwater.childrens.ananke.Ananke;

public class ScheduleMaker extends Ananke {

	static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	private ScheduleMaker() {

	}

	static final Runnable clearReminder = () -> {
		window.setVisible(false);
		SoundPlayer.stop();
	};

	static final Runnable remindWater = () -> {
		window.setLocation(random.nextInt((int) (SCREENWIDTH - IMAGEWIDTH)),
				random.nextInt((int) (SCREENHEIGHT - IMAGEHEIGHT)));
		window.setVisible(true);
		try {
			SoundPlayer.play();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		scheduler.schedule(clearReminder, 2, TimeUnit.SECONDS);
	};

	public static void scheduler() {
		scheduler.scheduleWithFixedDelay(remindWater, 0, REPETITIONTIME, TimeUnit.MINUTES);
	}

}
