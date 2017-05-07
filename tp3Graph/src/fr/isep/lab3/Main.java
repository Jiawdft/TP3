package fr.isep.lab3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import fr.isep.lab3.WDgraph.Node;


public class Main {

	public static void main(String[] args) throws IOException {
		
		/*
		Graph graph = new Graph("graph-DFS-BFS.txt");
		Dfs rundfs = new Dfs();
		ArrayList<Integer> listdfs = rundfs.dfs(graph, 5);
		System.out.println("the order of the first encounter of the node 5 by dfs : " + listdfs);
		int nombreComponent = rundfs.cc(graph);
		System.out.println("The graph have : " + nombreComponent + " components");
		boolean isConnected = rundfs.isConnected(graph);
		System.out.println("Connected graph : " + isConnected);
		
		Bfs runbfs = new Bfs();
		ArrayList<Integer> listbfs = runbfs.bfs(graph, 5);
		System.out.println("the order of the first encounter of the node 5 by bfs : " + listbfs);
		
		BFSShortestPaths runBfsShortestPaths = new BFSShortestPaths();
		Digraph digraph = new Digraph("graph-BFS-SP.txt");
		digraph.affichageAdj();
		ArrayList<Integer> excentricityList = new ArrayList<Integer>();
		//pour chaque noeuds on realise une bfs, on affiche le shortest path vers tous les autres noeuds
		//et on cherche son excentricity
		for (int i = 0; i < digraph.getAdj().length; i++) {
			System.out.println("Strating Node : " + digraph.getAdj()[i].nodeId);
			runBfsShortestPaths.bfs(digraph, digraph.getAdj()[i].nodeId);
			for (int j = 0; j < digraph.getAdj().length; j++) {
				runBfsShortestPaths.printSp(j);
			}
			int excentricity = runBfsShortestPaths.excentricity();
			if (excentricity != 0) {
				excentricityList.add(excentricity);
				System.out.println("Excentricity for the node " + digraph.getAdj()[i].nodeId + " is " + excentricity);
			}
			else {
				System.out.println("The node " + digraph.getAdj()[i].nodeId + " is isolated");
			}
			System.out.println();
		}
		
		int diameter = Collections.max(excentricityList);
		System.out.println("The diameter of the graph is " + diameter);
		int radius = Collections.min(excentricityList);
		System.out.println("The radius of the graph is " + radius);
		*/
		
		WDgraph wdGraph = new WDgraph("graph-WDG.txt");
		wdGraph.affichageAdj();
		DijkstraSP runDijkstrapSP = new DijkstraSP();
		boolean isNegative = runDijkstrapSP.verifyNonNegative(wdGraph);
		if (isNegative) {
			System.out.println( "there are at least one negative weight in the graph. ");
		}
		else {
			System.out.println("all weights in the graph are non negative.");
		}
		runDijkstrapSP.dijkstraSP(wdGraph, 1);
		boolean hasPathTo = runDijkstrapSP.hasPathTo(8);
		System.out.println("has path to v: " + hasPathTo);
		double distTo = runDijkstrapSP.distTo(8);
		System.out.println("Distance to v : " + distTo);
		System.out.print("The shortest path from s to v : ");
		runDijkstrapSP.printSP(8);
		/*
		Graph graph = new Graph("graph-DFS-BFS.txt");
		graph.affichageAdj();
		
		System.out.println("Average degree : " + graph.averageDegree());
		System.out.println("Minimum degree : " + graph.minimumDegree());
		System.out.println("Maximum degree : " + graph.maximumDegree());
		System.out.println("Edge density : " + graph.density());
		
		graph.neighbors(1);
		graph.neighbors(2);
		graph.neighbors(3);
		graph.neighbors(4);
		*/
		
		
	}
}
