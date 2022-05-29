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

class StarTest extends DijksrtaTestTest{
	 
		
		@Test
		public void testRandomNodes() {
			Node origin,destination;
			ArcInspector arcInspect;
			ShortestPathAlgorithm DijkstraAlgorithm,AStarAlgorithm;
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
					AStarAlgorithm = generateAlgorithm(new ShortestPathData(graph, origin, destination, arcInspect));
					assertTrue(DijkstraAlgorithm.run().getStatus() == Status.OPTIMAL);
					assertTrue(AStarAlgorithm.run().getStatus() == Status.OPTIMAL);
					assertEquals(DijkstraAlgorithm.run().getPath().getLength(), AStarAlgorithm.run().getPath().getLength(),0);
				}
				} catch (Exception e) {
					System.out.println(e.toString());
					
			
			
				
			
		}
		}

	public ShortestPathAlgorithm generateAlgorithm(ShortestPathData data){
			return new  AStarAlgorithm(data);
			
		}
	}
	

