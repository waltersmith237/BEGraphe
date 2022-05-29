package org.insa.graphs.algorithm.shortestpath;
import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;
import org.junit.Test;

class DijksrtaTest {
	

	@Test
	public void testNonExistentPath() {
		try {
			 // Visit these directory to see the list of available files on Commetud.
	        final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
	
	        // Create a graph reader.
	        final GraphReader reader = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	        // Read the graph.
	        final Graph graph = reader.read();	

	        ArcInspector arcInspect = ArcInspectorFactory.getAllFilters().get(0);
    		
	        Node origin = graph.get((int)83);//75
	        Node destination = graph.get((int)1753);		               
	        
	        
	        ShortestPathAlgorithm DijjstraSolution =getAlgorithm(new ShortestPathData(graph, origin, destination, arcInspect));	        
	        BellmanFordAlgorithm BellmanSolution = new BellmanFordAlgorithm(new ShortestPathData(graph, origin, destination, arcInspect));
	        assertEquals(DijjstraSolution,BellmanSolution);
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	/*@Test
	public void testShortestPath() {
		try {
			
			
			
		}catch(Exception e) {
			System.out.println(e.toString());
			
		}
		
	}*/
	@Test
	public void testLongestPath() {
		try {
			 // Visit these directory to see the list of available files on Commetud.
	        final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";
	
	        // Create a graph reader.
	        final GraphReader reader = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	        // Read the graph.
	        final Graph graph = reader.read();	
	        ArcInspector shortArcInspect = ArcInspectorFactory.getAllFilters().get(0);
	        ArcInspector fastArcInspect = ArcInspectorFactory.getAllFilters().get(2);
    		
	        Node origin = graph.get((int)(Math.random() * (graph.size()+ 1)));
	        Node destination = graph.get((int)(Math.random() * (graph.size()+ 1)));		               
	        
	        
	        ShortestPathAlgorithm DijkstraFastAlgorithm = getAlgorithm(new ShortestPathData(graph, origin, destination, fastArcInspect));	   
	        ShortestPathAlgorithm DijkstraShortAlgorithm =getAlgorithm(new ShortestPathData(graph, origin, destination, shortArcInspect));	     
	        
	        ShortestPathSolution DijkstraFastSolution = DijkstraFastAlgorithm.run();
	        ShortestPathSolution DijkstraShortSolution = DijkstraShortAlgorithm.run();
	        
	        assertEquals(DijkstraFastSolution.isFeasible(), DijkstraShortSolution.isFeasible());  
	        
	        if (DijkstraFastSolution.isFeasible()){
		        assertTrue(DijkstraFastSolution.getPath().getLength()>=DijkstraShortSolution.getPath().getLength() );
		        //System.out.println(origin.getId()+":"+destination.getId());
		        System.out.println(DijkstraFastSolution.getPath().getMinimumTravelTime()+":"+DijkstraShortSolution.getPath().getMinimumTravelTime());
		        assertTrue(DijkstraFastSolution.getPath().getMinimumTravelTime()<=DijkstraShortSolution.getPath().getMinimumTravelTime());	   
		       
		        	
		        }
			
		}catch(Exception e) {
			System.out.println(e.toString());
			
		}
		
	}
	@Test
	public void testNullPath() {
		try {
			// Visit these directory to see the list of available files on Commetud.
	        final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
	
	        // Create a graph reader.
	        final GraphReader reader = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	        // Read the graph.
	        final Graph graph = reader.read();	

	        ArcInspector arcInspect = ArcInspectorFactory.getAllFilters().get(0);
    		
	        Node origin = graph.get((int)Math.random() * graph.size());
	        Node destination = origin;
	        
	        
	        ShortestPathAlgorithm DijkstraSolution = getAlgorithm(new ShortestPathData(graph, origin, destination, arcInspect));	       
	        assertEquals(DijkstraSolution.run().getStatus(),Status.OPTIMAL);      
	        assertEquals(DijkstraSolution.run().getPath().size(),0);
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
	/*@Test
	public void testRandomNodes() {
		try {
			for (int i = 0; i < 20; i++) {
				generateRandomSolution();
				
				assertTrue(solutionsDij.getStatus() == Status.OPTIMAL);
				assertTrue(solutionsAst.getStatus() == Status.OPTIMAL);
				assertEquals(solutionsDij.getPath().getArcs(), solutionsAst.getPath().getArcs());
			}
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}

	private void generateRandomSolution() {
		
		do {
			Object origin = graph.get((int) Math.floor(Math.random() * graph.size()));
			Object destination = graph.get((int) Math.floor(Math.random() * graph.size()));

			ArcInspector arcInspect = ArcInspectorFactory.getAllFilters().get((int) Math.floor(Math.random() * 5));

			ShortestPathAlgorithm solutionsDij = getSolution(algorithm, graph, origin, destination, arcInspect);
			BellmanFordAlgorithm  solutionsAst = new BellmanFordAlgorithm (new ShortestPathData(graph, origin, destination, arcInspect);
		} while (solutionsDij.getStatus() == Status.INFEASIBLE || solutionsAst.getStatus() == Status.INFEASIBLE);
	}*/
	public ShortestPathAlgorithm getAlgorithm(ShortestPathData data){
		return new  DijkstraAlgorithm(data);
		
	}

}
