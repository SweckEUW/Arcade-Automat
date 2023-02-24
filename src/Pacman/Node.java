package Pacman;

import java.awt.Point;
import java.util.ArrayList;

/**
The Node Class is an object which has a position and calculates costs for the a*pathfinding algorithm.
It also calculates its neightbours and has a parent node (the node it came from)
*/
public class Node {

	private Node parent; //the node this node came from
	private Point start,end,position; //start and end point of the path and current position of this node
	private double h,g; //h= cost from start position to the nodes position, g==cost from the current position to the end position both costs as manhatten distance
	private ArrayList<Node> neightbours=new ArrayList<Node>(); //list containing all walkable neighbours of this node
	
	/**
	The Node Class is an object which has a position and calculates costs for the a*pathfinding algorithm.
	It also calculates its neightbours and has a parent object (the node it came from)
	*/
	Node(Point start,Point end,Point position){
		this.start=start;
		this.end=end;
		this.position=position;
		double x=position.getX()-start.getX(); //h x cost
		if(x<0) //absolute value
			x*=-1;
		double y=position.getY()-start.getY(); //h y cost
		if(y<0) //absolute value
			y*=-1;
		h=x+y; //h cost
		x=end.getX()-position.getX(); //g x cost
		if(x<0) //absolute value
			x*=-1;
		y=end.getY()-position.getY(); //g y cost
		if(y<0) //absolute value
			y*=-1;
		g=x+y; //g cost
	}
	 
	/**
	cost of this node
	*/
	public double getF() {
		return h+g; //F is combined cost of g and h
	}
	
	/**
	gets ghost position and calculates all walkable paths within one move
	*/
	public void setNeightbours(Ghost ghost) {
		if(ghost.isMovePossible(1, 0)) //down
			neightbours.add(new Node(start,end,new Point(ghost.getX()+1,ghost.getY())));
		if(ghost.isMovePossible(-1, 0)) //up
			neightbours.add(new Node(start,end,new Point(ghost.getX()-1,ghost.getY())));
		if(ghost.isMovePossible(0, 1)) //right
			neightbours.add(new Node(start,end,new Point(ghost.getX(),ghost.getY()+1)));
		if(ghost.isMovePossible(0, -1)) //left
			neightbours.add(new Node(start,end,new Point(ghost.getX(),ghost.getY()-1)));
	}
	
	public ArrayList<Node> getNeighbours(){
		return neightbours;
	} 
	
	public Point getStart() {
		return start;
	}
	
	public Point getEnd() {
		return end;
	}
	
	public void setParent(Node p) {
		parent=p;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public double getG() {
		return g;
	}
	
	public double getH() {
		return h;
	}

	public boolean equals(Object o) {
		if(o instanceof Node)
			if(((Node) o).getPosition().getX()==this.getPosition().getX() && ((Node) o).getPosition().getY()==this.getPosition().getY())
				return true;
		return false;
	}
	
}

