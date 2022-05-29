package org.insa.graphs.algorithm.shortestpath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractInputData.Mode;
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
    
    public Label[] initLabel(Label[] labels, Node node,Node destination) {
        
        if(labels[node.getId()]==null && data.getMode() == Mode.LENGTH) {
        	double heuristicCost = node.getPoint().distanceTo(destination.getPoint());
        	labels[node.getId()] = new LabelStar(node,heuristicCost);
    	}
        else if (labels[node.getId()]==null && data.getMode() == Mode.TIME) {
        	double heuristicCost = node.getPoint().distanceTo(destination.getPoint());
        	int speed = data.getGraph().getGraphInformation().getMaximumSpeed();
        	double heuristicTime = heuristicCost * (3.6/speed);
        	labels[node.getId()] = new LabelStar(node,heuristicTime);

        }
        
        return labels;
 }
    
    	
    
}

