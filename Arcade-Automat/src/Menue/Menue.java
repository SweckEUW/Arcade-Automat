package Menue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import Pacman.GameLoop;
import Pacman.Sound;
import Snake.Controler;
import Snake.Loop;
import Snake.Snake;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;

/**
 * Menue class contains every ui of the main menues(pause menue, main menue and
 * start screen) You also start the games in this class
 */
public class Menue extends Application {

	private static Scene scene;
	private static Loop gameSnake; // Snake game
	private static GameLoop gamePacman; // Pacman game
	private static Boolean game = false; // false=pacman; true=snake;
	private static Boolean levelPacman;// false=standardLevel; true=HSHL Level;
	private static MediaPlayer mainMusic; // BackgroundTheme
	private static ImageView arrowView, imageViewRasterDurringGame; // Arrow Image, Raster Image
	private static Text blinkText, resume, restart, exit; // Blinking Text
	private static ArrayList<Text> allOptions; // List with every text object of the specific menu (needed for
												// switchMenue())
	private static MediaView backgroundMediaView, RankingBackgroundMediaView; // Background video
	private static Rectangle backgroundMenueDurringGame; // BlackBox for the Game pause menue
	private static double t; // needed for blinking text animation
	private static int i; // needed for switchMenue() (blinkingText and arrow)

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage mainStage) throws FileNotFoundException {
		mainStage.setTitle("Arcade-Automat");
		mainStage.setResizable(false);
		Sound.setup(); // setup Sounds (loading and setting volume)
		Rankings.load(); // loading the scores
		startScreen();
		mainStage.setScene(scene);
		mainStage.setX(0);
		mainStage.setY(0);
		mainStage.show();
	}

	/**
	 * Shows start screen of the Arcade-Automat
	 */
	public void startScreen() throws FileNotFoundException {
		// set background video //Source: https://www.youtube.com/watch?v=hbD6Cl4HCIk
		Media backgroundMedia = new Media(new File("recources/Video/Menue/Background Video.mp4").toURI().toString());
		MediaPlayer backgroundMediaPlayer = new MediaPlayer(backgroundMedia);
		backgroundMediaPlayer.play();
		backgroundMediaPlayer.setMute(true);
		backgroundMediaPlayer.setCycleCount(-1); // repeat video permantently
		backgroundMediaView = new MediaView(backgroundMediaPlayer);

		// set background video for rankings screen //Source:
		// https://www.youtube.com/watch?v=hbD6Cl4HCIk
		Media RankingBackgroundMedia = new Media(new File("recources/Video/Rankings/rankings.mp4").toURI().toString());
		MediaPlayer RankingBackgroundMediaPlayer = new MediaPlayer(RankingBackgroundMedia);
		RankingBackgroundMediaPlayer.play();
		RankingBackgroundMediaPlayer.setMute(true);
		RankingBackgroundMediaPlayer.setCycleCount(-1);
		RankingBackgroundMediaView = new MediaView(RankingBackgroundMediaPlayer);

		// set blinking text during the start Screen
		Text pressAnyKey = new Text(840, 800, "PRESS ANY KEY");
		pressAnyKey.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		pressAnyKey.setFill(Color.WHITE);
		setBlinkText(pressAnyKey);

		// set background music //https://www.youtube.com/watch?v=i94rqSASTLU&t=1128s
		Media music = new Media(new File("recources/Sound/Menue/menueMusic.mp3").toURI().toString());
		mainMusic = new MediaPlayer(music);
		mainMusic.setVolume(0.1);// 0.1
		mainMusic.setCycleCount(-1);
		mainMusic.play();

		Pane root = new Pane();
		root.getChildren().add(backgroundMediaView);
		root.getChildren().add(pressAnyKey);
		addOverlay(root);
		scene = new Scene(root, 1920, 1080);
		scene.setFill(Color.BLACK);

		// disable cursor
		scene.setCursor(Cursor.NONE);

		// press any key to get to the main menue
		scene.setOnKeyPressed(e -> {
			try {
				mainMenue();
			} catch (FileNotFoundException e1) {
				System.err.println("Datei wurde nicht gefunden.");
				e1.printStackTrace();
			}
		});

		// Animator for blinking text
		AnimationTimer blinkingText = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				t += 0.05;
				if (t > 1) {
					blinkText.setVisible(true);
				}
				if (t > 2) {
					blinkText.setVisible(false);
					t = 0;
				}
			}
		};
		blinkingText.start();
	}

	/**
	 * Shows main menue of the Arcade-Automat. A UI with multiple selections(option
	 * menue,ranking menue,exit, start snake, pacman level select)
	 */
	public static void mainMenue() throws FileNotFoundException {
		// reset switch
		i = 0;

		// Menue options
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case UP:
				switchMenue(-1);
				break;
			case DOWN:
				switchMenue(1);
				break;
			case ENTER:
				switch (i) {
				case 0: // start pacman level selection
					try {
						pacmanLevelSelect();
					} catch (FileNotFoundException e2) {
						System.err.println("Datei wurde nicht gefunden.");
						e2.printStackTrace();
					}
					break;
				case 1: // start snake
					startSnake();
					mainMusic.pause();
					break;
				case 2: // switch to rankings menue
					Rankings.rankingMenue(scene);
					break;
				case 3:// switch to Option menue
					try {
						Option.optionMenue(scene);
					} catch (FileNotFoundException e1) {
						System.err.println("Datei wurde nicht gefunden.");
						e1.printStackTrace();
					}
					break;
				case 4: // Exit
					Stage stage = (Stage) scene.getWindow();
					stage.close();
					break;
				}
			default:
				break;
			}
		});

		// set Arrow
		Image arrow = new Image(new File("recources/Pictures/Menue/arrow.png").toURI().toString());
		arrowView = new ImageView(arrow);
		arrowView.setScaleX(0.3);
		arrowView.setScaleY(0.3);
		arrowView.setX(700);
		arrowView.setY(385);

		// setup text
		Text pacman = new Text(850, 500, "PACMAN");
		pacman.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		pacman.setFill(Color.WHITE);
		setBlinkText(pacman);

		Text snake = new Text(850, 600, "SNAKE");
		snake.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		snake.setFill(Color.WHITE);

		Text rankings = new Text(850, 700, "RANKINGS");
		rankings.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		rankings.setFill(Color.WHITE);

		Text options = new Text(850, 800, "OPTIONS");
		options.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		options.setFill(Color.WHITE);

		Text exit = new Text(850, 900, "EXIT");
		exit.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		exit.setFill(Color.WHITE);

		backgroundMediaView.setFitHeight(1080);
		backgroundMediaView.setFitWidth(1920);

		// 720p
		if (Option.getWindowSize()) {
			backgroundMediaView.setFitHeight(720);
			backgroundMediaView.setFitWidth(1280);
			arrowView.setScaleX(0.2);
			arrowView.setScaleY(0.2);
			arrowView.relocate(440, 267);
			pacman.relocate(580, 350);
			pacman.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
			snake.relocate(580, 400);
			snake.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
			rankings.relocate(580, 450);
			rankings.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
			options.relocate(580, 500);
			options.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
			exit.relocate(580, 550);
			exit.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		}

		// needed for switchMenue()
		allOptions = new ArrayList<Text>();
		allOptions.add(pacman);
		allOptions.add(snake);
		allOptions.add(rankings);
		allOptions.add(options);
		allOptions.add(exit);

		Pane root = new Pane();
		root.getChildren().add(backgroundMediaView);
		root.getChildren().add(arrowView);
		root.getChildren().add(pacman);
		root.getChildren().add(snake);
		root.getChildren().add(rankings);
		root.getChildren().add(exit);
		root.getChildren().add(options);
		addOverlay(root);

		scene.setRoot(root);
	}

	/**
	 * Shows level selection-screen of pacman. A UI with multiple selections(exit(to
	 * main menue),start pacman with level HSHL,start pacman with level standard)
	 */
	public static void pacmanLevelSelect() throws FileNotFoundException {
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case UP:
				switchMenue(-1);
				break;
			case DOWN:
				switchMenue(1);
				break;
			case ENTER:
				switch (i) {
				case 0:// start Standard Level
					startPacman(0);
					mainMusic.stop();
					break;
				case 1:// start HSHL Level
					startPacman(1);
					mainMusic.stop();
					break;
				case 2:// Back to main menue
					try {
						mainMenue();
					} catch (FileNotFoundException e1) {
						System.err.println("Datei wurde nicht gefunden.");
						e1.printStackTrace();
					}
					break;
				}
			default:
				break;
			}
		});

		// setup text
		Text standardLevel = new Text(850, 500, "Standard Level");
		standardLevel.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		standardLevel.setFill(Color.WHITE);
		blinkText = standardLevel;

		Text hshlLevel = new Text(850, 600, "HSHL Level");
		hshlLevel.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		hshlLevel.setFill(Color.WHITE);

		Text back = new Text(850, 700, "Back");
		back.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		back.setFill(Color.WHITE);

		// 720p
		if (Option.getWindowSize()) {
			standardLevel.relocate(580, 350);
			standardLevel.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 18));
			hshlLevel.relocate(580, 400);
			hshlLevel.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 18));
			back.relocate(580, 450);
			back.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 18));
		}

		// needed for switchMenue()
		allOptions = new ArrayList<Text>();
		allOptions.add(standardLevel);
		allOptions.add(hshlLevel);
		allOptions.add(back);

		Pane root = new Pane();
		root.getChildren().add(backgroundMediaView);
		root.getChildren().add(hshlLevel);
		root.getChildren().add(standardLevel);
		root.getChildren().add(back);
		root.getChildren().add(arrowView);
		addOverlay(root);

		scene.setRoot(root);
	}

	/**
	 * Starts the pacman game
	 * 
	 * @param s=0 start pacman with the standard Level
	 * @param s=1 start pacman with the HSHL Level
	 */
	public static void startPacman(int s) {
		GameLoop.reset(); // resets all static elements
		game = false; // set boolean so we know which game we are playing(needed for restarting the
						// game during game)

		// set boolean so we know which level we are playing (needed for restarting the
		// game during game)
		if (s == 0)
			levelPacman = false;
		else
			levelPacman = true;
		Pane root = new Pane();
		scene.setRoot(root);
		if (Option.getWindowSize())
			gamePacman = new GameLoop(root, 1280, 720, s);
		else
			gamePacman = new GameLoop(root, 1920, 1080, s);

		gamePacman.start();
		try {
			addOverlay(root);
		} catch (FileNotFoundException e) {
			System.err.println("Datei wurde nicht gefunden.");
			e.printStackTrace();
		}
	}

	/**
	 * Starts the pacman game
	 */
	public static void startSnake() {
		Snake.snakeList.clear();
		game = true;// set boolean so we know which game we are playing(needed for restarting the
					// game during game)
		Pane root = new Pane();
		scene.setRoot(root);
		Controler.gameOver = false;
		Controler.score = 0;
		Loop.speed = 8;
		Controler.highscore = Rankings.getHighScoreSnake();
		gameSnake = new Loop(root, scene);
		gameSnake.loop();
		gameSnake.resume();
		try {
			addOverlay(root);
		} catch (FileNotFoundException e) {
			System.err.println("Datei wurde nicht gefunden.");
			e.printStackTrace();
		}
	}

	/**
	 * Pauses the current game and adds a Pause menue to the pane
	 */
	public static void menueDurringGame(Pane root) {
		i = 0;
		if (game) { // pause Snake
			gameSnake.pause();
		} else // pause Pacman
			gamePacman.pause();
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case UP:
				switchMenue(-1);
				break;
			case DOWN:
				switchMenue(1);
				break;
			case ENTER:
				switch (i) {
				case 0:// resume
					if (game) // resume Snake
						gameSnake.resume();
					else // resume Pacman
						gamePacman.resume();
					GameLoop.setGameActive(true);
					visibleMenueDuringGame(false);
					break;
				case 1:// restart
					if (game) { // restart Snake
						startSnake();
					} else { // restart Pacman (just creates a brand new game)
						if (levelPacman)
							startPacman(1);
						else
							startPacman(0);
					}
					break;
				case 2:// exit to main menue
					mainMusic.play();
					try {
						Menue.mainMenue();
					} catch (FileNotFoundException e1) {
						System.err.println("Datei wurde nicht gefunden.");
						e1.printStackTrace();
					}
					break;
				}
			default:
				break;
			}
		});

		// add blackbox of the Menue
		backgroundMenueDurringGame = new Rectangle(750, 300, 415, 350);
		backgroundMenueDurringGame.setFill(Color.BLACK);
		backgroundMenueDurringGame.setStrokeWidth(4);
		backgroundMenueDurringGame.setStroke(Color.WHITE);
		backgroundMenueDurringGame.setOpacity(0.7);

		// add arrow
		Image arrow = new Image(new File("recources/Pictures/Menue/arrow.png").toURI().toString());
		arrowView = new ImageView(arrow);
		arrowView.setScaleX(0.3);
		arrowView.setScaleY(0.3);
		arrowView.relocate(700, 285);

		// add text
		resume = new Text(850, 400, "RESUME");
		resume.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		resume.setFill(Color.WHITE);
		blinkText = resume;

		restart = new Text(850, 500, "RESTART");
		restart.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		restart.setFill(Color.WHITE);

		exit = new Text(850, 600, "EXIT");
		exit.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		exit.setFill(Color.WHITE);

		// add grid
		Image rasterDurringGame = new Image(new File("recources/Pictures/Menue/raster.png").toURI().toString());
		imageViewRasterDurringGame = new ImageView(rasterDurringGame);
		imageViewRasterDurringGame.setFitHeight(1080);
		imageViewRasterDurringGame.setFitWidth(1920);
		imageViewRasterDurringGame.setOpacity(0.2);

		// 720p
		if (Option.getWindowSize()) {
			backgroundMenueDurringGame = new Rectangle(510, 230, 245, 230);
			backgroundMenueDurringGame.setFill(Color.BLACK);
			backgroundMenueDurringGame.setStrokeWidth(4);
			backgroundMenueDurringGame.setStroke(Color.WHITE);
			backgroundMenueDurringGame.setOpacity(0.7);
			arrowView.setScaleX(0.2);
			arrowView.setScaleY(0.2);
			arrowView.relocate(440, 200);
			resume.relocate(575, 280);
			resume.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 19));
			restart.relocate(575, 330);
			restart.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 19));
			exit.relocate(575, 380);
			exit.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 19));
			imageViewRasterDurringGame.setFitHeight(720);
			imageViewRasterDurringGame.setFitWidth(1280);
		}

		root.getChildren().add(backgroundMenueDurringGame);
		root.getChildren().add(arrowView);
		root.getChildren().add(resume);
		root.getChildren().add(restart);
		root.getChildren().add(exit);
		root.getChildren().add(imageViewRasterDurringGame);

		// needed for the switchMenue()
		allOptions = new ArrayList<Text>();
		allOptions.add(resume);
		allOptions.add(restart);
		allOptions.add(exit);
	}

	/**
	 * sets the visibility of the pause menue (menueDurringGame()) elements
	 * 
	 * @param b visibility of the pause menue
	 */
	public static void visibleMenueDuringGame(Boolean b) {
		backgroundMenueDurringGame.setVisible(b);
		arrowView.setVisible(b);
		resume.setVisible(b);
		restart.setVisible(b);
		exit.setVisible(b);
		imageViewRasterDurringGame.setVisible(b);
		blinkText = new Text(0, 0, "");
	}

	/**
	 * adds an Overlay to the Pane giving it a retro "Röhrenfernseher" look
	 */
	public static void addOverlay(Pane root) throws FileNotFoundException {
		// add grid
		Image raster = new Image(new File("recources/Pictures/Menue/raster.png").toURI().toString());
		ImageView imageViewRaster = new ImageView(raster);
		imageViewRaster.setFitHeight(1080);
		imageViewRaster.setFitWidth(1920);
		imageViewRaster.setOpacity(0.2);
		root.getChildren().add(imageViewRaster);

		// add frame
		Image frame = new Image(new File("recources/Pictures/Menue/Rahmen.png").toURI().toString());
		ImageView imageViewFrame = new ImageView(frame);
		imageViewFrame.setFitHeight(1160);
		imageViewFrame.setFitWidth(2000);
		imageViewFrame.setTranslateX(-40);
		imageViewFrame.setTranslateY(-40);
		root.getChildren().add(imageViewFrame);

		backgroundMediaView.setFitHeight(1080);
		backgroundMediaView.setFitWidth(1920);

		// 720p
		if (Option.getWindowSize()) {
			imageViewRaster.setFitHeight(720);
			imageViewRaster.setFitWidth(1280);
			imageViewFrame.setFitHeight(780);
			imageViewFrame.setFitWidth(1360);
			imageViewFrame.setTranslateX(-40);
			imageViewFrame.setTranslateY(-40);
			backgroundMediaView.setFitHeight(720);
			backgroundMediaView.setFitWidth(1280);
			backgroundMediaView.setScaleY(1.1);
			backgroundMediaView.setTranslateY(30);
		}
	}

	/**
	 * a switch controlling where the arrow is located and which text is blinking
	 * 
	 * @param s controls how much the arrow is moving (usually -1 or 1)
	 */
	public static void switchMenue(int s) {
		blinkText.setVisible(true);
		i += s; // i= pointer which text is blinking and where the arrow is located (s moves the
				// pointer)
		if (i == allOptions.size()) // prevent index out of bounce exceptions
			i = 0;
		if (i == -1) // prevent index out of bounce exceptions
			i = allOptions.size() - 1;
		blinkText = allOptions.get(i); // sets new blinking text
		blinkText.setVisible(false); // immediately change text to invisible (better feedback where you are in the
										// menue)
		arrowView.setTranslateY(100 * i);
		// 720p
		if (Option.getWindowSize())
			arrowView.setTranslateY(50 * i);
		t = 0; // reset blink animation (see lines 113-126)
	}

	public static void setBlinkText(Text t) {
		blinkText = t;
	}

	public static Loop getGameSnake() {
		return gameSnake;
	}

	public static Text getBlinkText() {
		return blinkText;
	}

	public static void resetBlinkAnimation() {
		t = 0; // reset blink animation (see lines 113-126)
	}

	public static MediaView getBackgroundVideo() {
		return backgroundMediaView;
	}

	public static MediaPlayer getMainMusic() {
		return mainMusic;
	}

	public static Boolean getGame() {
		return game;
	}

	public static MediaView getBackgroundVideoRankings() {
		return RankingBackgroundMediaView;
	}

}
