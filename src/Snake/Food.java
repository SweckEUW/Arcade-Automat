package Snake;

import java.util.Random;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/*
 * Die Klasse erbt von der Klasse Point eine x- und eine y-Koordinate, um
 * das Essen auf der Pane platzieren zu können.
 */
public class Food extends Point {

	private Color cc;
	private Random rnd = new Random();
	private Circle circle = new Circle();
	private Food food;
	private int radiusCircle = UI.pointsizeX / 2;

	public Color getCc() {
		return cc;
	}

	public void setCc(Color cc) {
		this.cc = cc;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public Food(int x, int y, Color cc) {
		super(x, y);
		this.cc = cc;
	}

/*
 * Methode um eine zufällige Farbe von 5 verschiedenen auszuwählen.
 */
	public Color randomColor() {
		int random = rnd.nextInt(5);
		Color cc = Color.WHITE;
		switch (random) {
		case 0:
			cc = Color.GREEN;
			break;
		case 1:
			cc = Color.BLUE;
			break;
		case 2:
			cc = Color.YELLOW;
			break;
		case 3:
			cc = Color.RED;
			break;
		case 4:
			cc = Color.PINK;
			break;
		case 5:
			cc = Color.TURQUOISE;
			break;

		}
		return cc;
	}

/*
 * Methode um neues Essen zu erstellen und die Schnelligkeit der Schlange zu erhöhen.
 */
	public Food newFood(Pane root) {
		food = new Food(roundToX(UI.playgroundX + rnd.nextInt(UI.playgroundWidth - UI.size)),
				roundToY(UI.playgroundY + rnd.nextInt(UI.playgroundHeight - UI.size)), // Anpassen!!!
				cc);
		food.setCc(randomColor());
		return food;
	}

	/*
	 *  Methode um das Essen auf der Pane zeichnen zu lassen.
	 */
	public void drawFood(Pane root) {
		circle = new Circle(getX() + radiusCircle, getY() + radiusCircle, radiusCircle);
		circle.setFill(getCc());
		circle.setStroke(Color.BLACK);
		root.getChildren().add(circle);
	}

/*
 * Methode um abzufragen ob die Schlange das Essen berührt hat.
 */
	public boolean eatFood() {
		for (Boddy b : Snake.snakeList) {

			if (b.getR().getBoundsInParent().intersects(getCircle().getBoundsInParent())) { // Kreis.x == p.X
				playEatSound();
				Loop.speed += 0.5;
				Snake.snakeList.add(new Boddy(-10, -10)); // Genau sagen wo sie was anhängen soll
				return true;
			}
		}
		return false;
	}
}