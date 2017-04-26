package fr.isep.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import fr.isep.lab3.Graph.Edge;
import fr.isep.lab3.Graph.Node;

public class Dfs {
	
	public ArrayList<Integer> dfs(Graph G, int startingNode){
		
		//Un ArrayList contenant les identifiants des noeuds visités dans l'ordre 
		ArrayList<Integer> visited = new ArrayList<Integer>();
	
		//Stack pour la recherche DFS
		Stack<Integer> stack = new Stack<Integer>();
		
		//ajout de la permiere valeur dans visited et le stack
		Node[] adj = G.getAdj();
		int startingPosition = G.getNodePosition(startingNode);
		visited.add(adj[startingPosition].nodeId);
		stack.push(adj[startingPosition].nodeId);
		
		while(!stack.empty()){
			int stackLastElemPosition = G.getNodePosition(stack.peek());
			Stack<Integer> stackVoisin = stackVoisin(adj[stackLastElemPosition]);

			while (!stackVoisin.empty()) {
				if (!visited.contains(stackVoisin.peek())) {
					visited.add(stackVoisin.peek());
					stack.push(stackVoisin.peek());
					break;
				}
				else
				{
					stackVoisin.pop();
				}
			}
			if (stackVoisin.empty()) {
				stack.pop();
			}
		}
		return visited;

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
	
	
	public int cc(Graph G){
		ArrayList<Integer> visited = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> componentList = new ArrayList<ArrayList<Integer>>();
		Node[] adj = G.getAdj();
		for (int i = 0; i < adj.length; i++) {
			if (!visited.contains(adj[i].nodeId)) {
				ArrayList<Integer> component = dfs(G, adj[i].nodeId);
				componentList.add(component);
				visited.addAll(component);
			}
		}
		return componentList.size();
	}
	
	public boolean isConnected(Graph G){
		int nombreDeComponent = cc(G);
		if (nombreDeComponent == 1) {
			return true;
		}
		else
		{
			return false;
		}
	}

}
