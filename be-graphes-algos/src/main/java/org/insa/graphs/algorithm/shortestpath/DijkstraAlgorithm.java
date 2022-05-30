package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	

	public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
    	// Retrieve the graph.
        final ShortestPathData data = getInputData();
        Graph graph = data.getGraph();
        final int nbNodes = graph.size();
     // Initialize list of Nodes.
        List<Node> nodes = graph.getNodes();
        //Node[] nodes = new Node[nbNodes];
        
     // Initialize array of predecessors.
        Arc[] predecessorArcs = new Arc[nbNodes];
     //create the binary Heap
        BinaryHeap<Label> heap = new BinaryHeap<Label>();
        
        notifyOriginProcessed(data.getOrigin());
        /*===============================initialisation de l'algorithme ================================================*/
     //List<Label> label = new ArrayList<Label>();
        Label[] labels = new Label[nbNodes]; //creation du tableau de labels
        Node dest = data.getDestination();
        
        labels[data.getOrigin().getId()] = new Label(data.getOrigin());
        /* le cout de l'origine à zero et insertion de l'origine dans le Heap*/
		labels[data.getOrigin().getId()].setCostFromOrigin(0);
		heap.insert(labels[data.getOrigin().getId()]);
		boolean destinationReached = false;
        
        //start dijkstra 
    
        while(!heap.isEmpty() && !destinationReached ) {
        	Label lab =  heap.deleteMin();
        	if (lab.getNode() == data.getDestination()) {
        		destinationReached = true;
        	}
        	lab.setMark();
        	//List of successors of the curent label
        	List<Arc> successors = lab.getNode().getSuccessors();
        	
        	for(Arc successor : successors ) {
        		/* ============create labels just for current node successors in order to optimize our algorithm===========*/
        		labels = initLabel(labels,successor.getDestination(),dest);
        		
				if(labels[successor.getDestination().getId()].getMark()==false && data.isAllowed(successor)) { 
					  double min =  Math.min(labels[successor.getDestination().getId()].getCostFromOrigin(),lab.getCostFromOrigin() + data.getCost(successor));
					  
					  if((lab.getCostFromOrigin()+successor.getLength()) <  labels[successor.getDestination().getId()].getCostFromOrigin()) {
						  /*the first time we visit this node*/
						  if(labels[successor.getDestination().getId()].getCostFromOrigin() == Double.POSITIVE_INFINITY) {

							  labels[successor.getDestination().getId()].setCostFromOrigin(min);
							  labels[successor.getDestination().getId()].setFather(successor);
							  heap.insert(labels[successor.getDestination().getId()]);
							  predecessorArcs[successor.getDestination().getId()]=lab.getArcFather();
							  notifyNodeReached(successor.getDestination());
						  }
						  /* the node have been visited before and we have to update his label(costFromOrigin and Father)*/
						  else {
							  heap.remove(labels[successor.getDestination().getId()]);
							  labels[successor.getDestination().getId()].setCostFromOrigin(min);
							  labels[successor.getDestination().getId()].setFather(successor);
							  predecessorArcs[successor.getDestination().getId()]=lab.getArcFather();
							  heap.insert(labels[successor.getDestination().getId()]);
							  notifyNodeReached(successor.getDestination());
						  }

					  
			  			}
				}
        	
        	}
        
        	}
        
        //solution 
        ShortestPathSolution solution = null;
        
        // Destination has no predecessor, the solution is infeasible...
        if (!labels[data.getDestination().getId()].getMark()) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {

            // The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());

            // Create the path from the array of predecessors...
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = labels[data.getDestination().getId()].getArcFather();
            while (arc != null) {
                arcs.add(arc);
                arc = labels[arc.getOrigin().getId()].getArcFather();
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
        }
        return solution;
    }
    public Label[] initLabel(Label[] labels, Node node,Node destination) {
    	//List<Label> label = new ArrayList<Label>();
    	if(labels[node.getId()]==null) {
    		labels[node.getId()] = new Label(node);
    	}
       
        return labels;
    	
    }

}


