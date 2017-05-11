package fr.isep.lab3and4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;




public class WDgraph {
	// Order N du graph
	private int N;
	
	//Size M du graph
	private int M;
	
	//une class Node avec un identifiant et une liste de DirectedEdge
	public class Node{
		int nodeId;
		ArrayList<DirectedEdge> listDirectedEdge;
	}
	//Initialisation adj list
	private Node [] adj;
	
	public Node[] getAdj() {
		return adj;
	}
	
	public WDgraph(String filePath){
		try {
			List<String> listLignes = Files.readAllLines(Paths.get(filePath),
					StandardCharsets.UTF_8);
			N = addNodesFromTxt(listLignes);
			M = addEdgesFromTxt(listLignes);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int addNodesFromTxt(List<String> listLignes){
		//un node est parfois present dans plusieurs edges,
		//on utilise HashSet car c'est une collection qui 
		//n'accepte pas les doublons
		//Dans nodeshs on va stocker les nodes du graph
		HashSet<String> nodeshs = new HashSet<String>();
		
		//on parcourt tous les edges du "graph.txt"
		for (int i = 0; i < listLignes.size(); i++) {
			String line = listLignes.get(i);
			//pour chaque edge, on recupere les identifiants des nodes
			String directedEdgeData[] = line.split(" ");
			nodeshs.add(directedEdgeData[0]);
			nodeshs.add(directedEdgeData[1]);
		}
		//Un graph d'ordre N (order)
		int N = nodeshs.size();
		
		//initialisation du graph
		adj = new Node [N];
		
		//convertion HashSet toArray String []
		String nodesTab[] = nodeshs.toArray(new String[nodeshs.size()]);
		
		//Initialisation des nodes
		for (int i = 0; i < nodesTab.length; i++) {
			adj[i] = new Node();
			adj[i].nodeId = Integer.parseInt(nodesTab[i]);
			adj[i].listDirectedEdge = new ArrayList<DirectedEdge>();
		}
		return N;
	}
	
	public int addEdgesFromTxt(List<String> listLignes){
		//on parcourt tous les edges du "graph.txt"
		for (int i = 0; i < listLignes.size(); i++) {
		String line = listLignes.get(i);
		//pour chaque edge, on recupere les identifiants 
		//et le poid des nodes
		String directedEdgeData[] = line.split(" ");
		int from = Integer.parseInt(directedEdgeData[0]);
		int to = Integer.parseInt(directedEdgeData[1]);
		double weight = Double.parseDouble(directedEdgeData[2]);

		//on ajout ce directedEdge dans la list du noeud correspondant
		addEdgeToAdj(from, to, weight);
	
		}
		//Un graph de taille M (size)
		int M = listLignes.size();
		return M;
	}
	
	public void addEdgeToAdj(int from, int to, double weight){
		//determination de la position du noeud from dans l'adj 
		//list (tab)
		int startNodePosition = getNodePosition(from);
		//creation de la directed edge avec les donnees
		DirectedEdge diEdge = new DirectedEdge(from, to, weight);
		//ajout de la directed edge creee dans la list des 
		//directed edge du noeud start
		adj[startNodePosition].listDirectedEdge.add(diEdge);
	}
	
	public int getNodePosition(int nodeId) {
		for (int i = 0; i < adj.length; i++) {
			if (adj[i].nodeId == nodeId) {
				return i;
			}
		}
		return -1;
	}
	
	public void affichageAdj(){
		System.out.println("Order : " + N);
		System.out.println("Size : " + M);
		System.out.println("Adjacency list of the weighted digraph :");
		for (int i = 0; i < adj.length; i++) {
			System.out.print(adj[i].nodeId + ": ");
			ArrayList<DirectedEdge> listDiEdge = adj[i].listDirectedEdge;
			for (DirectedEdge diEdge : listDiEdge) {
				System.out.print("[" + diEdge.from());
				System.out.print(", " + diEdge.to());
				System.out.print(", " + diEdge.weight() + "] ");
			}
			
			System.out.println();
		}
	}
}
