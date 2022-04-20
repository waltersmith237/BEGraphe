package org.insa.graphs.model;
import java.util.ArrayList;

public class Label{
	private int currentNode;
	private boolean mark;
	private double costFromOrigin;
	private Node father;
	
public 	Label(int currentNode,Node father) {
	this.currentNode = currentNode;
	this.mark = false;
	this.costFromOrigin = Double.MAX_VALUE; 
	this.father = father ;
	
	}
}
