package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class LabelStar extends Label implements Comparable<Label>{
	/*private  long costFromOrigin;
	private  Arc father;
	private  Node mark;*/
	private double heuristicCost;

	
	public LabelStar(Node node ,double heuristicCost) {
		super(node);
		this.heuristicCost =heuristicCost ;
		this.mark = false;
		this.costFromOrigin = Double.POSITIVE_INFINITY; 
		this.father = null;

	}
	public void setheuristicCost(long cost) {
		this.heuristicCost = cost;
	}
	public double getTotalCost() {
		return this.heuristicCost + this.costFromOrigin;
	}
}
