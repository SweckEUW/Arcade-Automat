package Pacman;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
The Ghost is a character which tries to hunt down pacman.
This class contains most of the logic how the ghost generate its next path and what it does when it dies or switches modes
 */
public abstract class Ghost extends Character {
	
	private Line m1,m2,m3,m4,m5,m6; //Mouth 
	private Arc h; //Head
	private Ellipse e1,e2; //Eyes
	private Rectangle b,p1,p2; //Body,pupils
	private Polygon l1,l2; //Legs
	private static ArrayList<Ghost> allGhosts=new ArrayList<Ghost>(); //Static list for every ghost
	private Color defaultColor; //default color of the ghost
	private boolean frightened,death,toBase,outOfBase,chase,on,frightenedPause,start=true; //booleans to decide which mode the ghost is in
	private ArrayList<Node> closed,open,path; //open and closed list for a* , path is the list with the next points the ghost is following
	private ArrayList<Point> everyPath=new ArrayList<Point>(); //list with every point a ghost can move to 
	private ArrayList<Point> topLeft=new ArrayList<Point>(); //top left part of every path list
	private ArrayList<Point> topRight=new ArrayList<Point>(); //top right part of every path list
	private ArrayList<Point> bottomLeft=new ArrayList<Point>(); //bottom left part of every path list
	private ArrayList<Point> bottomRight=new ArrayList<Point>(); //bottom right part of every path list
	private static int outOfBaseX,outOfBaseY,middleOfBaseX,middleOfBaseY,ghostEatenCounter=1; //outofBase and middleOfBase are coordinates for the ghost cage
	private PauseTransition delay=new PauseTransition(); //needed for frightenedmode
	private Timeline blinkAnimation =new Timeline();//needed for frightenedmodep
   
	/**
	Also initializes all the parts of the ghost form
	 */ 
	public Ghost(Level level,int startX,int startY) {
		super(level,startX,startY);
		//initialize all the parts for the form of the ghost and add them to the shape list
		h=new Arc(0,0,18,18,0,180);
		b=new Rectangle(-18,0,36,14);
		e1=new Ellipse(-7,-7,5,6);
		e2=new Ellipse(7,-7,5,6);
		p1=new Rectangle(-9,-8,4,4);
		p2=new Rectangle(5,-8,4,4);
		l1=new Polygon(); //leg state 1
		l1.getPoints().addAll(new Double[] {
				-18.0,+14.0,
				-18.0,+18.0,
				-12.0,+14.0,
				-12.0,14.0,
				-6.0,18.0,
				0.0,14.0,
				0.0,14.0,
				6.0,18.0,
				12.0,14.0,
				12.0,14.0,
				18.0,18.0,
				18.0,14.0,
		});
		l2=new Polygon(); //leg state 2
		l2.getPoints().addAll(new Double[] {
				-18.0,+14.0,
				-12.0,+18.0,
				-6.0,+14.0,
				0.0,18.0,
				6.0,14.0,
				12.0,18.0,
				18.0,14.0,
		});
		l2.setVisible(false);
		m1=new Line(-15,7,-10,3);
		m2=new Line(-10,3,-5,7);
		m3=new Line(-5,7,0,3);
		m4=new Line(0,3,5,7);
		m5=new Line(5,7,10,3);
		m6=new Line(10,3,15,7);
		//720p
		if(Level.getHieght()==720) {
			h=new Arc(0,0,12,12,0,180);
			b=new Rectangle(-12,0,24,9);
			e1=new Ellipse(-5,-5,3,4);
			e2=new Ellipse(5,-5,3,4);
			p1=new Rectangle(-7,-6,3,3);
			p2=new Rectangle(3.5,-6,3,3);
			l1=new Polygon();
			l1.getPoints().addAll(new Double[] {
					-12.0,9.0,
					-12.0,12.0,
					-8.0,9.0,					
					-8.0,9.0,
					-4.0,12.0,
					0.0,9.0,					
					0.0,9.0,
					4.0,12.0,
					8.0,9.0,					
					8.0,9.0,
					12.0,12.0,
					12.0,9.0,
			});
			l2=new Polygon();
			l2.getPoints().addAll(new Double[] {
					-12.0,9.0,
					-8.0,12.0,
					-4.0,9.0,
					0.0,12.0,
					4.0,9.0,
					8.0,12.0,
					12.0,9.0,
			});
			m1=new Line(-9,5,-6,3);
			m2=new Line(-6,3,-3,5);
			m3=new Line(-3,5,0,3);
			m4=new Line(0,3,3,5);
			m5=new Line(3,5,7,3);
			m6=new Line(7,3,9.5,5);
		}
		e1.setFill(Color.WHITE);
		e2.setFill(Color.WHITE);
		p1.setFill(Color.BLUE);
		p2.setFill(Color.BLUE);
		m1.setStroke(Color.WHITE);
		m1.setStrokeWidth(2);
		m1.setVisible(false);
		m2.setStroke(Color.WHITE);
		m2.setStrokeWidth(2);
		m2.setVisible(false);
		m3.setStroke(Color.WHITE);
		m3.setStrokeWidth(2);
		m3.setVisible(false);
		m4.setStroke(Color.WHITE);
		m4.setStrokeWidth(2);
		m4.setVisible(false);
		m5.setStroke(Color.WHITE);
		m5.setStrokeWidth(2);
		m5.setVisible(false);
		m6.setStroke(Color.WHITE);
		m6.setStrokeWidth(2);
		m6.setVisible(false);
		getForm().add(h);
		getForm().add(b);
		getForm().add(e1);
		getForm().add(e2);
		getForm().add(p1);
		getForm().add(p2);
		getForm().add(l1);
		getForm().add(l2);
		getForm().add(m1);
		getForm().add(m2);
		getForm().add(m3);
		getForm().add(m4);
		getForm().add(m5);
		getForm().add(m6);
		allGhosts.add(this);
	}

