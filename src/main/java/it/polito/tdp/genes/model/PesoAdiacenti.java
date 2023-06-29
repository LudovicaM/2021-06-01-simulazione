package it.polito.tdp.genes.model;

public class PesoAdiacenti implements Comparable<PesoAdiacenti>{
	
	private Genes gene;
	private double peso;
	
	
	public PesoAdiacenti(Genes gene, double peso) {
		super();
		this.gene = gene;
		this.peso = peso;
	}


	public Genes getGene() {
		return gene;
	}


	public void setGene(Genes gene) {
		this.gene = gene;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	@Override
	public int compareTo(PesoAdiacenti o) {
		// TODO Auto-generated method stub
		Double peso1 = o.getPeso();
		Double peso2 = this.peso;
		return peso1.compareTo(peso2);
	}


	@Override
	public String toString() {
		return gene.getGeneId() + " " + peso ;
	}
	
	
	
	

}
