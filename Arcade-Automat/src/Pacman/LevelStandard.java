package Pacman;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class LevelStandard extends Level {

	public LevelStandard(int width,int height){
		super(width,height);
	}
	
	public void addWallsToList() {
		getWallList().add(new Line((columnX(12)+columnX(13))/2,((rowX(12)+rowX(13))/2+rowX(12))/2,(columnX(14)+columnX(15))/2,((rowX(12)+rowX(13))/2+rowX(12))/2)); //ghost wall
		
		getWallList().add(new Line(columnX(0),rowX(0),columnX(13),rowX(0)));
		getWallList().add(new Line(columnX(14),rowX(0),columnX(27),rowX(0)));
		getWallList().add(new Line(columnX(0),rowX(0),columnX(0),rowX(9)));
		getWallList().add(new Line(columnX(0),rowX(9),columnX(5),rowX(9)));
		getWallList().add(new Line(columnX(5),rowX(9),columnX(5),rowX(13)));
		getWallList().add(new Line(columnX(5),rowX(13),columnX(-2),rowX(13)));
		getWallList().add(new Line(columnX(-3),rowX(13),columnX(-3),rowX(15)));
		getWallList().add(new Line(columnX(5),rowX(15),columnX(-2),rowX(15)));
		getWallList().add(new Line(columnX(5),rowX(15),columnX(5),rowX(19)));
		getWallList().add(new Line(columnX(5),rowX(19),columnX(0),rowX(19)));
		getWallList().add(new Line(columnX(0),rowX(19),columnX(0),rowX(24)));
		getWallList().add(new Line(columnX(0),rowX(24),columnX(2),rowX(24)));
		getWallList().add(new Line(columnX(2),rowX(24),columnX(2),rowX(25)));
		getWallList().add(new Line(columnX(2),rowX(25),columnX(0),rowX(25)));
		getWallList().add(new Line(columnX(0),rowX(25),columnX(0),rowX(30)));
		getWallList().add(new Line(columnX(0),rowX(30),columnX(27),rowX(30)));
		getWallList().add(new Line(columnX(27),rowX(30),columnX(27),rowX(25)));
		getWallList().add(new Line(columnX(27),rowX(25),columnX(25),rowX(25)));
		getWallList().add(new Line(columnX(25),rowX(25),columnX(25),rowX(24)));
		getWallList().add(new Line(columnX(25),rowX(24),columnX(27),rowX(24)));
		getWallList().add(new Line(columnX(27),rowX(24),columnX(27),rowX(19)));
		getWallList().add(new Line(columnX(27),rowX(19),columnX(22),rowX(19)));
		getWallList().add(new Line(columnX(22),rowX(19),columnX(22),rowX(15)));
		getWallList().add(new Line(columnX(22),rowX(15),columnX(29),rowX(15)));
		getWallList().add(new Line(columnX(30),rowX(15),columnX(30),rowX(13)));
		getWallList().add(new Line(columnX(22),rowX(13),columnX(29),rowX(13)));
		getWallList().add(new Line(columnX(22),rowX(13),columnX(22),rowX(9)));
		getWallList().add(new Line(columnX(22),rowX(9),columnX(27),rowX(9)));
		getWallList().add(new Line(columnX(27),rowX(9),columnX(27),rowX(0)));
	
		getWallList().add(new Line(columnX(2),rowX(2),columnX(5),rowX(2)));
		getWallList().add(new Line(columnX(2),rowX(4),columnX(5),rowX(4)));
		getWallList().add(new Line(columnX(2),rowX(2),columnX(2),rowX(4)));
		getWallList().add(new Line(columnX(5),rowX(2),columnX(5),rowX(4)));
		
		getWallList().add(new Line(columnX(2),rowX(6),columnX(5),rowX(6)));
		getWallList().add(new Line(columnX(2),rowX(7),columnX(5),rowX(7)));
		getWallList().add(new Line(columnX(2),rowX(6),columnX(2),rowX(7)));
		getWallList().add(new Line(columnX(5),rowX(6),columnX(5),rowX(7)));
		
		getWallList().add(new Line(columnX(7),rowX(2),columnX(11),rowX(2)));
		getWallList().add(new Line(columnX(7),rowX(4),columnX(11),rowX(4)));
		getWallList().add(new Line(columnX(7),rowX(2),columnX(7),rowX(4)));
		getWallList().add(new Line(columnX(11),rowX(2),columnX(11),rowX(4)));
		
		getWallList().add(new Line(columnX(13),rowX(0),columnX(13),rowX(4)));
		getWallList().add(new Line(columnX(14),rowX(0),columnX(14),rowX(4)));
		getWallList().add(new Line(columnX(13),rowX(4),columnX(14),rowX(4)));
	
		getWallList().add(new Line(columnX(16),rowX(2),columnX(20),rowX(2)));
		getWallList().add(new Line(columnX(16),rowX(4),columnX(20),rowX(4)));
		getWallList().add(new Line(columnX(16),rowX(2),columnX(16),rowX(4)));
		getWallList().add(new Line(columnX(20),rowX(2),columnX(20),rowX(4)));
		
		getWallList().add(new Line(columnX(22),rowX(2),columnX(25),rowX(2)));
		getWallList().add(new Line(columnX(22),rowX(4),columnX(25),rowX(4)));
		getWallList().add(new Line(columnX(22),rowX(2),columnX(22),rowX(4)));
		getWallList().add(new Line(columnX(25),rowX(2),columnX(25),rowX(4)));
		
		getWallList().add(new Line(columnX(22),rowX(6),columnX(25),rowX(6)));
		getWallList().add(new Line(columnX(22),rowX(7),columnX(25),rowX(7)));
		getWallList().add(new Line(columnX(22),rowX(6),columnX(22),rowX(7)));
		getWallList().add(new Line(columnX(25),rowX(6),columnX(25),rowX(7)));
		
		getWallList().add(new Line(columnX(10),rowX(6),columnX(17),rowX(6)));
		getWallList().add(new Line(columnX(10),rowX(6),columnX(10),rowX(7)));
		getWallList().add(new Line(columnX(17),rowX(6),columnX(17),rowX(7)));
		getWallList().add(new Line(columnX(10),rowX(7),columnX(13),rowX(7)));
		getWallList().add(new Line(columnX(17),rowX(7),columnX(14),rowX(7)));
		getWallList().add(new Line(columnX(14),rowX(7),columnX(14),rowX(10)));
		getWallList().add(new Line(columnX(13),rowX(7),columnX(13),rowX(10)));
		getWallList().add(new Line(columnX(13),rowX(10),columnX(14),rowX(10)));
		
		getWallList().add(new Line(columnX(10),rowX(18),columnX(17),rowX(18)));
		getWallList().add(new Line(columnX(10),rowX(19),columnX(10),rowX(18)));
		getWallList().add(new Line(columnX(17),rowX(18),columnX(17),rowX(19)));
		getWallList().add(new Line(columnX(10),rowX(19),columnX(13),rowX(19)));
		getWallList().add(new Line(columnX(17),rowX(19),columnX(14),rowX(19)));
		getWallList().add(new Line(columnX(14),rowX(19),columnX(14),rowX(22)));
		getWallList().add(new Line(columnX(13),rowX(19),columnX(13),rowX(22)));
		getWallList().add(new Line(columnX(13),rowX(22),columnX(14),rowX(22)));
		
		getWallList().add(new Line(columnX(10),rowX(24),columnX(17),rowX(24)));
		getWallList().add(new Line(columnX(10),rowX(25),columnX(10),rowX(24)));
		getWallList().add(new Line(columnX(17),rowX(24),columnX(17),rowX(25)));
		getWallList().add(new Line(columnX(10),rowX(25),columnX(13),rowX(25)));
		getWallList().add(new Line(columnX(17),rowX(25),columnX(14),rowX(25)));
		getWallList().add(new Line(columnX(14),rowX(25),columnX(14),rowX(28)));
		getWallList().add(new Line(columnX(13),rowX(25),columnX(13),rowX(28)));
		getWallList().add(new Line(columnX(13),rowX(28),columnX(14),rowX(28)));
		
		getWallList().add(new Line(columnX(7),rowX(6),columnX(7),rowX(13)));
		getWallList().add(new Line(columnX(7),rowX(6),columnX(8),rowX(6)));
		getWallList().add(new Line(columnX(8),rowX(6),columnX(8),rowX(9)));
		getWallList().add(new Line(columnX(8),rowX(9),columnX(11),rowX(9)));
		getWallList().add(new Line(columnX(11),rowX(9),columnX(11),rowX(10)));
		getWallList().add(new Line(columnX(11),rowX(10),columnX(8),rowX(10)));
		getWallList().add(new Line(columnX(8),rowX(10),columnX(8),rowX(13)));
		getWallList().add(new Line(columnX(8),rowX(13),columnX(7),rowX(13)));
		
		getWallList().add(new Line(columnX(7),rowX(15),columnX(8),rowX(15)));
		getWallList().add(new Line(columnX(7),rowX(19),columnX(8),rowX(19)));
		getWallList().add(new Line(columnX(7),rowX(15),columnX(7),rowX(19)));
		getWallList().add(new Line(columnX(8),rowX(15),columnX(8),rowX(19)));
		
		getWallList().add(new Line(columnX(19),rowX(15),columnX(20),rowX(15)));
		getWallList().add(new Line(columnX(19),rowX(19),columnX(20),rowX(19)));
		getWallList().add(new Line(columnX(19),rowX(15),columnX(19),rowX(19)));
		getWallList().add(new Line(columnX(20),rowX(15),columnX(20),rowX(19)));
		
		getWallList().add(new Line(columnX(19),rowX(6),columnX(19),rowX(9)));
		getWallList().add(new Line(columnX(19),rowX(9),columnX(16),rowX(9)));
		getWallList().add(new Line(columnX(16),rowX(9),columnX(16),rowX(10)));
		getWallList().add(new Line(columnX(16),rowX(10),columnX(19),rowX(10)));
		getWallList().add(new Line(columnX(19),rowX(10),columnX(19),rowX(13)));
		getWallList().add(new Line(columnX(19),rowX(13),columnX(20),rowX(13)));
		getWallList().add(new Line(columnX(20),rowX(13),columnX(20),rowX(6)));
		getWallList().add(new Line(columnX(20),rowX(6),columnX(19),rowX(6)));
		
		getWallList().add(new Line(columnX(7),rowX(21),columnX(11),rowX(21)));
		getWallList().add(new Line(columnX(7),rowX(22),columnX(11),rowX(22)));
		getWallList().add(new Line(columnX(7),rowX(21),columnX(7),rowX(22)));
		getWallList().add(new Line(columnX(11),rowX(21),columnX(11),rowX(22)));
		
		getWallList().add(new Line(columnX(16),rowX(21),columnX(20),rowX(21)));
		getWallList().add(new Line(columnX(16),rowX(22),columnX(20),rowX(22)));
		getWallList().add(new Line(columnX(16),rowX(21),columnX(16),rowX(22)));
		getWallList().add(new Line(columnX(20),rowX(21),columnX(20),rowX(22)));
		
		getWallList().add(new Line(columnX(2),rowX(21),columnX(2),rowX(22)));
		getWallList().add(new Line(columnX(2),rowX(22),columnX(4),rowX(22)));
		getWallList().add(new Line(columnX(4),rowX(22),columnX(4),rowX(25)));
		getWallList().add(new Line(columnX(4),rowX(25),columnX(5),rowX(25)));
		getWallList().add(new Line(columnX(5),rowX(25),columnX(5),rowX(21)));
		getWallList().add(new Line(columnX(5),rowX(21),columnX(2),rowX(21)));
		
		getWallList().add(new Line(columnX(22),rowX(21),columnX(22),rowX(25)));
		getWallList().add(new Line(columnX(22),rowX(25),columnX(23),rowX(25)));
		getWallList().add(new Line(columnX(23),rowX(25),columnX(23),rowX(22)));
		getWallList().add(new Line(columnX(23),rowX(22),columnX(25),rowX(22)));
		getWallList().add(new Line(columnX(25),rowX(22),columnX(25),rowX(21)));
		getWallList().add(new Line(columnX(25),rowX(21),columnX(22),rowX(21)));
		
		getWallList().add(new Line(columnX(10),rowX(12),columnX(10),rowX(16)));
		getWallList().add(new Line(columnX(17),rowX(12),columnX(17),rowX(16)));
		getWallList().add(new Line(columnX(10),rowX(12),(columnX(12)+columnX(13))/2,rowX(12)));
		getWallList().add(new Line((columnX(15)+columnX(14))/2,rowX(12),columnX(17),rowX(12)));
		getWallList().add(new Line(columnX(17),rowX(16),columnX(10),rowX(16)));
		
		getWallList().add(new Line((columnX(10)+columnX(11))/2,(rowX(13)+rowX(12))/2,(columnX(10)+columnX(11))/2,(rowX(15)+rowX(16))/2));
		getWallList().add(new Line((columnX(16)+columnX(17))/2,(rowX(13)+rowX(12))/2,(columnX(16)+columnX(17))/2,(rowX(15)+rowX(16))/2));
		getWallList().add(new Line((columnX(10)+columnX(11))/2,(rowX(13)+rowX(12))/2,(columnX(12)+columnX(13))/2,(rowX(13)+rowX(12))/2));
		getWallList().add(new Line((columnX(14)+columnX(15))/2,(rowX(13)+rowX(12))/2,(columnX(17)+columnX(16))/2,(rowX(13)+rowX(12))/2));
		getWallList().add(new Line((columnX(16)+columnX(17))/2,(rowX(15)+rowX(16))/2,(columnX(10)+columnX(11))/2,(rowX(15)+rowX(16))/2));
		
		getWallList().add(new Line((columnX(12)+columnX(13))/2,(rowX(13)+rowX(12))/2,(columnX(12)+columnX(13))/2,rowX(12)));
		getWallList().add(new Line((columnX(14)+columnX(15))/2,(rowX(13)+rowX(12))/2,(columnX(14)+columnX(15))/2,rowX(12)));
		
		getWallList().add(new Line(columnX(2),rowX(28),columnX(11),rowX(28)));
		getWallList().add(new Line(columnX(11),rowX(28),columnX(11),rowX(27)));
		getWallList().add(new Line(columnX(11),rowX(27),columnX(8),rowX(27)));
		getWallList().add(new Line(columnX(8),rowX(27),columnX(8),rowX(24)));
		getWallList().add(new Line(columnX(8),rowX(24),columnX(7),rowX(24)));
		getWallList().add(new Line(columnX(7),rowX(24),columnX(7),rowX(27)));
		getWallList().add(new Line(columnX(7),rowX(27),columnX(2),rowX(27)));
		getWallList().add(new Line(columnX(2),rowX(27),columnX(2),rowX(28)));
		
		getWallList().add(new Line(columnX(16),rowX(28),columnX(25),rowX(28)));
		getWallList().add(new Line(columnX(25),rowX(28),columnX(25),rowX(27)));
		getWallList().add(new Line(columnX(25),rowX(27),columnX(20),rowX(27)));
		getWallList().add(new Line(columnX(20),rowX(27),columnX(20),rowX(24)));
		getWallList().add(new Line(columnX(20),rowX(24),columnX(19),rowX(24)));
		getWallList().add(new Line(columnX(19),rowX(24),columnX(19),rowX(27)));
		getWallList().add(new Line(columnX(19),rowX(27),columnX(16),rowX(27)));
		getWallList().add(new Line(columnX(16),rowX(27),columnX(16),rowX(28)));
	}

	@Override
	public void addCoinsToList() {
			for(int i=1;i<27;i++) 
				if(i!=13 && i!=14)
					getEatableList().add(new Coin(columnX(i),rowX(1)));
			for(int i=1;i<27;i++) 
				if(i==1 || i==6 || i==12 || i==15 || i==21 || i==26)
					getEatableList().add(new Coin(columnX(i),rowX(2)));
			for(int i=1;i<27;i++) 
				if(i==6 || i==12 || i==15 || i==21)
					getEatableList().add(new Coin(columnX(i),rowX(3)));
			for(int i=1;i<27;i++) 
				if(i==1 || i==6 || i==12 || i==15 || i==21 || i==26)
					getEatableList().add(new Coin(columnX(i),rowX(4)));
			for(int i=1;i<27;i++)
				getEatableList().add(new Coin(columnX(i),rowX(5)));	
			for(int i=1;i<27;i++)
				if(i==1 || i==6 || i==9 || i==18 || i==21 || i==26)
					getEatableList().add(new Coin(columnX(i),rowX(6)));
			for(int i=1;i<27;i++)
				if(i==1 || i==6 || i==9 || i==18 || i==21 || i==26)
					getEatableList().add(new Coin(columnX(i),rowX(7)));
			for(int i=1;i<27;i++)
				if(i!=0 && i!=7 && i!=8 && i!=13 && i!=14 && i!=19 && i!=20)
					getEatableList().add(new Coin(columnX(i),rowX(8)));	
			for(int i=1;i<27;i++)
				if(i==6 || i==21)
				getEatableList().add(new Coin(columnX(i),rowX(9)));
			for(int i=1;i<27;i++)
				if(i==6 || i==21)
					getEatableList().add(new Coin(columnX(i),rowX(10)));
			for(int i=1;i<27;i++)
				if(i==6 || i==21)
					getEatableList().add(new Coin(columnX(i),rowX(11)));
			for(int i=1;i<27;i++)
				if(i==6 || i==21)
					getEatableList().add(new Coin(columnX(i),rowX(12)));
			for(int i=1;i<27;i++)
				if(i==6 || i==21)
					getEatableList().add(new Coin(columnX(i),rowX(13)));
			for(int i=1;i<27;i++)
				if(i==6 || i==21)
					getEatableList().add(new Coin(columnX(i),rowX(14)));
			for(int i=1;i<27;i++)
				if(i==6 || i==21)
					getEatableList().add(new Coin(columnX(i),rowX(15)));
			for(int i=1;i<27;i++)
				if(i==6 || i==21)
					getEatableList().add(new Coin(columnX(i),rowX(16)));
			for(int i=1;i<27;i++)
				if(i==6 || i==21)
					getEatableList().add(new Coin(columnX(i),rowX(17)));
			for(int i=1;i<27;i++)
				if(i==6 || i==21)
					getEatableList().add(new Coin(columnX(i),rowX(18)));
			for(int i=1;i<27;i++)
				if(i==6 || i==21)
					getEatableList().add(new Coin(columnX(i),rowX(19)));
			for(int i=1;i<27;i++)
				if(i!=13 && i!=14)
					getEatableList().add(new Coin(columnX(i),rowX(20)));
			for(int i=1;i<27;i++)
				if(i==1 || i==6 || i==12 || i==15 || i==21 || i==26)
					getEatableList().add(new Coin(columnX(i),rowX(21)));
			for(int i=1;i<27;i++)
				if(i==1 || i==6 || i==12 || i==15 || i==21 || i==26)
					getEatableList().add(new Coin(columnX(i),rowX(22)));
			for(int i=1;i<27;i++)
				if(i!=1 && i!=4 && i!=5 && i!=13 && i!=14 && i!=22 && i!=23 && i!=26)
					getEatableList().add(new Coin(columnX(i),rowX(23)));
			for(int i=1;i<27;i++)
				if(i==3 || i==6 || i==9 || i==18 || i==21 || i==24)
					getEatableList().add(new Coin(columnX(i),rowX(24)));
			for(int i=1;i<27;i++)
				if(i==3 || i==6 || i==9 || i==18 || i==21 || i==24)
					getEatableList().add(new Coin(columnX(i),rowX(25)));
			for(int i=1;i<27;i++)
				if(i!=7 && i!=8 && i!=13 && i!=14 && i!=19 && i!=20)
					getEatableList().add(new Coin(columnX(i),rowX(26)));
			for(int i=1;i<27;i++)
				if(i==1 || i==12 || i==15 || i==26)
					getEatableList().add(new Coin(columnX(i),rowX(27)));
			for(int i=1;i<27;i++)
				if(i==1 || i==12 || i==15 || i==26)
					getEatableList().add(new Coin(columnX(i),rowX(28)));
			for(int i=1;i<27;i++)
					getEatableList().add(new Coin(columnX(i),rowX(29)));
	}

	@Override
	public void addPowerBallsToList() {
		getEatableList().add(new PowerBall(columnX(1),rowX(3)));
		getEatableList().add(new PowerBall(columnX(26),rowX(3)));
		getEatableList().add(new PowerBall(columnX(1),rowX(23)));
		getEatableList().add(new PowerBall(columnX(26),rowX(23)));
	}
	
	public void addFruitToList() {
		getEatableList().add(new Fruit((columnX(13)+columnX(14))/2,rowX(17)));
	}
	
	public void addTeleportBlackBox(Pane root) {
		Rectangle m=new Rectangle(columnX(28),rowX(13)-20,100,100); //right blackbox for smoother teleportation
		Rectangle q=new Rectangle(columnX(-5),rowX(13)-20,90,90); // left blackbox for smoother teleportation
		if(Level.getHieght()==720) {
			m=new Rectangle(columnX(27),rowX(12),100,100);
			q=new Rectangle(columnX(-4),rowX(12),50,80);
		}
		root.getChildren().add(m);
		root.getChildren().add(q);
	}
	
	public void teleport(Character c) {
		if(!(Level.getHieght()==720)) {
			for(int s=-5;s<=5;s++) {
				if (c.getX()==columnX(-1)-20 && c.getY()==rowX(14)+s && c.getDirectionX()==-1) 
					c.setX(columnX(28));
				if (c.getX()==columnX(28)+20 && c.getY()==rowX(14)+s && c.getDirectionX()==1) 
					c.setX(columnX(-1));
			}
		}else {
			for(int s=-5;s<=5;s++) {
				if (c.getX()==columnX(-1) && c.getY()==rowX(14)+s && c.getDirectionX()==-1) 
					c.setX(columnX(28));
				if (c.getX()==columnX(28) && c.getY()==rowX(14)+s && c.getDirectionX()==1) 
					c.setX(columnX(-1));
			}
		}
	}
	
}
