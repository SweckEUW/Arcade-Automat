package Pacman;

import javafx.scene.media.AudioClip;
import java.io.File;

/**
contains all sound effects of the pacman game
 */
public class Sound {
	
	private static AudioClip eatCoin,ghostNoise,titleMusic,eatFruit,eatGhost,extraLive,pacmanDeath,sirene;

	/**
	loads all sound effects and sets volume
	 */
	public static void setup() {
		//Source: https://www.classicgaming.cc/classics/pac-man/sounds
		titleMusic = new AudioClip(new File("recources/Sound/Pacman/pacman_beginning.wav").toURI().toString());
		titleMusic.setVolume(0.1);
		//Source: http://samplekrate.com/drumkit.php?id=34
		eatCoin=new AudioClip(new File("recources/Sound/Pacman/eatball.wav").toURI().toString());
		eatCoin.setRate(6);
		eatCoin.setVolume(0.03);
		//Source: https://www.classicgaming.cc/classics/pac-man/sounds
		eatFruit= new AudioClip(new File("recources/Sound/Pacman/pacman_eatfruit.wav").toURI().toString());
		eatFruit.setVolume(0.1);
		//Source: https://www.classicgaming.cc/classics/pac-man/sounds
		eatGhost= new AudioClip(new File("recources/Sound/Pacman/pacman_eatghost.wav").toURI().toString());
		eatGhost.setVolume(0.1);
		//Source: https://www.classicgaming.cc/classics/pac-man/sounds
		extraLive= new AudioClip(new File("recources/Sound/Pacman/pacman_extrapac.wav").toURI().toString());
		extraLive.setVolume(0.1);
		//Source: http://samplekrate.com/drumkit.php?id=34
		ghostNoise= new AudioClip(new File("recources/Sound/Pacman/Pac Man Ghost Noises.wav").toURI().toString());
		ghostNoise.setCycleCount(-1);
		ghostNoise.setVolume(0.1);
		//Source: http://www.orangefreesounds.com/pacman-death-sound/
		pacmanDeath=new AudioClip(new File("recources/Sound/Pacman/Pacman-death-sound.mp3").toURI().toString());
		pacmanDeath.setVolume(0.1);
		//Source: http://samplekrate.com/drumkit.php?id=34
		sirene=new AudioClip(new File("recources/Sound/Pacman/Pacman_Large_Pellet_Loop.wav").toURI().toString());
		sirene.setCycleCount(-1);
		sirene.setVolume(0.1);
	}
	
	public static void playTitelMusic() {
		titleMusic.play();
		titleMusic.play();
	}
	
	public static void playMunchSound() {
		eatCoin.play();
	}
	
	public static void playEatFruit() {
		eatFruit.stop();
		eatFruit.play();
	}
	
	public static void playEatGhost() {
		eatGhost.stop();
		eatGhost.play();
	}
	
	public static void playExtraLive() {
		extraLive.stop();
		extraLive.play();
	}
	
	public static void playGhostNoise() {
		ghostNoise.play();
	}
	
	public static void stopGhostNoise() {
		ghostNoise.stop();
	}
	
	public static void playPacmanDeath() {
		pacmanDeath.stop();
		pacmanDeath.play();
	}
	
	public static AudioClip getSirene() {
		return sirene;
	}
	
	public static void stopSirene() {
		sirene.stop();
	}
	
	/**
	mutes or unmutes all pacman sounds
	 */
	public static void setAllSoundsMute(Boolean b) {	
		if(b) {
			eatCoin.setVolume(0);
			ghostNoise.setVolume(0);
			titleMusic.setVolume(0);
			eatFruit.setVolume(0);
			eatGhost.setVolume(0);
			extraLive.setVolume(0);
			pacmanDeath.setVolume(0);
			sirene.setVolume(0);
		}
		else {
			eatCoin.setVolume(0.03);
			ghostNoise.setVolume(0.1);
			titleMusic.setVolume(0.1);
			eatFruit.setVolume(0.1);
			eatGhost.setVolume(0.1);
			extraLive.setVolume(0.1);
			pacmanDeath.setVolume(0.1);
			sirene.setVolume(0.1);
		}
	}

	public static void stopSound() {
		sirene.stop();
		ghostNoise.stop();
	}
}
