package Pacman;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
The fruit is a eatable object which gets activated after the pacman reaches a certain score in every round.
the eatable gets represented by a picture of cherrys
 */
public class Fruit extends Eatable {

	private Text worth;
	private ImageView cherryView;
	private static Boolean f=true;

	/**
	Places a picture of a cherry at the given X and Y coordinates
	@param
	int x= x coordinate of the fruit
	int y= y coordinate of the fruit
	 */
	public Fruit(int x, int y){
		Circle c=new Circle(x,y,15); 
		c.setFill(Color.TRANSPARENT); //set transparent because the shape is not needed for the fruit
		setShape(c);
		//add cherry image
		Image cherry = new Image(new File("recources/Pictures/Pacman/Cherry.jpg").toURI().toString());
		cherryView =new ImageView(cherry);
		cherryView.relocate(x-20, y-20);
		//is disabled and invisible until pacman has a certain score during every round
		cherryView.setVisible(false); 
		disappear(); 
		setWorth(500);
	} 
	
	@Override
	public void actionOnPickUp() {
		Sound.playEatFruit(); //play eat fruit sound
		worth.setVisible(true); //show text that shows the worth of the fruit
		cherryView.setVisible(false); //set the image to invisible
		Timer timer = new Timer();   
		TimerTask task = new TimerTask(){
	        public void run(){
	        	worth.setVisible(false); //set the text that shows the worth of the fruit to invisible after 2.5 seconds
	    	}    
		};
		timer.schedule(task, 2500);	
	}

	/**
	sets up the fruit and adds it part to the pane
	 */ 
	public void setup(Pane root,Level level) {
		//add text that shows the worth of the fruit
		worth=new Text(level.columnX(12)+10,level.rowX(17)+10,""+getWorth()); 
		//720p
		if(Level.getHieght()==720) 
			worth=new Text(level.columnX(12)+3,level.rowX(17)+5,""+getWorth());
		//720p and hshl level
		if(GameLoop.getLevelSelector()==1&&Level.getHieght()==720)
			worth=new Text(level.columnX(15-2),level.rowX(18)+5,""+getWorth());
		//1080p and hshl level
		if(GameLoop.getLevelSelector()==1&&Level.getHieght()==1080)
			worth=new Text(level.columnX(15-2),level.rowX(18)+10,""+getWorth());
		worth.setFill(Color.RED);
		worth.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 15));
		//720p
		if(Level.getHieght()==720) {	
			worth.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
			cherryView.setScaleX(0.5); //scale cherry image down
			cherryView.setScaleY(0.5);
		}
		worth.setVisible(false); //set text invisible
		root.getChildren().add(worth);
		root.getChildren().add(cherryView);
	}
	
	public void appear() {
		f=true; //reset f so the setVisible method can trigger again
	}
	
	/**
	sets the fruit visible when the score of the current round reaches 1000
	 */ 
	public void setVisible(int score) {
		if(score>1000&&f) {
			f=false; //so this method gets called only once
			setDisabled(false); //enable fruit
			cherryView.setVisible(true); //set the cherry image to visible
			Timer timer = new Timer();   
			TimerTask task = new TimerTask(){
		        public void run(){
		        	setDisabled(true); //disable after 10 seconds
					cherryView.setVisible(false); //set invisible after 10 seconds
		    	}    
			};
			timer.schedule(task, 10000);	
		}		
	}
	
}
