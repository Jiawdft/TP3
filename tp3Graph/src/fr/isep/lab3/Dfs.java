package fr.isep.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import fr.isep.lab3.Graph.Edge;
import fr.isep.lab3.Graph.Node;

public class Dfs {
	
	public void dfs(Graph G, int startingNode){
		
		//Un set contenant les identifiants des noeuds visités dans l'ordre 
		Set<Integer> visited = new HashSet<Integer>();
	
		//Stack pour la recherche DFS
		Stack<Integer> stack = new Stack<Integer>();
		
		//ajout de la permiere valeur dans visited et le stack
		Node[] adj = G.getAdj();
		visited.add(adj[0].nodeId);
		stack.push(adj[0].nodeId);
		
		while(!stack.empty()){
			int stackLastElem = stack.peek();
			Stack<Integer> stackVoisin = stackVoisin(adj[stackLastElem]);
			while (!stackVoisin.empty()) {
				
			}
		}

	}
	
	//Creation stack des voisins d'un noeud en ordre decroissant 
	public Stack<Integer> stackVoisin(Node node){
		//Initialisation d'une ArrayList vide
		ArrayList<Integer> listVoisin = new ArrayList<Integer>();
		//Initialisation d'un Stack vide
		Stack<Integer> stack = new Stack<Integer>();
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
		//Trier en ordre decroissant 
		Collections.reverse(listVoisin);
		//Remplissage du stack du plus grand valeur au plus petit
		for (int valeur : listVoisin) {
			stack.push(valeur);
		}
		return stack;
	}

}
