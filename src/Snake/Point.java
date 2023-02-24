package Snake;

/* 
 * Die Klasse gibt den Klassen Boddy und Food eine x- und y-Koordinate vor.
*/
public abstract class Point {
	
	Sound sound = new Sound();
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/*
	 * Um eine Zahl passend auf die Spielfeldbreite aufzurunden.
	 */
	public int roundToX(int a) {
		// Aufrunden
		if (a % UI.pointsizeX != 0 ) {
			for (int i = 1; i < UI.pointsizeX; i++) {
				if ((a + i) % UI.pointsizeX == 0 && (a+i)<(UI.playgroundX+UI.playgroundWidth)) {
					return a + i;
				}
			}
		}
		return a;
	}

	/*
	 * Um eine Zahl passend auf die Spielfeldhöhe aufzurunden.
	 */
	public int roundToY(int a) {
		if (a % UI.pointsizeY != 0) {
			for (int i = 1; i < UI.pointsizeY; i++) {
				if ((a + i) % UI.pointsizeY == 0 && (a+i) < (UI.playgroundY+UI.playgroundHeight)) {
					return a + i;
				}
			}
		}
		return a;
	}
/*
 * Dadurch ist das Essensgeräusch abspielbar ohne das die Klasse Sound erstellt werden muss.
 */
	public void playEatSound() {
		sound.playEat();
	}
}
