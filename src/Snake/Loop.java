package Snake;

import java.io.FileNotFoundException;

import Menue.Option;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/*
 * Die Klasse Loop verwaltet die Schnelligkeit des Spieles und
 * den AnimationTimer.
 * Mithilfe dieses AnimationTimers kann man die Aktualisierungen/Sekunde einstellen.
 * Mit der Methode "handle" die überschrieben werden muss, kann man eintellen was
 * bei jeder Aktualisierung getan werden soll.
 */
public class Loop {

	private static Pane root;
	static Scene scene;
	private Controler snakeControler;
	private AnimationTimer at;
	// variables
	public static double speed = 8;

	public Controler getSnakeControler() {
		return snakeControler;
	}

	public void setSnakeControler(Controler snakeControler) {
		this.snakeControler = snakeControler;
	}

	public Loop(Pane root, Scene scene) { // Intialisierung der Spielekomponenten
		Loop.root = root;
		Loop.scene = scene;
		try {
			UI.setWindowsize();
		} catch (FileNotFoundException e) {
			System.err.println("Schriftdatei wurde nicht gefunden.");
			e.printStackTrace();
		}
		Snake.snakeList.add(new Boddy(UI.getSceneSizeX() / 2 - UI.pointsizeX, UI.getSceneSizeY() / 2));
		Snake.snakeList.add(new Boddy(UI.getSceneSizeX() / 2, UI.getSceneSizeY() / 2));
		Snake.snakeList.add(new Boddy(UI.getSceneSizeX() / 2 + UI.pointsizeX, UI.getSceneSizeY() / 2));
		Sound.playAudio();
		snakeControler = new Controler(root);
	}

	public void loop() {
		at = new AnimationTimer() {

			long lastTick = 0; // letzer Tick in Nanosekunden

			@Override
			public void handle(long now) {
				if (lastTick == 0) {
					lastTick = now;
					snakeControler.tick(root);
					return;
				} else if (now - lastTick > 1_000_000_000 / speed) { // erst bei bestimmter Differenz wird ein Tick
																		// ausgeführt
					lastTick = now; // Speicherung des letzten Ticks
					snakeControler.tick(root);
				}
			}
		};
		at.start();

	}

	/*
	 * Lässt das Spiel weiterlaufen.
	 */
	public void resume() {
		if (Option.getMuteAudio())
			Sound.turnOfAudio();
		else
			Sound.turnOnAudio();
		at.start();
		Controler.gamePaused = false;

	}

	/*
	 * Pausiert das Spiel.
	 */
	public void pause() {
		Sound.turnOfAudio();
		at.stop();
		Controler.gamePaused = true;
	}
}
