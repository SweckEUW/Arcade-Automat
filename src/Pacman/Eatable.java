package Pacman;

import javafx.scene.shape.Shape;

/**
Eatable is an abstract class for every eatable object in the pacman game.
 */
public abstract class Eatable {
	
	private int worth; //worth of the object(adds to the score of the pacman)
	private Shape shape; //appereance of the coin(coin=rectangle,power-ball=circle) 
	private boolean disabled; //gets disabled when eaten so you cant pick it up again

	/**
	Cheks if the pacman collided with the coin, adds 10 to the score of the pacman and disables the coin from the field
	 */
	public void disappear(Pac pac) {
		if(pac.getBounds().intersects(getShape().getBoundsInParent())&&!disabled) { //coin has to be enabled to get picked up
			disappear();
			pac.setScore(pac.getScore()+getWorth()); //add the worth of the eatable to the pac score
			pac.setScoreLevel(pac.getScoreLevel()+getWorth());  //add the worth of the eatable to the pac-level score
			actionOnPickUp();
		}
	}
	
	/**
	gets called when eatble gets eaten
	 */
	public abstract void actionOnPickUp();
	 
	/**
	sets the visibility of the coin Shape to true and disables the eatable
	 */
	public void appear() {
		disabled=false;
		shape.setVisible(true);
	}
	
	/**
	sets the visibility of the coin Shape to false and enables the eatable
	 */
	public void disappear() {
		disabled=true;
		shape.setVisible(false);
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public void setShape(Shape shape) {
		this.shape=shape;
	}
	
	public void setWorth(int worth) {
		this.worth=worth;
	}
	
	public int getWorth() {
		return worth;
	}
	
	public boolean isDisabled() {
		return disabled;
	}
	
	public void setDisabled(Boolean b) {
		disabled=b;
	}
}
