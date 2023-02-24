package Pacman;

import java.awt.Point;

import javafx.scene.paint.Color;

/**
The orange ghost tries to position itself infront of pacman during chase mode. In scatter mode it will move to the top left corner of the level.
 */
public class GhostPink extends Ghost {
	
	private double t; //time for new calculation of a path
	private boolean b; //true if it found a path infront of pacman
	
	GhostPink(Level level,int startX,int startY) {
		super(level,startX,startY);
		setDefaultColor(Color.PINK);
		changeColor(getDefaultColor());
	}

	@Override
	public void calculatePath(Pac pac) {
		t+=0.01;
		if(!getDeath()&&!getStart()) { //if its not dead or in start mode calculate a path
			if(getChase()&&!getFrightened()) { //if chase mode is active
				if(t>0.6||getPath().isEmpty()) { //after 1 seconds calculate a new path or when the current path is empty 
					b=false; //reset found path boolean
					for(int i=0;i>20;i--) { //searches 80 to 70 pixels infront of pacman (
						if(getEveryPath().contains(new Point(pac.getX()+pac.getDirectionNX()*i,pac.getY()+pac.getDirectionNY()*i))) { //if the point is in the every path list 
							pathFinding(new Point(pac.getX()+pac.getDirectionNX()*i,pac.getY()+pac.getDirectionNY()*i));	//calculate the path
							b=true;
							break;
						}
					}
					if(!b) //if there is no possible path 80-70 pixels infront of pacman
						pathFinding(new Point(pac.getX(),pac.getY())); //random path					
					t=0;
				}
			}else if(!getStart()&&getFrightened()) { //if frightened search a random path
				if(t>1||getPath().isEmpty()) {
					pathFinding(getEveryPath().get((int)(Math.random()*getEveryPath().size()-1)));
					t=0;
				}
			}else if(!getStart()){
				if(getPath().isEmpty()) 
					pathFinding(getTopLeft().get((int)(Math.random()*getTopLeft().size()-1))); //in scatter mode move to the top left corner 
			}
		}
	}

	@Override
	public void moveOutOfBase() {
		
		resetDirection();
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
		if(getX()==Ghost.getOutOfBaseX() && getY()==Ghost.getOutOfBaseY()) {
			pathFinding(getTopLeft().get((int)(Math.random()*getTopLeft().size()-1)));
			setStart(false);
		}
		
	}
	
}