	/**
	Searches every possible path a ghost can take for every ghost
	 */ 
	public static void setupPathfinding() {
		for(Ghost g:allGhosts) 
			g.addEveryPath();
	}
	
	/**
	Ghost "hover" animation
	 */ 
	@Override
	public void setAnimation() {
		getAnimationTimeline().getKeyFrames().add(new KeyFrame(Duration.millis(0),new KeyValue(l1.visibleProperty(),true),new KeyValue(l2.visibleProperty(),false))); //switches between leg 1 and 2 
		getAnimationTimeline().getKeyFrames().add(new KeyFrame(Duration.millis(75),new KeyValue(l1.visibleProperty(),false),new KeyValue(l2.visibleProperty(),true)));
		getAnimationTimeline().getKeyFrames().add(new KeyFrame(Duration.millis(150),new KeyValue(l1.visibleProperty(),true),new KeyValue(l2.visibleProperty(),false)));
		getAnimationTimeline().setAutoReverse(true);
		getAnimationTimeline().setCycleCount(-1);
	}
	
	public static ArrayList<Ghost> getallGhosts() {
		return allGhosts;
	}
	
	/**
	Changes the Color of the ghost. all Body parts except the eyes and pupils
	@param c = Color the ghost changes into
	 */
	public void changeColor(Color c) {
		h.setFill(c);
		b.setFill(c);
		l1.setFill(c);
		l2.setFill(c);
	}

	/**
	resets the color and also resets all other states including frightened animation and delay
	 */
	public void resetForm() {
		resetColor();
		death=false;
		delay.stop();
		blinkAnimation.stop();
	}
	
	/**
	Moves eyes to the given direction
	 */
	@Override
	public void faceDirection(int x, int y) {
		switch(x){
			case 1:
				p1.setTranslateX(getX()+2);
				p2.setTranslateX(getX()+2);
				break;
			case -1:
				p1.setTranslateX(getX()-2);
				p2.setTranslateX(getX()-2);
			}
	
		switch(y){
			case 1:
				p1.setTranslateY(getY()+2);
				p2.setTranslateY(getY()+2);
				break;
			case -1:
				p1.setTranslateY(getY()-2);
				p2.setTranslateY(getY()-2);
		}
	}

