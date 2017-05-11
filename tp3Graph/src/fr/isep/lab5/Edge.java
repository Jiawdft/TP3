package fr.isep.lab5;



public class Edge {
	
	private final int v;
	private final int w;
	private final double weight;
	
	public int either() {
		return v;
	}
	public int other() {
		return w;
	}
	public double weight() {
		return weight;
	}
	
	public Edge(int v, int w, double weight) {
		super();
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	
	
}
