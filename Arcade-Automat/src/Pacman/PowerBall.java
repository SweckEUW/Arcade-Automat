package Pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
the Power-ball is a blinking circle which activates the frightened phase when eaten. 
 */
public class PowerBall extends Eatable {
	
	private double t; //needed for timing of the animation
	
	/**
	Places a white circle at the given X and Y coordinates and changes the worth of the power-ball to 50
	@param
	int x= x coordinate of the power-ball
	int y= y coordinate of the power-ball
	 */
	public PowerBall(int x, int y){
		setShape(new Circle(x,y,10));
		//70p
		if(Level.getHieght()==720)
			setShape(new Circle(x,y,7));
		getShape().setFill(Color.PINK);
		setWorth(50); //adds 50 when eaten
	}
	
	@Override
	public void actionOnPickUp() {
		for(Ghost g:Ghost.getallGhosts()) 
			g.phaseFrightened(); //activates frightened phase of all ghosts
	}
	
	/**
	Applies a blink animation to the shape
	 */
	public void animation() {
        	t+=0.07; //gets called 60 per seconds
        	if(t>1) { //gets called approximate every 2 second
        		getShape().setFill(Color.TRANSPARENT); //sets color to the backgroundColor and not to invisible(it would not get picked up during the time it is invisible -> disappear())
        		if(t>2){  //gets called approximate every 4 second
        			getShape().setFill(Color.PINK);
        			t=0;
        		}
        	}  
	}

	
	
}
