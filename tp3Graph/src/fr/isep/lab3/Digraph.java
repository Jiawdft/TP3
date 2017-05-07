package fr.isep.lab3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;



public class Digraph {
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
		
		public Digraph(String filePath){
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
			}
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
			System.out.println("Adjacency list of a weighted digraph :");
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
}
