package Snake;

import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import Menue.Option;
import Menue.Player;
import Menue.Rankings;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

/*
 * Die Klasse ist dafür da, die Schlange zu steuern und festzulegen was bei jeder 
 * Aktualisierung ausgeführt werden soll. 
 */
public class Controler {

	public enum Direction { // erlaubte Richtungen der Schlange
		UP, DOWN, RIGHT, LEFT, ENTER
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	Direction direction = Direction.LEFT; // Startrichtung der Schlange

	UI ui = new UI();
	Food food = new Food(0, 0, null);
	PowerUp power = new PowerUp(0, 0, null);
	Pane root;
	int counter = 0;
	boolean creator = true;
	static boolean border = true;
	public static int highscore = Rankings.getHighScoreSnake();
	public static int score = 0;
	static boolean gamePaused;
	public static boolean gameOver = false;
	KeyCode k = KeyCode.LEFT;

	public Controler(Pane root) {
		this.root = root;
		food = food.newFood(root); // Intialisierung des Essens
	}

	public KeyCode getK() {
		return k;
	}

	public void setK(KeyCode k) {
		this.k = k;
	}

	public boolean isBorder() {
		return border;
	}

	public static void setBorder(boolean border) {
		Controler.border = border;
	}

	/*
	 * Es werden die Tastatureingaben ausgewertet. Die Schlange kann sich nicht
	 * entgegengesetzt ihrer aktuellen Richtung fortbewegen.
	 */
	public void setDirection(Pane root) {
		root.getScene().setOnKeyPressed((e) -> {
			setK(e.getCode());
		});
		if (getK() == Option.getUpK()) {
			if (direction != Direction.DOWN)
				direction = Direction.UP;
		} else if (getK() == Option.getDownK()) {
			if (direction != Direction.UP)
				direction = Direction.DOWN;
		} else if (getK() == Option.getLeftK()) {
			if (direction != Direction.RIGHT)
				direction = Direction.LEFT;
		} else if (getK() == Option.getRightK()) {
			if (direction != Direction.LEFT)
				direction = Direction.RIGHT;
		} else if (getK() == KeyCode.ENTER) {

			if (!gamePaused)
				Menue.Menue.menueDurringGame(root);

			switch (direction) {
			case LEFT:
				setK(Option.getLeftK());
				break;
			case RIGHT:
				setK(Option.getRightK());
				break;
			case UP:
				setK(Option.getUpK());
				break;
			case DOWN:
				setK(Option.getDownK());
			default:
				break;
			}
		}
	}

	/*
	 * Die Schlange wird aktualisiert. Dadurch wird uberprüft ob sie bei der
	 * Aktualisierung außerhalb der Border ist.
	 */

	public void refreshSnake() {
		switch (direction) {
		case UP:
			Snake.snakeList.get(0).setY(Snake.snakeList.get(0).getY() - UI.snakeSizeY);
			if (Snake.snakeList.get(0).getY() < UI.playgroundY) {
				if (border) {
					gameOver = true;
				} else {
					Snake.snakeList.get(0)
							.setY((int) UI.playground.getY() + (int) UI.playground.getHeight() - UI.pointsizeY);
				}
			}

			break;
		case DOWN:
			Snake.snakeList.get(0).setY(Snake.snakeList.get(0).getY() + UI.snakeSizeY);
			if (Snake.snakeList.get(0).getY() > (UI.playgroundY + UI.playgroundHeight - 2)) // Überprüfen
				if (border) {
					gameOver = true;
				} else {
					Snake.snakeList.get(0).setY((int) UI.playground.getY());
				}
			break;
		case LEFT:
			Snake.snakeList.get(0).setX(Snake.snakeList.get(0).getX() - UI.snakeSizeX);
			if (Snake.snakeList.get(0).getX() < UI.playground.getX())
				if (border) {
					gameOver = true;
				} else {
					Snake.snakeList.get(0)
							.setX((int) UI.playground.getX() + (int) UI.playground.getWidth() - UI.pointsizeX);
				}
			break;
		case RIGHT:
			Snake.snakeList.get(0).setX(Snake.snakeList.get(0).getX() + UI.snakeSizeX);
			if (Snake.snakeList.get(0).getX() > UI.playgroundX + UI.playgroundWidth - 10)
				if (border) {
					gameOver = true;
				} else {
					Snake.snakeList.get(0).setX((int) UI.playground.getX());
				}
			break;
		default:
			break;
		}
		for (int i = 2; i < Snake.snakeList.size(); i++) {
			if (Snake.snakeList.get(0).getX() == Snake.snakeList.get(i).getX() // theoretisch ab 1, jedoch 0 & 1
																				// gleiche
																				// Koords
					&& Snake.snakeList.get(0).getY() == Snake.snakeList.get(i).getY()) {
				gameOver = true;
			}
		}
	}

	/*
	 * Da das vorderste Teil der Schlange die Position verändert hat, wird mit
	 * dieser Methode die Position jedes Körperteils auf die Position des vorderen
	 * gesetzt wird.
	 */
	public void refresh() {
		for (int i = Snake.snakeList.size() - 1; i >= 1; i--) {
			Snake.snakeList.get(i).setY(Snake.snakeList.get(i - 1).getY());
			Snake.snakeList.get(i).setX(Snake.snakeList.get(i - 1).getX());
		}
		// in sich selber fahren Problem: wenn 2 starten, dann direkt gameOver
	}

	/*
	 * Wenn der Score den Highscore übersteigt, dann wird ein neuer Highscore durch
	 * den Score gesetzt.
	 */
	public void refreshScore() {
		if (!(Rankings.getHighScoreSnake() == 0)) {
			Controler.highscore = Rankings.getHighScoreSnake();
		}
		if (score >= Controler.highscore) {
			Controler.highscore = score;
		}
	}

	public void isGameOver() {
		if (gameOver) {
			try {
				ui.drawGameOver(root);
			} catch (FileNotFoundException e) {
				System.err.println("Schriftdatei wurde nicht gefunden.");
				e.printStackTrace();
			}

			Sound.turnOfAudio();
			Menue.Menue.getGameSnake().pause(); // Pausieren der Loop
			Timer t = new Timer();
			TimerTask reset = new TimerTask() {
				public void run() {
					Sound.playDeath();
					Player player = new Player(score, 1); // neuer Player wird erstellt
					player.setName(Loop.scene);
				}
			};
			t.schedule(reset, 1000);

			return;
		}
		return;
	}

	/*
	 * Bei jeden "Tick" verändert sich das Spiel.
	 */
	public void tick(Pane root) {
		ui.drawPlayground(root); // Zeichnen des ganzen Spielfeldes
		setDirection(root);	//Abfangen der Tastaturbefehle
		refresh();	// Schlange wird ein weiter gesetzt
		refreshSnake();	//Koordinaten werden verändert
		refreshScore();	//Score wird aktualisiert
		isGameOver();	//Es wird geprüft ob das Spiel vorbei ist
		Snake.drawSnake(root, direction);	//Schlange wird gezeichnet
		food.drawFood(root);	//Essen wird gezeichnet

		if (food.eatFood()) { // Kollision
			food = food.newFood(root);
			score += 100;
			counter++;
		}
		if (counter >= 3) {	//Bei jedem 3. Essen erscheint ein PowerUP
			if (creator) {
				power = power.createMe(root);
				creator = false;
			}
			power.drawMe(root);
		}
		if (counter >= 2 && power.eatMe(root)) {
			counter = 0;
			creator = true;
		}
	}
}
