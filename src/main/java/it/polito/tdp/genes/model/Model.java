package it.polito.tdp.genes.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private List<Genes> genes;
	private Graph<Genes, DefaultWeightedEdge> grafo;
	private Map<String, Genes> mapGenes;
	private List<Interaction> interazioni;
	
	public void creaGrafo() {
		this.dao = new GenesDao();
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.genes = dao.getEssentialGenes();
		this.mapGenes = new HashMap<>();
		
		for (Genes g : this.genes) {
			this.mapGenes.put(g.getGeneId(), g);
		}
		
		Graphs.addAllVertices(this.grafo, this.genes);
		
		this.interazioni = dao.getInteraction(mapGenes);
		
		
		for (Interaction i : this.interazioni) {
			if (i.getGene1().getChromosome() != i.getGene2().getChromosome()) 
				Graphs.addEdge(this.grafo, i.getGene1(), i.getGene2(), Math.abs(i.getExpressionCorr()));
			else
				Graphs.addEdge(this.grafo, i.getGene1(), i.getGene2(), 2*(Math.abs(i.getExpressionCorr())));
		}
		
	}
	
	public List<PesoAdiacenti> getAdiacenti(Genes g){
		List<PesoAdiacenti> result = new LinkedList<>();
		PesoAdiacenti pa;
		Set<DefaultWeightedEdge> vicini = this.grafo.edgesOf(g);
		for (DefaultWeightedEdge e : vicini) {
			Genes ge = Graphs.getOppositeVertex(this.grafo, e, g);
			double peso = this.grafo.getEdgeWeight(e);
			pa = new PesoAdiacenti(ge, peso);
			result.add(pa);
		}
		Collections.sort(result);
		return result;
	}
	
	public List<Genes> getVertici(){
		return this.genes;
	}
	
	public int getNumV() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNumE() {
		return this.grafo.edgeSet().size();
	}
	
	public Map<Genes, Integer> simulaIngegneri(Genes start, int n){
		try {
			Simulator sim = new Simulator(start, n, this.grafo);
			sim.run();
			return sim.getGeniStudiati();
		}catch (IllegalArgumentException ex) {
			return null;
		}
	}
	
	
}
