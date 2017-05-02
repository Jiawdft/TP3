package fr.isep.lab3;

public class DirectedEdge {
	private final int v; 
	private final int w;
	private final double weight;
	
	public DirectedEdge(){
		v = 0;
		w = 0;
		weight = 0.0;
	}
	
	public DirectedEdge(int v, int w, double weight) {
		super();
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int from(){
		return v;
	}
	public int to(){
		return w;
	}
	public double weight(){
		return weight;
	}
	
}
