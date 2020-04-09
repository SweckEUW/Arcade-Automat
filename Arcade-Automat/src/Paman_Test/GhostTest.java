package Paman_Test;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import Pacman.*;
import Pacman.Character;
import javafx.scene.layout.Pane;

public class GhostTest {

	Pac pac;
	Level level;
	Pane root;
	Ghost red;
	
	@Before
	public void setUp() throws Exception {
		root=new Pane();
    	level=new LevelStandard(1920,1080);
    	level.setup(root);
    	pac=new Pac(level,(level.columnX(13)+level.columnX(14))/2,level.rowX(23));
    	red=new GhostRed(level,(level.columnX(13)+level.columnX(14))/2,level.rowX(11));
    	Character.setupAllCharacters(root);
	}
	
	//that the red ghost finds at least some points on the level
	@Test
	public void everyPath() {
		red.pathFinding(new Point(pac.getX(),pac.getY()));
		assertSame(red.getPath().get(0),new Point(pac.getX(),pac.getY()));
	}

	@Test
	public void move2() {
		assertTrue(red.getStartX()==red.getX() );
	}

	@Test
	public void move3() {
		pac.setDirectionX(1);
		pac.setDirectionY(1);
		pac.move();
		assertSame(1, pac.getX() );
		assertSame(1, pac.getY() );
		assertSame((int)pac.getForm().get(0).getTranslateX(), 1 );
		assertSame((int)pac.getForm().get(0).getTranslateY(), 1 );
	}
	@Test
	public void move4() {
		pac.setDirectionX(-1);
		pac.setDirectionY(-1);
		pac.move();
		assertSame(-1, pac.getX() );
		assertSame(-1, pac.getY() );
		assertSame((int)pac.getForm().get(0).getTranslateX(), -1 );
		assertSame((int)pac.getForm().get(0).getTranslateY(), -1 );
	}
	
	@Test
	public void reset() {
		pac.reset();
		assertSame(0, pac.getDirectionX() );
		assertSame(0, pac.getDirectionY() );
		assertSame(0, pac.getDirectionNX() );
		assertSame(0, pac.getDirectionNY() );
		assertTrue(pac.getStartX()==pac.getX() );
		assertTrue(pac.getStartY()==pac.getY() );
	}
	
	@Test
	public void setup() {
		pac.setup(root);
		assertSame(0, pac.getDirectionX() );
		assertSame(0, pac.getDirectionY() );
		assertSame(0, pac.getDirectionNX() );
		assertSame(0, pac.getDirectionNY() );
		assertTrue(pac.getStartX()==pac.getX() );
		assertTrue(pac.getStartY()==pac.getY() );
	}
}