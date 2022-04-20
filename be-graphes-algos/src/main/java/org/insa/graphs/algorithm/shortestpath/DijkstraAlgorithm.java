package org.insa.graphs.algorithm.shortestpath;

import java.util.Arrays;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.algorithm.shortestpath.Label;

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
        
     // Initialize array of distances.
        double[] distances = new double[nbNodes];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[data.getOrigin().getId()] = 0;
        boolean found = false;
        
        
        
        
        
        
        
        for (int i = 0; !found && i < nbNodes; ++i) {
            found = true;
            data.getOrigin().getNumberOfSuccessors()
            
            
            
            
            
        }
        
        
        
        
        // Initialize array of predecessors.
        Arc[] predecessorArcs = new Arc[nbNodes];
        
        
        
        
        
        ShortestPathSolution solution = null;
        // TODO:
        
        
        
        
        
        
        
        
        
        
        
        return solution;
    }
    public void associateLabel(Label label, Node node) {
    	label.setNode(node);
    }
   

}