	/**
	updates the position of all ghosts and changes the next directions
	@param x = pacman x coordinate
	@param y = pacman y coordinate 
	 */
	public static void updateAllGhosts(Pac pac,Level level) {
		for(Ghost g:allGhosts) 
			g.update(pac,level);
	}
	
	/**
	updates the Ghost. calculates a new path if needed and moves it depending on the current mode the ghost has
	 */
	public void update(Pac pac,Level level) {
		setDirectionNXNY(); //sets direction if not death or in start mode
		calculatePath(pac); //calculates the path
		move();
		level.teleport(this);
		selectMode(); //switches between chase and scatter mode
	}
	
	/**
	overrides actualyMove method for the ghost needs (ghost does not need to check the collision with walls)
	 */
	public void actualMove(int dx, int dy) {
		moveHitBox(dx,dy);
		setX(getX()+dx);
		setY(getY()+dy);
		moveForm();
		moveHitBox2();
	}
	
	/**
	calculates the path the ghost will take (the ghosts have different behaviors depending the color)
	 */
	public abstract void calculatePath(Pac pac);
	
	/**
	Sets the next directions depending the mode the ghost is in
	 */
	public void setDirectionNXNY() {
		resetDirection();
		if(death) { //if the ghost is death it has a death movement
			deathMovement();
			Point next=getNextPoint();
			if(next!=null) {
				if(next.getX()>getX()) {
					setDirectionX(1);
					setDirectionY(0);
				}
				if(next.getX()<getX()) {
					setDirectionX(-1);
					setDirectionY(0);
				}
				if(next.getY()>getY()) {
					setDirectionX(0);
					setDirectionY(1);
				}
				if(next.getY()<getY()) {
					setDirectionX(0);
					setDirectionY(-1);
				}
			}
		}else if(start) { //if the game starts the ghost has to get out of the cage before it calculates a new path
			moveOutOfBase();
		}else { //move along the calculated path
			Point next=getNextPoint();
			if(next!=null) {
				if(next.getX()>getX()) {
					setDirectionX(1);
					setDirectionY(0);
				}
				if(next.getX()<getX()) {
					setDirectionX(-1);
					setDirectionY(0);
				}
				if(next.getY()>getY()) {
					setDirectionX(0);
					setDirectionY(1);
				}
				if(next.getY()<getY()) {
					setDirectionX(0);
					setDirectionY(-1);
				}
			}
		}
	}
	
	/**
	Movement into the base and again out of the base after a ghost died (ghost "respawn")
	 */
	public void deathMovement() {
		if(getX()==Ghost.getOutOfBaseX() && getY()==Ghost.getOutOfBaseY()) //move to the front of the cage
			toBase=true;
		
		if(toBase) { //move into the base
			if(GameLoop.getLevelSelector()==1) {
				setX(getX()+1);
				faceDirection(1,0);
			}else {
				setY(getY()+1);
				faceDirection(0,1);
			}
			moveForm();
			moveHitBox2();
			
			if(getX()==Ghost.getMiddleOfBaseOfBaseX() && getY()==Ghost.getMiddleOfBaseOfBaseY()) { //if the ghost is in the cage
				frightenedMode(false); //reset frightened mode
				death(false); //change form back to normal
				setDeath(true);
				toBase=false;
				outOfBase=true;		
			}	
		}
		
		if(outOfBase) { //move out of base again
			if(GameLoop.getLevelSelector()==1) {
				setX(getX()-1);
				faceDirection(-1,0);
			}
			else {
				setY(getY()-1);
				faceDirection(0,-1);
			}
			moveForm();
			moveHitBox2();	
			
			if(getX()==Ghost.getOutOfBaseX() && getY()==Ghost.getOutOfBaseY()) { //if ghost is out of base again
				setDeath(false); //death is false
				outOfBase=false;
				getAnimationTimeline().play(); //start animation again
			}
		}
	}
	
	/**
	Sets the visibility of all ghosts
	 */
	public static void setVisibleAllGhosts(boolean visibility) {
		for(Ghost g:allGhosts) 
			g.setVisible(visibility);
		
	}
	
