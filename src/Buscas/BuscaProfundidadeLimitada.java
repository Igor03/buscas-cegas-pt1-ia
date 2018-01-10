package Buscas;

//import java.util.Collections;
import java.util.LinkedList;

import Problemas.*;
import Estruturas.*;

public class BuscaProfundidadeLimitada {
	
	public No no;
	public int profunfidadeGeral = -1;
	
	/* Uma ajuda para o Aprofundamento Iterativo */
	public boolean encontrou = false;
	
	Problema problema;
	
	LinkedList<No> borda = new LinkedList<No>();
	LinkedList<Estado> explorados = new LinkedList<Estado>();
	
	public void buscaEmProfundidadeLimitada (Problema problema, int maxProfundidade) {
		
		this.problema = problema;
		this.borda.add(new No(problema.getEstadoInicial()));
		
		while (true) {
			if (this.borda.isEmpty()) {
				this.encontrou = false;
				System.err.println("FALHA");
				return;
			}
				
			this.no = this.borda.removeFirst();
			no.profundidade = ++profunfidadeGeral;
			explorados.add(no.estado);
			
		
			if (this.no.profundidade == maxProfundidade) {
				this.encontrou = false;
				//System.err.println("O OBJETIVO NAO FOI ATINGIDO\nLIMITE DE PROFUNDIDADE ATINGIDO");
				mostrarCaminho(no);
				return;
			}
			
			if (problema.testeDeObjetivo(no.estado)) {
				this.encontrou = true;
				mostrarCaminho(no);
				System.out.println("-----------------------------------");
				System.err.println("Objetivo alcancado!!!");
				System.out.println("-----------------------------------");
				return;
			}
			
			LinkedList<No> expandidos = expandir(no);
			
			for (int i=0; i<expandidos.size(); i++)
				if (!this.explorados.contains(expandidos.get(i).estado)) {
					this.borda.addFirst(expandir(no).get(i));
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
			sucessores.addFirst(s);
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
	
	/* Funcao de testes */
	public static void main (String[] args) {
		
		BuscaProfundidadeLimitada agente = new BuscaProfundidadeLimitada();
		ProblemaMapaRomenia problema = new ProblemaMapaRomenia("Arad", "Bucareste");
		
		//ProblemaAspirador problema = new ProblemaAspirador("ESS");
		
		agente.buscaEmProfundidadeLimitada(problema, 10);

		
	}

}
