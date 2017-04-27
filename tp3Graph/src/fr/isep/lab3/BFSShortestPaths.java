package fr.isep.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import fr.isep.lab3.Digraph.Node;
import fr.isep.lab3.Digraph.Edge;

public class BFSShortestPaths {
	private int startingNode;
	private int [] nodes;
	private boolean[] marked;
	private int[] previous;
	private int[] distance;
	
	public int[] getNodes() {
		return nodes;
	}

	public boolean[] getMarked() {
		return marked;
	}


	public int[] getPrevious() {
		return previous;
	}


	public int[] getDistance() {
		return distance;
	}


	public void bfs(Digraph G, int s){
		startingNode = s;
		Node[] adj = G.getAdj();
		int taille = adj.length;
		nodes = new int [taille];
		marked = new boolean [taille];
		previous = new int [taille];
		distance = new int [taille];
	
		for (int i = 0; i < taille; i++) {
			
			nodes[i] = adj[i].nodeId;
			marked[i] = false;
			previous[i] = -2;
			distance[i] = Integer.MAX_VALUE;
		}
		
		int startingNodePosition = G.getNodePosition(s);
		distance[startingNodePosition] = 0;
		Queue<Integer> queue = new LinkedList<>(); 
		queue.add(s);
		marked[startingNodePosition] = true;
		previous[startingNodePosition] = -1;
		
		while (!queue.isEmpty()) {
			int tempValue = queue.peek();
			int tempValuePosition = G.getNodePosition(tempValue);
			queue.remove();
			
			ArrayList<Integer> listVoisin = listVoisin(adj[tempValuePosition]);
			for(int valeur : listVoisin){
				int valeurPosition = G.getNodePosition(valeur);
				if (!marked[valeurPosition]) {
					marked[valeurPosition] = true;
					previous[valeurPosition] = tempValue;
					distance[valeurPosition] = distance[tempValuePosition] + 1;
					queue.add(valeur);
				}
				
			}
		}	
	}
	
	public boolean hadPathTo(int v){
	
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] == v) {
				return marked[i];
			}
		}
		return false;
	}
	
	public int distTo(int v){
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] == v) {
				return distance[i];
			}
		}
		return -1;
	}
	public void printSp(int v){
		int vPosition = -1;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] == v) {
				vPosition = i;
				break;
			}
		}
		if (marked[vPosition] && vPosition != -1) {
			ArrayList <Integer> sp = new ArrayList<Integer>();
			sp.add(v);
			int tempPosition = vPosition;
			while (distance[tempPosition] > 0) {
				int tempPreviousValue = previous[tempPosition];
				sp.add(previous[tempPosition]);
				for (int i = 0; i < nodes.length; i++) {
					if (nodes[i] == tempPreviousValue) {
						tempPosition = i;
					}
				}
			}
			Collections.reverse(sp);
			System.out.println("The shortest path from " + startingNode + " to " + v + " is : " + sp);
		}
		else
		{
			System.out.println("Sorry there is no path from " + startingNode + " to " + v);
		}
		
	}
	
	public int excentricity(){
		int maxDistance = 0;
		for (int i = 0; i < distance.length; i++) {
			if (distance[i] != Integer.MAX_VALUE && distance[i] > maxDistance) {
				maxDistance = distance[i];
			}
		}
		return maxDistance;
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
		
	public void displayNodeTab (int[] nodes){
		for (int valeur : nodes) {
			System.out.print(valeur + ", ");
		}
		System.out.println();
	}
	
	public void displayMarkedTab(boolean[] marked){
		for (Object valeur : marked) {
			System.out.print(valeur + ", ");
		}
		System.out.println();
	}
	public void displayDistanceTab(int[] tab){
		for (int i = 0; i < tab.length; i++) {
			if (marked[i]) {
				System.out.print(tab[i] + ", ");
			}
			else
			{
				System.out.print("NoPath, ");
			}
		}
	
		System.out.println();
	}
	public void displayPreviousTab(int[] tab){
		for (int i = 0; i < tab.length; i++) {
			if (marked[i]) {
				if (previous[i] == -1) {
					System.out.print("StartingNode, ");
				}else {
					System.out.print(previous[i] + ", ");
				}
			}
			else
			{
				System.out.print("NoPath, ");
			}
		}
		System.out.println();
	}
	public void displayNode (Node [] adj){
		for (int i = 0; i < adj.length; i++) {
			System.out.print(adj[i].nodeId + ", ");
		}
		System.out.println();
	}
}
