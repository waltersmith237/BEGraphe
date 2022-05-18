package org.insa.graphs.algorithm.shortestpath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;



public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    public LabelStar[] initLabel(int nbNodes, List <Node> nodes,Node destination) {
    	//List<Label> label = new ArrayList<Label>();
        LabelStar[] labels = new LabelStar[nbNodes];
        for (Node node:nodes) {
        	double heuristicCost = node.getPoint().distanceTo(destination.getPoint());
        	labels[node.getId()]=new LabelStar(node,heuristicCost);
        }
        return labels;
 }
}

