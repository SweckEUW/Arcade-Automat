package Pacman;

import java.awt.Point;
import javafx.scene.paint.Color;

/**
The red ghost tries to chase pacman during chase mode. In scatter mode it will move to the top right corner of the level.
The red ghost is the most aggressive one and will follow pacman in chase mode
 */
public class GhostRed extends Ghost {
	
	private double t;
	
	public GhostRed(Level level,int startX,int startY) {
		super(level,startX,startY);
		setDefaultColor(Color.RED);
		changeColor(getDefaultColor());
	}

	@Override
	public void calculatePath(Pac pac) {
		t+=0.01;
		if(!getDeath()&&!getStart()) { //if its not dead or in start mode calculate a path
			if(getChase()&&!getFrightened()) {//if chase mode is active
				if(t>1||getPath().isEmpty()) { //after 1.33 seconds calculate a new path or when the current path is empty 
					pathFinding(new Point(pac.getX(),pac.getY())); //straight up chase pacman
					t=0;
				}
			}else if(!getStart()&&getFrightened()) {  //if frightened calculate a random path
				if(t>1||getPath().isEmpty()) {
					pathFinding(getEveryPath().get((int)(Math.random()*getEveryPath().size()-1)));
					t=0;
				}
			}else if(!getStart()){
				if(getPath().isEmpty()) 
					pathFinding(getTopRight().get((int)(Math.random()*getTopRight().size()-1)));
			}
		}
	}

	@Override
	public void moveOutOfBase() {	//the red ghost is already out of the base (same in the original game)
		pathFinding(getTopRight().get((int)(Math.random()*getTopRight().size()-1)));
		setStart(false);
	}
	


}
