package fr.isep.lab3and4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import fr.isep.lab3and4.Graph.Edge;
import fr.isep.lab3and4.Graph.Node;

public class Dfs {
	
	public ArrayList<Integer> dfs(Graph G, int startingNode){
		
		//Un ArrayList contenant les identifiants des noeuds visite dans l'ordre 
		ArrayList<Integer> visited = new ArrayList<Integer>();
		//Stack pour la recherche DFS
		Stack<Integer> stack = new Stack<Integer>();
		Node[] adj = G.getAdj();
		//rechercher la position du noeud dans le tableau adj
		int startingPosition = G.getNodePosition(startingNode);
		//ajout de la permiere valeur dans visited et le stack
		visited.add(adj[startingPosition].nodeId);
		stack.push(adj[startingPosition].nodeId);
		//tant que le stack est non vide
		while(!stack.empty()){
			//je regarde les voisins pour le dernier element du stack
			int stackLastElemPosition = G.getNodePosition(stack.peek());
			Stack<Integer> stackVoisin = stackVoisin(adj[stackLastElemPosition]);
			//tant que le stack contenant les voisins est non vide je regarde s'ils sont visited
			while (!stackVoisin.empty()) {
				//si le voisin avec le plus petit id n'est visited
				if (!visited.contains(stackVoisin.peek())) {
					//le voisin est ajouter dans la liste des visited et ajouter dans le stack, 
					//puis on arrete la boucle while
					visited.add(stackVoisin.peek());
					stack.push(stackVoisin.peek());
					break;
				}
				else
				{
					//sinon on enleve le dernier element du stack des voisins
					stackVoisin.pop();
				}
			}
			//si le stack des voisins s'est vid� cela signefie que pour le noeud en question 
			//tous les voisins ont d�j� �t� visit�
			//donc on l'enleve du stack
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
		//List pour stocker tous les noeuds visited
		ArrayList<Integer> visited = new ArrayList<Integer>();
		//List contenant tous les component
		ArrayList<ArrayList<Integer>> componentList = new ArrayList<ArrayList<Integer>>();
		Node[] adj = G.getAdj();
		//Pour chaque noeud
		for (int i = 0; i < adj.length; i++) {
			//je verifie s'il sont visited
			if (!visited.contains(adj[i].nodeId)) {
				//s'ils sont non visited je realise une dfs en partant de ce noeud
				ArrayList<Integer> component = dfs(G, adj[i].nodeId);
				//j'ajoute tous les noeuds visited lors du dfs dans la liste visited
				componentList.add(component);
				//j'ajoute aussi la liste du resultat issue du dfs dans la liste des components
				visited.addAll(component);
			}
		}
		return componentList.size();
	}
	
	public boolean isConnected(Graph G){
		//je cherche le nombre de component
		int nombreDeComponent = cc(G);
		//si le graph possede 1 component alors il est connected sinon le graph est no connected
		if (nombreDeComponent == 1) {
			return true;
		}
		else
		{
			return false;
		}
	}

}
