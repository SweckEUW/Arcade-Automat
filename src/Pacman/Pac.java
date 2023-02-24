package Pacman;

import Menue.Menue;
import Menue.Option;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

/**
The main character of the game. this class contains all interactions from the pacman with other characters or objects.
It also contains the controller for the pacman
 */
public class Pac extends Character {
	
	private int score,scoreLevel; //Score of the Player (increases if pacman collects Coins,Balls etc.) scoreLevel is a score which will get reset after a round is complete (all eatables are gone)
	private Arc form; //the visible form of the pacman is an arc which changes its starting angle and length 
	private int life=3; //lifes get reduces when pacman dies to a ghost. game is over when life=0

	/**
	Also initializes the form of the Pacman
	 */
	public Pac(Level level,int startX,int startY){
		super(level,startX,startY);
		form=new Arc(0, 0,20,20, 0,360);
		//720p
		if(Level.getHieght()==720)
			form=new Arc(0, 0,12,12, 0,360);
		form.setType(ArcType.ROUND);
		form.setFill(Color.YELLOW);
		getForm().add(form); //adds to the form list
	}
	
	/** 
	Checks the colission of Pacman and the Ghost and returns an int representing in which way they collide (GameLoop lines 64-72)
	 */
	public int checkColissionGhosts() {
		for(Ghost g:Ghost.getallGhosts()) {
			if(g.getBounds().intersects(getBounds()) && !g.getfrightened() && g.getDeath()==false) //returns 1 when pacman hits ghost while its not frightened (=pacman death)
				return 1;
			if(g.getBounds().intersects(getBounds()) && g.getDeath()==false) { //returns 2 when pacman hits ghost while its frightened (=ghost death)
				Sound.playEatGhost(); //plays ghost eat sound
				g.death(true); //ghost=death
				g.getAnimationTimeline().stop(); //stopps ghost hover animation
				score+=Math.pow(2, Ghost.getGhostEatenCounter())*100; //adds score to the pacman (rises exponentially for every next ghost eaten during this frighten period)
				Ghost.setGhostEatenCounter(Ghost.getGhostEatenCounter()+1); //adds 1 to the ghost eaten counter which increases the worth of the next ghost eaten
				return 2;
			}
		}
		return 0; //return 0 if ghost and pacman doesnt touch at all
	}
	
	/** 
	sets up the pacman animation (mouth open, mouth closed... repeat)
	 */
	public void setAnimation() {
		getAnimationTimeline().getKeyFrames().add(new KeyFrame(Duration.millis(0),new KeyValue(form.lengthProperty(),360),new KeyValue(form.startAngleProperty(),0))); //mouth closed
		getAnimationTimeline().getKeyFrames().add(new KeyFrame(Duration.millis(120),new KeyValue(form.lengthProperty(),270),new KeyValue(form.startAngleProperty(),45))); //mouth open
		getAnimationTimeline().setAutoReverse(true);
		getAnimationTimeline().setCycleCount(-1); //repeat infinity times
	}

	/** 
	resets form to a full circle
	 */
	public void resetForm() {
		form.setStartAngle(0);
		form.setLength(360);
	}

	public void setScore(int score) {
		this.score=score;
	}
	
	public int getScore() {
		return score;
	}

	public void setScoreLevel(int scoreLevel) {
		this.scoreLevel=scoreLevel;
	}
	
	public int getScoreLevel() {
		return scoreLevel;
	}
	
	/** 
	rotates the arc to the direction where the pacman is facing
	@param x = current x direction (1 for right -1 for left)
	@param y = current y direction (1 for down -1 for up)
	 */
	@Override
	public void faceDirection(int x, int y) {
		switch(x){
			case 1:
				form.setRotate(0);
				break;
			case -1:
				form.setRotate(180);
		}
		
		switch(y){
			case 1:
				form.setRotate(90);
				break;
			case -1:
				form.setRotate(270);
		}		
	}

	/**
	Changes the next directions if the player presses a Key
	 */
	public void setDirectionNXNY(Pane root) {
		//gets keys out of the keys that are set in the options
		root.getScene().setOnKeyPressed((e)-> {
			if(e.getCode().equals(Option.getRightK())) { //right
				setDirectionNX(1);
				setDirectionNY(0);
			}else if(e.getCode().equals(Option.getDownK())){ //down
				setDirectionNX(0);
				setDirectionNY(1);
			}else if(e.getCode().equals(Option.getLeftK())){ //left
				setDirectionNX(-1);
				setDirectionNY(0);
			}else if(e.getCode().equals(Option.getUpK())){ //up
				setDirectionNX(0);
				setDirectionNY(-1);
			}else if(e.getCode().toString().equals("ENTER")) { //enter pauses the game
					if(GameLoop.isGameActive()) { //can only be paused if the game is active
						GameLoop.setGameActive(false); //pauses main loop
						Menue.menueDurringGame(root); //adds menue during game to the pane
					}
			}
		});
	}

	/**
	sets up the death animation and also plays it
	 */
	public void playDeathAnimation() {
		Timeline deathAnimation =new Timeline();
		form.setRotate(270);
		form.setStartAngle(45);
		form.setLength(270);
		deathAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(0.0),new KeyValue(form.lengthProperty(),270),new KeyValue(form.startAngleProperty(),45)));
		deathAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(1.3),new KeyValue(form.lengthProperty(),0),new KeyValue(form.startAngleProperty(),180)));
		deathAnimation.play();
	}

	public void setLife(int lifes) {
		this.life=lifes;
	}
	
	public int getLife() {
		return life;
	}
	
}