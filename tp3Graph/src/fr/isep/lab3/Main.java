package fr.isep.lab3;


public class Main {

	public static void main(String[] args) {

			GraphV2 test = new GraphV2("graph.txt");
			test.affichageAdj();
			test.neighbors(1);

	}
}
