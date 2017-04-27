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
		//initialisation des tableaux
		nodes = new int [taille];
		marked = new boolean [taille];
		previous = new int [taille];
		distance = new int [taille];
		//initialisation des valeurs par defaut dans les tableau
		for (int i = 0; i < taille; i++) {
			nodes[i] = adj[i].nodeId;
			marked[i] = false;
			previous[i] = -2;
			distance[i] = Integer.MAX_VALUE;
		}
		//chercher la position du noeud root
		int startingNodePosition = G.getNodePosition(s);
		
		//initialisation queue
		Queue<Integer> queue = new LinkedList<>(); 
		//ajout du noeud root dans queue
		queue.add(s);
		//initialisation du valeur distance pour le noeud root a 0
		//du marked a true et du previous a -1
		distance[startingNodePosition] = 0;
		marked[startingNodePosition] = true;
		previous[startingNodePosition] = -1;
		
		//tant que queue est non vide
		while (!queue.isEmpty()) {
			//on regarde pour le dernier element de queue en stockant son id entant que 
			//tempValue et en cherchant ses voisins
			int tempValue = queue.peek();
			int tempValuePosition = G.getNodePosition(tempValue);
			//on eleve un element de queue
			queue.remove();
			//on genere la liste des voisins du noeud en question
			ArrayList<Integer> listVoisin = listVoisin(adj[tempValuePosition]);
			for(int valeur : listVoisin){
				int valeurPosition = G.getNodePosition(valeur);
				//si le voisin n'est pas visited
				if (!marked[valeurPosition]) {
					//on le marked true,on lui affect tempValue comme previous et on 
					//lui affect la distance de tempValue +1 et on ajout ce voisin dans queue
					marked[valeurPosition] = true;
					previous[valeurPosition] = tempValue;
					distance[valeurPosition] = distance[tempValuePosition] + 1;
					queue.add(valeur);
				}
				
			}
		}	
	}
	
	public boolean hadPathTo(int v){
		//on cherche la position du noeud v dans les tableaux 
		//et on retourn la valeur du tableau marked à la position
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] == v) {
				return marked[i];
			}
		}
		return false;
	}
	
	public int distTo(int v){
		//on cherche la position du noeud v dans les tableaux 
		//et on return la valeur du tableau distance à cette position
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] == v) {
				return distance[i];
			}
		}
		return -1;
	}
	
	public void printSp(int v){
		//on cherche la position du noeud v dans les tableaux 
		int vPosition = -1;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] == v) {
				vPosition = i;
				break;
			}
		}
		//si le noeud v est visited et la position diff de -1
		// ie. le noeud v existe
		if (marked[vPosition] && vPosition != -1) {
			//initialisation d'une list pour stocker la path
			ArrayList <Integer> sp = new ArrayList<Integer>();
			//on ajout la destination, ie noeud v
			sp.add(v);
			//on stock la position du noeud v a l'aide de tempPosition
			int tempPosition = vPosition;
			//tant que tempPosition n'est pas le noeud root
			//ie. distance > 0
			while (distance[tempPosition] > 0) {
				//on stock la valeur du tableau previous à la position tempPosition
				// avec une autre valeur temporaire : tempPreviousValue
				int tempPreviousValue = previous[tempPosition];
				//on l'ajout dans la liste path
				sp.add(previous[tempPosition]);
				//on cherche la position du noeud tempPreviousValue 
				//et cette position deviens la nouvelle tempPosition
				for (int i = 0; i < nodes.length; i++) {
					if (nodes[i] == tempPreviousValue) {
						tempPosition = i;
					}
				}
			}
			//on reverse la liste pour avoir root vers destination
			Collections.reverse(sp);
			System.out.println("The shortest path from " + startingNode + " to " + v + " is : " + sp);
		}
		else
		{
			System.out.println("Sorry there is no path from " + startingNode + " to " + v);
		}
		
	}
	
	public int excentricity(){
		//search the distance between root and the most distant vertex
		int maxDistance = 0;
		//on parcoure le tableau distance et on retourne la plus grande value
		//sauf Integer.MAX_VALUE considere comme infinie
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
