package fr.isep.lab3;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

public class GraphAdjMatrix {
	private final int N;
	private int M;
	private boolean [][] adj;
	
	//1)-----------------------------------------------------------------------------	
	public GraphAdjMatrix(int N){
		this.N = N;
		this.M = 0;
		
		adj = new boolean[N][N];
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[0].length; j++) {
				adj[i][j] = false;
			}
		}
	}

	//2)-----------------------------------------------------------------------------	
	public GraphAdjMatrix(String filePath) throws IOException{
		
			List<String> listLignes = Files.readAllLines(Paths.get(filePath),
					StandardCharsets.UTF_8);
			N = addNodesFromTxt(listLignes);
			M = addEdgesFromTxt(listLignes);
			
	}
	
	public int addNodesFromTxt(List<String> listLignes){
		//un node est parfois present dans plusieurs edges,
		//on utilise HashSet car c'est une collection qui n'accepte pas les doublons
		//Dans nodeshs on va stocker les nodes du graph
		HashSet<String> nodeshs = new HashSet<String>();
		
		//on parcourt tous les edges du "graph.txt"
		for (int i = 0; i < listLignes.size(); i++) {
			String line = listLignes.get(i);
			//pour chaque edge, on recupere les identifiants des nodes
			String nodesId[] = line.split(" ");
			nodeshs.add(nodesId[0]);
			nodeshs.add(nodesId[1]);
		}
		//Un graph d'ordre N (order)
		int N = nodeshs.size();
					
		//initialisation d'une matrice carre de taille n
		adj = new boolean[N][N];
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[0].length; j++) {
				adj[i][j] = false;
			}
		}
		return N;
	}
	
	public int addEdgesFromTxt(List<String> listLignes) {
		//on parcourt tous les edges du "graph.txt"
				for (int i = 0; i < listLignes.size(); i++) {
				String line = listLignes.get(i);
				//pour chaque edge, on recupere les identifiants des nodes
				String nodesId[] = line.split(" ");
				int node1Id = Integer.parseInt(nodesId[0]);
				int node2Id = Integer.parseInt(nodesId[1]);
				adj[node1Id - 1][node2Id- 1] = true;
				adj[node2Id- 1][node1Id- 1] = true;
				}
				
				//Un graph de taille M (size)
				int M = listLignes.size();
				return M;
	}
	
	//Affichage-----------------------------------------------------------------------------
	public void affichage(){
		System.out.println("Order : " + N);
		System.out.println("Size : " + M);
		System.out.println("Adjacency Matrix :");
		for (int i = 0; i < adj.length; i++) {
			for (int j = 0; j < adj[0].length; j++) {
				if (adj[i][j] == true) {
					System.out.print(1 + " ");
				}else {
					System.out.print(0 + " ");
				}
			}
			System.out.println();
		}
	}


}
