package Pacman;

import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
The character is an object which can either be a ghost or a pacman and contains things like movement or reset
 */
public abstract class Character {
	
	private Level level; //current level
	private int x,y,startX,startY;  //X Y coordinates of the character
	private Rectangle hitBox2= new Rectangle(0, 0, 7, 7); // "hitBox" of the character. Needed for collision detection with other characters or eatables.
	private Rectangle hitBox= new Rectangle(0, 0, 40, 40); // "hitBox" of the character. Needed for collision detection with walls.
	private int directionX,directionY; //Actual direction the character is moving at the moment (1,0 right) ,(-1,0 left) , (0,1 down) , (0,-1 up)
	private int directionNX,directionNY; //Next direction the character is trying to move to (desired direction) (1,0 right) ,(-1,0 left) , (0,1 down) , (0,-1 up)
	private Timeline animation=new Timeline(); //Timeline needed for character animation
	private static ArrayList<Character> allCharacters=new ArrayList<Character>(); //Static List for every character
	private ArrayList<Shape> form=new ArrayList<Shape>(); //List of every Shape that forms the appearance of the Character
	
	/**
	@param startX starting x coordinate of the character
	@param startY starting y coordinate of the character
	 */
	Character(Level level,int startX,int startY){
		this.level=level;
		this.startX=startX; 
		this.startY=startY;
		allCharacters.add(this); //adds the new constructed character into the static List
		//720p
		if(Level.getHieght()==720) {
			hitBox=new Rectangle(0, 0, 22,22);
			hitBox2= new Rectangle(0, 0, 4, 4);
		} 
	}
	
	/**
	Resets every character(position,directions,animation,form)
	 */
	public static void resetAllCharacters() {
		for(Character c:allCharacters)
			c.reset();
	}
	
	/**
	Resets the character
	 */
	public void reset() {
		getAnimationTimeline().stop(); //stops "walking" animation
		resetForm(); 
		resetDirection();
		resetPosition();
	}
	
	/**
	Resets the x and y coordinates of the character to the starting ones
	 */
	public void resetStartCoordinates() {
		setX(startX);
		setY(startY);
	}
	
	/**
	Resets the position of the HitBox and the form to the starting position
	 */
	public void resetPosition() {
		resetStartCoordinates();
		moveForm();
		moveHitBox(0,0);
		moveHitBox2();
	}
	
	/**
	Resets the current directions and the next directions
	 */
	public void resetDirection() {
		directionX=0;
		directionY=0;
		directionNX=0;
		directionNY=0;
	}
	/**
	Resets the appearance of the character
	 */
	public abstract void resetForm();
	
	/**
	Sets up every character(adds every node to the pane, move HitBox to needed position and moves the form to the needed position)
	 */
	public static void setupAllCharacters(Pane root) {
		for(Character c:allCharacters) 
			c.setup(root);	
	}
	
	/**
	Adds every part of the form to the Pane, sets up the animation and moves the character to the starting position
	 */
	public void setup(Pane root) {
		for(Shape s:form)
			root.getChildren().add(s);
		setAnimation();
		resetPosition();
	}
	
	/**
	Moves the form of the character
	 */
	public void moveForm() {
		for(Shape s:form) {
			s.setTranslateX(getX());
			s.setTranslateY(getY());
		}
	}
	
	/**
	Moves the HitBox
	@param
	int x= X distance the HitBox moves
	int y= Y distance the HitBox moves
	 */
	public void moveHitBox(int x,int y) {
		hitBox.setTranslateX(this.x+x-hitBox.getWidth()/2);
		hitBox.setTranslateY(this.y+y-hitBox.getHeight()/2);
	}

	/**
	Moves the HitBox2
	 */
	public void moveHitBox2() {
		hitBox2.setTranslateX(getX()-hitBox2.getWidth()/2);
		hitBox2.setTranslateY(getY()-hitBox2.getHeight()/2);
	}
	
