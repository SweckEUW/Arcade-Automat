package Pacman;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
The interface contains every visible other visible text or symbol that shows during the game (current score, game over text, ready! text etc)
 */
public class Interface {

	private Text score,ready,gameOver,oneUp,highScoreT,highScore; //text that can show up during game
	private Arc life1,life2,life3,life4; //life symbols at the bottom left corner of the game(show how many lifes pacman has left)
	private ArrayList<Arc> lifes=new ArrayList<Arc>(); //list with every life symbols 
	private Rectangle blackScreen; //blackscreen to turn the whole screen black
	private Level level;
	private boolean m=true; //needed for updateScoreText()
	private double t; //needed for the blinking animation
	private static AnimationTimer animator; //1 up text blink animation
	
	Interface(Level level){
		this.level=level;
	} 
	
	/**
	sets up life symbols and adds everything to the pane
	@param currentHighScore= highest score of the pacman highscore list
	 */
	public void setup(Pane root,int currentHighScore) {
		addLifes(root);
		try {
			addReadyText(root);
			addScoreText(root);
			addGameOverText(root);
			add1UpText(root);
			addHIGH_SCOREText(root);
			addHighScoreText(root,currentHighScore);
		} catch (FileNotFoundException e) {
			System.err.println("Eine Datei wurde nicht gefunden.");
			e.printStackTrace();
		}
		addBlackScreen(root);
	}
	
	/**
	adds all life symbols to the pane and to the list
	 */
	public void addLifes(Pane root) {
		life1=new Arc(level.columnX(1),level.rowX(31)+7,20,20,215,290);
		life2=new Arc(level.columnX(3),level.rowX(31)+7,20,20,215,290);
		life3=new Arc(level.columnX(5),level.rowX(31)+7,20,20,215,290);
		life4=new Arc(level.columnX(7),level.rowX(31)+7,20,20,215,290);
		//720p
		if(Level.getHieght()==720) {
			life1=new Arc(level.columnX(1),level.rowX(31)+5,12,12,215,290);
			life2=new Arc(level.columnX(3),level.rowX(31)+5,12,12,215,290);
			life3=new Arc(level.columnX(5),level.rowX(31)+5,12,12,215,290);
			life4=new Arc(level.columnX(7),level.rowX(31)+5,12,12,215,290);
		}
		lifes.add(life1);
		lifes.add(life2);
		lifes.add(life3);
		lifes.add(life4);
		life4.setVisible(false); //invisible (can be activated when you get an extra life with a score of 10.000)
		for(Arc a:lifes) {
			a.setFill(Color.YELLOW);
			a.setType(ArcType.ROUND);
			root.getChildren().add(a);
		}
	}
	
	/**
	adds "ready!" text that shows up when you start the game, get to the next round or die
	 */
	public void addReadyText(Pane root) throws FileNotFoundException {
		ready=new Text(level.columnX(10)+20,level.rowX(17)+5,"Ready!");
		ready.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		//720p
		if(Level.getHieght()==720)
			ready.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
		//HSHL-level && 1080p
		if(GameLoop.getLevelSelector()==1&&Level.getHieght()==1080) {
			ready=new Text(level.columnX(12)-5,level.rowX(19)-10,"Ready!");
			ready.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		}
		//HSHL-level && 720p
		if(GameLoop.getLevelSelector()==1&&Level.getHieght()==720) {
			ready=new Text(level.columnX(12)-5,level.rowX(19)-10,"Ready!");
			ready.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
			
		}
		ready.setFill(Color.YELLOW);
		root.getChildren().add(ready);
	}
	
	/**
	adds the current score to the pane
	 */
	public void addScoreText(Pane root) throws FileNotFoundException {
		score=new Text(level.columnX(2),level.rowX(-1)+10,""+0);
		score.setFill(Color.WHITE);
		score.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		if(Level.getHieght()==720)
			score.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
		root.getChildren().add(score);
	}
		
	/**
	updates the current highscore text if the pacScore is > current highscore
	 */
	public void updateHighScoreText(int pacScore,int currentHighScore) {
		if(pacScore>currentHighScore)
			highScore.setText(""+pacScore);
	}
	