	/**
	in frightened phase the ghosts turn blue and the pacman can eat the ghosts. When the ghost enter frightened mode they choose a random path every second.
	Right before the ghost gets out of frightened mode (after 7 seconds) it starts blinking white to indicate that the phase is nearly over.
	The phase gets started when the pacman eats a power ball
	 */
	public void phaseFrightened() {
		if(frightened) { //if already in fightened mode restart frightened mode
			delay.playFromStart(); //restart 7 second 
			changeColor(Color.rgb(0, 50, 230)); //reset color to blue
			blinkAnimation.playFromStart(); //restart blink animation
		}
		if(!frightened && !death) { //if the ghost is not in frightened mode and not death
			blinkAnimation.getKeyFrames().clear(); //set blinking animation
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(4),e-> changeColor(Color.WHITE)));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(4.5),e-> changeColor(Color.rgb(0, 50, 230))));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(5),e-> changeColor(Color.WHITE)));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(5.4),e-> changeColor(Color.rgb(0, 50, 230))));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(5.8),e-> changeColor(Color.WHITE)));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(6),e-> changeColor(Color.WHITE)));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(6.2),e-> changeColor(Color.rgb(0, 50, 230))));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(6.4),e-> changeColor(Color.WHITE)));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(6.6),e-> changeColor(Color.rgb(0, 50, 230))));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(6.7),e-> changeColor(Color.WHITE)));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(6.8),e-> changeColor(Color.rgb(0, 50, 230))));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(6.9),e-> changeColor(Color.WHITE)));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(7),e-> changeColor(Color.rgb(0, 50, 230))));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(7.1),e-> changeColor(Color.WHITE)));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(7.2),e-> changeColor(Color.rgb(0, 50, 230))));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(7.3),e-> changeColor(Color.WHITE)));
			blinkAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(7.4),e-> changeColor(Color.rgb(0, 50, 230))));
			blinkAnimation.play();
			frightenedPause=true; //needed for pause menue during game
			if(!Sound.getSirene().isPlaying())
				Sound.getSirene().play(); //play sirene sound
			frightenedMode(true);
			delay.setDelay(Duration.seconds(7)); //set 7 second delay
			delay.setOnFinished( e->{ //after 7 seconds
				delay.setDelay(Duration.seconds(7));
				frightenedMode(false); //frightened mode is false
				ghostEatenCounter=1; //reset ghost eaten counter 
				Sound.stopSirene();
				frightenedPause=false;
			});
			delay.play();	
		}
	}
		
	/**
	if frightened=true sets the color of the ghost to blue, finds a random path and disables mouth
	if frightened=false resets color and disables the mouth
	*/
	public void frightenedMode(boolean frightened) {
		if(frightened) {
			changeColor(Color.rgb(0, 50, 230));
			pathFinding(getEveryPath().get((int)(Math.random()*getEveryPath().size()-1)));
		}
		else {
			resetColor();
			blinkAnimation.stop();
		}
		m1.setVisible(frightened);
		m2.setVisible(frightened);
		m3.setVisible(frightened);
		m4.setVisible(frightened);
		m5.setVisible(frightened);
		m6.setVisible(frightened);
		this.frightened=frightened;
	}
	
	/**
	if the ghost dies the body turns invisible and only the eyes are visible.
	when death is true it will find the shortest path to the front of the ghost cage
	*/
	public void death(boolean b) {
		if(b) {
			//sets mout visible
			m1.setVisible(!b);
			m2.setVisible(!b);
			m3.setVisible(!b);
			m4.setVisible(!b);
			m5.setVisible(!b);
			m6.setVisible(!b);
			this.b.setVisible(!b);
			delay.stop();
		}
		//sets body visible
		h.setVisible(!b);
		l1.setVisible(!b);
		l2.setVisible(!b);
		this.b.setVisible(!b);	
		if(b)
			pathFinding(new Point(Ghost.getOutOfBaseX(),Ghost.getOutOfBaseY())); //path to the front of the ghost cage
		death=b;
	}

	/**
	resets color to default color
	*/
	public void resetColor() {
		changeColor(defaultColor);
	}
	
	public void setDefaultColor(Color d) {
		defaultColor=d;
	}
	
	public Color getDefaultColor() {
		return defaultColor;
	}
	
	/**
	sets visibility of the ghost.
	*/
	public void setVisible(boolean visibility) {
		if(!visibility) {
			m1.setVisible(false);
			m2.setVisible(false);
			m3.setVisible(false);
			m4.setVisible(false);
			m5.setVisible(false);
			m6.setVisible(false);
		}
		h.setVisible(visibility);
		b.setVisible(visibility);
		e1.setVisible(visibility);
		e2.setVisible(visibility);
		p1.setVisible(visibility);
		p2.setVisible(visibility);
		l1.setVisible(visibility);
		l2.setVisible(visibility);
	}
	
	public static int getGhostEatenCounter() {
		return ghostEatenCounter;
	}
	
	public static void setGhostEatenCounter(int c) {
		ghostEatenCounter=c;
	}
	
	public boolean getfrightened() {
		return frightened;
	}

	/**
	A* pathfinding algorithm. It will find the shortest path from the current X and Y of the ghost to the end point
	@param Point end = find closest path to this point
	(for detailed description see documentation)
	*/
	public void pathFinding(Point end) {
		int k=0;
		Point start=new Point(getX(),getY());
		closed=new ArrayList<Node>();
		open=new ArrayList<Node>();
		open.add(new Node(start,end,start));
		while(!open.isEmpty()&&k<10000) {
			
			k++;
			Node currentNode=findBestNode();
			
			setX((int)currentNode.getPosition().getX());
			setY((int)currentNode.getPosition().getY());
			
			closed.add(currentNode);
			open.remove(currentNode);
			
			if(currentNode.equals(new Node(start,end,end))) {
				retracePath();
				break;
			}			
					
			currentNode.setNeightbours(this);
			
			for (int i = 0; i < currentNode.getNeighbours().size(); i++) {
				Node neighbour=currentNode.getNeighbours().get(i);
				
				if(closed.contains(neighbour)) 
					continue;		
				
				if(!open.contains(neighbour)) 
					open.add(neighbour);
				else if(currentNode.getG()>=neighbour.getG())
					continue;
				
				neighbour.setParent(currentNode);
			}
		}
		
		setX((int)start.getX());
		setY((int)start.getY());
	}	

	/**
	finds the node with the lowest f score (nearest node to the goal).
	it searches in the open list.
	*/
	public Node findBestNode() {
		Node bestNode = open.get(0);
		for (int i =1; i<open.size(); i++) 
		    if ( bestNode.getF() > open.get(i).getF())
		    	bestNode = open.get(i);
		
		open.remove(bestNode);
		return bestNode;
	}
	
	/**
	retraces the best path and saves every node of the path in the path list
	*/
	public void retracePath() {
		path=new ArrayList<Node>(); //clears list 
		Node goal=closed.get(closed.size()-1); //starting with the goal node
		while(goal.getParent()!=null) { //get the parent of the node and the parent of this node until you reach a node with no parent (the node with no parent is the starting node)
			path.add(goal);
			goal=goal.getParent();
		}
	}
	
	/**
	returns and removes the last node of the path list (the next node the ghost will move to)
	*/
	public Point getNextPoint() {
		if(path.size()==0)
			return null;
		Node nextNode=path.get(path.size()-1);
		path.remove(path.size()-1);
		return nextNode.getPosition();
	}
	
	public ArrayList<Node> getPath(){
		return path;
	}
	
	public Boolean getDeath() {
		return death;
	}
	
	public void setDeath(Boolean death) {
		this.death=death;
	}
	
	/**
	recursive function which will find every possible X and Y the ghost can go to in the specific level
	(stack overflow error will occure if you dont increase the stack size)
	Open the Run Configuration for your application
	The arguments tab has a text box Vm arguments, enter -Xss1m
	*/
	public void setupEveryPath(Point p) {
		if(!everyPath.contains(p)) {
			everyPath.add(p);
			setX((int)p.getX());
			setY((int)p.getY());
			if(isMovePossible(1,0)) 
				setupEveryPath(new Point(getX()+1,getY()));
			setX((int)p.getX());
			setY((int)p.getY());
			if(isMovePossible(-1,0)) 
				setupEveryPath(new Point(getX()-1,getY()));
			setX((int)p.getX());
			setY((int)p.getY());
			if(isMovePossible(0,1)) 
				setupEveryPath(new Point(getX(),getY()+1));
			setX((int)p.getX());
			setY((int)p.getY());
			if(isMovePossible(0,-1)) 
				setupEveryPath(new Point(getX(),getY()-1));
		}
	}
	
	/**
	this function will get the list with every possible X and Y a ghost can go to and separate it into 4 lists.
	Every ghost has a mode where he goes to his specific corner. We need the lists later to calculate a path to the specific corner 
	*/
	public void addEveryPath() {
		everyPath.clear();
		int startX=getX();
		int startY=getY();
		setupEveryPath(new Point(Ghost.getOutOfBaseX(),Ghost.getOutOfBaseY()));
		setX(startX);
		setY(startY);
		int x=0;
		int y=0;
		for(Point p:everyPath) {
			x+=p.getX();
			y+=p.getY();
		}
		x=x/everyPath.size(); //find the middle of every possible x coordinate
		y=y/everyPath.size(); //find the middle of every possible y coordinate
		//separate everyPath into 4 lists
		for(Point p:everyPath) { 
			if(p.getX()<x && p.getY()<y)
				topLeft.add(p);
			if(p.getX()>x && p.getY()<y)
				topRight.add(p);
			if(p.getX()<x && p.getY()>y)
				bottomLeft.add(p);
			if(p.getX()>x && p.getY()>y)
				bottomRight.add(p);
		}
	}
	
	public Boolean getChase() {
		return chase;
	}
	
	public ArrayList<Point> getEveryPath(){
		return everyPath;
	}
	
	public ArrayList<Point> getTopLeft(){
		return topLeft;
	}
	
	public ArrayList<Point> getTopRight(){
		return topRight;
	}
	
	public ArrayList<Point> getBottomLeft(){
		return bottomLeft;
	}
	
	public ArrayList<Point> getBottomRight(){
		return bottomRight;
	}
	
	/**
	switches between chase mode and scatter mode.
	In chase mode the ghost chase the pacman in their specific way.
	In scatter mode the ghosts move to their own corner.
	*/
	public void selectMode() {
		if(chase) {
			if(!on) {
				on=true;
				Timer timer = new Timer();
				TimerTask task = new TimerTask(){
					public void run(){
						chase=false;
						on=false;
					}
				};
				timer.schedule(task, 20000); //chase mode will last for 20 seconds
			}	
		}else { //chase=false=scatter mode
			if(!on) {
				on=true;
				Timer timer = new Timer();
				TimerTask task = new TimerTask(){
				       public void run(){
				    	   chase=true;
				    	   on=false;
				       }
				};
				timer.schedule(task, 8000); //scatter mode will last for 20 seconds
			}	
		}
			
	}
	
	/**
	every ghost has its own way to move out of the base at the beginning of the game
	*/
	public abstract void moveOutOfBase();
	
	public void setStart(boolean start) {
		this.start=start;
	}
	
	public boolean getStart() {
		return start;
	}
	
	public static void setOutOfBase(int x,int y) {
		outOfBaseX=x;
		outOfBaseY=y;
	}
	
	public static int getOutOfBaseX() {
		return outOfBaseX;
	}
	
	public static int getOutOfBaseY() {
		return outOfBaseY;
	}
	
	public static void setMiddleOfBase(int x,int y) {
		middleOfBaseX=x;
		middleOfBaseY=y;
	}
	
	public static int getMiddleOfBaseOfBaseX() {
		return middleOfBaseX;
	}
	
	public static int getMiddleOfBaseOfBaseY() {
		return middleOfBaseY;
	}
	
	/**
	pauses the frightened mode when you are in the menue during the game
	*/
	public void pause() {
		delay.pause();
		blinkAnimation.pause();
	}
	
	/**
	resumes the frightened mode when you are in the menue during the game
	*/
	public void resume() {
		if(frightenedPause) {
			delay.play();
			blinkAnimation.play();
		}
	}
	
	public boolean getFrightened() {
		return frightened;
	}
}



