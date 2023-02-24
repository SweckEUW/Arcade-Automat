package Snake;

import Snake.Controler.Direction;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/*
 * Die Klasse spiegelt die einzelnen Körperteile der Schlange wieder.
 */ 
public class Boddy extends Point {

	private Image imgBoddy = new Image("file:recources\\Pictures\\Snake\\Körper.png");
	private Image imgHead = new Image("file:recources\\Pictures\\Snake\\Kopf.png");
	private Rectangle r;

	public Rectangle getR() {
		return r;
	}

	public void setR(Rectangle r) {
		this.r = r;
	}

	public Boddy(int x, int y) {
		super(x, y);
	}

	/*
	 * Zeichnet den Kopf der Schlange.
	 */
	public void drawHead(Pane root, Direction direction) {
		r = new Rectangle(getX(), getY(), UI.snakeSizeX, UI.snakeSizeY); // Rectangle(x,y,width,height
		r.setFill(new ImagePattern(imgHead));
		switch (direction) { //überprüft die Richtung des Kopfes
		case UP:
			r.setRotate(0);
			break;
		case DOWN:
			r.setRotate(180);
			break;
		case LEFT:
			r.setRotate(-90);
			break;
		case RIGHT:
			r.setRotate(90);
			break;
		default:
			break;
		}
		root.getChildren().add(r);
	}

	/*
	 * Zeichnet ein Körperteil der Schlange.
	 */
	public void drawBoddy(Pane root) {
		r = new Rectangle(this.getX(), this.getY(), UI.snakeSizeX, UI.snakeSizeY);
		r.setFill(new ImagePattern(imgBoddy));
		root.getChildren().add(r);
	}

}
