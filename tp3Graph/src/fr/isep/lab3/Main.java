package fr.isep.lab3;

import java.io.IOException;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws IOException {
		
		Graph graph = new Graph("graph-DFS-BFS.txt");
		Dfs run = new Dfs();
		ArrayList<Integer> list = run.dfs(graph, 5);
		System.out.println("the order of the first encounter of the node 5 : " + list);
		int nombreComponent = run.cc(graph);
		System.out.println("The graph have : " + nombreComponent + " components");
		boolean isConnected = run.isConnected(graph);
		System.out.println("Connected graph : " + isConnected);
		
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
