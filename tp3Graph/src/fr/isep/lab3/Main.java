package fr.isep.lab3;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
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
		
		Graph graph = new Graph("graph-DFS-BFS.txt");
		Dfs toto = new Dfs();
		toto.dfs(graph, 0);
		
	}
}
