package fr.isep.lab3;


public class Main {

	public static void main(String[] args) {

			Graph test = new Graph("graph.txt");
			test.affichageAdj();

			
			Graph toto = new Graph();
			toto.affichageAdj();
			toto.neighbors(1);
			

	}
}
