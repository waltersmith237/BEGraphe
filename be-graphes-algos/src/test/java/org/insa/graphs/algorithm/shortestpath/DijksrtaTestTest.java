package org.insa.graphs.algorithm.shortestpath;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.jupiter.api.Test;

class DijksrtaTestTest {
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
	        
	        
	        ShortestPathAlgorithm DijjstraAlgorithm =getAlgorithm(new ShortestPathData(graph, origin, destination, arcInspect));	        
	        BellmanFordAlgorithm BellmanAlgorithm = new BellmanFordAlgorithm(new ShortestPathData(graph, origin, destination, arcInspect));
	        assertEquals(DijjstraAlgorithm,BellmanAlgorithm);
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
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
		    assertTrue(DijkstraFastSolution.getPath().getLength()>=DijkstraShortSolution.getPath().getLength() );
		    assertTrue(DijkstraFastSolution.getPath().getMinimumTravelTime()<=DijkstraShortSolution.getPath().getMinimumTravelTime());	   
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
	        
	        
	        ShortestPathAlgorithm DijkstraAlgorithm = getAlgorithm(new ShortestPathData(graph, origin, destination, arcInspect));	       
	        assertEquals(DijkstraAlgorithm.run().getStatus(),Status.OPTIMAL);      
	        assertEquals(DijkstraAlgorithm.run().getPath().size(),0);
			
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
	@Test
	public void testRandomNodes() {
		Node origin,destination;
		ArcInspector arcInspect;
		ShortestPathAlgorithm DijkstraAlgorithm;
		BellmanFordAlgorithm BellmanAlgorithm;
		try {
			 // Visit these directory to see the list of available files on Commetud.
	        final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/madagascar.mapgr";

	        // Create a graph reader.
	        final GraphReader reader = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

	        // Read the graph.
	        final Graph graph = reader.read();	
	        for (int i = 0; i < 10; i++) {
			
	        	origin = graph.get((int) Math.floor(Math.random() * graph.size()));
	        	destination = graph.get((int) Math.floor(Math.random() * 34));
				arcInspect = ArcInspectorFactory.getAllFilters().get((int) Math.floor(Math.random() * 5));
				DijkstraAlgorithm = getAlgorithm(new ShortestPathData(graph, origin, destination, arcInspect));
				BellmanAlgorithm = new BellmanFordAlgorithm(new ShortestPathData(graph, origin, destination, arcInspect));
				assertTrue(DijkstraAlgorithm.run().getStatus() == Status.OPTIMAL);
				assertEquals(DijkstraAlgorithm.run().getPath().getLength(), BellmanAlgorithm.run().getPath().getLength(),0);
			}
			} catch (Exception e) {
				System.out.println(e.toString());	
				e.printStackTrace();
			}
	}
	
	public ShortestPathAlgorithm getAlgorithm(ShortestPathData data){
		return new  DijkstraAlgorithm(data);
		
	}

}
