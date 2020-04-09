package Snake;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class PowerUp extends Point {
	private Image image;
	private Random rnd = new Random();
	private Rectangle pic = new Rectangle();
	private static int random;

	public PowerUp(int x, int y, Image image) {
		super(x, y);
		this.image = image;
	}

	public Rectangle getPic() {
		return pic;
	}

	public void setPic(Rectangle pic) {
		this.pic = pic;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	/*
	 * Erstellt ein neues PowerUP und gibt es zurück.
	 */
	public PowerUp createMe(Pane root) {
		PowerUp power = new PowerUp(UI.getPlaygroundX() + rnd.nextInt(UI.playgroundWidth - UI.size),
				UI.getPlaygroundY() + rnd.nextInt(UI.playgroundHeight - UI.size), image);
		try {
			power.setImage(randomPowerUp());
		} catch (FileNotFoundException e) {
			System.err.println("Bilddatei wurde nicht gefunden.");
			e.printStackTrace();
		}
		return power;
	}

	/*
	 * Zeichnet das PowerUP.
	 */
	public void drawMe(Pane root) {
		pic = new Rectangle(getX(), getY(), 2 * UI.pointsizeX, 2 * UI.pointsizeY);
		pic.setFill(new ImagePattern(getImage()));
		root.getChildren().add(pic);
	}

	/*
	 * Überprüft ob das PowerUP gegessen wird. Verschiedene PowerUP lösen
	 * verschieden Sachen aus.
	 */
	public boolean eatMe(Pane root) {
		for (Boddy b : Snake.snakeList) {
			// Überschneiden sich ein Körperteil der Schlange und das Bild
			if (b.getR().getBoundsInParent().intersects(getPic().getBoundsInParent())) {

				Timer t = new Timer();
				switch (random) {
				case 0: // Schnelligkeit verändern für 15 Sekunden
					Loop.speed += 6;
					TimerTask reset0 = new TimerTask() {
						public void run() {
							Loop.speed -= 8;
						}
					};
					t.schedule(reset0, 15000);
					break;
				case 1: // Man kann für 15 Sekunden durch die Wände ohne zu sterben
					Controler.setBorder(false);
					UI.strokeColor = Color.WHITE;
					UI.playground.setOpacity(0.3);
					TimerTask reset1 = new TimerTask() {
						public void run() {
							UI.strokeColor = Color.BLACK;
							Controler.setBorder(true);
							UI.playground.setOpacity(1);
						}
					};
					t.schedule(reset1, 15000); // anderer Timer ?

//					UI.drawTimer(root, timer, 10);
					break;
				case 2:
					// +500 Punkte
					Controler.score += 500;
					UI.strokeColor = Color.LIGHTGREEN;
					TimerTask reset2 = new TimerTask() {
						public void run() {
							UI.strokeColor = Color.BLACK;
						}
					};
					t.schedule(reset2, 500); 

					System.out.println("Case: 2");
					break;
				case 3: // Schlange wird größer
					UI.snakeSizeX += 4;
					UI.snakeSizeY += 4;
					Loop.speed = Loop.speed - 3;

					TimerTask reset3 = new TimerTask() {
						public void run() {
							UI.snakeSizeX -= 4;
							UI.snakeSizeY -= 4;
							Loop.speed = Loop.speed + 2;
						}
					};
					t.schedule(reset3, 15000); // anderer Timer ?
					break;
				case 4: // Schlange wird kleiner

					UI.snakeSizeX -= 4;
					UI.snakeSizeY -= 4;
					Loop.speed = Loop.speed - 3;

					TimerTask reset4 = new TimerTask() {
						public void run() {
							UI.snakeSizeX += 4;
							UI.snakeSizeY += 4;
							Loop.speed = Loop.speed + 2;
						}
					};
					t.schedule(reset4, 15000);
					break;

				}
				playEatSound();
				b.getR().setVisible(false);
				return true;
			}
			return false;
		}
		return false;
	}
/*
 * Gibt ein zufälliges Bild zurück, was gleichzeitig einem zufälligen PowerUP entspricht.
 */
	public Image randomPowerUp() throws FileNotFoundException {
		random = rnd.nextInt(5);
//		random = 1;
		switch (random) {
		case 0: // speed
			image = new Image("file:recources\\Pictures\\Snake\\Apple.png");
			break;
		case 1: // border
			image = new Image("file:recources\\Pictures\\Snake\\Carrot.png");
			break;
		case 2: // food
			image = new Image("file:recources\\Pictures\\Snake\\Papaya.png");
			break;
		case 3: // size
			image = new Image("file:recources\\Pictures\\Snake\\Pineapple.png");
			break;
		case 4: //
			image = new Image("file:recources\\Pictures\\Snake\\Radish.png");
			break;
		}
		return image;
	}
}
