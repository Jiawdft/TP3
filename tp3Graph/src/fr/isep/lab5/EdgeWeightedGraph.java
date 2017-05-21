package fr.isep.lab5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class EdgeWeightedGraph {
	// Order N du undirected weighted graph
	public int N;
	// Size M du undirected weighted graph
	public int M;

	// une class Node avec un identifiant et une liste de DirectedEdge
	public class Node {
		int nodeId;
		ArrayList<Edge> listWedge;
	}

	// init adj list (tab)
	Node[] adj;

	// getter de adj list
	public Node[] getAdj() {
		return adj;
	}

	public EdgeWeightedGraph(String filePath) {
		try {
			List<String> listLignes = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
			N = addNodesFromTxt(listLignes);
			M = addEdgesFromTxt(listLignes);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ajout des noeuds dans graoh à partir du fichier txt (source)
	public int addNodesFromTxt(List<String> listLignes) {
		// un node est parfois present dans plusieurs edges,
		// on utilise HashSet car c'est une collection qui
		// n'accepte pas les doublons
		// Dans nodeshs on va stocker les nodes du graph
		HashSet<String> nodeshs = new HashSet<String>();

		// on parcourt tous les edges du "graph.txt"
		for (int i = 0; i < listLignes.size(); i++) {
			String line = listLignes.get(i);
			// pour chaque edge, on recupere les identifiants des nodes
			String directedEdgeData[] = line.split(" ");
			nodeshs.add(directedEdgeData[0]);
			nodeshs.add(directedEdgeData[1]);
		}
		// Un graph d'ordre N (order)
		int N = nodeshs.size();

		// initialisation du graph
		adj = new Node[N];

		// convertion HashSet toArray String []
		String nodesTab[] = nodeshs.toArray(new String[nodeshs.size()]);

		// Initialisation des nodes
		for (int i = 0; i < nodesTab.length; i++) {
			adj[i] = new Node();
			adj[i].nodeId = Integer.parseInt(nodesTab[i]);
			adj[i].listWedge = new ArrayList<Edge>();
		}
		// return l'ordre du undirected weighted graph
		return N;
	}

	// lecture des donnees et identification des donnees
	public int addEdgesFromTxt(List<String> listLignes) {
		// on parcourt tous les edges du "graph.txt"
		for (int i = 0; i < listLignes.size(); i++) {
			String line = listLignes.get(i);

			// pour chaque edge, on recupere les identifiants
			// et le poid des nodes
			String directedEdgeData[] = line.split(" ");
			int either = Integer.parseInt(directedEdgeData[0]);
			int other = Integer.parseInt(directedEdgeData[1]);
			double weight = Double.parseDouble(directedEdgeData[2]);

			// on ajout ce undirected weighted Edge dans la list du noeud
			// correspondant
			addEdgeToAdj(either, other, weight);

		}
		// Un graph de taille M (size)
		int M = listLignes.size();
		return M;
	}

	public void addEdgeToAdj(int either, int other, double weight) {
		
		// creation de la directed edge avec les donnees
				Edge wEdge = new Edge(either, other, weight);
		
		// -----------------Noeud either--------------------------------------
		// determination de la position du noeud from dans l'adj
		// list (tab)
		int eitherNodePosition = getNodePosition(either, adj);
		// ajout de la directed edge creee dans la list des
		// directed edge du noeud start
		adj[eitherNodePosition].listWedge.add(wEdge);

		// -----------------Noeud other--------------------------------------
		// determination de la position du noeud from dans l'adj
		// list (tab)
		int otherNodePosition = getNodePosition(other, adj);
		// ajout de la directed edge creee dans la list des
		// directed edge du noeud start
		adj[otherNodePosition].listWedge.add(wEdge);
	}

	public int getNodePosition(int nodeId, Node[] adj) {
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
		System.out.println("Adjacency list of the undirected weighted graph :");
		for (int i = 0; i < adj.length; i++) {
			System.out.print(adj[i].nodeId + ": ");
			ArrayList<Edge> listWEdge = adj[i].listWedge;
			for (Edge wEdge : listWEdge) {
				System.out.print("[" + wEdge.either());
				System.out.print(", " + wEdge.other());
				System.out.print(", " + wEdge.weight() + "] ");
			}
			
			System.out.println();
		}
	}
}
