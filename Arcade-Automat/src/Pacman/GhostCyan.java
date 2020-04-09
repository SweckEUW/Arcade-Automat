package Pacman;

import javafx.scene.paint.Color;

/**
The cyan ghost moves randomly across the level during chase mode. In scatter mode it will move to the bottom right corner of the level.
 */
public class GhostCyan extends Ghost{
	
	GhostCyan(Level level,int startX,int startY) {
		super(level,startX,startY);
		setDefaultColor(Color.CYAN); //default color cyan
		changeColor(getDefaultColor());
	}

	@Override
	public void calculatePath(Pac pac) {
		if(!getDeath()&&!getStart()) {
			if(getChase()&&!getFrightened()) { 
				if(getPath().isEmpty()) //if the ghost is at its destination calculate a new path 
					pathFinding(getEveryPath().get((int)(Math.random()*getEveryPath().size()-1))); // =random path
			}else if(!getStart()&&getFrightened()) {
				if(getPath().isEmpty()) 
					pathFinding(getEveryPath().get((int)(Math.random()*getEveryPath().size()-1)));
			}else if(!getStart())
				if(getPath().isEmpty()) 
					pathFinding(getBottomRight().get((int)(Math.random()*getBottomRight().size()-1)));	//scatter mode moves to the bottom right corner	
		}
	}

	@Override
	public void moveOutOfBase() { //standard level = move right than up, HSHL level= move up than left
		resetDirection();
		if(getX()!=Ghost.getMiddleOfBaseOfBaseX() && getY()!=Ghost.getMiddleOfBaseOfBaseY()) {
			if(GameLoop.getLevelSelector()==1) {
				setY(getY()-1);
				faceDirection(0,-1);
			}
			else {
				setX(getX()+1);
				faceDirection(1,0);
			}
			moveForm();
			moveHitBox2();				
		}else {
			if(GameLoop.getLevelSelector()==1) {
				setX(getX()-1);
				faceDirection(-1,0);
			}
			else {
				setY(getY()-1);
				faceDirection(0,-1);
			}
			moveForm();
			moveHitBox2();
			if(getX()==Ghost.getOutOfBaseX() && getY()==Ghost.getOutOfBaseY()) { //got out of base
				pathFinding(getBottomRight().get((int)(Math.random()*getBottomRight().size()-1))); //scatter mode path 
				setStart(false);
			}	
		}		
	}
	
}