	/**
	adds highscore as a number to the pane
	 */
	public void addHighScoreText(Pane root,int currentHighScore) throws FileNotFoundException{
		highScore=new Text(level.columnX(12),level.rowX(-1)+10,""+currentHighScore);
		highScore.setFill(Color.WHITE);
		highScore.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		if(Level.getHieght()==720)
			highScore.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
		if(GameLoop.getLevelSelector()==1)
			highScore.setTranslateX(40);
		if(GameLoop.getLevelSelector()==1&&Level.getHieght()==1080)
			highScore.setTranslateX(80);
		root.getChildren().add(highScore);
	}
	
	/**
	adds highscore as a text to the pane
	 */
	public void addHIGH_SCOREText(Pane root) throws FileNotFoundException {
		highScoreT=new Text(level.columnX(9),level.rowX(-2)+10,"HIGH SCORE");
		if(Level.getHieght()==720) 
			highScoreT=new Text(level.columnX(10)-5,level.rowX(-2)+10,"HIGH SCORE");
		highScoreT.setFill(Color.WHITE);
		highScoreT.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		if(Level.getHieght()==720) 
			highScoreT.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
		if(GameLoop.getLevelSelector()==1)
			highScoreT.setTranslateX(40);
		if(GameLoop.getLevelSelector()==1&&Level.getHieght()==1080)
			highScoreT.setTranslateX(80);
		root.getChildren().add(highScoreT);
	}
	
	/**
	adds 1up text to the pane and animates it
	 */
	public void add1UpText(Pane root) throws FileNotFoundException {
		oneUp=new Text(level.columnX(2),level.rowX(-2)+10,"1UP");
		oneUp.setFill(Color.WHITE);
		oneUp.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));		
		if(Level.getHieght()==720)
			oneUp.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
		root.getChildren().add(oneUp);
		animator = new AnimationTimer(){
             @Override
             public void handle(long arg0) {
            	t+=0.08;
         		if(t>1) 
         			oneUp.setVisible(false);
         		if(t>3) {
         			oneUp.setVisible(true);
         			t=0;
         		}
             } 
		 };
		 animator.start(); 
	}
	
	/**
	adds game over text to the pane
	 */
	public void addGameOverText(Pane root) throws FileNotFoundException {
		gameOver=new Text(level.columnX(10)-15,level.rowX(17)+5,"Game Over");
		if(GameLoop.getLevelSelector()==1)
			gameOver=new Text(level.columnX(10),level.rowX(17+1)+5,"Game Over");
		gameOver.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		if(Level.getHieght()==720) {
			gameOver=new Text(level.columnX(10)+5,level.rowX(17)+5,"Game Over");
			gameOver.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
		}
		if(GameLoop.getLevelSelector()==1)
			gameOver.relocate(level.columnX(10)+5, level.rowX(17)+10);
		gameOver.setFill(Color.RED);
		gameOver.setVisible(false); //gets visible when the game is over
		root.getChildren().add(gameOver);
	}
	
	/**
	adds a black screen to the pane
	 */
	public void addBlackScreen(Pane root) {
		blackScreen=new Rectangle(0,0,1920,1080);
		blackScreen.setFill(Color.BLACK);
		blackScreen.setVisible(false);
		root.getChildren().add(blackScreen);
	}
	
	public Text getReadyText() {
		return ready;
	}
	
	/**
	updates the interface
	 */
	public void update(Pac pac,int currentHighScore) {
		updateScoreText(pac); //updates your current score
		updateHighScoreText(pac.getScore(),currentHighScore); //updates the hoghscore when your score is > current highscore
	}
	
	/**
	updates your current score and adds a bonus life when you get a score > 10.000
	 */
	public void updateScoreText(Pac pac) {
		this.score.setText(""+pac.getScore());
		if(pac.getScore()>10000 && m) {
			Sound.playExtraLive();
			m=false; //can only get activated once a game
			lifes.get(pac.getLife()).setVisible(true); //set an extra life symbol to true
			pac.setLife(pac.getLife()+1); //add a life to the pacman
		}
	}
		
	/**
	removes a life symbol and a life from the pacman
	 */
	public void removeLife(Pac pac) {
		pac.setLife(pac.getLife()-1);
		lifes.get(pac.getLife()).setVisible(false);
	}
	
	public Text getGameOverText() {
		return gameOver;
	}
	
	public Rectangle getBlackScreen() {
		return blackScreen;
	}
	
}
