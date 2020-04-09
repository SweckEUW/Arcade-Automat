package Pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
The coin is a eatable object in the pacman game which adds 10 to the score when eaten
 */
public class Coin extends Eatable{
	
	/**
	Places a golden Rectangle at the given X and Y coordinates
	@param
	int x= x coordinate of the coin
	int y= y coordinate of the coin
	 */
	public Coin(int x, int y){
		setShape(new Rectangle(x-2,y-2,5,5));
		if(Level.getHieght()==720)
			setShape(new Rectangle(x-1.5,y-1.5,3,3));
		getShape().setFill(Color.PINK);
		setWorth(10);
	}
	
	@Override
	public void actionOnPickUp() {
		Sound.playMunchSound(); //plays munch sound when eaten
	}	
	
}
