package Pacman;

import java.util.Timer;
import java.util.TimerTask;

import Menue.Player;
import Menue.Rankings;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
The GameLoop class contains the main game logic. It controls the start and end of the game. The main loop updates every object of the game in real time.
 */
public class GameLoop {

	private AnimationTimer mainLoop; //main loop
	private Pane root;
	private Level level;
	private Pac pac;
	private Interface gui;
	@SuppressWarnings("unused")
	private Ghost red,orange,cyan,pink;
	private int speed=3; //speed of the movement of the characters
	private static int levelSelector=1; //1= HSHL level 0=standard level
	private static Boolean isGameActive=false; //is true when the mainloop is running
	
	
	@SuppressWarnings("static-access")
	public GameLoop(Pane root,int width,int height,int levelSelector) {
		this.root=root;
		root.getChildren().add(new Rectangle(0,0,1920,1080)); //black background because background sometimes changes white??
		this.levelSelector=levelSelector;
		//level standard
		if(levelSelector==0) { 
			level=new LevelStandard(width,height);
			//changed starting positions of the ghosts
			pac=new Pac(level,(level.columnX(13)+level.columnX(14))/2,level.rowX(23));
			red=new GhostRed(level,(level.columnX(13)+level.columnX(14))/2,level.rowX(11));
			orange=new GhostOrange(level,(level.columnX(15)+level.columnX(16))/2,level.rowX(14));
			pink=new GhostPink(level,(level.columnX(13)+level.columnX(14))/2,level.rowX(14));
			cyan=new GhostCyan(level,(level.columnX(11)+level.columnX(12))/2,level.rowX(14));
		//level HSHL
		}else {
			//changed starting positions of the ghosts
			level=new LevelHSHL(width,height);
			pac=new Pac(level,level.columnX(14),level.rowX(23));
			red=new GhostRed(level,level.columnX(23),(level.rowX(22)+level.rowX(23))/2);
			orange=new GhostOrange(level,level.columnX(26),(level.rowX(20)+level.rowX(21))/2);
			pink=new GhostPink(level,level.columnX(26),(level.rowX(22)+level.rowX(23))/2);
			cyan=new GhostCyan(level,level.columnX(26),(level.rowX(24)+level.rowX(25))/2);
		}
		gui=new Interface(level);
		if(Level.getHieght()==720)
			speed=2; //decrease speed if the window is smaller 
	}
	
	/**
	main loop of the game. it updates all objects which get involved into the game in real time
	 */
	public void loop() {
		mainLoop = new AnimationTimer(){
             @Override
             public void handle(long arg0) {
            	 for(int i=0;i<speed;i++) { //the amount of times the loop gets called controls the speed of the characters
            		 pac.update(level); //update pacman
            		 level.checkColissionEatables(pac); //update level
            		 Ghost.updateAllGhosts(pac,level); //update all ghosts
            		 gui.update(pac,Rankings.getHighScorePacman()); //update 
            		
            		 int s =pac.checkColissionGhosts(); //pacman collision with ghost
            		 if(s==1) {	  
            			 pacDeath(); //pacman death
            			 break;
            			 }       
            		 if(s==2) {
            			 ghostDeath(); //ghost death
            			 break;
        			 }  
            		 if(level.checkIfLevelIsEmpty()) { //if level is empty move to next level
	             		nextLevel();
	             		break;
        			 }  
            	 }
             } 
		};
	}
	
