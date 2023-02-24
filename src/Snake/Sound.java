package Snake;

import java.io.File;

import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

/*
 * Die Klasse ist für den kompletten Sound zuständig.
 */
public class Sound {

	private static AudioClip soundtrack = new AudioClip(
			new File("recources\\Sound\\Snake\\Soundtrack.mp3").toURI().toString());
	private static AudioClip eatSound = new AudioClip(new File("recources\\Sound\\Snake\\eat.wav").toURI().toString());
	private static AudioClip deathSound = new AudioClip(
			new File("recources\\Sound\\Snake\\deathSound.mp3").toURI().toString());

	/* Spielt das "Essgeräusch" ab. */
	public void playEat() {
		eatSound.play();
	}

	public static void playDeath() {
		deathSound.play();
	}

	/* Spielt Hintergrundmusik ab. */
	public static void playBackroundMusic() {
		soundtrack.setCycleCount(MediaPlayer.INDEFINITE);
		soundtrack.play();
	}

	/* Macht den Ton an. */
	public static void turnOnAudio() {
		soundtrack.setVolume(0.1);
		eatSound.setVolume(0.03);
		deathSound.setVolume(0.02);
	}
	
	public static void playAudio() {
		soundtrack.play();
		eatSound.play();
	}

	/* Macht den Ton aus */
	public static void turnOfAudio() {
		soundtrack.setVolume(0);
		soundtrack.stop();
		eatSound.setVolume(0);
		eatSound.stop();
		deathSound.stop();
		deathSound.setVolume(0);
	}

}