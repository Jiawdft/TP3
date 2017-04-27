package fr.isep.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import fr.isep.lab3.Graph.Edge;
import fr.isep.lab3.Graph.Node;

public class Bfs {
	
	public ArrayList<Integer> bfs(Graph G, int startingNode){
		//initialisation d'une liste pour stocker les noeuds visited
		ArrayList<Integer> visited = new ArrayList<Integer>();
		//initialisation de queue
		Queue<Integer> queue = new LinkedList<>(); 
		 Node [] adj = G.getAdj();
		 //chercher la position du noeud de depart dans le tableau adj
		int startingNodePosition = G.getNodePosition(startingNode);
		//ajouter le noeud de depart dans la liste des visited et dans queue
		visited.add(adj[startingNodePosition].nodeId);
		queue.add(startingNode);
		//tant que queue est non vide
		while (!queue.isEmpty()) {
			//on prend le dernier element de queue et on cherche ses voisins
			int queueLastElemPosition = G.getNodePosition(queue.peek());
			ArrayList<Integer> listVoisin = listVoisin(adj[queueLastElemPosition]);
			//pour chaque voisins trouves
			for(int valeur : listVoisin)
			{
				//on regarde s'ils sont visited
				if (!visited.contains(valeur)) 
				{
					//s'ils ne sont pas visited, on l'ajout dans la liste visited et
					//on l'ajout dans queue sinon on passe au voisin suivant
					visited.add(valeur);
					queue.add(valeur);
				}
			}
			//on enleve un element de queue
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
