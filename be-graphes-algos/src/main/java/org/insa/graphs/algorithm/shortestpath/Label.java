package org.insa.graphs.algorithm.shortestpath;
import java.util.ArrayList;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label {
	private Node currentNode;
	private boolean mark;
	private double costFromOrigin;
	private Arc father;
	
	public 	Label() {
		this.currentNode = null;
		this.mark = false;
		this.costFromOrigin = Double.MAX_VALUE; 
		this.father = null;
	
	}	
	public double gestCost() {
		return this.costFromOrigin;
	}
	public void setNode(Node node ) {
		 this.currentNode = node;
	}
	
	
}