	public Level getLevel() {
		return level;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	/**
	Returns bounds of the HitBox2
	 */
	public Bounds getBounds() {
		return hitBox2.getBoundsInParent();
	}
	
	public int getDirectionX() {
		return this.directionX;
	}
	
	public int getDirectionY() {
		return this.directionY;
	}
	
	public int getDirectionNX() {
		return this.directionNX;
	}
	
	public int getDirectionNY() {
		return this.directionNY;
	}
	
	public void setDirectionX(int x) {
		this.directionX=x;
	}
	
	public void setDirectionY(int y) {
		this.directionY=y;
	}
	
	public void setDirectionNX(int nx) {
		this.directionNX=nx;
	}
	
	public void setDirectionNY(int ny) {
		this.directionNY=ny;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
	}

	public ArrayList<Shape> getForm(){
		return form;
	}
	
	public Timeline getAnimationTimeline() {
		return this.animation;
	}
	
	/**
	Sets up the animation
	 */
	public abstract void setAnimation();
	
	/**
	Updates the position of the character
	 */
	public void update(Level level) {
		checkForNextMove();
		move();
		level.teleport(this);
	}
	
	/**
	Takes next directions and checks if the character can move to the direction(no wall in the way). if possible the character changes the direction to the next direction.
	 */
	public void checkForNextMove() {
		moveHitBox(getDirectionNX()*10, getDirectionNY()*10); //moves hitbox to the next direction
		if(!level.checkColissionWalls(hitBox.getBoundsInParent())) { //if it collides dont change the actual direction
			directionX=getDirectionNX();//change direction if it doesnt collide
			directionY=getDirectionNY();
		}
		moveHitBox(-getDirectionNX()*10, -getDirectionNY()*10); //move hitbox back to the starting location
	}
	
	/**
	Moves and rotates the Character in the given direction
	 */
	public void move() {
		switch(getDirectionX()){
			case 1:		
				actualMove(1,0);
				faceDirection(1,0);
				break;
			case -1:
				actualMove(-1,0);
				faceDirection(-1,0);
		}
		
		switch(getDirectionY()){						
			case 1:
				actualMove(0,1);
				faceDirection(0,1);
				break;
			case -1:
				actualMove(0,-1);
				faceDirection(0,-1);
		}
	}
	
	/**
	Rotates the character to the direction of the character
	(1,0 right) ,(-1,0 left) , (0,1 down) , (0,-1 up)
	 */
	public abstract void faceDirection(int x,int y);
	
	/**
	Moves character to the direction. Stops animation and movement if character hits wall
	@param
	int dx= X distance the Character moves
	int dy= Y distance the Character moves
	 */
	public void actualMove(int dx, int dy) {
		moveHitBox(dx,dy); //move hitbox
		if(level.checkColissionWalls(hitBox.getBoundsInParent())) { //if it collides stop animation and move hitbox back to starting location
			moveHitBox(-dx, -dy);
			animation.pause();
		}else {			//if it doesnt collide continue animation and move hitbox1, hitbox 2 and set new x and y value
			animation.play();
			x+=dx;
			y+=dy;
			moveForm();
			moveHitBox2();
		}
	}
	
	/**
	Sets visibility of the form for every character
	 */
	public static void setVisibleAllCharacters(boolean visibility) {
		for(Character c:allCharacters) 
			c.setVisible(visibility);
	}
		
	/**
	Sets visibility of the form for the character
	 */
	public void setVisible(boolean visibility) {
		for (Shape s:form)
			s.setVisible(visibility);
	}
	
	public static ArrayList<Character> getAllCharacters() {
		return allCharacters;
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public Rectangle getHitBox2() {
		return hitBox2;
	}
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
	
	/**
	moves hitbox and returns boolean wether it collided with a wall or not
	@param x translation of the hitbox
	@param y translation of the hitbox
	 */
	public Boolean isMovePossible(int x, int y) {
		moveHitBox(x,y);
		if(level.checkColissionWalls(hitBox.getBoundsInParent())) {
			moveHitBox(-x,-y);
			return false;
		}
		moveHitBox(-x,-y);
		return true;
	}	
	
}
