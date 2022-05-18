package org.insa.graphs.algorithm.shortestpath;
//import java.util.ArrayList;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label  implements Comparable<Label>{
	protected Node currentNode;
	protected boolean mark;
	protected double costFromOrigin;
	protected Arc father;
	
	public 	Label(Node node) {
		this.currentNode = node;
		this.mark = false;
		this.costFromOrigin = Double.POSITIVE_INFINITY; 
		this.father = null;
	
	}	
	
	
	public Node getNode( ) {
		return this.currentNode;
	}
	public boolean getMark() {
		return this.mark;
	}
	public void setMark() {
		 this.mark = true;
	}
	public Arc getArcFather() {
		return this.father;
	}
	public void setFather(Arc father) {
		this.father = father;
	}
	public void  setCostFromOrigin(double cost) {
		this.costFromOrigin = cost;
	}
	public double getCostFromOrigin() {
		return this.costFromOrigin;
	}
	public double getTotalCost() {
		return this.costFromOrigin;
	}
	
	
	 public int compareTo(Label other) {
		 
		 return Double.compare(getTotalCost(), other.getTotalCost());
	      
	    }
	
	
	
	
	
}