	/**
	Resets the Level once all coins are gone
	 */
	public void nextLevel() {
		isGameActive=false;
		mainLoop.stop(); //stop loop
		Sound.stopSound();
		pac.setScoreLevel(0); //set the Level score to 0 
		level.getPowerBallAnimator().stop();
		for(Character c:Character.getAllCharacters()) //stop all animations
			c.getAnimationTimeline().stop();
		Timer timer1 = new Timer();   
		TimerTask task = new TimerTask(){
	        public void run(){ 
	        	//after 2.5 seconds
	        	Ghost.setVisibleAllGhosts(false);
	        	level.getWallList().get(0).setVisible(false);
	        	level.wallsColorChange(); //Blink animation for the walls
	        	Timer timer2 = new Timer();   
	    		TimerTask task = new TimerTask(){
	    	        public void run(){
	    	        	//after 2.5 seconds
	    	        	gui.getBlackScreen().setVisible(true); //show quick blackscreen
	    	        	Timer timer3 = new Timer();   
	    	    		TimerTask task = new TimerTask(){
	    	    	        public void run(){
	    	    	        	//after 0.2 seconds
	    	    	        	Character.resetAllCharacters(); //reset the position
	    	    	        	gui.getBlackScreen().setVisible(false); 
	    	    	        	Timer timer4 = new Timer();   
	    	    	    		TimerTask task = new TimerTask(){
	    	    	    	        public void run(){
	    	    	    	        	//reet level and show characters
	    	    	    	        	level.getWallList().get(0).setVisible(true);
	    	    	    	        	Character.setVisibleAllCharacters(true);
	    	    	    	        	level.resetColorPowerBalls();
	    	    	    	        	level.reset();
	    	    	    	        	freeze(2000); //start level after 2 seconds
	    	    	    	    	}    
	    	    	    		};
	    	    	    		timer4.schedule(task, 200);	
	    	    	    	}    
	    	    		};
	    	    		timer3.schedule(task, 500);
	    	    	}
	    		};
	    		timer2.schedule(task, 2500);     
	    	}
		};
		timer1.schedule(task, 2500); 
	}
	
	/**
	Resets the characters and deletes a life of pacman.
	Also ends game if pacmans life is <0
	 */
	public void pacDeath() {
		isGameActive=false;
		mainLoop.stop();
		Sound.stopSound();
		for(Character c:Character.getAllCharacters()) //stop all animations
			c.getAnimationTimeline().stop();
		Timer timer1 = new Timer();
		TimerTask task = new TimerTask(){
	        public void run(){
	        	//after 1 seconds
	        	Ghost.setVisibleAllGhosts(false);
	    		pac.playDeathAnimation(); //death animation 
	    		Sound.playPacmanDeath(); //death sound
	    		Timer timer2 = new Timer();
	    		TimerTask task = new TimerTask(){
	    	        public void run(){ 	 
	    	        	//after 2.7 seconds
	    	        	if(pac.getLife()<=0) { //checks if game is over
	    	        		//game over
	    	        		gui.getGameOverText().setVisible(true); //game over text
	    	        		level.getPowerBallAnimator().stop();
	    	        		level.resetColorPowerBalls();  
	    	        		Timer timer3 = new Timer();
	    		    		TimerTask task = new TimerTask(){
	    		    	        public void run(){ 	 
	    		    	        	//after 0.2 seconds
	    		    	        	gui.getBlackScreen().setVisible(true); //quick blackscreen
	    		    	        	Timer timer5 = new Timer();
	    	    		    		TimerTask task = new TimerTask(){
	    	    		    	        public void run(){	 
	    	    		    	        	Player player=new Player(pac.getScore(),0); //create object that holds the score
	    	    		    	        	player.setName(root.getScene());  //show screen where you can input a name for the score list
	    	    		    	        	gui.getBlackScreen().setVisible(false);
	    	    		    	    	}
	    	    	        		};
	    	    	        		timer5.schedule(task, 200);
	    		    	    	}
	    	        		};
	    	        		timer3.schedule(task, 3500);
	    	        	}else {
	    	        		//game continues
	    	        		level.setEatablesVisibility(false);
	    	        		level.getPowerBallAnimator().stop();
	    	        		level.resetColorPowerBalls();
	    	        		Timer timer4 = new Timer();
	    	        		TimerTask task = new TimerTask(){
	    	        			public void run(){ 
	    	        				gui.removeLife(pac); //remove one life
	    	        				level.setEatablesVisibility(true);
	    	        				Character.resetAllCharacters(); //reset characters
	    	        				Character.setVisibleAllCharacters(true);	    	    	    			
	    	        				freeze(2000); //continue game after 2 seconds
	    	        			}
	    	        		};
	    	        		timer4.schedule(task, 200);
	    	    	    }
	    	    	}
	    	    };
	    		timer2.schedule(task, 2700);
	        }
		};
		timer1.schedule(task, 1000); 
	}

