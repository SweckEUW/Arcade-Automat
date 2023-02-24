package Pacman;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class LevelHSHL extends Level{

	private static AnimationTimer animator; //changes the color of the HSHL letters and the logo
	private double m,s; //needed for the animation
	
	LevelHSHL(int width, int height) {
		super(width, height);
	}

	@Override
	public void addPowerBallsToList() {
		getEatableList().add(new PowerBall(columnX(31-2),rowX(18)));
		getEatableList().add(new PowerBall(columnX(12-2),rowX(9)));
		getEatableList().add(new PowerBall(columnX(16-2),rowX(27)));
		getEatableList().add(new PowerBall(columnX(1-2),rowX(1)));
	}

	@Override
	public void addCoinsToList() {
		for(int i=-1;i<30;i++) 
			if(i!=1-2)
				getEatableList().add(new Coin(columnX(i),rowX(1)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==5-2 ||i==9-2 || i==16-2 || i==20-2 || i==24-2 || i==28-2)
				getEatableList().add(new Coin(columnX(i),rowX(2)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==5-2 ||i==9-2 || i==16-2 || i==20-2 || i==24-2 || i==28-2)
				getEatableList().add(new Coin(columnX(i),rowX(3)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==5-2 ||i==9-2 || i==16-2 || i==20-2 || i==24-2 || i==28-2)
				getEatableList().add(new Coin(columnX(i),rowX(4)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==5-2 ||i==9-2 || i==13-2 || i==14-2 || i==15 -2 ||i==16-2 || i==20-2 || i==24-2 || i==28-2)
				getEatableList().add(new Coin(columnX(i),rowX(5)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 ||i==9-2 || i==16-2 || i==24-2 || i==28-2)
				getEatableList().add(new Coin(columnX(i),rowX(6)));
		for(int i=-1;i<33;i++)
			if(i==1-2 ||i==9-2 || i==16-2 || i==24-2 || i==28-2)
				getEatableList().add(new Coin(columnX(i),rowX(7)));
		for(int i=-1;i<33;i++)
			if(i==1-2 ||i==9-2 || i==16-2 || i==24-2 || i==28-2)
				getEatableList().add(new Coin(columnX(i),rowX(8)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==5-2 ||i==9-2 || i==10-2 || i==11-2 || i==16-2 || i==20-2 || i==24-2 || i==28-2 || i==29-2 || i==30-2 || i==31-2 || i==32-2)
				getEatableList().add(new Coin(columnX(i),rowX(9)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==5-2 || i==9-2 || i==16-2 || i==20-2 || i==24-2 || i==32-2)
				getEatableList().add(new Coin(columnX(i),rowX(10)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==5-2 || i==9-2 || i==16-2 || i==20-2 || i==24-2 || i==32-2)
				getEatableList().add(new Coin(columnX(i),rowX(11)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==5-2 || i==9-2 || i==16-2 || i==20-2 || i==24-2 || i==32-2)
				getEatableList().add(new Coin(columnX(i),rowX(12)));
		for(int i=-1;i<31;i++) 
			getEatableList().add(new Coin(columnX(i),rowX(13)));
		for(int i=-1;i<33;i++) 
			if(i==7-2  || i==25-2 || i==29-2)
				getEatableList().add(new Coin(columnX(i),rowX(14)));
		for(int i=-1;i<33;i++) 
			if(i==7-2  || i==25-2 || i==29-2)
				getEatableList().add(new Coin(columnX(i),rowX(15)));
		for(int i=-1;i<33;i++) 
			if(i==7-2||  i==25-2 || i==29-2)
				getEatableList().add(new Coin(columnX(i),rowX(16)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==2-2 || i==3-2 || i==4-2 || i==5-2 || i==6-2|| i==7-2 || i==25-2 || i==29-2)
				getEatableList().add(new Coin(columnX(i),rowX(17)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==7-2 || i==25-2 || i==26-2 || i==27-2 || i==28-2 || i==29-2 || i==30-2)
				getEatableList().add(new Coin(columnX(i),rowX(18)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==7-2 || i==25-2 || i==31-2)
				getEatableList().add(new Coin(columnX(i),rowX(19)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==7-2  || i==25-2 || i==31-2 )
				getEatableList().add(new Coin(columnX(i),rowX(20)));
		for(int i=-1;i<33;i++) 
			if(i==1-2 || i==2-2 || i==3-2 || i==7-2  || i==25-2 || i==31-2)
				getEatableList().add(new Coin(columnX(i),rowX(21)));
		for(int i=-1;i<33;i++) 
			if(i==3-2 || i==7-2  || i==25-2 || i==31-2)
				getEatableList().add(new Coin(columnX(i),rowX(22)));
		for(int i=-1;i<31;i++) 
			if(i!=1-2 && i!=2-2 && i!=16-2 && i!=26-2 && i!=27-2 && i!=28-2 && i!=29-2 && i!=30-2 && i!=32-2)
				getEatableList().add(new Coin(columnX(i),rowX(23)));
		for(int i=-1;i<33;i++) 
			if(i==3-2 || i==7-2|| i==13-2 || i==19-2 || i==25-2 || i==31-2)
				getEatableList().add(new Coin(columnX(i),rowX(24)));
		for(int i=-1;i<33;i++) 
			if(i==3-2 || i==7-2 || i==13-2 || i==19-2 || i==25-2 || i==31-2)
				getEatableList().add(new Coin(columnX(i),rowX(25)));
		for(int i=-1;i<33;i++) 
			if(i==3-2 || i==7-2 || i==13-2 || i==19-2 || i==25-2 || i==31-2)
				getEatableList().add(new Coin(columnX(i),rowX(26)));
		for(int i=-1;i<31;i++) 
			if(i!=8-2 && i!=9-2 && i!=10-2 && i!=11-2 && i!=16-2 && i!=12-2 && i!=20-2 && i!=21-2 && i!=22-2 && i!=23-2 && i!=24-2 && i!=32-2)
				getEatableList().add(new Coin(columnX(i),rowX(27)));
	}

	@Override
	public void addWallsToList() {
		getWallList().add(new Line(((columnX(26-2)+columnX(27-2))/2+columnX(26-2))/2,(rowX(24)+rowX(23))/2,((columnX(26-2)+columnX(27-2))/2+columnX(26-2))/2,(rowX(22)+rowX(21))/2)); 
		
		//H
		getWallList().add(new Line(columnX(2-2),rowX(2),columnX(2-2),rowX(12)));
		getWallList().add(new Line(columnX(2-2),rowX(2),columnX(4-2),rowX(2)));
		getWallList().add(new Line(columnX(4-2),rowX(2),columnX(4-2),rowX(6)));
		getWallList().add(new Line(columnX(4-2),rowX(6),columnX(6-2),rowX(6)));
		getWallList().add(new Line(columnX(6-2),rowX(6),columnX(6-2),rowX(2)));
		getWallList().add(new Line(columnX(6-2),rowX(2),columnX(8-2),rowX(2)));
		getWallList().add(new Line(columnX(8-2),rowX(2),columnX(8-2),rowX(12)));
		getWallList().add(new Line(columnX(8-2),rowX(12),columnX(6-2),rowX(12)));
		getWallList().add(new Line(columnX(6-2),rowX(12),columnX(6-2),rowX(8)));
		getWallList().add(new Line(columnX(6-2),rowX(8),columnX(4-2),rowX(8)));
		getWallList().add(new Line(columnX(4-2),rowX(8),columnX(4-2),rowX(12)));
		getWallList().add(new Line(columnX(4-2),rowX(12),columnX(2-2),rowX(12)));
		//S
		getWallList().add(new Line(columnX(10-2),rowX(2),columnX(15-2),rowX(2)));
		getWallList().add(new Line(columnX(15-2),rowX(2),columnX(15-2),rowX(4)));
		getWallList().add(new Line(columnX(15-2),rowX(4),columnX(12-2),rowX(4)));
		getWallList().add(new Line(columnX(12-2),rowX(4),columnX(12-2),rowX(6)));
		getWallList().add(new Line(columnX(12-2),rowX(6),columnX(15-2),rowX(6)));
		getWallList().add(new Line(columnX(15-2),rowX(6),columnX(15-2),rowX(12)));
		getWallList().add(new Line(columnX(15-2),rowX(12),columnX(10-2),rowX(12)));
		getWallList().add(new Line(columnX(10-2),rowX(12),columnX(10-2),rowX(10)));
		getWallList().add(new Line(columnX(10-2),rowX(10),columnX(13-2),rowX(10)));
		getWallList().add(new Line(columnX(13-2),rowX(10),columnX(13-2),rowX(8)));
		getWallList().add(new Line(columnX(13-2),rowX(8),columnX(10-2),rowX(8)));
		getWallList().add(new Line(columnX(10-2),rowX(8),columnX(10-2),rowX(2)));
		//H
		getWallList().add(new Line(columnX(17-2),rowX(2),columnX(19-2),rowX(2)));
		getWallList().add(new Line(columnX(19-2),rowX(2),columnX(19-2),rowX(6)));
		getWallList().add(new Line(columnX(19-2),rowX(6),columnX(21-2),rowX(6)));
		getWallList().add(new Line(columnX(21-2),rowX(6),columnX(21-2),rowX(2)));
		getWallList().add(new Line(columnX(21-2),rowX(2),columnX(23-2),rowX(2)));
		getWallList().add(new Line(columnX(23-2),rowX(2),columnX(23-2),rowX(12)));
		getWallList().add(new Line(columnX(23-2),rowX(12),columnX(21-2),rowX(12)));
		getWallList().add(new Line(columnX(21-2),rowX(12),columnX(21-2),rowX(8)));
		getWallList().add(new Line(columnX(21-2),rowX(8),columnX(19-2),rowX(8)));
		getWallList().add(new Line(columnX(19-2),rowX(8),columnX(19-2),rowX(12)));
		getWallList().add(new Line(columnX(19-2),rowX(12),columnX(17-2),rowX(12)));
		getWallList().add(new Line(columnX(17-2),rowX(12),columnX(17-2),rowX(2)));
		//L
		getWallList().add(new Line(columnX(25-2),rowX(2),columnX(25-2),rowX(12)));
		getWallList().add(new Line(columnX(25-2),rowX(12),columnX(31-2),rowX(12)));
		getWallList().add(new Line(columnX(31-2),rowX(12),columnX(31-2),rowX(10)));
		getWallList().add(new Line(columnX(31-2),rowX(10),columnX(27-2),rowX(10)));
		getWallList().add(new Line(columnX(27-2),rowX(10),columnX(27-2),rowX(2)));
		getWallList().add(new Line(columnX(27-2),rowX(2),columnX(25-2),rowX(2)));

		//C1
		getWallList().add(new Line(columnX(8-2),rowX(14),columnX(15-2),rowX(14)));
		getWallList().add(new Line(columnX(15-2),rowX(14),columnX(15-2),rowX(17)));
		getWallList().add(new Line(columnX(15-2),rowX(17),columnX(12-2),rowX(17)));
		getWallList().add(new Line(columnX(12-2),rowX(17),columnX(12-2),rowX(19)));
		getWallList().add(new Line(columnX(12-2),rowX(19),columnX(15-2),rowX(19)));
		getWallList().add(new Line(columnX(15-2),rowX(19),columnX(15-2),rowX(22)));
		getWallList().add(new Line(columnX(15-2),rowX(22),columnX(8-2),rowX(22)));
		getWallList().add(new Line(columnX(8-2),rowX(22),columnX(8-2),rowX(14)));
		//C2
		getWallList().add(new Line(columnX(17-2),rowX(14),columnX(24-2),rowX(14)));
		getWallList().add(new Line(columnX(24-2),rowX(14),columnX(24-2),rowX(22)));
		getWallList().add(new Line(columnX(24-2),rowX(22),columnX(17-2),rowX(22)));
		getWallList().add(new Line(columnX(17-2),rowX(22),columnX(17-2),rowX(19)));
		getWallList().add(new Line(columnX(17-2),rowX(19),columnX(20-2),rowX(19)));
		getWallList().add(new Line(columnX(20-2),rowX(19),columnX(20-2),rowX(17)));
		getWallList().add(new Line(columnX(20-2),rowX(17),columnX(17-2),rowX(17)));
		getWallList().add(new Line(columnX(17-2),rowX(17),columnX(17-2),rowX(14)));
			
		
		//Boundaries 
		getWallList().add(new Line(columnX(-2),rowX(0),columnX(-2),rowX(14)));
		getWallList().add(new Line(columnX(-2),rowX(14),columnX(6-2),rowX(14)));
		getWallList().add(new Line(columnX(6-2),rowX(14),columnX(6-2),rowX(16)));
		getWallList().add(new Line(columnX(6-2),rowX(16),columnX(0-2),rowX(16)));
		getWallList().add(new Line(columnX(0-2),rowX(16),columnX(0-2),rowX(22)));
		getWallList().add(new Line(columnX(0-2),rowX(22),columnX(2-2),rowX(22)));
		getWallList().add(new Line(columnX(2-2),rowX(22),columnX(2-2),rowX(26)));
		getWallList().add(new Line(columnX(2-2),rowX(26),columnX(-2-2),rowX(26)));
		getWallList().add(new Line(columnX(0-2),rowX(0),columnX(37-2),rowX(0)));
		
		getWallList().add(new Line(columnX(33-2),rowX(8),columnX(33-2),rowX(14)));
		getWallList().add(new Line(columnX(33-2),rowX(14),columnX(30-2),rowX(14)));
		getWallList().add(new Line(columnX(30-2),rowX(14),columnX(30-2),rowX(17)));
		getWallList().add(new Line(columnX(30-2),rowX(17),columnX(32-2),rowX(17)));
		getWallList().add(new Line(columnX(32-2),rowX(17),columnX(32-2),rowX(28)));
		
		getWallList().add(new Line(columnX(-2-2),rowX(28),columnX(8-2),rowX(28)));
		getWallList().add(new Line(columnX(8-2),rowX(28),columnX(8-2),rowX(24)));
		getWallList().add(new Line(columnX(8-2),rowX(24),columnX(12-2),rowX(24)));
		getWallList().add(new Line(columnX(12-2),rowX(24),columnX(12-2),rowX(28)));
		getWallList().add(new Line(columnX(12-2),rowX(28),columnX(20-2),rowX(28)));
		getWallList().add(new Line(columnX(20-2),rowX(28),columnX(20-2),rowX(24)));
		getWallList().add(new Line(columnX(20-2),rowX(24),columnX(24-2),rowX(24)));
		getWallList().add(new Line(columnX(24-2),rowX(24),columnX(24-2),rowX(28)));
		getWallList().add(new Line(columnX(24-2),rowX(28),columnX(32-2),rowX(28)));
		
		getWallList().add(new Line(columnX(29-2),rowX(2),columnX(37-2),rowX(2)));
		getWallList().add(new Line(columnX(33-2),rowX(8),columnX(29-2),rowX(8)));
		getWallList().add(new Line(columnX(29-2),rowX(8),columnX(29-2),rowX(2)));

		getWallList().add(new Line(columnX(2-2),rowX(18),columnX(6-2),rowX(18)));
		getWallList().add(new Line(columnX(6-2),rowX(18),columnX(6-2),rowX(22)));
		getWallList().add(new Line(columnX(6-2),rowX(22),columnX(4-2),rowX(22)));
		getWallList().add(new Line(columnX(4-2),rowX(22),columnX(4-2),rowX(20)));
		getWallList().add(new Line(columnX(4-2),rowX(20),columnX(2-2),rowX(20)));
		getWallList().add(new Line(columnX(2-2),rowX(20),columnX(2-2),rowX(18)));
		
		getWallList().add(new Line(columnX(4-2),rowX(24),columnX(6-2),rowX(24)));
		getWallList().add(new Line(columnX(6-2),rowX(24),columnX(6-2),rowX(26)));
		getWallList().add(new Line(columnX(6-2),rowX(26),columnX(4-2),rowX(26)));
		getWallList().add(new Line(columnX(4-2),rowX(26),columnX(4-2),rowX(24)));
		
		getWallList().add(new Line(columnX(14-2),rowX(24),columnX(18-2),rowX(24)));
		getWallList().add(new Line(columnX(18-2),rowX(24),columnX(18-2),rowX(26)));
		getWallList().add(new Line(columnX(18-2),rowX(26),columnX(14-2),rowX(26)));
		getWallList().add(new Line(columnX(14-2),rowX(26),columnX(14-2),rowX(24)));
		
		getWallList().add(new Line(columnX(26-2),rowX(14),columnX(28-2),rowX(14)));
		getWallList().add(new Line(columnX(28-2),rowX(14),columnX(28-2),rowX(17)));
		getWallList().add(new Line(columnX(28-2),rowX(17),columnX(26-2),rowX(17)));
		getWallList().add(new Line(columnX(26-2),rowX(17),columnX(26-2),rowX(14)));
		
		getWallList().add(new Line(columnX(26-2),rowX(19),columnX(30-2),rowX(19)));
		getWallList().add(new Line(columnX(30-2),rowX(19),columnX(30-2),rowX(26)));
		getWallList().add(new Line(columnX(30-2),rowX(26),columnX(26-2),rowX(26)));
		getWallList().add(new Line(columnX(26-2),rowX(26),columnX(26-2),(rowX(24)+rowX(23))/2)); 
		getWallList().add(new Line(columnX(26-2),(rowX(22)+rowX(21))/2,columnX(26-2),rowX(19))); 
		
		getWallList().add(new Line((columnX(26-2)+columnX(26-1))/2,(rowX(19)+rowX(20))/2,(columnX(30-2)+columnX(30-3))/2,(rowX(19)+rowX(20))/2));
		getWallList().add(new Line((columnX(30-2)+columnX(30-3))/2,(rowX(19)+rowX(20))/2,(columnX(30-2)+columnX(30-3))/2,(rowX(26)+rowX(25))/2));
		getWallList().add(new Line((columnX(26-2)+columnX(26-1))/2,(rowX(26)+rowX(25))/2,(columnX(30-2)+columnX(30-3))/2,(rowX(26)+rowX(25))/2));
		getWallList().add(new Line((columnX(26-2)+columnX(26-1))/2,(rowX(26)+rowX(25))/2,(columnX(26-2)+columnX(26-1))/2,(rowX(24)+rowX(23))/2)); 
		getWallList().add(new Line((columnX(26-2)+columnX(26-1))/2,(rowX(22)+rowX(21))/2,(columnX(26-2)+columnX(26-1))/2,(rowX(20)+rowX(19))/2)); 
		getWallList().add(new Line((columnX(26-2)+columnX(26-1))/2,(rowX(24)+rowX(23))/2,columnX(26-2),(rowX(24)+rowX(23))/2)); 
		getWallList().add(new Line((columnX(26-2)+columnX(26-1))/2,(rowX(22)+rowX(21))/2,columnX(26-2),(rowX(22)+rowX(21))/2)); 
		
		getWallList().add(new Line(columnX(-5),rowX(26),columnX(-5),rowX(28))); 
		getWallList().add(new Line(columnX(36),rowX(0),columnX(36),rowX(2))); 
		
		animator = new AnimationTimer(){
             @Override
             public void handle(long arg0) {
            	m+=0.01;
            	s+=0.01;
            	//HSHL-letters
         		if(m>1.5) {
         			for(int i=1;i<43;i++)
         				getWallList().get(i).setStroke(Color.RED);       			
         		}
         		if(m>2.5) {
         			for(int i=1;i<43;i++)
         				getWallList().get(i).setStroke(Color.MIDNIGHTBLUE);  
         		}
         		if(m>3.5) {
         			for(int i=1;i<43;i++)
         				getWallList().get(i).setStroke(Color.LIGHTSKYBLUE);  
         		}
         		if(m>4.5) {
         			for(int i=1;i<43;i++)
         				getWallList().get(i).setStroke(Color.ORANGE);  
         		}
         		if(m>5.5) {
         			for(int i=1;i<43;i++)
         				getWallList().get(i).setStroke(Color.rgb(0, 60, 200)); 
         			m=0;
         		}
         		//HSHL-logo
         		if(s>1.5) {
         			for(int i=43;i<51;i++)
         				getWallList().get(i).setStroke(Color.RED); 
         			for(int i=51;i<59;i++)
         				getWallList().get(i).setStroke(Color.ORANGE);    
         		}
         		if(s>2.5) {
         			for(int i=43;i<51;i++)
         				getWallList().get(i).setStroke(Color.YELLOWGREEN); 
         			for(int i=51;i<59;i++)
         				getWallList().get(i).setStroke(Color.MIDNIGHTBLUE);
         		}
         		if(s>3.5) {
         			for(int i=43;i<51;i++)
         				getWallList().get(i).setStroke(Color.LIGHTSKYBLUE); 
         			for(int i=51;i<59;i++)
         				getWallList().get(i).setStroke(Color.FORESTGREEN);
         		}
         		if(s>4.5) {
         			for(int i=43;i<51;i++)
         				getWallList().get(i).setStroke(Color.DARKBLUE); 
         			for(int i=51;i<59;i++)
         				getWallList().get(i).setStroke(Color.ORANGE); 
         		}
         		if(s>5.5) {
         			for(int i=43;i<51;i++)
         				getWallList().get(i).setStroke(Color.rgb(0, 60, 200));
         			for(int i=51;i<59;i++)
         				getWallList().get(i).setStroke(Color.rgb(0, 60, 200));
         			s=0;
         		}
             } 
		 };
		 animator.start(); 
	}

	@Override
	public void addFruitToList() {
		getEatableList().add(new Fruit(columnX(16-2),rowX(18)));
	}

	@Override
	public void addTeleportBlackBox(Pane root) {
		Rectangle m=new Rectangle(columnX(34),rowX(-1),100,100); //right blackbox for smoother teleportation
		Rectangle q=new Rectangle(columnX(-5),rowX(25),70,80); // leftt blackbox for smoother teleportation
		if(Level.getHieght()==720) {
			m.setFill(Color.TRANSPARENT);
			q.setFill(Color.TRANSPARENT);
		}
		root.getChildren().add(m);
		root.getChildren().add(q);
	}

	@Override
	public void teleport(Character c) {
			for(int s=-5;s<=5;s++) {
				if (c.getX()==columnX(35) && c.getY()==rowX(1)+s && c.getDirectionX()==1) {
					c.setX(columnX(-4));
					c.setY(rowX(27));
				}
				if (c.getX()==columnX(-4) && c.getY()==rowX(27)+s && c.getDirectionX()==-1) {
					c.setX(columnX(35));
					c.setY(rowX(1));
				}
			}
		}
	
}
