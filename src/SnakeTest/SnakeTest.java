package SnakeTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
// import org.junit.jupiter.api.Test;

import Snake.Loop;
import Snake.Snake;
import Snake.UI;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

class SnakeTest {

	Pane root;
	Scene scene;
	Loop loop;
	Snake snake;

	@Before
	public void setUp() {
		root = new Pane();
		scene = new Scene(root);
		loop = new Loop(root, scene);
	}

	
	// @Test 
	public void move() { //Testen von Verschieben der Koordinaten um eine Einheit nach links
		loop.getSnakeControler().refreshSnake();
		int pos = Snake.snakeList.get(0).getX()-UI.pointsizeX;
		assertTrue(Snake.snakeList.get(0).getX()==pos);
	}
}
