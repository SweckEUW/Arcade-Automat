package Snake;

import java.util.ArrayList;

import javafx.scene.layout.Pane;

/*
 * Die Klasse besteht aus einer ArrayList die die einzelnen Körperteile verwaltet.
 */
public class Snake {

	//Liste mit allen Körperteilen der Schlange
	public static ArrayList<Boddy> snakeList = new ArrayList<Boddy>();

	// Zeichnen der Schlange
	public static void drawSnake(Pane root, Controler.Direction direction) {
		Snake.snakeList.get(0).drawHead(root, direction);
		for (int i = 1; i < Snake.snakeList.size(); i++) {
			Snake.snakeList.get(i).drawBoddy(root);
		}
	}

}
