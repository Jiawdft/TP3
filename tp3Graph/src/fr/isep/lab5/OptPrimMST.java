package fr.isep.lab5;

import java.util.ArrayList;
import java.util.PriorityQueue;

import fr.isep.lab5.EdgeWeightedGraph.Node;

public class OptPrimMST {
	// initialisation d'un arrayList pour stocker
	// le min spanning trees
	private ArrayList<Vertice> mst;
	
	public class Vertice{
		int verticeId;
		int prev;
		double weight;
	}

	public ArrayList<Vertice> getMst() {
		return mst;
	}
	
	
	// ------------------ besoin amelioration : avec previous node dans la class Vertice ----------
	public void prim(EdgeWeightedGraph wGraph, int s) {
		mst = new ArrayList<>();
		//initiation priorqueue qui stock des Vertice
		PriorityQueue<Vertice> priorqueue = new PriorityQueue<>((Object v1, Object v2) -> {
			Vertice first = (Vertice) v1;
			Vertice second = (Vertice) v2;
			// input graph orderd by theur weight
			if (first.weight < second.weight) {
				return -1;
			} else if (first.weight > second.weight) {
				return 1;
			} else {
				return 0;
			}
		});
		
		
		//initialisation du priorqueue avec 
		//N-1 Vertice avec l'id du noeud 
		//et un weight = oO
		Node[] adj = wGraph.getAdj();
		for (int i = 0; i < adj.length; i++) {
			if (adj[i].nodeId != s) {
				Vertice v = new Vertice();
				v.verticeId = adj[i].nodeId;
				v.weight = Double.MAX_VALUE;
				v.prev = -1;
				priorqueue.add(v);
			}
		}
		
		int tempNode = s;
		while(!priorqueue.isEmpty()){
			int tempNodePosition = wGraph.getNodePosition(tempNode, adj);
			//maj des weight pour tous les noeuds voisin de tempNode
			for (Edge e : adj[tempNodePosition].listWedge) {
				for (Vertice v : priorqueue) {
					int to = -1;
					if (e.either() == tempNode) {
						to = e.other();
					}else {
						to = e.either();
					}
					
					if (to == v.verticeId && e.weight() < v.weight) {
						v.weight = e.weight();
						v.prev = tempNode;
						break;
					}
				}
			}
			Vertice reorder = priorqueue.poll();
			priorqueue.add(reorder);
			tempNode = priorqueue.peek().verticeId;
			mst.add(priorqueue.poll());			
		}
		
	}
	
	public void edges(ArrayList<Vertice> mst) {
		// affichage du MST
		System.out.println("MST :");
		for (Vertice v : mst) {
			System.out.print("[" + v.prev);
			System.out.print(", " + v.verticeId);
			System.out.print(", " + v.weight + "]");
			System.out.println();
		}
	}
	
	public double weight(ArrayList<Vertice> mst){
		double totalWeight = 0.0;
		for (Vertice v : mst) {
			totalWeight = totalWeight + v.weight;
		}
		return totalWeight;
	}
}
