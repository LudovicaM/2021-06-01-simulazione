package it.polito.tdp.genes.model;

public class Event implements Comparable<Event>{
	
	private int T; //tempo
	private int nIng; //numero ingegnere
	
	public Event(int t, int nIng) {
		T = t;
		this.nIng = nIng;
	}

	public int getT() {
		return T;
	}

	public int getnIng() {
		return nIng;
	}

	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.T-o.T;
	}

	
}
