package fr.isep.lab3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Graph {
	private final int N;
	private int M;
	private List<Integer> adj[];
	
	
	
	public int getM() {
		return M;
	}

	public void setM(int m) {
		M = m;
	}

	public List<Integer>[] getAdj() {
		return adj;
	}

	public void setAdj(List<Integer>[] adj) {
		this.adj = adj;
	}

	public int getN() {
		return N;
	}

	public Graph(int n, int m, List<Integer>[] adj) {
		super();
		N = n;
		M = m;
		this.adj = adj;
	}
	
	public Graph emptyGraph(int n){
		List<Integer> adj[] = new List[n];
		Graph emptyGraph = new Graph(n, 0, adj);
		return emptyGraph;
	}
	
	public static Graph txtGraph(String fichier){
		int n = 0;
		int m = 0;
		ArrayList<String> arcs = new ArrayList();
		ArrayList<String> adjArray = new ArrayList();
		
		
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);
				arcs.add(ligne);
				
				int node1 = Integer.parseInt(ligne.substring(0,1));
				int node2 = Integer.parseInt(ligne.substring(2,3));
				
				
				
				
				m++;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

		
		List<Integer> adjGraph[] = new List[m];
		
		Graph graph = new Graph(n, m, adjGraph);
		return graph;
	}
	
	public static int getOrder(Graph graph){
		int order = graph.getN();
		return order;
	}
	
	public static int getSize(Graph graph){
		int size = graph.getM();
		return size;
	}
	
	public void addEdge(int u, int v){
		M++;
		
		if (adj[u].contains(v) == false) {
			adj[u].add(v);
		}
		if (adj[v].contains(u) == false) {
			adj[v].add(u);
		}
		
	}
	
	
}
