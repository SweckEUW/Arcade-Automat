package Menue;

import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
A player object stores name and score after a game is done.
it gets the name through an UI after the game is over.
the player object than gets stored in the specific highscore list of the game
 */
public class Player {

	private String name; //name of the player (gets set in the UI)
	private int score;  
	private double t; //needed for drop shadow animation
	private int game; //stores in which highscore list the player gets added (from which game the player got created)  0=pacman 1=snake
	private static AnimationTimer colorText; //drop shadow animation
	private int numberInList;
	
	public Player(int score,int game){
		this.score=score;
		this.game=game;
	}
	
	// second constructor to load players out of list
	public Player(int score,String name){
		this.score=score;
		this.name=name;
	}
	
	/**
	opens UI where you can enter your name
	 */
	public void setName(Scene scene) {
		Pane root=new Pane();
		Menue.getMainMusic().play();
		
		//720p
		if(Option.getWindowSize()) {
			Menue.getBackgroundVideoRankings().setFitHeight(720);
			Menue.getBackgroundVideoRankings().setFitWidth(1280);
		}
		
		root.getChildren().add(Menue.getBackgroundVideoRankings());
		//add texfield 
		TextField textField=new TextField();
		textField.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		textField.setAlignment(Pos.CENTER);
		textField.setStyle("-fx-text-inner-color: red;-fx-background-color: transparent;-fx-background-radius: 0;");
		textField.setTranslateX(570);
		textField.setTranslateY(600);
		textField.setPrefWidth(750);
		textField.setPrefHeight(50);
		textField.setOnKeyTyped(e->{
			if(10<textField.getLength())  //no name >10 characters
				e.consume();			
		});

		Text highscore=new Text(650,500,"YOU GOT A HIGH SCORE!");
		//add dropshadow animation
		DropShadow ds = new DropShadow();
		ds.setRadius(50);
		ds.setSpread(0.5);
		ds.setColor(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
		highscore.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		highscore.setEffect(ds);
		highscore.setCache(true);
		highscore.setFill(Color.WHITE);
		colorText = new AnimationTimer(){
            @Override
            public void handle(long arg0) {
           	t+=0.01;
        		if(t>1) {
        			ds.setColor(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
        			t=0;
        		}
            } 
		 };
		colorText.start(); 
		
		//add text
		Text enterName=new Text(740,570,"PLEASE ENTER YOUR NAME:");
		enterName.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		enterName.setFill(Color.WHITE);
		
		Text textProblem=new Text(840,680,"INVALID NAME");
		textProblem.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		textProblem.setFill(Color.RED);
		textProblem.setVisible(false);
		
		Text yourscore=new Text(800,750,"YOUR SCORE:");
		yourscore.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
		yourscore.setFill(Color.WHITE);
		
		Text score=new Text(820,800," "+this.score);
		score.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 40));
		score.setFill(Color.YELLOW);
		
		//720p
		if(Option.getWindowSize()) {
			enterName.relocate(525, 370);
			enterName.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
			textProblem.relocate(580, 440);
			textProblem.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
			yourscore.relocate(530, 470);
			yourscore.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
			score.relocate(560, 500);
			score.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 30));
			highscore.relocate(435, 300);
			highscore.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
			textField.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
			textField.setAlignment(Pos.CENTER);
			textField.setStyle("-fx-text-inner-color: red;-fx-background-color: transparent;-fx-background-radius: 0;");
			textField.setTranslateX(410);
			textField.setTranslateY(400);
			textField.setPrefWidth(450);
			textField.setPrefHeight(50);
			textField.setOnKeyTyped(e->{
				if(10<textField.getLength()) 
					e.consume();			
			});
		}
		root.getChildren().add(enterName);
		root.getChildren().add(highscore);
		root.getChildren().add(textField);
		root.getChildren().add(textProblem);
		root.getChildren().add(yourscore);
		root.getChildren().add(score);
		
		scene.setOnKeyPressed(e->{
			switch(e.getCode()) {
				case ENTER:
					if(textField.getLength()==0) { //no name with 0 characters
						textProblem.setVisible(true);
						Timer timer = new Timer();
    	        		TimerTask task = new TimerTask(){
    	        			public void run(){ 
    	        				textProblem.setVisible(false); //show invalid name text
    	        			}
    	        		};
    	        		timer.schedule(task, 2000);
					}else {
						//set name and add player to the specific highscore list
						this.name=textField.getText();
						if(game==0) 
							Rankings.addPacmanScore(this);
						else 
							Rankings.addSnakeScore(this);
						Rankings.write(); //save list in a txt
						Rankings.rankingMenue(scene); //open main menue
					}
				default:
			}
		});
		
		try {
			Menue.addOverlay(root);
		} catch (FileNotFoundException e1) {
			System.err.println("Datei wurde nicht gefunden.");
			e1.printStackTrace();
		}
		scene.setRoot(root);
	}
	
	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}
	
	public void setNumberInList(int numberInList) {
		this.numberInList=numberInList;
	}
	
	public int getNumberInList() {
		return numberInList;
	}
}
