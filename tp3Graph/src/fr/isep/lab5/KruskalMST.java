package fr.isep.lab5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import fr.isep.lab5.EdgeWeightedGraph.Node;

public class KruskalMST {

	// initialisation d'un arrayList pour stocker
	// le min spanning trees
	private ArrayList<Edge> mst;

	// list contenant les subSet de l'algo
	private ArrayList<Set<Integer>> listSet;

	// fonction pour trouver dans quel subSet se trouve un noeud
	public int find(ArrayList<Set<Integer>> listSet, int target) {
		for (int i = 0; i < listSet.size(); i++) {
			if (listSet.get(i).contains(target)) {
				return i;
			}
		}
		return -1;
	}

	// union de deux subSet
	public void union(ArrayList<Set<Integer>> listSet, int vPosition, int wPosition) {
		Set<Integer> vSet = listSet.get(vPosition);
		Set<Integer> wSet = listSet.get(wPosition);
		vSet.addAll(wSet);
		listSet.remove(wPosition);
	}

	public ArrayList<Edge> getMst() {
		return mst;
	}

	public void kruskal(EdgeWeightedGraph G) {
		mst = new ArrayList<Edge>();

		// priority queue pour stocker tous les edges in order by weight
		PriorityQueue<Edge> pq = new PriorityQueue<>((Object e1, Object e2) -> {
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
		// pour eviter les redondances
		Node[] adj = G.getAdj();
		for (Node n : adj) {
			for (Edge e : n.listWedge) {
				if (!pq.contains(e)) {
					pq.add(e);
				}
			}
		}

		// union - find data structure : avec des set
		listSet = new ArrayList<>();

		// creation d'un subSet pour chaque noeuds du graph
		for (Node n : adj) {
			Set<Integer> subSet = new HashSet<Integer>();
			subSet.add(n.nodeId);
			listSet.add(subSet);
		}

		while (mst.size() < G.N - 1) {
			Edge minWeightEdge = pq.poll();
			int vPosition = find(listSet, minWeightEdge.getV());
			int wPosition = find(listSet, minWeightEdge.getW());

			// regarder si les deux vertices sont dans le meme subset
			if (vPosition != wPosition) {
				union(listSet, vPosition, wPosition);
				mst.add(minWeightEdge);
			}
			// else on fait rien, on regarde pour le prochain edge
			// du priorityqueue

		}
	}

	public void edges(ArrayList<Edge> mst) {
		System.out.println("MST :");
		for (Edge e : mst) {
			System.out.print("[" + e.either());
			System.out.print(", " + e.other());
			System.out.print(", " + e.weight() + "]");
			System.out.println();
		}
	}

	public double weight(ArrayList<Edge> mst) {
		double totalWeight = 0.0;
		for (Edge e : mst) {
			totalWeight = totalWeight + e.weight();
		}
		return totalWeight;
	}

}
