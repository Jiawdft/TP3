package fr.isep.lab3and4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import fr.isep.lab3and4.WDgraph.Node;

public class DijkstraSP {
	private int [] nodes;
	private boolean [] marked;
	private int [] previous;
	private double [] distance;
	
	public boolean verifyNonNegative(WDgraph G){
		//initiation d'un boolean
		//return false si all weights are non negative
		//and true if there are at least one negative weight
		
		Node [] adj = G.getAdj();
		//Parcourir tous les noeuds du graph
		for (int i = 0; i < adj.length; i++) {
			ArrayList<DirectedEdge> listDiEdge = adj[i].listDirectedEdge;
			//Parcourir tous les weighted edge du noeud
			for (DirectedEdge diEdge : listDiEdge) {
				//des qu'un weight est negative, affect true a isNegative
				//et stop le boucle
				if (diEdge.weight() < 0.0) {
					return true;
				}
			}
		}
		return false;
		
	}

	public ArrayList<Integer> dijkstraSP(WDgraph G, int s){
		
		//le dijkstraSP fonctionne seulement avec des 
		//weight non negative
		//on arrete donc le programme lorsqu'on detecte
		//des weight negative et on returne un code retour -2
		boolean isNegative = verifyNonNegative(G);
		if (isNegative) {
			System.exit(-2);
		}
		
		Node [] adj = G.getAdj();
		//init des tableaux
		nodes = new int [adj.length];
		marked = new boolean [adj.length];
		previous = new int [adj.length];
		distance = new double [adj.length];
		//Set pour stocker les noeuds non visited
		HashSet<Integer> unseattled = new HashSet<Integer>();
		//Array pour stocker les noeuds visited 
		//ainsi que l'ordre de visite
		ArrayList<Integer> seattled = new ArrayList<Integer>();
		//pour tous les noeuds 
		//Set distance of node i as D(i) to infinite
		for (int i = 0; i < adj.length; i++) {
			nodes[i] =adj[i].nodeId;
			//on considere Integer.MAX_VALUE = infini
			distance[i] = Integer.MAX_VALUE; 
			//on considere -1 comme ensemble vide
			previous[i] = -1;
			//tous met tous les noeuds dans unseattled
			unseattled.add(adj[i].nodeId);
		}


		int startPosition = G.getNodePosition(s);
		//distance source
		distance[startPosition] = 0;
		//marque source
		marked[startPosition] = true;
		
		while(!unseattled.isEmpty()){
			double minDistance = Integer.MAX_VALUE;
			int minNode = -1;
			int minNodePosition = -1;
			for(int thisNode : unseattled){
				int thisNodePosition = G.getNodePosition(thisNode);
				if (distance[thisNodePosition] < minDistance) {
					minDistance = distance[thisNodePosition];
					minNode = thisNode;
					minNodePosition = thisNodePosition;
				}
			}
			//minNode == -1 signifie que le graph est disconnected
			//on arrete donc la boucle while
			if (minNode == -1) {
				break;
			}
			unseattled.remove(minNode);
			seattled.add(minNode);
			//mettre a jour les tableaux : distances previous et marked
			for (DirectedEdge diEdge : adj[minNodePosition].listDirectedEdge) {
				int toPosition = G.getNodePosition(diEdge.to());
				double tempDistance = minDistance +diEdge.weight();
				if (tempDistance < distance[toPosition]) {
					distance[toPosition] = tempDistance;
					marked[toPosition] = true;
					previous[toPosition] = minNode;
				}
			}
		}
		return seattled;
	}
	
	public boolean hasPathTo(int v){
		int vPosition = -1;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] == v) {
				vPosition = i;
			}
		}
		
		if (vPosition == -1) {
			return false;
		}
		
		return marked[vPosition];
	}
	
	public double distTo(int v){
		int vPosition = -1;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] == v) {
				vPosition = i;
			}
		}
		
		if (vPosition == -1) {
			return -2;
		}
		
		return distance[vPosition];
	}
	
	public void printSP(int v){
		ArrayList<Integer> shortestPath = new ArrayList<Integer>();
		int vPosition = -2;
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] == v) {
				vPosition = i;
			}
		}
		if (vPosition == -2) {
			System.exit(-3);
		}
		int tempValue = v; 
		int tempValuePosition = vPosition;
		while (tempValue > -1) {
			shortestPath.add(tempValue);
			tempValue = previous[tempValuePosition];
			for (int i = 0; i < nodes.length; i++) {
				if (nodes[i] == tempValue) {
					tempValuePosition = i;
				}
			}
		}
		Collections.reverse(shortestPath);
		System.out.println(shortestPath);
	}
}
