package Menue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
The rakings class contains the the UI that shows the highscore lists (pacman and snake) in a sorted order.
The class also loads and saves the highscore lists
 */
public class Rankings {

	private static int x; //delay of each score text
	private static ArrayList<Player> pacmanScores = new ArrayList<Player>(); //highscore list pacman
	private static ArrayList<Player> snakeScores = new ArrayList<Player>(); //highscore list snake
	private static ArrayList<Player> displayedScores = new ArrayList<Player>(); //highscore list snake
	private static Boolean sort = false; //controls where the blackbox is located and which highscore list is getting showed
	private static int i = 0; //needed for switch
	private static ArrayList<Text> allOptions; //needed for switch
	private static TextField textField; // text input for search
	private static ImageView arrowView; //arrow
	private static Rectangle blackBoxSort; //indecates which highscore list gets shown
	private static int inputTextlength;
	
	//chris load and write
	private static File folder = new File("Save");
	private static File file = new File("Save/save.txt");
	private static String inputTextSnake;
	private static String inputTextPacMan;

	public static void addPacmanScore(Player p) {
		pacmanScores.add(p);
	}

	public static void addSnakeScore(Player p) {
		snakeScores.add(p);
	}

	/**
	Shows ranking menue of the Arcade-Automat.
	 */
	public static void rankingMenue(Scene scene) {
		inputTextlength=0;
		Pane root = new Pane();
		Menue.getBackgroundVideoRankings().setFitHeight(1080);
		Menue.getBackgroundVideoRankings().setFitWidth(1920);
		//720p
		if (Option.getWindowSize()) {
			Menue.getBackgroundVideoRankings().setFitHeight(720);
			Menue.getBackgroundVideoRankings().setFitWidth(1280);
		}
		root.getChildren().add(Menue.getBackgroundVideoRankings());
		//sorts each list in ascending order
		Sorting.sortInt(pacmanScores);
		Sorting.sortInt(snakeScores);
		
		//add the number in the scoreBoard for each list
		int k=1;
		for(Player p:pacmanScores) { 
			p.setNumberInList(k);
			k++;
		}
		k=1;
		for(Player p:snakeScores) {
			p.setNumberInList(k);
			k++;
		}
		try {
			addMenue(root, scene);
			addScoreBoard(root);
			Menue.addOverlay(root);
		} catch (FileNotFoundException e) {
			System.err.println("Datei wurde nicht gefunden.");
			e.printStackTrace();
		}
		scene.setRoot(root);
	}