	/**
	Is the first method that gets called to play Pacman. It startes the whole game and initializes everything
	 */
	public void start() {	
		//setup
		loop();
		gui.setup(root,Rankings.getHighScorePacman());
		level.setup(root);
		Character.setupAllCharacters(root);
		Character.setVisibleAllCharacters(false);
		pac.setDirectionNXNY(root); //key pressed for pacman
		Sound.playTitelMusic(); //play intro music
		gui.getReadyText().setVisible(true); //ready! text
    	Ghost.setOutOfBase(red.getStartX(), red.getStartY()); //sets ghost cage coordinates
    	Ghost.setMiddleOfBase(pink.getStartX(), pink.getStartY()); //sets ghost cage coordinates
    	Ghost.setupPathfinding(); //calculates every movable path
		Timer timer1 = new Timer();   
		TimerTask task = new TimerTask(){
	        public void run(){
	        	Character.setVisibleAllCharacters(true);
	        	gui.removeLife(pac);
	    		Timer timer2 = new Timer();
	    		TimerTask task = new TimerTask(){
	    	        public void run(){    	   
	    	        	gui.getReadyText().setVisible(false);
	    	        	level.getPowerBallAnimator().start();
	    	        	Sound.playGhostNoise();
	    	        	for(Ghost g:Ghost.getallGhosts()) { //start ghost animation
	    	        		g.getAnimationTimeline().play();
	    	        		g.setStart(true);
	    	        	}
	    	        	mainLoop.start();
	    	        	isGameActive=true;	    	        	
	    	        }
	    		};
	    		timer2.schedule(task, 2500); //2500
	    	}    
		};
		timer1.schedule(task, 2000);	//2000
	}
	
	/**
	Gets called when a ghost dies.
	 */
	public void ghostDeath() {
		isGameActive=false;
		mainLoop.stop(); //stops loop for a quick moment just like the original
		pac.setVisible(false);
		//text with the score that you get fromt he ghost
		Text t=new Text(pac.getX(),pac.getY()+20,""+(int)(Math.pow(2, Ghost.getGhostEatenCounter()-1)*100));
		t.setFill(Color.LIGHTSKYBLUE);
		t.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 20));
		if(Level.getHieght()==720) {
			t.setFont(Font.loadFont("file:recources\\Font\\PressStart2P-Regular.ttf", 10));
		}
		root.getChildren().add(t);
		Timer timer1 = new Timer();
		TimerTask task = new TimerTask(){
	        public void run(){
	        	// after 0.8 seconds continue game
	        	pac.setVisible(true);
	        	isGameActive=true;
	        	t.setVisible(false);
	    		mainLoop.start();
	        }
		};
		timer1.schedule(task, 800);     
	}
	
	/**
	Method that continues the game after some time
	@param millis= after that time the game will continue
	 */
	public void freeze(long millis) {
		gui.getReadyText().setVisible(true); //ready! text
		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
	        public void run(){
	        	gui.getReadyText().setVisible(false);
	        	level.getPowerBallAnimator().start();
	        	Sound.playGhostNoise();
	        	for(Ghost g:Ghost.getallGhosts()) {
	        		g.getAnimationTimeline().play();
	        		g.setStart(true);
	        	}
	        	mainLoop.start();
	        	isGameActive=true;
	        }
		};
		timer.schedule(task, millis); 
	}
	

	public static int getLevelSelector() {
		return levelSelector;
	}
	
	public AnimationTimer mainLoop() {
		return mainLoop;
	}
	
	/**
	Pauses the game
	 */
	public void pause() {
		mainLoop.stop();
		level.getPowerBallAnimator().stop(); //stop blinking animation
		for(Ghost g:Ghost.getallGhosts()) {
			g.getAnimationTimeline().stop(); //stops ghost when in frightened mode
			g.pause();
		}
		pac.getAnimationTimeline().stop();
		Sound.stopSound();
	}
	
	/**
	resumes the game
	 */
	public void resume() {
		mainLoop.start();
		level.getPowerBallAnimator().start(); //resumes blinking animation
		for(Ghost g:Ghost.getallGhosts()) { //resumes frightened mode
			if(!g.getDeath())
				g.getAnimationTimeline().play();
			g.resume();
		}
		pac.getAnimationTimeline().play();
		Sound.playGhostNoise();
		pac.setDirectionNXNY(root);
	}
	
	public static Boolean isGameActive() {
		return isGameActive;
	}
	
	public static void setGameActive(Boolean b) {
		isGameActive=b;
	}
	
	/**
	clears static lists.
	 */
	public static void reset() {
		Ghost.getallGhosts().clear();
		Character.getAllCharacters().clear();
	}
}
