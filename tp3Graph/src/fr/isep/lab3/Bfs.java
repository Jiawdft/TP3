package fr.isep.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import fr.isep.lab3.Graph.Edge;
import fr.isep.lab3.Graph.Node;

public class Bfs {
	public ArrayList<Integer> bfs(Graph G, int startingNode){
		ArrayList<Integer> visited = new ArrayList<Integer>();
		Queue<Integer> queue = new LinkedList<>(); 
		 Node [] adj = G.getAdj();
		int startingNodePosition = G.getNodePosition(startingNode);
		visited.add(adj[startingNodePosition].nodeId);
		queue.add(startingNode);
		
		while (!queue.isEmpty()) {
			int queueLastElemPosition = G.getNodePosition(queue.peek());
			ArrayList<Integer> listVoisin = listVoisin(adj[queueLastElemPosition]);
			for(int valeur : listVoisin)
			{
				if (!visited.contains(valeur)) 
				{
					visited.add(valeur);
					queue.add(valeur);
				}
			}
			queue.remove();
		}
		return visited;
	}
	
	//Renvoie les voisins d'un noeud en ordre decroissant 
	public ArrayList<Integer> listVoisin(Node node){
		//Initialisation d'une ArrayList vide
		ArrayList<Integer> listVoisin = new ArrayList<Integer>();
		
		//Initialisation de la boucle avec le 1er edge du noeud
		Edge edge = node.firstEdge; 
		//Ajouter les voisins du noeud dans la list
		while (edge != null) {
			//ajout dans array list
			listVoisin.add(edge.edgeID);
			//next edge
			edge = edge.nextEdge;
		}
		//Trier la list, ordre croissant
		Collections.sort(listVoisin);

		return listVoisin;
	}
}
