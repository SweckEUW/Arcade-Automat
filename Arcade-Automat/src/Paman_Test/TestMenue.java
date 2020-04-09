package Paman_Test;

import org.junit.Before;
import org.junit.Test;
import Menue.Menue;
import javafx.scene.layout.Pane;

public class TestMenue {

	Pane root;
	
	@Before
	public void setUp() throws Exception {
		root=new Pane();
	}
	
	//that the red ghost finds at least some points on the level
	@Test
	public void testSwitch() {
		Menue.switchMenue(-1);
		
	}

}