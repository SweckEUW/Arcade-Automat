package Pacman;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

/**
The level is an abstract class which provides walls and eatables for the pacman game
 */
public abstract class Level {
	
	private static AnimationTimer powerBallAnimator,animator; //blink animation for the power balls and animation for blinking walls
	private ArrayList<Eatable> eatables=new ArrayList<Eatable>(); //all eatables of the level
	private ArrayList<Line> walls=new ArrayList<Line>(); //List that contains every wall piece
	private double t; //used as Timer for wallsColorChange() and eatablesColorChange()
	private static int width,height; //width and height of the window 
		 
	public Level(int width, int height){
		Level.width=width;
		Level.height=height;
	}

	/**
	Adds all walls and eatables to the lists and to the Pane
	 */
	public void setup(Pane root) {
		addCoinsToList();
		addPowerBallsToList();
		addWallsToList();	
		addFruitToList();
		for(Eatable e:eatables) {
			root.getChildren().add(e.getShape());
			if(e instanceof Fruit)
				((Fruit) e).setup(root, this); //fruit needs an extra setup 
		}
		addWalls(root);
		animatePowerBalls();
		addTeleportBlackBox(root);
	}
	
	/**
	Checks if the pac collides with any coin in the level and calls the appropriate method of the eatable
	 */
	public void checkColissionEatables(Pac pac) {
		for(Eatable e:eatables) {
			e.disappear(pac); //calls the method of the coin class
			if(e instanceof Fruit)
				((Fruit) e).setVisible(pac.getScoreLevel());
		}
	}
	
	
	/**
	sets the animation of the power-balls
	 */
	public void animatePowerBalls() {
		 powerBallAnimator = new AnimationTimer(){
	         @Override
	         public void handle(long arg0) {
	        	 for(Eatable e:eatables)
	     			if(e instanceof PowerBall)
	     				((PowerBall) e).animation();
	        }
		 };
	}
	
	/**
	Checks if the pac collides with any wall in the level
	 */
	public boolean checkColissionWalls(Bounds s) {
		for(Line l:walls)
			if(s.intersects(l.getBoundsInLocal()))
				return true;
		return false;
	}
	
	/**
	Separates the Scene in 29 columns (needed to place assets correctly)
	Gets and integer(column) and returns the coordinate of the column
	 */
	public int columnX(int x) {
		if(width==1920)
			return (int)((1920-1300)/28*(x+0.5)+637);
		else if(GameLoop.getLevelSelector()==1)
			return (int)((1080-700)/28*(x+0.5)+445);
		return (int)((1080-700)/28*(x+0.5)+460);
	}

	/**
	Separates the Scene in 31 rows (needed to place assets correctly).
	Gets and integer(row) and returns the coordinate of the row
	 */
	public int rowX(int x) {
		if(height==1080)
			return (int)((1080-400)/30*(x+0.5)+200);
		return (int)((720-300)/30*(x+0.5)+150);
	}
		
	/**
	Checks if any eatable is left in the level 
	 */
	public boolean checkIfLevelIsEmpty() {
		for(Eatable e:eatables) {
			if(!e.isDisabled()) 
				return false;	
		}
		return true;
	}
	
	
	/**
	resets the level(refilling the level with eatables)
	 */
	public void reset() {
		for(Eatable e:eatables)
			e.appear();
	}
	
	/**
	adds all walls to the Pane and also changing Color and thickness 
	 */
	public void addWalls(Pane root) {
		for (Line l:walls) {
			l.setStroke(Color.rgb(0, 60, 200));
			l.setStrokeWidth(2);
			root.getChildren().add(l);
		}
		//first wall is the ghost-cage wall (its pink and thicker)
		walls.get(0).setStroke(Color.PINK);
		walls.get(0).setStrokeLineCap(StrokeLineCap.BUTT);
		walls.get(0).setStrokeWidth(5);
	}
	
	public AnimationTimer getPowerBallAnimator() {
		t=0; //resets it
		return powerBallAnimator;
	}
	
	/**
	resets the color of the power-balls
	 */
	public void resetColorPowerBalls() {
		for(Eatable e:eatables)
			if(e instanceof PowerBall)
			e.getShape().setFill(Color.PINK);
	}
	
	/**
	sets the visibility of every active eatable
	 */
	public void setEatablesVisibility(boolean visibility) {
		for(Eatable e:eatables) {
			if(!e.isDisabled()) //ignores disabled eatables
				e.getShape().setVisible(visibility);
		}
	}
	
	/**
	frequently changes the Color of all wall pieces to white and back to normal color. Changes color for 2.5 seconds
	 */
	public void wallsColorChange() {
		for(Line l:walls)
			l.setStroke(Color.WHITE); //instantly sets the color to white for better feedback
		animator = new AnimationTimer(){
             @Override
             public void handle(long arg0) {
            	t+=0.1;
         		if(t>1.5) {
         			for(Line l:walls) 
         				l.setStroke(Color.rgb(0, 60, 200));
         		}
         		if(t>2.5) {
         			for(Line l:walls) 
         				l.setStroke(Color.WHITE);
         			t=0;
         		}
             } 
		 };
		 animator.start(); 
		 
		 Timer timer = new Timer();   
		 TimerTask task = new TimerTask(){
		       public void run(){
		        	animator.stop(); //stops animation after 2.5 seconds
		        	for(Line l:walls) 
		    			l.setStroke(Color.rgb(0, 60, 200)); //resets color
		    		walls.get(0).setStroke(Color.PINK); //reset the color of the ghost-cage wall
		    	}
		};
		timer.schedule(task, 2500); 
	}
	
	/**
	teleports the character from a constant location to another constant location 
	 */
	public abstract void teleport(Character c);
	
	/**
	adds blackboxes for smoother teleportation to the pane
	 */
	public abstract void addTeleportBlackBox(Pane root);
	
	/**
	adds all power-balls to the eatable list
	 */
	public abstract void addPowerBallsToList();
	
	/**
	adds all coins to the eatable list
	 */
	public abstract void addCoinsToList();
	
	/**
	adds all walls to the eatable list
	 */
	public abstract void addWallsToList();
	
	/**
	adds the fruit to the eatable list
	 */
	public abstract void addFruitToList();
	
	public ArrayList<Eatable> getEatableList(){
		return eatables;
	}
	
	public ArrayList<Line> getWallList(){
		return walls;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHieght() {
		return height;
	}
	
}
	
	