	/**
	adds Text for each score to the rankings menue
	 */
	public static void addScoreBoard(Pane root) throws FileNotFoundException {
		x = 400; //reset delay for each Score
		for (int i = 0; i < displayedScores.size(); i++) {
			if (i == 10) //only load the top 10 scores
				break;
			Text r = new Text(680, 440 + 30 * (i - 2), displayedScores.get(i).getNumberInList() + "."); //number
			r.setFill(Color.WHITE);
			r.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
			Text s = new Text(780, 440 + 30 * (i - 2), "" + displayedScores.get(i).getScore()); //score
			s.setFill(Color.WHITE);
			s.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
			Text n = new Text(980, 440 + 30 * (i - 2), "" + displayedScores.get(i).getName()); //name
			n.setFill(Color.WHITE);
			n.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
			//720p
			if (Option.getWindowSize()) {
				r.relocate(460, 270 + 20 * (i - 2));
				r.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
				s.relocate(510, 270 + 20 * (i - 2));
				s.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
				n.relocate(630, 270 + 20 * (i - 2));
				n.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
			}
			if (i == 0) { //first score = golden and bigger font size
				r = new Text(680, 350, displayedScores.get(i).getNumberInList() + ".");
				r.setFill(Color.GOLD);
				r.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
				s = new Text(780, 350, "" + displayedScores.get(i).getScore());
				s.setFill(Color.GOLD);
				s.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
				n = new Text(980, 350, "" + displayedScores.get(i).getName());
				n.setFill(Color.GOLD);
				n.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
				//720p
				if (Option.getWindowSize()) {
					r.relocate(460, 210);
					r.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
					s.relocate(510, 210);
					s.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
					n.relocate(630, 210);
					n.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
				}
			}
			if (i == 1) { //second score = silver and slightly bigger font size
				r = new Text(680, 400, displayedScores.get(i).getNumberInList()+ ".");
				r.setFill(Color.SILVER);
				r.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 25));
				s = new Text(780, 400, "" + displayedScores.get(i).getScore());
				s.setFill(Color.SILVER);
				s.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 25));
				n = new Text(980, 400, "" + displayedScores.get(i).getName());
				n.setFill(Color.SILVER);
				n.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 25));
				//720p
				if (Option.getWindowSize()) {
					r.relocate(460, 240);
					r.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 15));
					s.relocate(510, 240);
					s.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 15));
					n.relocate(630, 240);
					n.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 15));
				}
			}
			root.getChildren().add(r);
			root.getChildren().add(s);
			root.getChildren().add(n);
			
			Timeline animation = new Timeline(); //animation that turns score after score visible with a delay
			animation.getKeyFrames().add(new KeyFrame(Duration.millis(0), new KeyValue(r.opacityProperty(), 0),
					new KeyValue(s.opacityProperty(), 0), new KeyValue(n.opacityProperty(), 0)));
			animation.getKeyFrames().add(new KeyFrame(Duration.millis(x - 1), new KeyValue(r.opacityProperty(), 0),
					new KeyValue(s.opacityProperty(), 0), new KeyValue(n.opacityProperty(), 0)));
			animation.getKeyFrames().add(new KeyFrame(Duration.millis(x), new KeyValue(r.opacityProperty(), 1),
					new KeyValue(s.opacityProperty(), 1), new KeyValue(n.opacityProperty(), 1)));
			animation.play();
			x += 200; //add delay
		}
	}

	/**
	adds key pressed event and text ti the menue
	 */
	public static void addMenue(Pane root, Scene scene) throws FileNotFoundException {
		i = 0; //reste switch
		
		blackBoxSort = new Rectangle(877, 790, 125, 30);
		blackBoxSort.setOpacity(0.9);

		//720p
		if (Option.getWindowSize()) {
			blackBoxSort = new Rectangle(557, 510, 70, 20);
			blackBoxSort.setOpacity(1);
		}
		
		if(Menue.getGame()) {
			displayedScores=snakeScores;
			if (Option.getWindowSize())
				blackBoxSort.setTranslateX(100);
			else
				blackBoxSort.setTranslateX(200);
		}else
			displayedScores=pacmanScores;
		
		textField = new TextField();
		textField.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		textField.setStyle("-fx-text-inner-color: white;-fx-background-color: black;-fx-background-radius: 0;");
		textField.setTranslateX(680);
		textField.setTranslateY(700);
		textField.setPrefWidth(530);
		textField.setPrefHeight(50);
		textField.setOnKeyPressed(e -> {			
			switch (e.getCode()) {
			case DOWN: //press down key to get out of the textbox
				textField.setDisable(true);
				switchRankings(1);
				switchRankings(1);
				scene.setOnKeyPressed(ev -> {
					switch (ev.getCode()) {
					case UP:
						switchRankings(-1);
						break;
					case DOWN:
						switchRankings(1);
						break;
					case ENTER:
						switch (i) {
						case 1: //select which highscore list shows
							root.getChildren().remove(8, root.getChildren().size()); //remove last highscore text
							if (!sort) { //snake score text will show
								sort = true;
								blackBoxSort.setTranslateX(200);
								displayedScores=snakeScores;
								//720p
								if (Option.getWindowSize())
									blackBoxSort.setTranslateX(100);
								try {							
									addScoreBoard(root); //add new highscore text
								} catch (FileNotFoundException e1) {
									System.err.println("Datei wurde nicht gefunden.");
									e1.printStackTrace();
								}
							} else { //pacman score text will show
								sort = false;
								blackBoxSort.setTranslateX(0);
								displayedScores=pacmanScores;
								try {
									addScoreBoard(root);
								} catch (FileNotFoundException e1) {
									System.err.println("Datei wurde nicht gefunden.");
									e1.printStackTrace();
								}
							}
							try {
								Menue.addOverlay(root);
							} catch (FileNotFoundException e1) {
								System.err.println("Datei wurde nicht gefunden.");
								e1.printStackTrace();
							}
							break;
						case 2: //back to main menue
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
			case UP: //same method for down and up
				switchRankings(-1);
				textField.setDisable(true);
				scene.setOnKeyPressed(ev -> {
					switch (ev.getCode()) {
					case UP:
						switchRankings(-1);
						break;
					case DOWN:
						switchRankings(1);
						break;
					case ENTER:
						switch (i) {
						case 1: //select which highscore list shows
							root.getChildren().remove(8, root.getChildren().size()); //remove last highscore text
							if (!sort) { //snake score text will show
								sort = true;
								blackBoxSort.setTranslateX(200);
								displayedScores=snakeScores;
								//720p
								if (Option.getWindowSize())
									blackBoxSort.setTranslateX(100);
								try {							
									addScoreBoard(root); //add new highscore text
								} catch (FileNotFoundException e1) {
									System.err.println("Datei wurde nicht gefunden.");
									e1.printStackTrace();
								}
							} else { //pacman score text will show
								sort = false;
								blackBoxSort.setTranslateX(0);
								displayedScores=pacmanScores;
								try {
									addScoreBoard(root);
								} catch (FileNotFoundException e1) {
									System.err.println("Datei wurde nicht gefunden.");
									e1.printStackTrace();
								}
							}
							try {
								Menue.addOverlay(root);
							} catch (FileNotFoundException e1) {
								System.err.println("Datei wurde nicht gefunden.");
								e1.printStackTrace();
							}
							break;
						case 2:
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
			default:
				if(!e.getCode().equals(KeyCode.UP) && !e.getCode().equals(KeyCode.DOWN) && !e.getCode().equals(KeyCode.SHIFT)){
					if(e.getCode().equals(KeyCode.BACK_SPACE)) {
						if(inputTextlength>0) 
							inputTextlength--;					
					}else 
						inputTextlength++;
						
					if(inputTextlength==0) {
						if(!sort) {
							displayedScores.clear();
							for(Player p:pacmanScores)
								displayedScores.add(p);
						}else{
							displayedScores.clear();
							for(Player p:snakeScores)
								displayedScores.add(p);
						}
					}
					if(e.getCode().isLetterKey()) {
						if(!textField.isDisable() && inputTextlength>0) {
							if(!sort) {
								displayedScores=Sorting.search(pacmanScores,textField.getText(0, inputTextlength-1).toLowerCase()+e.getText().toLowerCase());
							}else { 
								displayedScores=Sorting.search(snakeScores,textField.getText(0, inputTextlength-1).toLowerCase()+e.getText().toLowerCase());
							}
						}
					}else if(e.getCode().equals(KeyCode.BACK_SPACE)) {
						if(!textField.isDisable() && inputTextlength>0) {
							if(!sort) {
								displayedScores=Sorting.search(pacmanScores,textField.getText(0, inputTextlength-1).toLowerCase());								
							}else { 
								displayedScores=Sorting.search(snakeScores,textField.getText(0, inputTextlength-1).toLowerCase());							
							}
						}
					}
					root.getChildren().remove(8, root.getChildren().size()); //remove last highscore text
					try {
						addScoreBoard(root);
						Menue.addOverlay(root);
					} catch (FileNotFoundException e1) {
						System.err.println("Datei wurde nicht gefunden.");
						e1.printStackTrace();
					}	
				}
				
				break;
			}
		});

		//add arrow
		Image arrow = new Image(new File("recources/Pictures/Menue/arrow.png").toURI().toString());
		arrowView = new ImageView(arrow);
		arrowView.setScaleX(0.3);
		arrowView.setScaleY(0.3);
		arrowView.setX(530);
		arrowView.setY(625);

		Text placeholder = new Text(700, 800, ""); //placeholder for the switch
		Menue.setBlinkText(placeholder);

		Text game = new Text(680, 820, "GAME");
		game.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		game.setFill(Color.WHITE);

		Text pacman = new Text(880, 820, "PACMAN");
		pacman.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		pacman.setFill(Color.WHITE);

		Text snake = new Text(1080, 820, "SNAKE");
		snake.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		snake.setFill(Color.WHITE);

		Text back = new Text(680, 900, "BACK");
		back.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		back.setFill(Color.WHITE);

		allOptions = new ArrayList<Text>();
		allOptions.add(placeholder);
		allOptions.add(game);
		allOptions.add(back);

		//720p
		if (Option.getWindowSize()) {
			arrowView.setScaleX(0.1);
			arrowView.setScaleY(0.1);
			arrowView.relocate(340, 370);
			game.relocate(460, 505);
			game.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
			pacman.relocate(560, 505);
			pacman.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
			snake.relocate(660, 505);
			snake.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
			back.relocate(460, 550);
			back.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
			textField.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
			textField.setStyle("-fx-text-inner-color: white;-fx-background-color: black;-fx-background-radius: 0;");
			textField.setTranslateX(460);
			textField.setTranslateY(450);
			textField.setPrefWidth(360);
			textField.setPrefHeight(30);
		}

		root.getChildren().add(textField);
		root.getChildren().add(arrowView);
		root.getChildren().add(game);
		root.getChildren().add(back);
		root.getChildren().add(blackBoxSort);
		root.getChildren().add(snake);
		root.getChildren().add(pacman);
		scene.setOnKeyPressed(e -> { //overwrite scene key pressed
		});
	}

	/**
	a switch controlling where the arrow is located and which text is blinking
	@param s controls how much the arrow is moving (usually -1 or 1)
	 */
	public static void switchRankings(int s) {
		Menue.getBlinkText().setVisible(true);
		i += s;
		if (i == 3)
			i = 0;
		if (i == -1)
			i = 2;
		if (i == 2 || i == 1)
			textField.setDisable(true);
		if (i == 0)
			textField.setDisable(false);
		Menue.setBlinkText(allOptions.get(i));
		Menue.getBlinkText().setVisible(false);
		arrowView.setTranslateY(83 * i);
		if (Option.getWindowSize())
			arrowView.setTranslateY(47 * i);
		Menue.resetBlinkAnimation();
	}

	/**
	returns the highest score (pacman score list)
	 */
	public static int getHighScorePacman() {
		Sorting.sortInt(pacmanScores); //sort 
		if (pacmanScores.isEmpty())
			return 0;
		return pacmanScores.get(0).getScore(); //get highest score
	}

	/**
	returns the highest score (snake score list)
	 */
	public static int getHighScoreSnake() {
		Sorting.sortInt(snakeScores);
		if (snakeScores.isEmpty())
			return 0;
		return snakeScores.get(0).getScore();
	}

	public static void create() {
		if (folder.exists()) {
			System.out.println("Ordner existiert.");
		} else {
			folder.mkdirs();
		}
		if (file.exists()) {
			System.out.println("Datei existiert.");
		} else {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public static void load() {
		int i = 0;
		create();
		snakeScores.clear();
		pacmanScores.clear();
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
					String text = sc.nextLine();
					if (!text.equals("")) {
						String[] split = text.split("#");
						Player p = new Player(Integer.parseInt(split[0]), split[1]);
						if (i % 2 == 0)
							snakeScores.add(p);
						else
							pacmanScores.add(p);
	
				}
					i++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Datei konnte nicht gefunden werden.");
			e.printStackTrace();
		}
	}

	public static void write() {
		try {
			OutputStream stream = new FileOutputStream(file);

			for (int i = 0; i < snakeScores.size() || i < pacmanScores.size(); i++) {
				if (i<snakeScores.size())
					inputTextSnake = snakeScores.get(i).getScore() + "#" + snakeScores.get(i).getName()
							+ System.lineSeparator();
				else
					inputTextSnake = System.lineSeparator();

				if (i<pacmanScores.size())
					inputTextPacMan = pacmanScores.get(i).getScore() + "#" + pacmanScores.get(i).getName()
							+ System.lineSeparator();
				else
					inputTextPacMan = System.lineSeparator();
				try {
					stream.write(inputTextSnake.getBytes());
					stream.write(inputTextPacMan.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			stream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
