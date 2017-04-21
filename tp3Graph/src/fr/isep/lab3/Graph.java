package fr.isep.lab3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Graph {
	
//1)-------------------------------------------------------------------------------	
	//une class Node avec un identifiant et un element Edge
	public class Node{
		int nodeId;
		Edge firstEdge;
	}
	//une class Edge avec un identifiant du node voisin et l'element Edge suivant 
	public class Edge{
		int edgeID;
		Edge nextEdge;
	}
	//un tableau de node : adjacency list representation
	private Node[] adj;
	
	// Order N du graph
	private int N;
	
	//Size M du graph
	private int M;
	
	
	public Node[] getAdj() {
		return adj;
	}


	//Initialisation : graph vide d'ordre N
	public Graph(int N){
		this.N = N ;
		this.M = 0;
		
		//initialisation tableau de taille N
		adj = new Node[N];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new Node();
			adj[i].nodeId = i + 1;
			adj[i].firstEdge = null;
		}
	}

	
	//2 & 3)-------------------------------------------------------------------------------		
	//Initialisation graph : a partir de graph.txt
	public Graph(String filePath){
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
		//on utilise HashSet car c'est une collection qui n'accepte pas les doublons
		//Dans nodeshs on va stocker les nodes du graph
		HashSet<String> nodeshs = new HashSet<String>();
		
		//on parcourt tous les edges du "graph.txt"
		for (int i = 0; i < listLignes.size(); i++) {
			String line = listLignes.get(i);
			//pour chaque edge, on recupere les identifiants des nodes
			String nodesId[] = line.split(" ");
			nodeshs.add(nodesId[0]);
			nodeshs.add(nodesId[1]);
		}
		//Un graph d'ordre N (order)
		int N = nodeshs.size();
			
		//initialisation du graph
		adj = new Node[N];
		
		//convertion HashSet toArray String []
		String nodesTab[] = nodeshs.toArray(new String[nodeshs.size()]);
		
		//Initialisation des nodes
		for (int i = 0; i < nodesTab.length; i++) {
			adj[i] = new Node();
			adj[i].nodeId = Integer.parseInt(nodesTab[i]);
			adj[i].firstEdge = null;
		}
		return N;
	}
	
	public int addEdgesFromTxt(List<String> listLignes){
		//on parcourt tous les edges du "graph.txt"
		for (int i = 0; i < listLignes.size(); i++) {
		String line = listLignes.get(i);
		//pour chaque edge, on recupere les identifiants des nodes
		String nodesId[] = line.split(" ");
		int node1Id = Integer.parseInt(nodesId[0]);
		int node2Id = Integer.parseInt(nodesId[1]);

		//Voir 4)
		addEdgeToAdj(node1Id, node2Id);
		}
		//Un graph de taille M (size)
		int M = listLignes.size();
		return M;
	}

	//4)-------------------------------------------------------------------------------	
	public void addEdgeToAdj(int u, int v){
		
		//pour le noeud u
		//Initialisation d'un nouveau element du type Edge avec l'id: u
		Edge edgeU = new Edge();
		edgeU.edgeID = v;
		edgeU.nextEdge = null;
		//dans le cas ou 1er edge du node est null
		int nodePositionU = getNodePosition(u);
		if (adj[nodePositionU].firstEdge == null) {
			adj[nodePositionU].firstEdge = edgeU;
		}else { //dans le cas ou le 1er edge du node est non null
			//initialisation d'un edge temporaire pour stocker les information du 1er edge et des edges suivantes
			Edge edgeTemp = adj[nodePositionU].firstEdge;
			
			//Cibler le dernier edge
			while(edgeTemp.nextEdge != null){
				edgeTemp = edgeTemp.nextEdge;
			}
			edgeTemp.nextEdge = edgeU;
			/*
			//la fonction presence permet de verifier si v est deja voisin de u
			boolean presence = presence(u, v);
			if (presence == false) {
				edgeTemp.nextEdge = edgeU;
			}
			*/
		}
		
		//pour le noeud v
		//Initialisation d'un nouveau element du type Edge avec l'id: v
		Edge edgeV = new Edge();
		edgeV.edgeID = u;
		edgeV.nextEdge = null;
		//dans le cas ou 1er edge du node est null
		int nodePositionV = getNodePosition(v);
		if (adj[nodePositionV].firstEdge == null) {
			adj[nodePositionV].firstEdge = edgeV;
		}else {//dans le cas ou le 1er edge du node est non null
			//initialisation d'un edge temporaire pour stocker les information du 1er edge et des edges suivantes
			Edge edgeTemp = adj[nodePositionV].firstEdge;
			//Cibler le dernier edge
			while(edgeTemp.nextEdge != null){
				edgeTemp = edgeTemp.nextEdge;
			}
			edgeTemp.nextEdge = edgeV;
			/*
			//la fonction presence permet de verifier si u est deja voisin de v
			boolean presence = presence(v, u);
			if (presence == false) {
				edgeTemp.nextEdge = edgeV;
			}	
			*/
		}
	}
	
	public boolean presence(int v, int recherche){
		boolean presence = false;
		int nodePosition = getNodePosition(v);
		Edge edgeTemp = adj[nodePosition].firstEdge;
		while(edgeTemp != null){
			if (edgeTemp.edgeID == recherche) {
				presence = true;
			}
			edgeTemp = edgeTemp.nextEdge;
		}
		return presence;
	}
	
	private int getNodePosition(int nodeId) {
		for (int i = 0; i < adj.length; i++) {
			if (adj[i].nodeId == nodeId) {
				return i;
			}
		}
		return -1;
	}
	
	//5)-------------------------------------------------------------------------------	
	public void neighbors(int v){
		System.out.print("Pour le vertex " + v + " les voisins sont : ");
		int nodePosition = getNodePosition(v);
		Edge edgeTemp = adj[nodePosition].firstEdge;
		while(edgeTemp != null){
			System.out.print(edgeTemp.edgeID + ", ");
			edgeTemp = edgeTemp.nextEdge;
		}
		System.out.println();
	}
	
	
	
	//6)-------------------------------------------------------------------------------

	public void affichageAdj(){
		System.out.println("Order : " + N);
		System.out.println("Size : " + M);
		System.out.println("Adjacency list :");
		for (int i = 0; i < adj.length; i++) {
			System.out.print(adj[i].nodeId + ": ");
			Edge edge = adj[i].firstEdge;
			while (edge != null) {
				System.out.print(edge.edgeID + ", ");
				edge = edge.nextEdge;
			}
			System.out.println();
		}
	}
	//-------------------------------------------------------------------------------
	public int degree(int v){
		int compteur = 0;
		int nodePosition = getNodePosition(v);
		Edge edgeTemp = adj[nodePosition].firstEdge;
		while(edgeTemp != null){
			compteur++;
			edgeTemp = edgeTemp.nextEdge;
		}
		return compteur;
	}
	
	public int averageDegree(){
		int averageDegree = 2 * M / N;
		return averageDegree;
	}
	
	public int minimumDegree(){
		int minDegree = Integer.MAX_VALUE;
		for (Node node: adj) {
			int degreeTemp = degree(node.nodeId);
			if (degreeTemp < minDegree) {
				minDegree = degreeTemp;
			}
		}
		return minDegree;
	}
	
	public int maximumDegree(){
		int maxDegree = 0;
		for (Node node: adj) {
			int degreeTemp = degree(node.nodeId);
			if (degreeTemp > maxDegree) {
				maxDegree = degreeTemp;
			}
		}
		return maxDegree;
	}
	
	public double density(){
		return 2.0 * M / (N * N);
	}
	
	//-------------------------------------------------------------------------------
	public Graph(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of vertices:");
		N = scan.nextInt();
		adj = new Node[N];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new Node();
			adj[i].nodeId = i + 1;
			adj[i].firstEdge = null;
		}
		System.out.println("Enter the number of edges:");
		M = scan.nextInt();
		scan.nextLine();
				
		for (int i = 0; i < M; i++) {
			System.out.println("Enter the edges in the graph : <to> <from>");
			String ligne = scan.nextLine();
			String [] nodes = ligne.split(" ");
			int node1 = Integer.parseInt(nodes[0]);
			int node2 = Integer.parseInt(nodes[1]);
			addEdgeToAdj(node1, node2);
			
		}
		scan.close();
	}
	
	
	
}
