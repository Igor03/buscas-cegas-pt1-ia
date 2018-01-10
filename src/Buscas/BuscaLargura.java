package Buscas;

//import java.util.Collections;
import java.util.LinkedList;
import Problemas.*;
import Estruturas.*;

public class BuscaLargura {
	
	public No no;
	public int profunfidadeGeral = -1;
	
	Problema problema;
	LinkedList<No> borda = new LinkedList<No>(); 
	/* O livro poe uma lista de explorados apesar da busca em si ja ser completa */
	LinkedList<Estado> explorados = new LinkedList<Estado>();
	
	public void BFS (Problema problema) {
		
		this.problema = problema;
		this.borda.add(new No(problema.getEstadoInicial()));
		
		while (true) {
			if (this.borda.isEmpty()) {
				System.err.println("FALHA");
				return;
			}
				
			this.no = this.borda.removeFirst();
			//System.out.println("no autual - "+no.estado.nome);
			no.profundidade = ++profunfidadeGeral;
			explorados.add(no.estado);
									
			if (problema.testeDeObjetivo(no.estado)) {
				mostrarCaminho(no);
				System.out.println("-----------------------------------");
				System.err.println("Objetivo alcancado!!!");
				System.out.println("-----------------------------------");
				return;
			}
			
			LinkedList<No> expandidos = expandir(no);
			
			for (int i=0; i<expandidos.size(); i++) {
				if (!this.explorados.contains(expandidos.get(i).estado)) {
					this.borda.addLast(expandir(no).get(i));
				}
			}
		}
		
	}
	
	public LinkedList<No> expandir (No no) {
		
		LinkedList<No> sucessores = new LinkedList<No>();
		
		for (int i=0; i<problema.funcaoSucessora(no.estado).size(); i++) {
			No s = new No(problema.funcaoSucessora(no.estado).get(i));
			s.estado = problema.funcaoSucessora(no.estado).get(i);
			s.pai = no;
			s.acao = problema.funcaoSucessora(no.estado).get(i);
			s.profundidade = profunfidadeGeral+1;
			sucessores.add(s);
		}
		//Collections.shuffle(sucessores);
		return sucessores;
	}
	
	public void mostrarCaminho (No no) {
		
		No noAux = no;
		LinkedList<No> caminho = new LinkedList<No>();
				
		while (noAux != null) {
			caminho.addFirst(noAux);
			noAux = noAux.pai;
		}
		
		System.out.println("-----------------------------------");
		System.out.println("Estado inicial: "+this.problema.getNomeEstadoInicial());
		System.out.println("Objetivo: "+this.problema.getNomeObjetivo());
		System.out.println("-----------------------------------");
		
		for (int i=0; i<caminho.size(); i++) {
			if (i+1<caminho.size()) {
				System.out.println(caminho.get(i).estado.nome+" --> "+caminho.get(i+1).estado.nome);
			}
		}
		//System.out.println("Profundidade: "+caminho.size());
		System.out.println("Profundidade: "+no.profundidade);
	}
	
	/* Funcao para testes */
	public static void main (String[] args) {
		
		BuscaLargura agente = new BuscaLargura();
		
		ProblemaMapaRomenia problema = new ProblemaMapaRomenia("Arad", "Iasi");
		//ProblemaAspirador problema = new ProblemaAspirador("ESS");
		
		agente.BFS(problema);
		
		
	}

}
