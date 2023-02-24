package Snake;

import java.io.FileNotFoundException;
import java.util.Timer;

import Menue.Option;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UI {

	private Image imageBackAll = new Image("file:recources\\Pictures\\Menue\\Rahmen_Snake.png");
	static Rectangle playground;
	private Rectangle backroundAll;
	private static Font standard;
	// variables
	static int sceneSizeX, sceneSizeY, playgroundX, playgroundY, playgroundWidth, playgroundHeight;
	public static int size, barsX, barsY, pointsizeX, pointsizeY, snakeSizeX, snakeSizeY;
	static Color strokeColor = Color.BLACK;

	public static int getSceneSizeX() {
		return sceneSizeX;
	}

	public static void setSceneSizeX(int sceneSizeX) {
		UI.sceneSizeX = sceneSizeX;
	}

	public static int getSceneSizeY() {
		return sceneSizeY;
	}

	public void setSceneSizeY(int sceneSizeY) {
		UI.sceneSizeY = sceneSizeY;
	}

	public static int getPlaygroundX() {
		return UI.playgroundX;
	}

	public void setPlaygroundX(int playgroundX) {
		UI.playgroundX = playgroundX;
	}

	public static int getPlaygroundY() {
		return playgroundY;
	}

	public void setPlaygroundY(int playgroundY) {
		UI.playgroundY = playgroundY;
	}

	/*
	 * Das Spielfeld wird gezeichnet.
	 */
	public void drawPlayground(Pane root) {
		root.getChildren().clear(); // Pane wird geleert

		// Hintergrund Fernseher
		backroundAll = new Rectangle(0, 0, UI.getSceneSizeX(), UI.getSceneSizeY());
		backroundAll.setFill(new ImagePattern(imageBackAll));

		// Spielumrandung
		playground = new Rectangle(UI.getPlaygroundX(), UI.getPlaygroundY(), UI.playgroundWidth, UI.playgroundHeight);
		playground.setFill(Color.TRANSPARENT);
		playground.setStroke(strokeColor);
		playground.setStrokeWidth(8);

		root.getChildren().addAll(backroundAll, playground);

		// Interface
		Text t = new Text(UI.getPlaygroundX() + 20, UI.getPlaygroundY() - 10, "Score:" + Controler.score);
		t.setFont(standard);
		t.setFill(Color.GREEN);
		t.setStroke(Color.BLACK);

		Text hs = new Text((UI.getSceneSizeX() / 2) - 15, UI.getPlaygroundY() - 10, "Highscore:" + Controler.highscore); // Highscore.playerList.get(0).getScoreSnake();
		hs.setFont(standard);
		hs.setFill(Color.GREEN);
		hs.setStroke(Color.LIGHTGREEN);

		root.getChildren().addAll(t, hs);
	}

	/*
	 * Auskommentieren ?
	 */
	public void drawGameOver(Pane root) throws FileNotFoundException {
		root.getChildren().clear(); // sinnvoll ?
		standard = Font.loadFont(
				"file:C:\\Users\\chris\\sciebo\\Projekt Informatik 2\\Code\\Arcade-Automat\\recources\\Font\\PressStart2P-Regular.ttf",
				50);
		Text t = new Text(0, 0, "GAME OVER");
		t.setX(UI.getSceneSizeX() / 2 - ((t.getBoundsInLocal().getMaxX()))); // mittig??
		t.setY(UI.getSceneSizeY() / 2 - t.getBoundsInLocal().getMaxY());
		t.setFill(Color.RED);
		t.setFont(standard);
		root.getChildren().add(t);
	}

	/*
	 * Die Fenstergröße wird entsprechend der im Menü angegebenen Größe gesetzt.
	 */
	public static void setWindowsize() throws FileNotFoundException {

		if (Option.getWindowSize()) { // Anpassung an 1080p
			standard = Font.loadFont(
					"file:C:\\Users\\chris\\sciebo\\Projekt Informatik 2\\Code\\Arcade-Automat\\recources\\Font\\PressStart2P-Regular.ttf",
					15);

			double ratio = 1.5;

			sceneSizeX = 1280;
			sceneSizeY = 720;

			playgroundX = (int) (635 / ratio);
			playgroundY = (int) (214 / ratio);

			playgroundWidth = (int) (650 / ratio);
			playgroundHeight = (int) (653 / ratio);

			size = 17;
			barsX = UI.getSceneSizeX() / size;
			barsY = UI.getSceneSizeY() / size;
			pointsizeX = UI.getSceneSizeX() / barsX;
			pointsizeY = UI.getSceneSizeY() / barsY;
			snakeSizeX = pointsizeX;
			snakeSizeY = pointsizeY;

		} else { // Anpassung an 720p
			standard = Font.loadFont(
					"file:C:\\Users\\chris\\sciebo\\Projekt Informatik 2\\Code\\Arcade-Automat\\recources\\Font\\PressStart2P-Regular.ttf",
					25);

			sceneSizeX = 1920;
			sceneSizeY = 1080;

			playgroundX = 635;
			playgroundY = 210;

			playgroundWidth = 650;
			playgroundHeight = 653;

			size = 25;
			barsX = UI.getSceneSizeX() / size;
			barsY = UI.getSceneSizeY() / size;
			pointsizeX = UI.getSceneSizeX() / barsX;
			pointsizeY = UI.getSceneSizeY() / barsY;
			snakeSizeX = pointsizeX;
			snakeSizeY = pointsizeY;
		}
	}
	public void drawTimer(Pane root, Timer timer, int i) {
		
	}

}
