package Paman_Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import Pacman.Level;
import Pacman.LevelStandard;
import Pacman.Pac;
import javafx.animation.Animation.Status;
import javafx.scene.layout.Pane;

public class CharacterTest {

	Pac pac;
	Level level;
	Pane root;
	
	@Before
	public void setUp() throws Exception {
		root=new Pane();
    	level=new LevelStandard(1920,1080);
    	pac=new Pac(level,(level.columnX(13)+level.columnX(14))/2,level.rowX(23));
	}
	
	//testing move function of the character
	@Test
	public void move1() {
		pac.setDirectionX(1);
		pac.move();
		assertSame(1, pac.getX() );
		assertSame(0, pac.getY() );
		assertSame((int)pac.getForm().get(0).getTranslateX(),1);
		assertSame((int)pac.getForm().get(0).getTranslateY(),0);
		assertSame((int)(pac.getHitBox().getTranslateX()+pac.getHitBox().getWidth()/2),pac.getX());
		assertSame((int)(pac.getHitBox().getTranslateY()+pac.getHitBox().getWidth()/2),pac.getY());
		assertSame((int)(pac.getHitBox2().getTranslateX()+pac.getHitBox2().getWidth()/2),pac.getX());
		assertSame((int)(pac.getHitBox2().getTranslateY()+pac.getHitBox2().getWidth()/2),pac.getY());
	}

	@Test
	public void move2() {
		pac.setDirectionY(1);
		pac.move();
		assertSame(0, pac.getY() );
		assertSame(0, pac.getX() );
		assertSame((int)pac.getForm().get(0).getTranslateX(), 0 );
		assertSame((int)pac.getForm().get(0).getTranslateY(), 0 );
		assertSame((int)(pac.getHitBox().getTranslateX()+pac.getHitBox().getWidth()/2),pac.getX());
		assertSame((int)(pac.getHitBox().getTranslateY()+pac.getHitBox().getWidth()/2),pac.getY());
		assertSame((int)(pac.getHitBox2().getTranslateX()+pac.getHitBox2().getWidth()/2),pac.getX());
		assertSame((int)(pac.getHitBox2().getTranslateY()+pac.getHitBox2().getWidth()/2),pac.getY());
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
		assertSame((int)(pac.getHitBox().getTranslateX()+pac.getHitBox().getWidth()/2),pac.getX());
		assertSame((int)(pac.getHitBox().getTranslateY()+pac.getHitBox().getWidth()/2),pac.getY());
		assertSame((int)(pac.getHitBox2().getTranslateX()+pac.getHitBox2().getWidth()/2),pac.getX());
		assertSame((int)(pac.getHitBox2().getTranslateY()+pac.getHitBox2().getWidth()/2),pac.getY());
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
		assertSame((int)(pac.getHitBox().getTranslateX()+pac.getHitBox().getWidth()/2),pac.getX());
		assertSame((int)(pac.getHitBox().getTranslateY()+pac.getHitBox().getWidth()/2),pac.getY());
		assertSame((int)(pac.getHitBox2().getTranslateX()+pac.getHitBox2().getWidth()/2),pac.getX());
		assertSame((int)(pac.getHitBox2().getTranslateY()+pac.getHitBox2().getWidth()/2),pac.getY());
	}
	
	@Test
	public void reset() {
		pac.reset();
		assertTrue(pac.getAnimationTimeline().getStatus()!=Status.RUNNING);
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
