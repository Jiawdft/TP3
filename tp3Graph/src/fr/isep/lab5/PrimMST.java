package fr.isep.lab5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PrimMST {
	// initialisation d'un arrayList pour stocker
	// le min spanning trees
	private ArrayList<Edge> mst;
	
	
	public ArrayList<Edge> getMst() {
		return mst;
	}

	public void prim(EdgeWeightedGraph wGraph, int s) {

		mst = new ArrayList<>();
		// initialisation d'une liste pour stocker les nodes visited
		Set<Integer> visited = new HashSet<>();

		// initialisation d'un priorityQueue to store all edges of the
		// input graph orderd by theur weight
		// (avec comparaison des object edge)
		PriorityQueue<Edge> priorqueue = new PriorityQueue<>((Object e1, Object e2) -> {
			Edge first = (Edge) e1;
			Edge second = (Edge) e2;
			// input graph orderd by theur weight
			if (first.getWeight() < second.getWeight()) {
				return -1;
			} else if (first.getWeight() > second.getWeight()) {
				return 1;
			} else {
				return 0;
			}
		});

		// marque visited le noeud de depart s
		visited.add(s);
		int tempNode = s;

		while (mst.size() < wGraph.N - 1) {
			int tempNodePosition = wGraph.getNodePosition(tempNode, wGraph.getAdj());
			// ajout des edges du node s dans la priorqueue
			for (Edge e : wGraph.getAdj()[tempNodePosition].listWedge) {
				priorqueue.add(e);
			}

			while (true) {
				// si priorquue devient vide il y a alors une
				// anormalie, code retour -1
				if (priorqueue.isEmpty()) {
					System.exit(-1);
				}

				// si les noeuds from et to du edge sont deja visited
				// alors on l'enleve du priorqueue
				// sinon on l'ajoute dans mst et on marque ses noeuds
				// dans visited et on remplace tempNode
				// par le dern node visited
				if (visited.contains(priorqueue.peek().either()) && visited.contains(priorqueue.peek().other())) {
					priorqueue.remove();
				} else {
					mst.add(priorqueue.peek());
					if (visited.contains(priorqueue.peek().other())) {
						visited.add(priorqueue.peek().either());
						tempNode = priorqueue.peek().either();
					} else {
						visited.add(priorqueue.peek().other());
						tempNode = priorqueue.peek().other();
					}
					break;
				}
			}
		}
	}

	public void edges(ArrayList<Edge> mst) {
		// affichage du MST
		System.out.println("MST :");
		for (Edge e : mst) {
			System.out.print("[" + e.either());
			System.out.print(", " + e.other());
			System.out.print(", " + e.weight() + "]");
			System.out.println();
		}
	}
	
	public double weight(ArrayList<Edge> mst){
		double totalWeight = 0.0;
		for (Edge e : mst) {
			totalWeight = totalWeight + e.weight();
		}
		return totalWeight;
	}
}
