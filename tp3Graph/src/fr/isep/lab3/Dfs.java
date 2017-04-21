package fr.isep.lab3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import fr.isep.lab3.Graph.Edge;
import fr.isep.lab3.Graph.Node;

public class Dfs {
	
	public void dfs(Graph G){
		
		//Un set contenant les identifiants des noeuds visités dans l'ordre 
		Set<Integer> visited = new HashSet<Integer>();
	
		//Stack pour la recherche DFS
		Stack<Integer> stack = new Stack();
		
		//ajout de la permiere valeur dans visited et le stack
		Node[] adj = G.getAdj();
		visited.add(adj[0].nodeId);
		stack.push(adj[0].nodeId);
		
		while(!stack.empty()){
			int stackLastElem = stack.peek();
			Stack stackVoisin = stackVoisin(adj[stackLastElem]);
			for (int i = 0; i < stackVoisin.size(); i++) 
			{
				if (!visited.contains(stackVoisin.peek())) 
				{
					visited.add((Integer) stackVoisin.peek());
					stack.push((Integer) stackVoisin.peek());
					break;
				}
				else
				{
					stackVoisin.pop();
				}
				//3e possibilité quand les voisins sont tous visited 
			}
		}

	}
	
	public Stack<Integer> stackVoisin(Node node){
		ArrayList<Integer> listVoisin = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		Edge edge = node.firstEdge;
		while (edge != null) {
			listVoisin.add(edge.edgeID);
			edge = edge.nextEdge;
		}
		
		Collections.sort(listVoisin);
		Collections.reverse(listVoisin);
		for (int valeur : listVoisin) {
			stack.push(valeur);
		}
		return stack;
	}

}
