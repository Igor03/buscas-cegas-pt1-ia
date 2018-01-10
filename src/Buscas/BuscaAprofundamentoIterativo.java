package Buscas;

import Problemas.*;

public class BuscaAprofundamentoIterativo {
	
	public int profundidade = 0;
	Problema problema;
	
	public void DFS_aprof_iter (Problema problema) {
		
		BuscaProfundidadeLimitada agente = new BuscaProfundidadeLimitada();
		
		agente.problema = problema;
		agente.buscaEmProfundidadeLimitada(problema, this.profundidade);
		
		boolean encontrou = agente.encontrou;
		
		while (!encontrou) {
			
			System.out.println("\n######################\n");
			BuscaProfundidadeLimitada bAux = new BuscaProfundidadeLimitada();
			bAux.buscaEmProfundidadeLimitada(problema, ++this.profundidade);
			encontrou = bAux.encontrou;
			System.gc(); // Executando o Garbage Collector
		}
	}
	
	public static void main (String[] args) {
		
		BuscaAprofundamentoIterativo agente = new BuscaAprofundamentoIterativo();
		ProblemaMapaRomenia problema = new ProblemaMapaRomenia("Oradea", "Iasi");
		
		agente.DFS_aprof_iter(problema);
		
	}
	
}
